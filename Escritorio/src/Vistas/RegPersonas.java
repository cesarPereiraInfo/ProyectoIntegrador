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
import Core.ControladorPersona;
import Modelo.Persona;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RegPersonas extends javax.swing.JPanel {
    ControladorPersona cp;
    ResultSet r;
    DefaultTableModel miTabla=new DefaultTableModel();
    int fila=0;
    int id;
    Image foto;
     JButton boton;
    File fichero = null;
    private JPanel contentPane;
    
    /**
     * Creates new form RegProductos
     */
    public RegPersonas() throws Exception {
        initComponents();
        
        cp=new ControladorPersona();
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
                            r.getString(9),r.getString(10),r.getString(11),r.getString(12)};
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
                            r.getString(9),r.getString(10),r.getString(11),r.getString(12)};
            miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
        }
        
    }

    public void inicializarTabla() throws SQLException, Exception{
        miTabla=new DefaultTableModel();
        miTabla.addColumn("ID");
        miTabla.addColumn("Nombre");
        miTabla.addColumn("A. Paterno");        
        miTabla.addColumn("A. Materno");
        miTabla.addColumn("Genero");
        miTabla.addColumn("Domicilio");
        miTabla.addColumn("C. Postal");
        miTabla.addColumn("Ciudad");
        miTabla.addColumn("Estdo");
        miTabla.addColumn("Telefono");
        miTabla.addColumn("Fecha Nacimiento");
        miTabla.addColumn("Foto");
       
        this.jTable1.setModel(miTabla);
       
        
    }
    public void mostrarDatos() throws SQLException, Exception{
         r=cp.PersonaTodo();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),
                            r.getString(9),r.getString(10),r.getString(11),r.getString(12)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
  
   
   
   
    public void agregarRegistroProducto() throws Exception{
        try{
            Persona pro=new Persona();
            pro.setNombre(this.jTextField1.getText());
            pro.setApePaterno(this.jTextField2.getText());
            pro.setApeMaterno(this.jTextField3.getText());
            pro.setGenero(Integer.parseInt(this.jTextField4.getText()));
            pro.setDomicilio(this.jTextField5.getText());
            pro.setCodigoPostal(this.jTextField6.getText());
            pro.setCiudad(this.jTextField7.getText());
            pro.setEstado(this.jTextField8.getText());
            pro.setTelefono(this.jTextField9.getText());
            pro.setFechaNacimiento(this.jTextField10.getText());            
       
           pro.setFoto(this.jTextField11.getText());
        
            cp.agregarPersona(pro);
            this.jLabel13.setText("Registro agregado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch(Exception e){
            System.out.println(e);
        }
      
        
    }
    public void actualizar() throws Exception{
        try{
            Persona pro=new Persona();
           pro.setNombre(this.jTextField1.getText());
            pro.setApePaterno(this.jTextField2.getText());
            pro.setApeMaterno(this.jTextField3.getText());
            pro.setGenero(Integer.parseInt(this.jTextField4.getText()));
            pro.setDomicilio(this.jTextField5.getText());
            pro.setCodigoPostal(this.jTextField6.getText());
            pro.setCiudad(this.jTextField7.getText());
            pro.setEstado(this.jTextField8.getText());
            pro.setTelefono(this.jTextField9.getText());
            pro.setFechaNacimiento(this.jTextField10.getText());
             pro.setFoto(this.jTextField11.getText());
           

            cp.actualizarPersona(pro, id);
            this.jLabel13.setText("Registro modificado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch (Exception e){
                        System.out.println(e);
        }
    }  
    public void Eliminar(){
        try{
            Persona pro =new  Persona();
         pro.setNombre(this.jTextField1.getText());
            pro.setApePaterno(this.jTextField2.getText());
            pro.setApeMaterno(this.jTextField3.getText());
            pro.setGenero(Integer.parseInt(this.jTextField4.getText()));
            pro.setDomicilio(this.jTextField5.getText());
            pro.setCodigoPostal(this.jTextField6.getText());
            pro.setCiudad(this.jTextField7.getText());
            pro.setEstado(this.jTextField8.getText());
            pro.setTelefono(this.jTextField9.getText());
            pro.setFechaNacimiento(this.jTextField10.getText());
            
             pro.setFoto(this.jTextField11.getText());
           

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
        this.jTextField11.setText(null);
        this.txt_ruta.setText(null);
        this.lbfoto.setText(null);
        
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
        lbfoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnactivo = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txt_ruta = new javax.swing.JTextField();
        btnfoto = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Persona", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        jTextField1.setText("jTextField1");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 30, 470, -1));

        jLabel3.setText("Apellido Paterno");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, -1));

        jTextField2.setText("jTextField2");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 270, -1));

        jLabel4.setText("Apellido Materno:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 20));

        jTextField3.setText("jTextField3");
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 470, -1));

        jLabel5.setText("Genero:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, -1, -1));

        jTextField4.setText("jTextField4");
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, 270, -1));

        jLabel6.setText("Domicilio:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 70, -1));

        jTextField5.setText("jTextField5");
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 470, -1));

        jLabel7.setText("CP:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 80, -1));

        jTextField6.setText("jTextField6");
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 80, -1));

        jLabel8.setText("Ciudad:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, -1, -1));

        jTextField7.setText("jTextField7");
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 80, -1));

        jLabel9.setText("Estado:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jTextField8.setText("jTextField8");
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 470, -1));

        jTextField9.setText("jTextField9");
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 470, -1));

        jLabel10.setText("Telefono:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel11.setText("Fecha de Nacimiento:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        lbfoto.setText("jLabel12");
        lbfoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lbfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 20, 140, 130));

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
        btnactivo.setText("Eliminar");
        btnactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactivoActionPerformed(evt);
            }
        });
        jPanel1.add(btnactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 0, 102));
        jLabel13.setText("jLabel13");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 100, -1));

        txt_ruta.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jPanel1.add(txt_ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 160, -1, 0));

        btnfoto.setText("Foto");
        btnfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfotoActionPerformed(evt);
            }
        });
        jPanel1.add(btnfoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 180, 80, 30));

        jTextField11.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 160, -1, 0));

        try {
            jTextField10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 140, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Módulo  Personas");

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

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MTVx3.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(465, 465, 465))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addGap(47, 47, 47)
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
            Logger.getLogger(RegPersonas.class.getName()).log(Level.SEVERE, null, ex);
 }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            fila=this.jTable1.getSelectedRow();
            id=Integer.parseInt(this.jTable1.getValueAt(fila,0).toString());
            traerRegistro();
        } catch (Exception ex) {
            Logger.getLogger(RegPersonas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactivoActionPerformed
Eliminar(); 
    }//GEN-LAST:event_btnactivoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfotoActionPerformed
        try {
            foto();
        } catch (Exception ex) {
            Logger.getLogger(RegPersonas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnfotoActionPerformed
    public void traerRegistro() throws Exception{
         
        if(id>0){
           
             
        
               r=cp.personasUno(id);
           r.first();
           this.jTextField1.setText(r.getString(2));
           this.jTextField2.setText(r.getString(3));
           this.jTextField3.setText(r.getString(4));
           this.jTextField4.setText(r.getString(5));
           this.jTextField5.setText(r.getString(6));
           this.jTextField6.setText(r.getString(7));
           this.jTextField7.setText(r.getString(8));
           this.jTextField8.setText(r.getString(9));
           this.jTextField9.setText(r.getString(10));
           this.jTextField10.setText(r.getString(11));
            this.jTextField11.setText(r.getString(12));
           
          this.lbfoto.setIcon(null);
        
          
                 
       }
         
           
    }
   public void foto() throws Exception{
    JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg", "jpg");
        file.setFileFilter(filtro);

        int seleccion = file.showOpenDialog(contentPane);
        //Si el usuario, pincha en aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Seleccionamos el fichero
            fichero = file.getSelectedFile();
            //Ecribe la ruta del fichero seleccionado en el campo de texto
            txt_ruta.setText(fichero.getAbsolutePath());
            ImageIcon icon = new ImageIcon(fichero.toString());
            System.out.println(fichero.getName());
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbfoto.getWidth(), lbfoto.getHeight(), Image.SCALE_DEFAULT));
            lbfoto.setText(null);
            jTextField11.setText(fichero.getName());
            lbfoto.setIcon(icono);
}
        }
     int cont=0;
    public static String encriptionarCadena(BufferedImage image) {
        String imageString=null;
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        try{
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes=bos.toByteArray();
            BASE64Encoder ec=new BASE64Encoder();
            imageString=ec.encode(imageBytes);
            bos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return imageString;
            
    }
    public static BufferedImage decodeToImage(String imageString){
        BufferedImage image=null;
        byte[] imageByte;
        try{
            BASE64Decoder de=new BASE64Decoder();
            imageByte =de.decodeBuffer(imageString);
            ByteArrayInputStream xd=new ByteArrayInputStream (imageByte);
            image=ImageIO.read(xd);
            xd.close();
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }
    
   public void cargar(int limite)throws Exception{
        BufferedImage img=null;
       
        String image_string=null;
        try{
            while (r.next()){
                image_string = r.getString("imagen");
                
            }if(image_string==null){
                 cont = cont-1;
                 contar();
            }else{
                img=decodeToImage(image_string);
                ImageIcon icon=new ImageIcon(img);
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbfoto.getWidth(), lbfoto.getHeight(), Image.SCALE_DEFAULT));
                lbfoto.setText(null);
                lbfoto.setIcon(icono);
            }
        }catch(Exception e){
            
        }
    }
    public void contar() throws Exception{
        try{
            int con=0;
            while(r.next()){
                con=r.getInt("cont");
                
            }if(con==0){
                JOptionPane.showMessageDialog(null, ":c");
            }else{
                cont=con-1;
                cargar(cont);
            }
        }catch(Exception e){
            
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactivo;
    private javax.swing.JButton btnfoto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JFormattedTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbfoto;
    private javax.swing.JTextField txt_ruta;
    // End of variables declaration//GEN-END:variables
}
