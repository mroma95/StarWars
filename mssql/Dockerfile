# Default configuration parameters
ARG SA_PASSWORD_ARG=Password12

FROM mcr.microsoft.com/mssql/server:2017-CU20

ARG SA_PASSWORD_ARG
ARG DATABASE_TIME_ZONE_ARG
ARG MACHINE_TIME_ZONE_ARG

LABEL type=dctm-database

ENV ACCEPT_EULA="Y"
ENV SA_PASSWORD="$SA_PASSWORD_ARG"
ENV MSSQL_COLLATION="Polish_CS_AS"

EXPOSE 1433/tcp

WORKDIR /sql

COPY ./setup_database.sql /sql

RUN /opt/mssql/bin/sqlservr & \
    sleep 40 &&\
    /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "$SA_PASSWORD" -i setup_database.sql &&\
    pkill sqlservr

ENTRYPOINT [ "/opt/mssql/bin/sqlservr" ]
