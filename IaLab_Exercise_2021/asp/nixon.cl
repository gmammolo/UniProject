% problemi diamond: i quacker (religione pacifista) mentre i repubblicani non sono pacifisti. 
% Nixon è sia quacker che repubblicano
%         pacifist
%         |       |
%        |         -
%       |           |
%     quacker     republican
%       |           |
%        |         |
%         |       |
%           Nixon 
% in questo caso ci asprettiamo che non dia risultati

pacifist(X):-quacker(X), not -pacifist(X).
-pacifist(X):-republican(X), not pacifist(X).
quacker(nixon).
republican(nixon).

% Da notare che clingo avvisa che ci sono più modelli ma ne mostra solo uno.
% $ clingo nixon.cl                           
% clingo version 5.4.0
% Reading from nixon.cl
% Solving...
% Answer: 1
% quacker(nixon) republican(nixon) -pacifist(nixon)
% SATISFIABLE

% Models       : 1+
% Calls        : 1
% Time         : 0.007s (Solving: 0.00s 1st Model: 0.00s Unsat: 0.00s)
% CPU Time     : 0.001s
%  ---------------------------------------
% Per avere tutti i modelli minimi si usa il parametro 0:
% clingo nixon.cl 0
% clingo version 5.4.0
% Reading from nixon.cl
% Solving...
% Answer: 1
% quacker(nixon) republican(nixon) -pacifist(nixon)
% Answer: 2
% quacker(nixon) republican(nixon) pacifist(nixon)
% SATISFIABLE

% Models       : 2
% Calls        : 1
% Time         : 0.001s (Solving: 0.00s 1st Model: 0.00s Unsat: 0.00s)
% CPU Time     : 0.001s