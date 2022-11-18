
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author hp
 */
public class Doctor extends javax.swing.JFrame {

    /**
     * Creates new form Patient
     */
    public Doctor() {
        initComponents();
    }
    int id;//userId 
    String uType;
    int newId;
    
    public Doctor(int id, String uType) throws InstantiationException, IllegalAccessException, SQLException {
        initComponents();
        this.id = id;
        this.uType = uType;
        newId = id;
        //JOptionPane.showMessageDialog(this, newId);
        Connect();
        autoIncrId();
        setInfo(newId);
        loadDepartment();
    }
    
    public class Department{
        String id;
        String dname;

        Department(String id, String dname) {
            this.dname = dname;
            this.id = id;
        }
        
        public String toString(){
            return dname;
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
        
        rs = s.executeQuery(" select count(doctor.did), did from doctor where userid = " + id +"");
        rs.next();
        rs.getInt(1);
        if(rs.getString(2)!=null){
            rs = s.executeQuery("select doctor.Did from doctor where userId = " + id + "");
            rs.next();
            txtDid.setText(rs.getString(1));
            jButton1.setVisible(false);
        }
        else{
            rs = s.executeQuery("select MAX(Did)from doctor");
            rs.next();
            rs.getString("MAX(Did)");

            if(rs.getString("MAX(Did)")==null){
                txtDid.setText("DC001");
            }
            else{
                long id = Long.parseLong(rs.getString("MAX(Did)").substring(2,rs.getString("MAX(Did)").length() ));
                id++;
                txtDid.setText("DC"+String.format("%03d", id));
            }
        }
    }
    
    public void loadDoc(int id){
        try {
            pst = conn.prepareStatement("SELECT Did, firstName,lastName,Qualification,Specialization,"
                    + "department.deptName,mobno,Address,fees,roomno "
                    + "FROM doctor JOIN department on doctor.department = department.DeptId"
                    + "where Did = " + id);
            rs = pst.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void setInfo(int newid){
        try {
            pst = conn.prepareStatement("SELECT * from doctor where userId = " + newid);
            rs = pst.executeQuery();
            while(rs.next()){
                txtDid.setText(rs.getString(1));
                txtFirstName.setText(rs.getString(2));
                txtLastName.setText(rs.getString(3));
                txtQualification.setText(rs.getString(4));
                txtSpecialization.setText(rs.getString(5));
                txtDepartment.setSelectedItem(rs.getString(6));
                txtMobno.setText(rs.getString(7));
                txtAddress.setText(rs.getString(8));
                txtFees.setText(rs.getString(9));
                txtRoom.setValue(Integer.parseInt(rs.getString(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
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
        txtFirstName1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtAge1 = new javax.swing.JLabel();
        txtQualification = new javax.swing.JTextField();
        txtLastName1 = new javax.swing.JLabel();
        txtBloodGroup1 = new javax.swing.JLabel();
        txtMobno1 = new javax.swing.JLabel();
        txtDoctor1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtAddress1 = new javax.swing.JLabel();
        txtGender1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        txtPid1 = new javax.swing.JLabel();
        txtDid = new javax.swing.JLabel();
        txtDepartment1 = new javax.swing.JLabel();
        txtDepartment = new javax.swing.JComboBox();
        txtMobno = new javax.swing.JTextField();
        txtSpecialization = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFees = new javax.swing.JTextField();
        txtRoom = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HOSPITAL");

        txtFirstName1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFirstName1.setForeground(new java.awt.Color(255, 255, 255));
        txtFirstName1.setText("First Name");

        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        txtLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLastNameActionPerformed(evt);
            }
        });

        txtAge1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAge1.setForeground(new java.awt.Color(255, 255, 255));
        txtAge1.setText("Qualification");

        txtLastName1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtLastName1.setForeground(new java.awt.Color(255, 255, 255));
        txtLastName1.setText("Last Name");

        txtBloodGroup1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtBloodGroup1.setForeground(new java.awt.Color(255, 255, 255));
        txtBloodGroup1.setText("Specialization");

        txtMobno1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMobno1.setForeground(new java.awt.Color(255, 255, 255));
        txtMobno1.setText("Mob No");

        txtDoctor1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDoctor1.setForeground(new java.awt.Color(255, 255, 255));
        txtDoctor1.setText("Room no");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtAddress1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAddress1.setForeground(new java.awt.Color(255, 255, 255));
        txtAddress1.setText("Address");

        txtGender1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtGender1.setForeground(new java.awt.Color(255, 255, 255));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setText("Exit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtPid1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPid1.setForeground(new java.awt.Color(255, 255, 255));
        txtPid1.setText("Doctor ID");

        txtDid.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtDid.setForeground(new java.awt.Color(255, 255, 255));

        txtDepartment1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDepartment1.setForeground(new java.awt.Color(255, 255, 255));
        txtDepartment1.setText("Fees");

        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });

        txtSpecialization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpecializationActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Department");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton2.setText("View your Info");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPid1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAge1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFirstName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDepartment1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtGender1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(txtMobno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFirstName)
                            .addComponent(txtQualification)
                            .addComponent(txtDid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMobno)
                            .addComponent(txtFees, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDoctor1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRoom))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLastName1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtBloodGroup1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                            .addComponent(txtSpecialization)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPid1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(txtDid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstName1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAge1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtQualification, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBloodGroup1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGender1)
                                .addComponent(txtAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtDepartment))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMobno, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(txtMobno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtSpecialization, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDoctor1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFees, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDepartment1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSpecializationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpecializationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpecializationActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       
        String Did = txtDid.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String qualification = txtQualification.getText();
        String specification = txtSpecialization.getText();
        String department = txtDepartment.getSelectedItem().toString();
        String mob = txtMobno.getText();
        String address = txtAddress.getText();
        String fees = txtFees.getText();
        String roomno = txtRoom.getValue().toString();
        
        
        JFrame frame = new JFrame("Update Doctor Information");
        if(JOptionPane.showConfirmDialog(frame, "Click yes to Update Information","Hospital Mangement System",
                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
           
            try {
               pst = conn.prepareStatement("update doctor set firstName = ?, lastName = ?, qualification = ?, specialization = ?, "
                       + "department = ?, mobNo = ?, address = ?, fees = ?, roomno = ? where userId = " + newId);
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, qualification);
                pst.setString(4, specification);
                pst.setString(5, department);
                pst.setString(6, mob);
                pst.setString(7, address);
                pst.setString(8, 	fees);
                pst.setString(9, roomno);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Information Updated Successfully");

            } catch (SQLException ex) {
                Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Request fail");
            }  
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String did = txtDid.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String qualification = txtQualification.getText();
        String specialization = txtSpecialization.getText();
        Department department = (Department) txtDepartment.getSelectedItem();
        String mob = txtMobno.getText();
        String address = txtAddress.getText();
        String fees = txtFees.getText();
        String roomno = txtRoom.getValue().toString();
        
        try {
            pst = conn.prepareStatement("Insert into doctor(Did, firstName, lastName, Qualification, "
                + "Specialization, department, mobno, Address, fees, roomno, userId)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, did);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, qualification);
            pst.setString(5, specialization );
            pst.setString(6, department.id);
            pst.setString(7, mob);
            pst.setString(8, address);
            pst.setString(9, fees);
            pst.setString(10, roomno);
            pst.setInt(11, newId);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Doctor Information Added Successfully");

            autoIncrId();
            txtFirstName.setText("");
            txtLastName.setText("");
            txtQualification.setText("");
            txtSpecialization.setText("");
            txtDepartment.setSelectedIndex(-1);
            txtMobno.setText("");
            txtAddress.setText("");
            txtFees.requestFocus();
            txtRoom.setValue(0);

        } catch (SQLException ex) {
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Request fail");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            ViewDoctor vd = new ViewDoctor(newId);
            vd.setVisible(true);
        } catch (InstantiationException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentActionPerformed

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
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Patient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Patient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JLabel txtAddress1;
    private javax.swing.JLabel txtAge1;
    private javax.swing.JLabel txtBloodGroup1;
    private javax.swing.JComboBox txtDepartment;
    private javax.swing.JLabel txtDepartment1;
    private javax.swing.JLabel txtDid;
    private javax.swing.JLabel txtDoctor1;
    private javax.swing.JTextField txtFees;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JLabel txtFirstName1;
    private javax.swing.JLabel txtGender1;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JLabel txtLastName1;
    private javax.swing.JTextField txtMobno;
    private javax.swing.JLabel txtMobno1;
    private javax.swing.JLabel txtPid1;
    private javax.swing.JTextField txtQualification;
    private javax.swing.JSpinner txtRoom;
    private javax.swing.JTextField txtSpecialization;
    // End of variables declaration//GEN-END:variables
}
