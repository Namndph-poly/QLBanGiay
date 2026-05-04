/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import models.ChatLieu;
import models.ChiTietSP;
import models.DanhMucSP;
import models.KhuyenMai;
import models.KichCo;
import models.MauSac;
import models.NSX;
import models.ThuongHieu;
import org.bridj.cpp.com.OLEAutomationLibrary;
import services.IChatLieuServices;
import services.IChiTietSPServices;
import services.IDanhMucSPServices;
import services.IKhuyenmaiService;
import services.IKichCoServices;
import services.IMauSacServices;
import services.INSXServices;
import services.IThuongHieuServices;
import services.imp.ChatLieuServices;
import services.imp.ChiTietSPServices;
import services.imp.DanhMucSPServices;
import services.imp.KhuyenmaiService;
import services.imp.KichCoServices;
import services.imp.MauSacServices;
import services.imp.NSXServices;
import services.imp.ThuongHieuServices;
import viewmodels.ChiTietSPViewModel;
import viewmodels.KhuyenmaiViewmodel;
import viewmodels.SanPhamViewModel;

/**
 *
 * @author hungh
 */
public class frm_Sanpham extends javax.swing.JPanel {

    private IChiTietSPServices iChiTietSPServices;
    private INSXServices iNSXServices;
    private IMauSacServices iMauSacServices;
    private IDanhMucSPServices iDanhMucSPServices;
    private IKichCoServices iKichCoServices;
    private IChatLieuServices iChatLieuServices;
    private IThuongHieuServices iThuongHieuServices;
    private IKhuyenmaiService iKhuyenmaiService;
    DefaultTableModel defaultTableModel;
    private boolean hish = false;
    String sp = null;

    // Components
    private swing.PanelGradiente panelGradiente1;
    private swing.PanelBorder panelBorder1, panelBorder2, panelBorder3;
    private swing.MyTextField txt_ma, txt_ten, txt_soluongton, txt_gianhap, txt_giaban;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JComboBox<String> cbo_nsx, cbo_mausac, cbo_danhmuc, cbo_size, cbo_chatlieu, cbo_thuonghieu1;
    private swing.MyButton btn_them, btn_capnhat, btn_lammoi, btn_xoa, myButton1;
    private javax.swing.JTable tbl_sp;
    private swing.SearchText searchText;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8;
    private javax.swing.JLabel jLabel10, jLabel11, jLabel12, jLabel13, jLabel14, jLabel15, hideshow;
    private javax.swing.JScrollPane jScrollPane1, jScrollPane4;

    public frm_Sanpham() {
        initComponents();
        innitTable();
//        listctsp = iChiTietSPServices.getAll();
        iChiTietSPServices = new ChiTietSPServices();
        iNSXServices = new NSXServices();
        iMauSacServices = new MauSacServices();
        iDanhMucSPServices = new DanhMucSPServices();
        iKichCoServices = new KichCoServices();
        iChatLieuServices = new ChatLieuServices();
        iThuongHieuServices = new ThuongHieuServices();
        iKhuyenmaiService = new KhuyenmaiService();
        initcbo();
        loadData(iChiTietSPServices.getAll());

    }

    public void initcbo() {
        List<NSX> listnsx = iNSXServices.getAll();
        cbo_nsx.setModel(new DefaultComboBoxModel(listnsx.toArray()));

        List<MauSac> listms = iMauSacServices.getAll();
        cbo_mausac.setModel(new DefaultComboBoxModel(listms.toArray()));

        List<DanhMucSP> listdmsp = iDanhMucSPServices.getAll();
        cbo_danhmuc.setModel(new DefaultComboBoxModel(listdmsp.toArray()));

        List<KichCo> listkc = iKichCoServices.getAll();
        cbo_size.setModel(new DefaultComboBoxModel(listkc.toArray()));

        List<ChatLieu> listcl = iChatLieuServices.getAll();
        cbo_chatlieu.setModel(new DefaultComboBoxModel(listcl.toArray()));

        List<ThuongHieu> listth = iThuongHieuServices.getAll();
        cbo_thuonghieu1.setModel(new DefaultComboBoxModel(listth.toArray()));

    }

    private int getindexmausac(ChiTietSPViewModel x) {
        List<MauSac> lst = iMauSacServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getMausac().getId()) {
                index = i;
            }
        }
        return index;
    }

    private void xuatbarcode(ChiTietSPViewModel x) {
        System.out.println(x.toString());
        try {
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(x.getQrcode());
            barcode.setI(11.0f);
            barcode.renderBarcode("D:\\QRcode\\" + x.getTen() + ".png");
            System.out.println("xuất thành công");
        } catch (Exception e) {
            System.out.println("xuất thất bại");
        }
    }


    private int getindexnsx(ChiTietSPViewModel x) {
        List<NSX> lst = iNSXServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getNsx().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexdanhmuc(ChiTietSPViewModel x) {
        List<DanhMucSP> lst = iDanhMucSPServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getDanhmuc().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexsize(ChiTietSPViewModel x) {
        List<KichCo> lst = iKichCoServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getKichco().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexthuonghieu(ChiTietSPViewModel x) {
        List<ThuongHieu> lst = iThuongHieuServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getThuonghieu().getId()) {
                index = i;
            }
        }
        return index;
    }

    private int getindexchatlieu(ChiTietSPViewModel x) {
        List<ChatLieu> lst = iChatLieuServices.getAll();
        int index = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).getId() == x.getChatlieu().getId()) {
                index = i;
            }
        }
        return index;

    }

    private String zenbarcode() {
        Random random = new Random();
        int ran = random.nextInt(999999);
        int dom = random.nextInt(999999);
        return ran + "" + dom;
    }

    private ChiTietSPViewModel getdadtafrom() {
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txt_ma.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm!");
            return null;

        }
        if (txt_ma.getText().length() > 15) {
            JOptionPane.showMessageDialog(this, "Mã sản phẩm không quá 15 kí tự!");
            return null;
        }
//  tên sp
        if (txt_ten.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên sản phẩm!");
            return null;
        }

        if (p.matcher(txt_ten.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm phải là chữ!");
            return null;
        }

        if (txt_ten.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không quá 30 kí tự!");
            return null;
        }
// Số lượng tồn
        if (txt_soluongton.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số lượng tồn!");
            return null;
        }

        try {
            Integer.valueOf(txt_soluongton.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số!");
            return null;
        }

// giá nhập
        if (txt_gianhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá nhập!");
            return null;
        }

        try {
            Double.valueOf(txt_gianhap.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số!");
            return null;
        }

        if (Double.valueOf(txt_gianhap.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải lớn hơn 0!");
            return null;
        }
// giá bán
        if (txt_giaban.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá bán!");
            return null;
        }

        try {
            Double.valueOf(txt_giaban.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số!");
            return null;
        }

        if (Double.valueOf(txt_giaban.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Giá bán phải lớn hơn 0!");
            return null;
        }
///////////////
        String mota = txt_mota.getText();
        if (txt_mota.getText().equals("")) {
            mota = "không có";
        }
        NSX nsx = (NSX) cbo_nsx.getSelectedItem();
        DanhMucSP danhmuc = (DanhMucSP) cbo_danhmuc.getSelectedItem();
        MauSac mausac = (MauSac) cbo_mausac.getSelectedItem();
        ChatLieu chatlieu = (ChatLieu) cbo_chatlieu.getSelectedItem();
        ThuongHieu thuonghieu = (ThuongHieu) cbo_thuonghieu1.getSelectedItem();
        KichCo kichco = (KichCo) cbo_size.getSelectedItem();
        double gianhap;
        double giaban;
        int soluong;
        try {
            gianhap = Double.parseDouble(txt_gianhap.getText());
            giaban = Double.parseDouble(txt_giaban.getText());
            soluong = Integer.parseInt(txt_soluongton.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi");
        }
        gianhap = Double.parseDouble(txt_gianhap.getText());
        giaban = Double.parseDouble(txt_giaban.getText());
        soluong = Integer.parseInt(txt_soluongton.getText());
        ChiTietSPViewModel ctsp = new ChiTietSPViewModel(txt_ma.getText(), txt_ten.getText(), nsx, mausac, danhmuc, kichco, chatlieu, thuonghieu, null, soluong, gianhap, giaban, mota, zenbarcode());
        return ctsp;
    }

    private ChiTietSPViewModel getdataTB(int row) {
        String ma = tbl_sp.getValueAt(row, 0).toString();
        String ten = tbl_sp.getValueAt(row, 1).toString();
        NSX nsx = (NSX) tbl_sp.getValueAt(row, 2);
        MauSac ms = (MauSac) tbl_sp.getValueAt(row, 3);
        DanhMucSP dmsp = (DanhMucSP) tbl_sp.getValueAt(row, 4);
        KichCo kc = (KichCo) tbl_sp.getValueAt(row, 5);
        ChatLieu cl = (ChatLieu) tbl_sp.getValueAt(row, 6);
        ThuongHieu th = (ThuongHieu) tbl_sp.getValueAt(row, 7);
        KhuyenMai km = (KhuyenMai) tbl_sp.getValueAt(row, 8);
        int soluong = (int) tbl_sp.getValueAt(row, 9);
        double gianhap = (double) tbl_sp.getValueAt(row, 10);
        double giaban = (double) tbl_sp.getValueAt(row, 11);
        String mota = tbl_sp.getValueAt(row, 13).toString();
        String barcode = tbl_sp.getValueAt(row, 14).toString();
        return new ChiTietSPViewModel(ma, ten, nsx, ms, dmsp, kc, cl, th, km, soluong, gianhap, giaban, mota, barcode);
    }

    private void initComponents() {
        setLayout(null);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 640));
    }

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {
        loadData(iChiTietSPServices.getAll());
        clearForm();
    }

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {
        ChiTietSPViewModel x = getdadtafrom();
        if (x == null) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm sản phẩm ?", "Thêm sản phẩm mới", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            if (Integer.valueOf(txt_soluongton.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn phải lớn hơn 0!");
                return;
            }
            boolean kq = iChiTietSPServices.Add(x);
            if (kq == true) {
                loadData(iChiTietSPServices.getAll());
                String data = x.getQrcode();
                String path = "D:\\QRcode\\Qr" + x.getTen() + ".png";
                Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                generateQRcode(data, path, hashMap, 200, 200);
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
            }
        }
    }

    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tbl_sp.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, " Bạn cần chọn 1 dòng để cập nhật");
            return;
        }
        String ma = (String) tbl_sp.getValueAt(row, 0);
        ChiTietSPViewModel x = getdadtafrom();
        if (x == null) {
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có chắc muốn cập nhật lại sản phẩm ?", "Update", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            boolean kq = iChiTietSPServices.Update(ma, x);
            if (kq == true) {
                loadData(iChiTietSPServices.getAll());
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Update", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Update", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tbl_sp.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "chọn sản phẩm cần in barcode");
            return;
        }
        ChiTietSPViewModel x = getdataTB(row);
        String data = x.getQrcode();
        String path = "D:\\QRcode\\Qr" + x.getTen() + ".png";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        generateQRcode(data, path, hashMap, 200, 200);
        JOptionPane.showMessageDialog(this, "In QR Code thành công");
    }

    private void tbl_spMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tbl_sp.getSelectedRow();
        if (row == -1) {
            return;
        }
        ChiTietSPViewModel x = getdataTB(row);
        txt_ma.setText(x.getMa());
        txt_ten.setText(x.getTen());
        txt_soluongton.setText(x.getSoluongton() + "");
        txt_giaban.setText(x.getGiaban() + "");
        txt_gianhap.setText(x.getGianhap() + "");
        txt_mota.setText(x.getMota());
        cbo_chatlieu.setSelectedIndex(getindexchatlieu(x));
        cbo_danhmuc.setSelectedIndex(getindexdanhmuc(x));
        cbo_mausac.setSelectedIndex(getindexmausac(x));
        cbo_nsx.setSelectedIndex(getindexnsx(x));
        cbo_size.setSelectedIndex(getindexsize(x));
        cbo_thuonghieu1.setSelectedIndex(getindexthuonghieu(x));
    }

    private void hideshowMouseClicked(java.awt.event.MouseEvent evt) {
        if (hish == false) {
            hish = true;
            loadData(iChiTietSPServices.getSPhet());
        } else {
            hish = false;
            loadData(iChiTietSPServices.getAll());
        }
    }

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {
        new frm_themthuoctinh(new javax.swing.JFrame(), true).setVisible(true);
    }

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {
        loadData(iChiTietSPServices.getlistbyTen("%" + searchText.getText() + "%"));
    }

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        initcbo();
    }

    private void innitTable() {
        defaultTableModel = (DefaultTableModel) tbl_sp.getModel();
        defaultTableModel.getRowCount();
        defaultTableModel.addColumn("Mã");
        defaultTableModel.addColumn("Tên");
        defaultTableModel.addColumn("NSX");
        defaultTableModel.addColumn("Màu sắc");
        defaultTableModel.addColumn("Danh mục");
        defaultTableModel.addColumn("Size");
        defaultTableModel.addColumn("Chất liệu");
        defaultTableModel.addColumn("Thương hiệu");
        defaultTableModel.addColumn("Khuyến mãi");
        defaultTableModel.addColumn("Số lượng tồn");
        defaultTableModel.addColumn("Giá nhập");
        defaultTableModel.addColumn("Giá bán");
        defaultTableModel.addColumn("Được giảm");
        defaultTableModel.addColumn("Mô tả");
        defaultTableModel.addColumn("Mã Qr");
    }

    private void loadData(List<ChiTietSPViewModel> lst) {
        defaultTableModel = (DefaultTableModel) tbl_sp.getModel();
        defaultTableModel.setRowCount(0);
        for (ChiTietSPViewModel x : lst) {
            defaultTableModel.addRow(new Object[]{
                x.getMa(),
                x.getTen(),
                x.getNsx(),
                x.getMausac(),
                x.getDanhmuc(),
                x.getKichco(),
                x.getChatlieu(),
                x.getThuonghieu(),
                x.getKhuyenmai(),
                x.getSoluongton(),
                x.getGianhap(),
                x.getGiaban(),
                String.format("%.0f", x.getKhuyenmai().getGiaTriGiam()) + " " + x.getKhuyenmai().getHinhThucKM(),
                x.getMota(),
                x.getQrcode()
            });
        }
    }

    public void clearForm() {
        txt_ma.setText("");
        txt_ten.setText("");
        txt_soluongton.setText("");
        txt_gianhap.setText("");
        txt_giaban.setText("");
        txt_mota.setText("");
    }

    public static void generateQRcode(String data, String path, Map map, int h, int w) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, w, h);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
        } catch (Exception ex) {
            Logger.getLogger(frm_Sanpham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
