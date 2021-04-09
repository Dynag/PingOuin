/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import PackThread.threadMailPlantage;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mgand
 */
public class Test extends Thread{
    static ScheduledFuture<?> q;
    
    
    
      public Test() {
        
          
      }
@Override
    public void run(){
    int corePoolSize = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService execute = Executors.newScheduledThreadPool(corePoolSize);
        q = execute.scheduleAtFixedRate(new threadMailPlantage(), 0, 5, TimeUnit.SECONDS);
    }
        
      

}
