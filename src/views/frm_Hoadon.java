package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.IHoaDonServiec;
import services.imp.HoaDonServiec;
import viewmodels.HoaDonCHiTietViewModel;
import viewmodels.HoaDonViewModel;

public class frm_Hoadon extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    private HoaDonServiec HoaDonServiec;
    private HoaDonServiec HoaDonCTServiec;

    // Components
    private javax.swing.JTable tbl_hoadon;
    private javax.swing.JTable tbl_HoaDonCT;
    private swing.SearchText txt_tk;
    private javax.swing.JComboBox<String> cbo_trangthai;
    private com.toedter.calendar.JDateChooser Date_NgayTT;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;

    public frm_Hoadon() {
        initComponents();

        HoaDonServiec = new HoaDonServiec();
        HoaDonCTServiec = new HoaDonServiec();

        loatdate1();

    }

    private void loatdate1() {
        defaultTableModel = (DefaultTableModel) tbl_hoadon.getModel();
        defaultTableModel.getRowCount();
        List<HoaDonViewModel> hdv = HoaDonServiec.getAllHD();
        for (HoaDonViewModel hoaDonViewModel : hdv) {
            defaultTableModel.addRow(new Object[]{
                hoaDonViewModel.getMa(),
                hoaDonViewModel.getUs().getTen(),
                hoaDonViewModel.getKh().getTen(),
                hoaDonViewModel.getTongTien(),
                hoaDonViewModel.getNgayTao(),
                hoaDonViewModel.getNgayThanhToan(),
                hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                hoaDonViewModel.getGhiChu(),});
        }

    }

    public void TKTenHoaDOn() {

//        if (txt_tk.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tên Cần Tìm");
//            return;
//        }
//        if (HoaDonService.getTimHDTen(txt_tk.getText()).isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Tên này không có trong danh sách");
//            return;
//        }
        defaultTableModel = (DefaultTableModel) tbl_hoadon.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonViewModel> list = HoaDonServiec.getTimHDTen("%" + txt_tk.getText() + "%");
        for (HoaDonViewModel hoaDonViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                hoaDonViewModel.getMa(),
                hoaDonViewModel.getUs().getTen(),
                hoaDonViewModel.getKh().getTen(),
                hoaDonViewModel.getTongTien(),
                hoaDonViewModel.getNgayTao(),
                hoaDonViewModel.getNgayThanhToan(),
                hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                hoaDonViewModel.getGhiChu(),});

        }
    }

    public void TKTTHoaDOn() {

        int tt = cbo_trangthai.getSelectedIndex();
        defaultTableModel = (DefaultTableModel) tbl_hoadon.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonViewModel> list = HoaDonServiec.getTimHDTrangThai((int) tt);
        for (HoaDonViewModel hoaDonViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                hoaDonViewModel.getMa(),
                hoaDonViewModel.getUs().getTen(),
                hoaDonViewModel.getKh().getTen(),
                hoaDonViewModel.getTongTien(),
                hoaDonViewModel.getNgayTao(),
                hoaDonViewModel.getNgayThanhToan(),
                hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                hoaDonViewModel.getGhiChu(),});
        }
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 640));

        // Initialize components
        tbl_hoadon = new javax.swing.JTable();
        tbl_HoaDonCT = new javax.swing.JTable();
        txt_tk = new swing.SearchText();
        cbo_trangthai = new javax.swing.JComboBox<>();
        Date_NgayTT = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();

        // Initialize table models
        defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "MA", "TÊN NHÂN VIÊN", "TÊN KHÁCH HÀNG", "TỔNG TIỀN", "NGÀY TẠO", "NGÀY TT", "TRẠNG THÁI", "GHI CHÚ"
            }
        );
        tbl_hoadon.setModel(defaultTableModel);
        tbl_hoadon.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_hoadon);

        javax.swing.table.DefaultTableModel modelCT = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Mã Hóa Đơn", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "Thành Tiền"
            }
        );
        tbl_HoaDonCT.setModel(modelCT);
        tbl_HoaDonCT.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tbl_HoaDonCT);

        // ComboBox
        cbo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán" }));
        cbo_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_trangthaiActionPerformed(evt);
            }
        });

        // SearchText
        txt_tk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tkKeyReleased(evt);
            }
        });
        txt_tk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tkMouseClicked(evt);
            }
        });

        // DateChooser
        Date_NgayTT.setDateFormatString("dd/MM/yyyy");
        Date_NgayTT.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Date_NgayTTAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        // Label
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_24px.png")));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
    }

    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        // TODO add your handling code here:
//        if (JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xem Hóa Đơn Chi Tiết Không !") != JOptionPane.YES_OPTION) {
//            return;
//        }

        int row = tbl_hoadon.getSelectedRow();
        defaultTableModel = (DefaultTableModel) tbl_HoaDonCT.getModel();
        defaultTableModel.setRowCount(0);
        List<HoaDonCHiTietViewModel> list = HoaDonCTServiec.gettimma(tbl_hoadon.getValueAt(row, 0).toString());
        for (HoaDonCHiTietViewModel CT : list) {
            defaultTableModel.addRow(new Object[]{
                CT.getHaDon().getMa(),
                CT.getSanPham().getMa(),
                CT.getSanPham().getTen(),
                CT.getSoluong(),
                CT.getDonGia(),
                CT.getThanhTien()});
        }

    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void txt_tkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tkMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_txt_tkMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        if (Date_NgayTT.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = sdf.format(Date_NgayTT.getDate());
            if (HoaDonServiec.GetTimNTT(date3).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không Có Ngày Thanh Toán Nào");
                return;
            }
            defaultTableModel.setRowCount(0);
            List<HoaDonViewModel> list = HoaDonServiec.GetTimNTT(date3);
            for (HoaDonViewModel hoaDonViewModel : list) {
                defaultTableModel.addRow(new Object[]{
                    hoaDonViewModel.getMa(),
                    hoaDonViewModel.getUs().getTen(),
                    hoaDonViewModel.getKh().getTen(),
                    hoaDonViewModel.getTongTien(),
                    hoaDonViewModel.getNgayTao(),
                    hoaDonViewModel.getNgayThanhToan(),
                    hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                    hoaDonViewModel.getGhiChu(),});

            }

        }


    }//GEN-LAST:event_jLabel3MouseClicked

    private void cbo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_trangthaiActionPerformed
        // TODO add your handling code here:

        TKTTHoaDOn();
    }//GEN-LAST:event_cbo_trangthaiActionPerformed

    private void txt_tkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tkKeyReleased
        // TODO add your handling code here:
        TKTenHoaDOn();
    }//GEN-LAST:event_txt_tkKeyReleased

    private void Date_NgayTTAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Date_NgayTTAncestorAdded
        if (Date_NgayTT.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = sdf.format(Date_NgayTT.getDate());
            if (HoaDonServiec.GetTimNTT(date3).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không Có Ngày Thanh Toán Nào");
                return;
            }
            defaultTableModel.setRowCount(0);
            List<HoaDonViewModel> list = HoaDonServiec.GetTimNTT(date3);
            for (HoaDonViewModel hoaDonViewModel : list) {
                defaultTableModel.addRow(new Object[]{
                    hoaDonViewModel.getMa(),
                    hoaDonViewModel.getUs().getTen(),
                    hoaDonViewModel.getKh().getTen(),
                    hoaDonViewModel.getTongTien(),
                    hoaDonViewModel.getNgayTao(),
                    hoaDonViewModel.getNgayThanhToan(),
                    hoaDonViewModel.getTinhTrang() == 0 ? "Chưa thanh toán" : "Đã thanh toán",
                    hoaDonViewModel.getGhiChu(),});

            }

        }

    }//GEN-LAST:event_Date_NgayTTAncestorAdded


}


