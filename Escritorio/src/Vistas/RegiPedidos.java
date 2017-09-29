/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;
import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;
import java.util.Date;
import java.text.SimpleDateFormat;
import Core.ControladorProducto;
import Core.ControladorPedido;
import Modelo.DetallePedido;
import Core.ControladorSucursal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


import Core.ControllerLogin;


import BaseDeDatos.ConexionMySQL;
import Modelo.Pedido;
import static Vistas.Inicio.idEmpleado;

/**
 *
 * @author JaredXD
 */
public class RegiPedidos extends javax.swing.JPanel {
 DefaultTableModel miTabla = new DefaultTableModel();
  DefaultTableModel miTabla2 = new DefaultTableModel();
    Timer reloj;
    ResultSet r;
    ControladorPedido cpe;
    ControladorProducto cp;
    ControladorSucursal cs;
    Pedido ped;
    DefaultComboBoxModel miCombo, miCombo2;
    ConexionMySQL conn;
    String ngeneral, ngenerico, ff, concen;
    int id = 0, canti = 0;
    double pu = 0;
    ControllerLogin conlo;

    /**
     * Creates new form RegiPedidos
     */
    public RegiPedidos() throws Exception {
        initComponents();
        cpe = new ControladorPedido();
        cp = new ControladorProducto();
        cs = new ControladorSucursal();
        conlo = new ControllerLogin();
        System.out.println(conlo.validarUsuario("10060001", "10060001").getId());

        inicializarTabla();

        reloj = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fechaHora = new Date();
                String formato1 = "yyyy-MM-dd";
                String formato2 = "hh:mm:ss";
                SimpleDateFormat x1 = new SimpleDateFormat(formato1);
                SimpleDateFormat x2 = new SimpleDateFormat(formato2);
                String name;
                String pass;
                jLabel3.setText(x1.format(fechaHora));
                jLabel5.setText(x2.format(fechaHora));

            }
        });
        reloj.start();
        inicializarTabla();
         inicializarTabla2();
        inicializarCombo();
        llenarCombo2();
mostrarDatos();
        limpiarCajas();

    }
public void inicializarTabla2() throws SQLException, Exception{
        miTabla2=new DefaultTableModel();
        miTabla2.addColumn("ID Pedido");
        miTabla2.addColumn("Fecha Hora");
        miTabla2.addColumn("Nombre Empleado");
        miTabla2.addColumn("Nombre Producto");
        miTabla2.addColumn("Precio Compra");
        miTabla2.addColumn("Nombre Sucursal");
        miTabla2.addColumn("Cantidad");        
      
      
   
        this.jTable2.setModel(miTabla2);
       
        
    }
public void mostrarDatos() throws SQLException, Exception{
         r=cpe.PedidoTodos();
         r.beforeFirst();
         while(r.next()){
              miTabla2=(DefaultTableModel)jTable2.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7)}; 
             miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
         }
    }
public void mostrarDatosEliminados() throws SQLException, Exception{
      
       r=cpe.todasLasPedidosEliminados();
         r.beforeFirst();
         while(r.next()){
              miTabla2=(DefaultTableModel)jTable2.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7)};
             miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
         }
    }
    public void insertarDetalle() throws Exception {
        DetallePedido depe = new DetallePedido();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            depe.setIdPedido(Integer.parseInt(this.jLabel7.getText()));
            depe.setIdProducto((int) jTable1.getValueAt(i, 0));
            depe.setCantidad(Integer.parseInt((String) jTable1.getValueAt(i, 6)));
            depe.setPrecioCompra((double) jTable1.getValueAt(i, 3));
            cpe.agregarDetallePedido(depe);
            mostrarDatos();

        }
    }

    public void limpiarCajas() {
        this.jTextField1.setText(null);
        this.jTextField2.setText(null);
        this.jTextField3.setText(null);
        this.jTextField1.requestFocus();
    }

    public void inicializarTabla() throws SQLException, Exception {
        miTabla = new DefaultTableModel();
        miTabla.addColumn("Id");
        miTabla.addColumn("Nombre general");
        miTabla.addColumn("Nombre Genérico");
        miTabla.addColumn("Precio");
        miTabla.addColumn("FormaFarmaceutica");
        miTabla.addColumn("Concentracion");
        miTabla.addColumn("Cantidad");
        this.jTable1.setModel(miTabla);

    }

    public void inicializarCombo() {
        miCombo = new DefaultComboBoxModel();
        this.jComboBox1.setModel(miCombo);
        miCombo = (DefaultComboBoxModel) this.jComboBox1.getModel();
        miCombo.addElement("Medicamentos");

        miCombo2 = new DefaultComboBoxModel();
        this.jComboBox2.setModel(miCombo2);
        miCombo2 = (DefaultComboBoxModel) this.jComboBox2.getModel();
        miCombo2.addElement("Sucursales");
        
        
    }

    public void llenarCombo(String x) throws Exception {
        miCombo.removeAllElements();
        r = cpe.cargarDatos(x);
        r.beforeFirst();
        while (r.next()) {
            String cadena = r.getString(1) + "-" + r.getString(2) + "-" + r.getString(6) + "-" + r.getString(5) + "-" + r.getString(4) + "-" + r.getString(3);
            miCombo.addElement(cadena);
        }
    }

    public void llenarCombo2() throws Exception {
        miCombo2.removeAllElements();
        r = cs.consultarSucursales(idEmpleado);
        r.beforeFirst();
        while (r.next()) {
            String cadena = r.getString(1) + "-" + r.getString(2);
            miCombo2.addElement(cadena);

        }
        

    }
   
   public void bi() throws Exception{
        
        r=cpe.ip(this.jTextField4.getText());     
                this.jTable2.setModel(new DefaultTableModel());
        inicializarTabla2();
        r.beforeFirst();
         while(r.next()){
              miTabla2=(DefaultTableModel)jTable2.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7)};
             miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
         }
    
    }
    public void bf() throws Exception{
        
        r=cpe.fh(this.jTextField5.getText());     
                this.jTable2.setModel(new DefaultTableModel());
        inicializarTabla2();
        r.beforeFirst();
         while(r.next()){
              miTabla2=(DefaultTableModel)jTable2.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7)};
             miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
         }
    
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnpedido = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnConcluir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnok = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        btnoki = new javax.swing.JButton();
        btnactivo = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Módulo  Pedidos");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Medicamentos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        btnpedido.setText("Crear Pedido");
        btnpedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpedidoActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnConcluir.setText("Crear Detalle");
        btnConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("idMedicamento");

        jTextField1.setText("jTextField1");

        btnok.setText("OK");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Buscar Medicamento");

        jTextField2.setText("jTextField2");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Cantidad");

        jTextField3.setText("jTextField3");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        btnoki.setText("OK");
        btnoki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokiActionPerformed(evt);
            }
        });

        btnactivo.setText("Desactivar");
        btnactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactivoActionPerformed(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Pedidos Activos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Centro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Centro Max");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Plaza Mayor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Futurama");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setText("Pedidos en:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnok)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnoki, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jCheckBox1)
                                .addGap(18, 18, 18)
                                .addComponent(btnactivo)))
                        .addGap(42, 42, 42))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnpedido)
                .addGap(27, 27, 27)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnConcluir)
                        .addGap(209, 209, 209)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnpedido)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConcluir)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jLabel12))
                .addGap(32, 32, 32)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnok)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnoki))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnactivo)
                    .addComponent(jCheckBox1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setBackground(new java.awt.Color(0, 102, 51));
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Pedido No.");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Fecha:");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("jLabel3");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Hora:");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("jLabel5");

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
        jScrollPane1.setViewportView(jTable1);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MTVx3.png"))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel13.setText("Por id:");

        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel14.setText("Por fecha:");

        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel5)
                        .addGap(191, 191, 191))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(112, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(236, 236, 236)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(134, 134, 134)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(255, 255, 255))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jLabel14)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(204, 204, 204))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
asignarDatos();       
// TODO add your handling code here:
    }//GEN-LAST:event_btnokActionPerformed

    private void btnokiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokiActionPerformed
        // TODO add your handling code here:
        try {
            añadirFila();
            limpiarCajas();
            
        } catch (Exception ex) {
            Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnokiActionPerformed
private void insertarInventarioDB() throws Exception{
    int idSucursal=Integer.parseInt(miCombo2.getSelectedItem().toString());
    
    int idUsuario=Integer.parseInt(this.jTextField1.getText());
    int existencias= Integer.parseInt(this.jTextField3.getText());
    
    cs.insertarEnInventario(idSucursal,idUsuario,existencias);
}
    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
try {
            llenarCombo(this.jTextField2.getText());
        } catch (Exception ex) {
            Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
 if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            this.jTextField2.setText(miCombo.getSelectedItem().toString());
            String[] datos = jTextField2.getText().split("-");
            id = Integer.parseInt(datos[0]);
            this.jTextField1.setText(datos[0]);
            this.jTextField2.setText(datos[1]);
            ngeneral = datos[1];
            ngenerico = datos[5];
            concen = datos[2];
            ff = datos[3];
            pu = Double.parseDouble(datos[4]);
            this.jTextField3.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1KeyReleased

    private void btnpedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpedidoActionPerformed
try {
            String xx = miCombo2.getSelectedItem().toString();
            String[] datos = xx.split("-");
            int ids = Integer.parseInt(datos[0]);
            int ide = conlo.idEmpleado();
            ped = new Pedido();
            ped.setActivo((1));
            ped.setIdEmpleado(idEmpleado);
            ped.setIdSucursal(ids);
            ped.setFechaHoraPedido(this.jLabel3.getText()+" "+this.jLabel5.getText());
            ped.setEstatus(1);
            cpe.agregarPedido(ped);
            r = cpe.obtenerFolio();
            r.first();
            this.jLabel7.setText(r.getString(1));
JOptionPane.showMessageDialog(this, "Se agrego el pedido ツ ");
        } catch (Exception ex) {
            Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al crear el pedido ☹  ");
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnpedidoActionPerformed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
char c = evt.getKeyChar();
        if (c >= '0' && c <= '9') {

        } else {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyReleased

    private void btnConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirActionPerformed
try {
    
            insertarDetalle();
            inicializarTabla2();
            mostrarDatos();
            JOptionPane.showMessageDialog(this, "Se agrego el detalle ツ ");
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crer el detalle ☹ ");
            Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConcluirActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
         if(jCheckBox1.isSelected()){
           try{
                this.btnactivo.setText("Desactivar");
                inicializarTabla2();
                mostrarDatos();
            }catch(Exception ex){
                
            } 
        }
        else{
             try{
                this.btnactivo.setText("Activar");
                inicializarTabla2();
                mostrarDatosEliminados();
            }catch(Exception ex){
                
            } 
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed
public void traerRegistro() throws Exception{
       if(id>0){
           r=cpe.PeUno(id);
           r.first();
           this.jLabel7.setText(r.getString(1));
           
       }
}
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
             try {
             int fila=0;
            fila=this.jTable2.getSelectedRow();
            id=Integer.parseInt(this.jTable2.getValueAt(fila,0).toString());
            traerRegistro();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable2MouseClicked
public void status(){
        if(btnactivo.getText().equals("Desactivar")){
            try{
           
                cpe.delete(id);
                inicializarTabla2();
                mostrarDatos();
                
            }catch(Exception ex){
                
            }
        }
        if(btnactivo.getText().equals("Activar")){
            try{
              
                cpe.activar(id);
                inicializarTabla2();
                mostrarDatosEliminados();
                
            }catch(Exception ex){
                
            }
        }
    }
    private void btnactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactivoActionPerformed
        // TODO add your handling code here:
        status();
    }//GEN-LAST:event_btnactivoActionPerformed
public void V1() throws SQLException, Exception{
     r=cpe.v1();
         r.beforeFirst();
         while(r.next()){
              miTabla2=(DefaultTableModel)jTable2.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7)}; 
             miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
         }
    }
public void V2() throws SQLException, Exception{
     r=cpe.v2();
         r.beforeFirst();
         while(r.next()){
              miTabla2=(DefaultTableModel)jTable2.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7)}; 
             miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
         }
    }
public void V3() throws SQLException, Exception{
     r=cpe.v3();
         r.beforeFirst();
         while(r.next()){
              miTabla2=(DefaultTableModel)jTable2.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7)}; 
             miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
         }
    }
public void V4() throws SQLException, Exception{
     r=cpe.v4();
         r.beforeFirst();
         while(r.next()){
              miTabla2=(DefaultTableModel)jTable2.getModel();
             Object [] datos={r.getString(1),r.getString(2),r.getString(3),r.getString(4),
                            r.getString(5),r.getString(6),r.getString(7)}; 
             miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
         }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
         // TODO add your handling code here:
         inicializarTabla2();
         V1();
     } catch (Exception ex) {
         Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
         // TODO add your handling code here:
         inicializarTabla2();
         V2();
     } catch (Exception ex) {
         Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
         // TODO add your handling code here:
         inicializarTabla2();
         V3();
     } catch (Exception ex) {
         Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
         // TODO add your handling code here:
         inicializarTabla2();
         V4();
     } catch (Exception ex) {
         Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     try {
         bi();
         this.jTextField4.setText(null);
         // TODO add your handling code here:
     } catch (Exception ex) {
         Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     try {
         bf();   
          this.jTextField5.setText(null);
         // TODO add your handling code here:
     } catch (Exception ex) {
         Logger.getLogger(RegiPedidos.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jButton6ActionPerformed

public void asignarDatos() {
        try {
            r = cp.productosUno(Integer.parseInt(this.jTextField1.getText()));
            r.beforeFirst();
            while (r.next()) {
                this.jTextField2.setText(r.getString(2));
                id = Integer.parseInt(r.getString(1));
                ngeneral = r.getString(2);
                ngenerico = r.getString(3);
                ff = r.getString(4);
                concen = r.getString(5);
                pu = Double.parseDouble(r.getString(7));
            }
            llenarCombo(ngeneral);
            this.jTextField3.requestFocus();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void añadirFila() throws SQLException, Exception {
        miTabla = (DefaultTableModel) jTable1.getModel();
        Object[] datos = {id, ngeneral, ngenerico, pu, ff, concen, this.jTextField3.getText()};
        miTabla.addRow(datos);
        this.jTable1.setModel(miTabla);
    }
    public void limpiarTabla(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) (miTabla);
            int filas=miTabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConcluir;
    private javax.swing.JButton btnactivo;
    private javax.swing.JButton btnok;
    private javax.swing.JButton btnoki;
    private javax.swing.JButton btnpedido;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
