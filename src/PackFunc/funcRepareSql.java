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
            //JOptionPane.showMessageDialog(null, "Les tables doivent être réparés."+nomTable);
        }

        
    }
    /***************************************************************************
     * Tables Param
     ***************************************************************************/
    
    /**
     * Créer la liste des colones
     * @param nomTable 
     */
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
    /**
     * Tester si une réparation est nécessaire
     * @param nomTable
     * @return 
     */
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
        /**
         * Réparer la table
         * @param table 
         */
        public void dbRepare(String table){
            creeArray(table);
            // Afficher les noms et les types des colonne sur le console
            ArrayList<String> bddOptimal=new ArrayList();
            if(table.equals("param")){ bddOptimal = PackFunc.Var.bddParam; } else { bddOptimal = PackFunc.Var.bddOptions; }
            if(listCol.equals(bddOptimal)){
            }else{
                
                
                for (String col : bddOptimal) 
                {
                    if(listCol.contains(col)==false){
                        String colAjout = col;
                        creeColone(table, col);
                    }
                }
                
                
            }
    }
    /**
     * Créer la colone manquante
     * @param nomTable
     * @param nomColone 
     */
    public void creeColone(String nomTable, String nomColone){
        try {
            String sql = "ALTER TABLE "+nomTable+" ADD "+nomColone+" TEXT DEFAULT '0';";
            ps = PackFunc.Var.dbConParam.createStatement();
            ps.execute(sql);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(funcRepareSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /***************************************************************************
     * Tables IP
     **************************************************************************/
    
    /**
     * Créer la liste des colones
     * @param nomTable 
     */
    public void creeArrayIp(String nomTable){
        listCol.clear();
        try {
            String sql = "SELECT * FROM ip;";
            ps = PackFunc.Var.dbConSite.createStatement();
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
    /**
     * Tester si une réparation est nécessaire
     * @param nomTable
     * @return 
     */
        public boolean testDbIp(String nomTable){
            
            boolean dbOk = true;
            ArrayList<String> bddOptimal=new ArrayList();
            if(listCol.equals(PackFunc.Var.bddIp)){
                dbOk=true;
            }else{
                dbOk=false;
            }
            return dbOk;
        }
        /**
         * Réparer la table
         * @param table 
         */
        public void dbRepareIp(String table){
            creeArrayIp(table);
            // Afficher les noms et les types des colonne sur le console
            ArrayList<String> bddOptimal=new ArrayList();
            bddOptimal = PackFunc.Var.bddIp;
            if(listCol.equals(PackFunc.Var.bddIp)){
            }else{
                for (String col : bddOptimal) 
                {
                    if(listCol.contains(col)==false){
                        String colAjout = col;
                        creeColoneIp(table, col);
                    }
                }
            }
    }
    /**
     * Créer la colone manquante
     * @param nomTable
     * @param nomColone 
     */
    public void creeColoneIp(String nomTable, String nomColone){
        try {
            String sql = "ALTER TABLE ip ADD "+nomColone+" TEXT DEFAULT '0';";
            ps = PackFunc.Var.dbConSite.createStatement();
            ps.execute(sql);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(funcRepareSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
