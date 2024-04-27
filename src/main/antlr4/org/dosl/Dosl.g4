grammar Dosl;

root: (group)* EOF;
item: (group | path) ;
group: (path|label+) '{' body '}' ;
body: (item)* ;
path: (label)* PATH ;
label: '@' NAME ;

PATH: '"' [a-zA-Z0-9/\\&?=.:\-]+ '"';
NAME: [a-zA-Z0-9]+ ;
WS: [ \t\n\r]+ -> skip;