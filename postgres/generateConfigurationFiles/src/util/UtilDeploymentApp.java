package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilDeploymentApp {

	public static boolean createDeploymentAppFile(String nom, String image, int nbReplicas) {
		
				
		String deployment=

				"apiVersion: apps/v1\n"
				+ "kind: Deployment\n"
				+ "metadata:\n"
				+ "  name: "+nom+"\n"
				+ "spec:\n"
				+ "	 replicas: "+nbReplicas+"\n"
				+ "  selector:\n"
				+ "    matchLabels:\n"
				+ "      app: "+nom+"\n"
				+ "  template:\n"
				+ "    metadata:\n"
				+ "      labels:\n"
				+ "        app: "+nom+"\n"
				+ "    spec:\n"
				+ "      containers:\n"
				+ "        - name: "+nom+"\n"
				+ "          image: "+nom+":10.1\n"
				+ "          ports:\n"
				+ "           - containerPort: 5432\n"
				+ "          envFrom:\n"
				+ "           - configMapRef:\n"
				+ "              name: "+nom+"-config\n"
				+ "          volumeMounts:\n"
				+ "           - mountPath: /var/lib/"+nom+"ql/data\n"
				+ "             name: postgredb\n"
				+ "      volumes:\n"
				+ "      - name: postgredb\n"
				+ "        persistentVolumeClaim:\n"
				+ "          claimName: "+nom+"-pv-claim\n";

					/* 	"apiVersion: apps/v1\n"
				+ "kind: Deployment\n"
				+ "metadata:\n"
				+ "  name: "+nom+"\n"
				+ "spec:\n"
				+ "  replicas: "+nbReplicas+"\n"
				+ "  selector:\n"
				+ "    matchLabels:\n"
				+ "      app: "+nom+"\n"
				+ "  template:\n"
				+ "    metadata:\n"
				+ "      labels:\n"
				+ "        app: "+nom+"\n"
				+ "    spec:\n"
				+ "      containers:\n"
				+ "        - image: "+image+"\n"
				+ "          name: "+nom+"\n"
				+ "      restartPolicy: Always";
*/
		try {
			File fichierDeploiement=new File("postgres-deployment.yaml");
			FileWriter fw;
			fw = new FileWriter(fichierDeploiement);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(deployment);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}	
}
