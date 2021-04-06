/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;


import PackMain.FenMain;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dynaglien
 */
public class funcDb {
    funcMain fun = new funcMain();
    ResultSet rs = null;
    Statement ps = null;
    PreparedStatement ps1 = null;
    
    public boolean testTable(String table){
        boolean tableExist = true;
        try {
            boolean exists = PackFunc.Var.dbConParam.getMetaData().getTables(null, null, table, null).next();
            if(!exists){
                tableExist = false;
                System.out.println("Created table " + table);
            }
        } catch (SQLException ex) {
            Logger.getLogger(funcDbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableExist;
    }
    
    public void creerTables()
    {

//Table paramètres
        try{
            ps = PackFunc.Var.dbConParam.createStatement();
            String listColParam = listeCol("param");
System.out.println(listColParam);
            String sq1 = "CREATE TABLE IF NOT EXISTS param ("+listColParam+")";
            ps.execute(sq1);
System.out.println("2");
            ps.close();
            System.out.println("123");
// Table option
            String listColOptions = listeCol("options");
System.out.println(listColOptions);
            ps = PackFunc.Var.dbConParam.createStatement();
            String sq2 = "CREATE TABLE IF NOT EXISTS options ("+listColOptions+")"; //NOI18N
            ps.execute(sq2);
            ps.close();
        }catch (SQLException e) {
System.out.println(e);
            fun.ecritLogs(e, " - creerTables - "+getClass().getName());
        }
    }
    /**
     * Création des tables ip dans les historiques
     * @param id 
     */
    public void creerTableHist(String id)
    {
//Table paramètres
        try{
            ps = PackFunc.Var.dbConHist.createStatement();
            String sq1 = "CREATE TABLE IF NOT EXISTS '"+id+"' (id INTEGER PRIMARY KEY AUTOINCREMENT, date ,etat, lat)";
            ps.execute(sq1);
            ps.close();
        }catch (SQLException e) {
            fun.ecritLogs(e, " - creerTableHist - "+getClass().getName());
        }
    }
    
    
    public void tablesRemplir(){
// Table Paramètre
        try{
            String sq1 = "SELECT * FROM param WHERE id='1'"; //NOI18N
            ps = PackFunc.Var.dbConParam.createStatement();
            rs = ps.executeQuery(sq1);
            if(rs.next()){

            }else{
                String sq7 = "INSERT INTO param(id) VALUES(?)"; //NOI18N
                ps1 = PackFunc.Var.dbConParam.prepareStatement(sq7);
                ps1.setString(1,"1"); //NOI18N
                ps1.execute();
                ps1.close();
            }
// Options
            String sq4 = "SELECT * FROM options WHERE id='1'"; //NOI18N
            rs = ps.executeQuery(sq4);
            if(rs.next()){

            }else{
                String sq8 = "INSERT INTO options(PeriodeHeure, id) VALUES(?,?)"; //NOI18N
                ps1 = PackFunc.Var.dbConParam.prepareStatement(sq8);
                ps1.setString(1,"0"); //NOI18N
                ps1.setString(2,"1"); //NOI18N
                ps1.execute();
                ps1.close();
            }
        }catch(SQLException e){
            fun.ecritLogs(e, " - "+getClass().getName());
        } 
    }
    /***************************************************************************
    *****   Paramètres                                                     *****
    ***************************************************************************/
// Lire un paramètre
    public String paramLire(String param, String table){
        String value = null;
        try{
            String sq1 = "SELECT * FROM "+table+" WHERE id='1'"; //NOI18N
            ps = PackFunc.Var.dbConParam.createStatement();
            rs = ps.executeQuery(sq1);
            if(rs.next()){
                value = rs.getString(param);
            }
            rs.close();
            ps.close();
            }catch(SQLException e){
            fun.ecritLogs(e, " - paramLire - "+getClass().getName());
            
        }
        return value;
    }
// Ecrire un paramètre
    public void paramEcrit(String param, String value, String table){
        try{
            String sql = "UPDATE "+table+" SET '"+param+"'=? WHERE id='1';";
            ps1 = PackFunc.Var.dbConParam.prepareStatement(sql);
            ps1.setString(1, value);
            ps1.execute();
            ps1.close();
        }catch(SQLException e){
            fun.ecritLogs(e, " - paramEcrit - "+getClass().getName());
        }
    }
    public void tableIpCree(){
        

        try{
            String listColIp = listeCol("ip");
            ps = PackFunc.Var.dbConSite.createStatement();
            String sq1 = "CREATE TABLE IF NOT EXISTS ip ("+listColIp+")";
            ps.execute(sq1);
            ps.close();
        }catch (SQLException e) {
            fun.ecritLogs(e, " - tableIpCree - "+getClass().getName());
        }
    }
    
    public String listeCol(String table){
        String listeCol = "";
        Integer i = 0;
        ArrayList<String> liCol;
        if(table.equals("param")){ liCol = PackFunc.Var.bddParam; }else if(table.equals("options")){ liCol = PackFunc.Var.bddOptions; } else { liCol = PackFunc.Var.bddIp; }
        for (String col : liCol) {
            if(i==0){
                listeCol = listeCol+col+", ";
            }else if(i==liCol.size()-1){
                listeCol = listeCol+col+" DEFAULT '0'";
            }else{
                listeCol = listeCol+col+" DEFAULT '0', ";
            }
            i++;
        }
        
        return listeCol;
    }
    
    /***************************************************************************
    *****   DB une IP                                                      *****
    ***************************************************************************/
// Ajouter une IP
    public void ipAjDb(String ip, String nom, Integer id, String mac, String port){
        try{
            
            String sql = "INSERT INTO ip(id, ip, nom, latence, etat, popup, mac, port) VALUES(?,?,?,?,?,?,?,?)"; //NOI18N
                ps1 = PackFunc.Var.dbConSite.prepareStatement(sql);
                ps1.setInt(1,id); //NOI18N
                ps1.setString(2,ip); //NOI18N
                ps1.setString(3,nom); //NOI18N
                ps1.setString(4,"0"); //NOI18N
                ps1.setString(5,"0"); //NOI18N
                ps1.setString(6,"0"); //NOI18N
                ps1.setString(7,mac); //NOI18N
                ps1.setString(8,port); //NOI18N
                ps1.execute();
                ps1.close();
        }catch(SQLException e){
            fun.ecritLogs(e, " - ajIpDb - "+ip+" / "+getClass().getName());
        }
    }
// Lire la table Ip
    public String dbIpLire(String id, String param){
        String value = null;
        try{
            String sq1 = "SELECT * FROM ip WHERE ip='"+id+"'"; //NOI18N
            ps = PackFunc.Var.dbConSite.createStatement();
            rs = ps.executeQuery(sq1);
            if(rs.next()){
                value = rs.getString(param);
            }
            rs.close();
            ps.close();
            }catch(SQLException e){
            fun.ecritLogs(e, " - dbIpLire - "+id+" - "+getClass().getName());
        }
        return value;
    }
// Ecrire la table Ip
    public void dbIpEcrit(String id, String value, String param){
        try{
            String sql = "UPDATE ip SET '"+param+"'=? WHERE ip='"+id+"';";
            ps1 = PackFunc.Var.dbConSite.prepareStatement(sql);
            ps1.setString(1, value);
            ps1.execute();
            ps1.close();
        }catch(SQLException e){
            fun.ecritLogs(e, " - dbIpEcrit - "+id+" / "+getClass().getName());
        }
    }
    
    public void ipHistAj(String ip, String etat, String latence){
        String dateJour;
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd | HH:mm:ss");
        dateJour = ft.format(date);
        try{
            String sql = "INSERT INTO '"+ip+"'(date, etat, lat) VALUES(?,?,?)"; //NOI18N
                ps1 = PackFunc.Var.dbConHist.prepareStatement(sql);
                ps1.setString(1,dateJour); //NOI18N
                ps1.setString(2,etat); //NOI18N
                ps1.setString(3,latence); //NOI18N
                ps1.execute();
                ps1.close();
        }catch(SQLException e){
            //fun.ecritLogs(e, " - ipHistAj - "+ip+" / "+getClass().getName());
        }
    }
    
// Tables Ip maj
    public void listeIp(){
        try{
            FenMain.tabPrinc.removeAll();
            String sql = "SELECT ip AS 'ip', nom AS 'Nom', mac AS 'Mac', port AS 'Port', etat AS 'Etat', latence AS 'Latence' FROM ip ORDER BY "+PackFunc.Var.tri+"";
            ps1 = PackFunc.Var.dbConSite.prepareStatement(sql);
            rs = ps1.executeQuery();
            FenMain.tabPrinc.setModel(DbUtils.resultSetToTableModel(rs));
            FenMain.tabPrinc.getColumnModel().getColumn(4).setMaxWidth(50);
            FenMain.tabPrinc.getColumnModel().getColumn(5).setMaxWidth(50);

            rs.close();
            ps1.close();
        }catch(SQLException e){
            fun.ecritLogs(e, " - lisetIp - "+getClass().getName());
        }
    }
// Effacer la table Ip
    public void dbIpErase() throws ClassNotFoundException{
        try {
            Class.forName("org.sqlite.JDBC"); //NOI18N
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/db/ip.pigo"); //NOI18N
            String sq1 = "DELETE FROM ip"; //NOI18N
            ps1 = conn.prepareStatement(sq1);
            ps1.execute();
            ps1.close();
        } catch (SQLException e) {
            fun.ecritLogs(e, " - dbIpErase - "+getClass().getName()); //NOI18N
        }
    }
// Effacer la table Ip
    public void dbErase() throws ClassNotFoundException{
        try {
            String sq1 = "DELETE FROM ip"; //NOI18N
            ps1 = PackFunc.Var.dbConSite.prepareStatement(sq1);
            ps1.execute();
            ps1.close();
            //idZero();
        } catch (SQLException e) {
            fun.ecritLogs(e, " - dbErase - "+getClass().getName()); //NOI18N
        }
    }
    public void idZero(){
        try {
            String sql = "ALTER TABLE ip AUTOINCREMENT=1";
            ps1 = PackFunc.Var.dbConSite.prepareStatement(sql);
            ps1.execute();
            ps1.close();
        } catch (SQLException e) {
            fun.ecritLogs(e, " - idZero - "+getClass().getName()); //NOI18N
        }
    }
// Effacer une IP
    public void ipSiteErase(String ip){
        String sql = "DELETE FROM ip WHERE ip='"+ip+"'"; //NOI18N
        try{
            ps = PackFunc.Var.dbConSite.createStatement();
            ps.execute(sql);
            ps.close();
            
        }catch(Exception e){
            fun.ecritLogs(e, " - ipSiteErase - "+getClass().getName()); //NOI18N
        }
    }
    
    
    public void siteNouveau(String nom){
        PackFunc.Var.dbSite = nom+".pigo";
        fun.connectSite();
        tableIpCree();
        
        try{
            String sq1 = "SELECT * FROM ip"; //NOI18N
            Class.forName("org.sqlite.JDBC"); //NOI18N
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+PackFunc.Var.path+"/db/ip.pigo"); //NOI18N
            ps = conn.createStatement();
            rs = ps.executeQuery(sq1);
            while(rs.next()){
                String sql2 = "INSERT INTO ip(id, ip, nom, latence, etat, popup) VALUES(?,?,?,?,?,?)"; //NOI18N
                ps1 = PackFunc.Var.dbConSite.prepareStatement(sql2);
                ps1.setInt(1,rs.getInt("id")); //NOI18N
                ps1.setString(2,rs.getString("ip")); //NOI18N
                ps1.setString(3,rs.getString("nom")); //NOI18N
                ps1.setString(4,"0"); //NOI18N
                ps1.setString(5,"0"); //NOI18N
                ps1.setString(6,"0"); //NOI18N
                ps1.execute();
                ps1.close();
            }
            rs.close();
            ps.close();
            
        }catch(SQLException e){
        fun.ecritLogs(e, " - "+getClass().getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(funcDb.class.getName()).log(Level.SEVERE, null, ex);
            fun.ecritLogs(ex, " - SiteNouveau - "+getClass().getName());
        }
        FenMain.listeSite.removeAllItems();
        fun.listeSites();
    }
    
    public Integer ipSiteCount(){
        Integer count = null;
        try{
            String sql = "SELECT COUNT(*) AS tot FROM ip";
            ps1 = PackFunc.Var.dbConSite.prepareStatement(sql);
            rs = ps1.executeQuery();
            if(rs.next()){
                //if(Integer.parseInt(rs.getString("tot")) == 0){
                //    count = 1;
                //}else{
                   count = Integer.parseInt(rs.getString("tot")); 
                //}
                
            }
            
        }catch(SQLException e){
            fun.ecritLogs(e, " - ipSiteCount - "+getClass().getName());
        }
        return count;
    }
}
