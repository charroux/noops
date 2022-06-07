package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilServiceClusterIp {

	public static boolean createServiceClusterIpFile(String nom, int port) {
		
		String service="apiVersion: v1\n"
				    + "kind: Service\n"
				    + "metadata:\n"
				    + "  name: "+nom+"\n"
				    + "spec:\n"
				    + "  ports:\n"
				    + "  - port: "+port+"\n"
				    + "  selector:\n"
				    + "    app: "+nom;
		
		try {
			File fichierService=new File("serviceClusterIp.yaml");
			FileWriter fw;
			fw = new FileWriter(fichierService);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(service);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
}
