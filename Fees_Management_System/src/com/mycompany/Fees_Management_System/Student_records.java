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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;
import javax.swing.JTable;

/**
 *
 * @author ASUS
 */
public class Student_records extends javax.swing.JFrame {

    /**
     * Creates new form Student_records
     */
    String rollno, studentname, course, courseyear;

    DefaultTableModel Model;

    public Student_records() {

        initComponents();
        getTableRecords();
        setCourse();

        URL url = getClass().getResource("clg_1.jpg");
        ImageIcon imgicon = new ImageIcon(url);
        super.setIconImage(imgicon.getImage());
        setTitle("Fees-Management-System");
    }

    public void setCourse() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select course_name from course";
            PreparedStatement stmt = con.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                course = rs.getString("course_name");
                combo_course.addItem(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void clearTableRecords() {
        Model = (DefaultTableModel) table_records.getModel();
        Model.setRowCount(0);
    }

    public void getTableRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select *from student_records";
            PreparedStatement stmt = con.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rollno = rs.getString("Enrollment_no");
                studentname = rs.getString("Student_name");
                course = rs.getString("Course");
                courseyear = rs.getString("Course_year");

                Object[] obj = {rollno, studentname, course, courseyear};
                Model = (DefaultTableModel) table_records.getModel();
                Model.addRow(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void setTableRecords() {
        int rowno = table_records.getSelectedRow();
        TableModel Model = table_records.getModel();
        text_rollno.setText(Model.getValueAt(rowno, 0).toString());
        text_studentname.setText(Model.getValueAt(rowno, 1).toString());
        //s.setText(Model.getValueAt(rowno, 2).toString());
        combo_course.setSelectedItem(Model.getValueAt(rowno, 2).toString());
        text_courseyear.setText(Model.getValueAt(rowno, 3).toString());
    }

    public void insertRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "insert into student_records values(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            //java.sql.ResultSet rs = stmt.executeQuery();

            rollno = text_rollno.getText();
            studentname = text_studentname.getText();
            String s = studentname.substring(0, 1).toUpperCase() + studentname.substring(1);
            course = combo_course.getSelectedItem().toString();
            courseyear = text_courseyear.getText();

            stmt.setString(1, rollno);
            stmt.setString(2, s);
            stmt.setString(3, course);
            stmt.setString(4, courseyear);

            int count = stmt.executeUpdate();

            if (count == 1) {
                JOptionPane.showMessageDialog(this, "Data Inserted successfully");
                clearTableRecords();
                getTableRecords();
            } else {
                JOptionPane.showMessageDialog(this, "Data insertion Failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            e.printStackTrace();

        }
    }

    public void updateRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "update student_records set Enrollment_no = ?,Student_Name = ?,Course = ?,Course_Year = ? where Enrollment_no = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            //java.sql.ResultSet rs = stmt.executeQuery();

            rollno = text_rollno.getText();
            studentname = text_studentname.getText();
            String s = studentname.substring(0, 1).toUpperCase() + studentname.substring(1);
            course = combo_course.getSelectedItem().toString();
            courseyear = text_courseyear.getText();

            stmt.setString(1, rollno);
            stmt.setString(2, s);
            stmt.setString(3, course);
            stmt.setString(4, courseyear);
            stmt.setString(5, rollno);

            int count = stmt.executeUpdate();

            if (count == 1) {
                JOptionPane.showMessageDialog(this, "Data Updated successfully");
                clearTableRecords();
                getTableRecords();
            } else {
                JOptionPane.showMessageDialog(this, "Data Updation Failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            e.printStackTrace();

        }
    }

    public void deleteRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "delete from student_records where Enrollment_no = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            //java.sql.ResultSet rs = stmt.executeQuery();

            rollno = text_rollno.getText();

            stmt.setString(1, rollno);

            int count = stmt.executeUpdate();

            if (count == 1) {
                JOptionPane.showMessageDialog(this, "Data Deleted Successfully");
                clearTableRecords();
                getTableRecords();
            } else {
                JOptionPane.showMessageDialog(this, "Data Deletion Failed");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            e.printStackTrace();

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
        jScrollPane1 = new javax.swing.JScrollPane();
        table_records = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        text_rollno = new javax.swing.JTextField();
        text_studentname = new javax.swing.JTextField();
        text_courseyear = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        combo_course = new javax.swing.JComboBox<>();
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
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));

        table_records.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        table_records.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Enrollment No", "Student Name", "Course ", "Course Year"
            }
        ));
        table_records.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_recordsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_records);

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Student Records");

        jLabel10.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Student Name: ");

        jLabel11.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Course Year:");

        jLabel12.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Enrollment N0. ");

        jLabel13.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Course:");

        text_rollno.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_rollno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_rollnoActionPerformed(evt);
            }
        });

        text_studentname.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_studentname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_studentnameActionPerformed(evt);
            }
        });

        text_courseyear.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_courseyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_courseyearActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jButton2.setText("Update");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jButton3.setText("Add");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jButton4.setText("Delete");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        combo_course.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        combo_course.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_courseMouseClicked(evt);
            }
        });
        combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(514, 514, 514)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(text_rollno, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(text_studentname, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel13))
                    .addComponent(combo_course, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(text_courseyear, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
            .addComponent(jSeparator3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_courseyear)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(combo_course, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(text_rollno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(text_studentname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 1400, -1));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel14.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/update.png"))); // NOI18N
        jLabel14.setText("  Reload");

        javax.swing.GroupLayout ReloadLayout = new javax.swing.GroupLayout(Reload);
        Reload.setLayout(ReloadLayout);
        ReloadLayout.setHorizontalGroup(
            ReloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReloadLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ReloadLayout.setVerticalGroup(
            ReloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(View_all_records, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Edit_course, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Student_record, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Home_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Search_record, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Go_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Reload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(Home_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(Search_record, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(Edit_course, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(Student_record, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(View_all_records, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(Go_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Reload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 552, 1150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_courseActionPerformed

    private void combo_courseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_courseMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_courseMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        deleteRecords();    // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        insertRecords();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        updateRecords();             // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void text_courseyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_courseyearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_courseyearActionPerformed

    private void text_studentnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_studentnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_studentnameActionPerformed

    private void text_rollnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_rollnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_rollnoActionPerformed

    private void table_recordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_recordsMouseClicked
        setTableRecords();        // TODO add your handling code here:
    }//GEN-LAST:event_table_recordsMouseClicked

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
        Student_records e = new Student_records();
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
            java.util.logging.Logger.getLogger(Student_records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student_records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student_records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student_records.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student_records().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Edit_course;
    private javax.swing.JPanel Go_back;
    private javax.swing.JPanel Home_panel;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel Reload;
    private javax.swing.JPanel Search_record;
    private javax.swing.JPanel Student_record;
    private javax.swing.JPanel View_all_records;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable table_records;
    private javax.swing.JTextField text_courseyear;
    private javax.swing.JTextField text_rollno;
    private javax.swing.JTextField text_studentname;
    // End of variables declaration//GEN-END:variables
}
