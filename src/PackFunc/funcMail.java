/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author Dynaglien
 */
public class funcMail {
    funcMain fun = new funcMain();
    funcDb fdb = new funcDb();
    public void envoieMail(String objet, String message) throws MessagingException{
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", fdb.paramLire("smtp_serv", "param"));
        properties.setProperty("mail.smtp.port", fdb.paramLire("smtp_port", "param"));
        properties.setProperty("mail.smtp.user", fdb.paramLire("smtp_user", "param"));
        properties.setProperty("mail.from", fdb.paramLire("smtp_mail", "param"));
        Session session = Session.getInstance(properties);
        MimeMessage message1 = new MimeMessage(session);
        /*
                Préparation des addresses mails
            */
        String[] mailSpliter = fdb.paramLire("mail", "param").split(",");
        InternetAddress[] toClient = new InternetAddress[mailSpliter.length];
            for(Integer i = 0; mailSpliter.length > i; i++)
            {
                try {
                    message1.addRecipients(Message.RecipientType.TO,mailSpliter[i]);
                    toClient[i] =  new InternetAddress(mailSpliter[i]);
                } catch (AddressException ex) {
                    fun.ecritLogs(ex, " - "+getClass().getName());
                } catch (MessagingException ex) {
                    fun.ecritLogs(ex, " - "+getClass().getName());
                }
            }
            String mess = stringToHTML.stringToHTML(message);
            message1.setContent(mess, "text/html; charset=utf-8");
            message1.setSubject(objet);
            message1.setHeader("Content-Type", "text/html; charset=UTF-8");
            message1.setFrom(new InternetAddress(fdb.paramLire("smtp_mail", "param")));
            /*message1.setReplyTo(new javax.mail.Address[]
            {
                new javax.mail.internet.InternetAddress(fdb.paramLire("mail", "param"))
            });*/
            Transport transport=null;
            transport = session.getTransport("smtp");
            transport.connect(fdb.paramLire("smtp_user", "param"), fdb.paramLire("smtp_pass", "param"));   
            try{
                transport.sendMessage(message1, toClient); 
            }catch(Exception e){ 
            }
            transport.close();
            
    }
    
    public boolean envoieJour(){
        boolean test = false;
        long currentTimestamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String heureDebut = dateFormat.format(currentTimestamp);
        if(fdb.paramLire("PeriodeHeure", "options").equals(heureDebut)){
            if("1".equals(fdb.paramLire("PeriodeLun", "options"))){
                if(testJourSemaine("lun.")){
                test = true;
                }
            }
            if("1".equals(fdb.paramLire("PeriodeMar", "options"))){
                if(testJourSemaine("mar.")){
                    test = true;
                }
            }
            if("1".equals(fdb.paramLire("PeriodeMerc", "options"))){
                if(testJourSemaine("mer.")){
                    test = true;
                }
            }
            if("1".equals(fdb.paramLire("PeriodeJeu", "options"))){
                if(testJourSemaine("jeu.")){
                    test = true;
                }
            }
            if("1".equals(fdb.paramLire("PeriodeVen", "options"))){
                if(testJourSemaine("ven.")){
                    test = true;
                }
            }
            if("1".equals(fdb.paramLire("PeriodeSam", "options"))){
                if(testJourSemaine("sam.")){
                    test = true;
                }
            }
            if("1".equals(fdb.paramLire("PeriodeDim", "options"))){
                if(testJourSemaine("dim.")){
                    test = true;
                }
            }
        }
        return test;
    }
     public boolean testJourSemaine(String jour){
            boolean test = false;
            
            long currentTimestamp = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE");
            String date = dateFormat.format(currentTimestamp);
            if(date.equals(jour))
            {
                test = true;
            }
            return test;
        }

}
