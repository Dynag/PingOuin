package PackMain;

import PackFunc.funcDb;
import PackFunc.funcLicence;
import java.net.SocketException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dynaglien
 */
public class FenOption extends javax.swing.JFrame {
    funcDb fdb = new funcDb();
    /**
     * Creates new form pageOption
     */
    public FenOption() {
        initComponents();
        funcLicence fl = new funcLicence();
        
        labTitre.setText(PackFunc.Var.bundle.getString("param.option.titre"));
        cbMailPlantage.setText(PackFunc.Var.bundle.getString("param.option.mail.plantage"));
        cbMailRecap.setText(PackFunc.Var.bundle.getString("param.option.mail.recap"));
        cbPop.setText(PackFunc.Var.bundle.getString("param.option.pop"));
        cbArchive.setText(PackFunc.Var.bundle.getString("param.option.archive"));
        cbDbExtRecap.setText(PackFunc.Var.bundle.getString("param.option.dbext.recap"));
        cbDbExtPlantage.setText(PackFunc.Var.bundle.getString("param.option.dbext.plantage"));
        btnValid.setText(PackFunc.Var.bundle.getString("btn.valid"));
        
        
        try {
            if(fl.validLicense() == true){
                affichage();
            }else{
                labTitre.setText(PackFunc.Var.bundle.getString("licence.invalide"));
                cbMailPlantage.setVisible(false);
                cbMailRecap.setVisible(false);
                cbArchive.setVisible(false);
                cbDbExtRecap.setVisible(false);
                cbDbExtPlantage.setVisible(false);
                affichage();
            }
        } catch (SocketException ex) {
            Logger.getLogger(FenOption.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void affichage(){
        if(fdb.paramLire("mail_envoie", "param").equals("1")){ cbMailPlantage.setSelected(true); } else{ cbMailPlantage.setSelected(false);}
        if(fdb.paramLire("mail_rapport", "param").equals("1")){ cbMailRecap.setSelected(true); } else{ cbMailRecap.setSelected(false);}
        if(fdb.paramLire("archives", "param").equals("1")){ cbArchive.setSelected(true); } else{ cbArchive.setSelected(false);}
        if(fdb.paramLire("dbext", "param").equals("1")){ cbDbExtRecap.setSelected(true); } else{ cbDbExtRecap.setSelected(false);}
        if(fdb.paramLire("dbext_perte", "param").equals("1")){ cbDbExtPlantage.setSelected(true); } else{ cbDbExtPlantage.setSelected(false);}
        if(fdb.paramLire("pop_up", "param").equals("1")){ cbPop.setSelected(true); } else{ cbPop.setSelected(false);}

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labTitre = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbMailPlantage = new javax.swing.JCheckBox();
        cbMailRecap = new javax.swing.JCheckBox();
        cbPop = new javax.swing.JCheckBox();
        cbArchive = new javax.swing.JCheckBox();
        cbDbExtRecap = new javax.swing.JCheckBox();
        cbDbExtPlantage = new javax.swing.JCheckBox();
        btnValid = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        labTitre.setBackground(new java.awt.Color(51, 102, 255));
        labTitre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labTitre.setForeground(new java.awt.Color(255, 255, 255));
        labTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labTitre.setText("Mail recapitulatif");
        labTitre.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbMailPlantage.setText("jCheckBox1");

        cbMailRecap.setText("jCheckBox1");

        cbPop.setText("jCheckBox1");

        cbArchive.setText("jCheckBox1");

        cbDbExtRecap.setText("jCheckBox1");

        cbDbExtPlantage.setText("jCheckBox1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbPop)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbArchive)
                            .addComponent(cbDbExtRecap)
                            .addComponent(cbDbExtPlantage)
                            .addComponent(cbMailPlantage)
                            .addComponent(cbMailRecap))
                        .addGap(80, 233, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbPop)
                .addGap(16, 16, 16)
                .addComponent(cbMailPlantage)
                .addGap(18, 18, 18)
                .addComponent(cbMailRecap)
                .addGap(20, 20, 20)
                .addComponent(cbArchive)
                .addGap(18, 18, 18)
                .addComponent(cbDbExtRecap)
                .addGap(18, 18, 18)
                .addComponent(cbDbExtPlantage)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnValid.setText("Valider");
        btnValid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnValid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnValid)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidActionPerformed
        if(cbMailPlantage.isSelected()){
	fdb.paramEcrit("mail_envoie", "1", "param");
        }else{
            fdb.paramEcrit("mail_envoie", "0", "param");
        }
        if(cbMailRecap.isSelected()){
            fdb.paramEcrit("mail_rapport", "1", "param");
        }else{
            fdb.paramEcrit("mail_rapport", "0", "param");
        }
        if(cbArchive.isSelected()){
            fdb.paramEcrit("archives", "1", "param");
        }else{
            fdb.paramEcrit("archives", "0", "param");
        }
        if(cbDbExtRecap.isSelected()){
            fdb.paramEcrit("dbext", "1", "param");
        }else{
            fdb.paramEcrit("dbext", "0", "param");
        }
        if(cbDbExtPlantage.isSelected()){
            fdb.paramEcrit("dbext_perte", "1", "param");
        }else{
            fdb.paramEcrit("dbext_perte", "0", "param");
        }
        if(cbPop.isSelected()){
            fdb.paramEcrit("pop_up", "1", "param");
        }else{
            fdb.paramEcrit("pop_up", "0", "param");
	}

        
        dispose();
    }//GEN-LAST:event_btnValidActionPerformed

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
            java.util.logging.Logger.getLogger(FenOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenOption.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenOption().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnValid;
    private javax.swing.JCheckBox cbArchive;
    private javax.swing.JCheckBox cbDbExtPlantage;
    private javax.swing.JCheckBox cbDbExtRecap;
    private javax.swing.JCheckBox cbMailPlantage;
    private javax.swing.JCheckBox cbMailRecap;
    private javax.swing.JCheckBox cbPop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labTitre;
    // End of variables declaration//GEN-END:variables
}
