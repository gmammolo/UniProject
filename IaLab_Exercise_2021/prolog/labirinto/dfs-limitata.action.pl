:- dynamic num_righe/1, num_righe/1, trasforma/3, applicabile/2, iniziale/1, finale/1.
% ricerca in profondità con profondità limitata

% LABIRINTO SEMPLICE
% ?- ['labirinto-semplice.model.pl', 'labirinto.action.pl', 'dfs-limitata.action.pl'].
% ?- ricerca_soluzione(20,L), write(L), length(L,Length).
% LABIRINTO GRANDE
% ?- ['labirinto-grande.model.pl', 'labirinto.action.pl', 'dfs-limitata.action.pl'].
% ?- ricerca_soluzione(20,L), write(L), length(L,Length).


/*
 * effettua una ricerca iterativa del percorso minimo:
 * Calcola la Profondità minima e la profondità massima del labirinto e poi cerca la soluzione ottima
 * andando a cercare una soluzione a metà della profondità 
 */
% ricerca_iterativa/1 - ricerca_iterativa(-ListaAzioni)
ricerca_iterativa(ListaAzioni):-
    num_righe(NR),
    num_righe(NC),
    iniziale(pos(SX,SY)),
    finale(pos(GX,GY)),
    ProfMinima is (GY-SY)+(GX-SX),
    ProfMassima is NR*NC,
    sub_ricerca_iterativa_(ProfMinima, ProfMassima, ListaAzioni).


sub_ricerca_iterativa_(ProfMinima, ProfMassima, ListaAzioni):-
    ProfTest is ProfMassima/2+1,
    ProfTest > ProfMinima,
    ricerca_soluzione(ProfTest,ListaAzioni),
    sub_ricerca_iterativa_(ProfMinima, ProfTest, ListaAzioni).

sub_ricerca_iterativa_(ProfMinima, ProfMassima, ListaAzioni):-
    ProfTest is ProfMassima/2+1,
    ProfTest > ProfMinima,
    ricerca_soluzione(ProfTest,ListaAzioni),
    \+sub_ricerca_iterativa_(ProfMinima, ProfTest, ListaAzioni).



% ricerca_soluzione/2 - ricerca_soluzione(+ProfMassima, -ListaAzioni)
ricerca_soluzione(ProfMassima, ListaAzioni):-
    iniziale(S0),
    dfs_limitata_(ProfMassima,S0, [S0], ListaAzioni).

% dfs_limitata/4 - dfs_limitata(+ProfMassima, +S, +Visitati, -ListaAzioni)
dfs_limitata_(_, S, _, []):-finale(S),!.
dfs_limitata_(ProfMassima,S,Visitati,[Azione|ListaAzioni]):-
    ProfMassima > 0,
    NextProfMassima is ProfMassima-1,
    applicabile(Azione,S),
    trasforma(Azione,S,SNuovo),
    \+member(SNuovo, Visitati),
    dfs_limitata_(NextProfMassima, SNuovo, [SNuovo|Visitati], ListaAzioni).