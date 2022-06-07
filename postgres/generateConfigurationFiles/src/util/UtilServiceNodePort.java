package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilServiceNodePort {

	public static boolean createServiceNodePortFile(String nom, int port, int nodePort) {
		
		String service="apiVersion: v1\n"
		+ "kind: Service\n"
		+ "metadata:\n"
		+ "  name: "+nom+"\n"
		+ "  labels: \n"
		+ "    app: "+nom+"\n"
		+ "spec:\n"
		+ "  type: NodePort\n"
		+ "  ports:\n"
		+ "   - port: "+port+"\n"
		+ "  selector:\n"
		+ "    app: "+nom+"\n";


		
		try {
			File fichierService=new File("serviceNodePort.yaml");
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
