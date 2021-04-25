/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import PackFunc.funcDb;
import PackFunc.funcMain;

/**
 *
 * @author Dynaglien
 */
public class threadMailPlantage implements Runnable{
    funcMain fun = new funcMain();
    funcDb fdb = new funcDb();
    ResultSet rs = null;
    Statement ps = null;
    public threadMailPlantage(){
        
    }
    
    
    @Override
    public void run(){
        Integer nbr = 0;
        String ipHsMail = "";
        String ipOkMail = "";
        try{
            String sql1 = "SELECT * FROM 'ip';";
            ps = PackFunc.Var.dbConSite.createStatement();
            rs = ps.executeQuery(sql1);
            while(rs.next()){
                if(Integer.parseInt(rs.getString("etat")) == PackFunc.Var.nbrHs){
                    ipHsMail = ipHsMail + rs.getString("ip")+" : "+rs.getString("nom") + " \n ";
                    
                    fdb.dbIpEcrit(rs.getString("ip"), "4", "etat");
                }
                if(Integer.parseInt(rs.getString("etat")) == 5)
               {
                    ipOkMail = ipOkMail + rs.getString("ip")+" : "+rs.getString("nom") + " \n ";
                    fdb.dbIpEcrit(rs.getString("ip"), "0", "etat");
               }
            }
            ps.close();
        }catch(SQLException e){
            fun.ecritLogs(e, " - "+getClass().getName());
        }
        if(ipHsMail != ""){
            threadMail t4 = new threadMail(ipHsMail, "HS");
            t4.start();
        }
        if(ipOkMail != ""){
            threadMail t4 = new threadMail(ipOkMail, "OK");
            t4.start();
        }
        Thread.currentThread().interrupt();
    }
}
