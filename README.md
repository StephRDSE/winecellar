# winecellar
application with a database to manage my cellar of wine

## Datatbase specifics
Database Name : elephantSql
Database is in PostgreSQL 10.0 on x86_64-pc-linux-gnu
ServerName : baasu.db.elephantsql.com:5432

Database contains 2 tables.

## Database Tables

### wine Table 
How to create this table : implements this code to SQL
CREATE TABLE wine (wine_id SERIAL PRIMARY KEY NOT NULL,
                   name VARCHAR NOT NULL,
                   country VARCHAR NOT NULL,
                   county VARCHAR NOT NULL,
                   type VARCHAR NOT NULL,
                   color VARCHAR NOT NULL,
                   year SMALLINT NOT NULL,
                   price NUMERIC (6,2)
                   );
                   
### cellar Table
How to create this table : implements this code to SQL
CREATE TABLE cellar (cellar_id SERIAL PRIMARY KEY NOT NULL,
                     wine_id Long REFERENCES wine(wine_id),
                     bottle_nb SMALLINT CONSTRAINT minimum CHECK bottle_nb >= 0
                     );
