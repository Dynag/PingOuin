/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author mgand
 */
public class FuncRac {
    public void creerRacBureau() throws IOException, InterruptedException{
      String commande1 =  "@echo off &&" +
                            "echo Set oWS = WScript.CreateObject(\"WScript.Shell\") > CreateShortcut.vbs &&" +
                            "echo sLinkFile = \"%HOMEDRIVE%%HOMEPATH%\\Desktop\\PingOuin.lnk\" >> CreateShortcut.vbs &&" +
                            "echo Set oLink = oWS.CreateShortcut(sLinkFile) >> CreateShortcut.vbs &&" +
                            "echo oLink.TargetPath = \""+PackFunc.Var.path+"\\PingOuin.jar\" >> CreateShortcut.vbs &&" +
                            "echo oLink.WorkingDirectory  = \""+PackFunc.Var.path+"\" >> CreateShortcut.vbs &&" +
                            "echo oLink.IconLocation = \""+PackFunc.Var.path+"\\ico.ico\" >> CreateShortcut.vbs &&"+
                            "echo oLink.Save >> CreateShortcut.vbs &&" +
                            "cscript CreateShortcut.vbs &&" +
                            "del CreateShortcut.vbs";

        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(new String[]{"cmd.exe","/c "+commande1, "start"});
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
        public void creerRacDemarrer() throws IOException, InterruptedException{
        // Il est également possible de spécifier des options de copie.
        // Ici : écrase le fichier destination s'il existe et copie les attributs de la source sur la destination.
        //Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        String commande1 =  "@echo off &&" +
                            "echo Set oWS = WScript.CreateObject(\"WScript.Shell\") > CreateShortcut.vbs &&" +
                            "echo sLinkFile = \"%APPDATA%\\Microsoft\\Windows\\Start Menu\\Programs\\PingOuin.lnk\" >> CreateShortcut.vbs &&" +
                            "echo Set oLink = oWS.CreateShortcut(sLinkFile) >> CreateShortcut.vbs &&" +
                            "echo oLink.TargetPath = \""+PackFunc.Var.path+"\\PingOuin.jar\" >> CreateShortcut.vbs &&" +
                            "echo oLink.WorkingDirectory  = \""+PackFunc.Var.path+"\\PingOuin\" >> CreateShortcut.vbs &&" +
                            "echo oLink.IconLocation = \""+PackFunc.Var.path+"\\ico.ico\" >> CreateShortcut.vbs &&"+
                            "echo oLink.Save >> CreateShortcut.vbs &&" +
                            "cscript CreateShortcut.vbs &&" +
                            "del CreateShortcut.vbs";
        

        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(new String[]{"cmd.exe","/c "+commande1, "start"});
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        /*String[] commande = {"cmd.exe","/c",commande1};
        ProcessBuilder pb = new ProcessBuilder(commande);
        pb.redirectError(Redirect.INHERIT);
        pb.redirectOutput(Redirect.INHERIT);
        Process p = pb.start();
        p.waitFor();*/
    }
}
