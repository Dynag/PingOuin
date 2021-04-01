/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.sql.ResultSet;
import java.sql.Statement;
import PackFunc.funcDb;
import PackFunc.funcMain;

/**
 *
 * @author Dynaglien
 */
public class threadDbExt implements Runnable{
    funcMain fun = new funcMain();
    funcDb fdb = new funcDb();
    PackFunc.funcDbConnect dbc = new PackFunc.funcDbConnect();
    ResultSet rs = null;
    Statement ps = null;
    
    public void run(){
        dbc.DbConnectDist();
        boolean tableCree = dbc.testTableDist(fdb.paramLire("site", "param"));
        if(tableCree = true){ dbc.dbExtRecapCreate(); }
        testEnvoie();
    }
    public void testEnvoie(){
        if(fdb.paramLire("dbext_purge", "param").equals("1")){
            dbc.dbExtPurge();
        }
        String sql1 = "SELECT * FROM ip ORDER BY nom;";
        try{
            ps = PackFunc.Var.dbConSite.createStatement();
            rs = ps.executeQuery(sql1);
            while(rs.next()){
            Integer etat = Integer.parseInt(rs.getString("etat"));
            String ip = rs.getString("ip");
            String nom = rs.getString("nom");
            String etatIp;
            if(etat == 0){
                etatIp = "OK";
            }else{
                etatIp = "HS";
            }
            dbc.dbExtEcrire(ip, nom, etatIp);
            }
            ps.close();
            Thread.currentThread().interrupt();
System.out.println("Fin du Thread eriture");
        }catch(Exception e){
            System.out.println(e);
            //fun.ecritLogs(e, " - "+getClass().getName());
            testEnvoie();
        }
            
        }
    
}