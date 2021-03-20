/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import PackFunc.funcDb;
import PackFunc.funcMail;
import PackFunc.funcMain;

/**
 *
 * @author Dynaglien
 */
public class threadMailRecap implements Runnable{
    funcMail fm = new funcMail();
    funcMain fun = new funcMain();
    funcDb fdb = new funcDb();
    ResultSet rs = null;
    Statement ps = null;
    public threadMailRecap(){
        
    }
    
    public void run(){
        Date auj = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        testEnvoie();
    }
    public void testEnvoie(){
            boolean mailEnvoie = fm.envoieJour();
            if(mailEnvoie == true){
                String message = prepaMessage();
                String objet = prepaObjet();
                try {
                    fm.envoieMail(objet, message);
                } catch (MessagingException ex) {
                    Logger.getLogger(threadMailRecap.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex);
                    fun.ecritLogs(ex, " - "+getClass().getName());
                }
            }
            Thread.currentThread().interrupt();
        }
    
/***************************************************************************
    *****       Préparation du message                                     *****
    ***************************************************************************/
    
    public String prepaMessage(){
        String mail = "Etat des périphériques : <br><br>";
        String sql1 = "SELECT * FROM ip ORDER BY nom;";
        try{
            ps = PackFunc.Var.dbConSite.createStatement();
            rs = ps.executeQuery(sql1);
            while(rs.next()){
            Integer etat = Integer.parseInt(rs.getString("etat"));
                if(etat == 0){
                    mail = mail + "- L'adresse IP <font color=green>"+rs.getString("ip")+"</font>, nommée "+rs.getString("nom")+ " est <font color=green>OK</font><br>";
                }else{
                    mail = mail + "- L'adresse IP <font color=red>"+rs.getString("ip")+"</font>, nommée "+rs.getString("nom")+ " est <font color=red>HS</font><br>";
                }
            }
        }catch(Exception e){
            System.out.println(e);
            fun.ecritLogs(e, " - "+getClass().getName());
        }
        return mail;
    }
    
    /***************************************************************************
    *****       Préparation de l'objet                                     *****
    ***************************************************************************/
    
    public String prepaObjet(){
        String objet = null;
        Date auj =  new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dateMail = dateFormat.format(auj);
        objet = "Etat du site "+fdb.paramLire("site", "param")+" au "+dateMail;
        return objet;
    }
    
}
