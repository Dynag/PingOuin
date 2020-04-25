/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import PackFunc.funcDb;
import PackFunc.funcIp;
import PackFunc.funcMain;
import PackMain.FenMain;

/**
 *
 * @author Matthieu.GANDY
 */
public class threadAjIpTout implements Runnable{
    private String ip;
    private Integer id;
    funcDb fdb = new funcDb();
    funcIp funIp = new funcIp();
    public threadAjIpTout(String ip1, Integer id1){
        ip = ip1;
        id = id1;
    }
    @Override
    public void run(){
        test();
    }
    public void test(){
        String hostName;
        if(funIp.isAlive(ip).equals("500")){
            hostName = ip;
        }else{
            hostName = funIp.threadHostName(ip);
        }
        fdb.ipAjDb(ip, hostName, id+1);
        funcIp.idIp = funcIp.idIp + 1;
        PackFunc.Var.threadFerme = PackFunc.Var.threadFerme + 1;
        funIp.avanceThread(PackFunc.Var.threadFerme);
        FenMain.labThreadFerme.setText(Integer.toString(PackFunc.Var.threadFerme));
        fdb.listeIp();
        //threadHisto th = new threadHisto();
        //th.start();
        Thread.currentThread().interrupt();
    }
    
}
