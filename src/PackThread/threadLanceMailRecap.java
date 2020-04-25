/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import PackFunc.funcDb;
import PackFunc.funcLicence;
import PackFunc.funcMain;

/**
 *
 * @author Dynaglien
 */
public class threadLanceMailRecap extends Thread{
    funcLicence fl = new funcLicence();
    funcDb fdb = new funcDb();
    int corePoolSize = Runtime.getRuntime().availableProcessors();
    ScheduledExecutorService execute = Executors.newScheduledThreadPool(corePoolSize);
    static ScheduledFuture<?> p;
    static ScheduledFuture<?> q;
    static ScheduledFuture<?> r;
    
    public threadLanceMailRecap(){
        
    }
    
    @Override
    public void run(){
        
        
        try {
            if(fl.validLicense() == true){
                if(fdb.paramLire("mail_rapport", "param").equals("1")){
                    p = execute.scheduleAtFixedRate(new threadMailRecap(),0 , 60, TimeUnit.SECONDS);
                }
                if(fdb.paramLire("mail_envoie", "param").equals("1")){
                    q = execute.scheduleAtFixedRate(new threadMailPlantage(), 0, 60, TimeUnit.SECONDS);
                }
                if(fdb.paramLire("pop_up", "param").equals("1")){
                    r = execute.scheduleAtFixedRate( new threadPopUp(), 0, 60, TimeUnit.SECONDS);
                }
            }
            ScheduledExecutorService  queueCancelCheckExecutor = Executors.newSingleThreadScheduledExecutor();
            queueCancelCheckExecutor.scheduleAtFixedRate(new Runnable(){
                @Override
                public void run(){
                    if(PackFunc.Var.stopPing == true){
                        p.cancel(true);
                        q.cancel(true);
                        r.cancel(true);
                        execute.shutdown();
                        Thread.currentThread().interrupt();
                    }
                }
            },1,1,TimeUnit.SECONDS);
        } catch (SocketException ex) {
            Logger.getLogger(threadLanceMailRecap.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
}
