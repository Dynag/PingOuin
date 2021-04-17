/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author mgand
 */
public class FuncLang {
    public static ResourceBundle choixLangue(){
        ResourceBundle bundle;
        Locale locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("lang.langue",locale);

        return bundle;
    }
}
