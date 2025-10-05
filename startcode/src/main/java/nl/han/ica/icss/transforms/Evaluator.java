package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;

public class Evaluator implements Transform {
    private IHANLinkedList<HashMap<String, Literal>> variableValues;

    public Evaluator() {
        variableValues = new HANLinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new HANLinkedList<>();

        applyStylesheet(ast.root);
    }

    private void applyStylesheet(Stylesheet stylesheet) {
        for (ASTNode astNode : stylesheet.getChildren()) {
            if (astNode instanceof Stylerule) {
                applyStyleRule((Stylerule) astNode);
            } else if (astNode instanceof VariableAssignment) {
                applyVariableAssignment((VariableAssignment) astNode);
            }
        }
    }

    private void applyStyleRule(Stylerule stylerule) {
        for (ASTNode astNode : stylerule.getChildren()) {
            if (astNode instanceof Selector) {
                applySelector((Selector) astNode);
            } else if (astNode instanceof Declaration) {
                applyDeclaration((Declaration) astNode);
            } else if (astNode instanceof VariableAssignment) {
                applyVariableAssignment((VariableAssignment) astNode);
            } else if (astNode instanceof IfClause) {
                applyIfClause((IfClause) astNode, stylerule);
            }
        }
    }

    private void applySelector(Selector selector) {
        //TODO Empty
    }

    private void applyDeclaration(Declaration declaration) {
        for (ASTNode astNode : declaration.getChildren()) {
            if (astNode instanceof Operation) {
                applyOperation((Operation) astNode, declaration);
            } else if (astNode instanceof VariableReference) {
                applyVariableReference((VariableReference) astNode, declaration);
            }
        }
    }

    private void applyOperation(Operation operation, ASTNode parent) {
        for (ASTNode astNode : operation.getChildren()) {
            if (astNode instanceof VariableReference) {
                applyVariableReference((VariableReference) astNode, operation);
            } else if (astNode instanceof Operation) {
                applyOperation((Operation) astNode, operation);
            }
        }

        ExpressionType operationExpressionType = assignOperationExpressionType(operation.lhs, operation.rhs);

        Literal newLiteral;

        if (operationExpressionType == ExpressionType.PIXEL) {
            newLiteral = new PixelLiteral(operation.calculate());
        } else if (operationExpressionType == ExpressionType.PERCENTAGE) {
            newLiteral = new PercentageLiteral(operation.calculate());
        } else {
            newLiteral = new ScalarLiteral(operation.calculate());
        }

        parent.replaceChild(operation, newLiteral);
    }

    private ExpressionType assignOperationExpressionType(Expression lhs, Expression rhs) {
        if (lhs instanceof PercentageLiteral || rhs instanceof PercentageLiteral) {
            return ExpressionType.PERCENTAGE;
        } else if (lhs instanceof PixelLiteral || rhs instanceof PixelLiteral) {
            return ExpressionType.PIXEL;
        }

        return ExpressionType.SCALAR;
    }

    private void applyVariableReference(VariableReference variableReference, ASTNode parent) {
        for (int i = 0; i < variableValues.getSize(); i++) {
            if (variableValues.get(i).containsKey(variableReference.name)) {
                parent.replaceChild(variableReference, variableValues.get(i).get(variableReference.name));
            }
        }
    }

    private void applyVariableAssignment(VariableAssignment variableAssignment) {
        updateVariableValues(variableAssignment);
        applyExpression(variableAssignment.expression, variableAssignment);
    }

    private void updateVariableValues(VariableAssignment variableAssignment) {
        HashMap<String, Literal> temp;
        temp = new HashMap<>();

        temp.put(variableAssignment.name.name, (Literal) variableAssignment.expression);

        if (!updateExistingVariables(variableAssignment.name.name, (Literal) variableAssignment.expression)) {
            variableValues.addFirst(temp);
        }
    }

    private boolean updateExistingVariables(String key, Literal value) {
        for (int i = 0; i < variableValues.getSize(); i++) {
            if (variableValues.get(i).containsKey(key)) {
                variableValues.get(i).put(key, value);
                return true;
            }
        }

        return false;
    }

    private void applyExpression(Expression expression, ASTNode parent) {
        if (expression instanceof Operation) {
            applyOperation((Operation) expression, parent);
        } else if (expression instanceof VariableReference) {
            applyVariableReference((VariableReference) expression, parent);
        }
    }

    private void applyIfClause(IfClause ifClause, ASTNode parent) {
        Expression conditionalExpression = ifClause.conditionalExpression;
        ElseClause elseClause = ifClause.elseClause;

        if (evaluateConditionalExpression(conditionalExpression)) {
            parent.replaceChild(ifClause, ifClause.body);
            for (ASTNode astNode : ifClause.body) {
                if (astNode instanceof Declaration) {
                    applyDeclaration((Declaration) astNode);
                } else if (astNode instanceof VariableAssignment) {
                    applyVariableAssignment((VariableAssignment) astNode);
                } else if (astNode instanceof IfClause) {
                    applyIfClause((IfClause) astNode, ifClause);
                }
            }
        } else if (elseClause != null) {
            parent.replaceChild(ifClause, elseClause.body);
            applyElseClause(elseClause);
        } else {
            parent.removeChild(ifClause);
        }
    }

    private void applyElseClause(ElseClause elseClause) {
        for (ASTNode astNode : elseClause.getChildren()) {
            if (astNode instanceof Declaration) {
                applyDeclaration((Declaration) astNode);
            } else if (astNode instanceof VariableAssignment) {
                applyVariableAssignment((VariableAssignment) astNode);
            } else if (astNode instanceof IfClause) {
                applyIfClause((IfClause) astNode, elseClause);
            }
        }
    }

    private boolean evaluateConditionalExpression(Expression conditionalExpression) {
        if (conditionalExpression instanceof Literal) {
            return checkLiteral((Literal) conditionalExpression);
        }

        if (conditionalExpression instanceof VariableReference) {
            for (int i = 0; i < variableValues.getSize(); i++) {
                if (variableValues.get(i).containsKey(((VariableReference) conditionalExpression).name)
                        && ((BoolLiteral) variableValues.get(i).get(((VariableReference) conditionalExpression).name)).value) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkLiteral(Literal conditionalExpression) {
        Literal trueComparator = new BoolLiteral(true);

        return conditionalExpression.equals(trueComparator);
    }
}
