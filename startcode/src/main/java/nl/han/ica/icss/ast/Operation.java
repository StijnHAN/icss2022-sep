package nl.han.ica.icss.ast;

import java.util.ArrayList;

public abstract class Operation extends Expression {

    public Expression lhs;
    public Expression rhs;

    public int calculate(){
        return 0;
    }

    @Override
    public ASTNode replaceChild(ASTNode oldChild, ASTNode newChild) {
        if (lhs.equals(oldChild)) {
            lhs = (Expression) newChild;
        } else if (rhs.equals(oldChild)) {
            rhs = (Expression) newChild;
        }

        return this;
    }

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if (lhs != null)
            children.add(lhs);
        if (rhs != null)
            children.add(rhs);
        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if (lhs == null) {
            lhs = (Expression) child;
        } else if (rhs == null) {
            rhs = (Expression) child;
        }
        return this;
    }
}
