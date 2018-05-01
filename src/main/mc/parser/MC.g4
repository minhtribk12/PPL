/**
 * Student name: Nguyen Minh Tri
 * Student ID: 1770026
 */
grammar MC;

@lexer::header{
	package mc.parser;
}

@lexer::members{
@Override
public Token emit() {
    switch (getType()) {
    case UNCLOSE_STRING:       
        Token result = super.emit();
        // you'll need to define this method
        throw new UncloseString(result.getText());
        
    case ILLEGAL_ESCAPE:
    	result = super.emit();
    	throw new IllegalEscape(result.getText());

    case ERROR_CHAR:
    	result = super.emit();
    	throw new ErrorToken(result.getText());	

    default:
        return super.emit();
    }
}
}

@parser::header{
	package mc.parser;
}

options{
	language=Java;
}
/* 
program  : mctype 'main' LB RB LP body? RP EOF ;

mctype: INTTYPE | VOIDTYPE ;

body: funcall SEMI;

exp: funcall | INTLIT ;

funcall: ID LB exp? RB ;
*/
program: declaration+ ;

declaration: vardecl | funcdecl ;

vardecl: primitivetype varlist SEMI ;

primitivetype: INTTYPE | FLOATTYPE | BOOLTYPE | STRINGTYPE ;

varlist: varname (CM varname)* ;

varname: ID (LSB INTLIT RSB)? ;

funcdecl: functype ID LB paralist RB block ;

functype: (primitivetype (LSB RSB)?) | VOIDTYPE ;

paralist: (paradecl (CM paradecl )*)? ;

paradecl: primitivetype ID (LSB RSB)? ;

block: LP blockbody RP ;

blockbody: declpart stmtpart ;

declpart: vardecl* ;

stmtpart: stmt* ;

stmt: ifstmt | whilestmt | forstmt | breakstmt | continuestmt | returnstmt | expression SEMI | block ;

ifstmt: ifelse | ifnoelse ;

ifelse: IF LB expression RB stmtif ELSE stmt ;

stmtif: ifelse | whilestmt | forstmt | breakstmt | continuestmt | returnstmt | expression SEMI | block ;

ifnoelse: IF LB expression RB stmt ;

whilestmt: DO stmt* WHILE expression SEMI ;

forstmt: FOR LB expression SEMI expression SEMI expression RB  stmt ;

breakstmt: BREAK SEMI ;

continuestmt: CONTINUE SEMI ;

returnstmt: RETURN SEMI | RETURN expression SEMI ;

// expression: operand
//         |   LB expression RB
//         |   naexpression LSB expression RSB
//         |   <assoc=right> (SUB|NOT) expression
//         |   <assoc=left> expression (DIV | MUL | MOL) expression
//         |   <assoc=left> expression (ADD | SUB) expression
//         |   naexpression (LT | LEQ | GT | GEQ) naexpression // non-associate expression
//         |   naexpression (EQ | NEQ) naexpression
//         |   <assoc=left> expression AND expression
//         |   <assoc=left> expression OR expression
//         |   <assoc=right> expression ASSIGN expression 
//         ;

// naexpression: operand
//         |   LB expression RB
//         |   <assoc=right> (SUB|NOT) naexpression
//         |   <assoc=left> naexpression (DIV | MUL | MOL) naexpression
//         |   <assoc=left> naexpression (ADD | SUB) naexpression 
//         ;

expression: <assoc=right> expression ASSIGN expression | exp1 ;

exp1: <assoc=left> exp1 OR exp1 | exp2 ;

exp2: <assoc=left> exp2 AND exp2 | exp3 ;

exp3: exp5 (EQ | NEQ) exp5 | exp4 ;

exp4: exp5 (LT | LEQ | GT | GEQ) exp5 | exp5 ;

exp5: <assoc=left> exp5 (ADD | SUB) exp5 | exp6 ;

exp6: <assoc=left> exp6 (DIV | MUL | MOL) exp6 | exp7 ;

exp7: <assoc=right> (SUB|NOT) exp7 | exp8 ; 

exp8: exp9 LSB expression RSB | exp9 ;

exp9: LB expression RB | operand ;

operand: literal | ID | funcall ;

literal: INTLIT | FLOATLIT | STRINGLIT | BOOLLIT ;

funcall: ID LB arglist RB ;

arglist: (expression (CM expression)*)?;

LSB: '[' ;

RSB: ']' ;

CM: ',' ;

COMMENT: (('/*' .*? '*/') | ('//' ~[\r\n]*)) -> skip ;

BOOLLIT: 'true' | 'false' ;

BOOLTYPE: 'boolean' ;

INTTYPE: 'int' ;

INTLIT: DIGIT+ ;

FLOATTYPE: 'float' ;

FLOATLIT: FLOATDIGIT EXP? | DIGIT+EXP;

fragment EXP: [eE](SUB)?INTLIT; //notice 1e01

fragment FLOATDIGIT: DIGIT+'.'DIGIT* | DIGIT*'.'DIGIT+ ;

fragment DIGIT: [0-9] ;

STRINGTYPE: 'string';

STRINGLIT: '"' ( ('\\'[bfrnt'"\\] ) | (~[\r\n"\\]) )* '"' {String s = getText(); setText(s.substring(1, s.length() - 1));};

VOIDTYPE: 'void' ;

LB: '(' ;

RB: ')' ;

LP: '{';

RP: '}';

SEMI: ';' ;

IF: 'if' ;

ELSE: 'else' ;

FOR: 'for' ;

WHILE: 'while' ;

DO: 'do' ;

BREAK: 'break' ;

CONTINUE: 'continue' ;

RETURN: 'return' ;

ADD: '+' ;

SUB: '-' ;

MUL: '*' ;

DIV: '/' ;

LT: '<' ;

GT: '>';

LEQ: '<=' ;

GEQ: '>=' ;

EQ: '==' ;

NOT: '!' ;

OR: '||';

NEQ: '!=' ;

ASSIGN: '=' ;

MOL: '%' ;

AND: '&&' ;

ID: [_a-zA-Z][_a-zA-Z0-9]* ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

ILLEGAL_ESCAPE: '"' (NOTESCAPE? '\\' (~[bfrnt'"\\])  NOTESCAPE?)+ '"';

fragment NOTESCAPE: (~[\n\r])* ;

UNCLOSE_STRING: '"' (('\\''"') | ~('"'))* EOF ;

ERROR_CHAR: .;