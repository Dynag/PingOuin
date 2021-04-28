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
System.out.println("Mail HS "+PackFunc.Var.nbrHs);
        Integer nbr = 0;
        String ipHsMail = "";
        String ipOkMail = "";
        try{
            String sql1 = "SELECT * FROM 'ip';";
            ps = PackFunc.Var.dbConSite.createStatement();
            rs = ps.executeQuery(sql1);
            while(rs.next()){
                if(Integer.parseInt(rs.getString("etat")) == 500)
               {
                    ipOkMail = ipOkMail + "<table width=90% align=center><tr><td width=50%>"+rs.getString("ip")+"</tD><td width=50%>"+rs.getString("nom") + "</td></tr></table>";
                    fdb.dbIpEcrit(rs.getString("ip"), "0", "etat");
               }
                else if(Integer.parseInt(rs.getString("etat")) == PackFunc.Var.nbrHs){
                    ipHsMail = ipHsMail + "<table width=90% align=center><tr><td width=50%>"+rs.getString("ip")+"</tD><td width=50%>"+rs.getString("nom") + "</td></tr></table>";
                    
                    fdb.dbIpEcrit(rs.getString("ip"), "400", "etat");
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
