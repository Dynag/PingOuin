/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author mgand
 */
public class FuncLang {
    ResultSet rs = null;
    Statement ps = null;
    public ResourceBundle choixLangue(){
        PackFunc.funcDb fdb = new PackFunc.funcDb();
        String langue = "en_US";
        
        langue = fdb.paramLire("langue", "param");
        ResourceBundle bundle;
        Locale locale = new Locale(langue);
        bundle = ResourceBundle.getBundle("lang.langue",locale);
        PackFunc.Var.bundle = bundle;

        return bundle;
    }
}
