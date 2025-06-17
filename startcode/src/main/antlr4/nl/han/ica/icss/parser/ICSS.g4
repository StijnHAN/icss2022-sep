grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//--- PARSER: ---
stylesheet: (styleRule | variableAssignment)* EOF;

styleRule: selector OPEN_BRACE (declaration | variableAssignment | ifClause)+ CLOSE_BRACE;

selector: classSelector | idSelector | tagSelector;
classSelector: CLASS_IDENT;
idSelector: ID_IDENT;
tagSelector: LOWER_IDENT;

declaration: propertyName COLON expression SEMICOLON;

propertyName: 'color' | 'background-color' | 'width' | 'height' ;

expression: literal | operation | variableReference;
//expressionType: ;

literal: boolLiteral | colorLiteral | percentageLiteral | pixelLiteral | scalarLiteral;
boolLiteral: TRUE | FALSE;
colorLiteral: COLOR;
percentageLiteral: PERCENTAGE;
pixelLiteral: PIXELSIZE;
scalarLiteral: SCALAR;

variableAssignment: variableReference ASSIGNMENT_OPERATOR expression SEMICOLON;
variableReference: CAPITAL_IDENT;

ifClause: IF BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE OPEN_BRACE (declaration | variableAssignment | ifClause)+ CLOSE_BRACE elseClause?;
elseClause: ELSE OPEN_BRACE (declaration | variableAssignment | ifClause)+ CLOSE_BRACE;

operation: addOperation | subtractOperation | multiplyOperation;
multiplyOperation: (literal | variableReference) MUL (literal | variableReference);
addOperation: (literal | variableReference | multiplyOperation) PLUS (literal | variableReference | operation);
subtractOperation: (literal | variableReference | multiplyOperation) MIN (literal | variableReference | operation);
