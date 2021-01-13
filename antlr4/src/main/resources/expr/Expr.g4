grammar Expr;
prog : stat+ ;
stat : expr NEWLINE              # printExpr
     | ID '=' expr NEWLINE       # assign
     | NEWLINE                   # blank
     ;
expr : expr op=('*'|'/') expr    # MulDiv
     | expr op=('+'|'-') expr    # AddSub
     | INT                       # int
     | ID                        # id
     | '(' expr ')'              # parens
     ;
MUL : '*' ; // assigns token name to '*' used abve in grammar
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
ID : [a-zA-Z]+ ;          // 匹配字母
INT : [0-9]+ ;            // 匹配数字
NEWLINE : '\r'? '\n' ;    // 新的一行
WS : [ \t]+ -> skip ;     // 忽略空白字符
