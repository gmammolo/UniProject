/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.opendfa;

import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.java.com.opendfa.GUI.DfaFilter;
import main.java.com.opendfa.GUI.GestMoveGump;
import main.java.com.opendfa.GUI.GestStateGump;

/**
 *
 * @author terasud
 */
public class OpenDFAGUI extends javax.swing.JFrame implements Observer {

    private OpenDFA dfa;
    private String name;
    private File saveFile;

    /**
     * Creates new form OpenDFAGUI
     *
     * @param dfa
     */
    public OpenDFAGUI(OpenDFA dfa) {
        setTitle("OpenDFA");
        this.dfa = dfa;
        name = "NuovoDfa";
        saveFile = null;
        this.dfa.addObserver(this);
        initComponents();
        reload();

        jSaveChooser.setAcceptAllFileFilterUsed(false);
        jSaveChooser.addChoosableFileFilter(new DfaFilter());
        jLoadChooser.setAcceptAllFileFilterUsed(false);
        jLoadChooser.addChoosableFileFilter(new DfaFilter());

        jExportJavaChooser.setAcceptAllFileFilterUsed(false);
        jExportJavaChooser.addChoosableFileFilter(new FileNameExtensionFilter("Java File", "java"));

        jExportDotChooser.setAcceptAllFileFilterUsed(false);
        jExportDotChooser.addChoosableFileFilter(new FileNameExtensionFilter("dot file", "dot"));

        jExportPngChooser.setAcceptAllFileFilterUsed(false);
        jExportPngChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSaveChooser = new javax.swing.JFileChooser();
        jLoadChooser = new javax.swing.JFileChooser();
        jExportJavaChooser = new javax.swing.JFileChooser();
        jExportDotChooser = new javax.swing.JFileChooser();
        jExportPngChooser = new javax.swing.JFileChooser();
        Edge_Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        transitionList = new javax.swing.JList<>();
        FinalState_Panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        finalStateList = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        Content_Panel = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        toJavaText = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        toDotText = new javax.swing.JTextArea();
        graphLabel = new javax.swing.JLabel();
        AddMoveButton = new javax.swing.JButton();
        AddStateButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exportMenu = new javax.swing.JMenu();
        exportJava = new javax.swing.JMenuItem();
        exportDot = new javax.swing.JMenuItem();
        exportPng = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        addMoveMenuItem = new javax.swing.JMenuItem();
        addFinalStateMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        jSaveChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveChooserActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Edge_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Edge_Panel.setAutoscrolls(true);
        Edge_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Transizioni");

        transitionList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        transitionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transitionListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(transitionList);

        javax.swing.GroupLayout Edge_PanelLayout = new javax.swing.GroupLayout(Edge_Panel);
        Edge_Panel.setLayout(Edge_PanelLayout);
        Edge_PanelLayout.setHorizontalGroup(
            Edge_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Edge_PanelLayout.setVerticalGroup(
            Edge_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edge_PanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
        );

        FinalState_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FinalState_Panel.setAutoscrolls(true);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Stati Finali");

        finalStateList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "q0" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        finalStateList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        finalStateList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                finalStateListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(finalStateList);

        javax.swing.GroupLayout FinalState_PanelLayout = new javax.swing.GroupLayout(FinalState_Panel);
        FinalState_Panel.setLayout(FinalState_PanelLayout);
        FinalState_PanelLayout.setHorizontalGroup(
            FinalState_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinalState_PanelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 81, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        FinalState_PanelLayout.setVerticalGroup(
            FinalState_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinalState_PanelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Content_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        toJavaText.setEditable(false);
        toJavaText.setColumns(20);
        toJavaText.setRows(5);
        jScrollPane1.setViewportView(toJavaText);

        Content_Panel.addTab("toJava", jScrollPane1);

        toDotText.setEditable(false);
        toDotText.setColumns(20);
        toDotText.setRows(5);
        jScrollPane2.setViewportView(toDotText);

        Content_Panel.addTab("toDOT", jScrollPane2);

        graphLabel.setMaximumSize(new java.awt.Dimension(100, 100));
        graphLabel.setMinimumSize(new java.awt.Dimension(50, 50));
        Content_Panel.addTab("graph", graphLabel);

        AddMoveButton.setText("Add Move");
        AddMoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMoveButtonActionPerformed(evt);
            }
        });

        AddStateButton.setText("Add State");
        AddStateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddStateButton)
                .addGap(18, 18, 18)
                .addComponent(AddMoveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(Content_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Content_Panel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddMoveButton)
                    .addComponent(AddStateButton)))
        );

        menuBar.setName("OpenDFA"); // NOI18N

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exportMenu.setText("Export As...");

        exportJava.setText("Java Code");
        exportJava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportJavaActionPerformed(evt);
            }
        });
        exportMenu.add(exportJava);

        exportDot.setText("Dot File");
        exportDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportDotActionPerformed(evt);
            }
        });
        exportMenu.add(exportDot);

        exportPng.setText("PNG Image");
        exportPng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPngActionPerformed(evt);
            }
        });
        exportMenu.add(exportPng);

        fileMenu.add(exportMenu);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        addMoveMenuItem.setMnemonic('t');
        addMoveMenuItem.setText("Add Move");
        addMoveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMoveMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(addMoveMenuItem);

        addFinalStateMenuItem.setMnemonic('y');
        addFinalStateMenuItem.setText("Add Final State");
        editMenu.add(addFinalStateMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FinalState_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Edge_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Edge_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FinalState_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void addMoveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMoveMenuItemActionPerformed
        this.addMove();
    }//GEN-LAST:event_addMoveMenuItemActionPerformed

    private void AddMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMoveButtonActionPerformed
        // TODO add your handling code here:
        addMove();
    }//GEN-LAST:event_AddMoveButtonActionPerformed

    private void AddStateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStateButtonActionPerformed
        GestStateGump.generateNewState(dfa, dfa.numState());
    }//GEN-LAST:event_AddStateButtonActionPerformed

    private void finalStateListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finalStateListMouseClicked
        int index = finalStateList.getSelectedIndex();
        GestStateGump.generateNewState(dfa, index);
    }//GEN-LAST:event_finalStateListMouseClicked

    private void transitionListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transitionListMouseClicked
        int index = transitionList.getSelectedIndex();
        GestMoveGump.generateAddMoveGump(dfa, index);
    }//GEN-LAST:event_transitionListMouseClicked

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        if (saveFile != null) { //TODO: check if file exist and overwrite
            _saveFile(saveFile);
        } else {
            saveAsMenuItemActionPerformed(evt);
        }

    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        int returnVal = jSaveChooser.showDialog(this, "Save as..");
        if (returnVal == jSaveChooser.APPROVE_OPTION) {
            File file = jSaveChooser.getSelectedFile();
            _saveFile(file);
            saveFile = file;
        }

    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void jSaveChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveChooserActionPerformed

    }//GEN-LAST:event_jSaveChooserActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        int returnVal = jLoadChooser.showDialog(this, "load file");
        if (returnVal == jLoadChooser.APPROVE_OPTION) {
            File file = jLoadChooser.getSelectedFile();
            _loadFile(file);
            saveFile = file;
        }

    }//GEN-LAST:event_openMenuItemActionPerformed

    private void exportJavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportJavaActionPerformed
        int returnVal = jExportJavaChooser.showDialog(this, "Save as..");
        if (returnVal == jExportJavaChooser.APPROVE_OPTION) {
            File file = jExportJavaChooser.getSelectedFile();
            _exportFile(file, dfa.toJava(name));
        }
    }//GEN-LAST:event_exportJavaActionPerformed

    private void exportDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportDotActionPerformed
        int returnVal = jExportDotChooser.showDialog(this, "Save as..");
        if (returnVal == jExportDotChooser.APPROVE_OPTION) {
            File file = jExportDotChooser.getSelectedFile();
            _exportFile(file, dfa.toDot(name));
        }
    }//GEN-LAST:event_exportDotActionPerformed

    private void exportPngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPngActionPerformed

        int returnVal = jExportPngChooser.showDialog(this, "Save as..");
        if (returnVal == jExportPngChooser.APPROVE_OPTION) {
            File file = jExportPngChooser.getSelectedFile();
            dfa.ToPng(name, file);
        }
    }//GEN-LAST:event_exportPngActionPerformed

    private void addMove() {
        GestMoveGump.generateAddMoveGump(dfa);

    }

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
            java.util.logging.Logger.getLogger(OpenDFAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpenDFAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpenDFAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpenDFAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OpenDFAGUI(new OpenDFA(1)).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMoveButton;
    private javax.swing.JButton AddStateButton;
    private javax.swing.JTabbedPane Content_Panel;
    private javax.swing.JPanel Edge_Panel;
    private javax.swing.JPanel FinalState_Panel;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem addFinalStateMenuItem;
    private javax.swing.JMenuItem addMoveMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem exportDot;
    private javax.swing.JMenuItem exportJava;
    private javax.swing.JMenu exportMenu;
    private javax.swing.JMenuItem exportPng;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JList<String> finalStateList;
    private javax.swing.JLabel graphLabel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JFileChooser jExportDotChooser;
    private javax.swing.JFileChooser jExportJavaChooser;
    private javax.swing.JFileChooser jExportPngChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JFileChooser jLoadChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFileChooser jSaveChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextArea toDotText;
    private javax.swing.JTextArea toJavaText;
    private javax.swing.JList<String> transitionList;
    // End of variables declaration//GEN-END:variables

    public void reloadTransitionPanel() {
        transitionList.removeAll();
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<String> transictionStryngify = dfa.getEdgesStringify();
        for (String s : transictionStryngify) {
            listModel.addElement(s);
        }
        transitionList.setModel(listModel);
        transitionList.revalidate();
        transitionList.repaint();
    }

    private void reloadFinalState() {
        finalStateList.removeAll();
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < dfa.numState(); i++) {
            listModel.addElement("q" + i + ((dfa.isFinalState(i)) ? " *" : ""));
        }
        finalStateList.setModel(listModel);
        finalStateList.revalidate();
        finalStateList.repaint();
    }

    @Override
    public void update(Observable o, Object o1) {
        reload();
    }

    private void reloadContent() {
        toJavaText.setText(dfa.toJava(name));
        toDotText.setText(dfa.toDot(name));
        //dfa.toPngTemp(name);
        //graph.setIcon(new javax.swing.ImageIcon("/home/terasud/NetBeansProjects/OpenDFA/Temp/NuovoDfa.png")); // NOI18N
        ImageIcon icon = new javax.swing.ImageIcon(dfa.toPngTemp(name));
        icon.getImage().flush();
        graphLabel.setIcon(icon); // NOI18N
        graphLabel.revalidate();
        graphLabel.repaint();
        Content_Panel.revalidate();
        Content_Panel.repaint();
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    private ImageIcon createImageIcon(String path, String description) {
        BufferedImage img = null;
        try {
            File f = new File(path);
            img = ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(OpenDFAGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    private void _saveFile(File file) {
        dfa.saveFile(file);
    }

    private void _loadFile(File file) {
        dfa = OpenDFA.loadFile(file);
        dfa.addObserver(this);
        reload();
    }

    public void reload() {
        reloadTransitionPanel();
        reloadFinalState();
        reloadContent();
    }

    private void _exportFile(File file, String text) {
        dfa.writeFile(file, text);
    }

}
