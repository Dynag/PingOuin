/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.io.IOException;
import java.net.InetAddress;
import java.time.Duration;
import java.time.Instant;
import PackFunc.cellRendererPing;
import PackFunc.cellRendererTime;
import PackFunc.funcDb;
import PackFunc.funcIp;
import PackFunc.funcMain;
import PackMain.FenMain;


/**
 *
 * @author Matthieu.GANDY
 */
public class threadPing implements Runnable{
    funcMain fun = new funcMain();
    private String ip;
    private Integer i3;
    funcIp fip = new funcIp();
    funcDb fdb = new funcDb();
    
    public threadPing(String ip1, Integer i1){
        ip = ip1;
        i3 = i1;
    }
    public void run(){
        //On affiche le nom du thread où on se trouve
        pinger();
    }
    public void pinger(){
        String result = null;
        String ipTest = isAlive(ip);
        Integer ipPresent = Integer.parseInt(fdb.dbIpLire(ip, "etat"));
        Integer ipPopup = Integer.parseInt(fdb.dbIpLire(ip, "popup"));
        Integer ipDbExt = Integer.parseInt(fdb.dbIpLire(ip, "bdext_perte"));
        if(ipTest == "500"){
            result = "HS";
            if(ipPresent <= 2){
                ipPresent = ipPresent+1;
            }
            if(ipPopup <= 2){
                ipPopup = ipPopup+1;
            }
            if(ipDbExt <= 2){
                ipDbExt = ipDbExt+1;
            }
            
            }else{
            result = "OK";
            if(ipPresent == 4){
                ipPresent = 5;
            }else if(ipPresent <= 3 || ipPresent == 5){
                ipPresent = 0;
            }
            
            if(ipPresent == 4){
                ipPresent = 5;
            }else if(ipPopup <= 3 || ipPopup == 5){
                ipPopup = 0;
            }
            
            if(ipDbExt == 4){
                ipDbExt = 5;
            }else if(ipDbExt <= 3 || ipDbExt == 5){
                ipDbExt = 0;
            }
            

                
        }
        FenMain.tabPrinc.setValueAt(result, i3, 3);
        FenMain.tabPrinc.getColumnModel().getColumn(3).setCellRenderer(new cellRendererPing());
        FenMain.tabPrinc.setValueAt(ipTest+" ms", i3, 4);
        FenMain.tabPrinc.getColumnModel().getColumn(4).setCellRenderer(new cellRendererTime());
        fdb.dbIpEcrit(ip, Integer.toString(ipPresent), "etat");
        fdb.dbIpEcrit(ip, Integer.toString(ipPopup), "popup");
        fdb.dbIpEcrit(ip, Integer.toString(ipDbExt), "bdext_perte");
        
        if(fdb.paramLire("archives", "param").equals("1")){
            fdb.ipHistAj(ip, result, ipTest);
        }
        try{
            Thread.currentThread().interrupt();
        }catch(Exception e){
            fun.ecritLogs(e, " - "+getClass().getName());
        }
    }
    
    
    
    public String isAlive(String ip) {
        Instant startTime = Instant.now();
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (address.isReachable(1000)) {
                long timeR = Duration.between(startTime, Instant.now()).toMillis();
                return Long.toString(timeR);
            }
            else{
                return ("500");
            }
        } catch (IOException e) {
            // Host not available, nothing to do here
        }
        return Duration.ofDays(1).toString();
    }
}
