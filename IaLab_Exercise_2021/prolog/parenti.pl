/* Primo esercizio con Prolog.
 * Si testano delle prime regole 
 */

/*
 * NOTA: per abilitare il debug scrivere trace.
 * per disattivarlo nodebug.
 * per uscire da swipl scrivere halt.
 */
genitore(edoardo,gianni).
genitore(edoardo,clara).
genitore(edoardo,susanna).
genitore(edoardo,umberto).
genitore(edoardo,mariasole).
genitore(edoardo,giorgio).
genitore(edoardo,cristiana).
genitore(virginiaBourbon,gianni).
genitore(virginiaBourbon,clara).
genitore(virginiaBourbon,susanna).
genitore(virginiaBourbon,umberto).
genitore(virginiaBourbon,mariasole).
genitore(virginiaBourbon,giorgio).
genitore(virginiaBourbon,cristiana).
genitore(gianni,margherita).
genitore(marellaCaracciolo,margherita).
genitore(gianni,edoardo2).
genitore(marellaCaracciolo,edoardo2).
genitore(margherita,johnElkann).
genitore(alainElkann,johnElkann).
genitore(margherita,lapoElkann).
genitore(alainElkann,lapoElkann).
genitore(margherita,ginevraElkann).
genitore(alainElkann,ginevraElkann).
genitore(umberto,giovanni).
genitore(antonellaBechiPiaggio,giovanni).
genitore(umberto,andrea).
genitore(umberto,anna).
genitore(allegraCaracciolo,andrea).
genitore(allegraCaracciolo,anna).

% antenato/2 - antenato(X,Y)
antenato(X,Y):-genitore(X,Y).
antenato(X,Y):-genitore(X,Z), antenato(Z,Y).

% discendente(X,Y):-genitore(Y,X).
% discendente(X,Y):-genitore(Z,X), discendente(Y,Z).

discendente(X,Y):-antenato(Y,X).

% fratelloGermano/2 - fratelloGermano(X,Y)
fratelloGermano(X,Y):-
    genitore(PrimoGenitore,X),
    genitore(SecondoGenitore,X),
    PrimoGenitore \== SecondoGenitore,
    genitore(PrimoGenitore,Y),
    X \== Y,
    genitore(SecondoGenitore,Y).

% fratelloUnilaterale/2 - fratelloUnilaterale(X,Y)
fratelloUnilaterale(X,Y):-
    genitore(GenitoreComune, X),
    genitore(GenitoreComune, Y),
    X \== Y,
    % TODO: alternativamente è possibile escludere i  fratelliGermano e terminare qui la regola.
    % non sono fratelliGermano(X,Y)
    genitore(AltroGenitoreX, X),
    AltroGenitoreX \== GenitoreComune,
    genitore(AltroGenitoreY, Y),
    AltroGenitoreY \== GenitoreComune,
    AltroGenitoreX \== AltroGenitoreY.
    