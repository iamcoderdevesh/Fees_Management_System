/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Fees_Management_System;

import java.awt.Color;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ASUS
 */
public class Edit_Course extends javax.swing.JFrame {

    /**
     * Creates new form Edit_Course
     */
    DefaultTableModel Model;
    String course_id, course_name, course_price;

    public Edit_Course() {
        initComponents();
        getTableRecords();
        text_courseid.setText(Integer.toString(setCourseId()));

        URL url = getClass().getResource("clg_1.jpg");
        ImageIcon imgicon = new ImageIcon(url);
        super.setIconImage(imgicon.getImage());
        setTitle("Fees-Management-System");
    }

    public int setCourseId() {
        int courseid = 100;
        try {
            //Connection con = (Connection) dbconnection.getConnection();
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select max(course_id) from course";
            PreparedStatement stmt = con.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                courseid = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
        return courseid + 1;
    }

    public void clearTableRecords() {
        Model = (DefaultTableModel) table_course.getModel();
        Model.setRowCount(0);
    }

    public void getTableRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select *from course";
            PreparedStatement stmt = con.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                course_id = rs.getString("course_id");
                course_name = rs.getString("course_name");
                course_price = rs.getString("course_price");

                Object[] obj = {course_id, course_name, course_price};
                Model = (DefaultTableModel) table_course.getModel();
                Model.addRow(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void setTableRecords() {
        int rowno = table_course.getSelectedRow();
        TableModel Model = table_course.getModel();
        text_courseid.setText(Model.getValueAt(rowno, 0).toString());
        text_coursename.setText(Model.getValueAt(rowno, 1).toString());
        text_courseprice.setText(Model.getValueAt(rowno, 2).toString());
    }

    public void insertRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "insert into course values(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            //java.sql.ResultSet rs = stmt.executeQuery();

            course_id = text_courseid.getText();
            course_name = text_coursename.getText();
            course_price = text_courseprice.getText();

            stmt.setString(1, course_id);
            stmt.setString(2, course_name);
            stmt.setString(3, course_price);

            int count = stmt.executeUpdate();

            if (count == 1) {
                JOptionPane.showMessageDialog(this, "Data Inserted successfully");
                clearTableRecords();
                getTableRecords();
            } else {
                JOptionPane.showMessageDialog(this, "Data Insertion Failed");
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
            String sql = "update course set course_name = ?,course_price = ? where course_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            //java.sql.ResultSet rs = stmt.executeQuery();

            course_id = text_courseid.getText();
            course_name = text_coursename.getText();
            course_price = text_courseprice.getText();

            stmt.setString(1, course_name);
            stmt.setString(2, course_price);
            stmt.setString(3, course_id);

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
            String sql = "delete from course where course_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            //java.sql.ResultSet rs = stmt.executeQuery();

            course_id = text_courseid.getText();

            stmt.setString(1, course_id);

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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        text_coursename = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        text_courseid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_course = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        text_courseprice = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
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
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Course Name:");

        text_coursename.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Course I.D : ");

        jButton1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jButton2.setText("Add");
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
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        text_courseid.setEditable(false);
        text_courseid.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_courseid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_courseidActionPerformed(evt);
            }
        });

        table_course.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        table_course.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course I.D", "Course Name", "Course Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_course.setRowHeight(25);
        table_course.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_courseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_course);
        if (table_course.getColumnModel().getColumnCount() > 0) {
            table_course.getColumnModel().getColumn(0).setPreferredWidth(100);
        }

        jLabel10.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Course Price:");

        text_courseprice.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Edit & View Course");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(text_courseprice, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(text_courseid, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(text_coursename, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator3)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(516, 516, 516)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1390, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(457, 457, 457))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(11, 11, 11)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(text_courseid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(text_courseprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(text_coursename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 1390, 1140));

        jPanel3.setBackground(new java.awt.Color(255, 153, 0));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Student_record, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(View_all_records, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Edit_course, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Home_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Search_record, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Go_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Reload, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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
                .addContainerGap(141, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 552, 1140));

        setSize(new java.awt.Dimension(1951, 1161));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void text_courseidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_courseidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_courseidActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void table_courseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_courseMouseClicked
        setTableRecords();        // TODO add your handling code here:
    }//GEN-LAST:event_table_courseMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        insertRecords();
        Edit_Course e = new Edit_Course();
        e.show();
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        updateRecords();
        Edit_Course e = new Edit_Course();
        e.show();
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteRecords();
        Edit_Course e = new Edit_Course();
        e.show();
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
        Edit_Course e = new Edit_Course();
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
            java.util.logging.Logger.getLogger(Edit_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edit_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edit_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edit_Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Edit_Course().setVisible(true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable table_course;
    private javax.swing.JTextField text_courseid;
    private javax.swing.JTextField text_coursename;
    private javax.swing.JTextField text_courseprice;
    // End of variables declaration//GEN-END:variables
}
