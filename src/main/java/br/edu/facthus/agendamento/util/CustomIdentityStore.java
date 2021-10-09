package br.edu.facthus.agendamento.util;

import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {
	
	public CredentialValidationResult validate(UsernamePasswordCredential userCredential) {
		if (userCredential.compareTo("admin", "FacthusUnibrasilia2021"))
            return new CredentialValidationResult("admin", new HashSet<>(Arrays.asList("ADMIN")));

        return CredentialValidationResult.INVALID_RESULT;
    }

}
