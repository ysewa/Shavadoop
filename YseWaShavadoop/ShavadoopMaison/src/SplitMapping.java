import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;




public class SplitMapping extends Thread{
	String Sxligne;
	//HashMap<String, Integer>  map = null;
	HashMap<String, Integer>  map =	new HashMap<String, Integer> ();
	HashMap<String, String> dico = null;
	private int indice;
	
	public SplitMapping (String Sxligne, int indice){
		this.Sxligne= Sxligne;
		this.indice=indice;
		//map = new HashMap();
		dico = new HashMap<String, String>();
	}
	
	public void run(){
		
		//Faire le split mapping et faire un thread
		// on stocke les resultats du mapping dans une hashmap map
				try{						
						
						String[] mots=this.Sxligne.split(" ");
						for (String mot: mots){
							if (!map.containsKey(mot)){
								map.put(mot, 1);
							}else{
								map.put(mot, map.get(mot)+1);
							}
						}

						//System.out.println(map);

				    
				}
				catch (Exception e){
					System.out.println(e.toString());
				}
					// enregistrer les resultats du splitmapping
					//dans un fichier texte 'UM'+i+'.txt'
					// créer un fichier dictionnaire avec clé et nom de fichier UMx
					BufferedWriter file;
					try {
						file = new BufferedWriter(new FileWriter(new File("UM"+ indice +".txt")));
						BufferedWriter file2 = new BufferedWriter(new FileWriter(new File("DICOUM"+ indice +".txt")));
						for (Entry<String, Integer> entry : map.entrySet()){
						file.write( entry.getKey() +"="+ entry.getValue());
						file.write("\n");
						file2.write(entry.getKey()+"=UM"+ indice +".txt");
						file2.write("\n");
						}
						file.close();
						file2.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
	
	
	//public HashMap<String, String> getDico(){
	//	return this.dico;
	//}
   
}

