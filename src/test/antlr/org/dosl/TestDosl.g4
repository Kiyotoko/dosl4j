grammar TestDosl;

root: (item)* EOF;
item: (group | path | comment) ;
group: (path|label+) '{' body '}' ;
body: (item)* ;
path: (label)* PATH ;
label: '@' NAME ;
comment: BLOCKCOMMENT
       | LINECOMMENT;

PATH: '"' [a-zA-Z0-9/\\&?=.:\-+~_]+ '"';
NAME: [a-zA-Z0-9]+ ;
BLOCKCOMMENT: '/*' ().*? '*/' ;
LINECOMMENT: '//'().*?'\n';
WS: [ \t\n\r]+ -> skip;
