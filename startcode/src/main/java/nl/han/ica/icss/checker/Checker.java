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

import java.util.HashMap;

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
        //TODO CH02 & CH03

        Expression lhs = addOperation.lhs;
        Expression rhs = addOperation.rhs;

        if (!compareSides(lhs, rhs)) {
            addOperation.setError("Min en Plus operanden moeten hetzelfde type hebben");
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

    private boolean compareSides(Expression lhs, Expression rhs) {
        ExpressionType lhsType = null;
        ExpressionType rhsType = null;

        if (lhs instanceof VariableReference) {
            lhsType = declaredVariables.get(((VariableReference) lhs).name);
        } else if (lhs instanceof MultiplyOperation) {
            lhsType = getOperationType((Operation) lhs);
        } else {
            for (int i = 0; i < variableTypes.getSize(); i++) {
                if (variableTypes.get(i).containsKey(lhs.getClass().getSimpleName())) {
                    lhsType = variableTypes.get(i).get(lhs.getClass().getSimpleName());
                    break;
                }
            }
        }

        if (rhs instanceof VariableReference) {
            rhsType = declaredVariables.get(((VariableReference) rhs).name);
        } else if (rhs instanceof Operation) {
            rhsType = getOperationType((Operation) rhs);
        } else {
            for (int i = 0; i < variableTypes.getSize(); i++) {
                if (variableTypes.get(i).containsKey(rhs.getClass().getSimpleName())) {
                    rhsType = variableTypes.get(i).get(rhs.getClass().getSimpleName());
                    break;
                }
            }
        }

        return lhsType.equals(rhsType) || lhsType == ExpressionType.SCALAR || rhsType == ExpressionType.SCALAR;
    }

    private ExpressionType getOperationType(Operation operation) {
        Expression lhs = operation.lhs;
        Expression rhs = operation.rhs;

        ExpressionType lhsType;
        ExpressionType rhsType;

        if (lhs instanceof VariableReference) {
            return declaredVariables.get(((VariableReference) lhs).name);
        } else if (lhs instanceof MultiplyOperation) {
            return getOperationType((Operation) lhs);
        } else {
            for (int i = 0; i < variableTypes.getSize(); i++) {
                if (variableTypes.get(i).containsKey(lhs.getClass().getSimpleName())) {
                    lhsType = variableTypes.get(i).get(lhs.getClass().getSimpleName());
                    if (lhsType == ExpressionType.SCALAR) {
                        break;
                    }
                    return lhsType;
                }
            }
        }

        if (rhs instanceof VariableReference) {
            return declaredVariables.get(((VariableReference) rhs).name);
        } else if (rhs instanceof Operation) {
            return getOperationType((Operation) rhs);
        } else {
            for (int i = 0; i < variableTypes.getSize(); i++) {
                if (variableTypes.get(i).containsKey(rhs.getClass().getSimpleName())) {
                    rhsType = variableTypes.get(i).get(rhs.getClass().getSimpleName());
                    if (rhsType == ExpressionType.SCALAR) {
                        break;
                    }
                    return rhsType;
                }
            }
        }

        return ExpressionType.SCALAR;
    }

    private void checkSubtractOperation(SubtractOperation subtractOperation) {
        //TODO CH02 & CH03

        Expression lhs = subtractOperation.lhs;
        Expression rhs = subtractOperation.rhs;

        if (!compareSides(lhs, rhs)) {
            subtractOperation.setError("Min en Plus operanden moeten hetzelfde type hebben");
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
        //TODO CH02 & CH03

        Expression lhs = multiplyOperation.lhs;
        Expression rhs = multiplyOperation.rhs;

        if (!compareSides(lhs, rhs)) {
            multiplyOperation.setError("Min en Plus operanden moeten hetzelfde type hebben");
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
