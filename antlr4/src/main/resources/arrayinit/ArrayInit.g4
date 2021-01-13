
grammar ArrayInit;
@header{package com.newland.lzj.antlr4.arrayinit.core;}
init    : '{' value (',' value)* '}';
value   : init
        | INT
        ;

INT :   [0-9]+;
WS  :   [ \r\t\n] -> skip;