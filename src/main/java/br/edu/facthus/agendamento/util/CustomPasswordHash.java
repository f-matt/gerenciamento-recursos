package br.edu.facthus.agendamento.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.enterprise.identitystore.PasswordHash;

public class CustomPasswordHash implements PasswordHash {
	
	private final Logger logger = 
			Logger.getLogger(CustomPasswordHash.class.getName());

	@Override
	public String generate(char[] password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(new String(password).getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);

			return encoded;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erro codificando senha", e);
		}

		return null;
	}

	@Override
	public boolean verify(char[] password, String hashedPassword) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(new String(password).getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);

			return encoded.equals(hashedPassword);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Erro verificando senha", e);
		}

		return false;
	}

}
