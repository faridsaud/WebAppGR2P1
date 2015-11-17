/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     11/16/2015 8:21:30 PM                        */
/*==============================================================*/


drop table if exists CATEGORIA;

drop table if exists ITEM;

drop table if exists MULTIMEDIA;

drop table if exists REVIEW;

drop table if exists USUARIO;

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table CATEGORIA
(
   NOMBRECATEGORIA      varchar(20) not null,
   DESCRIPCIONCATEGORIA varchar(300),
   primary key (NOMBRECATEGORIA)
);

/*==============================================================*/
/* Table: ITEM                                                  */
/*==============================================================*/
create table ITEM
(
   IDITEM               int not null,
   NOMBRECATEGORIA      varchar(20),
   EMAILUSR             varchar(50),
   NOMBREITEM           varchar(100) not null,
   DESCRIPCIONITEM      varchar(300) not null,
   PATHIMAGENITEM       varchar(200),
   CALIFICACIONITEM     float(3),
   NUMVOTOSITEM         int,
   primary key (IDITEM)
);

/*==============================================================*/
/* Table: MULTIMEDIA                                            */
/*==============================================================*/
create table MULTIMEDIA
(
   IDMULTIMEDIA         int not null,
   IDITEM               int,
   PATHMULTIMEDIA       varchar(300),
   primary key (IDMULTIMEDIA)
);

/*==============================================================*/
/* Table: REVIEW                                                */
/*==============================================================*/
create table REVIEW
(
   TITULOREVIEW         varchar(100) not null,
   CALIFICACIONREVIEW   int not null,
   COMENTARIOREVIEW     varchar(500) not null,
   FECHAREVIEW          date,
   IDREVIEW             int not null,
   EMAILUSR             varchar(50),
   IDITEM               int,
   primary key (IDREVIEW)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   EMAILUSR             varchar(50) not null,
   PASSWORDUSR          varchar(20) not null,
   NOMBREUSR            varchar(20) not null,
   APELLIDOUSR          varchar(20),
   FECHANACIMIENTOUSR   date,
   PAISUSR              varchar(20),
   ADMINUSR             bool,
   primary key (EMAILUSR)
);

alter table ITEM add constraint FK_ITEMCATEGORIA foreign key (NOMBRECATEGORIA)
      references CATEGORIA (NOMBRECATEGORIA) on delete restrict on update restrict;

alter table ITEM add constraint FK_USUARIOITEM foreign key (EMAILUSR)
      references USUARIO (EMAILUSR) on delete restrict on update restrict;

alter table MULTIMEDIA add constraint FK_MULTIMEDIAITEM foreign key (IDITEM)
      references ITEM (IDITEM) on delete restrict on update restrict;

alter table REVIEW add constraint FK_REVIEWITEM foreign key (IDITEM)
      references ITEM (IDITEM) on delete restrict on update restrict;

alter table REVIEW add constraint FK_USUARIOREVIEW foreign key (EMAILUSR)
      references USUARIO (EMAILUSR) on delete restrict on update restrict;

