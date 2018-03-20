# winecellar
application with a database to manage my cellar of wine

## Datatbase specifics
Database Name : elephantSql
Database is in PostgreSQL 10.0 on x86_64-pc-linux-gnu
ServerName : baasu.db.elephantsql.com:5432

Database contains 2 tables.

## Database Tables

### wine Table 
#### How to create this table : implements this code to SQL
-----------------------------
CREATE TABLE public.wine
(
    wine_id integer NOT NULL DEFAULT nextval('wine_wine_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    country character varying COLLATE pg_catalog."default" NOT NULL,
    county character varying COLLATE pg_catalog."default" NOT NULL,
    type character varying COLLATE pg_catalog."default" NOT NULL,
    color character varying COLLATE pg_catalog."default" NOT NULL,
    year smallint NOT NULL,
    price numeric(6,2) NOT NULL,
    CONSTRAINT wine_pkey PRIMARY KEY (wine_id)
)
                   
### cellar Table
#### How to create this table : implements this code to SQL
-----------------------------
CREATE TABLE public.cellar
(
    cellar_id integer NOT NULL DEFAULT nextval('cellar_cellar_id_seq'::regclass),
    wine_id integer,
    bottle_nb smallint,
    CONSTRAINT cellar_pkey PRIMARY KEY (cellar_id),
    CONSTRAINT cellar_wine_id_fkey FOREIGN KEY (wine_id)
        REFERENCES public.wine (wine_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT minimum CHECK (bottle_nb >= 0)
)
## Usage

In execution, the application purpose a menu to choose action, seeing below.
![MENU](winecellar/screens/menu.png)

First choice permitt to Insert a new wine Entry in table wine.
    The app ask operator all value to Insert into Columns and purpose to add entry in cellar Table
![CHOICE1](winecellar/screens/Choice1.png)

Second Choice allow user to modify bottle_nb Value decreasing Limit 0.
![CHOICE2](winecellar/screens/Choice2.png)

Choice 0 permitt to quit application.
![CHOICE0](https://github.com/StephRDSE/winecellar/blob/master/screens/Choice0.PNG)

<img align="left" src="./winecellar/screens/Choice0.png" alt="" title="Choice 0" hspace="20"/>

EnD
