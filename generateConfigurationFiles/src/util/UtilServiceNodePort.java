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
				    + "spec:\n"
				    + "  ports:\n"
				    + "    - nodePort: "+nodePort+"\n"
				    + "      port: "+port+"\n"
				    + "      protocol: TCP\n"
				    + "      targetPort: "+port+"\n"
				    + "  selector:\n"
				    + "    app: "+nom+"\n"
				    + "  type: NodePort";
		
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
