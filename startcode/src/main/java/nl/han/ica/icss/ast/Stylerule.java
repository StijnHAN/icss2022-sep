package nl.han.ica.icss.ast;

import java.util.ArrayList;
import java.util.Objects;

public class Stylerule extends ASTNode {

    public ArrayList<Selector> selectors = new ArrayList<>();
    public ArrayList<ASTNode> body = new ArrayList<>();

    public Stylerule() {
    }

    public Stylerule(Selector selector, ArrayList<ASTNode> body) {

        this.selectors = new ArrayList<>();
        this.selectors.add(selector);
        this.body = body;
    }

    @Override
    public String getNodeLabel() {
        return "Stylerule";
    }

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        children.addAll(selectors);
        children.addAll(body);

        return children;
    }

    @Override
    public ASTNode addChild(ASTNode child) {
        if (child instanceof Selector)
            selectors.add((Selector) child);
        else
            body.add(child);

        return this;
    }

    @Override
    public ASTNode removeChild(ASTNode child) {
        if (child instanceof Selector) {
            selectors.remove(child);
        } else {
            body.remove(child);
        }

        return this;
    }

    @Override
    public ASTNode replaceChild(ASTNode oldChild, ASTNode newChild) {
        int index;

        if (newChild instanceof Selector) {
            index = selectors.indexOf(oldChild);

            removeChild(oldChild);
            selectors.add(index, (Selector) newChild);
        } else {
            index = body.indexOf(oldChild);

            removeChild(oldChild);
            body.add(index, newChild);
        }

        return this;
    }

    @Override
    public ASTNode replaceChild(ASTNode oldChild, ArrayList<ASTNode> newChild) {
        int index;
        
        if (newChild.get(0) instanceof Selector) {
            index = selectors.indexOf(oldChild);
        } else {
            index = body.indexOf(oldChild);
        }

        removeChild(oldChild);

        for (int i = 0; i < newChild.size(); i++) {
            if (newChild.get(i) instanceof Selector) {
                selectors.add(index + i, (Selector) newChild.get(i));
            } else {
                body.add(index + i, newChild.get(i));
            }
        }

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Stylerule stylerule = (Stylerule) o;
        return Objects.equals(selectors, stylerule.selectors) &&
                Objects.equals(body, stylerule.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selectors, body);
    }
}
