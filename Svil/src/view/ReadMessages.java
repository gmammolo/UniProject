/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.GuiController;
import model.Messaggio;
import model.Utente;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import model.ProxyMessage;

/**
 *
 * @author Giuseppe
 */
public class ReadMessages extends javax.swing.JFrame {

    
    public Utente reader;
    public ChooseFrame parent;
    /**
     * Creates new form ReadMessages
     */
    public ReadMessages(Utente reader, ChooseFrame parent) {
        this.reader=reader;
        this.parent=parent;
        initComponents();
        CreaLista();
        
    }

    private void CreaLista()
    {
        ArrayList<ProxyMessage> list=Messaggio.GetRequestMessage(reader,10);
        PanelNuovi.setLayout(new GridLayout(list.size()+1 , 4 ));
        PanelNuovi.add(new JLabel("Author"));
        PanelNuovi.add(new JLabel("Metodo"));
        PanelNuovi.add(new JLabel("Accetto"));
        PanelNuovi.add(new JLabel("Rifiuto"));        
        for(Messaggio mess : list)
        {
            JLabel label=new JLabel(mess.Sender);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelNuovi.add(label);
            
            label=new JLabel(mess.CryptMethod.name());
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelNuovi.add(label);
            
            AcceptedButton accbut=new AcceptedButton(mess,this);
            accbut.addActionListener(accbut);
            accbut.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelNuovi.add(accbut);
            
            RefuseButton but=new RefuseButton(mess,this);
            but.addActionListener(but);
            but.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelNuovi.add(but);
        }       
                
        list=Messaggio.GetProposte(reader,10);
        PanelAccettati.setLayout(new GridLayout(list.size()+1 , 4 ));
        PanelAccettati.add(new JLabel("Read"));
        PanelAccettati.add(new JLabel("Testo"));
        PanelAccettati.add(new JLabel("Author"));
        PanelAccettati.add(new JLabel("Letto"));
        
        for(Messaggio mess : list)
        {
            ReadButton but=new ReadButton(mess,this);
            but.addActionListener(but);
            but.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelAccettati.add(but);
            
            JLabel label=new JLabel(LimitedMessage(mess.Testo));
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelAccettati.add(label);
            
            label=new JLabel(mess.Sender);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelAccettati.add(label);
            
            label=new JLabel(mess.IsRead() ? "Letto" : "Non Letto");
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            PanelAccettati.add(label);
        }
        PanelAccettati.setBorder(BorderFactory.createLineBorder(Color.black));
        this.pack();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelAccettati = new javax.swing.JPanel();
        Annulla = new javax.swing.JButton();
        PanelNuovi = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout PanelAccettatiLayout = new javax.swing.GroupLayout(PanelAccettati);
        PanelAccettati.setLayout(PanelAccettatiLayout);
        PanelAccettatiLayout.setHorizontalGroup(
            PanelAccettatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelAccettatiLayout.setVerticalGroup(
            PanelAccettatiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        Annulla.setText("Annulla");
        Annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnullaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelNuoviLayout = new javax.swing.GroupLayout(PanelNuovi);
        PanelNuovi.setLayout(PanelNuoviLayout);
        PanelNuoviLayout.setHorizontalGroup(
            PanelNuoviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelNuoviLayout.setVerticalGroup(
            PanelNuoviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(Annulla)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelAccettati, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelNuovi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelNuovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(PanelAccettati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(Annulla)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnullaActionPerformed
        // TODO add your handling code here:
        GuiController.UndoFrame(parent, this);
//        parent.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_AnnullaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annulla;
    private javax.swing.JPanel PanelAccettati;
    private javax.swing.JPanel PanelNuovi;
    // End of variables declaration//GEN-END:variables


    public void Refresh()
    {
//        this.dispose();
//        new ReadMessages(reader, parent).setVisible(true);
        PanelNuovi.removeAll();
        PanelAccettati.removeAll();
        CreaLista();
    }
    
    
        protected static String LimitedMessage(String Messaggio)
    {
        return LimitedMessage(Messaggio, 30);
    }
    
    protected static String LimitedMessage(String Messaggio, int Limit)
    {
        int lim = (Limit < Messaggio.length()) ? Limit :  Messaggio.length();
        String plus= (lim < Messaggio.length()) ? "..." : "";
        return Messaggio.substring(0,lim)+ plus;
    }
    
    
     private class ReadButton extends JButton implements ActionListener
    {
        Messaggio Mess;
        ReadMessages This;
        public ReadButton(Messaggio messaggio, ReadMessages This)
        {
            super("Read");
            this.Mess=messaggio;
            this.This=This;
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //read Message
            GuiController.GoReadMessage(reader,Mess,This,parent);
            Mess.SetRead();
            Refresh();

            
        }
    }
    
     
    private class AcceptedButton extends JButton implements ActionListener
    {
        Messaggio Mess;
        ReadMessages This;
        public AcceptedButton(Messaggio messaggio, ReadMessages This)
        {
            super("Accetta");
            this.Mess=messaggio;
            this.This=This;
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //read Message
            Mess.SetAccepted();
            Refresh();

            
        }
    }
    
    /*bottone personalizzato per settare i messaggi come letti */
    private class RefuseButton extends JButton implements ActionListener
    {
        Messaggio Mess;
        ReadMessages This;
        public RefuseButton(Messaggio messaggio, ReadMessages This)
        {
            super("Rifiuta");
            this.Mess=messaggio;
            this.This=This;
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //read Message
            Mess.SetRefuse();
            Refresh();

            
        }
    }
}