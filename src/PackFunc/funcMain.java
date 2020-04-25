/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import PackMain.FenMain;
import PackThread.threadPop;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Dynaglien
 */
public class funcMain {
    private static String OS = System.getProperty("os.name").toLowerCase();
    
    
    /***************************************************************************
    *****   Connection a la BDD paramètres                                 *****
     * @throws java.lang.ClassNotFoundException
    ***************************************************************************/
    public void connectParam() throws ClassNotFoundException{
        try {
            Class.forName("org.sqlite.JDBC"); //NOI18N
            if(PackFunc.Var.dbConParam == null){
                PackFunc.Var.dbConParam = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/pingouin.pigo"); //NOI18N
            }else{
                PackFunc.Var.dbConParam.close();
                PackFunc.Var.dbConParam = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/pingouin.pigo"); //NOI18N
            }
            
            Class.forName("org.sqlite.JDBC"); //NOI18N
            if(PackFunc.Var.dbConSite == null){
                PackFunc.Var.dbConSite = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/db/"+PackFunc.Var.dbSite); //NOI18N
            }else{
                PackFunc.Var.dbConSite.close();
                PackFunc.Var.dbConSite = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/db/"+PackFunc.Var.dbSite); //NOI18N
            }
            
        } catch (SQLException e) {
            ecritLogs(e, " - 001 - "+getClass().getName()); //NOI18N
        }
    }
    /**
     * Connection à la table Historique
     */
    public void connectHist(){
        try {
            Class.forName("org.sqlite.JDBC"); //NOI18N
            if(PackFunc.Var.dbConHist == null){
                PackFunc.Var.dbConHist = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/db/hist.pigo"); //NOI18N
            }else{
                PackFunc.Var.dbConHist.close();
                PackFunc.Var.dbConHist = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/db/hist.pigo"); //NOI18N
            }
            
        } catch (SQLException e) {
            ecritLogs(e, " - 001 - "+getClass().getName()); //NOI18N
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(funcMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection connectSite(){
        Connection conn1=null;
        try {
            Class.forName("org.sqlite.JDBC"); //NOI18N
            if(PackFunc.Var.dbConSite == null){
                PackFunc.Var.dbConSite = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/db/"+PackFunc.Var.dbSite); //NOI18N
            }else{
                PackFunc.Var.dbConSite.close();
                PackFunc.Var.dbConSite = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/db/"+PackFunc.Var.dbSite); //NOI18N
            }
            
        } catch (SQLException e) {
            ecritLogs(e, " - 002 - "+getClass().getName()); //NOI18N
            ecritLogs(e, " - 002 - "+getClass().getName());
        } catch (ClassNotFoundException ex) {
            ecritLogs(ex, " - 002 - "+getClass().getName());
            Logger.getLogger(funcMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn1;
    }
    
    /***************************************************************************
    *****   Ecriture des logs                                              *****
     * @param log1
     * @param page
    ***************************************************************************/
    public void ecritLogs(Exception log1, String page)
    {
        Exception log = log1;
        Date auj =  new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //NOI18N

        try { 
            PrintWriter fich = new PrintWriter(new BufferedWriter(new FileWriter(PackFunc.Var.path+"/logs.txt", true))); //NOI18N
            fich.println(dateFormat.format(auj)+" - "+page+" / "+log); //NOI18N
            fich.close();
        } catch (IOException e) {
            ecritLogs(e, " - "+getClass().getName()); //NOI18N
        }
        JOptionPane.showMessageDialog(null,"Une erreur est survenue, vérifiez les logs");
        
    }
    
/***************************************************************************
*****   Gérer les sites                                                *****
***************************************************************************/
    
// Lister les sites
   public void listeSites(){
       FenMain.listeSite.removeAllItems();
       String[] listeSites = null;
       File repertoire = new File(PackFunc.Var.path+"/db");
        String liste[] = repertoire.list();
        if (liste != null) {
           for (String liste1 : liste) {
               String listeParse = listeSiteParse(liste1);
               if(listeParse.equals("hist"))
               {
                   
               }else{
                    FenMain.listeSite.addItem(listeParse);
               }
           }
           FenMain.listeSite.setSelectedItem(PackFunc.Var.path+"/ip.pigo");
        }
   }
   
   public String listeSiteParse(String site){
        String siteParse = null;
        String[] ipParse = site.split("\\.");
        siteParse = ipParse[0];
       return siteParse;
   }
// Supprimer un site
   public void siteSupp(String db){
       File fichier = new File(PackFunc.Var.path+"/db/"+db);
       fichier.delete();
       listeSites();
       threadPop t2 = new threadPop("Les données ont été supprimés.");
       t2.start();
   }
   
/***************************************************************************
*****   Fonctions générales                                            *****
***************************************************************************/
   
// quitter le logiciel
   public void quit(){
       funcDb fdb = new funcDb();
        try {
            fdb.dbIpErase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(funcMain.class.getName()).log(Level.SEVERE, null, ex);
            ecritLogs(ex, " - "+getClass().getName());
        }
       System.exit(1);
   }
   

// Créer le dossier Db
   public void dossCree(){
       try{
       File file = new File(PackFunc.Var.path+"/db");
       if (file.exists()) {
        } else {
            if (file.mkdir()) {
            } else {
            }
       }
       }catch(Exception e){
           ecritLogs(e, " - "+getClass().getName());
       }
   }
   /**
    * Test de l'OS
    */
   public void testOs(){
       
       if (isWindows()) {
           PackFunc.Var.os = "windows";
        } else if (isUnix()) {
            PackFunc.Var.os = "linux";
        } else {
            threadPop tpop = new threadPop("Votres systeme n'est pas supporté !");
            tpop.start();
        }
       getPath();
       
   }
   public void getPath(){
       if(PackFunc.Var.os.equals("linux")){
            String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
            PackFunc.Var.path = absolutePath;
        }else{
            String path = System.getProperty("user.dir" );
            PackFunc.Var.path = path;
        }
   }
   public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
		
	}

	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}
}
