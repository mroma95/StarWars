CREATE SEQUENCE star_wars.people_id_sequence START WITH 1 INCREMENT BY 1;
GO


CREATE TABLE star_wars.peoples
(
    id                       bigint                      NOT NULL
        CONSTRAINT DEFAULT_PEOPLE_ID DEFAULT NEXT VALUE FOR star_wars.people_id_sequence,
    name                     VARCHAR(256)                NULL,
    height                   INT                         NULL,
    mass                     INT                         NULL,
    CONSTRAINT PK_PEOPLE PRIMARY KEY (id)
);
GO