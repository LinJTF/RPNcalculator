# RPNcalculator

Essa atividade tem o intuito de realizar os cálculos através do Reverse Polish Notation (RPN).

# Exemplo

## Quando o arquivo está com formatação certa:

10 <br/>
10 <br/>
+

Token [type=NUM, lexeme=10] <br/>
Token [type=NUM, lexeme=10] <br/>
Token [type=PLUS, lexeme=+] <br/>

Saida: 20

## Quando o arquivo está com formatação errada:

10 <br/>
s <br/>
+ 

Error: Unexpected character: s
