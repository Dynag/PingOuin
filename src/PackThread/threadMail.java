/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import PackFunc.funcMail;
import PackFunc.funcMain;

/**
 *
 * @author Dynaglien
 */
public class threadMail extends Thread{
    ResultSet rs = null;
    PreparedStatement ps = null;
    funcMain fun = new funcMain();
    String ipIp;
    String ipNom;
    String type;
    public volatile boolean running = true;
    
    public threadMail(String ipIp1, String type1){
        ipIp = ipIp1;
        type = type1;
    }
    public void run(){
        while(running == true){
        try{
            String sql = "SELECT * FROM param";
            try{
            ps = PackFunc.Var.dbConParam.prepareStatement(sql);
            rs = ps.executeQuery();
            }catch(Exception e){
                System.out.println(e);
            }
            if(rs.next()){
                Date auj =  new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                String objet;
                String message;
                if(type == "HS"){
                    message = PackFunc.Var.bundle.getString("thread.mail.messageperte1")+dateFormat.format(auj)+".\n"+
                            PackFunc.Var.bundle.getString("thread.mail.messageperte2")+rs.getString("site")+" : \n \n"+
                            ipIp+PackFunc.Var.bundle.getString("thread.mail.messageperte3");
                    objet = PackFunc.Var.bundle.getString("thread.mail.objetperte")+rs.getString("site");
                }else{
                    message = PackFunc.Var.bundle.getString("thread.mail.messageperte1")+dateFormat.format(auj)+".\n"+
                            PackFunc.Var.bundle.getString("thread.mail.messageretour")+rs.getString("site")+" : \n \n"+
                            ipIp+PackFunc.Var.bundle.getString("thread.mail.messageperte3");
                    objet = PackFunc.Var.bundle.getString("thread.mail.objetretour")+rs.getString("site");
                }
                funcMail mem = new funcMail();
                try {
                    mem.envoieMail(objet, message);
                } catch (MessagingException ex) {
                    Logger.getLogger(threadMail.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            fun.ecritLogs(ex, " - "+getClass().getName());
            Logger.getLogger(threadMail.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        running = false;
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ex){ }

        }
    }
}
