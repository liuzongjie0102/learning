grammar Hello;

@header{package com.newland.lzj.antlr4.hello.core;}

r   :   'hello' ID;

ID  :   [a-z]+ ;
WS  :   [ \t\r\n]+ -> skip ;