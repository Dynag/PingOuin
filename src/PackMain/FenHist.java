/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackMain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartPanel;
import PackFunc.cellRendererPing;
import PackFunc.cellRendererTime;
import PackFunc.funcMain;

/**
 *
 * @author Dynaglien
 */
public class FenHist extends javax.swing.JFrame {
    ResultSet rs = null;
    Statement ps = null;
    PreparedStatement ps1 = null;
    public static String ipHist;
    /**
     * Creates new form fenHist
     */
    public FenHist() {
        initComponents();
        labIp.setText(ipHist);
        remplireChantier();
        Graph();
    }
    
    public void remplireChantier(){
        try {
            String sql = "SELECT date AS 'Date', etat AS 'Etat', lat AS 'Latence' FROM '"+ipHist+"' ORDER BY date desc";
            ps1 = PackFunc.Var.dbConHist.prepareStatement(sql);
            ps1.setFetchSize(100);
            rs = ps1.executeQuery();
            tabHist.setModel(DbUtils.resultSetToTableModel(rs));
            ps1.close();
            tabHist.getColumnModel().getColumn(1).setCellRenderer(new cellRendererPing());
            tabHist.getColumnModel().getColumn(2).setCellRenderer(new cellRendererTime());
            tabHist.getColumnModel().getColumn(1).setMaxWidth(200);
            tabHist.getColumnModel().getColumn(2).setMaxWidth(200);
        } catch (SQLException ex) {
            Logger.getLogger(FenHist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Graph(){
        PageGraphEvoLatence.ipHist = ipHist;
        PageGraphEvoLatence gl = new PageGraphEvoLatence();
        ChartPanel crepart = gl.graphEvoLatence();
        //crepart.setFillZoomRectangle(closable);
        panGraph.add(crepart);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labIp = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabHist = new javax.swing.JTable();
        panGraph = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labIp.setBackground(new java.awt.Color(102, 204, 255));
        labIp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labIp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labIp.setText("jLabel1");
        labIp.setOpaque(true);

        tabHist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabHist);

        panGraph.setPreferredSize(new java.awt.Dimension(0, 0));
        panGraph.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labIp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                    .addComponent(panGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labIp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenHist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenHist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenHist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenHist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenHist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labIp;
    private javax.swing.JPanel panGraph;
    private javax.swing.JTable tabHist;
    // End of variables declaration//GEN-END:variables
}
