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
public class RegVentasPrueba extends javax.swing.JPanel {
    DefaultTableModel miTabla = new DefaultTableModel();
    DefaultTableModel miTabla2 = new DefaultTableModel();
    Timer reloj;
    ResultSet r;
    ControladorVenta cv;
    String[] arreglos;
    ControladorProducto cp;
    ControladorSucursal cs;
    ControladorEmpleado ce;
    ControladorCliente cc;
    Venta v;
    DetalleVenta dv;
    DefaultComboBoxModel miCombo, miCombo2, miCombo3;
    ConexionMySQL conn;
    String ngeneral, ngenerico, ff, concen;
    int id = 0, canti = 0, idv;
    double pu = 0;
    ControllerLogin conlo;
    
    public RegVentasPrueba() throws Exception {
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
                fechaOut.setText(x1.format(fechaHor));
                horaOut.setText(x2.format(fechaHor));
            }
        });
        reloj.start();

        inicializarTabla();
        mostrarDatos();
        inicializarCombo();
        
        
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
    public void mostrarDatos() throws SQLException, Exception {
        r = cv.VentaTodos();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable1.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable1.setModel(miTabla2);
        }
    }
    public void inicializarCombo() {
        miCombo = new DefaultComboBoxModel();
        nomGeneralLista.setModel(miCombo);
        miCombo = (DefaultComboBoxModel) nomGeneralLista.getModel();
        miCombo.addElement("Medicamentos");
        
        /*miCombo3 = new DefaultComboBoxModel();
        this.jComboBox3.setModel(miCombo3);
        miCombo3 = (DefaultComboBoxModel) this.jComboBox3.getModel();
        miCombo3.addElement("Clientes");
         */
    }
    public void inicializarComboCl() throws Exception {
        Cliente[] clientes = null;
        ResultSet rs;
        int index;
        String valor = null;
        rs=cc.ClienteTodo();
        rs.last();
        index=rs.getRow();
        int i = 0;
        arreglos = new String[rs.getRow()];
        //Se hace el for:
        rs.beforeFirst();
            while(rs.next()){
                arreglos[i]=clientes[i].getId()+".-"+clientes[i].getPersona().getNombre()+" "+clientes[i].getPersona().getApePaterno();
                i++;
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
    public void asignarDatos() {
        try {
            r = cp.productosUno(Integer.parseInt(this.codBarrasIn.getText()));
            r.beforeFirst();
            while (r.next()) {
//                this.jTextField2.setText(r.getString(2));
                id = Integer.parseInt(r.getString(1));
                ngeneral = r.getString(2);
                ngenerico = r.getString(3);
                ff = r.getString(4);
                concen = r.getString(5);
                pu = Double.parseDouble(r.getString(7));
            }
            llenarCombo(ngeneral);
            cantidadIn.requestFocus();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void x1() throws SQLException, Exception {
        r = cv.idventa1();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable1.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable1.setModel(miTabla2);
        }
    }

    public void x2() throws SQLException, Exception {
        r = cv.idventa2();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable1.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable1.setModel(miTabla2);
        }
    }

    public void x3() throws SQLException, Exception {
        r = cv.idventa3();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable1.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable1.setModel(miTabla2);
        }
    }

    public void x4() throws SQLException, Exception {
        r = cv.idventa4();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable1.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable1.setModel(miTabla2);
        }
    }
    public void status() {
        if (btnActivo.getText().equals("Desactivar")) {
            try {

                cv.delete(idv);
                inicializarTabla();
                mostrarDatos();

            } catch (Exception ex) {

            }
        }
        if (btnActivo.getText().equals("Activar")) {
            try {

                cv.activarVenta(idv);
                inicializarTabla();
                mostrarDatosEliminados();

            } catch (Exception ex) {

            }
        }
    }
    public void mostrarDatosEliminados() throws SQLException, Exception {

        r = cv.todasLasVentasEliminados();
        r.beforeFirst();
        while (r.next()) {
            miTabla2 = (DefaultTableModel) jTable1.getModel();
            Object[] datos = {r.getString(1), r.getString(2), r.getString(3), r.getString(4),
                r.getString(5), r.getString(6), r.getString(7), r.getString(8)};
            miTabla2.addRow(datos);
            this.jTable1.setModel(miTabla2);
        }
    }
    public void traerRegistro() throws Exception {
        if (id > 0) {
            r = cv.VentasUno(id);
            r.first();
            numVentaout.setText("N° "+r.getString(2));

        }
    }
    public void traerRegistrox2() throws Exception {

        if (id > 0) {
            r = cv.mt(id);
            r.first();
            this.jLabel6.setText(r.getString(1));
        }
    }
    public void añadirFila() throws SQLException, Exception {
        miTabla = (DefaultTableModel) jTable1.getModel();
        Object[] datos = {id, ngeneral, ngenerico, pu, ff, concen, cantidadIn.getText()};
        miTabla.addRow(datos);
        this.jTable1.setModel(miTabla);
    }
    public static void agregarVenta(Venta vta) throws Exception {
        String sqlVenta = "INSERT INTO venta(fechaHora, activo, idCliente, idEmpleado, idSucursal)"
                + "VALUES(NOW(),1,?,?,?)";
        String sqlDetalleVenta = "INSERT INTO detalleVenta(idProducto, idVenta, cantidad, precioVenta)"
                + "VALUES(?,?,?,?)";

        ConexionMySQL connMySQL = new ConexionMySQL();
        java.sql.Connection conn = connMySQL.abrir();

        java.sql.PreparedStatement pstmt = null;

        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        //Aqui guadaremps el ID generado de la Venta:
        int idVentaGenerado = 0;

        // Establecemos la conexión en modo "Manual":
        conn.setAutoCommit(false);

        try {
            // Generamos un PreparedStatement para ejecutar la consulta
            // Y le indicamos que nos va a devolver el Id que se generó:
            pstmt = conn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            //Establecemos los valores que se necesitan en la consulta:
            pstmt.setInt(1, vta.getCliente().getId());
            pstmt.setInt(2, vta.getEmpleado().getId());
            pstmt.setInt(3, vta.getEmpleado().getSucursal().getId());
            //Ejecutamos la consulta:
            pstmt.executeUpdate();
            //Extraemos el resultado:
            rs = pstmt.getResultSet();
            //Extraemos del ID de venta generado:
            rs.first();//Nos movemos al primer registro
            idVentaGenerado = rs.getInt(1);

            //Cerramos el ResultSet y el PreparedStatement:
            rs.close();
            pstmt.close();

            //Generamos un nuevo PreparedStatement:
            pstmt = conn.prepareStatement(sqlDetalleVenta);
            //Establecemos los valores de cd detalle de venta
            //y lo guardamos en la BD:
            for (int i = 0; i < vta.getProducto().length; i++) {
                pstmt.setInt(1, vta.getProducto()[i].getId());
                pstmt.setInt(2, idVentaGenerado);
                pstmt.setInt(3, vta.getProducto()[i].getCantidad());
                pstmt.setDouble(4, vta.getProducto()[i].getPrecioUnitario());

                pstmt.executeUpdate();
            }
            //Como no hubo errores, persistimos los cambios de forma definitiva en la BD:
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        }
        // Regresamos la conexión al modo "Automático":
        conn.setAutoCommit(true);
        //Cerramos los objetos de conexión con la BD:
        pstmt.close();
        connMySQL.cerrar();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        codBarrasIn = new javax.swing.JTextField();
        nomGeneralLista = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cantidadIn = new javax.swing.JTextField();
        alertaOut = new javax.swing.JLabel();
        numVentaout = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        fechaOut = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        horaOut = new javax.swing.JLabel();
        btnActivo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Módulo Ventas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 48), new java.awt.Color(0, 102, 102))); // NOI18N

        codBarrasIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codBarrasInKeyPressed(evt);
            }
        });

        nomGeneralLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Cantidad:");

        cantidadIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantidadInKeyPressed(evt);
            }
        });

        alertaOut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        alertaOut.setForeground(new java.awt.Color(255, 0, 0));

        numVentaout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        numVentaout.setText("N°");

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setForeground(new java.awt.Color(51, 51, 51));
        jCheckBox1.setText("Activas");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jCheckBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCheckBox1KeyPressed(evt);
            }
        });

        jLabel2.setText("Fecha:");

        jLabel4.setText("Hora");

        btnActivo.setText("Desactivar");
        btnActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Buscar en:");

        jButton2.setText("Centro");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Centro Max");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Plaza Mayor");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Futurama");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setText("Total:");

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(94, Short.MAX_VALUE)
                        .addComponent(codBarrasIn, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(nomGeneralLista, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alertaOut, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cantidadIn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(356, 356, 356))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1209, Short.MAX_VALUE)
                .addComponent(numVentaout)
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(horaOut, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jButton2)
                        .addGap(41, 41, 41)
                        .addComponent(jButton3)
                        .addGap(39, 39, 39)
                        .addComponent(jButton4)
                        .addGap(43, 43, 43)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActivo))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1086, 1086, 1086)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaOut, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codBarrasIn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomGeneralLista, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(numVentaout))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(horaOut, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))))
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fechaOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(alertaOut, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cantidadIn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(btnActivo)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(32, 32, 32))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, 1340, 300));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 331, 1340, 329));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void codBarrasInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codBarrasInKeyPressed
        // TODO add your handling code here:
        if(codBarrasIn.getText() == "" && evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            try {
                inicializarComboCl();
                JOptionPane.showInputDialog(this, "Selecciona", "Titulo", JOptionPane.INFORMATION_MESSAGE, null, arreglos, arreglos[0]);
            } catch (Exception ex) {
                Logger.getLogger(RegVentasPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
           asignarDatos();
       }
    }//GEN-LAST:event_codBarrasInKeyPressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            inicializarTabla();
            x4();
        } catch (Exception ex) {
            Logger.getLogger(RegVentasPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            inicializarTabla();
            x3();
        } catch (Exception ex) {
            Logger.getLogger(RegVentasPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            inicializarTabla();
            x2();
        } catch (Exception ex) {
            Logger.getLogger(RegVentasPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            inicializarTabla();
            x1();
        } catch (Exception ex) {
            Logger.getLogger(RegVentasPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBox1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1KeyPressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox1.isSelected()) {
            try {
                this.btnActivo.setText("Desactivar");
                inicializarTabla();
                mostrarDatos();
            } catch (Exception ex) {

            }
        } else {
            try {
                this.btnActivo.setText("Activar");
                inicializarTabla();
                mostrarDatosEliminados();
            } catch (Exception ex) {

            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivoActionPerformed
        // TODO add your handling code here:
        status();
    }//GEN-LAST:event_btnActivoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        try {
            int fila = 0;
            fila = this.jTable1.getSelectedRow();
            id = Integer.parseInt(this.jTable1.getValueAt(fila, 0).toString());
            traerRegistro();
            traerRegistrox2();
        } catch (Exception ex) {
            Logger.getLogger(RegEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cantidadInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadInKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_F2) {
            v.setActivo(1);
            
            
        }
    }//GEN-LAST:event_cantidadInKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertaOut;
    private javax.swing.JButton btnActivo;
    private javax.swing.JTextField cantidadIn;
    private javax.swing.JTextField codBarrasIn;
    private javax.swing.JLabel fechaOut;
    private javax.swing.JLabel horaOut;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> nomGeneralLista;
    private javax.swing.JLabel numVentaout;
    // End of variables declaration//GEN-END:variables
}
