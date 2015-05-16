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
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('1','Vasos (Flores e Plantas)','Água, esponja e sabão. Depositar areia na  vasilha sob o vaso a cada limpeza.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('2','Ralos','Jogar água sanitária. Mantê-los vedados caso não o utilize.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('3','Recipientes para Armazenamento de Água','Água, esponja e sabão. (Jarras, garrafas, potes e baldes).');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('4','Caixa d’água','Água, sabão e água sanitária. Devem ser mantidas tampadas durante todo o tempo.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('5','Piscinas','Água, sabão e água sanitária. O cloro deve estar sempre no nível adequado, tratando a água. Manter coberta, caso não esteja usando.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('6','Bebedouros de Animais','Água, esponja e sabão. Mantê-los limpo, trocando a água diariamente.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('7','Calhas','Água, sabão e água sanitária. Remova tudo que possa impedir a passagem da água.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('8','Bromélias (Planta).','Diluir uma colher (sopa) de água sanitária em 1 litro de água limpa e regá-las. Eliminá-las poderia afetar o equilíbrio ecológico,  portanto o melhor é preservar de maneira correta.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('9','Pneus Velhos','4 colheres de sopa de borra de café, para cada 300 ml de água. Devem ser furados para eliminar a água que eventualmente se acumule ou guardados em lugar coberto e nunca joga-los em terrenos baldios.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('10','Recipientes Descartáveis.','Usar Água e sabão. Mantê-los secos e bem guardados. Separar para serem levados pela coleta de lixo, nunca joga-los em terrenos baldios. ');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('11','Garagens e Subsolos.','Diluir 4 colheres de sopa de borra de café, para cada 300 ml de água. (De acordo com o objeto em questão). Veja se a água da chuva que cai nas telhas circula e se não há nenhum objeto em que a água possa se acumular eventualmente.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('12','Depressões de Terrenos','Preencha-os com areia, pois podem acumular água da chuva. Repor a areia, caso a mesma tenha saído por causa da água.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('13','Sacos de Lixo','Devem ser bem fechados e DIARIAMENTE recolhidos pela coleta de lixo. Depositá-lo no exterior da moradia, no lugar correto para que a coleta o leve.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('14','Caixa de Ar Condicionado','Deve ser instalada de forma que esta não possa acumular água. Verificar o acúmulo de água não apenas depois de usá-lo, também após as chuvas.');
Insert into CENTRAL.FOCO (COD_FOCO,NOM_FOCO,COM_LIMPAR) values ('15','Geladeiras','4 colheres de sopa de borra de café, para cada 300 ml de água, depositar na gaveta que algumas geladeiras possuem, abaixo, no seu exterior. Verifique se esta não tem vazamento e consequentemente acúmulo de água.');
REM INSERTING into CENTRAL.PREVENCAO
SET DEFINE OFF;
Insert into CENTRAL.PREVENCAO (COD_CELULAR,COD_FOCO,DAT_CRIACAO,DAT_PRAZO,DAT_EFETUADA,END_BAIRRO,END_CIDADE,END_ESTADO) values ('12345','1',to_date('18/02/15','DD/MM/RR'),to_date('23/02/15','DD/MM/RR'),null,'Jardim Quietude','Praia Grande','SÃ£o Paulo');
Insert into CENTRAL.PREVENCAO (COD_CELULAR,COD_FOCO,DAT_CRIACAO,DAT_PRAZO,DAT_EFETUADA,END_BAIRRO,END_CIDADE,END_ESTADO) values ('123456','1',to_date('18/02/15','DD/MM/RR'),to_date('23/02/15','DD/MM/RR'),null,'Jardim Quietude','Praia Grande','SÃ£o Paulo');
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
