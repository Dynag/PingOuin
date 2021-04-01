/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackFunc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mgand
 */
public class funcRepareSql {
    ResultSet rs = null;
    Statement ps = null;
    private ArrayList<String> listCol=new ArrayList();
    
    public void testDbOk(String nomTable){
        creeArray(nomTable);
        boolean result = testDb(nomTable);
        if(result == false){
            JOptionPane.showMessageDialog(null, "Les tables doivent être réparés."+nomTable);
        }

        
    }
    
    public void creeArray(String nomTable){
        listCol.clear();
        try {
            String sql = "SELECT * FROM "+nomTable+";";
            ps = PackFunc.Var.dbConParam.createStatement();
            rs = ps.executeQuery(sql);
            ResultSetMetaData rsMetaData=rs.getMetaData();
            int nbrColonne = rsMetaData.getColumnCount();
            for (int i = 1; i <= nbrColonne; i++)
            {
                // Retourner le nom de la colonne
                String nom=rsMetaData.getColumnName(i);
                // Retourner le type de la colonne
                 listCol.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(funcRepareSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public boolean testDb(String nomTable){
            
            boolean dbOk = true;
            ArrayList<String> bddOptimal=new ArrayList();
            if(nomTable.equals("param")){ bddOptimal = PackFunc.Var.bddParam; } else { bddOptimal = PackFunc.Var.bddOptions; }
            if(listCol.equals(bddOptimal)){
                dbOk=true;
            }else{
                dbOk=false;
            }
            return dbOk;
        }
        public void dbRepare(String table){
            creeArray(table);
            // Afficher les noms et les types des colonne sur le console
            ArrayList<String> bddOptimal=new ArrayList();
            if(table.equals("param")){ bddOptimal = PackFunc.Var.bddParam; } else { bddOptimal = PackFunc.Var.bddOptions; }
            if(listCol.equals(bddOptimal)){
                JOptionPane.showMessageDialog(null, "Aucune réparation n'est nécessaire.");
            }else{
                
                
                for (String col : bddOptimal) 
                {
                    if(listCol.contains(col)==false){
                        String colAjout = col;
                        System.out.println(col);
                        creeColone(table, col);
                    }
                }
                JOptionPane.showMessageDialog(null, "Les tables ont été réparés.");
                
                
            }
    }
    
    public void creeColone(String nomTable, String nomColone){
        try {
            String sql = "ALTER TABLE "+nomTable+" ADD "+nomColone+" TEXT DEFAULT '0';";
            ps = PackFunc.Var.dbConParam.createStatement();
            ps.execute(sql);
            System.out.println("2");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(funcRepareSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
