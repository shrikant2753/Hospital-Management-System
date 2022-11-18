
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ViewDoctor extends javax.swing.JFrame {

    /**
     * Creates new form ViewPatient
     */
    public ViewDoctor() throws SQLException {
        initComponents();
        try {
            Connect();
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewPatient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        DoctorTable();   
    }
    int userId;
    ViewDoctor(int userId) throws InstantiationException, IllegalAccessException{
        initComponents();
        this.userId = userId;
        Connect();
        System.out.println(userId);
        DoctorTable(userId);
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
    
    
    public void DoctorTable(){
        try {
            pst = conn.prepareStatement("SELECT Did, firstName,lastName,Qualification,Specialization,"
                    + "department.deptName,mobno,Address,fees,roomno "
                    + "FROM doctor JOIN department on doctor.department = department.DeptId");
            rs = pst.executeQuery();
            java.sql.ResultSetMetaData rsm = rs.getMetaData();
            int c = rsm.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);
            
            while(rs.next()){
                Vector v = new Vector();
                
                for(int i=0; i<c; i++){
                    v.add(rs.getString(1));
                    v.add(rs.getString(2));
                    v.add(rs.getString(3));
                    v.add(rs.getString(4));
                    v.add(rs.getString(5));
                    v.add(rs.getString(6));
                    v.add(rs.getString(7));
                    v.add(rs.getString(8));
                    v.add(rs.getString(9));
                    v.add(rs.getString(10));
                    //v.add(rs.getString("userId"));
                }
                df.addRow(v);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(ViewPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void DoctorTable(int userId){
        try {
            pst = conn.prepareStatement("Select * from Doctor where userId=?");
            pst.setInt(1, userId);
            rs = pst.executeQuery();
            java.sql.ResultSetMetaData rsm = rs.getMetaData();
            int c = rsm.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);
            
            while(rs.next()){
                Vector v = new Vector();
                
                for(int i=0; i<c; i++){
                    v.add(rs.getString("Did"));
                    v.add(rs.getString("firstName"));
                    v.add(rs.getString("lastName"));
                    v.add(rs.getString("Qualification"));
                    v.add(rs.getString("Specialization"));
                    v.add(rs.getString("Department"));
                    v.add(rs.getString("mobno"));
                    v.add(rs.getString("address"));
                    v.add(rs.getString("fees"));
                    v.add(rs.getString("roomno"));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DId", "First Name", "Last Name", "Qualification", "Specialization", "Department", "Mobile No", "Address", "Fees", "Room No"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1242, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
//        DefaultTableModel d1 = (DefaultTableModel) jTable1.getModel();
//        int selectIndex = jTable1.getSelectedRow();
//        
//        txtPid.setText(d1.getValueAt(selectIndex, 0).toString());
//        txtFirstName.setText(d1.getValueAt(selectIndex, 1).toString());
//        txtLastName.setText(d1.getValueAt(selectIndex, 2).toString());
//        txtAge.setText(d1.getValueAt(selectIndex, 3).toString());
//        //txtBloodGroup.setSelectedIndex(-1);
//        //txtGender.setSelectedItem();
//        txtMobno.setText(d1.getValueAt(selectIndex, 6).toString());
//        txtAddress.setText(d1.getValueAt(selectIndex, 7).toString());
//        //txtDepartment.setSelectedIndex(-1);
//        //txtDoctor.setSelectedIndex(-1);
//        
//        jButton1.setEnabled(false);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewPatient().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewPatient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    void setVisible() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
