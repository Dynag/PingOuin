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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthieu.GANDY
 */
public class threadAjIpRepond implements Runnable{
    private String ip;
    private Integer id;
    funcDb fdb = new funcDb();
    funcIp funIp = new funcIp();
    public threadAjIpRepond(String ip1, Integer id1){
        ip = ip1;
        id = id1;
    }
    @Override
    public void run(){
        test();
    }
    public void test(){
        if(funIp.isAlive(ip) != "500"){
            try {
                Integer idIp = funcIp.idIp;
                String hostName = funIp.threadHostName(ip);
                String mac = funIp.getMac("arp -a " +ip);
                String port = "";
                Integer i=0;
                if(funcIp.testPort(80, ip) == true){ if(i==0){ port = "80"; }else{ port = port + "/80"; } i++;}
                if(funcIp.testPort(443, ip) == true){ if(i==0){ port = "443"; }else{ port = port + "/443"; } i++;}
                if(funcIp.testPort(21, ip) == true){ if(i==0){ port = "21"; }else{ port = port + "/21"; } i++;}
                if(funcIp.testPort(3389, ip) == true){ if(i==0){ port = "3389"; }else{ port = port + "/3389"; } i++;}
                fdb.ipAjDb(ip, hostName, idIp, mac, port);
                funcIp.idIp = funcIp.idIp + 1;
            } catch (IOException ex) {
                Logger.getLogger(threadAjIpRepond.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        PackFunc.Var.threadFerme = PackFunc.Var.threadFerme + 1;
        funIp.avanceThread(PackFunc.Var.threadFerme);
        FenMain.labThreadFerme.setText(Integer.toString(PackFunc.Var.threadFerme));
        fdb.listeIp();
        threadHisto th = new threadHisto();
        //th.start();
        Thread.currentThread().interrupt();
    }
    
}
