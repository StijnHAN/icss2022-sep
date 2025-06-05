// Generated from C:/Users/stijn/OneDrive/Documents/APP/icss2022-sep/startcode/src/main/antlr4/nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.13.2
package nl.han.ica.icss.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#rule}.
	 * @param ctx the parse tree
	 */
	void enterRule(ICSSParser.RuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#rule}.
	 * @param ctx the parse tree
	 */
	void exitRule(ICSSParser.RuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(ICSSParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ICSSParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#regularDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterRegularDeclaration(ICSSParser.RegularDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#regularDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitRegularDeclaration(ICSSParser.RegularDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#colorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterColorDeclaration(ICSSParser.ColorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#colorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitColorDeclaration(ICSSParser.ColorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#sizeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSizeDeclaration(ICSSParser.SizeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#sizeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSizeDeclaration(ICSSParser.SizeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(ICSSParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#regularValue}.
	 * @param ctx the parse tree
	 */
	void enterRegularValue(ICSSParser.RegularValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#regularValue}.
	 * @param ctx the parse tree
	 */
	void exitRegularValue(ICSSParser.RegularValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#colorValue}.
	 * @param ctx the parse tree
	 */
	void enterColorValue(ICSSParser.ColorValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#colorValue}.
	 * @param ctx the parse tree
	 */
	void exitColorValue(ICSSParser.ColorValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#sizeValue}.
	 * @param ctx the parse tree
	 */
	void enterSizeValue(ICSSParser.SizeValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#sizeValue}.
	 * @param ctx the parse tree
	 */
	void exitSizeValue(ICSSParser.SizeValueContext ctx);
}