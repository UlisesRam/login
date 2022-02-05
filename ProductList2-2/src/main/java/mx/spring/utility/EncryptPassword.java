package mx.spring.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPassword {
	
	public static void main(String[] args) {
		
		String password = "1234";
		
		System.out.println("PASSWORD: " + password);
		System.out.println("Encrypt Password: " + encryptor(password));
	}

	private static String encryptor(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
