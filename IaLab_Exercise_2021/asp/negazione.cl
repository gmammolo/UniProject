bird(X):-penguin(X).
fly(X):-bird(X).
% -fly(X):-penguin(X).
fly(X):-bird(X), not -fly(X).

bird(tweety).
penguin(tux).
