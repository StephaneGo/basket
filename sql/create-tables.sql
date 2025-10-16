create table EQUIPES(  
    noEquipe int NOT NULL PRIMARY KEY IDENTITY,  
    nom varchar(30)
    );  

ALTER TABLE EQUIPES
ADD CONSTRAINT unique_nom UNIQUE (nom);


create table JOUEURS(
	noJoueur int NOT NULL PRIMARY KEY IDENTITY,
	prenom varchar(30) not null,
	nom  varchar(30) not null,
	email varchar(30) null,
	noEquipe int not null)

alter table JOUEURS 
add foreign key (noEquipe) references EQUIPES(noEquipe)