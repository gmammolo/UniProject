/*
 * Esercizio che mostra il costrutto lista.
 * Somma gli elementi di una lista
 */

/*
 * INFO SULLE LISTE:
 * ?- [Head|Tail] = [1,ciao,'questa è una stringa',4].
 * Head = 1,
 * Tail = [ciao, 'questa è una stringa', 4].
 */


/*
 * per fare una somma si usano 2 parametri, dove il + rappresentra un valore di input e il - un valore di output.
 * Non è possibile fare direttamente una somma come SommaTail+Head perchè il + è a sua volta una regola e quindi equivalew a scrivere +(SommaTail,Head).
 * Per fare la somma si crea quindi una clausula con is.
 */

% somma/2 - somma(+Lista, -ValoreDellaSomma)
somma([], 0).
somma([Head|Tail],SommaTotale):-somma(Tail, SommaTail), SommaTotale is SommaTail+Head.


/*
Nota: nel caso del primo appartiene la lista dovrebbe essere [X|Tail], ma la variabile Tail non sarà poi più usata.
in questo caso si può usare il carattere '_' che rappresenta "don't care", quindi la variabile verrà ignorata
*/
% appartiene/2 - appartiene(X, Lista)
appartiene(X, [X|_]).
appartiene(X, [Y|Tail]):-
    X \== Y,
    appartiene(X, Tail).

/*
 *   SELECT
 *   esiste in prolog la select che permette di estrarre un elemento dalla lista ed ottenere una lista ripulita da quel valore
 *   Es:
 *
 *   ?- select(ciccio, [1,2,ciccio,3,4], Ris).
 *   Ris = [1, 2, 3, 4] 
 *
 */


/*
 * NOTA1: append - unisce due liste.
 * NOTA2: inverti([X,Y], [Y,X]) non è necessario, ma potrebbe rendere l'esecuzione più veloce 
 */
% inverti/2 - inverti (+Lista, -ListaInversa)
inverti([], []).
% inverti([X,Y], [Y,X]).
inverti([Head|Tail], Inversa):- 
    inverti(Tail, TailInv),
    append(TailInv,[Head], Inversa).

/*
 * Variante dell' inverti che deve evitare di usare l'append, che è computazionalmente molto pesante.
 * ?- invertiVeloce([1,2,3],[],Ris).
 * Ris = [3, 2, 1].
 */
% invertiVeloce/3 - invertiVeloce (+Lista, +InversaAccumulata, -ListaInversa)
invertiVeloce([], InversaAccumulata, InversaAccumulata).
invertiVeloce([Head|Tail],InversaAccumulata, ListaInversa):-
    invertiVeloce(Tail, [Head|InversaAccumulata], ListaInversa).

/*
 * inserisciVeloce è scomodo nell' utilizzo perchè necessita 2 input,
 * dove il secondo è sempre []. 
 * E' possibile però creare una regola che nasconda gli input non necessari: 
 */
% invertiOpt/2 - invertiOpt(+Lista, -ListaInversa)
invertiOpt(Lista, ListaInversa) :- invertiVeloce(Lista,[], ListaInversa).