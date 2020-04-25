/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import PackFunc.funcDb;
import PackFunc.funcMain;
import static xyz.pingouin.serenity.PingOuin.funcMain.path;



/**
 *
 * @author Dynaglien
 */
public class threadHisto extends Thread{
    funcDb fdb = new funcDb();
    ResultSet rs = null;
    PreparedStatement ps = null;
    funcMain fun = new funcMain();
    public threadHisto(){
        
    }
    
    @Override
    public void run(){
System.out.println("Thread Hist");
        try {
            PackFunc.Var.dbConHist.close();
        } catch (SQLException ex) {
            Logger.getLogger(threadHisto.class.getName()).log(Level.SEVERE, null, ex);
        }
        File fichier = new File(path+"/db/hist.pig");
        fichier.delete();
        fun.connectHist();
        if(fdb.paramLire("archives", "param") == "0"){
            
        }else{
            try{
                String sql = "SELECT * FROM ip ORDER BY ip";
                ps = PackFunc.Var.dbConSite.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    fdb.creerTableHist(rs.getString("ip"));
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        Thread.currentThread().interrupt();
    }
}
