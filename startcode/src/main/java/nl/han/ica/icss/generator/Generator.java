package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;

public class Generator {

    private StringBuilder cssBuilder;
    private int scopeLevel;

    public Generator() {
        this.cssBuilder = new StringBuilder();
        scopeLevel = 0;
    }

    public String generate(AST ast) {
        generateStylesheet(ast.root);
        return cssBuilder.toString();
    }

    private void generateStylesheet(Stylesheet root) {
        for (ASTNode astNode : root.getChildren()) {
            if (astNode instanceof Stylerule) {
                generateStylerule((Stylerule) astNode);
            }
        }
    }

    private void generateStylerule(Stylerule stylerule) {
        scopeLevel++;

        for (ASTNode astNode : stylerule.getChildren()) {
            if (astNode instanceof Selector) {
                generateSelector((Selector) astNode);
            } else if (astNode instanceof Declaration) {
                generateDeclaration((Declaration) astNode);
            }
        }

        cssBuilder.append("}\n");
        scopeLevel--;
    }

    private void generateDeclaration(Declaration declaration) {
        cssBuilder.append("  ".repeat(scopeLevel));
        generatePropertyName(declaration.property);
        generateExpression(declaration.expression);
        cssBuilder.append(";")
                .append("\n");
    }

    private void generatePropertyName(PropertyName propertyName) {
        cssBuilder.append(propertyName.name)
                .append(": ");
    }

    private void generateExpression(Expression expression) {
        if (expression instanceof Literal) {
            generateLiteral((Literal) expression);
        }
    }

    private void generateLiteral(Literal literal) {
        if (literal instanceof BoolLiteral) {
            generateBoolLiteral((BoolLiteral) literal);
        } else if (literal instanceof ColorLiteral) {
            generateColorLiteral((ColorLiteral) literal);
        } else if (literal instanceof PercentageLiteral) {
            generatePercentageLiteral((PercentageLiteral) literal);
        } else if (literal instanceof PixelLiteral) {
            generatePixelLiteral((PixelLiteral) literal);
        } else if (literal instanceof ScalarLiteral) {
            generateScalarLiteral((ScalarLiteral) literal);
        }
    }

    private void generateBoolLiteral(BoolLiteral boolLiteral) {
        cssBuilder.append(boolLiteral.value);
    }

    private void generateColorLiteral(ColorLiteral colorLiteral) {
        cssBuilder.append(colorLiteral.value);
    }

    private void generatePercentageLiteral(PercentageLiteral percentageLiteral) {
        cssBuilder.append(percentageLiteral.value)
                .append("%");
    }

    private void generatePixelLiteral(PixelLiteral pixelLiteral) {
        cssBuilder.append(pixelLiteral.value)
                .append("px");
    }

    private void generateScalarLiteral(ScalarLiteral scalarLiteral) {
        cssBuilder.append(scalarLiteral.value);
    }

    private void generateSelector(Selector selector) {
        cssBuilder.append(selector.toString())
                .append(" {")
                .append("\n");
    }
}
