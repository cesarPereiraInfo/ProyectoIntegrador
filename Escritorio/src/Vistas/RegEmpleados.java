/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import BaseDeDatos.ComandosSucursal;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Core.ControladorEmpleado;
import Core.ControladorSucursal;
import Modelo.Empleado;
import Modelo.Usuario;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.grios.jwebcam.WebCamAdapter;

public class RegEmpleados extends javax.swing.JPanel {
    Empleado e;
    ControladorEmpleado cp;
    ResultSet r;
    ResultSet resuls;
    DefaultTableModel miTabla=new DefaultTableModel();
    int fila=0;
    int id;
    JButton boton;
    RegEmpleados d;
    DefaultComboBoxModel miCombo, miCombo2, miCombo3, miCombo4;
    String valor;
    ControladorSucursal cs;
    WebCamAdapter webCamAdapter;
    /**
     * Creates new form RegProductos
     */
    public RegEmpleados() throws Exception {
        initComponents();
        consultarCamarasWeb();
        cp=new ControladorEmpleado();
        limpiar();
        inicializarTabla();
        
        mostrarDatos();
         inicializarCombo(); 
         llenarCombo();
       
        llenarCombo3();
       
    }
    
       public void inicializarCombo() {
        miCombo = new DefaultComboBoxModel();
        this.jComboBox1.setModel(miCombo);
        miCombo = (DefaultComboBoxModel) this.jComboBox1.getModel();
        miCombo.addElement("Persona");

        miCombo2 = new DefaultComboBoxModel();
      
        miCombo3 = new DefaultComboBoxModel();
        this.jComboBox3.setModel(miCombo3);
        miCombo3 = (DefaultComboBoxModel) this.jComboBox3.getModel();
        miCombo3.addElement("Sucursales");
        
      
        
        
    }
        public void llenarCombo() throws Exception {
        miCombo.removeAllElements();
        r = cp.consultarPersona();
        r.beforeFirst();
        while (r.next()) {
            String cadena = r.getString(1) + "-" + r.getString(2)+ " " +r.getString(3);
            miCombo.addElement(cadena);
        }
    }

  
     public void llenarCombo3() throws Exception {
        miCombo3.removeAllElements();
        r = cp.consultarSucursales();
        r.beforeFirst();
        while (r.next()) {
            String cadena = r.getString(1) + "-" + r.getString(2);
            miCombo3.addElement(cadena);

        }
        

    }
    
 
     public void letra() throws Exception{
        
        r=cp.datosColeccion(this.jTextField8.getText());
        this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
        while(r.next()){
            miTabla=(DefaultTableModel)this.jTable1.getModel();
            Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),r.getString(9),r.getString(10)}; 
            miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
        }
        
    }
       public void FI() throws Exception{
        
        r=cp.fechas(this.jTextField9.getText());
        this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
        while(r.next()){
            miTabla=(DefaultTableModel)this.jTable1.getModel();
            Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),r.getString(9),r.getString(10)}; 
            miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
        }
        
    }
       private void consultarCamarasWeb(){
           //Consultar las camaras web que tiene el sistema:
           Webcam[] camarasWeb = WebCamAdapter.getSystemCamDevicesAsArray();
           //Llenamos el combobox:
           for (int i = 0; i < camarasWeb.length; i++) {
               cmbWebCams.addItem(camarasWeb[i].getName());
           }
       }
       private void conectarConCamaraWeb(){
           WebcamPanel wcp = null;
           pnlWebCam.removeAll();
           if (webCamAdapter != null)
               webCamAdapter.stop();
               webCamAdapter = new WebCamAdapter();
               wcp = webCamAdapter.start(cmbWebCams.getSelectedItem().toString());
               pnlWebCam.add(wcp, BorderLayout.CENTER);
               pnlWebCam.paintAll(pnlWebCam.getGraphics());
       }
     public void numericos() throws Exception{
        
        r=cp.numeros((this.jTextField7.getText()));
        this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
        while(r.next()){
            miTabla=(DefaultTableModel)this.jTable1.getModel();
            Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),r.getString(9),r.getString(10)}; 
            miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
        }
        
    }
     public void bc() throws Exception{
        
        r=cp.buscarxcodigo(this.jTextField6.getText());     
                this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),r.getString(9),r.getString(10)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    
    
     }
       public void bd() throws Exception{
        
        r=cp.buscarxdo(this.jTextField10.getText());     
                this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),r.getString(9),r.getString(10)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    
    }
       public void bcp() throws Exception{
        
        r=cp.buscarxcp(this.jTextField11.getText());     
                this.jTable1.setModel(new DefaultTableModel());
        inicializarTabla();
        r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),r.getString(9),r.getString(10)};
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
        miTabla.addColumn("Codigo");
        miTabla.addColumn("Fecha Ingreso");
         miTabla.addColumn("CP");
          miTabla.addColumn("Domicilio");
        miTabla.addColumn("Puesto");
        miTabla.addColumn("Salario");
        
        this.jTable1.setModel(miTabla);
       
        
    }
   public void mostrarDatos() throws SQLException, Exception{
         r=cp.EmpleadoTodo();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),r.getString(9),r.getString(10)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
   
   public void mostrarDatosEliminados() throws SQLException, Exception{
       limpiar();  
       r=cp.todosLosEmpEliminados();
         r.beforeFirst();
         while(r.next()){
              miTabla=(DefaultTableModel)jTable1.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7),r.getString(8),r.getString(9),r.getString(10)};
             miTabla.addRow(datos);
            this.jTable1.setModel(miTabla);
         }
    }
   
   
    public void agregarRegistroEmpleado() throws Exception{
        try{
            Empleado pro=new Empleado();
            pro.setCodigo(this.jTextField1.getText());
            pro.setFechaIngreso(this.jTextField2.getText());
            pro.setPuesto(this.jTextField3.getText());
            pro.setSalario(Float.parseFloat(this.jTextField4.getText()));
            pro.setUsuarios(Integer.parseInt(this.jTextField5.getText()));
            String x = miCombo.getSelectedItem().toString();
            String[] d = x.split("-");
            
            String xxx = miCombo3.getSelectedItem().toString();
            String[] dato = xxx.split("-");
            int idp = Integer.parseInt(d[0]);

            int ids = Integer.parseInt(dato[0]);
            pro.setPersonas(idp);
            
            pro.setSucursales(ids);
         
            cp.agregarEmpleado(pro);
            this.jLabel13.setText("Registro agregado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch(Exception e){
            System.out.println(e);
        }
      
        
    }
     public void agregarRegistroUsuario() throws Exception{
        try{
            Usuario usu=new Usuario();
         
           usu.setRol((String) this.jComboBox4.getSelectedItem());
           
            
             
            
           
            cp.agregarUsuario(usu);
            this.jLabel13.setText("Registro agregado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch(Exception e){
            System.out.println(e);
        }
      
        
    }
      public void ActUsuario() throws Exception{
        try{
            Usuario usu=new Usuario();
           
             
            
           
            cp.actualizarU(usu, id);
            this.jLabel13.setText("Registro Modificado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch(Exception e){
            System.out.println(e);
        }
      
        
    }
    public void actualizar() throws Exception{
        try{
           Empleado pro=new Empleado();
            pro.setCodigo(this.jTextField1.getText());
            pro.setFechaIngreso(this.jTextField2.getText());
            pro.setPuesto(this.jTextField3.getText());
            pro.setSalario(Float.parseFloat(this.jTextField4.getText()));
            pro.setUsuarios(Integer.parseInt(this.jTextField5.getText()));
            String x = miCombo.getSelectedItem().toString();
            String[] d = x.split("-");
           
            String xxx = miCombo3.getSelectedItem().toString();
            String[] dato = xxx.split("-");
            int idp = Integer.parseInt(d[0]);
       
            int ids = Integer.parseInt(dato[0]);
            pro.setPersonas(idp);
           
            pro.setSucursales(ids);

            cp.actualizarEmpleado(pro, id);
            this.jLabel13.setText("Registro modificado!!!");
            inicializarTabla();
            mostrarDatos();
        }catch (Exception e){
                        System.out.println(e);
                      
        }
    }  
    public void Eliminar(){
        try{
            Empleado pro=new Empleado();
            pro.setCodigo(this.jTextField1.getText());
            pro.setFechaIngreso(this.jTextField2.getText());
            pro.setPuesto(this.jTextField3.getText());
            pro.setSalario(Float.parseFloat(this.jTextField4.getText()));
            pro.setUsuarios(Integer.parseInt(this.jTextField5.getText()));
            String x = miCombo.getSelectedItem().toString();
            String[] d = x.split("-");
            
            String xxx = miCombo3.getSelectedItem().toString();
            String[] dato = xxx.split("-");
            int idp = Integer.parseInt(d[0]);
      ;
            int ids = Integer.parseInt(dato[0]);
            pro.setPersonas(idp);
        
            pro.setSucursales(ids);

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
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnactivo = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        pnlWebCam = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        cmbWebCams = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Codigo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        jTextField1.setEditable(false);
        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 250, -1));

        jLabel3.setText("Fecha Ingreso:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel4.setText("Puesto:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, 20));

        jTextField3.setText("jTextField3");
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 250, -1));

        jLabel5.setText("Salario:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        jTextField4.setText("jTextField4");
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 270, -1));

        jLabel6.setText("Persona:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 70, -1));

        jLabel7.setText("Usuario:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 60, -1));

        jLabel8.setText("Sucursal:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 80, -1));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Empleados Activos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, -1, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Añadir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, -1, -1));

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, -1, -1));

        btnactivo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnactivo.setText("Desactivar");
        btnactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactivoActionPerformed(evt);
            }
        });
        jPanel1.add(btnactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 0, 102));
        jLabel13.setText("jLabel13");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 220, -1));

        jButton4.setText("OK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 60, -1));

        jLabel16.setText("Rol:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 150, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 140, -1));

        jComboBox4.setMaximumRowCount(2);
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Empleado" }));
        jComboBox4.setToolTipText("");
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 330, -1));

        jTextField5.setEditable(false);
        jTextField5.setText("jTextField5");
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 140, -1));

        try {
            jTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 218, -1));

        jLabel19.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 30, 110, 120));

        jButton11.setText("C");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 180, -1, -1));

        pnlWebCam.setLayout(new java.awt.BorderLayout());
        jPanel1.add(pnlWebCam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 250, 160));

        jButton12.setText("E");
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 180, -1, -1));

        jPanel1.add(cmbWebCams, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 180, 170, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Módulo  Empleados");

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

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Por nombre:");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Por id:");

        jLabel12.setForeground(new java.awt.Color(25, 29, 35));
        jLabel12.setText("Por codigo:");

        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Por fecha:");

        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Buscar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        try {
            jTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel15.setForeground(new java.awt.Color(10, 11, 14));
        jLabel15.setText("Por domicilio:");

        jButton9.setText("Buscar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(5, 10, 16));
        jLabel17.setText("Por C.P:");

        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jButton10.setText("Buscar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9)
                        .addGap(280, 280, 280)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(jTextField6))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(jButton5))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8)
                            .addComponent(jButton7)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1328, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel15)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jLabel12)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
           
            agregarRegistroEmpleado();
            limpiar();
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 try{
     actualizar();

 } catch(Exception ex){
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
 }
    }//GEN-LAST:event_jButton3ActionPerformed
public void traerRegistroX2() throws Exception{
       if(id>0){
           r=cp.UsuUno(id);
           r.first();
           
         
           
          
       }
        
    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            fila=this.jTable1.getSelectedRow();
            id=Integer.parseInt(this.jTable1.getValueAt(fila,0).toString());
            traerRegistro();
            traerRegistroX2();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
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
        String rol="";
        if(this.jComboBox4.getSelectedIndex()==0){
            rol="Gerente";
        }
        else{
            rol="Empleado";
        }
        try {
            cp.ce(this.jTextField2.getText(),rol );
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sustraer=null;
            r=cp.contarUsuario();
            r.next();
            valor=r.getString(1);
            r=null;
            r=cp.traerUsuario(Integer.parseInt(valor));
            r.next();
            this.jTextField1.setText(r.getString(2));
            this.jTextField5.setText(r.getString(1));
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            bc();
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            numericos();
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            // TODO add your handling code here:
            letra();
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            // TODO add your handling code here
            FI();
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            // TODO add your handling code here
            bd();
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            bcp();
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        conectarConCamaraWeb();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton11MouseClicked
    public void traerRegistro() throws Exception{
       if(id>0){
           r=cp.EmpleadoUno(id);
           r.first();
           this.jTextField1.setText(r.getString(2));
           this.jTextField2.setText(r.getString(3));
           this.jTextField3.setText(r.getString(4));
           this.jTextField4.setText(r.getString(5));
           this.jTextField5.setText(r.getString(8));
           
          
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
                cp.activarEmpleado(id);
                inicializarTabla();
                mostrarDatosEliminados();
                limpiar();
            }catch(Exception ex){
                
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactivo;
    private javax.swing.JComboBox<String> cmbWebCams;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JFormattedTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JFormattedTextField jTextField9;
    private javax.swing.JPanel pnlWebCam;
    // End of variables declaration//GEN-END:variables
}
