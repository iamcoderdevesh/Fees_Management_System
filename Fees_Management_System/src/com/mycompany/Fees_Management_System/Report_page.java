/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Fees_Management_System;

import java.awt.Color;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class Report_page extends javax.swing.JFrame {

    /**
     * Creates new form Report_page
     */
    public Report_page() {
        initComponents();
        setTableRecords();
        setCourse();
        getTotalStudent();
        getTotalCourse();
        getTotalFees();

        URL url = getClass().getResource("clg_1.jpg");
        ImageIcon imgicon = new ImageIcon(url);
        super.setIconImage(imgicon.getImage());
        setTitle("Fees-Management-System");
    }
    DefaultTableModel Model;

    public void getTotalStudent() {
        int count;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select count(Student_Name) from fees_record";
            PreparedStatement stmt = con.prepareStatement(sql);

            //stmt.setString(3,course);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
                String s = String.valueOf(count);
                lbl_student.setText(s);
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e);
            e.printStackTrace();
        }
    }

    public void getTotalCourse() {
        int count;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select count(course_name) from course";
            PreparedStatement stmt = con.prepareStatement(sql);

            //stmt.setString(3,course);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
                String s = String.valueOf(count);
                lbl_course.setText(s);
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e);
            e.printStackTrace();
        }
    }

    public void getTotalFees() {
        int count;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select sum(Total_Amount) from fees_record";
            PreparedStatement stmt = con.prepareStatement(sql);

            //stmt.setString(3,course);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
                String s = String.valueOf(count);
                lbl_total.setText(s);
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e);
            e.printStackTrace();
        }
    }

    public void setCourse() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select course_name from course";
            PreparedStatement stmt = con.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String course = rs.getString("course_name");
                combo_course.addItem(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void clearTable() {
        Model = (DefaultTableModel) table_records.getModel();
        Model.setRowCount(0);
    }

    public void searchTableRecords() {
        String course = combo_course.getSelectedItem().toString();
        SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
        String Date1 = dateformat.format(DateChooser1.getDate());
        String Date2 = dateformat.format(DateChooser2.getDate());
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select *from fees_record where Date between ? and ? and Course = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, Date1);
            stmt.setString(2, Date2);
            stmt.setString(3, course);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String receiptno = rs.getString("Receipt_No");
                String studentname = rs.getString("Student_Name");
                String Date = rs.getString("Date");
                String rollno = rs.getString("Enrollment_No");
                String paymentmode = rs.getString("Payment_Mode");
                String coursename = rs.getString("Course");
                String amount = rs.getString("Total_Amount");
                String remark = rs.getString("Remarks");

                Object[] obj = {receiptno, Date, studentname, rollno, coursename, amount, paymentmode, remark};

                Model = (DefaultTableModel) table_records.getModel();
                Model.addRow(obj);
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void setTableRecords() {
        //  String course = combo_course.getSelectedItem().toString();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select *from fees_record";
            PreparedStatement stmt = con.prepareStatement(sql);

            //stmt.setString(3,course);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String receiptno = rs.getString("Receipt_No");
                String studentname = rs.getString("Student_Name");
                String Date = rs.getString("Date");
                String rollno = rs.getString("Enrollment_No");
                String paymentmode = rs.getString("Payment_Mode");
                String coursename = rs.getString("Course");
                String amount = rs.getString("Total_Amount");
                String remark = rs.getString("Remarks");

                Object[] obj = {receiptno, Date, studentname, rollno, coursename, amount, paymentmode, remark};

                Model = (DefaultTableModel) table_records.getModel();
                Model.addRow(obj);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void printReport() {
        SimpleDateFormat Date_Format = new SimpleDateFormat("YYYY-MM-dd");
        String datefrom = Date_Format.format(DateChooser1.getDate());
        String dateto = Date_Format.format(DateChooser2.getDate());

        MessageFormat header = new MessageFormat("Report From " + datefrom + " To " + dateto);
        MessageFormat footer = new MessageFormat("page{0,number,integer}");
        try {
            table_records.print(JTable.PrintMode.FIT_WIDTH, header, footer);

        } catch (Exception e) {
            e.getMessage();
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lbl_student = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        DateChooser2 = new com.toedter.calendar.JDateChooser();
        DateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_records = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        combo_course = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Edit_course = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Student_record = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        View_all_records = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Logout = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Go_back = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Home_panel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Search_record = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        Reload = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel2.setText("Total Fees Colleted");

        lbl_total.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_total.setText("1");

        jLabel16.setFont(new java.awt.Font("Georgia", 1, 56)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/view all record.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 68, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(51, 51, 51))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel10.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel10.setText("Total Students");

        lbl_student.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_student.setText("1");

        jLabel14.setFont(new java.awt.Font("Georgia", 1, 56)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/new-user.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel14))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(lbl_student, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_student, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jLabel12.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel12.setText("Total Course Available");

        lbl_course.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_course.setText("1");

        jLabel15.setFont(new java.awt.Font("Georgia", 1, 56)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/edit2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(36, 36, 36))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel15))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(lbl_course, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_course, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jLabel17.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Select Course:");

        jLabel18.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("To:");

        jLabel19.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("From:");

        jButton1.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        table_records.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        table_records.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt No.", "Date", "Student Name", "Enrollment No.", "Course", "Amount", "Payment Mode", "Remark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_records.setGridColor(new java.awt.Color(0, 0, 0));
        table_records.setRowHeight(25);
        jScrollPane1.setViewportView(table_records);

        jLabel20.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Select Date:");

        combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Report");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(681, 681, 681)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(DateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel18))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(combo_course, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(DateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_course, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 0, 1440, 1100));

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));

        Edit_course.setBackground(new java.awt.Color(102, 102, 102));
        Edit_course.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        Edit_course.setForeground(new java.awt.Color(255, 255, 255));
        Edit_course.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Edit_courseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Edit_courseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Edit_courseMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/view all record.png"))); // NOI18N
        jLabel1.setText("Update Fees");

        javax.swing.GroupLayout Edit_courseLayout = new javax.swing.GroupLayout(Edit_course);
        Edit_course.setLayout(Edit_courseLayout);
        Edit_courseLayout.setHorizontalGroup(
            Edit_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_courseLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Edit_courseLayout.setVerticalGroup(
            Edit_courseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_courseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        Student_record.setBackground(new java.awt.Color(102, 102, 102));
        Student_record.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        Student_record.setForeground(new java.awt.Color(255, 255, 255));
        Student_record.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Student_recordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Student_recordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Student_recordMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/new-user.png"))); // NOI18N
        jLabel4.setText("Student Records");

        javax.swing.GroupLayout Student_recordLayout = new javax.swing.GroupLayout(Student_record);
        Student_record.setLayout(Student_recordLayout);
        Student_recordLayout.setHorizontalGroup(
            Student_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Student_recordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        Student_recordLayout.setVerticalGroup(
            Student_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Student_recordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        View_all_records.setBackground(new java.awt.Color(102, 102, 102));
        View_all_records.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        View_all_records.setForeground(new java.awt.Color(255, 255, 255));
        View_all_records.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                View_all_recordsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                View_all_recordsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                View_all_recordsMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/list_1.png"))); // NOI18N
        jLabel5.setText("View All Records");

        javax.swing.GroupLayout View_all_recordsLayout = new javax.swing.GroupLayout(View_all_records);
        View_all_records.setLayout(View_all_recordsLayout);
        View_all_recordsLayout.setHorizontalGroup(
            View_all_recordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(View_all_recordsLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        View_all_recordsLayout.setVerticalGroup(
            View_all_recordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(View_all_recordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );

        Logout.setBackground(new java.awt.Color(102, 102, 102));
        Logout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LogoutMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/logout.png"))); // NOI18N
        jLabel6.setText("Logout");

        javax.swing.GroupLayout LogoutLayout = new javax.swing.GroupLayout(Logout);
        Logout.setLayout(LogoutLayout);
        LogoutLayout.setHorizontalGroup(
            LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogoutLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LogoutLayout.setVerticalGroup(
            LogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        Go_back.setBackground(new java.awt.Color(102, 102, 102));
        Go_back.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        Go_back.setForeground(new java.awt.Color(255, 255, 255));
        Go_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Go_backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Go_backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Go_backMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/left-arrow.png"))); // NOI18N
        jLabel7.setText("Back");

        javax.swing.GroupLayout Go_backLayout = new javax.swing.GroupLayout(Go_back);
        Go_back.setLayout(Go_backLayout);
        Go_backLayout.setHorizontalGroup(
            Go_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Go_backLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Go_backLayout.setVerticalGroup(
            Go_backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Go_backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Home_panel.setBackground(new java.awt.Color(102, 102, 102));
        Home_panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        Home_panel.setForeground(new java.awt.Color(255, 255, 255));
        Home_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Home_panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Home_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Home_panelMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/home.png"))); // NOI18N
        jLabel8.setText("Home");

        javax.swing.GroupLayout Home_panelLayout = new javax.swing.GroupLayout(Home_panel);
        Home_panel.setLayout(Home_panelLayout);
        Home_panelLayout.setHorizontalGroup(
            Home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home_panelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Home_panelLayout.setVerticalGroup(
            Home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        Search_record.setBackground(new java.awt.Color(102, 102, 102));
        Search_record.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        Search_record.setForeground(new java.awt.Color(255, 255, 255));
        Search_record.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Search_recordMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Search_recordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Search_recordMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/search2.png"))); // NOI18N
        jLabel9.setText("Search Record");

        javax.swing.GroupLayout Search_recordLayout = new javax.swing.GroupLayout(Search_record);
        Search_record.setLayout(Search_recordLayout);
        Search_recordLayout.setHorizontalGroup(
            Search_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Search_recordLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(34, 34, 34))
        );
        Search_recordLayout.setVerticalGroup(
            Search_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Search_recordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        Reload.setBackground(new java.awt.Color(102, 102, 102));
        Reload.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        Reload.setForeground(new java.awt.Color(255, 255, 255));
        Reload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReloadMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReloadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReloadMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/update.png"))); // NOI18N
        jLabel11.setText("  Reload");

        javax.swing.GroupLayout ReloadLayout = new javax.swing.GroupLayout(Reload);
        Reload.setLayout(ReloadLayout);
        ReloadLayout.setHorizontalGroup(
            ReloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReloadLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ReloadLayout.setVerticalGroup(
            ReloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Student_record, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(View_all_records, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Edit_course, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Home_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Search_record, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Go_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Reload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(Home_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(Search_record, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(Edit_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(Student_record, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(View_all_records, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(Go_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(Reload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 552, 1100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearTable();
        searchTableRecords();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        printReport();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Edit_courseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Edit_courseMouseClicked
        Update_fees v = new Update_fees();
        v.show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_Edit_courseMouseClicked

    private void Edit_courseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Edit_courseMouseEntered
        Color clr = new Color(0, 204, 204);
        Edit_course.setBackground(clr); // TODO add your handling code here:
    }//GEN-LAST:event_Edit_courseMouseEntered

    private void Edit_courseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Edit_courseMouseExited
        Color clr = new Color(102, 102, 102);
        Edit_course.setBackground(clr);  // TODO add your handling code here:
    }//GEN-LAST:event_Edit_courseMouseExited

    private void Student_recordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Student_recordMouseClicked
        Student_records s = new Student_records();
        s.show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_Student_recordMouseClicked

    private void Student_recordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Student_recordMouseEntered
        Color clr = new Color(0, 204, 204);
        Student_record.setBackground(clr); // TODO add your handling code here:
    }//GEN-LAST:event_Student_recordMouseEntered

    private void Student_recordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Student_recordMouseExited
        Color clr = new Color(102, 102, 102);
        Student_record.setBackground(clr);// TODO add your handling code here:
    }//GEN-LAST:event_Student_recordMouseExited

    private void View_all_recordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_View_all_recordsMouseClicked
        View_all_records l = new View_all_records();
        l.show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_View_all_recordsMouseClicked

    private void View_all_recordsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_View_all_recordsMouseEntered
        Color clr = new Color(0, 204, 204);
        View_all_records.setBackground(clr);        // TODO add your handling code here:
    }//GEN-LAST:event_View_all_recordsMouseEntered

    private void View_all_recordsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_View_all_recordsMouseExited
        Color clr = new Color(102, 102, 102);
        View_all_records.setBackground(clr); // TODO add your handling code here:
    }//GEN-LAST:event_View_all_recordsMouseExited

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        login l = new login();
        l.show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_LogoutMouseClicked

    private void LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseEntered
        Color clr = new Color(0, 204, 204);
        Logout.setBackground(clr);// TODO add your handling code here:
    }//GEN-LAST:event_LogoutMouseEntered

    private void LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseExited
        Color clr = new Color(102, 102, 102);
        Logout.setBackground(clr); // TODO add your handling code here:
    }//GEN-LAST:event_LogoutMouseExited

    private void Go_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Go_backMouseClicked
        Home_page h = new Home_page();
        h.show();
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_Go_backMouseClicked

    private void Go_backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Go_backMouseEntered
        Color clr = new Color(0, 204, 204);
        Go_back.setBackground(clr);       // TODO add your handling code here:
    }//GEN-LAST:event_Go_backMouseEntered

    private void Go_backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Go_backMouseExited
        Color clr = new Color(102, 102, 102);
        Go_back.setBackground(clr); // TODO add your handling code here:
    }//GEN-LAST:event_Go_backMouseExited

    private void Home_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_panelMouseClicked
        Home_page h = new Home_page();
        h.show();
        this.dispose();      // TODO add your handling code here:
    }//GEN-LAST:event_Home_panelMouseClicked

    private void Home_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_panelMouseEntered
        Color clr = new Color(0, 204, 204);
        Home_panel.setBackground(clr);        // TODO add your handling code here:
    }//GEN-LAST:event_Home_panelMouseEntered

    private void Home_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_panelMouseExited
        Color clr = new Color(102, 102, 102);
        Home_panel.setBackground(clr);         // TODO add your handling code here:
    }//GEN-LAST:event_Home_panelMouseExited

    private void Search_recordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_recordMouseClicked
        Search_record s = new Search_record();
        s.show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_Search_recordMouseClicked

    private void Search_recordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_recordMouseEntered
        Color clr = new Color(0, 204, 204);
        Search_record.setBackground(clr);         // TODO add your handling code here:
    }//GEN-LAST:event_Search_recordMouseEntered

    private void Search_recordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search_recordMouseExited
        Color clr = new Color(102, 102, 102);
        Search_record.setBackground(clr);  // TODO add your handling code here:
    }//GEN-LAST:event_Search_recordMouseExited

    private void ReloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReloadMouseClicked
        Report_page e = new Report_page();
        e.show();
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_ReloadMouseClicked

    private void ReloadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReloadMouseEntered
        Color clr = new Color(0, 204, 204);
        Reload.setBackground(clr);          // TODO add your handling code here:
    }//GEN-LAST:event_ReloadMouseEntered

    private void ReloadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReloadMouseExited
        Color clr = new Color(102, 102, 102);
        Reload.setBackground(clr);     // TODO add your handling code here:
    }//GEN-LAST:event_ReloadMouseExited

    private void combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_courseActionPerformed

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
            java.util.logging.Logger.getLogger(Report_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Report_page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooser1;
    private com.toedter.calendar.JDateChooser DateChooser2;
    private javax.swing.JPanel Edit_course;
    private javax.swing.JPanel Go_back;
    private javax.swing.JPanel Home_panel;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel Reload;
    private javax.swing.JPanel Search_record;
    private javax.swing.JPanel Student_record;
    private javax.swing.JPanel View_all_records;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_student;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JTable table_records;
    // End of variables declaration//GEN-END:variables
}
