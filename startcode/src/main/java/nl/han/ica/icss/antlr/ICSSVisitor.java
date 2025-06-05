// Generated from C:/Users/stijn/OneDrive/Documents/APP/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.2
package nl.han.ica.icss.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ICSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ICSSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRule(ICSSParser.RuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#regularDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularDeclaration(ICSSParser.RegularDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#colorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorDeclaration(ICSSParser.ColorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#sizeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSizeDeclaration(ICSSParser.SizeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#regularValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularValue(ICSSParser.RegularValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#colorValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorValue(ICSSParser.ColorValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ICSSParser#sizeValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSizeValue(ICSSParser.SizeValueContext ctx);
}