:- dynamic trasforma/3, applicabile/2, finale/1, iniziale/1.
% file di esecuzione del labirinto: implementa una strategia di ricerca
% RUN:
% LABIRINTO SEMPLICE
% ?- ['labirinto/labirintoSemplice.pl', 'labirinto/azioniLabirinto.pl', 'labirinto/run.pl'].
% ?- ricerca_soluzione(L), write(L), length(L,Length).
% LABIRINTO GRANDE
% ?- ['labirinto-grande.model.pl', 'labirinto.action.pl', 'dfs.action.pl'].
% ?- ricerca_soluzione(L), write(L), length(L,Length).



/*
 *  il consult carica automaticamente altri file pl e quindi permette di rimuovere i warning
 *  ma ci vincola all' utilizzo di quei file
 */
% :-consult('azioniLabirinto.pl').

% ricerca_soluzione/1 - ricerca_soluzione(-ListaAzioni)
ricerca_soluzione(ListaAzioni):-
    iniziale(S0),
    dfs_(S0,[S0],ListaAzioni).

/*
 * Write mostra la lista anche se è molto lunga
 * ?- ricerca_soluzione(Lista), write(Lista).
 */

% dfs/2 - dfs(+S, +StatiVisitati, -ListaAzioni)
dfs_(S,_, []):-finale(S),!.
dfs_(S,StatiVisitati,[Azione|ListaAzioni]):-
    applicabile(Azione,S),
    trasforma(Azione,S,SNuovo),
    \+member(SNuovo,StatiVisitati),
    dfs_(SNuovo,[SNuovo|StatiVisitati] ,ListaAzioni).

