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
public class threadPopUp implements Runnable{
    funcMain fun = new funcMain();
    funcDb fdb = new funcDb();
    ResultSet rs = null;
    Statement ps = null;
    public threadPopUp(){
        
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
                if(Integer.parseInt(rs.getString("popup")) == PackFunc.Var.nbrHs){
                    ipHsMail = ipHsMail + rs.getString("ip")+" : "+rs.getString("nom") + " \n ";
                    fdb.dbIpEcrit(rs.getString("ip"), "4", "popup");
                }
                if(Integer.parseInt(rs.getString("etat")) == 5)
               {
                    ipOkMail = ipOkMail + rs.getString("ip")+" : "+rs.getString("nom") + " \n ";
                    fdb.dbIpEcrit(rs.getString("ip"), "0", "popup");
               }
            }
            ps.close();
        }catch(SQLException e){
        fun.ecritLogs(e, " - "+getClass().getName());
        }
        if(ipHsMail != ""){
            threadPop tpop = new threadPop(PackFunc.Var.bundle.getString("thread.popup.hs")+ipHsMail);
            tpop.start();
        }
        if(ipOkMail != ""){
            threadPop tpop = new threadPop(PackFunc.Var.bundle.getString("thread.popup.ok")+ipOkMail);
            tpop.start();
        }
        
        Thread.currentThread().interrupt();
        
    }
}
