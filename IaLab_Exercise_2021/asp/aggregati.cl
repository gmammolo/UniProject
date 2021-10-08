tipo(integer).
tipo(char).
tipo(float).
variabile(x).
variabile(y).

% aggregato:
1 {ha_tipo(V,T):tipo(T)} 1:- variabile(V).
% Per ogni istanziazione di V, se V è variable, è vero un fatto ha_tipo(V,T) dove T ha la proprietà tipo.
% I due numeri rappresentano rispettivamewnte il numero minimo e massimo di occorrenze che risultano vere in variabile(V)
% che vanno prese:
% --> 1 {ha_tipo(V,T):tipo(T)} 1:- variabile(V).
% ha 9 Answer set e sono tutte le possibili combinazioni con 1  `ha_tipo(x,TIPO)` e `ha_tipo(y,TIPO)` al suo interno
% --> 2 {ha_tipo(V,T):tipo(T)} 2:- variabile(V).
% ha 9 Answer set e sono tutte le possibili combinazioni con 1 coppia di  `ha_tipo(x,TIPO)` e `ha_tipo(y,TIPO)` al suo interno
% --> 0 {ha_tipo(V,T):tipo(T)} 3:- variabile(V).
% ad esempio accetterà tra le soluzioni che hanno da 0 fino a 3 occorrenze di `ha_tipo(x,TIPO)` e `ha_tipo(y,TIPO)`   
% --> 3 {ha_tipo(V,T):tipo(T)} 3:- variabile(V).
% ha 1 solo Answer set dato da tutti e 6 i valori nei fatti: ha_tipo(x,integer) ha_tipo(x,char) ha_tipo(x,float) ha_tipo(y,integer) ha_tipo(y,char) ha_tipo(y,float)

% per rendere la scrittura dei risultati meno verbosa si può mettere una direttiva #show che mostra meno 
% risultati nascondendo gli altri inutili perché sempre veri.
#show ha_tipo/2.