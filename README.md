# Software-Design-Project

Aceasta aplicație web este o revista online, care cuprinde articole din diferite categorii scrise de catre noi sau chiar culese de la pasionati. Orice doritor poate sa citeasca articolele, dar se poate si abona la revista si sa primeasca notificari despre o anumita categorie de articole sau noi articole de la un autor preferat.

### UML Diagram

![UML](https://github.com/andreeamircea16/Software-Design-Project/blob/master/src/main/resources/static/umlDiagram.png)

### Observable Design Pattern

Acest pattern defineste o relatie OneToMany intre obiecte, astfel incat atunci cand un obiect isi schimba starea, toate obiectele in relatie cu el sunt notificate si actualizate automat. Obiectul care este urmarit se numeste subiect. Obiectele care urmaresc schimbarile de stare se numesc observatori sau ascultatori.

In proiect am implementat acest pattern astfel: un user, daca are cont, poate sa urmareasca orice autor/autori. Atunci cand un autor posteaza un articol nou folosim metoda _notifySubscribers()_ care trece prin lista de utilizatori abonati la acel autor si apeleaza metoda _update()_ care actualizeaza o lista de articole ale autorilor la care utilizatorul este abonat.
