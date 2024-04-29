grammar Dosl;

root: (item)* EOF;
item: (group | path | comment) ;
group: (path|label+) '{' body '}' ;
body: (item)* ;
path: (label)* PATH ;
label: '@' NAME ;
comment: COMMENT ;

PATH: '"' [a-zA-Z0-9/\\&?=.:\-+~_]+ '"';
NAME: [a-zA-Z0-9]+ ;
COMMENT: '/*' ().*? '*/' ;
WS: [ \t\n\r]+ -> skip;
