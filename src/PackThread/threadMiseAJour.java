/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import PackFunc.funcMiseAJour;
import java.awt.Color;

/**
 *
 * @author Dynaglien
 */
public class threadMiseAJour  extends Thread{
    
    
    public threadMiseAJour(){
        
    }
    
    @Override
    public void run(){
        funcMiseAJour maj = new funcMiseAJour();
        if(maj.majDispo() == true){
            PackMain.FenMain.labMaj.setText(PackFunc.Var.bundle.getString("thread.maj.dispo"));
            PackMain.FenMain.labMaj.setForeground(Color.red);
        }else{
            PackMain.FenMain.labMaj.setText(PackFunc.Var.bundle.getString("thread.maj.ok"));
            PackMain.FenMain.labMaj.setForeground(Color.white);
        }
        Thread.currentThread().interrupt();
    }
    
    
    
}
