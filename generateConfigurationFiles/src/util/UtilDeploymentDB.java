package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class UtilDeploymentDB {

	
	public static boolean createDeploymentDBFile(String nom,String image,String secretName,String mountPath, int port, String claimName) {
		
		String first="apiVersion: apps/v1\n"
				+ "kind: Deployment\n"
				+ "metadata:\n"
				+ "  name: "+nom+"\n"
				+ "spec:\n"
				+ "  selector:\n"
				+ "    matchLabels:\n"
				+ "      app: "+nom+"\n"
				+ "  strategy:\n"
				+ "    type: Recreate\n"
				+ "  template:\n"
				+ "    metadata:\n"
				+ "      labels:\n"
				+ "        app: "+nom+"\n";
		
	    String second="    spec:\n"
				+ "      containers:\n"
				+ "      - image: "+image+"\n"
				+ "        name: "+nom+"\n"
				+ "        env:\n"
				+ "        - name: MYSQL_ROOT_PASSWORD\n"
				+ "          valueFrom:\n"
				+ "            secretKeyRef:\n"
				+ "              name: "+secretName+"\n"
				+ "              key: password\n"
				+ "        ports:\n"
				+ "        - containerPort: "+port+"\n"
				+ "          name: "+nom+"\n"
				+ "        volumeMounts:\n"
				+ "        - name: mysql-persistent-storage\n"
				+ "          mountPath: "+mountPath+"\n"
				+ "      volumes:\n"
				+ "      - name: mysql-persistent-storage\n"
				+ "        persistentVolumeClaim:\n"
				+ "          claimName: "+claimName;
		
		try {
			File fichierDeploiement=new File("deploymentDB.yaml");
			FileWriter fw;
			fw = new FileWriter(fichierDeploiement);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(first+second);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
}
