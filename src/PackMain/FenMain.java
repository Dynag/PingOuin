/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackMain;

import PackFunc.Var;
import PackThread.threadHisto;
import PackThread.threadPrepaPing;
import PackThread.lanceThread;
import PackThread.threadPrepaAj;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import PackFunc.funcDb;
import PackFunc.funcIp;
import PackFunc.funcMain;
import PackFunc.funcMiseAJour;
import java.awt.event.KeyEvent;


/**
 *
 * @author Hemge
 */
public final class FenMain extends javax.swing.JFrame {
    funcMain fun = new funcMain();
    funcIp funIp = new funcIp();
    funcDb fdb = new funcDb();
    PackFunc.Var funVar = new PackFunc.Var();
    funcDb funDb = new funcDb();
    PackFunc.funcRepareSql rep = new PackFunc.funcRepareSql();

    /**
     * Creates new form mainAcc
     */
    public FenMain(){
    System.out.println("00");    
        fun.testOs();
        File fichier = new File(PackFunc.Var.path+"/db/ip.pigo");
        fichier.delete();
        

        
//menTest.setVisible(false);
        fun.dossCree();
        
        try {
            fun.connectParam();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FenMain.class.getName()).log(Level.SEVERE, null, ex);
            fun.ecritLogs(ex, " - "+getClass().getName());        }
        
        funVar.remplireListeParam();
        funVar.remplireListeOptions();
        funVar.remplireListeIp();
        
        if(fdb.testTable("param")==false || fdb.testTable("options")==false){ funDb.creerTables(); }
        funDb.tablesRemplir();
        
        rep.testDbOk("param");
        rep.testDbOk("options");
        PackFunc.funcRepareSql prsql = new PackFunc.funcRepareSql();
        prsql.dbRepare("param");
        prsql.dbRepare("options");
        PackThread.threadMiseAJour tMaJ = new PackThread.threadMiseAJour();
        tMaJ.start();
        initComponents();
        lang();
        
        progerAjout.setVisible(false);
        btnLancer.setBackground(Color.red);
        
System.out.println(PackFunc.Var.dbConParam);
// Test mise à jour
        
        
// Création des tables
        
        
        
        fun.connectHist();
        
        funDb.tableIpCree();
        tfPremIp.setText(funIp.ipPc());
        fun.listeSites();
        threadHisto th = new threadHisto();
        //th.start();

    fdb.listeIp();
    }
    
    public void lang(){
        PackFunc.FuncLang langue= new PackFunc.FuncLang();
        
        // Choix de la langue
        langue.choixLangue();
        labVersion.setText(PackFunc.Var.bundle.getString("PingOuin.version")+" "+PackFunc.Var.version);
        labAjout.setText(PackFunc.Var.bundle.getString("main.ajout"));
        labPingDelais.setText(PackFunc.Var.bundle.getString("main.ping.delais"));
        labModif.setText(PackFunc.Var.bundle.getString("main.modif"));
        btnNav.setText(PackFunc.Var.bundle.getString("main.btn.nav"));
        btnClear.setText(PackFunc.Var.bundle.getString("main.btn.clear"));
        btnEfface.setText(PackFunc.Var.bundle.getString("main.btn.efface"));
        labNbrHs.setText(PackFunc.Var.bundle.getString("main.nbrHs"));
        
        menFichier.setText(PackFunc.Var.bundle.getString("menu.fichier"));
        menFichierExport.setText(PackFunc.Var.bundle.getString("menu.fichier.export"));
        menFichierImport.setText(PackFunc.Var.bundle.getString("menu.fichier.import"));
        menFichierSave.setText(PackFunc.Var.bundle.getString("menu.fichier.save"));
        menFichierSites.setText(PackFunc.Var.bundle.getString("menu.fichier.sites"));
        menFichierMaj.setText(PackFunc.Var.bundle.getString("menu.fichier.maj"));
        menFichierQuitter.setText(PackFunc.Var.bundle.getString("menu.fichier.quitter"));
        
        menParam.setText(PackFunc.Var.bundle.getString("menu.param"));
        menParamParam.setText(PackFunc.Var.bundle.getString("menu.param.param"));
        menParamOption.setText(PackFunc.Var.bundle.getString("menu.param.option"));
        
        menAutre.setText(PackFunc.Var.bundle.getString("menu.autre"));
        menAutreApropos.setText(PackFunc.Var.bundle.getString("menu.autre.apropos"));
        menAutreSite.setText(PackFunc.Var.bundle.getString("menu.autre.site"));
        menTest.setText(PackFunc.Var.bundle.getString("menu.autre.test"));
        menAutreChangelog.setText(PackFunc.Var.bundle.getString("menu.autre.changelog"));
    }
    
    
    /**
     * Test de la licence
     */
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLancer = new javax.swing.JToggleButton();
        progerAjout = new javax.swing.JProgressBar();
        labMaj = new javax.swing.JLabel();
        comboTri = new javax.swing.JComboBox<>();
        labImg = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        labAjout = new javax.swing.JLabel();
        tfPremIp = new javax.swing.JTextField();
        spinPlage = new javax.swing.JSpinner();
        cbTout = new javax.swing.JComboBox<>();
        btnValid = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        labPingDelais = new javax.swing.JLabel();
        labDelaisPing = new javax.swing.JLabel();
        slidDelaisPing = new javax.swing.JSpinner();
        labNbrHs = new javax.swing.JLabel();
        spinNbrHs = new javax.swing.JSpinner();
        jPanel9 = new javax.swing.JPanel();
        listeSite = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        labThreadOuvert = new javax.swing.JLabel();
        labThreadFerme = new javax.swing.JLabel();
        labVersion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabPrinc = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        tfNom = new javax.swing.JTextField();
        labModif = new javax.swing.JButton();
        labIp = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnNav = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnClear = new javax.swing.JButton();
        btnEfface = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menFichier = new javax.swing.JMenu();
        menFichierExport = new javax.swing.JMenuItem();
        menFichierImport = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        menFichierSave = new javax.swing.JMenuItem();
        menFichierSites = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menFichierMaj = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menFichierQuitter = new javax.swing.JMenuItem();
        menParam = new javax.swing.JMenu();
        menParamParam = new javax.swing.JMenuItem();
        menParamOption = new javax.swing.JMenuItem();
        menAutre = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menAutreApropos = new javax.swing.JMenuItem();
        menAutreSite = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menTest = new javax.swing.JMenuItem();
        menAutreChangelog = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PingOuin");

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        btnLancer.setText("Lancer le ping");
        btnLancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancerActionPerformed(evt);
            }
        });

        progerAjout.setMaximum(254);

        labMaj.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labMaj.setForeground(new java.awt.Color(255, 255, 255));
        labMaj.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labMaj.setText("A jour");

        comboTri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trier par", "IP", "Nom" }));
        comboTri.setMaximumSize(new java.awt.Dimension(47, 20));
        comboTri.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTriItemStateChanged(evt);
            }
        });
        comboTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTriActionPerformed(evt);
            }
        });

        labImg.setBackground(new java.awt.Color(255, 255, 255));
        labImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PackImg/logo.png"))); // NOI18N
        labImg.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labImg, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLancer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboTri, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progerAjout, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labMaj, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labMaj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labImg, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLancer, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progerAjout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labAjout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labAjout.setText("Ajout IP");

        tfPremIp.setToolTipText("Première IP");

        spinPlage.setToolTipText("Nombres d'hotes à ajouter");
        spinPlage.setValue(1);

        cbTout.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plage", "Repondant" }));
        cbTout.setToolTipText("Ajouter toute la plage ou seulement les hotes répondant");
        cbTout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbToutActionPerformed(evt);
            }
        });

        btnValid.setText("Valider");
        btnValid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTout, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labAjout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfPremIp)
                    .addComponent(spinPlage)
                    .addComponent(btnValid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labAjout)
                .addGap(18, 18, 18)
                .addComponent(tfPremIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(spinPlage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbTout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnValid)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labPingDelais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labPingDelais.setText("Delais entre 2 pings");

        labDelaisPing.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labDelaisPing.setText("5 s");

        slidDelaisPing.setValue(5);
        slidDelaisPing.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slidDelaisPingStateChanged(evt);
            }
        });

        labNbrHs.setText("jLabel1");

        spinNbrHs.setValue(3);
        spinNbrHs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinNbrHsStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labPingDelais, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(labDelaisPing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(slidDelaisPing, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labNbrHs, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(spinNbrHs))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labPingDelais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slidDelaisPing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labDelaisPing)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labNbrHs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spinNbrHs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        listeSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeSiteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listeSite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listeSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        labThreadOuvert.setForeground(new java.awt.Color(255, 255, 255));
        labThreadOuvert.setText("Thread : 0 / ");

        labThreadFerme.setForeground(new java.awt.Color(255, 255, 255));

        labVersion.setForeground(new java.awt.Color(255, 255, 255));
        labVersion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labVersion.setText("jLabel3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(labThreadOuvert, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labThreadFerme, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labThreadOuvert, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labThreadFerme, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(labVersion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        tabPrinc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabPrinc.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabPrinc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabPrincFocusLost(evt);
            }
        });
        tabPrinc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPrincMouseClicked(evt);
            }
        });
        tabPrinc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabPrincKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabPrinc);

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labModif.setText("Modifier");
        labModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labModifActionPerformed(evt);
            }
        });

        labIp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNom)
                    .addComponent(labModif, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(labIp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(labIp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labModif)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnNav.setText("Ouvrir navigateur");
        btnNav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNav, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNav)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnEfface.setText("Effacer ip");
        btnEfface.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffaceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEfface, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEfface, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menFichier.setText("Fichier");

        menFichierExport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menFichierExport.setText("Exporter en xls");
        menFichierExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFichierExportActionPerformed(evt);
            }
        });
        menFichier.add(menFichierExport);

        menFichierImport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menFichierImport.setText("Importer depuis un xls");
        menFichierImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFichierImportActionPerformed(evt);
            }
        });
        menFichier.add(menFichierImport);
        menFichier.add(jSeparator6);

        menFichierSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menFichierSave.setText("Enregistrer la plage");
        menFichierSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFichierSaveActionPerformed(evt);
            }
        });
        menFichier.add(menFichierSave);

        menFichierSites.setText("Gestion des sites");
        menFichierSites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFichierSitesActionPerformed(evt);
            }
        });
        menFichier.add(menFichierSites);
        menFichier.add(jSeparator7);

        menFichierMaj.setText("Mise a jour");
        menFichierMaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFichierMajActionPerformed(evt);
            }
        });
        menFichier.add(menFichierMaj);
        menFichier.add(jSeparator1);

        menFichierQuitter.setText("Quitter");
        menFichierQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFichierQuitterActionPerformed(evt);
            }
        });
        menFichier.add(menFichierQuitter);

        jMenuBar1.add(menFichier);

        menParam.setText("Paramètres");

        menParamParam.setText("Paramètres généraux");
        menParamParam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menParamParamActionPerformed(evt);
            }
        });
        menParam.add(menParamParam);

        menParamOption.setText("Options");
        menParamOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menParamOptionActionPerformed(evt);
            }
        });
        menParam.add(menParamOption);

        jMenuBar1.add(menParam);

        menAutre.setText("?");

        jMenuItem13.setText("Updater BDD");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        menAutre.add(jMenuItem13);
        menAutre.add(jSeparator4);

        menAutreApropos.setText("A propos");
        menAutreApropos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAutreAproposActionPerformed(evt);
            }
        });
        menAutre.add(menAutreApropos);

        menAutreSite.setText("Site internet");
        menAutreSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAutreSiteActionPerformed(evt);
            }
        });
        menAutre.add(menAutreSite);
        menAutre.add(jSeparator5);

        menTest.setText("Test");
        menTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menTestActionPerformed(evt);
            }
        });
        menAutre.add(menTest);

        menAutreChangelog.setText("Changelog");
        menAutreChangelog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAutreChangelogActionPerformed(evt);
            }
        });
        menAutre.add(menAutreChangelog);

        jMenuBar1.add(menAutre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menParamParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menParamParamActionPerformed
        FenParam pp = new FenParam();
        pp.show();
    }//GEN-LAST:event_menParamParamActionPerformed

    private void listeSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeSiteActionPerformed
        PackFunc.Var.stopPing = true;
        PackFunc.Var.dbSite = (String) listeSite.getSelectedItem()+".pigo";
        btnLancer.setText(PackFunc.Var.bundle.getString("main.start"));
        btnLancer.setBackground(Color.red);
        fun.connectSite();
        rep.dbRepareIp("ip");
        fdb.listeIp();
        //threadHisto th = new threadHisto();
        //th.start();
        
    }//GEN-LAST:event_listeSiteActionPerformed

    private void btnValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidActionPerformed
        if((Integer) spinPlage.getValue() > 255){
            PackThread.threadPop tpop = new PackThread.threadPop(PackFunc.Var.bundle.getString("main.error.255"));
            tpop.start();
        }else{
            threadPrepaAj tpaj = new threadPrepaAj(tfPremIp.getText(), (String) cbTout.getSelectedItem(), (Integer) spinPlage.getValue());
            tpaj.start();
        }
        
    }//GEN-LAST:event_btnValidActionPerformed

    private void cbToutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbToutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbToutActionPerformed

    private void menFichierQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFichierQuitterActionPerformed
        fun.quit();
    }//GEN-LAST:event_menFichierQuitterActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        try {
            fdb.dbErase();
        } catch (ClassNotFoundException ex) {
            fun.ecritLogs(ex, " - "+getClass().getName());
        }
        PackFunc.Var.stopPing = true;
        btnLancer.setText(PackFunc.Var.bundle.getString("main.start"));
        btnLancer.setBackground(Color.red);
        fdb.listeIp();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnLancerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancerActionPerformed
        funcDb funDb = new funcDb();
        threadPrepaPing tpp = new threadPrepaPing();
        if(FenMain.btnLancer.isSelected()){
            FenMain.btnLancer.setText(PackFunc.Var.bundle.getString("main.stop"));
            FenMain.btnLancer.setBackground(Color.green);
            if(funDb.paramLire("archives", "param").equals("1")){
                
                fun.connectHist();
                threadHisto th = new threadHisto();
                th.start();
            }
            
            PackFunc.Var.stopPing = false;
            tpp.start();
            lanceThread tpmr = new lanceThread();
            tpmr.start();
        }else{
            FenMain.btnLancer.setText(PackFunc.Var.bundle.getString("main.start"));
            FenMain.btnLancer.setBackground(Color.red);
            PackFunc.Var.stopPing = true;           
        }
    }//GEN-LAST:event_btnLancerActionPerformed

    private void menFichierSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFichierSaveActionPerformed
        FenSiteNouveau psn = new FenSiteNouveau();
        psn.show();
    }//GEN-LAST:event_menFichierSaveActionPerformed

    private void tabPrincMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPrincMouseClicked

        int buttonDown = evt.getButton();
        Integer row = tabPrinc.getSelectedRow();
        funcIp.rowNum = (tabPrinc.getModel().getValueAt(row, 0).toString());
        
        JPopupMenu menuIp = new JPopupMenu();
        JMenuItem ouvrir = new JMenuItem(PackFunc.Var.bundle.getString("main.menu.nav"));
        menuIp.add(ouvrir);
        JMenuItem suppr = new JMenuItem(PackFunc.Var.bundle.getString("main.menu.sup"));
        menuIp.add(suppr);
        JMenuItem hist = new JMenuItem(PackFunc.Var.bundle.getString("main.menu.hist"));
        if(fdb.paramLire("archives", "param").equals("1")){
            menuIp.add(hist);
        }
        
        ouvrir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler \"http://"+labIp.getText()+"\""); //NOI18N
                    } catch (IOException ex) {

                    }
            }
        });
        suppr.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fdb.ipSiteErase(funcIp.rowNum);
                    btnLancer.setText(PackFunc.Var.bundle.getString("main.start"));
                    btnLancer.setBackground(Color.red);
                    fdb.listeIp();
            }
        });
        hist.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    FenHist.ipHist = funcIp.rowNum;
                    FenHist fh = new FenHist();
                    fh.show();
            }
        });
        
        switch (buttonDown) {
        // Bouton GAUCHE enfoncé
            case MouseEvent.BUTTON1:
                funIp.nomHote();
                break;
        // Bouton du MILIEU enfoncé
            case MouseEvent.BUTTON2:
                
                break;
        // Bouton DROIT enfoncé
            case MouseEvent.BUTTON3:
                menuIp.show(tabPrinc, evt.getPoint().x, evt.getPoint().y);
                break;
            default:
                break;
        }






        
        
    }//GEN-LAST:event_tabPrincMouseClicked

    private void labModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labModifActionPerformed
        String nom = tfNom.getText();
        
        fdb.dbIpEcrit(funcIp.rowNum, nom, "nom");
        fdb.listeIp();
    }//GEN-LAST:event_labModifActionPerformed

    private void btnNavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavActionPerformed
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler \"http://"+labIp.getText()+"\""); //NOI18N
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_btnNavActionPerformed

    private void menFichierSitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFichierSitesActionPerformed
        FenGestionSite pgs = new FenGestionSite();
        pgs.show();
    }//GEN-LAST:event_menFichierSitesActionPerformed

    private void menFichierMajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFichierMajActionPerformed
        funcMiseAJour maj = new funcMiseAJour();
        maj.update();
    }//GEN-LAST:event_menFichierMajActionPerformed

    private void btnEffaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffaceActionPerformed
        fdb.ipSiteErase(funcIp.rowNum);
        btnLancer.setText(PackFunc.Var.bundle.getString("main.start"));
        btnLancer.setBackground(Color.red);
        fdb.listeIp();
    }//GEN-LAST:event_btnEffaceActionPerformed

    private void menAutreSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAutreSiteActionPerformed
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler \"https://prog.hemge.eu/\""); //NOI18N
        } catch (IOException ex) {
            fun.ecritLogs(ex, " - "+getClass().getName());
        }
    }//GEN-LAST:event_menAutreSiteActionPerformed

    private void menAutreAproposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAutreAproposActionPerformed
        FenAPropos pap = new FenAPropos();
        pap.show();
    }//GEN-LAST:event_menAutreAproposActionPerformed

    private void slidDelaisPingStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slidDelaisPingStateChanged
        // TODO add your handling code here:
        long delaisPause = Long.parseLong(String.valueOf(slidDelaisPing.getValue()));
        
        if(delaisPause >= 60 && delaisPause < 3600){
            delaisPause = delaisPause / 60;
            labDelaisPing.setText(Long.toString(delaisPause)+" min");
        }
        else if(delaisPause >= 3600){
            delaisPause = delaisPause / 3600;
            labDelaisPing.setText(Long.toString(delaisPause)+" h");
        }else if(delaisPause <= 0){
            PackThread.threadPop tp = new PackThread.threadPop(PackFunc.Var.bundle.getString("main.error.0"));
            tp.start();
            slidDelaisPing.setValue(5);
        }else{
            labDelaisPing.setText(Long.toString(delaisPause)+" s");
        }
        
        PackFunc.Var.delaisPause = (Integer) slidDelaisPing.getValue();
    }//GEN-LAST:event_slidDelaisPingStateChanged

    private void comboTriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTriItemStateChanged
        
    }//GEN-LAST:event_comboTriItemStateChanged

    private void comboTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTriActionPerformed
        if(comboTri.getSelectedItem().equals(PackFunc.Var.bundle.getString("main.trie"))){
            PackFunc.Var.tri = "IP";
        }else{
            PackFunc.Var.tri = (String) comboTri.getSelectedItem();
        }
        fdb.listeIp();
        PackFunc.Var.stopPing = true;
        btnLancer.setText(PackFunc.Var.bundle.getString("main.start"));
        btnLancer.setBackground(Color.red);
        System.out.println(PackFunc.Var.tri);
    }//GEN-LAST:event_comboTriActionPerformed

    private void tabPrincFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabPrincFocusLost
    
    

// TODO add your handling code here:
    }//GEN-LAST:event_tabPrincFocusLost

    private void tabPrincKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabPrincKeyPressed
        if (KeyEvent.VK_ENTER==evt.getKeyCode()){
            String ob = tabPrinc.getValueAt(tabPrinc.getSelectedRow(),1).toString();
            String ob1 = tabPrinc.getValueAt(tabPrinc.getSelectedRow(),0).toString();
            fdb.dbIpEcrit(ob1, ob, "nom");
            fdb.listeIp();
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_tabPrincKeyPressed

    private void menAutreChangelogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAutreChangelogActionPerformed
        FenChange fenc = new FenChange();
        fenc.show();
    }//GEN-LAST:event_menAutreChangelogActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        PackFunc.funcRepareSql prsql = new PackFunc.funcRepareSql();
        prsql.dbRepare("param");
        prsql.dbRepare("options");
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void menTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menTestActionPerformed
        PackFunc.FuncRac rac = new PackFunc.FuncRac();
        try {
            rac.creerRacDemarrer();
        } catch (IOException ex) {
            Logger.getLogger(FenMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FenMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_menTestActionPerformed

    private void menFichierExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFichierExportActionPerformed
        PackFunc.funcExportXls export = new PackFunc.funcExportXls();
        export.main();
    }//GEN-LAST:event_menFichierExportActionPerformed

    private void menFichierImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFichierImportActionPerformed
        PackFunc.funcImportXls Import = new PackFunc.funcImportXls();
        Import.main();
    }//GEN-LAST:event_menFichierImportActionPerformed

    private void menParamOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menParamOptionActionPerformed
        FenOption fenO = new FenOption();
        fenO.show();
    }//GEN-LAST:event_menParamOptionActionPerformed

    private void spinNbrHsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinNbrHsStateChanged
        Var.nbrHs = (Integer) spinNbrHs.getValue();
        System.out.println(Var.nbrHs);
    }//GEN-LAST:event_spinNbrHsStateChanged

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
            java.util.logging.Logger.getLogger(FenMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FenMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnEfface;
    public static javax.swing.JToggleButton btnLancer;
    private javax.swing.JButton btnNav;
    private javax.swing.JButton btnValid;
    private javax.swing.JComboBox<String> cbTout;
    private javax.swing.JComboBox<String> comboTri;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JLabel labAjout;
    public static javax.swing.JLabel labDelaisPing;
    public static javax.swing.JLabel labImg;
    public static javax.swing.JLabel labIp;
    public static javax.swing.JLabel labMaj;
    private javax.swing.JButton labModif;
    private javax.swing.JLabel labNbrHs;
    private javax.swing.JLabel labPingDelais;
    public static javax.swing.JLabel labThreadFerme;
    public static javax.swing.JLabel labThreadOuvert;
    private javax.swing.JLabel labVersion;
    public static javax.swing.JComboBox<String> listeSite;
    private javax.swing.JMenu menAutre;
    private javax.swing.JMenuItem menAutreApropos;
    private javax.swing.JMenuItem menAutreChangelog;
    private javax.swing.JMenuItem menAutreSite;
    private javax.swing.JMenu menFichier;
    private javax.swing.JMenuItem menFichierExport;
    private javax.swing.JMenuItem menFichierImport;
    private javax.swing.JMenuItem menFichierMaj;
    private javax.swing.JMenuItem menFichierQuitter;
    private javax.swing.JMenuItem menFichierSave;
    private javax.swing.JMenuItem menFichierSites;
    private javax.swing.JMenu menParam;
    private javax.swing.JMenuItem menParamOption;
    private javax.swing.JMenuItem menParamParam;
    private javax.swing.JMenuItem menTest;
    public static javax.swing.JProgressBar progerAjout;
    private javax.swing.JSpinner slidDelaisPing;
    private javax.swing.JSpinner spinNbrHs;
    private javax.swing.JSpinner spinPlage;
    public static javax.swing.JTable tabPrinc;
    public static javax.swing.JTextField tfNom;
    public static javax.swing.JTextField tfPremIp;
    // End of variables declaration//GEN-END:variables
}
