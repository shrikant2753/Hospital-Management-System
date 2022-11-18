
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author hp
 */
public class AppointmentQueue extends javax.swing.JFrame {

    /**
     * Creates new form AppointmentQueue
     * @throws java.sql.SQLException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public AppointmentQueue() throws SQLException, InstantiationException, IllegalAccessException {
        initComponents();
        Connect();
        autoIncrId();
        loadDoctor();
        loadPatient();
        //AutocompleteDecore.decore(txtpatient);
        loadDepartment();
        AppointmentTable();
    }
    
    
    public class Doctor{
        String id;
        String fname;
        String lname;

        Doctor(String id, String fname, String lname) {
            this.fname = fname;
            this.id = id;
            this.lname = lname;
        }
        
        public String toString(){
            String name = id + " " + fname + " " + lname;
            return name;
        }
    }
    
    public void loadDoctor(){
        try {
            pst = conn.prepareStatement("Select * from doctor");
            rs = pst.executeQuery();
            txtDoctor.removeAll();
            
            while(rs.next()){
                String id = rs.getString(1);
                String fname = rs.getString("firstName");
                String lname = rs.getString("lastName");
                txtDoctor.addItem(new Doctor(id, fname, lname));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public class Patient{
        String id;
        String fname;
        String lname;
        
        Patient(String id, String fname, String lname) {
            this.fname = fname;
            this.id = id;
            this.lname = lname;
        }
        
        public String toString(){
            String name = id + " " + fname + " " + lname;
            return name;
        }
    }
    
    public void loadPatient(){
        try {
            pst = conn.prepareStatement("Select * from patient");
            rs = pst.executeQuery();
            txtPatient.removeAll();
            
            while(rs.next()){
                String id = rs.getString(1);
                String fname = rs.getString("firstName");
                String lname = rs.getString("lastName");
                txtPatient.addItem(new Patient(id, fname, lname));
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class Department{
        String id;
        String dname;

        Department(String id, String dname) {
            this.dname = dname;
            this.id = id;
        }
        
        public String toString(){
            return id + " " + dname;
        }
    }
    
    public void loadDepartment(){
        try {
            pst = conn.prepareStatement("SELECT * FROM department");
            rs = pst.executeQuery();
            txtDepartment.removeAll();
            
            while(rs.next()){
                String id = rs.getString("DeptId");
                String dname = rs.getString("deptName");
                txtDepartment.addItem(new Department(id, dname));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    static final String DB_URL = "jdbc:mysql://localhost:3306/hospitalmanagementsystem";
    static final String USER = "root";
    static final String PASS = "";
    String appointmentNo;
    
    public void Connect() throws InstantiationException, IllegalAccessException{
        try {
            //Class.forName ("com.mysql.cj.jdbc.Driver").newInstance();
            Class.forName("com.mysql.cj.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL, USER, PASS);
             System.out.print("Data base connection establish");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Cannot connect to database server"+ ex);
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void autoIncrId() throws SQLException{
        java.sql.Statement s =  conn.createStatement();
        rs = s.executeQuery("select MAX(appointmentId)from appointment");
        rs.next();
        rs.getString("MAX(appointmentId)");
        
        if(rs.getString("MAX(appointmentId)")==null){
            txtAppointmentno.setText("CH001");
        }
        else{
            long id = Long.parseLong(rs.getString("MAX(appointmentId)").substring(2,rs.getString("MAX(appointmentId)").length() ));
            id++;
            txtAppointmentno.setText("CH"+String.format("%03d", id));
        }   
    }
    
    
    public void AppointmentTable(){
        try {
            pst = conn.prepareStatement("SELECT appointmentId, "
                    + "patient.firstName, patient.lastName, "
                    + "doctor.firstName, doctor.lastName, "
                    + "department.deptName, appointment.date "
                    + "FROM appointment JOIN patient on appointment.patientName = patient.Pid "
                    + "JOIN doctor on appointment.doctorName = doctor.Did "
                    + "JOIN department on appointment.department = department.DeptId");
            rs = pst.executeQuery();
            java.sql.ResultSetMetaData rsm = rs.getMetaData();
            int c = rsm.getColumnCount();
            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);
            
            while(rs.next()){
                Vector v = new Vector();
                
                for(int i=0; i<c; i++){
                    v.add(rs.getString(1));
                    v.add(rs.getString(2)+ " " + rs.getString(3));
                    v.add(rs.getString(4)+ " " + rs.getString(5));
                    v.add(rs.getString(6));
                    v.add(rs.getString(7));
                }
                df.addRow(v);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(ViewPatient.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAppointmentno = new javax.swing.JLabel();
        txtDepartment = new javax.swing.JComboBox();
        txtDoctor = new javax.swing.JComboBox();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtPatient = new javax.swing.JComboBox();
        clearAppointmentData = new javax.swing.JButton();
        createAppointment = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ExitPage = new javax.swing.JButton();
        deleteAppointment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(238, 252, 242));

        jLabel1.setText("Appointment no");

        jLabel2.setText("Patient Name");

        jLabel3.setText("Department");

        jLabel4.setText("Doctor Name");

        jLabel5.setText("Date");

        txtAppointmentno.setText("jLabel6");

        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });

        txtPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPatientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAppointmentno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDoctor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAppointmentno, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPatient, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        clearAppointmentData.setText("Clear");
        clearAppointmentData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAppointmentDataActionPerformed(evt);
            }
        });

        createAppointment.setText("Create ");
        createAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAppointmentActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Appointment no", "Patient Name", "Doctor Name", "Department", "Date"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        ExitPage.setText("Exit");
        ExitPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitPageActionPerformed(evt);
            }
        });

        deleteAppointment.setText("Cancle");
        deleteAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteAppointmentMouseClicked(evt);
            }
        });
        deleteAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(clearAppointmentData, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136)
                        .addComponent(createAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)
                        .addComponent(ExitPage, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearAppointmentData, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitPage, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void createAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAppointmentActionPerformed
        // TODO add your handling code here:
        String Aid = txtAppointmentno.getText();
        Patient patientName = (Patient) txtPatient.getSelectedItem();
        Department department = (Department) txtDepartment.getSelectedItem();
        Doctor doctorName = (Doctor) txtDoctor.getSelectedItem();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(txtDate.getDate());
        
        try {
            pst = conn.prepareStatement("Insert into appointment(appointmentId, patientName, department, doctorName, date)"
                + "values (?, ?, ?, ?, ?)");
            pst.setString(1, Aid);
            pst.setString(2, patientName.id);
            pst.setString(3, department.id);
            pst.setString(4, doctorName.id);
            pst.setString(5, date);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Appointment Schedule");

            autoIncrId();
            txtPatient.setSelectedIndex(-1);
            txtDepartment.setSelectedIndex(-1);
            txtDoctor.setSelectedIndex(-1);
            
            AppointmentTable();

        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Request fail");
        }
    }//GEN-LAST:event_createAppointmentActionPerformed

    private void clearAppointmentDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAppointmentDataActionPerformed
        // TODO add your handling code here:
            txtPatient.setSelectedIndex(-1);
            txtDepartment.setSelectedIndex(-1);
            txtDoctor.setSelectedIndex(0);
            createAppointment.setEnabled(true);
    }//GEN-LAST:event_clearAppointmentDataActionPerformed

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentActionPerformed

    private void ExitPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitPageActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_ExitPageActionPerformed

    private void deleteAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteAppointmentMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_deleteAppointmentMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        
        appointmentNo = d.getValueAt(selectIndex, 0).toString();
       // JOptionPane.showMessageDialog(this, appointmentNo);
       createAppointment.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void deleteAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAppointmentActionPerformed
       JFrame frame = new JFrame("Delete Appointment");
        if(JOptionPane.showConfirmDialog(frame, "Are you sure?","Hospital Mangement System",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
            try {
                // TODO add your handling code here:

                pst = conn.prepareStatement("Delete from appointment where appointmentId = ?");
                pst.setString(1, appointmentNo);
                pst.execute();

                JOptionPane.showMessageDialog(this, "AppointmentCancel");
                AppointmentTable();
                createAppointment.setEnabled(true);

            } catch (SQLException ex) {
                Logger.getLogger(AppointmentQueue.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Request Fail");
            }
        }
    }//GEN-LAST:event_deleteAppointmentActionPerformed

    private void txtPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPatientActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPatientActionPerformed

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
            java.util.logging.Logger.getLogger(AppointmentQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppointmentQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppointmentQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppointmentQueue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() {
                try {
                    new AppointmentQueue().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AppointmentQueue.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(AppointmentQueue.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AppointmentQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitPage;
    private javax.swing.JButton clearAppointmentData;
    private javax.swing.JButton createAppointment;
    private javax.swing.JButton deleteAppointment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtAppointmentno;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JComboBox txtDepartment;
    private javax.swing.JComboBox txtDoctor;
    private javax.swing.JComboBox txtPatient;
    // End of variables declaration//GEN-END:variables
}
