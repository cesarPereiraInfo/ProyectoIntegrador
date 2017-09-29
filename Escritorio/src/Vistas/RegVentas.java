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
import Core.ControladorVenta;
import Modelo.DetalleVenta;
import Core.ControladorSucursal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import Core.ControllerLogin;
import BaseDeDatos.ConexionMySQL;
import Core.ControladorCliente;
import Core.ControladorEmpleado;
import Modelo.Cliente;
import Modelo.Venta;
import static Vistas.Inicio.idEmpleado;

/**
 *
 * @author JaredXD
 */
public class RegVentas extends javax.swing.JPanel {

    DefaultTableModel miTabla = new DefaultTableModel();
    DefaultTableModel miTabla2 = new DefaultTableModel();
    Timer reloj;
    ResultSet r;
    ControladorVenta cv;
    ControladorProducto cp;
    ControladorSucursal cs;
    ControladorEmpleado ce;
    ControladorCliente cc;
    String[] arreglos = null;
    Venta v;
    DetalleVenta dv;
    DefaultComboBoxModel miCombo, miCombo2, miCombo3;
    ConexionMySQL conn;
    String ngeneral, ngenerico, ff, concen;
    int id = 0, canti = 0, idv;
    double pu = 0;
    ControllerLogin conlo;

    /**
     * Creates new form RegiPedidos
     */
    public RegVentas() throws Exception {
        initComponents();
        System.out.println("Empleado: " + idEmpleado);

        cc = new ControladorCliente();
        cv = new ControladorVenta();
        cp = new ControladorProducto();
        cs = new ControladorSucursal();
        conlo = new ControllerLogin();
        System.out.println(conlo.validarUsuario("10060001", "10060001").getId());

        inicializarTabla();

        reloj = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date fechaHor = new Date();
                String formato1 = "yyyy-MM-dd";
                String formato2 = "hh:mm:ss";
                SimpleDateFormat x1 = new SimpleDateFormat(formato1);
                SimpleDateFormat x2 = new SimpleDateFormat(formato2);
                String name;
                String pass;
                jLabel3.setText(x1.format(fechaHor));
                jLabel5.setText(x2.format(fechaHor));

            }
        });
        reloj.start();

        inicializarTabla();
        inicializarTabla2();
        mostrarDatos();
        inicializarCombo();
        llenarCombo2();
        llenarCombo3();
        limpiarCajas();
        inicializarComboCl(null);
    }

    public void inicializarTabla2() throws SQLException, Exception {
        miTabla2 = new DefaultTableModel();
        miTabla2.addColumn("ID Venta");
        miTabla2.addColumn("Fecha Hora");
        miTabla2.addColumn("Nombre Empleado");
        miTabla2.addColumn("Nombre Producto");
        miTabla2.addColumn("Cantidad");
        miTabla2.addColumn("Precio Venta");
        miTabla2.addColumn("ID Cliente");
        miTabla2.addColumn("Nombre Sucursal");
        this.jTable2.setModel(miTabla2);
    }

    public void mostrarDatos() throws SQLException, Exception {
        r = cv.VentaTodos();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable2.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
        }
    }

    public void limpiarCajas() {
        this.jTextField1.setText(null);
        this.jTextField2.setText(null);
        this.jTextField3.setText(null);
        this.jTextField4.setText(null);
        this.jTextField5.setText(null);
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

        /*miCombo3 = new DefaultComboBoxModel();
        this.jComboBox3.setModel(miCombo3);
        miCombo3 = (DefaultComboBoxModel) this.jComboBox3.getModel();
        miCombo3.addElement("Clientes");
         */
    }

    public void inicializarComboCl(String filtro) {
        Cliente[] clientes = null;
        String valor = null;
        try {
            clientes = cc.getCliente(filtro);
            cmbClientes.removeAllItems();
            arreglos = new String[clientes.length];
            if (clientes != null) {
                //Recorremos el arreglo de tipo Cliente:
                for (int i = 0; i < clientes.length; i++) {
                    //Generamos el valor que agregaremos al JComboBox
                    valor = clientes[i].getId() + "- " + clientes[i].getPersona().getNombre() + " "
                            + clientes[i].getPersona().getApePaterno() + " " + clientes[i].getPersona().getApeMaterno();
                    //Agregamos el valor al JComboBox:
                    cmbClientes.addItem(valor);
                    arreglos[i]=clientes[i].getId()+".-"+clientes[i].getPersona().getNombre()+" "+clientes[i].getPersona().getApePaterno();
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrio un error" + e.toString(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void llenarCombo(String x) throws Exception {
        miCombo.removeAllElements();
        r = cv.cargarDatos(x);
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

    public void llenarCombo3() throws Exception {
        miCombo3.removeAllElements();
        r = cs.consultarCliente();
        r.beforeFirst();
        while (r.next()) {
            String cadena = r.getString(1) + "-" + r.getString(2) + " " + r.getString(3);
            miCombo3.addElement(cadena);

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
        btnVenta = new javax.swing.JButton();
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
        cmbClientes = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnactivo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCodeBar = new javax.swing.JTextField();
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Módulo  Ventas");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas"));

        btnVenta.setText("Crear Venta");
        btnVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaActionPerformed(evt);
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
        jLabel8.setText("idMedicamento");

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        btnok.setText("OK");
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
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
        jLabel10.setText("Cantidad");

        jTextField3.setText("jTextField3");
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

        cmbClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Ventas Activas");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        btnactivo.setText("Desactivar");
        btnactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactivoActionPerformed(evt);
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

        jLabel12.setText("Ventas en:");

        jTextField4.setEditable(false);
        jTextField4.setText("jTextField4");

        jTextField5.setText("jTextField5");

        jButton5.setText("Cambio");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setText("Pago:");

        jLabel14.setText("Total:");

        jLabel15.setText("Buscar:");

        txtCodeBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeBarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnVenta)
                                .addGap(27, 27, 27)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnConcluir)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel12)
                                .addGap(35, 35, 35)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodeBar)
                                .addGap(94, 94, 94)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnactivo)))
                        .addContainerGap(45, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnoki, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVenta)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConcluir)
                            .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jLabel12))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnok)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnoki)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(btnactivo)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtCodeBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jLabel6.setBackground(new java.awt.Color(0, 102, 51));
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("Venta No.");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Fecha:");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("jLabel3");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Hora:");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("jLabel5");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(206, 206, 206))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(63, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(7, 7, 7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(4, 4, 4)))))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(321, 321, 321)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(326, 326, 326)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(202, Short.MAX_VALUE)))
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
            Logger.getLogger(RegVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnokiActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try {
            llenarCombo(this.jTextField2.getText());
        } catch (Exception ex) {
            Logger.getLogger(RegVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
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

    private void btnVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaActionPerformed
        try {
            String xx = miCombo2.getSelectedItem().toString();
            String[] datos = xx.split("-");
            String xxx = miCombo3.getSelectedItem().toString();
            String[] dato = xxx.split("-");
            int idc = Integer.parseInt(dato[0]);
            int ids = Integer.parseInt(datos[0]);
            int ide = conlo.idEmpleado();
            v = new Venta();
            v.setFechaHora(this.jLabel3.getText() + " " + this.jLabel5.getText());
            v.setActivo((1));
            r = cv.obtenerFolio();
            r.first();
            this.jLabel7.setText(r.getString(1));
            JOptionPane.showMessageDialog(this, "Se agrego la venta ツ ");
        } catch (Exception ex) {
            Logger.getLogger(RegVentas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al hacer la venta ☹ ");

        }
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentaActionPerformed

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
            inicializarTabla2();
            mostrarDatos();
            JOptionPane.showMessageDialog(this, "Se agrego el detalle ツ");
            limpiarTabla();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: Ya no se encuentran existencias del producto que solicitaste, favor de hacer un pedido");
            Logger.getLogger(RegVentas.class.getName()).log(Level.SEVERE, null, ex);

        }

        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConcluirActionPerformed
    public void mostrarDatosEliminados() throws SQLException, Exception {

        r = cv.todasLasVentasEliminados();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable2.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
        }
    }
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            try {
                this.btnactivo.setText("Desactivar");
                inicializarTabla2();
                mostrarDatos();
            } catch (Exception ex) {

            }
        } else {
            try {
                this.btnactivo.setText("Activar");
                inicializarTabla2();
                mostrarDatosEliminados();
            } catch (Exception ex) {

            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactivoActionPerformed
        // TODO add your handling code here:
        status();
    }//GEN-LAST:event_btnactivoActionPerformed
    public void traerRegistro() throws Exception {
        if (id > 0) {
            r = cv.VentasUno(id);
            r.first();
            this.jLabel7.setText(r.getString(2));

        }
    }

    public void traerRegistrox2() throws Exception {

        if (id > 0) {
            r = cv.mt(id);
            r.first();
            this.jTextField4.setText(r.getString(1));
        }
    }
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        try {
            int fila = 0;
            fila = this.jTable2.getSelectedRow();
            id = Integer.parseInt(this.jTable2.getValueAt(fila, 0).toString());
            traerRegistro();
            traerRegistrox2();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable2MouseClicked
    public void x1() throws SQLException, Exception {
        r = cv.idventa1();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable2.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
        }
    }

    public void x2() throws SQLException, Exception {
        r = cv.idventa2();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable2.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
        }
    }

    public void x3() throws SQLException, Exception {
        r = cv.idventa3();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable2.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
        }
    }

    public void x4() throws SQLException, Exception {
        r = cv.idventa4();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable2.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable2.setModel(miTabla2);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            inicializarTabla2();
            x1();
        } catch (Exception ex) {
            Logger.getLogger(RegVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            inicializarTabla2();
            x2();
        } catch (Exception ex) {
            Logger.getLogger(RegVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            inicializarTabla2();
            x3();
        } catch (Exception ex) {
            Logger.getLogger(RegVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            inicializarTabla2();
            x4();
        } catch (Exception ex) {
            Logger.getLogger(RegVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        float a, b, c;
        a = Float.parseFloat(this.jTextField5.getText());
        b = Float.parseFloat(this.jTextField4.getText());
        c = a - b;
        JOptionPane.showMessageDialog(this, "Su cambio es $ " + c);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtCodeBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeBarActionPerformed
        buscar();
    }//GEN-LAST:event_txtCodeBarActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void buscar() {
        String codeBar = this.txtCodeBar.getText();
    }

    public void status() {
        if (btnactivo.getText().equals("Desactivar")) {
            try {

                cv.delete(idv);
                inicializarTabla2();
                mostrarDatos();

            } catch (Exception ex) {

            }
        }
        if (btnactivo.getText().equals("Activar")) {
            try {

                cv.activarVenta(idv);
                inicializarTabla2();
                mostrarDatosEliminados();

            } catch (Exception ex) {

            }
        }
    }

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

    public void limpiarTabla() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) (miTabla);
            int filas = miTabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    public void añadirFila() throws SQLException, Exception {
        miTabla = (DefaultTableModel) jTable1.getModel();
        Object[] datos = {id, ngeneral, ngenerico, pu, ff, concen, this.jTextField3.getText()};
        miTabla.addRow(datos);
        this.jTable1.setModel(miTabla);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConcluir;
    private javax.swing.JButton btnVenta;
    private javax.swing.JButton btnactivo;
    private javax.swing.JButton btnok;
    private javax.swing.JButton btnoki;
    private javax.swing.JComboBox<String> cmbClientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField txtCodeBar;
    // End of variables declaration//GEN-END:variables
}
