package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Checker {

    private HANLinkedList<String> currentValidScopes = new HANLinkedList<>();

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;
    private HashMap<String, ExpressionType> declaredVariables;
    private HashMap<String, String> variableScopes;

    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        declaredVariables = new HashMap<>();
        variableScopes = new HashMap<>();
        currentValidScopes.addFirst("body");

        Stylesheet stylesheet = ast.root;

        setVariableTypes();
        checkStylesheet(stylesheet);
    }

    private void setVariableTypes() {
        HashMap<String, ExpressionType> temp;

        for (int i = 0; i < ExpressionType.values().length; i++) {
            ExpressionType value = ExpressionType.values()[i];

            temp = new HashMap<>();
            temp.put(capitalize(value.name().toLowerCase()) + "Literal", value);
            variableTypes.addFirst(temp);
        }
    }

    private void checkStylesheet(Stylesheet stylesheet) {
        for (ASTNode astNode : stylesheet.getChildren()) {
            if (astNode instanceof Stylerule) {
                checkStyleRule((Stylerule) astNode);
            } else if (astNode instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) astNode);
            }
        }
    }

    private void checkStyleRule(Stylerule styleRule) {

        for (ASTNode astNode : styleRule.getChildren()) {
            if (astNode instanceof Selector) {
                checkSelector((Selector) astNode);
            } else if (astNode instanceof Declaration) {
                checkDeclaration((Declaration) astNode);
            } else if (astNode instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) astNode);
            } else if (astNode instanceof IfClause) {
                checkIfClause((IfClause) astNode);
            }
        }

        currentValidScopes.removeFirst();
    }

    private void checkSelector(Selector selector) {
        currentValidScopes.addFirst(selector.getNodeLabel());
    }

    private void checkDeclaration(Declaration declaration) {
        ExpressionType valueExpressionType = assignExpressionType(declaration.expression);
        String propertyName = declaration.property.name;

        if (propertyName.equals("color") || propertyName.equals("background-color")) {
            if (valueExpressionType != ExpressionType.COLOR) {
                declaration.setError("Value klopt niet met property");
            }
        } else if (propertyName.equals("width") || propertyName.equals("height")) {
            if (valueExpressionType != ExpressionType.PIXEL && valueExpressionType != ExpressionType.PERCENTAGE) {
                declaration.setError("Value klopt niet met property");
            }
        }

        for (ASTNode astNode : declaration.getChildren()) {
            if (astNode instanceof Operation) {
                checkOperation((Operation) astNode);
            } else if (astNode instanceof VariableReference) {
                checkVariableReference((VariableReference) astNode);
            }
        }
    }

    private void checkVariableAssignment(VariableAssignment variableAssignment) {
        if (!declaredVariables.containsKey(variableAssignment.name.name)) {
            declaredVariables.put(variableAssignment.name.name, assignExpressionType(variableAssignment.expression));
            variableScopes.put(variableAssignment.name.name, currentValidScopes.getFirst());
        }
    }

    private ExpressionType assignExpressionType(Expression expression) {
        if (expression instanceof VariableReference) {
            return declaredVariables.get(((VariableReference) expression).name);
        } else if (expression instanceof Operation) {
            return prioritizeTypes(getOperationTypes((Operation) expression));
        } else {
            for (int i = 0; i < variableTypes.getSize(); i++) {
                if (variableTypes.get(i).containsKey(expression.getClass().getSimpleName())) {
                    return variableTypes.get(i).get(expression.getClass().getSimpleName());
                }
            }
        }

        return ExpressionType.UNDEFINED;
    }

    private void checkVariableReference(VariableReference variableReference) {
        if (!declaredVariables.containsKey(variableReference.name)) {
            variableReference.setError("Variabele moet gedefinieerd worden: " + variableReference.name);
        } else if (!checkIfScopeIsValid(variableReference.name)) {
            variableReference.setError("Variabele is niet gedefinieerd in deze scope");
        }
    }

    private boolean checkIfScopeIsValid(String name) {
        for (int i = currentValidScopes.getSize() - 1; i >= 0; i--) {
            if (currentValidScopes.get(i).equals(variableScopes.get(name))) {
                return true;
            }
        }

        return false;
    }

    private void checkOperation(Operation operation) {
        if (operation instanceof AddOperation) {
            checkAddOperation((AddOperation) operation);
        }

        if (operation instanceof SubtractOperation) {
            checkSubtractOperation((SubtractOperation) operation);
        }

        if (operation instanceof MultiplyOperation) {
            checkMultiplyOperation((MultiplyOperation) operation);
        }

        List<ExpressionType> expressionTypeList = getOperationTypes(operation);

        ExpressionType lhs = expressionTypeList.get(0);
        ExpressionType rhs = expressionTypeList.get(1);

        if (checkForColors(lhs, rhs)) {
            operation.setError("Kleuren mogen niet gebruikt worden in operaties");
        }

        if (checkForBools(lhs, rhs)) {
            operation.setError("Booleans mogen niet gebruikt worden in operaties");
        }

        for (ASTNode astNode : operation.getChildren()) {
            if (astNode instanceof VariableReference) {
                checkVariableReference((VariableReference) astNode);
            } else if (astNode instanceof Operation) {
                checkOperation((Operation) astNode);
            }
        }
    }

    private void checkAddOperation(AddOperation addOperation) {
        List<ExpressionType> expressionTypeList = getOperationTypes(addOperation);

        ExpressionType lhs = expressionTypeList.get(0);
        ExpressionType rhs = expressionTypeList.get(1);

        if (((lhs != rhs)
                && (lhs != ExpressionType.SCALAR)
                && (rhs != ExpressionType.SCALAR))) {
            addOperation.setError("Min en Plus operanden moeten hetzelfde type hebben");
        }
    }

    private void checkSubtractOperation(SubtractOperation subtractOperation) {
        List<ExpressionType> expressionTypeList = getOperationTypes(subtractOperation);

        ExpressionType lhs = expressionTypeList.get(0);
        ExpressionType rhs = expressionTypeList.get(1);

        if (((lhs == rhs)
                && (lhs != ExpressionType.SCALAR)
                && (rhs != ExpressionType.SCALAR))) {
            subtractOperation.setError("Min en Plus operanden moeten hetzelfde type hebben");
        }
    }

    private void checkMultiplyOperation(MultiplyOperation multiplyOperation) {
        List<ExpressionType> expressionTypeList = getOperationTypes(multiplyOperation);

        ExpressionType lhs = expressionTypeList.get(0);
        ExpressionType rhs = expressionTypeList.get(1);

        if ((lhs == ExpressionType.SCALAR && rhs == ExpressionType.SCALAR)
                || (lhs != ExpressionType.SCALAR && rhs != ExpressionType.SCALAR)) {
            multiplyOperation.setError("Één van de operanden bij vermenigvuldigen moet scalar zijn");
        }
    }

    private boolean checkForColors(ExpressionType lhs, ExpressionType rhs) {
        return (lhs == ExpressionType.COLOR || rhs == ExpressionType.COLOR);
    }

    private boolean checkForBools(ExpressionType lhs, ExpressionType rhs) {
        return (lhs == ExpressionType.BOOL || rhs == ExpressionType.BOOL);
    }

    private List<ExpressionType> getOperationTypes(Operation operation) {
        List<ExpressionType> operationTypes = new ArrayList<>();

        Expression lhs = operation.lhs;
        Expression rhs = operation.rhs;

        operationTypes.add(assignExpressionType(lhs));
        operationTypes.add(assignExpressionType(rhs));

        return operationTypes;
    }

    private ExpressionType prioritizeTypes(List<ExpressionType> expressionType) {
        ExpressionType lhsType = expressionType.get(0);
        ExpressionType rhsType = expressionType.get(1);

        if (lhsType == rhsType) {
            return lhsType;
        } else if (lhsType == ExpressionType.SCALAR) {
            return rhsType;
        } else if (rhsType == ExpressionType.SCALAR) {
            return lhsType;
        }

        return ExpressionType.UNDEFINED;
    }

    private void checkIfClause(IfClause ifClause) {
        currentValidScopes.addFirst(ifClause.toString());

        if (assignExpressionType(ifClause.conditionalExpression) != ExpressionType.BOOL) {
            ifClause.setError("Expressies in if-statements moeten booleans zijn");
        }

        for (ASTNode astNode : ifClause.getChildren()) {
            if (astNode instanceof Operation) {
                checkOperation((Operation) astNode);
            } else if (astNode instanceof VariableReference) {
                checkVariableReference((VariableReference) astNode);
            } else if (astNode instanceof Declaration) {
                checkDeclaration((Declaration) astNode);
            } else if (astNode instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) astNode);
            } else if (astNode instanceof IfClause) {
                checkIfClause((IfClause) astNode);
            } else if (astNode instanceof ElseClause) {
                checkElseClause((ElseClause) astNode);
            }
        }

        currentValidScopes.removeFirst();
    }

    private void checkElseClause(ElseClause elseClause) {
        currentValidScopes.addFirst(elseClause.toString());

        for (ASTNode astNode : elseClause.getChildren()) {
            if (astNode instanceof Declaration) {
                checkDeclaration((Declaration) astNode);
            } else if (astNode instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) astNode);
            } else if (astNode instanceof IfClause) {
                checkIfClause((IfClause) astNode);
            }
        }

        currentValidScopes.removeFirst();
    }
}
