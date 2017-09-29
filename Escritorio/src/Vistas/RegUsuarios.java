/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Core.ControladorUsuario;
import Modelo.Usuario;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RegUsuarios extends javax.swing.JPanel {
    ControladorUsuario cp;
    ResultSet r;
    DefaultTableModel miTabla=new DefaultTableModel();
    int fila=0;
    int id;
    Image foto;
    JButton boton;
    
    /**
     * Creates new form RegProductos
     */
    public RegUsuarios() throws Exception {
        initComponents();
        
        cp=new ControladorUsuario();
        limpiar();
        inicializarTabla();
        
        inicializarColeccion2();
       mostrarDatos();
        
    }
    
     ActionListener num=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                numer(ae);
            } catch (Exception ex) {
                Logger.getLogger(RegProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    };
      
       public void numer(ActionEvent e) throws Exception{
        JButton fox=(JButton)e.getSource();
        r=cp.numeros(Integer.parseInt(fox.getText()));
        this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
        while(r.next()){
            miTabla=(DefaultTableModel)this.jTable1.getModel();
            Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4)}; 
            miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
        }
        
    }

public void inicializarColeccion2(){
        this.jPanel3.setLayout(new FlowLayout());
        for(char i='0';i<='9';i++){
            boton=new JButton();
            boton.setText(String.valueOf(i));
            boton.setVisible(true);
            boton.addActionListener(num);
            this.jPanel3.add(this.boton);
        }
    }
    public void inicializarTabla() throws SQLException, Exception{
        miTabla=new DefaultTableModel();
        miTabla.addColumn("ID");
        miTabla.addColumn("Usuario");
        miTabla.addColumn("Contraseña");        
        miTabla.addColumn("Rol");
   
        this.jTable1.setModel(miTabla);
       
        
    }
    public void mostrarDatos() throws SQLException, Exception{
         r=cp.UsuarioTodo();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4)}; 
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
  
   
   
   
    public void agregarRegistroUsuario() throws Exception{
        try{
            Usuario pro=new Usuario();
            pro.setNombre(this.jTextField1.getText());
            pro.setContrasennia(this.jPasswordField1.getText());
            pro.setRol(this.jTextField3.getText());
           
            cp.agregarUsuario(pro);
            this.jLabel13.setText("Registro agregado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch(Exception e){
            System.out.println(e);
        }
      
        
    }
    public void actualizar() throws Exception{
        try{
            Usuario pro=new Usuario();
           pro.setNombre(this.jTextField1.getText());
            pro.setContrasennia(this.jPasswordField1.getText());
            pro.setRol(this.jTextField3.getText());
            
            cp.actualizarUsuario(pro, id);
            this.jLabel13.setText("Registro modificado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch (Exception e){
                        System.out.println(e);
        }
    }  
    public void Eliminar(){
        try{
            Usuario pro =new  Usuario();
         pro.setNombre(this.jTextField1.getText());
            pro.setContrasennia(this.jPasswordField1.getText());
            pro.setRol(this.jTextField3.getText());
          
            cp.delete(id);
            this.jLabel13.setText("Registro eliminado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch(Exception e){
             System.out.println(e);
        }
    }
    
    public void limpiar(){
        this.jTextField1.setText(null);
        this.jPasswordField1.setText(null);
        this.jTextField3.setText(null);
      
        
        this.jLabel13.setText(null);
        this.jTextField1.requestFocus();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnactivo = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jTextField1.setText("jTextField1");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 630, -1));

        jLabel3.setText("Contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jLabel4.setText("Rol:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, 20));

        jTextField3.setText("jTextField3");
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 630, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 130, 90, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Añadir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 130, 90, -1));

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, 90, -1));

        btnactivo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnactivo.setText("Eliminar");
        btnactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactivoActionPerformed(evt);
            }
        });
        jPanel1.add(btnactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 130, 90, -1));

        jLabel13.setForeground(new java.awt.Color(255, 0, 102));
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 220, 20));

        jPasswordField1.setText("jPasswordField1");
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 630, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Módulo  Usuarios");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MTVx3.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Por id:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jLabel5)
                            .addGap(246, 246, 246)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(65, 65, 65)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            agregarRegistroUsuario();
            limpiar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 try{
     actualizar();

 } catch(Exception ex){
            Logger.getLogger(RegUsuarios.class.getName()).log(Level.SEVERE, null, ex);
 }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            fila=this.jTable1.getSelectedRow();
            id=Integer.parseInt(this.jTable1.getValueAt(fila,0).toString());
            traerRegistro();
        } catch (Exception ex) {
            Logger.getLogger(RegUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactivoActionPerformed
Eliminar(); 
    }//GEN-LAST:event_btnactivoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void traerRegistro() throws Exception{
       if(id>0){
           r=cp.UsuariosUno(id);
           r.first();
           this.jTextField1.setText(r.getString(2));
           this.jPasswordField1.setText(r.getString(3));
           this.jTextField3.setText(r.getString(4));
          
                 
       }
        
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
