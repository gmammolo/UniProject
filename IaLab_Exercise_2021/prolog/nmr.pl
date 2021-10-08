/*
 * su prolog non esiste il not logico.
 * si usa la NEGAZIONE PER FALLIMENTO.
 * su Prolog si può negare un goal.
 * Esempio Uccello e Pinguino.
 * Gli uccelli volano ma i pinguini no. Non è possibile inserire il goal \+penguin(X):-fly(X),
 * ma è possibile aggiungere la negazione del goal penguin alla definizione di fly
 */

fly(X):-bird(X), \+penguin(X).
bird(X):-penguin(X).
bird(tweety).
penguin(tux).



% [3,1,2] U [4,2,5,1] => [1,2,3,4,5]
%unione/3 -unione(+A, +B, -AunitoB)
unione([],B,B).
unione([Head|Tail],B,AunitoB ):-
    member(Head,B),
    unione(Tail,B,AunitoB).
unione([Head|Tail],B,[Head|AunitoB] ):-
    true,
    \+member(Head,B),
    unione(Tail,B,AunitoB).


/*
 * predicato che data una lista di interi, conta quanti sono positivi
 */
% contaPositivi/2 - contaPositivi(+Lista, -NumeroPositivi)
contaPositivi([],0).
contaPositivi([Head|Tail], NumeroPositivi):-
    Head>0,
    !, % blocca il backtracking, evità cioè che una volta superato Head>0 non cerchi più altri valori 
       % (e quindi è possibile evitare il controllo nell'altro contaPositivi). ATTENZIONE a non usarlo con delle liste
    contaPositivi(Tail, PosInTail),
    NumeroPositivi is PosInTail+1.

contaPositivi([_|Tail], PosInTail):-
    contaPositivi(Tail, PosInTail).


% [3,1,2] U [4,2,5,1] => [1,2,3,4,5]
% Variante con l'uso del cut (!)
%unionecut/3 -unionecut(+A, +B, -AunitoB)
unionecut([],B,B).
unionecut([Head|Tail],B,AunitoB ):-
    member(Head,B),
    !,
    unionecut(Tail,B,AunitoB).
unionecut([Head|Tail],B,[Head|AunitoB] ):-
    unionecut(Tail,B,AunitoB).
