# Software-Design-Project

## 1. Descriere problema
Aceasta aplicație web este o revista online, care cuprinde articole din diferite categorii scrise de catre noi sau chiar culese de la pasionati. Orice doritor poate sa citeasca articolele, dar se poate si abona la revista si sa primeasca notificari despre o anumita categorie de articole sau noi articole de la un autor preferat.

## 2. Solutie

### API (Application Programming Interface)

Un API este un set de functii si proceduri care permit crearea de aplicatii care acceseaza caracteristicile sau datele unui serviciu sau ale unei aplicatii.
Obiectele returnate sunt de tip JSON (JavaScript Object Notation). Am folosit header-ul _Content-Type: application/json_.

**Endpoints**

**Statusuri si mesaje:**
+ 200 _OK_ - request-ul a fost realizat cu succes 
+ 201 _CREATED_ - request-ul a fost realizat cu succes si a dus la crearea uneia sau a mai multor resurse noi
+ 400 _BAD_REQUESTS_ - serverul nu poate prelucra cererea din cauza unei o erori de sintaxa sau ruta
+ 404 _NOT_FOUND_ - nu poate fi gasita o resursa care sa indeplineasca solicitarea

## 3. Implementare

### UML Diagram

![UML](https://github.com/andreeamircea16/Software-Design-Project/blob/master/src/main/resources/static/umlDiagram.png)

### Observable Design Pattern

Acest pattern defineste o relatie OneToMany intre obiecte, astfel incat atunci cand un obiect isi schimba starea, toate obiectele in relatie cu el sunt notificate si actualizate automat. Obiectul care este urmarit se numeste subiect. Obiectele care urmaresc schimbarile de stare se numesc observatori sau ascultatori.

In proiect am implementat acest pattern astfel: un user, daca are cont, poate sa urmareasca orice autor/autori. Atunci cand un autor posteaza un articol nou folosim metoda _notifySubscribers()_ care trece prin lista de utilizatori abonati la acel autor si apeleaza metoda _update()_ care actualizeaza o lista de articole ale autorilor la care utilizatorul este abonat.


### Factory Design Pattern

Acest pattern este folosit cand avem o super-clasa cu multiple sub-clase si bazandu-ne pe un input, trebuie sa returnam una din sub-clase. Pattern-ul preia responsabilitatea instantierii unei clase din programul client in clasa factory.

Super-clasa poate fi o interfata, o clasa abstracta sau chiar si o clasa java normala. In proiect este folosita o interfata (UserOperations). Sub-clasele sunt 2 metode _createAdminUser()_ si _createUser()_, iar clasa de factory este metoda _createUserByType()_.

![UML](https://github.com/andreeamircea16/Software-Design-Project/blob/master/src/main/resources/static/FactoryDiagram.jpg)
