import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map.Entry;


public class Shuffling extends Thread{
	Entry<String,String> entry;
	int i;
	
	public Shuffling (Entry<String,String> entry,int i){
		this.entry=entry;
		this.i=i;
	}
	
	//Chaque Thread lit une clé du Dico, ouvre les fichiers correspondants
	// et crée un fichier SM
	public void run(){
	String key=entry.getKey();
	System.out.println(key);
	BufferedWriter newfile;
	try {
		newfile = new BufferedWriter(new FileWriter(new File("SM"+ i +".txt")));
	//on splite les valeurs de la clé
	// on obtient la liste de tous les fichiers UMX à ouvrir
	String [] listeUMX=entry.getValue().split(" ");
	//System.out.println(listeUMX);
	for (String file : listeUMX){
		System.out.println(file);
		// ouvrir et lire le fichier
		try{
			InputStream ips=new FileInputStream(file); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//splitter les lignes
				//System.out.println(ligne);
				String[] line=ligne.split("=");
				if (line[0].equals(key)){
					//System.out.println("blaaa" +line[0]);
					newfile.write(ligne);
					newfile.write("\n");
				}
			       }
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	newfile.close();	
	} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
	}
}
}
