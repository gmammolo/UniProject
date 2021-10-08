# IALab - Intelligenza artificiale e Laboratorio  2021/2022

Raccolta personale di esercitazioni e suggerimenti per lo sviluppo 
di applicazioni tramite prolog

## Installazioni

indicazioni generali per Distro Debian based.

- **Swipl**: Aggiungete la PPA ed installate il package. Su vscode installare VSC-Prolog per il supporto
- **Clingo**: Compilate da source oppure installarlo con anaconda

[guida caricata su moodle](https://informatica.i-learn.unito.it/mod/forum/discuss.php?d=30866)

## Esacuzione

### Prolog

```sh
$ swipl
```

per comodità è stato creato il file sh per avviarlo nella direcotir corretta

```sh
$ sh ./run_prolog.sh
```

caricare il file 
```prolog
?- ['nome_file.pl'].
# restituisce true se il caricamento è andato a buon fine
?- regola(A,B).
```