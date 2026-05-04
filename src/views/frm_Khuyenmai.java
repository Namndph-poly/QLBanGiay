/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.KhuyenMai;
import repositorys.IKhuyenmaiRepository;
import repositorys.imp.KhuyenmaiReponsitory;
import services.IChiTietSPServices;
import services.IKhuyenmaiService;
import services.imp.ChiTietSPServices;
import services.imp.KhuyenmaiService;
import viewmodels.ChiTietSPViewModel;
import viewmodels.KhuyenmaiViewmodel;

/**
 *
 * @author hungh
 */
public class frm_Khuyenmai extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModel1;
    private IKhuyenmaiService khuyenmaiService;
    IChiTietSPServices chiTietSPServices;

    // Components
    private swing.MyTextField txt_tenkm, txt_giatrgiam;
    private swing.MyButton btn_them, btn_sua, btn_clear;
    private javax.swing.JTable tb_khuyenmai, tb_sp;
    private javax.swing.JCheckBox cb_selectAll;
    private com.toedter.calendar.JDateChooser date_BD, date_KT, dateTK_BD, dateTK_KT;
    private javax.swing.JRadioButton rd_VND, rd_phantram;
    private javax.swing.ButtonGroup buttonGroup1;
    private swing.SearchText src_timkiem;
    private javax.swing.JLabel lbl_timkiem;
    private javax.swing.JScrollPane jScrollPane1, jScrollPane2;

    /**
     * Creates new form khuyenmai
     */
    public frm_Khuyenmai() {
        initComponents();
        defaultTableModel = (DefaultTableModel) tb_khuyenmai.getModel();
        defaultTableModel1 = (DefaultTableModel) tb_sp.getModel();
        khuyenmaiService = new KhuyenmaiService();
        chiTietSPServices = new ChiTietSPServices();
        khuyenmaiService.UpdateTT();
        khuyenmaiService.UpdateTT2();
        LoadData();
        LoadDataSP();
    }

    void LoadData() {
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (KhuyenmaiViewmodel x : khuyenmaiService.GetALL()) {
            defaultTableModel.addRow(new Object[]{
                stt,
                x.getTenKM(),
                x.getNgayBatDau(),
                x.getNgayKetThuc(),
                String.format("%.0f", x.getGiaTriGiam()) + " " + x.getHinhThucKM(),
                x.getTrangthai() == 0 ? "Còn hạn" : "Hết hạn"
            });
            stt++;
        }
    }

    void LoadDataSP() {
        defaultTableModel1.setRowCount(0);
        for (ChiTietSPViewModel x : chiTietSPServices.GetAll()) {
            defaultTableModel1.addRow(new Object[]{
                false,
                x.getMa(),
                x.getTen()
            });
        }
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 640));

        // Initialize components
        txt_tenkm = new swing.MyTextField();
        txt_giatrgiam = new swing.MyTextField();
        btn_them = new swing.MyButton();
        btn_sua = new swing.MyButton();
        btn_clear = new swing.MyButton();
        tb_khuyenmai = new javax.swing.JTable();
        tb_sp = new javax.swing.JTable();
        cb_selectAll = new javax.swing.JCheckBox();
        date_BD = new com.toedter.calendar.JDateChooser();
        date_KT = new com.toedter.calendar.JDateChooser();
        dateTK_BD = new com.toedter.calendar.JDateChooser();
        dateTK_KT = new com.toedter.calendar.JDateChooser();
        rd_VND = new javax.swing.JRadioButton();
        rd_phantram = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        src_timkiem = new swing.SearchText();
        lbl_timkiem = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();

        // Table model for tb_khuyenmai
        defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "STT", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Giá trị giảm", "Trạng thái"
            }
        );
        tb_khuyenmai.setModel(defaultTableModel);
        tb_khuyenmai.setRowHeight(25);
        tb_khuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_khuyenmaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_khuyenmai);

        // Table model for tb_sp
        defaultTableModel1 = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Select", "Mã sản phẩm", "Tên sản phẩm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        tb_sp.setModel(defaultTableModel1);
        jScrollPane2.setViewportView(tb_sp);

        // Button listeners
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        cb_selectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_selectAllActionPerformed(evt);
            }
        });

        // Date choosers
        date_BD.setDateFormatString("dd/MM/yyyy");
        date_KT.setDateFormatString("dd/MM/yyyy");
        dateTK_BD.setDateFormatString("dd/MM/yyyy");
        dateTK_KT.setDateFormatString("dd/MM/yyyy");

        // Radio buttons
        buttonGroup1.add(rd_VND);
        buttonGroup1.add(rd_phantram);

        // Search
        src_timkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                src_timkiemCaretUpdate(evt);
            }
        });

        lbl_timkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_timkiemMouseClicked(evt);
            }
        });
    }

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        Pattern p = Pattern.compile("^[0-9]+$");
        try {
            if (txt_tenkm.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên khuyến mãi");
                return;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            if (date_BD.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày bắt đầu");
                return;
            }
        } catch (Exception e) {
        }
        try {
            if (date_KT.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày kết thúc");
                return;
            }
        } catch (Exception e) {
        }
        try {
            if (!rd_VND.isSelected() && !rd_phantram.isSelected()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn hình thức giảm giá");
                return;
            }
        } catch (Exception e) {
        }

        try {
            if (txt_giatrgiam.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá trị giảm");
                return;
            }
        } catch (Exception e) {
        }

        try {
            Integer.valueOf(txt_giatrgiam.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm phải là số!");
            return;
        }

        try {
            if (date_KT.getDate().before(date_BD.getDate())) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                return;
            }
        } catch (Exception e) {
        }
        if (p.matcher(txt_tenkm.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên không được nhập số");
            return;
        }
        if (txt_tenkm.getText().length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên không được quá 50 kí tự");
            return;
        }
        if (khuyenmaiService.checktrung(txt_tenkm.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Tên khuyến mãi đã tồn tại");
            return;
        }
        
             if (rd_phantram.isSelected()) {
            if (Integer.parseInt(txt_giatrgiam.getText().trim()) >= 100) {
                JOptionPane.showMessageDialog(this, "Giá trị khuyến mãi phải nhỏ hơn 100% !!!");
                return;
            }
        }
             
                 if (Integer.parseInt(txt_giatrgiam.getText().trim()) < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị khuyến mãi không được phép âm");
                return;
            }
                 if (Integer.parseInt(txt_giatrgiam.getText().trim()) == 0) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập giá trị khuyến mãi lớn hơn 0");
                return;
            }
        
        IKhuyenmaiRepository repository = new KhuyenmaiReponsitory();
        List<KhuyenMai> lst = repository.GetAll();
        long time = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(time);

        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Add", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            KhuyenmaiViewmodel km = new KhuyenmaiViewmodel();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(date_BD.getDate());
            String date2 = sdf.format(date_KT.getDate());
            km.setNgayBatDau(date1);
            km.setNgayKetThuc(date2);
            km.setTenKM(txt_tenkm.getText());
            if (rd_VND.isSelected()) {
                km.setHinhThucKM("VND");
            } else if (rd_phantram.isSelected()) {
                km.setHinhThucKM("%");
            }
            km.setGiaTriGiam(Double.parseDouble(txt_giatrgiam.getText()));
            khuyenmaiService.Add(km);
            LoadData();
            if (date.before(chiTietSPServices.checkngay(lst.get(lst.size() - 1).getID()))) {
                JOptionPane.showMessageDialog(this, "khuyến mãi chưa đến ngày áp dụng vui lòng xem và chọn khuyến mãi khác");
                return;
            }
            for (int i = 0; i < tb_sp.getRowCount(); i++) {
                boolean ischeckbox = (boolean) tb_sp.getValueAt(i, 0);
                if (ischeckbox) {
                    System.out.println(tb_sp.getValueAt(i, 1));
                    chiTietSPServices.Update(lst.get(lst.size() - 1).getID(), tb_sp.getValueAt(i, 1).toString());
                }
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        int r = tb_khuyenmai.getSelectedRow();
        IKhuyenmaiRepository repository = new KhuyenmaiReponsitory();
        List<KhuyenMai> lst = repository.GetAll();

        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào");
            return;
        }
              if (rd_phantram.isSelected()) {
            if (Integer.parseInt(txt_giatrgiam.getText().trim()) >= 100) {
                JOptionPane.showMessageDialog(this, "Giá trị khuyến mãi phải nhỏ hơn 100% !!!");
                return;
            }
        }
                          if (Integer.parseInt(txt_giatrgiam.getText().trim()) < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị khuyến mãi không được phép âm");
                return;
            }
                 if (Integer.parseInt(txt_giatrgiam.getText().trim()) == 0) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập giá trị khuyến mãi lớn hơn 0");
                return;
            }
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "Update", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            KhuyenmaiViewmodel km = new KhuyenmaiViewmodel();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(date_BD.getDate());
            String date2 = sdf.format(date_KT.getDate());
            km.setNgayBatDau(date1);
            km.setNgayKetThuc(date2);
            km.setTenKM(txt_tenkm.getText());
            if (rd_VND.isSelected()) {
                km.setHinhThucKM("VND");
            } else if (rd_phantram.isSelected()) {
                km.setHinhThucKM("%");
            }
            km.setGiaTriGiam(Double.parseDouble(txt_giatrgiam.getText()));
            if (date_KT.getDate().before(date_BD.getDate())) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu");
                return;
            }
            
            long time = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(time);
            if (date.before(chiTietSPServices.checkngay(lst.get(r).getID()))) {
                JOptionPane.showMessageDialog(this, "khuyến mãi chưa đến ngày áp dụng vui lòng xem và chọn khuyến mãi khác");
                return;
            }
            for (int i = 0; i < tb_sp.getRowCount(); i++) {
                boolean ischeckbox = (boolean) tb_sp.getValueAt(i, 0);
                if (ischeckbox) {
                    System.out.println(tb_sp.getValueAt(i, 1));
                    chiTietSPServices.Update(lst.get(r).getID(), tb_sp.getValueAt(i, 1).toString());
                }

            }
            khuyenmaiService.Update(km, lst.get(r).getID());
            khuyenmaiService.UpdateTT2();
            LoadData();
            JOptionPane.showMessageDialog(this, "Sửa thành công ");

        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        txt_tenkm.setText("");
        txt_giatrgiam.setText("");
        date_BD.setCalendar(null);
        date_KT.setCalendar(null);
        dateTK_BD.setCalendar(null);
        dateTK_KT.setCalendar(null);
        buttonGroup1.clearSelection();
        src_timkiem.setText("");
        khuyenmaiService.UpdateTT();
        khuyenmaiService.UpdateTT2();
        LoadData();


    }//GEN-LAST:event_btn_clearActionPerformed

    private void tb_khuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_khuyenmaiMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int r = tb_khuyenmai.getSelectedRow();
            txt_tenkm.setText((String) tb_khuyenmai.getValueAt(r, 1));
//            txt_giatrgiam.setText((String) tb_khuyenmai.getValueAt(r, 4));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse((String) tb_khuyenmai.getValueAt(r, 2));
            Date date2 = sdf.parse((String) tb_khuyenmai.getValueAt(r, 3));
            date_BD.setDate(date1);
            date_KT.setDate(date2);
            String hinhthuc = tb_khuyenmai.getValueAt(r, 4).toString();
            String giatri = tb_khuyenmai.getValueAt(r, 4).toString();
            if (hinhthuc.contains("%")) {
                rd_phantram.setSelected(true);
            } else if (hinhthuc.contains("VND")) {
                rd_VND.setSelected(true);
            }
            if (giatri.contains("%")) {
                int index = giatri.indexOf("%");
                txt_giatrgiam.setText(giatri.substring(0, index).trim());
            } else if (giatri.contains("VND")) {
                int index = giatri.indexOf("VND");
                txt_giatrgiam.setText(giatri.substring(0, index).trim());
            }
        } catch (ParseException ex) {
//            Logger.getLogger(FrmKhuyenmai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tb_khuyenmaiMouseClicked

    private void lbl_timkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_timkiemMouseClicked
        // TODO add your handling code here:
        if (dateTK_BD.getDate() == null && dateTK_KT.getDate() == null && src_timkiem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày bắt đầu hoặc ngày kết thúc hoặc tên khuyến mãi để tìm kiếm");
            return;
        }
        if (dateTK_BD.getDate() != null && dateTK_KT.getDate() == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = sdf.format(dateTK_BD.getDate());
            if (khuyenmaiService.GetOnebyBD(date3).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có khuyến mãi nào !");
                return;
            }
            defaultTableModel.setRowCount(0);
            int stt = 1;
            for (KhuyenmaiViewmodel x : khuyenmaiService.GetOnebyBD(date3)) {
                defaultTableModel.addRow(new Object[]{
                    stt,
                    x.getTenKM(),
                    x.getNgayBatDau(),
                    x.getNgayKetThuc(),
                    String.format("%.0f", x.getGiaTriGiam()) + " " + x.getHinhThucKM(),
                    x.getTrangthai() == 0 ? "Còn hạn" : "Hết hạn"
                });
                stt++;
            }
            return;
        }
        if (dateTK_BD.getDate() == null && dateTK_KT.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date4 = sdf.format(dateTK_KT.getDate());
            if (khuyenmaiService.GetOnebyKT(date4).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có khuyến mãi nào");
                return;
            }
            defaultTableModel.setRowCount(0);
            int stt = 1;
            for (KhuyenmaiViewmodel x : khuyenmaiService.GetOnebyKT(date4)) {
                defaultTableModel.addRow(new Object[]{
                    stt,
                    x.getTenKM(),
                    x.getNgayBatDau(),
                    x.getNgayKetThuc(),
                    String.format("%.0f", x.getGiaTriGiam()) + " " + x.getHinhThucKM(),
                    x.getTrangthai() == 0 ? "Còn hạn" : "Hết hạn"
                });
                stt++;
            }
            return;
        }
        if (dateTK_BD.getDate() != null && dateTK_KT.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = sdf.format(dateTK_BD.getDate());
            String date4 = sdf.format(dateTK_KT.getDate());
            if (khuyenmaiService.GetOnebyALL(date3, date4).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có khuyến mãi nào");
                return;
            }
            defaultTableModel.setRowCount(0);
            int stt = 1;
            for (KhuyenmaiViewmodel x : khuyenmaiService.GetOnebyALL(date3, date4)) {
                defaultTableModel.addRow(new Object[]{
                    stt,
                    x.getTenKM(),
                    x.getNgayBatDau(),
                    x.getNgayKetThuc(),
                    String.format("%.0f", x.getGiaTriGiam()) + " " + x.getHinhThucKM(),
                    x.getTrangthai() == 0 ? "Còn hạn" : "Hết hạn"
                });
                stt++;
            }
            return;
        }
    }//GEN-LAST:event_lbl_timkiemMouseClicked

    private void src_timkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_src_timkiemCaretUpdate
        // TODO add your handling code here:
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (KhuyenmaiViewmodel x : khuyenmaiService.GetOnebyten("%" + src_timkiem.getText() + "%")) {
            defaultTableModel.addRow(new Object[]{
                stt,
                x.getTenKM(),
                x.getNgayBatDau(),
                x.getNgayKetThuc(),
                String.format("%.0f", x.getGiaTriGiam()) + " " + x.getHinhThucKM(),
                x.getTrangthai() == 0 ? "Còn hạn" : "Hết hạn"
            });
            stt++;
        }
    }//GEN-LAST:event_src_timkiemCaretUpdate

    private void cb_selectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_selectAllActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tb_sp.getRowCount(); i++) {
            if (cb_selectAll.isSelected() == true) {
                tb_sp.setValueAt(true, i, 0);
            } else {
                tb_sp.setValueAt(false, i, 0);
            }
        }
    }//GEN-LAST:event_cb_selectAllActionPerformed


}


