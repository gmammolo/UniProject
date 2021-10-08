# Esercizio 3.1

## Legenda  
start := S  
expr := E  
exprp := E'  
term := T    
termp := T'  
fact := F  

## GRAMMATICA   
S -> E  
E -> TE'  
E' -> +TE' | -TE' | ε  
T -> FT'  
T'-> * FT'| /FT' | ε  
F -> (E) | NUM


## FIRST

FST(S) = FST(E) =  ***{ ( , NUM}***    
FST(E) = FST (T)=  ***{ ( , NUM}***  
FST(E') = ***{ ε , + , -}***  
FST(T) = FST(F) = ***{ ( , NUM}***  
FST(T') = ***{ ε , **** *** , / }***  
FST(F) =  ***{ ( , NUM}***  

## FOLLOW

FW(S) = ***{ $ }***  
FW(E) = FW(S) + { ) } =   ***{ $, ) }***  
FW(E') = FW(E) = ***{ $, ) }***  
FW(T) = FST(E')+FW(E) +FW(E') = ***{ $, ) +, - }***  
FW(T') = FW(T) = FST(T') +FW(T)+FW(T')=  ***{ $, ), +, - }***  
FW(F) = ***{ $, ), +, - , **** *** , / }***  
1
## Tabella di Parsing
|    | NUM      | +          | -          | *          | /          | (        | )     | $     |
|----|----------|------------|------------|------------|------------|----------|-------|-------|
| S  | S -> E   |            |            |            |            | S -> E   |       |       |
| E  | E -> TE' |            |            |            |            | E -> TE' |       |       |
| E' |          | E' -> +TE' | E' -> -TE' |            |            |          | E'->ε | E'->ε |
| T  | T -> FT' |            |            |            |            | T -> FT' |       |       |
| T' |          | T'->ε      | T'->ε      | T'-> * FT' | T'-> / FT' |          | T'->ε | T'->ε |
|  F | F -> NUM |            |            |            |            | F -> (E) |       |       |       |  

### Note:
Tabella generata su http://www.tablesgenerator.com/markdown_tables.  
Per comodità sarà inserito anche un salvataggio del file per modificarla nella stessa directory.
