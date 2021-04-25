/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author mgand
 */
public class funcImportXls {
    PackFunc.funcDb funDb = new PackFunc.funcDb();
    
    public static void main(){
        String path="";
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle(PackFunc.Var.bundle.getString("func.import.choix"));
        //jfc.setFileSelectionMode(JFileChooser.);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            path = selectedFile.getAbsolutePath();
        }
        try {
            new funcImportXls().xlsImport(path);
        } catch (IOException ex) {
            Logger.getLogger(funcImportXls.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void xlsImport(String FILE_NAME) throws IOException 
     {
        
                         try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator iterator = datatypeSheet.iterator();
            Integer c = 0;
            while (iterator.hasNext()) {
                Integer i = 0;
                
                    String ip = null;
                    String nom = null;
                    String mac = null;
                    String port = null;
                    Row currentRow = (Row) iterator.next();
                    Iterator cellIterator = currentRow.iterator();
                    while (cellIterator.hasNext()) {
                        Cell currentCell = (Cell) cellIterator.next();
                        if(i == 0){ ip = currentCell.getStringCellValue(); }
                        if(i == 1){ nom = currentCell.getStringCellValue(); }
                        if(i == 2){ mac = currentCell.getStringCellValue(); }
                        if(i == 3){ port = currentCell.getStringCellValue(); }
                        i++;
                    }
                    if(c > 0){
                    
                    funDb.ipAjDb(ip, nom, c, mac, port);
                }
                c++;
            }
            funDb.listeIp();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
}
