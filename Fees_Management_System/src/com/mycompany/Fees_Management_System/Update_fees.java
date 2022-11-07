/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Fees_Management_System;

import java.awt.Color;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import javax.swing.ImageIcon;

/**
 *
 * @author ASUS
 */
public class Update_fees extends javax.swing.JFrame {

    /**
     * Creates new form Update_fees
     */
    String student_name, course, courseyear;
    int i;
    DefaultTableModel Model;
    ResultSet rs;

    public Update_fees() {
        initComponents();
        displaydefaultcash();
        getDate();
        setTableRecords();
        setCourse();

        text_receiptno.setText(Integer.toString(getReceiptNo()));
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

    public void getDate() {
        String pattern = "dd-MM-YYYY";
        DateFormat d = new SimpleDateFormat(pattern);
        Date date = Calendar.getInstance().getTime();
        String s = d.format(date);
        text_date.setText(s);
    }

    public void displaydefaultcash() {
        lbl_bankname.setVisible(false);
        lbl_cardno.setVisible(false);
        lbl_chequeno.setVisible(false);
        lbl_transactionid.setVisible(false);

        text_bank.setVisible(false);
        text_transactionid.setVisible(false);
        text_chequeno.setVisible(false);
        text_cardno.setVisible(false);
    }

    public void displayPaymentMethod() {

        if (combo_payment.getSelectedIndex() == 0) {
            lbl_bankname.setVisible(false);
            lbl_chequeno.setVisible(false);
            lbl_cardno.setVisible(false);
            lbl_transactionid.setVisible(false);

            text_transactionid.setVisible(false);
            text_bank.setVisible(false);
            text_chequeno.setVisible(false);
            text_cardno.setVisible(false);
        } else if (combo_payment.getSelectedIndex() == 1) {
            lbl_bankname.setVisible(true);
            lbl_transactionid.setVisible(true);
            lbl_cardno.setVisible(false);

            text_bank.setVisible(true);
            text_transactionid.setVisible(true);
            text_cardno.setVisible(false);

        } else if (combo_payment.getSelectedIndex() == 2) {
            lbl_bankname.setVisible(true);
            lbl_chequeno.setVisible(true);
            lbl_cardno.setVisible(false);
            lbl_transactionid.setVisible(false);

            text_bank.setVisible(true);
            text_chequeno.setVisible(true);
            text_cardno.setVisible(false);
            text_transactionid.setVisible(false);

        } else if (combo_payment.getSelectedIndex() == 3) {
            lbl_bankname.setVisible(true);
            lbl_cardno.setVisible(true);
            lbl_chequeno.setVisible(false);
            lbl_transactionid.setVisible(false);

            text_bank.setVisible(true);
            text_cardno.setVisible(true);
            text_chequeno.setVisible(false);
            text_transactionid.setVisible(false);

        } else {
            lbl_bankname.setVisible(false);
            lbl_chequeno.setVisible(false);
            lbl_cardno.setVisible(false);
            lbl_transactionid.setVisible(false);

            text_transactionid.setVisible(false);
            text_bank.setVisible(false);
            text_chequeno.setVisible(false);
            text_cardno.setVisible(false);
        }

    }

    public int validation() {

        if (text_receiptno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Name");
            return 1;
        } else if (text_rollno.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Enrollment No");
            return 1;
        } else if (combo_payment.getSelectedItem().toString().equalsIgnoreCase("Online/Bank")) {
            if (text_transactionid.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Transaction ID");
                return 1;
            }
            if (text_bank.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                return 1;
            }
        } else if (combo_payment.getSelectedItem().toString().equalsIgnoreCase("Cheque")) {
            if (text_chequeno.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Cheque No");
                return 1;
            }
            if (text_bank.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                return 1;
            }
        } else if (combo_payment.getSelectedItem().toString().equalsIgnoreCase("Card")) {
            if (text_bank.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Bank Name");
                return 1;
            }
            if (text_cardno.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please Enter Your Card Number");
                return 1;
            }
        } else if (text_receivedfrom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Fill Received From");
            return 1;
        } else if (text_1year.getText().equals("") || text_1year.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Please Enter Payment Year From");
            return 1;
        } else if (text_2year.getText().equals("") || text_2year.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Please Enter Payment Year To");
            return 1;
        } else if (text_1stamount.getText().equals("") || text_1stamount.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Please Enter Amount in Numbers");
            return 1;
        }

        return 0;

    }

    public void setAmount() {

        float amount = Float.parseFloat(text_1stamount.getText());
        int gst = (int) (amount * 0.18);
        String s = String.valueOf(gst);
        text_gst.setText(s);

        int total = (int) (amount + gst);
        String s1 = String.valueOf(total);
        text_totalamount.setText(s1);

        text_totalwords.setText(NumberToWordsConverter.convert((int) total) + " Only");
    }

    public int getReceiptNo() {
        int receipt = 0;
        try {
            //Connection con = (Connection) dbconnection.getConnection();
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select max(Receipt_no) from fees_record";
            PreparedStatement stmt = con.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery();

            if (rs.next() == true) {
                receipt = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
        return receipt + 1;
    }

    public int getStudentRecord() {
        try {
            //Connection con = (Connection) dbconnection.getConnection();
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select *from fees_record where Student_Name = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            String Studentname = text_student.getText();
            String s = Studentname.substring(0, 1).toUpperCase() + Studentname.substring(1);

            stmt.setString(1, s);
            java.sql.ResultSet rs = stmt.executeQuery();

            String rollno = null, course, courseyear = null, receipt, studentname;

            while (rs.next()) {
                studentname = rs.getString("Student_Name");
                receipt = rs.getString("Receipt_No");
                rollno = rs.getString("Enrollment_no");
                course = rs.getString("Course");
                courseyear = rs.getString("Course_year");

                text_student.setText(studentname);
                text_receiptno.setText(receipt);
                text_rollno.setText(rollno);
                combo_course.setSelectedItem(course);
                //text_course.setText(course);
                text_courseyear.setText(courseyear);
                return 0;

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);

        }
        return 1;
    }

    public void clearTable() {
        Model = (DefaultTableModel) table_records.getModel();
        Model.setRowCount(0);
    }

    public void setTableRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select *from student_records";
            PreparedStatement stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void diplayTableRecords() throws SQLException {
        while (rs.next()) {
            student_name = rs.getString("Student_Name");
            String rollno = rs.getString("Enrollment_no");
            //course = rs.getString("course");
            courseyear = rs.getString("Course_year");

            Object[] obj = {student_name, rollno, course, courseyear};
            Model = (DefaultTableModel) table_records.getModel();
            Model.addRow(obj);

        }
    }

    public void searchRecords(String s) {
        Model = (DefaultTableModel) table_records.getModel();
        TableRowSorter<DefaultTableModel> t = new TableRowSorter<>(Model);
        table_records.setRowSorter(t);
        t.setRowFilter(RowFilter.regexFilter(s));

    }

    public void getTotalAmount() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select Total_Amount from fees_record";
            PreparedStatement stmt = con.prepareStatement(sql);
            java.sql.ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                i = rs.getInt("Total_Amount");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public int updateRecords() {
        try {
            //Connection con = (Connection) dbconnection.getConnection();
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "update fees_record set Date = ?, Payment_Mode = ?, Transaction_ID = ?, Card_No = ?, Cheque_No = ?, Bank_Name = ?, Received_From = ?, Year1 = ?, Year2 = ?, Amount = Amount + ?, Total_Amount = Total_Amount + ?, GST = GST + ?,Total_In_Words = ?, Remarks = ? where Enrollment_No = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            String rollno, paymentmode, transaction, card, cheque, bank_name, receivedfrom, year1, year2, amount, totalamount, gst, remarks;

            rollno = text_rollno.getText();
            paymentmode = combo_payment.getSelectedItem().toString();
            transaction = text_transactionid.getText();
            card = text_cardno.getText();
            cheque = text_chequeno.getText();
            bank_name = text_bank.getText();
            receivedfrom = text_receivedfrom.getText();
            year1 = text_1year.getText();
            year2 = text_2year.getText();
            amount = text_1stamount.getText();
            totalamount = text_totalamount.getText();
            gst = text_gst.getText();
            remarks = text_remarks.getText();

            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

            int s = Integer.parseInt(totalamount);
            int total = (int) (i + s);
            //String s1 = String.valueOf(total);
            String s1 = NumberToWordsConverter.convert((int) total) + " Only";

            stmt.setTimestamp(1, date);
            stmt.setString(2, paymentmode);
            stmt.setString(3, transaction);
            stmt.setString(4, card);
            stmt.setString(5, cheque);
            stmt.setString(6, bank_name);
            stmt.setString(7, receivedfrom);
            stmt.setString(8, year1);
            stmt.setString(9, year2);
            stmt.setString(10, amount);
            stmt.setString(11, totalamount);
            stmt.setString(12, gst);
            stmt.setString(13, s1);
            stmt.setString(14, remarks);
            stmt.setString(15, rollno);

            int row = stmt.executeUpdate();
            if (row >= 1) {
                JOptionPane.showMessageDialog(this, "Records Updated Successfully");
                Receipt r = new Receipt();
                r.show();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Records Updation Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e);
        }
        return 1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_parent = new javax.swing.JPanel();
        Reciept2 = new javax.swing.JLabel();
        Reciept5 = new javax.swing.JLabel();
        Reciept8 = new javax.swing.JLabel();
        Reciept9 = new javax.swing.JLabel();
        Reciept10 = new javax.swing.JLabel();
        Reciept6 = new javax.swing.JLabel();
        lbl_cardno = new javax.swing.JLabel();
        lbl_chequeno = new javax.swing.JLabel();
        text_receiptno = new javax.swing.JTextField();
        text_rollno = new javax.swing.JTextField();
        combo_payment = new javax.swing.JComboBox<>();
        Reciept = new javax.swing.JLabel();
        Panel_child = new javax.swing.JPanel();
        Reciept14 = new javax.swing.JLabel();
        text_receivedfrom = new javax.swing.JTextField();
        Year = new javax.swing.JLabel();
        text_2year = new javax.swing.JTextField();
        Reciept15 = new javax.swing.JLabel();
        text_1year = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        Reciept16 = new javax.swing.JLabel();
        Reciept17 = new javax.swing.JLabel();
        text_gst = new javax.swing.JTextField();
        text_1stamount = new javax.swing.JTextField();
        Reciept18 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        Reciept19 = new javax.swing.JLabel();
        text_totalwords = new javax.swing.JTextField();
        Reciept20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        text_remarks = new javax.swing.JTextArea();
        btn_submit = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        text_totalamount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        text_bank = new javax.swing.JTextField();
        lbl_bankname = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        Reciept1 = new javax.swing.JLabel();
        text_student = new javax.swing.JTextField();
        text_courseyear = new javax.swing.JTextField();
        lbl_transactionid = new javax.swing.JLabel();
        text_chequeno = new javax.swing.JTextField();
        text_cardno = new javax.swing.JTextField();
        text_transactionid = new javax.swing.JTextField();
        text_date = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_records = new javax.swing.JTable();
        combo_course = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
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
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_parent.setBackground(new java.awt.Color(51, 51, 51));
        panel_parent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Reciept2.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept2.setForeground(new java.awt.Color(255, 255, 255));
        panel_parent.add(Reciept2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        Reciept5.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept5.setForeground(new java.awt.Color(255, 255, 255));
        Reciept5.setText("Student Name :");
        panel_parent.add(Reciept5, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 180, -1, -1));

        Reciept8.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept8.setForeground(new java.awt.Color(255, 255, 255));
        Reciept8.setText("Enrollment No :");
        panel_parent.add(Reciept8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        Reciept9.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept9.setForeground(new java.awt.Color(255, 255, 255));
        Reciept9.setText("Course :");
        panel_parent.add(Reciept9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        Reciept10.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept10.setForeground(new java.awt.Color(255, 255, 255));
        Reciept10.setText("Course Year :");
        panel_parent.add(Reciept10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, -1, -1));

        Reciept6.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept6.setForeground(new java.awt.Color(255, 255, 255));
        Reciept6.setText("Mode of Payment:");
        panel_parent.add(Reciept6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, -1, -1));

        lbl_cardno.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        lbl_cardno.setForeground(new java.awt.Color(255, 255, 255));
        lbl_cardno.setText("Card Number:");
        panel_parent.add(lbl_cardno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, -1, -1));

        lbl_chequeno.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        lbl_chequeno.setForeground(new java.awt.Color(255, 255, 255));
        lbl_chequeno.setText("Cheque No:");
        panel_parent.add(lbl_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, -1, -1));

        text_receiptno.setEditable(false);
        text_receiptno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        panel_parent.add(text_receiptno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 130, -1));

        text_rollno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        text_rollno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_rollnoActionPerformed(evt);
            }
        });
        panel_parent.add(text_rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 290, -1));

        combo_payment.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        combo_payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Online/Bank", "Cheque", "Card" }));
        combo_payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_paymentActionPerformed(evt);
            }
        });
        panel_parent.add(combo_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 165, 28));

        Reciept.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept.setForeground(new java.awt.Color(255, 255, 255));
        Reciept.setText("Receipt No :");
        panel_parent.add(Reciept, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        Panel_child.setBackground(new java.awt.Color(51, 51, 51));
        Panel_child.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N

        Reciept14.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept14.setForeground(new java.awt.Color(255, 255, 255));
        Reciept14.setText("Sr No: ");

        text_receivedfrom.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Year.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Year.setForeground(new java.awt.Color(255, 255, 255));
        Year.setText("The following payment in the collage office for the year :");

        text_2year.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Reciept15.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept15.setForeground(new java.awt.Color(255, 255, 255));
        Reciept15.setText("To");

        text_1year.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Reciept16.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept16.setForeground(new java.awt.Color(255, 255, 255));
        Reciept16.setText("Received From :");

        Reciept17.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept17.setForeground(new java.awt.Color(255, 255, 255));
        Reciept17.setText("Total Amount:");

        text_gst.setEditable(false);
        text_gst.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        text_1stamount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        text_1stamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_1stamountActionPerformed(evt);
            }
        });

        Reciept18.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept18.setForeground(new java.awt.Color(255, 255, 255));
        Reciept18.setText("Enter Amount To Be Update:");

        Reciept19.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept19.setForeground(new java.awt.Color(255, 255, 255));
        Reciept19.setText("Remarks:");

        text_totalwords.setEditable(false);
        text_totalwords.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Reciept20.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept20.setForeground(new java.awt.Color(255, 255, 255));
        Reciept20.setText("Total In Words :");

        text_remarks.setColumns(20);
        text_remarks.setRows(5);
        jScrollPane1.setViewportView(text_remarks);

        btn_submit.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        btn_submit.setText("Submit");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });

        text_totalamount.setEditable(false);
        text_totalamount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("G.S.T 18% ");

        text_bank.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        text_bank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_bankActionPerformed(evt);
            }
        });

        lbl_bankname.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        lbl_bankname.setForeground(new java.awt.Color(255, 255, 255));
        lbl_bankname.setText("Bank Name:");

        javax.swing.GroupLayout Panel_childLayout = new javax.swing.GroupLayout(Panel_child);
        Panel_child.setLayout(Panel_childLayout);
        Panel_childLayout.setHorizontalGroup(
            Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_childLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(Reciept14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Reciept18)
                .addGap(124, 124, 124))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addGroup(Panel_childLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Reciept20)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_childLayout.createSequentialGroup()
                        .addComponent(Reciept19)
                        .addGap(9, 9, 9)))
                .addGap(18, 18, 18)
                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_childLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_childLayout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_childLayout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_childLayout.createSequentialGroup()
                                        .addComponent(Reciept17)
                                        .addGap(28, 28, 28)
                                        .addComponent(text_totalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(117, 117, 117))))
                            .addGroup(Panel_childLayout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(jLabel2))))
                    .addGroup(Panel_childLayout.createSequentialGroup()
                        .addComponent(text_totalwords)
                        .addGap(266, 266, 266)
                        .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(text_gst, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_1stamount, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120))))
            .addGroup(Panel_childLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_childLayout.createSequentialGroup()
                        .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_bankname)
                            .addComponent(Reciept16))
                        .addGap(48, 48, 48)
                        .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_receivedfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_bank, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Year))
                .addGap(249, 249, 249)
                .addComponent(text_1year, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(Reciept15)
                .addGap(18, 18, 18)
                .addComponent(text_2year, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_childLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        Panel_childLayout.setVerticalGroup(
            Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_childLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_bankname)
                    .addComponent(text_bank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_receivedfrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reciept16))
                .addGap(18, 18, 18)
                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_1year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_2year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reciept15)
                    .addComponent(Year))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Reciept18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reciept14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_childLayout.createSequentialGroup()
                        .addComponent(text_1stamount, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_gst, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Reciept17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_totalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(Panel_childLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Reciept20)
                            .addComponent(text_totalwords, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(Panel_childLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Reciept19))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        panel_parent.add(Panel_child, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, -1, 640));
        panel_parent.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        panel_parent.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Reciept1.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept1.setForeground(new java.awt.Color(255, 255, 255));
        Reciept1.setText("Date :");
        panel_parent.add(Reciept1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 120, -1, -1));

        text_student.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        text_student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_studentActionPerformed(evt);
            }
        });
        text_student.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                text_studentKeyReleased(evt);
            }
        });
        panel_parent.add(text_student, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 180, 270, -1));

        text_courseyear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        panel_parent.add(text_courseyear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 190, -1));

        lbl_transactionid.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        lbl_transactionid.setForeground(new java.awt.Color(255, 255, 255));
        lbl_transactionid.setText("Transaction I.D:");
        panel_parent.add(lbl_transactionid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, -1, -1));

        text_chequeno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        panel_parent.add(text_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 280, -1));

        text_cardno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        panel_parent.add(text_cardno, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 280, -1));

        text_transactionid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        text_transactionid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_transactionidActionPerformed(evt);
            }
        });
        panel_parent.add(text_transactionid, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 280, -1));

        text_date.setEditable(false);
        text_date.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        panel_parent.add(text_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 120, 200, -1));

        table_records.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Name", "Enrollment No.", "Course", "Course Year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table_records);

        panel_parent.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(922, 240, 460, 210));

        panel_parent.add(combo_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 170, 30));
        panel_parent.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1470, 20));

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Update Records");
        panel_parent.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, -1, -1));

        getContentPane().add(panel_parent, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 1470, 1110));

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

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

        setSize(new java.awt.Dimension(2028, 1123));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
        Update_fees e = new Update_fees();
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

    private void text_transactionidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_transactionidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_transactionidActionPerformed

    private void text_studentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_studentKeyReleased
        String Studentname = text_student.getText();
        String s = Studentname.substring(0, 1).toUpperCase() + Studentname.substring(1);
        searchRecords(s);
        try {
            diplayTableRecords();       // TODO add your handling code here:
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_text_studentKeyReleased

    private void text_studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_studentActionPerformed
        clearTable();
        int i = getStudentRecord();
        if (i == 1) {
            JOptionPane.showMessageDialog(this, "No Reocrds found");
            Update_fees u = new Update_fees();
            u.show();
            this.dispose();
        }
    }//GEN-LAST:event_text_studentActionPerformed

    private void text_bankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_bankActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_bankActionPerformed

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        int i = validation();
        if (i == 0) {
            getTotalAmount();
            updateRecords();

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_submitActionPerformed

    private void text_1stamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_1stamountActionPerformed
        setAmount();
    }//GEN-LAST:event_text_1stamountActionPerformed

    private void combo_paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_paymentActionPerformed
        displayPaymentMethod();
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_paymentActionPerformed

    private void text_rollnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_rollnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_rollnoActionPerformed

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
            java.util.logging.Logger.getLogger(Update_fees.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Update_fees.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Update_fees.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Update_fees.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Update_fees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Edit_course;
    private javax.swing.JPanel Go_back;
    private javax.swing.JPanel Home_panel;
    private javax.swing.JPanel Logout;
    private javax.swing.JPanel Panel_child;
    private javax.swing.JLabel Reciept;
    private javax.swing.JLabel Reciept1;
    private javax.swing.JLabel Reciept10;
    private javax.swing.JLabel Reciept14;
    private javax.swing.JLabel Reciept15;
    private javax.swing.JLabel Reciept16;
    private javax.swing.JLabel Reciept17;
    private javax.swing.JLabel Reciept18;
    private javax.swing.JLabel Reciept19;
    private javax.swing.JLabel Reciept2;
    private javax.swing.JLabel Reciept20;
    private javax.swing.JLabel Reciept5;
    private javax.swing.JLabel Reciept6;
    private javax.swing.JLabel Reciept8;
    private javax.swing.JLabel Reciept9;
    private javax.swing.JPanel Reload;
    private javax.swing.JPanel Search_record;
    private javax.swing.JPanel Student_record;
    private javax.swing.JPanel View_all_records;
    private javax.swing.JLabel Year;
    private javax.swing.JButton btn_submit;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JComboBox<String> combo_payment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lbl_bankname;
    private javax.swing.JLabel lbl_cardno;
    private javax.swing.JLabel lbl_chequeno;
    private javax.swing.JLabel lbl_transactionid;
    private javax.swing.JPanel panel_parent;
    private javax.swing.JTable table_records;
    private javax.swing.JTextField text_1stamount;
    private javax.swing.JTextField text_1year;
    private javax.swing.JTextField text_2year;
    private javax.swing.JTextField text_bank;
    private javax.swing.JTextField text_cardno;
    private javax.swing.JTextField text_chequeno;
    private javax.swing.JTextField text_courseyear;
    private javax.swing.JTextField text_date;
    private javax.swing.JTextField text_gst;
    private javax.swing.JTextField text_receiptno;
    private javax.swing.JTextField text_receivedfrom;
    private javax.swing.JTextArea text_remarks;
    private javax.swing.JTextField text_rollno;
    private javax.swing.JTextField text_student;
    private javax.swing.JTextField text_totalamount;
    private javax.swing.JTextField text_totalwords;
    private javax.swing.JTextField text_transactionid;
    // End of variables declaration//GEN-END:variables
}
