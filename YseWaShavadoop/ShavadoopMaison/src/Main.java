import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class Main {
	
	public static HashMap<String, String> dicoUM;
	public static ArrayList<Thread> Threads;
	public static ArrayList<HashMap<String, Integer>> UM;
		
	public static void main(String[] args) throws InterruptedException, IOException{
		ArrayList<String> Sx= new ArrayList<String>();
		String fichier ="input.txt";
		
		//lecture du fichier input.txt	
		//and SPLITTING par lignes
		// On stocke dans une arrayList Sx
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//splitter les lignes
				//System.out.println(ligne);
				Sx.add(ligne);
			       }
			br.close(); 
			System.out.println(Sx);
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		
		//Faire le split mapping et faire un thread
		Threads=new ArrayList<Thread>();
		int indice=0;
		for (String Sxligne: Sx){
			SplitMapping  splitmap= new SplitMapping(Sxligne, indice);
			Thread thda= new Thread(splitmap);
			//thda.start();
			//thda.join();
			Threads.add(thda);
			indice+=1;
		}
		for (Thread thd : Threads){
			thd.start();
			//thd.join();
		}
		for (Thread thd : Threads){
			//thd.start();
			thd.join();
		}
		
		//Lire les dicoUMs et les mettre dans une hashmap dicoUM
		dicoUM= new HashMap<String, String>();
			int indice1=0;
			File f = new File("DICOUM"+indice1+".txt");
			while(f.exists()){
				try{
					InputStream ips=new FileInputStream(f); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						String[] line=ligne.split("=");
						if (dicoUM.containsKey(line[0])){
							dicoUM.put(line[0], dicoUM.get(line[0])+" "+line[1]);
						}else{
							dicoUM.put(line[0],line[1]);
						}
					}
					br.close();
					}catch (Exception e){
						System.out.println(e.toString());
					}
				indice1+=1;
				f =new File("DICOUM"+indice1+".txt");
			}
			System.out.println("dicoUM" +dicoUM);
			
		// Thread Shuffling
		Threads=new ArrayList<Thread>();
		int i =0;
		for (Entry<String, String> entry : dicoUM.entrySet()){
		Shuffling  shuffl= new Shuffling(entry, i);
		Thread thda= new Thread(shuffl);
		//thda.start();
		//thda.join();
		Threads.add(thda);
		i+=1;	
		}
		for (Thread thd : Threads){
			thd.start();
			//thd.join();
		}
		for (Thread thd : Threads){
			//thd.start();
			thd.join();
		}
		//Faire le Reduce
		Threads=new ArrayList<Thread>();
		int j=0;
		File f1 = new File("SM"+j+".txt");
		while(f1.exists()){
			System.out.println("File existed"+"SM"+j+".txt");
			//ouvrir et lire le fichier, puis le splitter 
			//et enregister les resultats de la hashmap reduce
			Reducing  shuffl= new Reducing(f1, j);
			Thread thda= new Thread(shuffl);
			//thda.start();
			//thda.join();
			Threads.add(thda);
			j+=1;
			f1 = new File("SM"+j+".txt");
		}
		for (Thread thd : Threads){
			thd.start();
			//thd.join();
		}
		for (Thread thd : Threads){
			//thd.start();
			thd.join();
		}
		//imprimer dans un fichier output le résultat des fichiers RMx
		BufferedWriter outputfile = new BufferedWriter(new FileWriter(new File("output.txt")));
		int k=0;
		File f2= new File("RM"+k+".txt");
		while (f2.exists()){
			System.out.println("File existed"+"RM"+k+".txt");
			//ouvrir et lire RMx
			InputStream ips=new FileInputStream(f2); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				outputfile.write(ligne);
				outputfile.write("\n");
			}
		br.close();
		k+=1;
		f2= new File("RM"+k+".txt");
		}
		outputfile.close();
	}
}

