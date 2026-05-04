/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.KhachHang;
import services.imp.khahangsvImpl;
import viewmodels.KhachHangViewMD;
import services.IKhachHangService;
import viewmodels.KhachHang02ViewMD;

/**
 *
 * @author hungh
 */
public class frm_Khachhang extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel = new DefaultTableModel();
    List<KhachHangViewMD> listKhachHang;
    List<KhachHang02ViewMD> listKhachHang01;

    private IKhachHangService KH;

    // Components
    private javax.swing.JTable TB_bang, TB_bang1, TB_bang02;
    private swing.SearchText txt_timKiem, txt_timKiem01;
    private swing.MyTextField txt_Ten, txt_tenDem, txt_Ho, txt_sdt, txt_email;
    private com.toedter.calendar.JDateChooser date_ngaysinh1;
    private javax.swing.JRadioButton rd_Nam, rd_nu;
    private javax.swing.ButtonGroup buttonGroup1;
    private swing.MyButton btn_them, Btn_capNhat, btn_LamMoi, btn_LamMoi1;
    private javax.swing.JLabel TXT_01, Btn_timKiem1, LBL_SOLUONG;
    private javax.swing.JScrollPane jScrollPane1, jScrollPane2, jScrollPane4, jScrollPane5;
    private swing.PanelBorder panelBorder1, panelBorder3;
    private swing.PanelGradiente panelGradiente1;
    private javax.swing.JPanel jPanel1, jPanel2;
    private javax.swing.JTabbedPane jTabbedPane3;

    public frm_Khachhang() {
        initComponents();
        KH = new khahangsvImpl();
        listKhachHang = KH.getall();
        showTable(listKhachHang);
        TXT_01.setText("Tổng số khách hàng là : " + listKhachHang.size());

    }

    public void showTable(List<KhachHangViewMD> list) {
        defaultTableModel = (DefaultTableModel) TB_bang1.getModel();
        defaultTableModel.setRowCount(0);
        for (KhachHangViewMD khachHang01 : list) {
            defaultTableModel.addRow(khachHang01.toDataRow());
        }
    }

    public void showTable2(List<KhachHang02ViewMD> list01) {
        defaultTableModel = (DefaultTableModel) TB_bang02.getModel();
        defaultTableModel.setRowCount(0);
        for (KhachHang02ViewMD khachHang01 : list01) {
            defaultTableModel.addRow(khachHang01.toDataRow());
        }
    }

    public void showTable3(List<KhachHang02ViewMD> list01) {
        int id = layid();
        defaultTableModel = (DefaultTableModel) TB_bang02.getModel();
        defaultTableModel.setRowCount(0);
        for (KhachHang02ViewMD khachHangViewMD : KH.GetTKTheoIDKH(id)) {
            defaultTableModel.addRow(khachHangViewMD.toDataRow());
        }
    }

    private KhachHang getData() {
        KhachHang cv = new KhachHang();
        cv.setTen(txt_Ten.getText());
        cv.setTendem(txt_tenDem.getText());
        cv.setHo(txt_Ho.getText());
        int gt;
        if (rd_Nam.isSelected()) {
            gt = 0;
        } else {
            gt = 1;
        }
        cv.setGioitinh(gt);
        cv.setNgaysinh(date_ngaysinh1.getDate());
        cv.setSdt(txt_sdt.getText());
        cv.setEmail(txt_email.getText());

        return cv;
    }

    public int layid() {
        Integer row = TB_bang1.getSelectedRow();
        int id = Integer.parseInt(TB_bang1.getValueAt(row, 0).toString());
        return id;

    }

    public boolean check() {
        String sdt = "(0\\d{9})";
        String mail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txt_Ten.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên!");
            return false;
        }
        if (p.matcher(txt_Ten.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên của bạn không được nhập số");
            return false;
        }
        if (txt_Ten.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên không được quá 30 kí tự");
            return false;
        }

        if (txt_sdt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập SĐT!");
            return false;
        }
        try {
            if (!txt_sdt.getText().matches(sdt)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại của bạn chưa đúng định dạng");
                return false;
            }
        } catch (Exception e) {
        }

        if (KH.kiemtrasdt(txt_sdt.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Số điện thoại của bạn đã tồn tại");
            return false;
        } else if (txt_email.getText().equals("")) {
            return true;
        } else {
            try {

                if (!txt_email.getText().matches(mail)) {
                    JOptionPane.showMessageDialog(this, "Email của bạn chưa đúng định dạng");
                    return false;
                }
                if (KH.kiemtra(txt_email.getText()) != null) {
                    JOptionPane.showMessageDialog(this, "Email đã tồn tại");
                    return false;
                }

            } catch (Exception e) {
            }
        }

        return true;

    }

    public boolean check2() {
        Pattern p = Pattern.compile("^[0-9]+$");

        if (txt_Ho.getText() == null & txt_tenDem.getText() == null) {
            return true;
        } else {

            if (p.matcher(txt_Ho.getText()).find() == true) {
                JOptionPane.showMessageDialog(this, "Họ của bạn không được nhập số");
                return false;
            }
            if (txt_Ho.getText().length() > 30) {
                JOptionPane.showMessageDialog(this, "Họ không được quá 30 kí tự");
                return false;
            }
            if (p.matcher(txt_tenDem.getText()).find() == true) {
                JOptionPane.showMessageDialog(this, "Tên đệm của bạn không được nhập số");
                return false;
            }
            if (txt_tenDem.getText().length() > 30) {
                JOptionPane.showMessageDialog(this, "Tên Đệm không được quá 30 kí tự");
                return false;

            }
        }

        return true;
    }

    public boolean check3() {
        String sdt = "(0\\d{9})";
        String mail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txt_Ten.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên!");
            return false;
        }
        if (p.matcher(txt_Ten.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên của bạn không được nhập số");
            return false;
        }
        if (txt_Ten.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên không được quá 30 kí tự");
            return false;
        }
        if (txt_sdt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập SĐT!");
            return false;
        }
        try {
            if (!txt_sdt.getText().matches(sdt)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại của bạn chưa đúng định dạng");
                return false;
            }
        } catch (Exception e) {
        }

        if (txt_email.getText().equals("")) {
            return true;
        } else {
            try {

                if (!txt_email.getText().matches(mail)) {
                    JOptionPane.showMessageDialog(this, "Email của bạn chưa đúng định dạng");
                    return false;
                }

            } catch (Exception e) {
            }
        }

        return true;

    }

    public boolean check4() {
        Pattern p = Pattern.compile("^[0-9]+$");

        if (txt_Ho.getText() == null & txt_tenDem.getText() == null) {
            return true;
        } else {

            if (p.matcher(txt_Ho.getText()).find() == true) {
                JOptionPane.showMessageDialog(this, "Họ của bạn không được nhập số");
                return false;
            }
            if (txt_Ho.getText().length() > 30) {
                JOptionPane.showMessageDialog(this, "Họ không được quá 30 kí tự");
                return false;
            }
            if (p.matcher(txt_tenDem.getText()).find() == true) {
                JOptionPane.showMessageDialog(this, "Tên đệm của bạn không được nhập số");
                return false;
            }
            if (txt_tenDem.getText().length() > 30) {
                JOptionPane.showMessageDialog(this, "Tên Đệm không được quá 30 kí tự");
                return false;

            }
        }

        return true;
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1010, 640));
        setMinimumSize(new java.awt.Dimension(1010, 640));

        // Initialize components
        panelGradiente1 = new swing.PanelGradiente();
        panelBorder1 = new swing.PanelBorder();
        panelBorder3 = new swing.PanelBorder();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();

        txt_Ten = new swing.MyTextField();
        txt_tenDem = new swing.MyTextField();
        txt_Ho = new swing.MyTextField();
        txt_sdt = new swing.MyTextField();
        txt_email = new swing.MyTextField();
        txt_timKiem = new swing.SearchText();
        txt_timKiem01 = new swing.SearchText();

        date_ngaysinh1 = new com.toedter.calendar.JDateChooser();

        rd_Nam = new javax.swing.JRadioButton();
        rd_nu = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup1.add(rd_Nam);
        buttonGroup1.add(rd_nu);

        btn_them = new swing.MyButton();
        Btn_capNhat = new swing.MyButton();
        btn_LamMoi = new swing.MyButton();
        btn_LamMoi1 = new swing.MyButton();

        // Table models
        defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Họ Và Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email"}
        );
        TB_bang1 = new javax.swing.JTable(defaultTableModel);
        TB_bang1.setRowHeight(25);
        TB_bang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TB_bang1MouseClicked(evt);
            }
        });
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane5.setViewportView(TB_bang1);

        javax.swing.table.DefaultTableModel model2 = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Họ Và Tên", "SĐT", "Mã Hoá Đơn", "Ngày Tạo Hoá Đơn", "Tổng Tiền", "Trạng Thái"}
        );
        TB_bang02 = new javax.swing.JTable(model2);
        TB_bang02.setRowHeight(25);
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane4.setViewportView(TB_bang02);

        TXT_01 = new javax.swing.JLabel();
        LBL_SOLUONG = new javax.swing.JLabel();
        Btn_timKiem1 = new javax.swing.JLabel();
        Btn_timKiem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_timKiem1MouseClicked(evt);
            }
        });

        txt_timKiem01.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiem01KeyReleased(evt);
            }
        });
        txt_timKiem01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiem01ActionPerformed(evt);
            }
        });

        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        Btn_capNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_capNhatActionPerformed(evt);
            }
        });

        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_LamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoi1ActionPerformed(evt);
            }
        });
    }

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        if (check() && check2()) {
            JOptionPane.showMessageDialog(this, KH.add(getData()));
            listKhachHang = KH.getall();
            showTable(listKhachHang);
            TXT_01.setText("Tổng số khách hàng là : " + listKhachHang.size());

        }


    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        txt_Ten.setText("");
        txt_tenDem.setText("");
        txt_Ho.setText("");
        date_ngaysinh1.setCalendar(null);
        txt_sdt.setText("");
        txt_email.setText("");

    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void Btn_capNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_capNhatActionPerformed
        int row = TB_bang1.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "cần chọn khách hàng để cập nhật");
            return;
        }
        if (check3() && check4()) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật không?") == JOptionPane.YES_OPTION) {
                int id = layid();

                JOptionPane.showMessageDialog(this, KH.update(id, getData()));
                listKhachHang = KH.getall();
                showTable(listKhachHang);
                TXT_01.setText("Tổng số khách hàng là : " + listKhachHang.size());

            }
        }

    }//GEN-LAST:event_Btn_capNhatActionPerformed

    private void TB_bangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_bangMouseClicked

    }//GEN-LAST:event_TB_bangMouseClicked

    private void Btn_timKiem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_timKiem1MouseClicked

    }//GEN-LAST:event_Btn_timKiem1MouseClicked

    private void txt_timKiem01KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiem01KeyReleased
        defaultTableModel = (DefaultTableModel) TB_bang1.getModel();
        defaultTableModel.setRowCount(0);
        for (KhachHangViewMD khachHang01 : KH.GetTK(txt_timKiem01.getText())) {
            defaultTableModel.addRow(khachHang01.toDataRow());
        }
    }//GEN-LAST:event_txt_timKiem01KeyReleased

    private void btn_LamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoi1ActionPerformed
        listKhachHang01 = KH.getall01();
        showTable2(listKhachHang01);
        LBL_SOLUONG.setText("Tổng số hoá đơn là : " + listKhachHang01.size());

    }//GEN-LAST:event_btn_LamMoi1ActionPerformed

    private void TB_bang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_bang1MouseClicked
        // TODO add your handling code here:
        int id = layid();
        showTable3(listKhachHang01);
        listKhachHang01 = KH.GetTKTheoIDKH(id);
        LBL_SOLUONG.setText("Tổng số hoá đơn là : " + KH.GetTKTheoIDKH(id).size());

        int row = TB_bang1.getSelectedRow();
        KhachHangViewMD kh = listKhachHang.get(row);
        txt_Ten.setText(kh.getTen());
        txt_tenDem.setText(kh.getTendem());
        txt_Ho.setText(kh.getHo());
        String gt = (TB_bang1.getValueAt(row, 2).toString());
        if (gt == "Nam") {
            rd_Nam.setSelected(true);
        } else {
            rd_nu.setSelected(true);
        }
        date_ngaysinh1.setDate((Date) TB_bang1.getValueAt(row, 3));
        txt_sdt.setText(kh.getSdt());
        txt_email.setText(kh.getEmail());
    }//GEN-LAST:event_TB_bang1MouseClicked

    private void txt_timKiem01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiem01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timKiem01ActionPerformed


}


