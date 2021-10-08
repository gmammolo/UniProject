# Social Project

> NOTA: Progetto universitario di Tecnologie Software.


 * URL: http://labappweb.labinfo.educ.di.unito.it/studenti/st116798/SocialProject
 * ADMIN: Admin:Qwe123
 * USER: 
  *  Topolino:Qwe123
  *  Pippo:Qwe123
  *  Pluto:Qwe123
  *  Paperino:Qwe123
  
## HOWTO
Il progetto dovrebbe partire senza problemi, tuttavia se si dovessero presentare problemi riguardanti il percorso è possibile modificare quest'ultimo
globalmente dentro i file di configurazioni, posizionati nella cartella config.
Un'altra possibile aggiunta (non necessaria al funzionamento, ma che implementa un ulteriore livello di sicurezza) è l'abilitazione dell' .htaccess (Qualora  apache non sia già configurato per attivarlo)

```
E' Opportuno attivare gli .htaccess aggiungendo al file /etc/apache2/sites-available/000-default.conf

<Directory "/var/www">
   AllowOverride All
</Directory>
```
> Nota: In questo modo  .htaccess presente nella dir principale del progetto impedirà l'accesso a qualsiasi file php diverso dall' index.php (i singoli file hanno in ogni caso i controlli che impediscono l'accesso non autorizzato, ma nessuno dovrebbe essere in grado di accedere direttamente a un file php che non sia tramite index.php, anche avendo gli accessi per vedere quella sezione )

## Realizzazione

Il progetto implementa il pattern mvc, prendendo anche un' ispirazione da Wordpress come struttura di realizzazione:
Il progetto sarà composto infatti oltre al Model, al Controller e alle View anche da una cartella **Template**.


### Database
* **User**: Contiene le informazioni principali dell' utente
* **Profile**: Con una relazione 1:1 con User, Contiene le altre informazioni personali dell' utente. E' stato pensato separatamente dall' Utente Semplicemente per predisporlo alla possibilità futura di avere multi-profili o magari cambiare profilo mantenendo i dati di login, lasciando comunque uno storico nel database.
* **Relationship**:Relazioni fra utenti diversi (Sia amicizie ma anche utenti bloccati)
* **Post**: I Post scritti dagli utenti. E' possibile citare altri utenti usano il @NOME_UTENTE oppure aggiungere hashtag #HashTag, oltre che condividere immagini
* **Comment**: I commenti ai post
* **Showcase**: La bacheca: serve per indicare quali post devono essere mostrati nella home. Tale Tabella si rende necessaria 
* **Notify**: Tabella usata per inviare le notifiche ai vari utenti

### Controller
Il Controller viene richiamato subito da index.php.
E' lui che esegue tutte le azioni in base alla variabile richiesta.
In particolare è il **MasterController.php** che oltre a generare il menu, richiama i vari sotto-controller in base alle richieste ricevute tramite POST o GET.
Alla fine di tutto si occupa anche di generare la pagina richiamando il Template.
In genere il controller si può comportare in due modi in base alle richieste:
nel caso di una richiesta ajax restituisce una porzione di html o in alcuni casi anche dei file JSON, nel caso di richieste non asincrone invece genera nuovamente tutta la pagina.

### Model
I model si interfacciano con il Database.
A ogni tabella del database corrisponde una corrispettiva classe php che si occupa di recuperare i dati dal database oppure effettuare le query per salvarli.
particolare importanza si può dare al **Model.php**, che è una classe astratta che estende poi tutte le altre classi Model.
Questa classe richiama la classe **Database.php** per effettuare le query. Per interfacciarsi al database sono stati usati i **PDO**.
In questa classe è possibile cambiare le configurazioni del database cambiando semplicemente delle variabili.

### VIEW

In questo progetto le view sono trattati di fatto come se fossero dei plugin da aggiungere al template. Sono dei "blocchi" che implementano una funzionalità.
Spesso questi rappresentano il contenuto di un intera pagina, ma potrebbero anche essere solo una parte di essa. Il Controller posiziona la view corretta all' interno del template.
Se ad esempio siamo interessati a vedere il profilo di un utente il controller caricherà la view Profile.php e Showcase.php per vedere rispettivamente le informazioni dell' utente e i suou ultimi post in bacheca.

### Template
Il template contiene la struttura della pagina, in cui saranno inseriti i contenuti come una sorta di "hook semplificati". L'idea di base è quella di avere la possibilità di cambiare il Template della pagina agevolmente, senza intaccare il contenuto del SocialWeb.
i componenti fondamentali sono:
* **ManageTemplate.php**: Mette a disposizione delle funzioni che permettono di aggiungere agevolmente css, js, voci del menu e contenuto da visualizzare in una pagina. Il tutto viene fatto caricando degli array che saranno poi usati per inserire il contenuto una volta che si genera la pagina.
* **page.php**: è il Template della pagina. Cambiando questo file è possibile cambiare l'aspetto del Social Network, lasciando inalterato (o quasi) il contenuto dello stesso.

## Funzionalita
Attualmente il Social Network implementa tutte le richieste della consegna, con alcune piccole aggiunte.
Ad esempio, è possibile inserire hashtag nel commento oppure citare un amico nel commento usando @Nome_Utente.
Nelle varie barre di ricerca e nel caso si cerchi un amico si è supportati nella ricerca tramite funzioni js, che man mano si va avanti ti offrono suggerimenti su quello che stai scrivendo.


  
## Contenuto
```
/
├───Config
├───Controller
├───Model
├───Public
├───Template
│   ├───css
│   │   └───images
│   ├───fonts
│   ├───images
│   └───js
└───View
    ├───Account
    ├───Friends
    ├───Hashtag
    ├───Join
    ├───Login
    ├───News
    ├───Notify
    ├───PrivateArea
    ├───SearchBar
    ├───Showcase
    └───Statistiche
        └───Grafici
```
