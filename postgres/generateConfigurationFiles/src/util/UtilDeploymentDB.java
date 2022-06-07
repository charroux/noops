package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class UtilDeploymentDB {

	
	public static boolean createDeploymentDBFile(String nom,String image,String secretName,String mountPath, int port, String claimName) {
		
		String first=	"apiVersion: apps/v1 \n"
		+ "	kind: Deployment\n"
		+ "	metadata:\n"
		+ "	name: "+nom+"\n"
		+ "	spec:\n"
		+ "	replicas: 1\n"
		+ "	selector:\n"
		+ "		matchLabels:\n"
		+ "		app: "+nom+"\n"
		+ "	template:\n"
		+ "		metadata:\n"
		+ "		labels:\n"
		+ "			app: "+nom+"\n"
		+ "		spec:\n"
		+ "		containers:\n"
		+ "			- name: "+nom+"\n"
		+ "			image: "+nom+":10.1\n"
		+ "			ports:\n"
		+ "				- containerPort: 5432\n"
		+ "			envFrom:\n"
		+ "				- configMapRef:\n"
		+ "					name: "+nom+"-config\n"
		+ "			volumeMounts:\n"
		+ "				- mountPath: /var/lib/"+nom+"ql/data\n"
		+ "				name: postgredb\n"
		+ "		volumes:\n"
		+ "			- name: postgredb\n"
		+ "			persistentVolumeClaim:\n"
		+ "				claimName: "+nom+"-pv-claim\n";
		
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
