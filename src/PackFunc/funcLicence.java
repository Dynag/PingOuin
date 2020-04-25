/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Dynaglien
 */
public class funcLicence {
    funcDb fdb = new funcDb();
    funcMain fun = new funcMain();
// Vérification de la licence
     public boolean validLicense() throws SocketException{
        long code = recupCode();
        long license = recupLicence();
        boolean validLicence = false;
        code = (code+11235813)/2;
        if(Objects.equals(code, license))
        {
            validLicence = true;
        }
        return validLicence;
    }
// Récupération du code du PC
     public long recupCode() throws SocketException{
        long code2=0;
        long code1 = Long.parseLong(macAddresse());
        code2 = (code1 + 11235813) / 2;
        return code2;
    }
// Récupération de la licence
     public long recupLicence(){
        long code2 = 0;
        String code1 = fdb.paramLire("licence", "param");
        if(code1 == null || code1.length() == 0){
            code2 = 0;
        }else{
            code2 = Long.parseLong(code1);
        }
        return code2;
     }
// Adresse MAC du pc
    public String macAddresse() throws SocketException{
        String licenceAdd = "";
        String firstInterface = null;        
    Map<String, String> addressByNetwork = new HashMap<>();
    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

    while(networkInterfaces.hasMoreElements()){
        NetworkInterface network = networkInterfaces.nextElement();

        byte[] bmac = network.getHardwareAddress();
        if(bmac != null){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bmac.length; i++){
                sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "-" : ""));        
            }

            if(sb.toString().isEmpty()==false){
                addressByNetwork.put(network.getName(), sb.toString());
                licenceAdd = sb.toString();
                break;
            }
        }
    }

    if(firstInterface != null){
        //return addressByNetwork.get(firstInterface);
    }
    String licenceAdd1 = "";
        String[] licenceParse = licenceAdd.split("-");
        String licenceAdd2 = "";
        for(String t : licenceParse){
            licenceAdd2 = licenceAdd2+t;
        }
        for(int i = 0 ; i < licenceAdd2.length(); i++){
                String licenceLettre = licenceLettres(licenceAdd2.substring(i,i+1));
                licenceAdd1 = licenceAdd1+licenceLettre;
            }
            return licenceAdd1;
        
        
        
        
        
        
        
        // Obtenir l'adresse IP de la machine locale
        /*InetAddress address;
        String Mac = "";
        String licenceAdd = "";
        try {
            address = InetAddress.getLocalHost();
            NetworkInterface ni;
            ni = NetworkInterface.getByInetAddress(address);

            byte[] mac = ni.getHardwareAddress();

       // Afficher l'adresse Mac
            for (int i = 0; i < mac.length; i++) {
              Mac = Mac + String.format("%02X%s", mac[i], "");
              
            }
            for(int i = 0 ; i < Mac.length(); i++){
                String licenceLettre = licenceLettres(Mac.substring(i,i+1));
                licenceAdd = licenceAdd+licenceLettre;
            }
        }   catch (SocketException ex) {
            fun.ecritLogs(ex, " - "+getClass().getName());
        } catch (UnknownHostException ex) {
            fun.ecritLogs(ex, " - "+getClass().getName());
        }*/
        
    }
    public String licenceLettres(String a){
        String b;
        if(null == a){
            b = a;
        }else switch (a) {
            case "A":
            case "B":
            case "C":
            case "D":
                b = "1";
                break;
            case "E":
            case "F":
            case "G":
            case "H":
                b = "2";
                break;
            case "I":
            case "J":
            case "K":
            case "L":
                b = "3";
                break;
            case "M":
            case "N":
            case "O":
            case "P":
                b = "4";
                break;
            case "Q":
            case "R":
            case "S":
            case "T":
                b = "5";
                break;
            case "U":
            case "V":
            case "W":
            case "X":
                b = "6";
                break;
            case "Y":
            case "Z":
                b = "7";
                break;
            default:
                b = a;
                break;
        }
        return b;
    }
}
