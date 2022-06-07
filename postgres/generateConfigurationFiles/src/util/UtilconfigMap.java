package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class UtilconfigMap {
  
	public static boolean createConfigMaptFile(String nom, String password) {

		String secret="apiVersion: v1\n"
				    + "kind: ConfigMap\n"
				    + "metadata:\n"
				    + "  name: "+nom+"config\n"
				    + "  labels:\n"
				    + "    app: "+nom+"\n"
				    + "data: \n"
                    + "  POSTGRES_DB:postgres_DB\n"
                    + "  POSTGRES_USER: postgresadmin\n"
                    + "  POSTGRES_PASSWORD: "+password;
                    
		System.out.println("hello worlds 2");
		try {
			File fichierSecret=new File("configmap.yaml");
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