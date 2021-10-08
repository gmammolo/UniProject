/*
 * applicabile verifica che sia possibile prendere una direzione
 * 
 * 
 * ?- applicabile(Azione,pos(3,4)).
 *   Azione = nord ;
 *   Azione = sud ;
 *   Azione = est ;
 *   false.
 * % findAll può essere usato per avere una lista di tutti i valori
 * ?- findAll(Azione,applicabile(Azione,pos(3,4)),ListaAzioniApplicabili).
 *   Correct to: "findall(Azione,applicabile(Azione,pos(3,4)),ListaAzioniApplicabili)"? yes
 *   ListaAzioniApplicabili = [nord, sud, est].
 */
% applicabile/2 - applicabile(AZ,S)

applicabile(sud,pos(Riga,Colonna)):-
    num_righe(N),
    Riga<N,
    RigaSotto is Riga+1,
    \+occupata(pos(RigaSotto,Colonna)).

applicabile(est,pos(Riga,Colonna)):-
    num_colonne(N),
    Colonna<N,
    ColonnaDestra is Colonna+1,
    \+occupata(pos(Riga,ColonnaDestra)).

applicabile(ovest,pos(Riga,Colonna)):-
    Colonna>1,
    ColonnaSinistra is Colonna-1,
    \+occupata(pos(Riga,ColonnaSinistra)).

applicabile(nord,pos(Riga,Colonna)):-
    Riga>1,
    RigaSopra is Riga-1,
    \+occupata(pos(RigaSopra,Colonna)).


%  trasforma/3 - trasforma(AZ,S,SNuovo)
trasforma(nord,pos(Riga,Colonna),pos(RigaSopra, Colonna)):-RigaSopra is Riga-1.
trasforma(sud,pos(Riga,Colonna),pos(RigaSotto, Colonna)):-RigaSotto is Riga+1.
trasforma(est,pos(Riga,Colonna),pos(Riga, ColonnaDestra)):-ColonnaDestra is Colonna+1.
trasforma(ovest,pos(Riga,Colonna),pos(Riga, ColonnaSinistra)):-ColonnaSinistra is Colonna-1.

/**
 * ?- iniziale(S),applicabile(sud,S),trasforma(sud,S,NuovoStato).
 * S = pos(1, 1),
 * NuovoStato = pos(0, 1) .
 */