grammar TestDosl;

@header {
package org.dosl;
}

root: (entry)* EOF;
entry: (group | item | comment) ;
group: (label* PATH|label+) '{' body '}' ;
body: (entry)* ;
item: (label)* PATH ;
label: '@'NAME ;
comment: BLOCKCOMMENT
       | LINECOMMENT;

PATH: '"'~('\n'|'\t'|'\r')+'"' ;
NAME: [a-zA-Z0-9]+ ;
BLOCKCOMMENT: '/*' ().*? '*/' ;
LINECOMMENT: '//'().*?'\n';
WS: [ \t\n\r]+ -> skip;
