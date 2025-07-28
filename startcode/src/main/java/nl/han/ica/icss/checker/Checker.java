package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;
    private HashMap<String, ExpressionType> declaredVariables;

    public void check(AST ast) {
        variableTypes = new HANLinkedList<>();
        declaredVariables = new HashMap<>();

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
            if (astNode instanceof ClassSelector) {
                checkClassSelector((ClassSelector) astNode);
            } else if (astNode instanceof IdSelector) {
                checkIdSelector((IdSelector) astNode);
            } else if (astNode instanceof TagSelector) {
                checkTagSelector((TagSelector) astNode);
            } else if (astNode instanceof Declaration) {
                checkDeclaration((Declaration) astNode);
            } else if (astNode instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) astNode);
            } else if (astNode instanceof IfClause) {
                checkIfClause((IfClause) astNode);
            }
        }
    }

    private void checkSelector(Selector selector) {
        //TODO
    }

    private void checkClassSelector(ClassSelector astNode) {
        //TODO
    }

    private void checkIdSelector(IdSelector astNode) {
        //TODO
    }

    private void checkTagSelector(TagSelector astNode) {
        //TODO
    }

    private void checkDeclaration(Declaration declaration) {
        for (ASTNode astNode : declaration.getChildren()) {
            if (astNode instanceof PropertyName) {
                checkPropertyName((PropertyName) astNode);
            } else if (astNode instanceof Literal) {
                checkLiteral((Literal) astNode);
            } else if (astNode instanceof AddOperation) {
                checkAddOperation((AddOperation) astNode);
            } else if (astNode instanceof SubtractOperation) {
                checkSubtractOperation((SubtractOperation) astNode);
            } else if (astNode instanceof MultiplyOperation) {
                checkMultiplyOperation((MultiplyOperation) astNode);
            } else if (astNode instanceof VariableReference) {
                checkVariableReference((VariableReference) astNode);
            }
        }
    }

    private void checkExpression(Expression expression) {
        //TODO
    }

    private void checkLiteral(Literal literal) {
        //TODO
    }

    private void checkPropertyName(PropertyName propertyName) {
        //TODO
    }

    private void checkVariableAssignment(VariableAssignment variableAssignment) {
        declaredVariables.put(variableAssignment.name.name, assignType(variableAssignment.expression));
    }

    private ExpressionType assignType(Expression expression) {
        if (expression instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        } else if (expression instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        } else if (expression instanceof ColorLiteral) {
            return ExpressionType.COLOR;
        } else if (expression instanceof ScalarLiteral) {
            return ExpressionType.SCALAR;
        } else if (expression instanceof BoolLiteral) {
            return ExpressionType.BOOL;
        } else {
            return ExpressionType.UNDEFINED;
        }
    }

    private void checkVariableReference(VariableReference variableReference) {
        //TODO CH06
        if (!declaredVariables.containsKey(variableReference.name)) {
            variableReference.setError("Variabelen moeten gedefinieerd worden: " + variableReference.name);
        }
    }

    private void checkOperation(Operation operation) {
        //TODO
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

        if (checkForColors(lhs, rhs)) {
            addOperation.setError("Kleuren mogen niet gebruikt worden in operaties");
        }

        for (ASTNode astNode : addOperation.getChildren()) {
            if (astNode instanceof Literal) {
                checkLiteral((Literal) astNode);
            } else if (astNode instanceof VariableReference) {
                checkVariableReference((VariableReference) astNode);
            } else if (astNode instanceof MultiplyOperation) {
                checkMultiplyOperation((MultiplyOperation) astNode);
            } else if (astNode instanceof AddOperation) {
                checkAddOperation((AddOperation) astNode);
            } else if (astNode instanceof SubtractOperation) {
                checkSubtractOperation((SubtractOperation) astNode);
            }
        }
    }

    private boolean checkForColors(ExpressionType lhs, ExpressionType rhs) {
        return (lhs == ExpressionType.COLOR || rhs == ExpressionType.COLOR);
    }

    private List<ExpressionType> getOperationTypes(Operation operation) {
        List<ExpressionType> operationTypes = new ArrayList<>();

        Expression lhs = operation.lhs;
        Expression rhs = operation.rhs;

        ExpressionType lhsType;
        ExpressionType rhsType;

        if (lhs instanceof VariableReference) {
            operationTypes.add(declaredVariables.get(((VariableReference) lhs).name));
        } else if (lhs instanceof Operation) {
            operationTypes.add(prioritizeTypes(getOperationTypes((Operation) lhs)));
        } else {
            for (int i = 0; i < variableTypes.getSize(); i++) {
                if (variableTypes.get(i).containsKey(lhs.getClass().getSimpleName())) {
                    lhsType = variableTypes.get(i).get(lhs.getClass().getSimpleName());
                    operationTypes.add(lhsType);
                }
            }
        }

        if (rhs instanceof VariableReference) {
            operationTypes.add(declaredVariables.get(((VariableReference) rhs).name));
        } else if (rhs instanceof Operation) {
            operationTypes.add(prioritizeTypes(getOperationTypes((Operation) rhs)));
        } else {
            for (int i = 0; i < variableTypes.getSize(); i++) {
                if (variableTypes.get(i).containsKey(rhs.getClass().getSimpleName())) {
                    rhsType = variableTypes.get(i).get(rhs.getClass().getSimpleName());
                    operationTypes.add(rhsType);
                }
            }
        }

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

    private void checkSubtractOperation(SubtractOperation subtractOperation) {

        List<ExpressionType> expressionTypeList = getOperationTypes(subtractOperation);

        ExpressionType lhs = expressionTypeList.get(0);
        ExpressionType rhs = expressionTypeList.get(1);

        if (((lhs == rhs)
                && (lhs != ExpressionType.SCALAR)
                && (rhs != ExpressionType.SCALAR))) {
            subtractOperation.setError("Min en Plus operanden moeten hetzelfde type hebben");
        }

        if (checkForColors(lhs, rhs)) {
            subtractOperation.setError("Kleuren mogen niet gebruikt worden in operaties");
        }

        for (ASTNode astNode : subtractOperation.getChildren()) {
            if (astNode instanceof Literal) {
                checkLiteral((Literal) astNode);
            } else if (astNode instanceof VariableReference) {
                checkVariableReference((VariableReference) astNode);
            } else if (astNode instanceof MultiplyOperation) {
                checkMultiplyOperation((MultiplyOperation) astNode);
            } else if (astNode instanceof AddOperation) {
                checkAddOperation((AddOperation) astNode);
            } else if (astNode instanceof SubtractOperation) {
                checkSubtractOperation((SubtractOperation) astNode);
            }
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

        if (checkForColors(lhs, rhs)) {
            multiplyOperation.setError("Kleuren mogen niet gebruikt worden in operaties");
        }

        for (ASTNode astNode : multiplyOperation.getChildren()) {
            if (astNode instanceof Literal) {
                checkLiteral((Literal) astNode);
            } else if (astNode instanceof VariableReference) {
                checkVariableReference((VariableReference) astNode);
            } else if (astNode instanceof AddOperation) {
                checkAddOperation((AddOperation) astNode);
            } else if (astNode instanceof SubtractOperation) {
                checkSubtractOperation((SubtractOperation) astNode);
            } else if (astNode instanceof MultiplyOperation) {
                checkMultiplyOperation((MultiplyOperation) astNode);
            }
        }
    }

    private void checkIfClause(IfClause ifClause) {
        //TODO CH05
        for (ASTNode astNode : ifClause.getChildren()) {
            if (astNode instanceof Literal) {
                checkLiteral((Literal) astNode);
            } else if (astNode instanceof AddOperation) {
                checkAddOperation((AddOperation) astNode);
            } else if (astNode instanceof SubtractOperation) {
                checkSubtractOperation((SubtractOperation) astNode);
            } else if (astNode instanceof MultiplyOperation) {
                checkMultiplyOperation((MultiplyOperation) astNode);
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
    }

    private void checkElseClause(ElseClause elseClause) {
        //TODO CH05
        for (ASTNode astNode : elseClause.getChildren()) {
            if (astNode instanceof Declaration) {
                checkDeclaration((Declaration) astNode);
            } else if (astNode instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) astNode);
            } else if (astNode instanceof IfClause) {
                checkIfClause((IfClause) astNode);
            }
        }
    }
}
