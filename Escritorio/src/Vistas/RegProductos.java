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
import Core.ControladorProducto;
import Modelo.producto;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class RegProductos extends javax.swing.JPanel {
    ControladorProducto cp;
    ResultSet r;
    DefaultTableModel miTabla=new DefaultTableModel();
    int fila=0;
    int id;
     JButton boton;
     File fichero = null;
    private JPanel contentPane;
    
    /**
     * Creates new form RegProductos
     */

      
    public RegProductos() throws Exception {
        initComponents();
        
        cp=new ControladorProducto();
        limpiar();
        inicializarTabla();
   
        mostrarDatos();
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
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),
                            r.getString(9),r.getString(10),r.getString(11)}; 
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
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),
                            r.getString(9),r.getString(10),r.getString(11)}; 
            miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
        }
        
    }
    public void inicializarTabla() throws SQLException, Exception{
        miTabla=new DefaultTableModel();
        miTabla.addColumn("ID");
        miTabla.addColumn("Nombre general");
        miTabla.addColumn("Nombre Genérico");        
        miTabla.addColumn("Forma");
        miTabla.addColumn("Concentración");
        miTabla.addColumn("Presentación");
        miTabla.addColumn("Precio");
        miTabla.addColumn("Existencias");
        miTabla.addColumn("P. Indicaciones");
        miTabla.addColumn("Uni. Medida");
        miTabla.addColumn("Contraindicaciones");
        
        this.jTable1.setModel(miTabla);
       
        
    }
 
   public void mostrarDatos() throws SQLException, Exception{
         r=cp.ProductosTodo();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),
                            r.getString(9),r.getString(10),r.getString(11)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
   
   public void mostrarDatosEliminados() throws SQLException, Exception{
       limpiar();  
       r=cp.todosLosProductosEliminados();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),
                            r.getString(9),r.getString(10),r.getString(11)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
   
   
    public void agregarRegistroProducto() throws Exception{
        try{
            producto pro=new producto();
            pro.setNombreGeneral(this.jTextField1.getText());
            pro.setNombreGenerico(this.jTextField2.getText());
            pro.setFormaFarmaceutica(this.jTextField3.getText());
            pro.setConcentracion(this.jTextField4.getText());
            pro.setPresentacion(this.jTextField5.getText());
            pro.setPrecioUnitario(Double.parseDouble(this.jTextField6.getText()));
            
            pro.setUnidadEnvases(Integer.parseInt(this.jTextField7.getText()));
            pro.setPrincipalIndicacion(this.jTextField8.getText());
            pro.setContraindicaciones(this.jTextField9.getText());
            pro.setUnidadMedida(this.jTextField10.getText());
            pro.setFoto(this.fotot.getText());
            pro.setRutaFoto(this.rt.getText());
            cp.agregarProducto(pro);
            this.jLabel13.setText("Registro agregado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch(Exception e){
            System.out.println(e);
        }
      
        
    }
    public void actualizar() throws Exception{
        try{
            producto pro=new producto();
            pro.setNombreGeneral(this.jTextField1.getText());
            pro.setNombreGenerico(this.jTextField2.getText());
            pro.setFormaFarmaceutica(this.jTextField3.getText());
            pro.setConcentracion(this.jTextField4.getText());
            pro.setPresentacion(this.jTextField5.getText());
            pro.setPrecioUnitario(Double.parseDouble(this.jTextField6.getText()));
            
            pro.setUnidadEnvases(Integer.parseInt(this.jTextField7.getText()));
            pro.setPrincipalIndicacion(this.jTextField8.getText());
            pro.setContraindicaciones(this.jTextField9.getText());
            pro.setUnidadMedida(this.jTextField10.getText());
pro.setFoto(this.fotot.getText());
            pro.setRutaFoto(this.rt.getText());
            cp.actualizarProducto(pro, id);
            this.jLabel13.setText("Registro modificado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch (Exception e){
                        System.out.println(e);
        }
    }  
    public void Eliminar(){
        try{
            producto pro =new  producto();
            pro.setNombreGeneral(this.jTextField1.getText());
            pro.setNombreGenerico(this.jTextField2.getText());
            pro.setFormaFarmaceutica(this.jTextField3.getText());
            pro.setConcentracion(this.jTextField4.getText());
            pro.setPresentacion(this.jTextField5.getText());
            pro.setPrecioUnitario(Double.parseDouble(this.jTextField6.getText()));
           
            pro.setUnidadEnvases(Integer.parseInt(this.jTextField7.getText()));
            pro.setPrincipalIndicacion(this.jTextField8.getText());
            pro.setContraindicaciones(this.jTextField9.getText());
            pro.setUnidadMedida(this.jTextField10.getText());
            pro.setFoto(this.fotot.getText());
            pro.setRutaFoto(this.rt.getText());
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
        this.jTextField3.setText(null);
        this.jTextField4.setText(null);
        this.jTextField5.setText(null);
        this.jTextField6.setText(null);
        this.jTextField7.setText(null);
        this.jTextField8.setText(null);
        this.jTextField9.setText(null);
        this.jTextField10.setText(null);
       this.fotot.setText(null);
       this.rt.setText(null);
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
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnactivo = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        fotot = new javax.swing.JTextField();
        rt = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre General:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jTextField1.setText("jTextField1");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 30, 470, -1));

        jLabel3.setText("Nombre Genérico:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, -1, -1));

        jTextField2.setText("jTextField2");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 270, -1));

        jLabel4.setText("Forma Farmaceutica:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jTextField3.setText("jTextField3");
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 470, -1));

        jLabel5.setText("Concentración:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, -1, -1));

        jTextField4.setText("jTextField4");
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 270, -1));

        jLabel6.setText("Presentación:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, -1));

        jTextField5.setText("jTextField5");
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 470, -1));

        jLabel7.setText("Precio unidad:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 90, -1));

        jTextField6.setText("jTextField6");
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 80, -1));

        jLabel8.setText("Unidades Envace:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, -1, -1));

        jTextField7.setText("jTextField7");
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, 80, -1));

        jLabel9.setText("Principal Indicación:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jTextField8.setText("jTextField8");
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 880, -1));

        jTextField9.setText("jTextField9");
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 510, -1));

        jLabel10.setText("Contra Indicaciones:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel11.setText("Unidad de Medida:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jTextField10.setText("jTextField10");
        jPanel1.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 150, -1));

        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 20, 140, 140));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Medicamentos Activos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, -1, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Añadir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, -1, -1));

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, -1));

        btnactivo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnactivo.setText("Desactivar");
        btnactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactivoActionPerformed(evt);
            }
        });
        jPanel1.add(btnactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 0, 102));
        jLabel13.setText("jLabel13");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 100, -1));

        jButton4.setText("Foto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 180, 70, 30));
        jPanel1.add(fotot, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 170, -1, 0));
        jPanel1.add(rt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 170, -1, 0));

        jButton5.setText("Todo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Módulo  Productos");

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

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MTVx3.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1072, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1328, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addGap(35, 35, 35)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            agregarRegistroProducto();
            limpiar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 try{
     actualizar();

 } catch(Exception ex){
            Logger.getLogger(RegProductos.class.getName()).log(Level.SEVERE, null, ex);
 }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            fila=this.jTable1.getSelectedRow();
            id=Integer.parseInt(this.jTable1.getValueAt(fila,0).toString());
            traerRegistro();
        } catch (Exception ex) {
            Logger.getLogger(RegProductos.class.getName()).log(Level.SEVERE, null, ex);
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
           // TODO add your handling code here:
   JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg", "jpg");
        file.setFileFilter(filtro);

        int seleccion = file.showOpenDialog(contentPane);
        //Si el usuario, pincha en aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Seleccionamos el fichero
            fichero = file.getSelectedFile();
            //Ecribe la ruta del fichero seleccionado en el campo de texto
            rt.setText(fichero.getAbsolutePath());
            ImageIcon icon = new ImageIcon(fichero.toString());
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_DEFAULT));
            jLabel12.setText(null);
            fotot.setText(fichero.toString());
           jLabel12.setIcon(icono);

        }
    }//GEN-LAST:event_jButton4ActionPerformed
public void QueTodoHombre() throws SQLException, Exception{
       limpiar();  
       r=cp.TodoXD();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),
                            r.getString(9),r.getString(10),r.getString(11)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            inicializarTabla();
            QueTodoHombre();
        } catch (Exception ex) {
            Logger.getLogger(RegProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    public void traerRegistro() throws Exception{
       if(id>0){
           r=cp.productosUno(id);
           r.first();
           this.jTextField1.setText(r.getString(2));
           this.jTextField2.setText(r.getString(3));
           this.jTextField3.setText(r.getString(4));
           this.jTextField4.setText(r.getString(5));
           this.jTextField5.setText(r.getString(6));
           this.jTextField6.setText(r.getString(7));           
           this.jTextField7.setText(r.getString(8));
           this.jTextField8.setText(r.getString(9));
           this.jTextField9.setText(r.getString(11));
           this.jTextField10.setText(r.getString(10));
           this.fotot.setText(r.getString(12));
           
           
            ImageIcon icon = new ImageIcon(r.getString(12));
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(jLabel12.getWidth(), jLabel12.getHeight(), Image.SCALE_DEFAULT));
            jLabel12.setText(null);
            fotot.setText(r.getString(12));
           jLabel12.setIcon(icono);
           
           this.rt.setText(r.getString(13));
                 
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
                cp.activarProducto(id);
                inicializarTabla();
                mostrarDatosEliminados();
                limpiar();
            }catch(Exception ex){
                
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactivo;
    private javax.swing.JTextField fotot;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField rt;
    // End of variables declaration//GEN-END:variables
}
