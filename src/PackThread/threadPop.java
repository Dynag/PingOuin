/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import javax.swing.JOptionPane;

/**
 *
 * @author Dynaglien
 */
public class threadPop  extends Thread{
    String mess;
    
    public threadPop(String mess1){
        mess = mess1;
    }
    
    @Override
    public void run(){
        JOptionPane.showMessageDialog(null, mess);
        Thread.currentThread().interrupt();
    }
    
    
    
}
