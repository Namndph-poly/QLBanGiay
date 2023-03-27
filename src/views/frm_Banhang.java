/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.DanhMucSP;
import models.KhachHang;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import repositorys.IHoaDonRepostory;
import repositorys.imp.HoaDonRepostory;

//import repositorys.IHoaDonRepostory;
//import repositorys.imp.HoaDonRepostory;
import services.IDanhMucSPServices;
import services.IHoaDonServiec;
//import services.IHoaDonServiec;
import services.IKhachHangService;
import services.ISanPhamServiecs;
import services.imp.DanhMucSPServices;
import services.imp.HoaDonServiec;
//import services.imp.HoaDonServiec;
import services.imp.SanPhamServiec;
import services.imp.khahangsvImpl;

import viewmodels.GioHangViewModel;
import viewmodels.HoaDonCHiTietViewModel;
import viewmodels.HoaDonViewModel;
import viewmodels.KhachHangViewMD;
import viewmodels.SanPhamViewModel;

//import viewmodels.barCode;
/**
 *
 * @author hungh
 */
public class frm_Banhang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    private DefaultTableModel model;
    private DefaultTableModel modelGioHang;
    private DefaultComboBoxModel combox;
    private ISanPhamServiecs sanISamPhamServiecs;
    private IHoaDonServiec hoaDonServiec;
    private IKhachHangService khachHangService = new khahangsvImpl();
    private List<GioHangViewModel> listGioHang = new ArrayList<>();

    private Integer id;
    private String TenNhanVien;
    private IHoaDonRepostory hoaDonRepostory = new HoaDonRepostory();
    private IDanhMucSPServices danhMucSPServices = new DanhMucSPServices();

    public frm_Banhang(Integer idNhanVien, String TenNV) {
        initComponents();
        inintWebCam();

        chk_inHoaDon.setSelected(true);
        chk_inHoaDon.setSelected(true);
        model = new DefaultTableModel();
        modelGioHang = (DefaultTableModel) tb_gioHang.getModel();
        combox = new DefaultComboBoxModel();
        sanISamPhamServiecs = new SanPhamServiec();
        hoaDonServiec = new HoaDonServiec();

        TenNhanVien = TenNV;
        id = idNhanVien;
        getListSP();
        getListHoaDon();
        loadCBMau();

    }

    private void inintWebCam() {

        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
//        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 220, 150));
        executor.execute(this);
    }

    private void getListSP() {
        model = (DefaultTableModel) tb_sanPham.getModel();
        model.setRowCount(0);
        List<SanPhamViewModel> getList = sanISamPhamServiecs.getListSanPham();
        for (SanPhamViewModel x : getList) {
            model.addRow(new Object[]{
                x.getMa(),
                x.getTen(),
                x.getMauSac().getTen(),
                String.format("%.0f", x.getKhuyenMai().getGiaTriGiam()),
                x.getKhuyenMai().getHinhThucKM(),
                x.getChatLieu().getTen(),
                x.getKichCo().getTen(),
                String.format("%.0f", x.getGiaBan()),
                x.getSoLuongTon(),});
        }
    }

    private void getListGioHang() {
        modelGioHang = (DefaultTableModel) tb_gioHang.getModel();
        modelGioHang.setRowCount(0);
        for (GioHangViewModel x : listGioHang) {
            modelGioHang.addRow(new Object[]{
                x.getMaSP(),
                x.getTenSP(),
                x.getSoLuong(),
                String.format("%.0f", x.getDonGia()),
                String.format("%.0f", x.getThanhTien())
            });
        }
    }

    private void getListGioHangHDCT(String MaHD) {
        modelGioHang = (DefaultTableModel) tb_gioHang.getModel();
        modelGioHang.setRowCount(0);
        List<HoaDonCHiTietViewModel> list = hoaDonServiec.getListHoaDonChiTiet(MaHD);
        if (list.isEmpty()) {
            return;
        }
        for (HoaDonCHiTietViewModel x : list) {
            GioHangViewModel gioHang = new GioHangViewModel();
            gioHang.setMaSP(x.getSanPham().getMa());
            gioHang.setTenSP(x.getSanPham().getTen());
            gioHang.setSoLuong(x.getSoluong());
            gioHang.setDonGia(x.getDonGia());
            gioHang.setGiamGia(x.getSanPham().getKhuenMai().getGiaTriGiam());
            gioHang.setHinhThucGiamGia(x.getSanPham().getKhuenMai().getHinhThucKM());
            listGioHang.add(gioHang);
            getListGioHang();

        }
    }
        private String getTrangThaiHD(int TrangThai) {
        if (TrangThai == 0) {
            return "chờ thanh Toán";
        }
        if (TrangThai == 1) {
            return "Đã thanh Toán";
        }

        return null;
    }

    private void getListHoaDon() {
        model = (DefaultTableModel) tb_hoaDon.getModel();
        model.setRowCount(0);
        List<HoaDonViewModel> getList = hoaDonServiec.getListHD(0);
        for (HoaDonViewModel x : getList) {
            model.addRow(new Object[]{
                x.getMa(),
                x.getNgayTao(),
                x.getUs().getTen(),
                getTrangThaiHD(x.getTinhTrang())

            });
        }
    }
    
    private void clear() {
        lbl_sdt.setText("");
        txt_diem.setText("");
        lbl_tongTien1.setText(String.valueOf(0));
        lbl_giamGia1.setText(String.valueOf(0.0));
        lbl_thanhTien.setText(String.valueOf(0));
        lbl_diemThuong.setText(String.valueOf(0));
        txt_tienKhachDua.setText("");
        lbl_tienThua.setText("");
        txt_ghiChu.setText("");
        lbl_sdt.setText("");
        txt_diem.setText("");
        lbl_tenKhachHang.setText("");
    }
    
    private void loadCBMau() {
        combox = (DefaultComboBoxModel) cb_danhMuc.getModel();
        List<DanhMucSP> listDanhMuc = danhMucSPServices.getAll();
        listDanhMuc.forEach(danhMuc -> combox.addElement(danhMuc.getTen()));

    }
     private HoaDonViewModel inputHD() {
        HoaDonViewModel hd = new HoaDonViewModel();
        String Ma = "HD";
        Random random = new Random();
        hd.setMa(Ma + random.nextInt());

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hd.setNgayTao(date);

        return hd;
    }

    private HoaDonCHiTietViewModel inputHDCT(Double DonGia, int SoLuong) {
        HoaDonCHiTietViewModel hdct = new HoaDonCHiTietViewModel();
        hdct.setDonGia(DonGia);
        hdct.setSoluong(SoLuong);

        return hdct;
    }

    private void mouse() {
        int rowHD = tb_hoaDon.getSelectedRow();
        int row = tb_hoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        listGioHang.clear();
        String MaHD = tb_hoaDon.getValueAt(row, 0).toString();
        lbl_tongTien1.setText(String.valueOf(0));
        lbl_giamGia1.setText(String.valueOf(0));
        lbl_thanhTien.setText(String.valueOf(0));
        getListGioHangHDCT(MaHD);
        Double tongPT = 0.0;
        Double tongVN = 0.0;
        Double tongTien = 0.0;
        Double giam = Double.parseDouble(lbl_giamGia1.getText());
        int count = 0;
        List<HoaDonCHiTietViewModel> list = hoaDonServiec.getListHoaDonChiTiet(MaHD);
        for (HoaDonCHiTietViewModel x : list) {
            tongTien = tongTien + x.getThanhTien();
            lbl_tongTien1.setText(String.format("%.0f", tongTien));
            List<SanPhamViewModel> listSanPham = sanISamPhamServiecs.getListSanPham();
            if (tb_gioHang.getValueAt(count, 0).equals(x.getSanPham().getMa()) && x.getSanPham().getKhuenMai().getHinhThucKM().equals("%")) {
                tongPT = x.getThanhTien() * x.getSanPham().getKhuenMai().getGiaTriGiam() / 100;
                lbl_giamGia1.setText(String.valueOf(giam += tongPT));
                lbl_giamGia1.setText(String.format("%.0f", giam));
            } else {
                tongVN = x.getSanPham().getKhuenMai().getGiaTriGiam();
                lbl_giamGia1.setText(String.valueOf(giam += tongVN));
            }
            count++;
        }
        Double ThanhTien = Double.parseDouble(lbl_tongTien1.getText()) - Double.parseDouble(lbl_giamGia1.getText());
        lbl_thanhTien.setText(String.valueOf(String.format("%.0f", ThanhTien)));
        if (Integer.parseInt(lbl_thanhTien.getText()) >= 500000) {
            int diemThuong = Integer.parseInt(lbl_thanhTien.getText()) / 100000;
            lbl_diemThuong.setText(String.valueOf(diemThuong));
        } else {
            lbl_diemThuong.setText(String.valueOf(0));
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        panelGradiente1 = new swing.PanelGradiente();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_sanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cb_danhMuc = new javax.swing.JComboBox<>();
        panelBorder1 = new swing.PanelBorder();
        searchText1 = new swing.SearchText();
        jLabel3 = new javax.swing.JLabel();
        panelGradiente2 = new swing.PanelGradiente();
        btn_xoa = new swing.MyButton();
        btn_clear = new swing.MyButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_gioHang = new javax.swing.JTable();
        panelGradiente3 = new swing.PanelGradiente();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hoaDon = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelGradiente4 = new swing.PanelGradiente();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_diem = new swing.MyTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_tienKhachDua = new swing.MyTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_ghiChu = new javax.swing.JTextArea();
        chk_inHoaDon = new javax.swing.JCheckBox();
        btn_thanhToan = new swing.MyButton();
        btn_xacNhan = new swing.MyButton();
        btn_suDung = new swing.MyButton();
        lbl_thanhTien = new javax.swing.JLabel();
        lbl_tienThua = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_tongTien1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_giamGia1 = new javax.swing.JLabel();
        myButton9 = new swing.MyButton();
        lbl_tenKhachHang = new javax.swing.JLabel();
        lbl_diemThuong = new javax.swing.JLabel();
        btn_thayDoi = new swing.MyButton();
        jLabel6 = new javax.swing.JLabel();
        lbl_sdt = new javax.swing.JLabel();
        btn_taoHoaDon = new swing.MyButton();
        jLabel17 = new javax.swing.JLabel();

        jMenuItem1.setText("Thêm");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setBackground(new java.awt.Color(204, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 640));
        setName(""); // NOI18N

        panelGradiente1.setColorPrimario(new java.awt.Color(204, 255, 255));
        panelGradiente1.setColorSecundario(new java.awt.Color(204, 204, 255));

        tb_sanPham.setBackground(new java.awt.Color(255, 245, 255));
        tb_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Mằu Sắc", "Giảm Giá", "Hình Thức Giảm", "Chất Liệu", "Kích Cỡ", "Giá Bán", "Số Lượng"
            }
        ));
        tb_sanPham.setGridColor(new java.awt.Color(255, 255, 255));
        tb_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_sanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_sanPham);

        panelGradiente1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 60, 560, 190);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Sản phẩm");
        panelGradiente1.add(jLabel1);
        jLabel1.setBounds(10, 0, 100, 15);

        cb_danhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cb_danhMuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        cb_danhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_danhMucItemStateChanged(evt);
            }
        });
        panelGradiente1.add(cb_danhMuc);
        cb_danhMuc.setBounds(270, 20, 200, 30);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        searchText1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchText1CaretUpdate(evt);
            }
        });
        panelBorder1.add(searchText1);
        searchText1.setBounds(10, 0, 180, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_24px.png"))); // NOI18N
        jLabel3.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jLabel3AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        panelBorder1.add(jLabel3);
        jLabel3.setBounds(194, 0, 40, 30);

        panelGradiente1.add(panelBorder1);
        panelBorder1.setBounds(10, 20, 230, 30);

        panelGradiente2.setColorPrimario(new java.awt.Color(204, 255, 255));
        panelGradiente2.setColorSecundario(new java.awt.Color(204, 204, 255));

        btn_xoa.setBackground(new java.awt.Color(125, 224, 237));
        btn_xoa.setText("Xóa");
        btn_xoa.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });
        panelGradiente2.add(btn_xoa);
        btn_xoa.setBounds(490, 50, 100, 30);

        btn_clear.setBackground(new java.awt.Color(125, 224, 237));
        btn_clear.setText("Xoá Tất Cả");
        btn_clear.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        panelGradiente2.add(btn_clear);
        btn_clear.setBounds(490, 110, 100, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Giỏ hàng");
        panelGradiente2.add(jLabel2);
        jLabel2.setBounds(10, 0, 55, 15);

        tb_gioHang.setBackground(new java.awt.Color(255, 245, 255));
        tb_gioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        tb_gioHang.setComponentPopupMenu(jPopupMenu1);
        tb_gioHang.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(tb_gioHang);

        panelGradiente2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 20, 470, 150);

        panelGradiente3.setColorPrimario(new java.awt.Color(204, 255, 255));
        panelGradiente3.setColorSecundario(new java.awt.Color(204, 204, 255));

        tb_hoaDon.setBackground(new java.awt.Color(255, 245, 255));
        tb_hoaDon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_hoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tb_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Ngày Tạo", "Nhân Viên", "Trạng Thái"
            }
        ));
        tb_hoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.SW_RESIZE_CURSOR));
        tb_hoaDon.setGridColor(new java.awt.Color(255, 255, 255));
        tb_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tb_hoaDonMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tb_hoaDon);

        panelGradiente3.add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 370, 150);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Hóa đơn chờ");
        jLabel4.setToolTipText("");
        panelGradiente3.add(jLabel4);
        jLabel4.setBounds(10, 0, 90, 15);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelGradiente3.add(jPanel1);
        jPanel1.setBounds(380, 20, 220, 150);

        panelGradiente4.setColorPrimario(new java.awt.Color(204, 255, 255));
        panelGradiente4.setColorSecundario(new java.awt.Color(204, 204, 255));
        panelGradiente4.setMinimumSize(new java.awt.Dimension(1010, 640));
        panelGradiente4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Thanh toán");
        jLabel5.setToolTipText("");
        panelGradiente4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tên khách hàng");
        panelGradiente4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 100, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("SĐT khách hàng");
        panelGradiente4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 210, 20));

        txt_diem.setBackground(new java.awt.Color(244, 244, 254));
        txt_diem.setForeground(new java.awt.Color(0, 153, 153));
        panelGradiente4.add(txt_diem, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 200, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Điểm thưởng");
        panelGradiente4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 250, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Khách Hàng Được Điểm");
        panelGradiente4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 250, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Khách Cần Trả");
        panelGradiente4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 250, 20));

        txt_tienKhachDua.setBackground(new java.awt.Color(244, 244, 254));
        txt_tienKhachDua.setForeground(new java.awt.Color(255, 51, 51));
        txt_tienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_tienKhachDuaCaretUpdate(evt);
            }
        });
        panelGradiente4.add(txt_tienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 200, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tiền khách đưa");
        panelGradiente4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 250, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel13.setText("Ghi chú");
        panelGradiente4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 250, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Tiền thừa");
        panelGradiente4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 70, 20));

        txt_ghiChu.setBackground(new java.awt.Color(244, 244, 254));
        txt_ghiChu.setColumns(20);
        txt_ghiChu.setRows(3);
        txt_ghiChu.setTabSize(0);
        txt_ghiChu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jScrollPane4.setViewportView(txt_ghiChu);
        txt_ghiChu.getAccessibleContext().setAccessibleDescription("");

        panelGradiente4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 240, 90));

        chk_inHoaDon.setBackground(new java.awt.Color(204, 204, 255));
        chk_inHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chk_inHoaDon.setText("In hóa đơn");
        chk_inHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_inHoaDonActionPerformed(evt);
            }
        });
        panelGradiente4.add(chk_inHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 100, -1));

        btn_thanhToan.setBackground(new java.awt.Color(125, 224, 237));
        btn_thanhToan.setForeground(new java.awt.Color(0, 51, 102));
        btn_thanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/credit-card 3.png"))); // NOI18N
        btn_thanhToan.setText("Thanh toán");
        btn_thanhToan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });
        panelGradiente4.add(btn_thanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 310, 50));

        btn_xacNhan.setBackground(new java.awt.Color(125, 224, 237));
        btn_xacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check-mark.png"))); // NOI18N
        btn_xacNhan.setText("Xác Nhận");
        btn_xacNhan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_xacNhan.setRolloverEnabled(false);
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });
        panelGradiente4.add(btn_xacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 110, 30));

        btn_suDung.setBackground(new java.awt.Color(125, 224, 237));
        btn_suDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/circular-economy.png"))); // NOI18N
        btn_suDung.setText("Sử dụng");
        btn_suDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_suDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suDungActionPerformed(evt);
            }
        });
        panelGradiente4.add(btn_suDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 110, 30));

        lbl_thanhTien.setForeground(new java.awt.Color(255, 51, 51));
        lbl_thanhTien.setText("0");
        panelGradiente4.add(lbl_thanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 260, 20));

        lbl_tienThua.setForeground(new java.awt.Color(0, 153, 153));
        panelGradiente4.add(lbl_tienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 240, 20));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Tổng tiền");
        panelGradiente4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 250, 20));

        lbl_tongTien1.setForeground(new java.awt.Color(255, 51, 51));
        lbl_tongTien1.setText("0");
        panelGradiente4.add(lbl_tongTien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 240, 20));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Giảm Giá");
        panelGradiente4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 60, 20));

        lbl_giamGia1.setForeground(new java.awt.Color(255, 51, 51));
        lbl_giamGia1.setText("0");
        panelGradiente4.add(lbl_giamGia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 250, 20));

        myButton9.setBackground(new java.awt.Color(125, 224, 237));
        myButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        myButton9.setText("Làm Mới");
        myButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        myButton9.setMaximumSize(new java.awt.Dimension(101, 25));
        myButton9.setMinimumSize(new java.awt.Dimension(101, 25));
        myButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton9ActionPerformed(evt);
            }
        });
        panelGradiente4.add(myButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, 140, 40));

        lbl_tenKhachHang.setForeground(new java.awt.Color(0, 153, 153));
        panelGradiente4.add(lbl_tenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 120, 20));

        lbl_diemThuong.setForeground(new java.awt.Color(255, 51, 51));
        panelGradiente4.add(lbl_diemThuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 220, 20));

        btn_thayDoi.setBackground(new java.awt.Color(125, 224, 237));
        btn_thayDoi.setText("Khách Hàng");
        btn_thayDoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_thayDoi.setRolloverEnabled(false);
        btn_thayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thayDoiActionPerformed(evt);
            }
        });
        panelGradiente4.add(btn_thayDoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 110, 40));
        panelGradiente4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 220, -1));

        lbl_sdt.setForeground(new java.awt.Color(0, 153, 153));
        panelGradiente4.add(lbl_sdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 220, -1));

        btn_taoHoaDon.setBackground(new java.awt.Color(125, 224, 237));
        btn_taoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bill (2).png"))); // NOI18N
        btn_taoHoaDon.setText("Tạo Hoá Đơn");
        btn_taoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_taoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoHoaDonActionPerformed(evt);
            }
        });
        panelGradiente4.add(btn_taoHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 150, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excel (1).png"))); // NOI18N
        panelGradiente4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelGradiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelGradiente3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGradiente4, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGradiente3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(panelGradiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(panelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelGradiente4, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tb_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_sanPhamMouseClicked
int row = tb_sanPham.getSelectedRow();
        int rowHD = tb_hoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn hóa đơn để thêm sản phẩm!");
            return;
        }
        try {
            int NhapSoLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập Số Lượng!"));
            String MaSP = tb_sanPham.getValueAt(row, 0).toString();
            String TenSP = tb_sanPham.getValueAt(row, 1).toString();
            int SoLuong = Integer.parseInt(tb_sanPham.getValueAt(row, 8).toString());
            Double DonGia = Double.parseDouble(tb_sanPham.getValueAt(row, 7).toString());
            Double GiamGia = Double.parseDouble(tb_sanPham.getValueAt(row, 3).toString());
            String hinhThucGiamGia = tb_sanPham.getValueAt(row, 4).toString();
            List<HoaDonCHiTietViewModel> listh = hoaDonServiec.getListHoaDonChiTiet(tb_hoaDon.getValueAt(rowHD, 0).toString());
            if (SoLuong >= NhapSoLuong) {
                for (HoaDonCHiTietViewModel x : listh) {
                    if (x.getSanPham().getMa().equals(MaSP)) {
                        JOptionPane.showMessageDialog(this, "Sản Phẩm Đã Có Trên Giỏ Hàng");
                        return;
                    }
                }

                listGioHang.add(new GioHangViewModel(MaSP, TenSP, NhapSoLuong, DonGia, GiamGia, hinhThucGiamGia));
                getListGioHang();

                int kq = SoLuong - NhapSoLuong;
                sanISamPhamServiecs.updateSoLuongSP(MaSP, kq);
                List<SanPhamViewModel> list = sanISamPhamServiecs.getListSanPham();
                list.clear();
                getListSP();
                Double tongPT = 0.0;
                Double tongVN = 0.0;
                Double tongTien = 0.0;
                Double giam = Double.parseDouble(lbl_giamGia1.getText());
                int count = 0;
                for (GioHangViewModel x : listGioHang) {
                    tongTien = tongTien + x.getThanhTien();
                    lbl_tongTien1.setText(String.format("%.0f", tongTien));
                    if (tb_gioHang.getValueAt(count, 0).equals(MaSP) && x.getHinhThucGiamGia().equals("%")) {
                        tongPT = x.getThanhTien() * x.getGiamGia() / 100;
                        lbl_giamGia1.setText(String.valueOf(giam += tongPT));
                        lbl_giamGia1.setText(String.format("%.0f", giam));
                    } else {
                        tongVN = x.getGiamGia();
                        lbl_giamGia1.setText(String.valueOf(giam + tongVN));
                    }
                    count++;

                }
                Double ThanhTien = Double.parseDouble(lbl_tongTien1.getText()) - Double.parseDouble(lbl_giamGia1.getText());
                lbl_thanhTien.setText(String.valueOf(String.format("%.0f", ThanhTien)));

            } else if (SoLuong < NhapSoLuong) {
                JOptionPane.showMessageDialog(this, "Sản phẩm không đủ ");
                return;

            }
            if (Integer.parseInt(lbl_thanhTien.getText()) >= 500000) {
                int diemThuong = Integer.parseInt(lbl_thanhTien.getText()) / 100000;
                lbl_diemThuong.setText(String.valueOf(diemThuong));
            } else {
                lbl_diemThuong.setText(String.valueOf(0));
            }
            List<HoaDonViewModel> listHoaDon = hoaDonServiec.getListHD(1);
            for (HoaDonViewModel x : listHoaDon) {
                if (tb_hoaDon.getValueAt(rowHD, 0).toString().equals(x.getMa())) {
                    HoaDonCHiTietViewModel hdct = inputHDCT(DonGia, NhapSoLuong);
                    hoaDonServiec.saveHDCT(hdct, MaSP, x.getMa());

                    return;

                }
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tb_sanPhamMouseClicked

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed
 int rowHD = tb_hoaDon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn hoá đơn bạn muốn thanh toán");
            return;

        }
        if (lbl_tenKhachHang.getText().isEmpty()) {
          JOptionPane.showMessageDialog(this, "chưa có thông tin khách hàng");
            return;  
        }
        if (txt_tienKhachDua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "tiền khách Đưa không được để trống");
            return;

        }
        try {
             if (Double.parseDouble(txt_tienKhachDua.getText()) < Double.parseDouble(lbl_thanhTien.getText())) {
            JOptionPane.showMessageDialog(this, "tiền khách Đưa chưa đủ");
            return;
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "không nhập kí tự tiền khách đưa");
            return;
        }
        HoaDonViewModel hoaDon = new HoaDonViewModel();
        hoaDon.setGhiChu(txt_ghiChu.getText());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        hoaDon.setNgayThanhToan(date);
        hoaDon.setMa(tb_hoaDon.getValueAt(rowHD, 0).toString());
        hoaDon.setTongTien(Double.parseDouble(lbl_thanhTien.getText()));
        hoaDonServiec.updateTrangThaiHoaDon(hoaDon);

        if (btn_suDung.getText().equals("Hoàn Tác")) {
            List<KhachHang> getListKhachHang = khachHangService.TenDiemKhachHang(lbl_sdt.getText());
            for (KhachHang khachHang : getListKhachHang) {
                khachHangService.updateDiemKhachHang(lbl_sdt.getText(), khachHang.getDiemthuong() - Integer.parseInt(txt_diem.getText()));

                break;
            }
        }
        List<KhachHang> getListKhachHang = khachHangService.TenDiemKhachHang(lbl_sdt.getText());
        for (KhachHang khachHang : getListKhachHang) {
            khachHangService.updateDiemKhachHang(lbl_sdt.getText(), khachHang.getDiemthuong() + Integer.parseInt(lbl_diemThuong.getText()));
            break;
        }

        JOptionPane.showMessageDialog(this, "thanh toán thành công");
        if (chk_inHoaDon.isSelected()) {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph xWPFParagraph = document.createParagraph();
            XWPFRun run = xWPFParagraph.createRun();
            XWPFParagraph titleGraph = document.createParagraph();

            titleGraph.setAlignment(ParagraphAlignment.CENTER);

            String title = "CỬA HÀNG BÁN QUẦN ÁO";

            XWPFRun titleRun = titleGraph.createRun();

            titleRun.setBold(true);

            titleRun.setText(title);

            XWPFParagraph xWPFParagraph1 = document.createParagraph();
            xWPFParagraph1.setAlignment(ParagraphAlignment.CENTER);
            run = xWPFParagraph1.createRun();
            run.setText("ĐC: Phố Trinh Văn Bô , Nam Từ Liêm , Hà Nội");

            XWPFParagraph xWPFParagraph2 = document.createParagraph();
            xWPFParagraph2.setAlignment(ParagraphAlignment.CENTER);
            run = xWPFParagraph2.createRun();
            run.setText("SĐT:0395123133 ");

            XWPFParagraph xWPFParagraph3 = document.createParagraph();

            xWPFParagraph3.setAlignment(ParagraphAlignment.CENTER);

            run = xWPFParagraph3.createRun();
            run.setText("HOÁ ĐƠN BÁN QUẦN ÁO");

            XWPFParagraph xWPFParagraph4 = document.createParagraph();
            run = xWPFParagraph4.createRun();
            run.setText("Khách Hàng :" + lbl_tenKhachHang.getText());

            XWPFParagraph xWPFParagraph0 = document.createParagraph();
            run = xWPFParagraph0.createRun();
            run.setText("Mã Hoá Đơn :" + tb_hoaDon.getValueAt(rowHD, 0));

            XWPFParagraph xWPFParagraph5 = document.createParagraph();
            run = xWPFParagraph5.createRun();
            run.setText("Địa Chỉ :");

            XWPFParagraph xWPFParagraph6 = document.createParagraph();
            run = xWPFParagraph6.createRun();
            run.setText("Số Điện Thoại :" + lbl_sdt.getText());
            XWPFParagraph xWPFParagraph7 = document.createParagraph();
            run = xWPFParagraph7.createRun();
            run.setText("Ngày lập :" + date);

            File f = new File("D:\\DA1//HoaDon//" + hoaDon.getMa() + ".docx");
            try {
                FileOutputStream fos = new FileOutputStream(f);
                XWPFParagraph xWPFParagraph13 = document.createParagraph();
                run = xWPFParagraph13.createRun();
                run.setText("  ");
                XWPFTable table = document.createTable();
                table.setWidth(10000);
                XWPFTableRow tableRowOne = table.getRow(0);

                tableRowOne.getCell(0).setText("tên Sản Phẩm");
                tableRowOne.addNewTableCell().setText("Số Lượng");
                tableRowOne.addNewTableCell().setText(
                        "Đơn Giá)");
                tableRowOne.addNewTableCell().setText(
                        "Thành Tiền");
                int row = 0;

                for (int i = 0; i < tb_gioHang.getRowCount(); i++) {
                    XWPFTableRow tableRowTwo = table.createRow();

                    tableRowTwo.getCell(0).setText(tb_gioHang.getValueAt(row, 1).toString());

                    tableRowTwo.getCell(1).setText(tb_gioHang.getValueAt(row, 2).toString());

                    tableRowTwo.getCell(2).setText(tb_gioHang.getValueAt(row, 3).toString());

                    tableRowTwo.getCell(3).setText(String.valueOf(Double.parseDouble(tb_gioHang.getValueAt(row, 2).toString()) * Double.parseDouble(tb_gioHang.getValueAt(row, 3).toString())));

                    row++;
                }

                XWPFParagraph xWPFParagraph14 = document.createParagraph();
                run = xWPFParagraph14.createRun();
                run.setText("  ");
                XWPFParagraph xWPFParagraph8 = document.createParagraph();
                run = xWPFParagraph8.createRun();
                run.setText("Giảm Giá :" + lbl_giamGia1.getText());

                XWPFParagraph xWPFParagraph9 = document.createParagraph();
                run = xWPFParagraph9.createRun();
                run.setText("Tổng Tiền Thanh Toán :" + lbl_thanhTien.getText());
                XWPFParagraph xWPFParagraph10 = document.createParagraph();
                run = xWPFParagraph10.createRun();
                run.setText("tiền trả lại :" + lbl_tienThua.getText());
                XWPFParagraph xWPFParagraph11 = document.createParagraph();
                xWPFParagraph11.setAlignment(ParagraphAlignment.RIGHT);

                run = xWPFParagraph11.createRun();
                run.setText("Người Lập Hoá Đơn");

                XWPFParagraph xWPFParagraph12 = document.createParagraph();
                xWPFParagraph12.setAlignment(ParagraphAlignment.RIGHT);
                run = xWPFParagraph12.createRun();
                run.setText(TenNhanVien);
                document.write(fos);
                fos.close();
                document.close();
                System.out.println("xuất hoá đơn thành công");

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        List<HoaDonViewModel> getList = hoaDonServiec.getListHD(1);

        for (HoaDonViewModel hoaDonViewModel : getList) {
            if (tb_hoaDon.getValueAt(rowHD, 0).equals(hoaDonViewModel.getMa())) {
                getList.remove(hoaDonViewModel);
                getList.clear();
                clear();
                getListHoaDon();
                listGioHang.clear();
                getListGioHang();

                break;
            }
        }
    }//GEN-LAST:event_btn_thanhToanActionPerformed

    private void btn_taoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoHoaDonActionPerformed
        HoaDonViewModel hoaDon = inputHD();
        Integer add = hoaDonServiec.saveHD(hoaDon, id);
        if (add > 0) {
            System.out.println("thêm thành công");
            List<HoaDonViewModel> list = hoaDonServiec.getListHD(1);
            list.clear();
            getListHoaDon();
        } else {
            System.out.println("thêm thất bại");
        }
    }//GEN-LAST:event_btn_taoHoaDonActionPerformed

    private void btn_suDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suDungActionPerformed
try {
                 if (lbl_tenKhachHang.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "nhập số điện thoại khách hàng");
            return;
        }
        if (txt_diem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điểm không được để trống");
            return;
        }
        if (btn_suDung.getText().equals("Sử dụng")) {
            List<KhachHang> listKhachHang = khachHangService.TenDiemKhachHang(lbl_sdt.getText());
            for (KhachHang x : listKhachHang) {
                if (Double.parseDouble(txt_diem.getText()) > x.getDiemthuong()) {
                    JOptionPane.showMessageDialog(this, "khách Hàng Không Đủ điểm");
                    return;
                }
                double tongTien = Double.parseDouble(lbl_tongTien1.getText());
                int diem = Integer.parseInt(txt_diem.getText());
                double suDungDien = diem * 1000;
                Double giam = Double.parseDouble(lbl_giamGia1.getText()) + suDungDien;
                lbl_giamGia1.setText(String.valueOf(giam));
                double thanhTien = tongTien - Double.parseDouble(lbl_giamGia1.getText());
                lbl_thanhTien.setText(String.valueOf(thanhTien));
                btn_suDung.setText("Hoàn Tác");
                return;
            }
        }
        if (btn_suDung.getText().equals("Hoàn Tác")) {
            mouse();
            btn_suDung.setText("Sử dụng");
            return;
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "không nhập kí tự");
        }
    }//GEN-LAST:event_btn_suDungActionPerformed

    private void tb_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoaDonMouseClicked
 int row = tb_hoaDon.getSelectedRow();
        if (row < 0) {
            return;
        }
        listGioHang.clear();
        String MaHD = tb_hoaDon.getValueAt(row, 0).toString();
        try {
            lbl_tongTien1.setText(String.valueOf(0));
            lbl_giamGia1.setText(String.valueOf(0));
            lbl_thanhTien.setText(String.valueOf(0));

            getListGioHangHDCT(MaHD);
            lbl_tenKhachHang.setText("");
            lbl_sdt.setText("");
            txt_diem.setText("");
            Double tongPT = 0.0;
            Double tongVN = 0.0;
            Double tongTien = 0.0;
            Double giam = Double.parseDouble(lbl_giamGia1.getText());
            int count = 0;
            List<HoaDonCHiTietViewModel> list = hoaDonServiec.getListHoaDonChiTiet(MaHD);

            for (HoaDonCHiTietViewModel x : list) {
                tongTien = tongTien + x.getThanhTien();
                lbl_tongTien1.setText(String.format("%.0f", tongTien));

                List<SanPhamViewModel> listSanPham = sanISamPhamServiecs.getListSanPham();

                if (tb_gioHang.getValueAt(count, 0).equals(x.getSanPham().getMa()) && x.getSanPham().getKhuenMai().getHinhThucKM().equals("%")) {
                    tongPT = x.getThanhTien() * x.getSanPham().getKhuenMai().getGiaTriGiam() / 100;
                    lbl_giamGia1.setText(String.valueOf(giam += tongPT));
                    lbl_giamGia1.setText(String.format("%.0f", giam));
                } else {
                    tongVN = x.getSanPham().getKhuenMai().getGiaTriGiam();
                    lbl_giamGia1.setText(String.valueOf(giam += tongVN));
                }

                count++;

            }
            Double ThanhTien = Double.parseDouble(lbl_tongTien1.getText()) - Double.parseDouble(lbl_giamGia1.getText());
            lbl_thanhTien.setText(String.valueOf(String.format("%.0f", ThanhTien)));
            if (Integer.parseInt(lbl_thanhTien.getText()) >= 500000) {
                int diemThuong = Integer.parseInt(lbl_thanhTien.getText()) / 100000;
                lbl_diemThuong.setText(String.valueOf(diemThuong));
            } else {
                lbl_diemThuong.setText(String.valueOf(0));
            }

        } catch (Exception e) {
            lbl_tongTien1.setText(String.valueOf(0));
            lbl_giamGia1.setText(String.valueOf(0));
            lbl_thanhTien.setText(String.valueOf(0));
        }
    }//GEN-LAST:event_tb_hoaDonMouseClicked

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
int rowSP = tb_gioHang.getSelectedRow();
        int rowHD = tb_hoaDon.getSelectedRow();
        if (rowSP < 0) {
            JOptionPane.showMessageDialog(this, "chọn 1 sản phẩm trong giỏ hàng để xoá");
            return;
        }
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn Hoá đơn bạn muốn xoá sản phẩm đấy");
            return;
        }
        String MaSP = tb_gioHang.getValueAt(rowSP, 0).toString();
        String MaHD = tb_hoaDon.getValueAt(rowHD, 0).toString();
        Integer soLuong = Integer.parseInt(tb_gioHang.getValueAt(rowSP, 2).toString());
        Integer idSP = sanISamPhamServiecs.getIdSanPham(MaSP);
        Integer idHd = hoaDonServiec.getIdHD(MaHD);
        Integer isDelete = hoaDonServiec.deleteSanPham(idHd, idSP);
        List<SanPhamViewModel> list = sanISamPhamServiecs.getListSanPham();
        for (SanPhamViewModel x : list) {
            if (MaSP.equals(x.getMa())) {
                sanISamPhamServiecs.updateSoLuongSP(MaSP, x.getSoLuongTon() + soLuong);
                list.clear();
                getListSP();
                getListGioHangHDCT(MaHD);
                mouse();
                break;
            }
        }
        listGioHang.clear();

    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed


    }//GEN-LAST:event_btn_clearActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void searchText1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchText1CaretUpdate

    }//GEN-LAST:event_searchText1CaretUpdate

    private void myButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton9ActionPerformed

    }//GEN-LAST:event_myButton9ActionPerformed

    private void btn_xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanActionPerformed

    }//GEN-LAST:event_btn_xacNhanActionPerformed

    private void txt_tienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaCaretUpdate


    }//GEN-LAST:event_txt_tienKhachDuaCaretUpdate

    private void jLabel3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel3AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3AncestorAdded

    private void btn_thayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thayDoiActionPerformed

    }//GEN-LAST:event_btn_thayDoiActionPerformed

    private void cb_danhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_danhMucItemStateChanged

    }//GEN-LAST:event_cb_danhMucItemStateChanged

    private void tb_hoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_hoaDonMouseEntered

    private void chk_inHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_inHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_inHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton btn_clear;
    private swing.MyButton btn_suDung;
    private swing.MyButton btn_taoHoaDon;
    private swing.MyButton btn_thanhToan;
    private swing.MyButton btn_thayDoi;
    private swing.MyButton btn_xacNhan;
    private swing.MyButton btn_xoa;
    private javax.swing.JComboBox<String> cb_danhMuc;
    private javax.swing.JCheckBox chk_inHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_diemThuong;
    private javax.swing.JLabel lbl_giamGia1;
    private javax.swing.JLabel lbl_sdt;
    private javax.swing.JLabel lbl_tenKhachHang;
    private javax.swing.JLabel lbl_thanhTien;
    private javax.swing.JLabel lbl_tienThua;
    private javax.swing.JLabel lbl_tongTien1;
    private swing.MyButton myButton9;
    private swing.PanelBorder panelBorder1;
    private swing.PanelGradiente panelGradiente1;
    private swing.PanelGradiente panelGradiente2;
    private swing.PanelGradiente panelGradiente3;
    private swing.PanelGradiente panelGradiente4;
    private swing.SearchText searchText1;
    private javax.swing.JTable tb_gioHang;
    private javax.swing.JTable tb_hoaDon;
    private javax.swing.JTable tb_sanPham;
    private swing.MyTextField txt_diem;
    private javax.swing.JTextArea txt_ghiChu;
    private swing.MyTextField txt_tienKhachDua;
    // End of variables declaration//GEN-END:variables
  @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {

                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                }
            }

//            }
        } while (true);

    }

    @Override

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "example-runner");
        t.setDaemon(true);
        return t;
    }
}
