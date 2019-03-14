-- add primary key(id) in absen_file_package_relation&absen_file_series
ALTER TABLE absen_file_package_relation ADD id int identity(1,1)  PRIMARY KEY;
ALTER TABLE absen_file_series ADD id int identity(1,1)  PRIMARY KEY;

-- add column in absen_offer_panels table
alter table absen_offer_panels add cust_horizontal decimal(18,6);
alter table absen_offer_panels add cust_longitudinal decimal(18,6);
alter table absen_offer_panels add split_screen_parent bigint;

-- drop table Sheet1&Spart_EN
drop table Sheet1;
drop table Spart_EN;

-- remove id column identity in absen_offer table
ALTER table absen_offer add myid bigint not null DEFAULT 1;
update absen_offer set myid = id;
DECLARE @constraint_name_1 VARCHAR(500)
select @constraint_name_1 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_offer'
if @constraint_name_1 is not null
exec('ALTER table absen_offer drop CONSTRAINT '+@constraint_name_1);
ALTER table absen_offer drop COLUMN id;
ALTER table absen_offer add CONSTRAINT PK_absen_offer PRIMARY KEY(myid);
exec sp_rename 'absen_offer.myid','id','column';

-- remove id column identity in absen_offer_panels table
ALTER table absen_offer_panels add myid bigint not null DEFAULT 1;
update absen_offer_panels set myid = id;
DECLARE @constraint_name_2 VARCHAR(500)
select @constraint_name_2 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_offer_panels'
if @constraint_name_2 is not null
exec('ALTER table absen_offer_panels drop CONSTRAINT '+@constraint_name_2);
ALTER table absen_offer_panels drop COLUMN id;
ALTER table absen_offer_panels add CONSTRAINT PK_absen_offer_panels PRIMARY KEY(myid);
exec sp_rename 'absen_offer_panels.myid','id','column';

-- modify column offer in absen_offer_panels
drop INDEX absen_offer_panels.offerPanels_offer;
ALTER table absen_offer_panels ALTER column offer bigint;
create index offerPanels_offer on absen_offer_panels(offer);

-- remove id column identity in absen_offer_preference table
ALTER table absen_offer_preference add myid bigint not null DEFAULT 1;
update absen_offer_preference set myid = id;
DECLARE @constraint_name_3 VARCHAR(500)
select @constraint_name_3 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_offer_preference'
if @constraint_name_3 is not null
exec('ALTER table absen_offer_preference drop CONSTRAINT '+@constraint_name_3);
ALTER table absen_offer_preference drop COLUMN id;
ALTER table absen_offer_preference add CONSTRAINT PK_absen_offer_preference PRIMARY KEY(myid);
exec sp_rename 'absen_offer_preference.myid','id','column';

-- modify column offer in absen_offer_preference
ALTER table absen_offer_preference ALTER column offer bigint;

-- remove id column identity in absen_offer_service table
ALTER table absen_offer_service add myid bigint not null DEFAULT 1;
update absen_offer_service set myid = id;
DECLARE @constraint_name_4 VARCHAR(500)
select @constraint_name_4 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_offer_service'
if @constraint_name_4 is not null
exec('ALTER table absen_offer_service drop CONSTRAINT '+@constraint_name_4);
ALTER table absen_offer_service drop COLUMN id;
ALTER table absen_offer_service add CONSTRAINT PK_absen_offer_service PRIMARY KEY(myid);
exec sp_rename 'absen_offer_service.myid','id','column';

-- modify column offer in absen_offer_service table
ALTER table absen_offer_service ALTER column offer bigint;

-- remove id column identity in absen_offer_spare_selfdefine table
ALTER table absen_offer_spare_selfdefine add myid bigint not null DEFAULT 1;
update absen_offer_spare_selfdefine set myid = id;
DECLARE @constraint_name_5 VARCHAR(500)
select @constraint_name_5 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_offer_spare_selfdefine'
if @constraint_name_5 is not null
exec('ALTER table absen_offer_spare_selfdefine drop CONSTRAINT '+@constraint_name_5);
ALTER table absen_offer_spare_selfdefine drop COLUMN id;
ALTER table absen_offer_spare_selfdefine add CONSTRAINT PK_absen_offer_spare_selfdefine PRIMARY KEY(myid);
exec sp_rename 'absen_offer_spare_selfdefine.myid','id','column';

-- modify column offers and panel in absen_offer_spare_selfdefine
ALTER table absen_offer_spare_selfdefine ALTER column offers bigint;
ALTER table absen_offer_spare_selfdefine ALTER column panel bigint;

-- remove id column identity in absen_offer_spares table
ALTER table absen_offer_spares add myid bigint not null DEFAULT 1;
update absen_offer_spares set myid = id;
DECLARE @constraint_name_6 VARCHAR(500)
select @constraint_name_6 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_offer_spares'
if @constraint_name_6 is not null
exec('ALTER table absen_offer_spares drop CONSTRAINT '+@constraint_name_6);
ALTER table absen_offer_spares drop COLUMN id;
ALTER table absen_offer_spares add CONSTRAINT PK_absen_offer_spares PRIMARY KEY(myid);
exec sp_rename 'absen_offer_spares.myid','id','column';

-- modify column offer and panel in absen_offer_spares table
ALTER table absen_offer_spares ALTER column offer bigint;
ALTER table absen_offer_spares ALTER column panel bigint;

-- remove id column identity in absen_offer_transfer table
ALTER table absen_offer_transfer add myid bigint not null DEFAULT 1;
update absen_offer_transfer set myid = id;
DECLARE @constraint_name_7 VARCHAR(500)
select @constraint_name_7 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_offer_transfer'
if @constraint_name_7 is not null
exec('ALTER table absen_offer_transfer drop CONSTRAINT '+@constraint_name_7);
ALTER table absen_offer_transfer drop COLUMN id;
ALTER table absen_offer_transfer add CONSTRAINT PK_absen_offer_transfer PRIMARY KEY(myid);
exec sp_rename 'absen_offer_transfer.myid','id','column';

-- modify column orders in absen_offer_transfer table
ALTER table absen_offer_transfer ALTER column orders bigint;

-- remove id column identity in absen_transport_package table
ALTER table absen_transport_package add myid bigint not null DEFAULT 1;
update absen_transport_package set myid = id;
DECLARE @constraint_name_8 VARCHAR(500)
select @constraint_name_8 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_transport_package'
if @constraint_name_8 is not null
exec('ALTER table absen_transport_package drop CONSTRAINT '+@constraint_name_8);
ALTER table absen_transport_package drop COLUMN id;
ALTER table absen_transport_package add CONSTRAINT PK_absen_transport_package PRIMARY KEY(myid);
exec sp_rename 'absen_transport_package.myid','id','column';

-- modify column transfer and offer in absen_transport_package table;
ALTER table absen_transport_package ALTER column transfer bigint;
ALTER table absen_transport_package ALTER column offer bigint;

-- remove id column identity in absen_approval_config table
ALTER table absen_approval_config add myid bigint not null DEFAULT 1;
update absen_approval_config set myid = id;
DECLARE @constraint_name_9 VARCHAR(500)
select @constraint_name_9 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_approval_config'
if @constraint_name_9 is not null
exec('ALTER table absen_approval_config drop CONSTRAINT '+@constraint_name_9);
ALTER table absen_approval_config drop COLUMN id;
ALTER table absen_approval_config add CONSTRAINT PK_absen_approval_config PRIMARY KEY(myid);
exec sp_rename 'absen_approval_config.myid','id','column';

-- remove id column identity in absen_approval_task table
ALTER table absen_approval_task add myid bigint not null DEFAULT 1;
update absen_approval_task set myid = id;
DECLARE @constraint_name_10 VARCHAR(500)
select @constraint_name_10 = CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE TABLE_NAME='absen_approval_task'
if @constraint_name_10 is not null
exec('ALTER table absen_approval_task drop CONSTRAINT '+@constraint_name_10);
ALTER table absen_approval_task drop COLUMN id;
ALTER table absen_approval_task add CONSTRAINT PK_absen_approval_task PRIMARY KEY(myid);
exec sp_rename 'absen_approval_task.myid','id','column';

-- modify column orders in absen_approval_task table
ALTER table absen_approval_task ALTER column orders bigint;

-- modify column(panel,wcount,lcount) is null in absen_offer_panels table
alter table absen_offer_panels alter column panel int null;
alter table absen_offer_panels alter column wcount int null;
alter table absen_offer_panels alter column lcount int null;