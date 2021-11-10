package br.edu.facthus.agendamento.config;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

import br.edu.facthus.agendamento.util.CustomPasswordHash;

@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(
				loginPage = "/login.jsf",
				errorPage = "/login.jsf?error=true",
				useForwardToLogin = false)
)
@DatabaseIdentityStoreDefinition(
		dataSourceLookup = "java:/agendamentoDS",
		callerQuery = "SELECT password "
				+ "FROM usuarios "
				+ "WHERE login = ?",
		groupsQuery = "SELECT p.nome "
				+ "FROM perfis p "
				+ "JOIN usuarios u ON u.perfil_id = p.id "
				+ "WHERE u.login = ?",
		hashAlgorithm = CustomPasswordHash.class)
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {

}
