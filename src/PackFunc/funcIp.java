/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import PackMain.FenMain;
import PackThread.threadPop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dynaglien
 */
public class funcIp {
    funcMain fun = new funcMain();
    funcDb fdb = new funcDb();
    public static String rowNum;
    public static Integer idIp = 1;
    
    
// Retourner l'ip du PC
    public String ipPc(){
        String ipAdd=null;
        try {
            InetAddress ip = InetAddress.getLocalHost(); 
            String ipAd = ip.getHostAddress();
            String[] ipAd1 = ipAd.split("\\."); //NOI18N
            ipAdd = ipAd1[0]+"."+ipAd1[1]+"."+ipAd1[2]+".1"; //NOI18N
        } catch (UnknownHostException e) {
            fun.ecritLogs(e, " - "+getClass().getName()); //NOI18N
        }
        return ipAdd;
    }
    
// Parse IP en plage
    public String parseIp(String ip){
        String[] ipParse = ip.split("\\.");
        String plageIp = ipParse[0]+"."+ipParse[1]+"."+ipParse[2];
        return plageIp;
    }
// Parse IP en plage
    public String parsePremIp(String ip){
        String[] ipParse = ip.split("\\.");
        String plageIp = ipParse[3];
        return plageIp;
    }

    public void avanceThread(Integer ferme){
        long result = ferme;
        FenMain.progerAjout.setVisible(true);
        FenMain.progerAjout.setMaximum(PackFunc.Var.threadOuvert);
        FenMain.progerAjout.setValue(ferme);
        FenMain.progerAjout.setString(PackFunc.Var.threadOuvert+" / "+ferme);
        if(PackFunc.Var.threadOuvert == ferme){
            idIp = 1;
            FenMain.progerAjout.setValue(1);
            threadPop t2 = new threadPop("La plage IP à bien été ajoutée");
            t2.start();
            FenMain.progerAjout.setVisible(false);
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
    public void nomHote(){
        FenMain.tfNom.setText(fdb.dbIpLire(rowNum, "nom"));
        FenMain.labIp.setText(fdb.dbIpLire(rowNum, "ip"));
    }
    
    public String threadHostName(String ipTest){ 
        String name=null;
        try {
            InetAddress host = InetAddress.getByName(ipTest);
            name=host.getCanonicalHostName();
            } catch(final Exception e) {
                fun.ecritLogs(e, " - "+getClass().getName()); //NOI18N
            }
        return name;
    }
    
    public static String getMac(String param) throws IOException{
          String mac1 = null;
          
        Process p = Runtime.getRuntime().exec(param);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            if (!line.trim().equals("")) {
                // keep only the process name
                line = line.substring(1);
                String mac = extractMacAddr(line);
                if (mac.isEmpty() == false) {
                    return mac;
                }
            }

        }
        return "";

      }
        
      public static String extractMacAddr(String str) {
        String arr[] = str.split("   ");
        for (String string : arr) {
            if (string.trim().length() == 17) {
                return string.trim().toUpperCase();
            }
        }
        return "";
    }
      
    public static boolean testPort(Integer port, String ip){
        boolean ouvert = false;
        try {
                Socket soc = new Socket (ip,port);
                ouvert = true;
        } catch(SocketException e){
                ouvert = false;
        } catch (IOException e) {
                e.printStackTrace();
} 
          return ouvert;
      }
    
}
