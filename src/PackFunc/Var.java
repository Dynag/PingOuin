/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Dynaglien
 */
public class Var {
    // Version
    public static String version = "03.02.09";
    //Connections postgrsql
    public static Connection dbConParam = null;
    public static Connection dbConSite = null;
    public static Connection dbConHist = null;
    public static String dbSite = "ip.pigo";
    // options
    public static boolean stopPing = false;
    public static Integer delaisPause = 5;
    public static Integer threadOuvert = 0;
    public static Integer threadFerme = 0;
    public static String os;
    public static String path;
    public static String tri = "IP";
    public static Connection conn;
    // Modeles de tables
    public static ArrayList<String> bddParam = new ArrayList<>();
    public static ArrayList<String> bddOptions = new ArrayList<>();
    
    /***************************************************************************
     * Modele de la table Param
     **************************************************************************/
    public void remplireListeParam(){
        bddParam.add("id");
        bddParam.add("site");
        bddParam.add("smtp_serv");
        bddParam.add("smtp_port");
        bddParam.add("smtp_user");
        bddParam.add("smtp_mail");
        bddParam.add("smtp_pass");
        bddParam.add("mail_envoie");
        bddParam.add("licence");
        bddParam.add("pop_up");
        bddParam.add("mail_rapport");
        bddParam.add("archives");
        bddParam.add("mail");
        bddParam.add("dbext_adress");
        bddParam.add("dbext_port");
        bddParam.add("dbext_user");
        bddParam.add("dbext_pass");
        bddParam.add("dbext_delais");
        bddParam.add("dbext_name");
        bddParam.add("dbext");
        bddParam.add("dbext_purge");
        bddParam.add("dbext_perte");
        
    }
    /***************************************************************************
     * Modele de la table Options
     **************************************************************************/
    public void remplireListeOptions(){
        bddOptions.add("id");
        bddOptions.add("EnvoieMail");
        bddOptions.add("MailPeriode");
        bddOptions.add("PeriodeJour");
        bddOptions.add("PeriodeHeure");
        bddOptions.add("PeriodeLun");
        bddOptions.add("PeriodeMar");
        bddOptions.add("PeriodeMerc");
        bddOptions.add("PeriodeJeu");
        bddOptions.add("PeriodeVen");
        bddOptions.add("PeriodeSam");
        bddOptions.add("PeriodeDim");
    }
}
