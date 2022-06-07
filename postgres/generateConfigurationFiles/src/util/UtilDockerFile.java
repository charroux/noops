package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UtilDockerFile {
	
	public static boolean createDockerFile(String nomJar, int port, int javaVersion) {
		
		String java="adoptopenjdk/openjdk11";
		
		if(javaVersion==8)
			java="openjdk:8-jdk-alpine";
			
		String dockerFile="FROM "+java+"\n"
				+ "VOLUME /tmp\n"
				+ "EXPOSE "+port+"\n"
				+ "ADD "+nomJar+" app.jar\n"
				+ "ENTRYPOINT [\"java\",\"-Djava.security.egd=file:/dev/./urandom\",\"-jar\",\"/app.jar\"]";
		
		try {
			File fichierDocker=new File("Dockerfile");
			FileWriter fw;
			fw = new FileWriter(fichierDocker);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(dockerFile);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}

}
