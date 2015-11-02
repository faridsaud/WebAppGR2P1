/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     11/2/2015 12:58:52 PM                        */
/*==============================================================*/


drop table if exists CATEGORIA;

drop table if exists ITEM;

drop table if exists ITEMTAG;

drop table if exists REVIEW;

drop table if exists TAG;

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
   NOMBREITEM           varchar(100) not null,
   DESCRIPCIONITEM      varchar(300) not null,
   CALIFICACIONITEM     decimal(3,2),
   PATHIMAGENITEM       varchar(100),
   FECHAIMAGEN          date,
   NUMVOTOSITEM         int,
   IDITEM               bigint not null,
   NOMBRECATEGORIA      varchar(20),
   EMAILUSR             varchar(50),
   IDREVIEW             bigint,
   primary key (IDITEM)
);

/*==============================================================*/
/* Table: ITEMTAG                                               */
/*==============================================================*/
create table ITEMTAG
(
   IDITEM               bigint not null,
   NOMBRETAG            varchar(20) not null,
   primary key (IDITEM, NOMBRETAG)
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
   IDREVIEW             bigint not null,
   EMAILUSR             varchar(50),
   IDITEM               bigint,
   primary key (IDREVIEW)
);

/*==============================================================*/
/* Table: TAG                                                   */
/*==============================================================*/
create table TAG
(
   NOMBRETAG            varchar(20) not null,
   primary key (NOMBRETAG)
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
   ESTADOUSR            bool,
   primary key (EMAILUSR)
);

alter table ITEM add constraint FK_ITEMCATEGORIA foreign key (NOMBRECATEGORIA)
      references CATEGORIA (NOMBRECATEGORIA) on delete restrict on update restrict;

alter table ITEM add constraint FK_REVIEWITEM2 foreign key (IDREVIEW)
      references REVIEW (IDREVIEW) on delete restrict on update restrict;

alter table ITEM add constraint FK_USUARIOITEM foreign key (EMAILUSR)
      references USUARIO (EMAILUSR) on delete restrict on update restrict;

alter table ITEMTAG add constraint FK_ITEMTAG foreign key (IDITEM)
      references ITEM (IDITEM) on delete restrict on update restrict;

alter table ITEMTAG add constraint FK_ITEMTAG2 foreign key (NOMBRETAG)
      references TAG (NOMBRETAG) on delete restrict on update restrict;

alter table REVIEW add constraint FK_REVIEWITEM foreign key (IDITEM)
      references ITEM (IDITEM) on delete restrict on update restrict;

alter table REVIEW add constraint FK_USUARIOREVIEW foreign key (EMAILUSR)
      references USUARIO (EMAILUSR) on delete restrict on update restrict;

