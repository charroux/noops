package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilSecret {

	
	public static boolean createSecretFile(String nom, String password) {
		
		String secret="apiVersion: v1\n"
				    + "kind: Secret\n"
				    + "metadata:\n"
				    + "  name: "+nom+"\n"
				    + "type: kubernetes.io/basic-auth\n"
				    + "stringData:\n"
				    + "  password: "+password;
		
		try {
			File fichierSecret=new File("postgres-secret.yaml");
			FileWriter fw;
			fw = new FileWriter(fichierSecret);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(secret);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
}
