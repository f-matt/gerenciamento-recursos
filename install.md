-- Driver
module add --name=org.postgresql --resources=/home/user/postgresql-42.2.24.jar --dependencies=javax.api,javax.transaction.api

/subsystem=datasources/jdbc-driver=postgresql:add(driver-name="postgresql", driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)

-- Data source (postgresql)
data-source add --jndi-name=java:/agendamentoDS --name=agendamentoDS --connection-url=jdbc:postgresql://localhost:5432/agendamentoDB --driver-name=postgresql --user-name=<user> --password=<password>

--teste do data source
/subsystem=datasources/data-source=agendamentoDS:test-connection-in-pool() 

-- Autenticação / autorização

/subsystem=elytron/jdbc-realm=agendamentoRealm:add(principal-query=[{sql="SELECT password FROM usuarios WHERE login = ?",data-source=agendamentoDS,simple-digest-mapper={algorithm=simple-digest-sha-256,hash-encoding=base64,password-index=1}}, {sql="SELECT p.nome FROM perfis p JOIN usuarios u ON u.perfil_id = p.id WHERE u.login = ?",data-source=agendamentoDS, attribute-mapping=[{index=1,to=groups}]}])

/subsystem=elytron/security-domain=agendamentoDomain:add(realms=[{realm=agendamentoRealm,role-decoder=groups-to-roles}],default-realm=agendamentoRealm,permission-mapper=default-permission-mapper)

/subsystem=elytron/http-authentication-factory=agendamentoFactory:add(http-server-mechanism-factory=global,security-domain=agendamentoDomain,mechanism-configurations=[{mechanism-name=FORM,mechanism-realm-configurations=[{realm-name=agendamentoDomain}]}])

/subsystem=undertow/application-security-domain=agendamentoApplicationDomain:add(http-authentication-factory=agendamentoFactory)

/subsystem=ejb3/application-security-domain=agendamentoApplicationDomain:add(security-domain=agendamentoDomain)










/subsystem=elytron/jdbc-realm=agendamentoRealm:add(principal-query=[{sql="SELECT password FROM usuarios WHERE login = ?",data-source=agendamentoDS,simple-digest-mapper={algorithm=simple-digest-sha-256,hash-encoding=base64,password-index=1}}, {sql="SELECT p.nome FROM perfis p JOIN usuarios u ON u.perfil_id = p.id WHERE u.login = ?",data-source=agendamentoDS, attribute-mapping=[{index=1,to=groups}]}])

/subsystem=elytron/security-domain=agendamentoDomain:add(realms=[{realm=agendamentoRealm,role-decoder=groups-to-roles}],default-realm=agendamentoRealm,permission-mapper=default-permission-mapper)

/subsystem=elytron/http-authentication-factory=agendamentoFactory:add(http-server-mechanism-factory=global,security-domain=agendamentoDomain,mechanism-configurations=[{mechanism-name=FORM,mechanism-realm-configurations=[{realm-name=agendamentoDomain}]}])

/subsystem=undertow/application-security-domain=agendamentoApplicationDomain:add(http-authentication-factory=agendamentoFactory)

/subsystem=ejb3/application-security-domain=agendamentoApplicationDomain:add(security-domain=agendamentoDomain)