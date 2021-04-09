/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * Cette classe permet de faire une mise à jour de votre application
 * @author Wichtounet
 *
 */
public class funcMiseAJour {
	//Chemin vers le lanceur
	private String lanceurPath = "lanceur.jar";
	
	//Chemin vers le fichier xml 
	private String xmlPath = "https://hemge.eu/prog/PingOuin/maj.xml";
	
	//Version actuelle
	private String version = PackFunc.Var.version;
	//Document xml
	private Document xmlDocument = null;
	
	//Variable contenant le nom du répértoire courant
	private String currentFolder = PackFunc.Var.path;
	
	/**
	 * Cette méthode permet de mettre à jour votre programme, elle va chercher 
	 * sur internet la dernière version disponible et effectue la mise à jour 
	 * selon le consentement de l'utilisateur
	 *
	 */
	public void update(){
		ArrayList<String> versions = getVersions();
		
		//Si la version est nulle
		if(versions.size() == 0){
			JOptionPane.showMessageDialog(null,PackFunc.Var.bundle.getString("func.maj.conn"));
		}else{
			//Si la dernière version n'est pas la même que l'actuelle
			if(!versions.get(versions.size() - 1).equals(version)){

				String versionChoisie = (String)JOptionPane.showInputDialog(null,PackFunc.Var.bundle.getString("func.maj.version"),PackFunc.Var.bundle.getString("func.maj.version2"),JOptionPane.QUESTION_MESSAGE,
						null,versions.toArray(),versions.get(versions.size() - 1));
				
				//S'il veut la télécharger
				if(versionChoisie != ""){					
					Element racine = xmlDocument.getRootElement();
										
					//On liste toutes les versions
					List listVersions = racine.getChildren("version");
					Iterator iteratorVersions = listVersions.iterator();
					
					//On parcourt toutes les versions
					while(iteratorVersions.hasNext()){
						Element version = (Element)iteratorVersions.next();
						
						Element elementNom = version.getChild("nom");
						
						//Si c'est la bonne version, on télécharge tous ses fichiers
						if(elementNom.getText().equals((String)versionChoisie)){
							Element elementFiles = version.getChild("files");
							
							//On liste tous les fichiers d'une version
							List listFiles = elementFiles.getChildren("file");
							Iterator iteratorFiles = listFiles.iterator();
							
							//On parcours chaque fichier de la version
							while(iteratorFiles.hasNext()){
								Element file = (Element)iteratorFiles.next();
								
								//On télécharge le fichier
								downloadFile(file.getChildText("url"),currentFolder + 
										File.separator + file.getChildText("destination")+"maj");
							}
							
							break;
						}
					}
					
					JOptionPane.showMessageDialog(null,PackFunc.Var.bundle.getString("func.maj.ok"));
					
					File lanceur = new File(lanceurPath);
					
					try {
						//On lance le lanceur
						Desktop.getDesktop().open(lanceur);
						
						//On quitte le programme				
						System.exit(0);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,PackFunc.Var.bundle.getString("func.maj.error"));
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null,PackFunc.Var.bundle.getString("func.maj.no"));
			}
		}
	}

	/**
	 * Cette méthode va chercher sur internet les versions disponibles pour l'application
	 * @return les versions disponibles
	 */
	public ArrayList<String> getVersions(){
		ArrayList<String> versions = new ArrayList<String>();
		
		try {
			URL xmlUrl = new URL(xmlPath);
			
			//On ouvre une connections ur la page
			URLConnection urlConnection = xmlUrl.openConnection();
			urlConnection.setUseCaches(false);
			
			//On se connecte sur cette page
			urlConnection.connect();
			
			//On récupère le fichier XML sous forme de flux
			InputStream stream = urlConnection.getInputStream();
						
			SAXBuilder sxb = new SAXBuilder();
						
			//On crée le document xml avec son flux
			try {xmlDocument = sxb.build(stream);
			} catch (JDOMException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();}
			
			//On récupère la racine
			Element racine = xmlDocument.getRootElement();
			
			//On liste toutes les versions
			List listVersions = racine.getChildren("version");
			Iterator iteratorVersions = listVersions.iterator();
			
			//On parcourt toutes les versions
			while(iteratorVersions.hasNext()){
				Element version = (Element)iteratorVersions.next();
				
				Element elementNom = version.getChild("nom");
                                versions.add(elementNom.getText());
                                
				versions.add(elementNom.getText());
                                
			}
			
			//On trie la liste
			Collections.sort(versions);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return versions;
	}
	
	/**
	 * Cette méthode télécharge une fichier sur internet et le stocke en local
	 * @param filePath, chemin du fichier à télécharger
	 * @param destination, chemin du fichier en local
	 */
	private void downloadFile(String filePath, String destination) { 
		URLConnection connection = null;
		InputStream is = null;
		FileOutputStream destinationFile = null;
		
		try { 
			//On crée l'URL
	        URL url = new URL(filePath);

			//On crée une connection vers cet URL
			connection = url.openConnection( );
	        
			//On récupère la taille du fichier
			int length = connection.getContentLength();

			//Si le fichier est inexistant, on lance une exception
			if(length == -1){
				throw new IOException("Fichier vide");
	       	}

			//On récupère le stream du fichier
			is = new BufferedInputStream(connection.getInputStream());

			//On prépare le tableau de bits pour les données du fichier
			byte[] data = new byte[length];

			//On déclare les variables pour se retrouver dans la lecture du fichier
			int currentBit = 0;
			int deplacement = 0;
			
			//Tant que l'on n'est pas à la fin du fichier, on récupère des données
			while(deplacement < length){
				currentBit = is.read(data, deplacement, data.length-deplacement);	
				if(currentBit == -1)break;	
				deplacement += currentBit;
			}

			//Si on est pas arrivé à la fin du fichier, on lance une exception
			if(deplacement != length){
				throw new IOException("Le fichier n'a pas été lu en entier (seulement " 
					+ deplacement + " sur " + length + ")");
			}		
		
			//On crée un stream sortant vers la destination
			destinationFile = new FileOutputStream(destination); 

			//On écrit les données du fichier dans ce stream
			destinationFile.write(data);

			//On vide le tampon et on ferme le stream
			destinationFile.flush();

	      } catch (MalformedURLException e) { 
	    	  System.err.println("Problème avec l'URL : " + filePath); 
	      } catch (IOException e) { 
                  JOptionPane.showMessageDialog(null,e);
	        e.printStackTrace();
                System.out.println(e);
	      } finally{
	    	  try {
	    		  is.close();
				  destinationFile.close();
	    	  } catch (IOException e) {
	    		  e.printStackTrace();
	    	  }
	      }
	}
        public boolean majDispo(){
            boolean majOk = false;
            ArrayList<String> versions = getVersions();
            if(versions.size() == 0){

            }else{
                    //Si la dernière version n'est pas la même que l'actuelle
                    if(!versions.get(versions.size() - 1).equals(version)){
                        majOk = true;
                    }
            }
            return majOk;
        }
        
        private ArrayList<String> getChangelog(){
		ArrayList<String> versions = new ArrayList<String>();
		
		try {
			URL xmlUrl = new URL(xmlPath);
			
			//On ouvre une connections ur la page
			URLConnection urlConnection = xmlUrl.openConnection();
			urlConnection.setUseCaches(false);
			
			//On se connecte sur cette page
			urlConnection.connect();
			
			//On récupère le fichier XML sous forme de flux
			InputStream stream = urlConnection.getInputStream();
						
			SAXBuilder sxb = new SAXBuilder();
						
			//On crée le document xml avec son flux
			try {xmlDocument = sxb.build(stream);
			} catch (JDOMException e) {e.printStackTrace();
			} catch (IOException e) {e.printStackTrace();}
			
			//On récupère la racine
			Element racine = xmlDocument.getRootElement();
			
			//On liste toutes les versions
			List listVersions = racine.getChildren("version");
			Iterator iteratorVersions = listVersions.iterator();
			
			//On parcourt toutes les versions
			while(iteratorVersions.hasNext()){
				Element version = (Element)iteratorVersions.next();
				
				if(version.getChild("change") != null){
                                    Element elementChange = version.getChild("change");
                                    versions.add(elementChange.getText());
                                }
			}
			
			//On trie la liste
			Collections.sort(versions);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return versions;
	}
        
}