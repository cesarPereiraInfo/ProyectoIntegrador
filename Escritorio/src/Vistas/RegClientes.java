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
import Core.ControladorCliente;
import Modelo.Cliente;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RegClientes extends javax.swing.JPanel {
    ControladorCliente cp;
    ResultSet r;
    DefaultTableModel miTabla=new DefaultTableModel();
    int fila=0;
    int id;
    JButton boton;
    DefaultComboBoxModel miCombo, miCombo2;
    
    /**
     * Creates new form RegProductos
     */
    public RegClientes() throws Exception {
        initComponents();
        
        cp=new ControladorCliente();
        limpiar();
        inicializarTabla();
        
         inicializarCombo(); 
         llenarCombo();
        llenarCombo2();
        mostrarDatos();
    }
    public void inicializarCombo() {
        miCombo = new DefaultComboBoxModel();
        this.jComboBox1.setModel(miCombo);
        miCombo = (DefaultComboBoxModel) this.jComboBox1.getModel();
        miCombo.addElement("Persona");

        miCombo2 = new DefaultComboBoxModel();
        this.jComboBox2.setModel(miCombo2);
        miCombo2 = (DefaultComboBoxModel) this.jComboBox2.getModel();
        miCombo2.addElement("Sucursales");
    }
     public void llenarCombo() throws Exception {
        miCombo.removeAllElements();
        r = cp.consultarPersona();
        r.beforeFirst();
        while (r.next()) {
            String cadena = r.getString(1) + "-" + r.getString(2)+ "-" +r.getString(3);
            miCombo.addElement(cadena);
        }
    }

    public void llenarCombo2() throws Exception {
        miCombo2.removeAllElements();
        r = cp.consultarSucursales();
        r.beforeFirst();
        while (r.next()) {
            String cadena = r.getString(1) + "-" + r.getString(2);
            miCombo2.addElement(cadena);

        }
    }
 
     public void letra(ActionEvent e) throws Exception{
        JButton fox=(JButton)e.getSource();
        
        r=cp.datosColeccion(fox.getText());
        this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
        while(r.next()){
            miTabla=(DefaultTableModel)this.jTable1.getModel();
            Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6)}; 
            miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
        }
        
    }
     public void numer(ActionEvent e) throws Exception{
        JButton fox=(JButton)e.getSource();
        r=cp.numeros(Integer.parseInt(fox.getText()));
        this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
        while(r.next()){
            miTabla=(DefaultTableModel)this.jTable1.getModel();
            Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6)}; 
            miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
        }
        
    }

    public void inicializarTabla() throws SQLException, Exception{
        miTabla=new DefaultTableModel();
        miTabla.addColumn("ID");
        miTabla.addColumn("Nombre");
        miTabla.addColumn("Ape. Paterno");        
        miTabla.addColumn("Ape. Materno");
        miTabla.addColumn("Correo Electronico");
        miTabla.addColumn("Fecha Registro");
      
        
        this.jTable1.setModel(miTabla);
       
        
    }
   public void mostrarDatos() throws SQLException, Exception{
         r=cp.ClienteTodo();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
   
   public void mostrarDatosEliminados() throws SQLException, Exception{
       limpiar();  
       r=cp.todosLosClientesEliminados();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
   
   
    public void agregarRegistroCliente() throws Exception{
        try{
            Cliente pro=new Cliente();
            pro.setCorreo(this.jTextField1.getText());
            pro.setFechaRegistro(this.jTextField2.getText());          
            String x = miCombo.getSelectedItem().toString();
            String[] d = x.split("-");
            String xx = miCombo2.getSelectedItem().toString();
            String[] datos = xx.split("-");
             int idp = Integer.parseInt(d[0]);
             int idu = Integer.parseInt(datos[0]);
             pro.setPersonas(idp);
             pro.setSucursales(idu);
            cp.agregarCliente(pro);
            this.jLabel13.setText("Registro agregado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch(Exception e){
            System.out.println(e);
        }
      
        
    }
    public void actualizar() throws Exception{
        try{
           Cliente pro=new Cliente();
            pro.setCorreo(this.jTextField1.getText());
            pro.setFechaRegistro(this.jTextField2.getText());          
              String x = miCombo.getSelectedItem().toString();
            String[] d = x.split("-");
            String xx = miCombo2.getSelectedItem().toString();
            String[] datos = xx.split("-");
             int idp = Integer.parseInt(d[0]);
             int idu = Integer.parseInt(datos[0]);
             pro.setPersonas(idp);
             pro.setSucursales(idu);
            cp.actualizarCliente(pro, id);
            this.jLabel13.setText("Registro modificado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch (Exception e){
                        System.out.println(e);
        }
    }  
    public void Eliminar(){
        try{
           Cliente pro=new Cliente();
            pro.setCorreo(this.jTextField1.getText());
            pro.setFechaRegistro(this.jTextField2.getText());          
              String x = miCombo.getSelectedItem().toString();
            String[] d = x.split("-");
            String xx = miCombo2.getSelectedItem().toString();
            String[] datos = xx.split("-");
             int idp = Integer.parseInt(d[0]);
             int idu = Integer.parseInt(datos[0]);
             pro.setPersonas(idp);
             pro.setSucursales(idu);

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
        this.jTextField2.setText(null);
        
       
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
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnactivo = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Correo Electronico:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jTextField1.setText("jTextField1");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 30, 470, -1));

        jLabel3.setText("Fecha Registro:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, -1, -1));

        jLabel4.setText("Persona:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, 20));

        jLabel5.setText("Sucursal:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, -1, -1));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Clientes Activos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 180, -1, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Añadir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        btnactivo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnactivo.setText("Desactivar");
        btnactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactivoActionPerformed(evt);
            }
        });
        jPanel1.add(btnactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 0, 102));
        jLabel13.setText("jLabel13");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 170, 100, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 470, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 530, -1));

        try {
            jTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 520, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Módulo  Clientes");

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MTVx3.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(395, 395, 395))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1328, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            agregarRegistroCliente();
            limpiar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 try{
     actualizar();

 } catch(Exception ex){
            Logger.getLogger(RegClientes.class.getName()).log(Level.SEVERE, null, ex);
 }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            fila=this.jTable1.getSelectedRow();
            id=Integer.parseInt(this.jTable1.getValueAt(fila,0).toString());
            traerRegistro();
        } catch (Exception ex) {
            Logger.getLogger(RegClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactivoActionPerformed
    status();
                
    }//GEN-LAST:event_btnactivoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
           try{
                this.btnactivo.setText("Desactivar");
                inicializarTabla();
                mostrarDatos();
            }catch(Exception ex){
                
            } 
        }
        else{
             try{
                this.btnactivo.setText("Activar");
                inicializarTabla();
                mostrarDatosEliminados();
            }catch(Exception ex){
                
            } 
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    public void traerRegistro() throws Exception{
       if(id>0){
           r=cp.ClienteUno(id);
           r.first();
           this.jTextField1.setText(r.getString(2));
           this.jTextField2.setText(r.getString(3));
           this.jComboBox1.setModel(miCombo);
           this.jComboBox2.setModel(miCombo2);
        
          
       }
        
    }
    
    public void status(){
        if(btnactivo.getText().equals("Desactivar")){
            try{
                cp.delete(id);
                inicializarTabla();
                mostrarDatos();
                limpiar();
            }catch(Exception ex){
                
            }
        }
        if(btnactivo.getText().equals("Activar")){
            try{
                cp.activarCliente(id);
                inicializarTabla();
                mostrarDatosEliminados();
                limpiar();
            }catch(Exception ex){
                
            }
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JFormattedTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
