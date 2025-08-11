package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;

import java.util.HashMap;

public class Evaluator implements Transform {
    private IHANLinkedList<HashMap<String, Literal>> variableValues;

    public Evaluator() {
        variableValues = new HANLinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new HANLinkedList<>();

        applyNode(ast.root);
    }

    private void applyNode(ASTNode astNode) {
        for (ASTNode checkedNode : astNode.getChildren()) {
            if (checkedNode instanceof IfClause) {
                applyIfClause((IfClause) checkedNode, astNode);
            } else if (checkedNode instanceof Expression) {
                applyExpression((Expression) checkedNode);
            }
            applyNode(checkedNode);
        }
    }

//    private void applyVariableAssignment(VariableAssignment checkedNode) {
//        System.out.println(checkedNode.getNodeLabel());
//    }

    private void applyExpression(Expression expression) {
        System.out.println(expression.getNodeLabel());
    }

    private void applyIfClause(IfClause ifClause, ASTNode parent) {
        Expression conditionalExpression = ifClause.conditionalExpression;
        ElseClause elseClause = ifClause.elseClause;

        if (evaluateConditionalExpression(conditionalExpression)) {
            parent.replaceChild(ifClause, ifClause.body);
        } else if (elseClause != null) {
            parent.replaceChild(ifClause, elseClause.body);
        } else {
            parent.removeChild(ifClause);
        }
    }

    private boolean evaluateConditionalExpression(Expression conditionalExpression) {
        if (conditionalExpression instanceof Literal) {
            return checkLiteral((Literal) conditionalExpression);
        }

        if (conditionalExpression instanceof VariableReference) {
            //TODO Doe iets met VariableValues
        }

        if (conditionalExpression instanceof Operation) {
            //TODO ??
        }

        return false;
    }

    private boolean checkLiteral(Literal conditionalExpression) {
        Literal trueComparator = new BoolLiteral(true);

        return conditionalExpression.equals(trueComparator);
    }

//    private void applyElseClause() {
//        System.out.println("ElseClause");
//    }
}
