/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackThread;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    PackFunc.funcDbConnect dbc = new PackFunc.funcDbConnect();
    public threadDbExtPerte(){
        
    }
    
    
    @Override
    public void run(){
        dbc.DbConnectDist();
        String table = fdb.paramLire("site", "param");
        table = table+"_perte";
        boolean tableCree = dbc.testTableDist(table);
        if(tableCree = true){ dbc.dbExtPerteCreate(); }
        ecriture();
    }
    public void ecriture(){
        Integer nbr = 0;
        String ipHsMail = "";
        String ipOkMail = "";
System.out.println("perte depart");
        try{
            String sql1 = "SELECT * FROM 'ip';";
            ps = PackFunc.Var.dbConSite.createStatement();
            rs = ps.executeQuery(sql1);
            while(rs.next()){
                if(Integer.parseInt(rs.getString("bdext_perte")) == PackFunc.Var.nbrHs){
                    ipHsMail = ipHsMail + rs.getString("ip")+" : "+rs.getString("nom") + " \n ";
                    fdb.dbIpEcrit(rs.getString("ip"), "4", "bdext_perte");
                    dbc.dbExtPerteEcrire(rs.getString("ip"), rs.getString("nom"), "HS");
System.out.println("Ecrit HS");
                }
                if(Integer.parseInt(rs.getString("bdext_perte")) == 5)
               {
                    ipOkMail = ipOkMail + rs.getString("ip")+" : "+rs.getString("nom") + " \n ";
                    fdb.dbIpEcrit(rs.getString("ip"), "0", "bdext_perte");
                    dbc.dbExtPerteEcrire(rs.getString("ip"), rs.getString("nom"), "OK");
System.out.println("Ecrit OK");
               }
                
           }
            ps.close();
            
        }catch(SQLException e){
            ecriture();
            //fun.ecritLogs(e, " - "+getClass().getName());
        }
        Thread.currentThread().interrupt();
System.out.println("perte fin");        
        
    }

}
