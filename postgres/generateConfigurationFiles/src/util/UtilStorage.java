package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilStorage {

	public static boolean createStorageFile(String persistentVolumeName,String persistentVolumeClaimName,String capacite,String hostPath) {
		
		String persistentVolume="apiVersion: v1\n"
				+ "kind: PersistentVolume\n"
				+ "metadata:\n"
				+ "  name: "+persistentVolumeName+"\n"
				+ "  labels: \n"
				+ "    type: local\n"
				+ "    app: postgres\n"
				+ "spec:\n"
				+ "  storageClassName: manual\n"
				+ "  capacity:\n"
				+ "    storage: "+capacite+"\n"
				+ "  accessModes:\n"
				+ "    - ReadWriteMany\n"
				+ "  hostPath:\n"
				+ "    path: "+hostPath+"\n"
				+ "---\n";
		
		String persistentVolumeClaim="apiVersion: v1\n"
				+ "kind: PersistentVolumeClaim\n"
				+ "metadata:\n"
				+ "  name: "+persistentVolumeClaimName+"\n"
				+ "spec:\n"
				+ "  storageClassName: manual\n"
				+ "  accessModes:\n"
				+ "    - ReadWriteMany\n"
				+ "  resources:\n"
				+ "    requests:\n"
				+ "      storage: "+capacite;
		
		try {
			File fichierStorage=new File("postgres-storage.yaml");
			FileWriter fw;
			fw = new FileWriter(fichierStorage);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(persistentVolume+persistentVolumeClaim);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}
}
