/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import PackFunc.funcDb;
import PackFunc.funcLicence;
import PackFunc.funcMain;

/**
 *
 * @author Matthieu.GANDY
 */
public class threadPrepaPing extends Thread{
    ResultSet rs = null;
    PreparedStatement ps = null;
    funcDb fdb = new funcDb();
    funcMain fun = new funcMain();
    funcLicence fl = new funcLicence();
    int corePoolSize = Runtime.getRuntime().availableProcessors();
    ScheduledExecutorService execute = Executors.newScheduledThreadPool(corePoolSize-1);
    static ScheduledFuture<?> t;
    public threadPrepaPing(){
        
    }
    @Override
    public void run(){
        try{
            String sql = "SELECT * FROM ip ORDER BY "+PackFunc.Var.tri+"";
            
            
            Integer i = 0;
            ps = PackFunc.Var.dbConSite.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                t = execute.scheduleAtFixedRate(new threadPing(rs.getString("ip"),i) , 0, PackFunc.Var.delaisPause,  TimeUnit.SECONDS);
                i++;
            }
            ScheduledExecutorService  queueCancelCheckExecutor = Executors.newSingleThreadScheduledExecutor();
            queueCancelCheckExecutor.scheduleAtFixedRate(new Runnable(){
             @Override
             public void run(){
            if(PackFunc.Var.stopPing == true){
                t.cancel(true);
                execute.shutdown();
                Thread.currentThread().interrupt();
            }
            }
            },1,1,TimeUnit.SECONDS);
        }catch(SQLException e){
            fun.ecritLogs(e, " - "+getClass().getName());
        }
    }
}
