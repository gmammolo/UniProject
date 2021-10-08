% Su un'isola ci sono due tipi di persone:
% - onesti, che dicono sempre il vero
% - bugiardi, che dicono sempre il falso

% Incontriamo due persone: A e B.
% A ci dice che sia A che B sono bugiardi
% 1) come classifichiamo A e B?
persona(a; b).
tipo(onesto; bugiardo).
1 {ha_tipo(P,T) : tipo(T)} 1 :- persona(P).

dice_il_vero(a) :- ha_tipo(a,bugiardo), ha_tipo(b,bugiardo).

:- ha_tipo(a,bugiardo), dice_il_vero(a).
:- ha_tipo(a,onesto), not dice_il_vero(a).

#show ha_tipo/2.