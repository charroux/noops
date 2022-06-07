package model;
import util.UtilconfigMap;
public class ConfigMap {
    private String nomConfig;
	private String passwordConfig;
//	private String postgres_USER;
//	private String postgres_DB;
	
	public ConfigMap(String nomConfig, String passwordConfig) {
		this.nomConfig=nomConfig;
		this.passwordConfig=passwordConfig;
       // this.postgres_USER=postgres_USER;
        //this.postgres_DB=postgres_DB;
	}

	public void createConfigFile() {

		boolean isCreateFile=UtilconfigMap.createConfigMapFile(nomConfig, passwordConfig);

		if(isCreateFile)
			System.out.println("Fichier configmap.yaml créeer avec succès");
		
		else System.out.println("Erreur de cr�ation du Fichier configmap.yaml, vérifiez les droits d'�criture");

		
	}
	
	public String getnomConfig() {
		return nomConfig;
	}

	public void setnomConfig(String nomConfig) {
		this.nomConfig = nomConfig;
	}

	public String getpasswordConfig() {
		return passwordConfig;
	}

	public void setpasswordConfig(String passwordConfig) {
		this.passwordConfig = passwordConfig;
	}


	/* 
    public String getPostgres_User() {
		return postgres_USER;
	}

	public void setPostgres_User(String postgres_USER) {
		this.postgres_USER = postgres_USER;
	}
	public String getPostgres_DB() {
		return postgres_DB;
	}

	public void setPostgres_DB(String passwordConfig) {
		this.postgres_DB = postgres_DB;
	}
  */  
}