--------------------------------------------------------
--  Arquivo criado - Quarta-feira-Fevereiro-18-2015   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table FOCO
--------------------------------------------------------

  CREATE TABLE "CENTRAL"."FOCO" 
   (	"COD_FOCO" NUMBER, 
	"NOM_FOCO" VARCHAR2(60 BYTE), 
	"COM_LIMPAR" VARCHAR2(300 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table PREVENCAO
--------------------------------------------------------

  CREATE TABLE "CENTRAL"."PREVENCAO" 
   (	"COD_CELULAR" NUMBER, 
	"COD_FOCO" NUMBER, 
	"DAT_CRIACAO" DATE, 
	"DAT_PRAZO" DATE, 
	"DAT_EFETUADA" DATE, 
	"END_BAIRRO" VARCHAR2(60 BYTE), 
	"END_CIDADE" VARCHAR2(60 BYTE), 
	"END_ESTADO" VARCHAR2(60 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into CENTRAL.FOCO
SET DEFINE OFF;
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('1','Vasos (Flores e Plantas)','�gua, esponja e sab�o. Depositar areia na  vasilha sob o vaso a cada limpeza.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('2','Ralos','Jogar �gua sanit�ria. Mant�-los vedados caso n�o o utilize.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('3','Recipientes para Armazenamento de �gua','�gua, esponja e sab�o. (Jarras, garrafas, potes e baldes).');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('4','Caixa d��gua','�gua, sab�o e �gua sanit�ria. Devem ser mantidas tampadas durante todo o tempo.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('5','Piscinas','�gua, sab�o e �gua sanit�ria. O cloro deve estar sempre no n�vel adequado, tratando a �gua. Manter coberta, caso n�o esteja usando.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('6','Bebedouros de Animais','�gua, esponja e sab�o. Mant�-los limpo, trocando a �gua diariamente.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('7','Calhas','�gua, sab�o e �gua sanit�ria. Remova tudo que possa impedir a passagem da �gua.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('8','Brom�lias (Planta).','Diluir uma colher (sopa) de �gua sanit�ria em 1 litro de �gua limpa e reg�-las. Elimin�-las poderia afetar o equil�brio ecol�gico,  portanto o melhor � preservar de maneira correta.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('9','Pneus Velhos','4 colheres de sopa de borra de caf�, para cada 300 ml de �gua. Devem ser furados para eliminar a �gua que eventualmente se acumule ou guardados em lugar coberto e nunca joga-los em terrenos baldios.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('10','Recipientes Descart�veis.','Usar �gua e sab�o. Mant�-los secos e bem guardados. Separar para serem levados pela coleta de lixo, nunca joga-los em terrenos baldios. ');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('11','Garagens e Subsolos.','Diluir 4 colheres de sopa de borra de caf�, para cada 300 ml de �gua. (De acordo com o objeto em quest�o). Veja se a �gua da chuva que cai nas telhas circula e se n�o h� nenhum objeto em que a �gua possa se acumular eventualmente.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('12','Depress�es de Terrenos','Preencha-os com areia, pois podem acumular �gua da chuva. Repor a areia, caso a mesma tenha sa�do por causa da �gua.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('13','Sacos de Lixo','Devem ser bem fechados e DIARIAMENTE recolhidos pela coleta de lixo. Deposit�-lo no exterior da moradia, no lugar correto para que a coleta o leve.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('14','Caixa de Ar Condicionado','Deve ser instalada de forma que esta n�o possa acumular �gua. Verificar o ac�mulo de �gua n�o apenas depois de us�-lo, tamb�m ap�s as chuvas.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('15','Geladeiras','4 colheres de sopa de borra de caf�, para cada 300 ml de �gua, depositar na gaveta que algumas geladeiras possuem, abaixo, no seu exterior. Verifique se esta n�o tem vazamento e consequentemente ac�mulo de �gua.');
REM INSERTING into CENTRAL.PREVENCAO
SET DEFINE OFF;
Insert into CENTRAL.PREVENCAO (COD_CELULAR,COD_FOCO,DAT_CRIACAO,DAT_PRAZO,DAT_EFETUADA,END_BAIRRO,END_CIDADE,END_ESTADO) values ('12345','1',to_date('18/02/15','DD/MM/RR'),to_date('23/02/15','DD/MM/RR'),null,'Jardim Quietude','Praia Grande','São Paulo');
Insert into CENTRAL.PREVENCAO (COD_CELULAR,COD_FOCO,DAT_CRIACAO,DAT_PRAZO,DAT_EFETUADA,END_BAIRRO,END_CIDADE,END_ESTADO) values ('123456','1',to_date('18/02/15','DD/MM/RR'),to_date('23/02/15','DD/MM/RR'),null,'Jardim Quietude','Praia Grande','São Paulo');
--------------------------------------------------------
--  DDL for Index PK_PREVENCAO
--------------------------------------------------------

  CREATE UNIQUE INDEX "CENTRAL"."PK_PREVENCAO" ON "CENTRAL"."PREVENCAO" ("COD_CELULAR", "COD_FOCO", "DAT_CRIACAO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table FOCO
--------------------------------------------------------

  ALTER TABLE "CENTRAL"."FOCO" ADD PRIMARY KEY ("COD_FOCO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PREVENCAO
--------------------------------------------------------

  ALTER TABLE "CENTRAL"."PREVENCAO" ADD CONSTRAINT "PK_PREVENCAO" PRIMARY KEY ("COD_CELULAR", "COD_FOCO", "DAT_CRIACAO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PREVENCAO
--------------------------------------------------------

  ALTER TABLE "CENTRAL"."PREVENCAO" ADD CONSTRAINT "FK_PREVENCAO" FOREIGN KEY ("COD_FOCO")
	  REFERENCES "CENTRAL"."FOCO" ("COD_FOCO") ENABLE;
