/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import PackFunc.funcDb;
import PackFunc.funcMain;

/**
 *
 * @author Dynaglien
 */
public class threadDbExtPerte implements Runnable{
    funcMain fun = new funcMain();
    funcDb fdb = new funcDb();
    ResultSet rs = null;
    Statement ps = null;
    PackFunc.DbConnect dbc = new PackFunc.DbConnect();
    public threadDbExtPerte(){
        
    }
    
    
    @Override
    public void run(){
        dbc.DbConnectDist();
        dbc.dbExtPerteCreate();
        Integer nbr = 0;
System.out.println("perte depart");
        try{
            String sql1 = "SELECT * FROM 'ip';";
            ps = PackFunc.Var.dbConSite.createStatement();
            rs = ps.executeQuery(sql1);
            while(rs.next()){
                if(Integer.parseInt(rs.getString("etat")) == 3){
                    dbc.dbExtPerteEcrire(rs.getString("ip"), rs.getString("nom"), "HS");
                    fdb.dbIpEcrit(rs.getString("ip"), "4", "etat");
                }
                if(Integer.parseInt(rs.getString("etat")) == 5)
               {
                    dbc.dbExtPerteEcrire(rs.getString("ip"), rs.getString("nom"), "OK");
                    fdb.dbIpEcrit(rs.getString("ip"), "0", "etat");
               }
            }
            ps.close();
        }catch(SQLException e){
            fun.ecritLogs(e, " - "+getClass().getName());
        }
System.out.println("perte fin");        
        Thread.currentThread().interrupt();
    }
}
