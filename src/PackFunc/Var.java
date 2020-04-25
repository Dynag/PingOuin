/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.sql.Connection;

/**
 *
 * @author Dynaglien
 */
public class Var {
    public static String version = "02.02.01";
    public static Connection dbConParam = null;
    public static Connection dbConSite = null;
    public static Connection dbConHist = null;
    public static String dbSite = "ip.pigo";
    public static boolean stopPing = false;
    public static Integer delaisPause = 5;
    public static Integer threadOuvert = 0;
    public static Integer threadFerme = 0;
    public static String os;
    public static String path;
}
