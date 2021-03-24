/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dynaglien
 */
public class DbConnect {
    ResultSet rs = null;
    PreparedStatement ps = null;
    Statement ps1 = null;
    funcDb fdb = new funcDb();
    funcMain fun = new funcMain();
    /**
     * Connection à la DB mysql
     */
    
    public static void DbConnectDist(){
        String addresse=null;
        String nom=null;
        String user=null;
        String pass=null;
        String port=null;
        funcDb fdb = new funcDb();
        
        addresse = fdb.paramLire("dbext_adress", "param");
        nom = fdb.paramLire("dbext_name", "param");
        user = fdb.paramLire("dbext_user", "param");
        pass = fdb.paramLire("dbext_pass", "param");
        port = fdb.paramLire("dbext_port", "param");
        
        try {
        Class.forName("com.mysql.jdbc.Driver");
          if(Var.conn == null){
                Var.conn = DriverManager.getConnection("jdbc:mysql://"+addresse+":"+port+"/"+nom+"?useUnicode=true&characterEncoding=utf8", user, pass);

          }else{
              Var.conn.close();
              Var.conn = DriverManager.getConnection("jdbc:mysql://"+addresse+":"+port+"/"+nom+"?useUnicode=true&characterEncoding=utf8", user, pass);
          }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void dbExtRecapCreate(){
System.out.println("01");
        try {
            String nomSite = fdb.paramLire("site", "param");
            ps1 = PackFunc.Var.conn.createStatement();
            String sq1 = "CREATE TABLE IF NOT EXISTS `"+nomSite+"` (`id` INTEGER NOT NULL AUTO_INCREMENT , `date` TEXT, `ip` TEXT, `nom` TEXT, `etat` TEXT, PRIMARY KEY(`id`));";
            ps1.execute(sq1);
            ps1.close();
        } catch (SQLException ex) {
            fun.ecritLogs(ex, " - creerTables dist recap - "+getClass().getName());
            System.out.println(ex);
        }
    }
    public void dbExtPerteCreate(){
        
        try {
            String nomSite = fdb.paramLire("site", "param");
            ps1 = PackFunc.Var.conn.createStatement();
            String sq1 = "CREATE TABLE IF NOT EXISTS `"+nomSite+"_perte` (`id` INTEGER NOT NULL AUTO_INCREMENT , `date` TEXT, `ip` TEXT, `nom` TEXT, `etat` TEXT, PRIMARY KEY(`id`));";
            ps1.execute(sq1);
            ps1.close();
        } catch (SQLException ex) {
            fun.ecritLogs(ex, " - creerTables dist perte - "+getClass().getName());
            System.out.println(ex);
        }
    }
    public void dbExtEcrire(String ip, String nom, String etat){
        String date = null;
        Date date1 = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd-HH:mm:ss");  
        date= dateFormat.format(date1);  
        
        try {
            String nomSite = fdb.paramLire("site", "param");
            ps1 = PackFunc.Var.conn.createStatement();
            String sq1 = "INSERT INTO `"+nomSite+"` (`date`, `ip`, `nom`, `etat`) VALUES ('"+date+"', '"+ip+"', '"+nom+"', '"+etat+"');";
            ps1.execute(sq1);
            ps1.close();
        } catch (SQLException ex) {
            fun.ecritLogs(ex, " - Ecrire dist recap - "+getClass().getName());
            System.out.println(ex);
        }
    }
    public void dbExtPerteEcrire(String ip, String nom, String etat){
        String date = null;
        Date date1 = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd-HH:mm:ss");  
        date= dateFormat.format(date1);  
        
        try {
            String nomSite = fdb.paramLire("site", "param");
            ps1 = PackFunc.Var.conn.createStatement();
            String sq1 = "INSERT INTO `"+nomSite+"_perte` (`date`, `ip`, `nom`, `etat`) VALUES ('"+date+"', '"+ip+"', '"+nom+"', '"+etat+"');";
            ps1.execute(sq1);
            ps1.close();
        } catch (SQLException ex) {
            fun.ecritLogs(ex, " - Ecrire dist Perte - "+getClass().getName());
            System.out.println(ex);
        }
    }
    public void dbExtPurge(){
        DbConnectDist();
        
        try {
            String nomSite = fdb.paramLire("site", "param");
            ps1 = PackFunc.Var.conn.createStatement();
            String sq1 = "TRUNCATE TABLE `"+nomSite+"` ";
            ps1.execute(sq1);
            ps1.close();
        } catch (SQLException ex) {
            fun.ecritLogs(ex, " - dbExt purge dist - "+getClass().getName());
            System.out.println(ex);
        }
    }
}
