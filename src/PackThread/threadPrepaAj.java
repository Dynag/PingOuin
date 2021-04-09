/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import PackFunc.funcDb;
import PackFunc.funcIp;
import PackFunc.funcMain;
import PackMain.FenMain;

/**
 *
 * @author Dynaglien
 */
public class threadPrepaAj extends Thread{
    String ip;
    String tout;
    Integer hote;
    funcIp fip = new funcIp();
    funcDb fdb = new funcDb();
    public threadPrepaAj(String ip1, String tout1, Integer hote1){
        ip = ip1;
        tout = tout1;
        hote = hote1;
    }
    @Override
    public void run(){
        plageAdd();
    }
    /***************************************************************************
    *****   Ajouter plage Ip                                               *****
    ***************************************************************************/
    public void plageAdd(){
        String plageIp = fip.parseIp(ip);
        Integer j = Integer.parseInt(fip.parsePremIp(ip))-1;
        Integer id = 1;
        PackFunc.Var.threadFerme = 0;
        PackFunc.Var.threadOuvert = hote;
        FenMain.labThreadOuvert.setText("Thread "+hote+" / ");
        ExecutorService execute = Executors.newFixedThreadPool(25);
        if(tout.equals("Repondant")){
            for(Integer i=Integer.parseInt(fip.parsePremIp(ip)); i <= hote+j; i++){
                execute.submit(new threadAjIpRepond(plageIp+"."+i, id));
                id++;
            }

        }else{
            for(Integer i=Integer.parseInt(fip.parsePremIp(ip)); i <= hote+j; i++){
                execute.submit(new threadAjIpTout(plageIp+"."+i, id));
                id++;
            }
        }
        execute.shutdown();
        if(Objects.equals(PackFunc.Var.threadOuvert, PackFunc.Var.threadFerme)){
            fdb.listeIp();
            threadPop t2 = new threadPop(PackFunc.Var.bundle.getString("thread.prepaaj.ok"));
            t2.start();
        
        }
        Thread.currentThread().interrupt();
        
    }
}
