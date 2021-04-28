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
        System.out.println("001");
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        testEnvoie();
    }
    public void testEnvoie(){
System.out.println("002");
            boolean mailEnvoie = fm.envoieJour();
            if(mailEnvoie == true){
                String message = prepaMessage();
                String objet = prepaObjet();
System.out.println("007");
                try {
System.out.println("008");
                    fm.envoieMail(objet, message);
System.out.println("009");
                } catch (MessagingException ex) {
                    Logger.getLogger(threadMailRecap.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex);
                    fun.ecritLogs(ex, " - "+getClass().getName());
                }
            }
            System.out.println("003");
            Thread.currentThread().interrupt();
        }
    
/***************************************************************************
    *****       Préparation du message                                     *****
    ***************************************************************************/
    
    public String prepaMessage(){
System.out.println("005");
        String mail = "<p style='font-size: 15px; font-weight: bold; font-family: arial, times, serif; background-color: #9DFFAF; border-radius:10px; text-align: center; padding:5px;border-style: groove;'>"
                + "Etat des périphériques : </p>";
        String sql1 = "SELECT * FROM ip ORDER BY nom;";
        try{
            ps = PackFunc.Var.dbConSite.createStatement();
            rs = ps.executeQuery(sql1);
            while(rs.next()){
            Integer etat = Integer.parseInt(rs.getString("etat"));
System.out.println(etat);
                if(etat == 0){
                    mail = mail + "<table alig=center width=90% bgcolor=#8AFBB1><tr><td width=33%>" +PackFunc.Var.bundle.getString("thread.mailrecap.message1")+" "+rs.getString("ip")+"</td><td width=33%>, "+PackFunc.Var.bundle.getString("thread.mailrecap.message2")+" "+rs.getString("nom")+ "</td><td width=33%>"+PackFunc.Var.bundle.getString("thread.mailrecap.message3")+" "+PackFunc.Var.bundle.getString("thread.mailrecap.messageok")+"</td></tr></table>";
                }else{
                    mail = mail + "<table align=center width=90% bgcolor=#FA9493><tr><td width=33%>" +PackFunc.Var.bundle.getString("thread.mailrecap.message1")+" "+rs.getString("ip")+"</td><td width=33%>, "+PackFunc.Var.bundle.getString("thread.mailrecap.message2")+" "+rs.getString("nom")+ "</td><td width=33%>"+PackFunc.Var.bundle.getString("thread.mailrecap.message3")+" "+PackFunc.Var.bundle.getString("thread.mailrecap.messagehs")+"</td></tr></table>";
                }
            }
        }catch(Exception e){
            System.out.println(e);
            fun.ecritLogs(e, " - "+getClass().getName());
        }
        System.out.println(mail);
        return mail;
    }
    
    /***************************************************************************
    *****       Préparation de l'objet                                     *****
    ***************************************************************************/
    
    public String prepaObjet(){
System.out.println("006");
        String objet = null;
        Date auj =  new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dateMail = dateFormat.format(auj);
        System.out.println(dateMail);
        objet = PackFunc.Var.bundle.getString("thread.mailrecap.objet")+" "+fdb.paramLire("site", "param")+" "+PackFunc.Var.bundle.getString("thread.mailrecap.objet2")+" "+dateMail;
        System.out.println(objet);
        return objet;
    

    }
    
}
