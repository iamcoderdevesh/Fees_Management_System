/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Fees_Management_System;

import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Receipt extends javax.swing.JFrame {

    /**
     * Creates new form Receipt
     */
    public Receipt() throws ClassNotFoundException {
        initComponents();
        getStudentRecord();

        URL url = getClass().getResource("clg_1.jpg");
        ImageIcon imgicon = new ImageIcon(url);
        super.setIconImage(imgicon.getImage());
        setTitle("Fees-Management-System");
        //getstudentrecord();
    }

    public void getStudentRecord() {
        try {
            //Connection con = (Connection) sqlconnection.getConnection();
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management_system", "Devesh", "Admin123");
            String sql = "select *from Fees_record order by Receipt_No desc limit 1";
            PreparedStatement stmt = con.prepareStatement(sql);

            // String Studentname = text_student.getText();
            //stmt.setString(1, Studentname);
            java.sql.ResultSet rs = stmt.executeQuery();
            String receipt_no, date, student_name, rollno, course, courseyear, paymentmode, transaction, card, cheque, bank_name, receivedfrom, year1, year2, amount, totalamount, gst, totalinwords, remarks;

            if (rs.next()) {
                receipt_no = rs.getString("Receipt_No");
                date = rs.getString("Date");
                student_name = rs.getString("Student_Name");
                rollno = rs.getString("Enrollment_No");
                course = rs.getString("Course");
                paymentmode = rs.getString("Payment_Mode");
                card = rs.getString("Card_No");
                cheque = rs.getString("Cheque_No");
                bank_name = rs.getString("Bank_Name");
                receivedfrom = rs.getString("Received_From");
                year1 = rs.getString("Year1");
                year2 = rs.getString("Year2");
                amount = rs.getString("Amount");
                totalamount = rs.getString("Total_Amount");
                gst = rs.getString("GST");
                totalinwords = rs.getString("Total_In_Words");
                remarks = rs.getString("Remarks");

                text_receiptno.setText(receipt_no);
                text_date.setText(date);
                text_studentname.setText(student_name);
                text_rollno.setText(rollno);
                text_course.setText(course);
                text_paymentmode.setText(paymentmode);
                text_recievedfrom.setText(receivedfrom);
                text_yearfrom.setText(year1);
                text_yearto.setText(year2);
                text_amount.setText(amount);
                text_totalamount.setText(totalamount);
                text_gst.setText(gst);
                text_totalinwords.setText(totalinwords);
                text_remarks.setText(remarks);

                if (paymentmode.equalsIgnoreCase("cash")) {
                    text_bank.setText("----");
                    text_cardno.setText("----");
                    text_chequeno.setText("----");
                } else if (paymentmode.equalsIgnoreCase("cheque")) {
                    text_bank.setText(bank_name);
                    text_cardno.setText("----");
                    text_chequeno.setText(cheque);

                } else if (paymentmode.equalsIgnoreCase("card")) {
                    text_bank.setText(bank_name);
                    text_cardno.setText(card);
                    text_chequeno.setText("----");
                } else if (paymentmode.equalsIgnoreCase("online/bank")) {
                    text_bank.setText(bank_name);
                    text_cardno.setText("----");
                    text_chequeno.setText("----");
                }

            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void printReceipt() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                pf.setOrientation(PageFormat.LANDSCAPE);
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.47, 0.47);

                jPanel3.print(g2);

                return Printable.PAGE_EXISTS;

            }
        });
        boolean ok = job.printDialog();
        if (ok) {
            try {

                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, ex);
            }
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

        Reciept10 = new javax.swing.JLabel();
        Reciept4 = new javax.swing.JLabel();
        Reciept7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        text_receiptno = new javax.swing.JLabel();
        text_date = new javax.swing.JLabel();
        Reciept27 = new javax.swing.JLabel();
        text_rollno = new javax.swing.JLabel();
        text_studentname = new javax.swing.JLabel();
        Reciept30 = new javax.swing.JLabel();
        text_course = new javax.swing.JLabel();
        Reciept32 = new javax.swing.JLabel();
        Reciept33 = new javax.swing.JLabel();
        text_paymentmode = new javax.swing.JLabel();
        Reciept36 = new javax.swing.JLabel();
        Reciept37 = new javax.swing.JLabel();
        text_cardno = new javax.swing.JLabel();
        text_bank = new javax.swing.JLabel();
        Reciept40 = new javax.swing.JLabel();
        Reciept41 = new javax.swing.JLabel();
        text_recievedfrom = new javax.swing.JLabel();
        Year1 = new javax.swing.JLabel();
        Reciept43 = new javax.swing.JLabel();
        text_yearfrom = new javax.swing.JLabel();
        Reciept45 = new javax.swing.JLabel();
        text_yearto = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        text_chequeno = new javax.swing.JLabel();
        Reciept52 = new javax.swing.JLabel();
        text_remarks = new javax.swing.JLabel();
        text_gst = new javax.swing.JLabel();
        text_totalinwords = new javax.swing.JLabel();
        Reciept57 = new javax.swing.JLabel();
        Reciept58 = new javax.swing.JLabel();
        Reciept59 = new javax.swing.JLabel();
        Reciept60 = new javax.swing.JLabel();
        Reciept61 = new javax.swing.JLabel();
        Reciept62 = new javax.swing.JLabel();
        text_totalamount = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        text_amount = new javax.swing.JLabel();
        Reciept65 = new javax.swing.JLabel();
        Reciept66 = new javax.swing.JLabel();
        Reciept67 = new javax.swing.JLabel();
        Reciept28 = new javax.swing.JLabel();
        Reciept29 = new javax.swing.JLabel();
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

        Reciept10.setBackground(new java.awt.Color(102, 102, 102));
        Reciept10.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept10.setForeground(new java.awt.Color(102, 102, 102));
        Reciept10.setText("SYBCA101");

        Reciept4.setBackground(new java.awt.Color(51, 51, 51));
        Reciept4.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept4.setForeground(new java.awt.Color(51, 51, 51));
        Reciept4.setText("Student Name:");

        Reciept7.setBackground(new java.awt.Color(51, 51, 51));
        Reciept7.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept7.setForeground(new java.awt.Color(51, 51, 51));
        Reciept7.setText("Course:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Georgia", 0, 48)); // NOI18N
        jLabel2.setText("Sahyog College IT");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 202, 1490, 27));

        jLabel16.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel16.setText("Near Raghoba Shankar Road, Phul Market, Thane West");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 700, -1));

        jLabel17.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel17.setText("Thane, Maharastra 400601");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 364, 39));

        text_receiptno.setBackground(new java.awt.Color(51, 51, 51));
        text_receiptno.setFont(new java.awt.Font("Georgia", 0, 36)); // NOI18N
        text_receiptno.setForeground(new java.awt.Color(51, 51, 51));
        text_receiptno.setText("____");
        jPanel3.add(text_receiptno, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 100, 38));

        text_date.setBackground(new java.awt.Color(51, 51, 51));
        text_date.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        text_date.setForeground(new java.awt.Color(51, 51, 51));
        text_date.setText("_____");
        jPanel3.add(text_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 230, 140, 30));

        Reciept27.setBackground(new java.awt.Color(51, 51, 51));
        Reciept27.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept27.setText("Enrollment No :-");
        jPanel3.add(Reciept27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, 42));

        text_rollno.setBackground(new java.awt.Color(0, 0, 0));
        text_rollno.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_rollno.setText("_____");
        jPanel3.add(text_rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, -1, 42));

        text_studentname.setBackground(new java.awt.Color(51, 51, 51));
        text_studentname.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_studentname.setForeground(new java.awt.Color(51, 51, 51));
        text_studentname.setText("______");
        jPanel3.add(text_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 280, 213, 50));

        Reciept30.setBackground(new java.awt.Color(51, 51, 51));
        Reciept30.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept30.setForeground(new java.awt.Color(51, 51, 51));
        Reciept30.setText("Student Name:");
        jPanel3.add(Reciept30, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 280, 164, 51));

        text_course.setBackground(new java.awt.Color(51, 51, 51));
        text_course.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_course.setForeground(new java.awt.Color(51, 51, 51));
        text_course.setText("_____");
        jPanel3.add(text_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 133, 40));

        Reciept32.setBackground(new java.awt.Color(51, 51, 51));
        Reciept32.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept32.setForeground(new java.awt.Color(51, 51, 51));
        Reciept32.setText("Course:-");
        jPanel3.add(Reciept32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 133, 36));

        Reciept33.setBackground(new java.awt.Color(51, 51, 51));
        Reciept33.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept33.setForeground(new java.awt.Color(51, 51, 51));
        Reciept33.setText("Mode Of Payment:-");
        jPanel3.add(Reciept33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 218, 36));

        text_paymentmode.setBackground(new java.awt.Color(51, 51, 51));
        text_paymentmode.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_paymentmode.setForeground(new java.awt.Color(51, 51, 51));
        text_paymentmode.setText("_____");
        jPanel3.add(text_paymentmode, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 207, 36));

        Reciept36.setBackground(new java.awt.Color(51, 51, 51));
        Reciept36.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept36.setForeground(new java.awt.Color(51, 51, 51));
        Reciept36.setText("Cheque No:");
        jPanel3.add(Reciept36, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 460, 137, 36));

        Reciept37.setBackground(new java.awt.Color(51, 51, 51));
        Reciept37.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept37.setForeground(new java.awt.Color(51, 51, 51));
        Reciept37.setText("Received From:-");
        jPanel3.add(Reciept37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 207, 36));

        text_cardno.setBackground(new java.awt.Color(51, 51, 51));
        text_cardno.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_cardno.setForeground(new java.awt.Color(51, 51, 51));
        text_cardno.setText("____");
        jPanel3.add(text_cardno, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 520, 205, 36));

        text_bank.setBackground(new java.awt.Color(51, 51, 51));
        text_bank.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_bank.setForeground(new java.awt.Color(51, 51, 51));
        text_bank.setText("_____");
        jPanel3.add(text_bank, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 450, 207, 36));

        Reciept40.setBackground(new java.awt.Color(51, 51, 51));
        Reciept40.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept40.setForeground(new java.awt.Color(51, 51, 51));
        Reciept40.setText("Card No:");
        jPanel3.add(Reciept40, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 520, 110, 36));

        Reciept41.setBackground(new java.awt.Color(51, 51, 51));
        Reciept41.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept41.setForeground(new java.awt.Color(51, 51, 51));
        Reciept41.setText("Bank Name:-");
        jPanel3.add(Reciept41, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 207, 36));

        text_recievedfrom.setBackground(new java.awt.Color(51, 51, 51));
        text_recievedfrom.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_recievedfrom.setForeground(new java.awt.Color(51, 51, 51));
        text_recievedfrom.setText("_____");
        jPanel3.add(text_recievedfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 510, 207, 36));

        Year1.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Year1.setForeground(new java.awt.Color(51, 51, 51));
        Year1.setText("The following payment in the collage office for the year :-");
        jPanel3.add(Year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 577, -1, -1));

        Reciept43.setBackground(new java.awt.Color(51, 51, 51));
        Reciept43.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept43.setForeground(new java.awt.Color(51, 51, 51));
        Reciept43.setText("From:-");
        jPanel3.add(Reciept43, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 576, 88, 30));

        text_yearfrom.setBackground(new java.awt.Color(51, 51, 51));
        text_yearfrom.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_yearfrom.setForeground(new java.awt.Color(51, 51, 51));
        text_yearfrom.setText("____");
        jPanel3.add(text_yearfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(764, 573, 70, 36));

        Reciept45.setBackground(new java.awt.Color(51, 51, 51));
        Reciept45.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept45.setForeground(new java.awt.Color(51, 51, 51));
        Reciept45.setText("To:");
        jPanel3.add(Reciept45, new org.netbeans.lib.awtextra.AbsoluteConstraints(852, 573, -1, 36));

        text_yearto.setBackground(new java.awt.Color(51, 51, 51));
        text_yearto.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_yearto.setForeground(new java.awt.Color(51, 51, 51));
        text_yearto.setText("____");
        jPanel3.add(text_yearto, new org.netbeans.lib.awtextra.AbsoluteConstraints(916, 573, -1, 36));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 846, 502, 28));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 636, 1490, 27));

        text_chequeno.setBackground(new java.awt.Color(51, 51, 51));
        text_chequeno.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_chequeno.setForeground(new java.awt.Color(51, 51, 51));
        text_chequeno.setText("____");
        jPanel3.add(text_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 460, 187, 36));

        Reciept52.setBackground(new java.awt.Color(51, 51, 51));
        Reciept52.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept52.setForeground(new java.awt.Color(51, 51, 51));
        Reciept52.setText("Remarks:-");
        jPanel3.add(Reciept52, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 896, 207, 36));

        text_remarks.setBackground(new java.awt.Color(51, 51, 51));
        text_remarks.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_remarks.setForeground(new java.awt.Color(51, 51, 51));
        text_remarks.setText("____________________");
        jPanel3.add(text_remarks, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 900, 386, 36));

        text_gst.setBackground(new java.awt.Color(51, 51, 51));
        text_gst.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_gst.setForeground(new java.awt.Color(51, 51, 51));
        text_gst.setText("______");
        jPanel3.add(text_gst, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 800, 132, 36));

        text_totalinwords.setBackground(new java.awt.Color(51, 51, 51));
        text_totalinwords.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_totalinwords.setForeground(new java.awt.Color(51, 51, 51));
        text_totalinwords.setText("____________________");
        jPanel3.add(text_totalinwords, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 820, 560, 36));

        Reciept57.setBackground(new java.awt.Color(51, 51, 51));
        Reciept57.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept57.setForeground(new java.awt.Color(51, 51, 51));
        Reciept57.setText("Sr No.");
        jPanel3.add(Reciept57, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 656, 207, 36));

        Reciept58.setBackground(new java.awt.Color(51, 51, 51));
        Reciept58.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept58.setForeground(new java.awt.Color(51, 51, 51));
        Reciept58.setText("Student Sign:");
        jPanel3.add(Reciept58, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 976, 183, 36));

        Reciept59.setBackground(new java.awt.Color(51, 51, 51));
        Reciept59.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept59.setForeground(new java.awt.Color(51, 51, 51));
        Reciept59.setText("Amount");
        jPanel3.add(Reciept59, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 656, 207, 36));

        Reciept60.setBackground(new java.awt.Color(51, 51, 51));
        Reciept60.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept60.setForeground(new java.awt.Color(51, 51, 51));
        Reciept60.setText("Amount:-");
        jPanel3.add(Reciept60, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 740, -1, 36));

        Reciept61.setBackground(new java.awt.Color(51, 51, 51));
        Reciept61.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept61.setForeground(new java.awt.Color(51, 51, 51));
        Reciept61.setText("Total:-");
        jPanel3.add(Reciept61, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 880, 100, 36));

        Reciept62.setBackground(new java.awt.Color(51, 51, 51));
        Reciept62.setFont(new java.awt.Font("Georgia", 0, 36)); // NOI18N
        Reciept62.setForeground(new java.awt.Color(51, 51, 51));
        Reciept62.setText("1");
        jPanel3.add(Reciept62, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 736, 50, 36));

        text_totalamount.setBackground(new java.awt.Color(51, 51, 51));
        text_totalamount.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_totalamount.setForeground(new java.awt.Color(51, 51, 51));
        text_totalamount.setText("______");
        jPanel3.add(text_totalamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 880, 150, 36));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 716, 1410, 30));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 936, 500, 40));

        text_amount.setBackground(new java.awt.Color(51, 51, 51));
        text_amount.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        text_amount.setForeground(new java.awt.Color(51, 51, 51));
        text_amount.setText("______");
        jPanel3.add(text_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 740, 126, 36));

        Reciept65.setBackground(new java.awt.Color(51, 51, 51));
        Reciept65.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept65.setForeground(new java.awt.Color(51, 51, 51));
        Reciept65.setText("Total In Words:-");
        jPanel3.add(Reciept65, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 826, 207, 36));

        Reciept66.setBackground(new java.awt.Color(51, 51, 51));
        Reciept66.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept66.setForeground(new java.awt.Color(51, 51, 51));
        Reciept66.setText("__________________");
        jPanel3.add(Reciept66, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 980, 270, 36));

        Reciept67.setBackground(new java.awt.Color(51, 51, 51));
        Reciept67.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept67.setForeground(new java.awt.Color(51, 51, 51));
        Reciept67.setText("GST 18 %:-");
        jPanel3.add(Reciept67, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 800, 124, 36));

        Reciept28.setBackground(new java.awt.Color(51, 51, 51));
        Reciept28.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept28.setForeground(new java.awt.Color(51, 51, 51));
        Reciept28.setText("Receipt No :");
        jPanel3.add(Reciept28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 164, 51));

        Reciept29.setBackground(new java.awt.Color(51, 51, 51));
        Reciept29.setFont(new java.awt.Font("Georgia", 0, 24)); // NOI18N
        Reciept29.setForeground(new java.awt.Color(51, 51, 51));
        Reciept29.setText("Date:-");
        jPanel3.add(Reciept29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 230, 90, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/Fees_Management_System/clg_1.jpg"))); // NOI18N
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 150, 110));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, -1, 1070));

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

        jLabel11.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/java/com/mycompany/test/my icons/printer-.png"))); // NOI18N
        jLabel11.setText("Print");

        javax.swing.GroupLayout ReloadLayout = new javax.swing.GroupLayout(Reload);
        Reload.setLayout(ReloadLayout);
        ReloadLayout.setHorizontalGroup(
            ReloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReloadLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ReloadLayout.setVerticalGroup(
            ReloadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
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
                .addContainerGap(90, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 552, 1100));

        pack();
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
        printReceipt();        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Receipt().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Edit_course;
    private javax.swing.JPanel Go_back;
    private javax.swing.JPanel Home_panel;
    private javax.swing.JPanel Logout;
    private javax.swing.JLabel Reciept10;
    private javax.swing.JLabel Reciept27;
    private javax.swing.JLabel Reciept28;
    private javax.swing.JLabel Reciept29;
    private javax.swing.JLabel Reciept30;
    private javax.swing.JLabel Reciept32;
    private javax.swing.JLabel Reciept33;
    private javax.swing.JLabel Reciept36;
    private javax.swing.JLabel Reciept37;
    private javax.swing.JLabel Reciept4;
    private javax.swing.JLabel Reciept40;
    private javax.swing.JLabel Reciept41;
    private javax.swing.JLabel Reciept43;
    private javax.swing.JLabel Reciept45;
    private javax.swing.JLabel Reciept52;
    private javax.swing.JLabel Reciept57;
    private javax.swing.JLabel Reciept58;
    private javax.swing.JLabel Reciept59;
    private javax.swing.JLabel Reciept60;
    private javax.swing.JLabel Reciept61;
    private javax.swing.JLabel Reciept62;
    private javax.swing.JLabel Reciept65;
    private javax.swing.JLabel Reciept66;
    private javax.swing.JLabel Reciept67;
    private javax.swing.JLabel Reciept7;
    private javax.swing.JPanel Reload;
    private javax.swing.JPanel Search_record;
    private javax.swing.JPanel Student_record;
    private javax.swing.JPanel View_all_records;
    private javax.swing.JLabel Year1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel text_amount;
    private javax.swing.JLabel text_bank;
    private javax.swing.JLabel text_cardno;
    private javax.swing.JLabel text_chequeno;
    private javax.swing.JLabel text_course;
    private javax.swing.JLabel text_date;
    private javax.swing.JLabel text_gst;
    private javax.swing.JLabel text_paymentmode;
    private javax.swing.JLabel text_receiptno;
    private javax.swing.JLabel text_recievedfrom;
    private javax.swing.JLabel text_remarks;
    private javax.swing.JLabel text_rollno;
    private javax.swing.JLabel text_studentname;
    private javax.swing.JLabel text_totalamount;
    private javax.swing.JLabel text_totalinwords;
    private javax.swing.JLabel text_yearfrom;
    private javax.swing.JLabel text_yearto;
    // End of variables declaration//GEN-END:variables
}
