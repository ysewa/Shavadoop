import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Reducing extends Thread{
	File f1;
	BufferedWriter file;
	int j;
	public Reducing(File f1, int j){
		this.f1=f1;
		this.j=j;
	}
	
	public void run(){
		//Pour chaque Thread lire le fichier SM et écrire le fichier RMx
		//Si possible poursuivre en construisant un dico UMx-Machines
		try{
			InputStream ips=new FileInputStream(f1); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			int valeur=0;
			String cle= new String();
			while ((ligne=br.readLine())!=null){
				String[] line=ligne.split("=");
				cle=line[0];
				valeur=valeur+Integer.parseInt(line[1]);
			       }
			br.close(); 
			file = new BufferedWriter(new FileWriter(new File("RM"+ j +".txt")));
			file.write(cle+"="+valeur);
			file.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

}
