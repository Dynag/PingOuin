/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import PackThread.threadPop;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author mgand
 */
public class funcExportXls {
    public static void main() {
        String path="";
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle(PackFunc.Var.bundle.getString("func.export.choix"));
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                path = jfc.getSelectedFile().toString();
System.out.println("You selected the directory: " + jfc.getSelectedFile());
            }
        }
        
        try {
            new funcExportXls().export(path);
        } catch (IOException ex) {
            Logger.getLogger(funcExportXls.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void export(String path) throws FileNotFoundException, IOException {
        try{

            String filename=path+"\\"+Var.dbSite+".xls" ;
            HSSFWorkbook hwb=new HSSFWorkbook();
            
            CellStyle style = hwb.createCellStyle();  
            style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); 
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND );
            style.setAlignment(HorizontalAlignment.CENTER);
            CellStyle style1 = hwb.createCellStyle();  
            style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); 
            style1.setFillPattern(FillPatternType.SOLID_FOREGROUND );
            
            CellStyle styleHs = hwb.createCellStyle();  
            styleHs.setFillForegroundColor(IndexedColors.RED.getIndex()); 
            styleHs.setFillPattern(FillPatternType.SOLID_FOREGROUND );
            
            CellStyle styleOk = hwb.createCellStyle();  
            styleOk.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex()); 
            styleOk.setFillPattern(FillPatternType.SOLID_FOREGROUND );
            
            HSSFSheet sheet =  hwb.createSheet("new sheet");

            HSSFRow rowhead=   sheet.createRow((short)0);
            Cell cell;

            cell = rowhead.createCell((short) 0);
            cell.setCellStyle(style);
            cell.setCellValue("Ip");
            cell = rowhead.createCell((short) 1);
            cell.setCellStyle(style);
            cell.setCellValue("Nom");
            cell = rowhead.createCell((short) 2);
            cell.setCellStyle(style);
            cell.setCellValue("Adresse Mac");
            cell = rowhead.createCell((short) 3);
            cell.setCellStyle(style);
            cell.setCellValue("Port");
            cell = rowhead.createCell((short) 4);
            cell.setCellStyle(style);
            cell.setCellValue("Etat");
            freezeSheet(sheet);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = PackFunc.Var.dbConSite;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from ip ORDER BY nom");
            int i=1;
            int j=0;
            while(rs.next()){
            HSSFRow row=   sheet.createRow((short)i);
            if(j == 1){

                cell = row.createCell((short) 0);
                cell.setCellStyle(style1);
                cell.setCellValue(rs.getString("ip"));
                cell = row.createCell((short) 1);
                cell.setCellStyle(style1);
                cell.setCellValue(rs.getString("nom"));
                cell = row.createCell((short) 2);
                cell.setCellStyle(style1);
                cell.setCellValue(rs.getString("mac"));
                cell = row.createCell((short) 3);
                cell.setCellStyle(style1);
                cell.setCellValue(rs.getString("port"));
                cell = row.createCell((short) 4);
                if(rs.getString("etat").equals("0")){
                    cell.setCellValue("OK");
                    cell.setCellStyle(styleOk);
                }else{
                    cell.setCellValue("HS");
                    cell.setCellStyle(styleHs);
                }
                j=0;
            }else{

                row.createCell((short) 0).setCellValue(rs.getString("ip"));
                row.createCell((short) 1).setCellValue(rs.getString("nom"));
                row.createCell((short) 2).setCellValue(rs.getString("mac"));
                row.createCell((short) 3).setCellValue(rs.getString("port"));
                cell = row.createCell((short) 4);
                if(rs.getString("etat").equals("0")){
                    cell.setCellValue("OK");
                    cell.setCellStyle(styleOk);
                }else{
                    cell.setCellValue("HS");
                    cell.setCellStyle(styleHs);
                }
                
                
                j=1;
            }
            i++;
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            FileOutputStream fileOut =  new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            threadPop t2 = new threadPop(PackFunc.Var.bundle.getString("func.export.pop1")+path+PackFunc.Var.bundle.getString("func.export.pop2"));
            t2.start();

        } catch ( Exception ex ) {
            System.out.println(ex);
            threadPop t2 = new threadPop(PackFunc.Var.bundle.getString("func.export.error"));
            t2.start();
        }
    }
    public void freezeSheet(HSSFSheet sheet) {
   // suivant le RefName
	sheet.createFreezePane(0,1);
    }
}
