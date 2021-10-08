/*
 * $ swipl run.pl hello darkness my old friend
 * Example script
 * Called with [hello,darkness,my,old,friend]
 */ 

% TODO: scegliere quale labirinto risolvere
:- consult('labirinto-semplice.model.pl').
% :- consult('labirinto-grande.model.pl').
% end labirinto


:- consult('labirinto.action.pl').
:- consult('dfs-limitata.action.pl').

% :- set_prolog_flag(verbose, silent).
:- initialization(main).
:- dynamic ricerca_soluzione/1.

main :-
    format('Soluzione labirinto con dfs ad altezza limitata~n'),
    ricerca_soluzione(L),  
    length(L,Length),
    format('Length: ~d ~n', Length),
    format('soluzione: ~k ~n',[L]),
    /* il sistema permette di leggere le variabili in input (guardare sopra) */
    % current_prolog_flag(argv, Argv),
    % format('Called with ~q~n', [Argv]),
    halt.
main :-
    halt(1).