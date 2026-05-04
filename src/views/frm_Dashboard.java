package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class frm_Dashboard extends JFrame {

    private JPanel panelchid;
    private int xx, xy;
    private int id;
    private String TenNV;
    private String TenCV;

    public frm_Dashboard(String TenNhanVien, int idNV, String tencv) {
        initComponents();
        TenCV = tencv;
        id = idNV;
        lbl_tenNhanVien.setText(TenNhanVien);
        TenNV = TenNhanVien;
        setLocationRelativeTo(null);
        setdashboad();
    }

    private JPanel jPanel1, jPanel2, jPanel4;
    private JLabel jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11;
    private JPanel btnbanhang, btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnhoadon, btnthongke, btndangxuat;
    private JPanel crep1, crep2, crep3, crep4, crep5, crepp1, crepp2, crepp3;
    private JLabel lbl_tenNhanVien, lbltieude;
    private JPanel pnmain;

    public frm_Dashboard() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        // Initialize all components
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel4 = new JPanel();

        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();

        btnbanhang = new JPanel();
        btnsanpham = new JPanel();
        btnkhuyenmai = new JPanel();
        btnkhachhang = new JPanel();
        btnnhanvien = new JPanel();
        btnhoadon = new JPanel();
        btnthongke = new JPanel();
        btndangxuat = new JPanel();

        crep1 = new JPanel();
        crep2 = new JPanel();
        crep3 = new JPanel();
        crep4 = new JPanel();
        crep5 = new JPanel();
        crepp1 = new JPanel();
        crepp2 = new JPanel();
        crepp3 = new JPanel();

        lbl_tenNhanVien = new JLabel();
        lbltieude = new JLabel();
        pnmain = new JPanel();
        pnmain.setLayout(null);
        pnmain.setBounds(0, 0, 1200, 700);

        panelchid = new JPanel();

        // Add pnmain to frame
        add(pnmain);

        // Add mouse listeners to buttons
        btnbanhang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnbanhangMouseClicked(evt);
            }
        });

        btnsanpham.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnsanphamMouseClicked(evt);
            }
        });

        btnkhuyenmai.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnkhuyenmaiMouseClicked(evt);
            }
        });

        btnkhachhang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnkhachhangMouseClicked(evt);
            }
        });

        btnnhanvien.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnnhanvienMouseClicked(evt);
            }
        });

        btnthongke.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnthongkeMouseClicked(evt);
            }
        });

        btnhoadon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btnhoadonMouseClicked(evt);
            }
        });

        btndangxuat.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                btndangxuatMousePressed(evt);
            }
        });

        jLabel8.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
    }

    private void setdashboad() {
        setColor(btnbanhang);
        crep1.setOpaque(true);
        resetColor(new JPanel[]{btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep2, crep3, crep4, crep5, crepp1, crepp2, crepp3});
        setpanal(new frm_Banhang(id, TenNV));
    }

    private void setColor(JPanel pane) {
        pane.setBackground(new Color(24, 116, 205));
    }

    private void resetColor(JPanel[] pane, JPanel[] indicators) {
        for (int i = 0; i < pane.length; i++) {
            pane[i].setBackground(new Color(0, 0, 128));

        }
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setOpaque(false);
        }
    }

    private void setpanal(JPanel panel) {
        panelchid = panel;
        pnmain.removeAll();
        pnmain.add(panelchid);
        pnmain.validate();
    }


    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void btnbanhangMouseClicked(java.awt.event.MouseEvent evt) {
        setColor(btnbanhang);
        resetColor(new JPanel[]{btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep2, crep3, crep4, crep5, crepp1, crepp2, crepp3});
        crep1.setOpaque(true);
        setpanal(new frm_Banhang(id, TenNV));
    }

    private void btnsanphamMouseClicked(java.awt.event.MouseEvent evt) {
        setColor(btnsanpham);
        resetColor(new JPanel[]{btnbanhang, btnkhuyenmai, btnkhachhang, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep3, crep4, crep5, crepp1, crepp2, crepp3});
        crep2.setOpaque(true);
        setpanal(new frm_Sanpham());
    }

    private void btnkhuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {
        setColor(btnkhuyenmai);
        resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhachhang, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep2, crep4, crep5, crepp1, crepp2, crepp3});
        crep3.setOpaque(true);
        setpanal(new frm_Khuyenmai());
    }

    private void btnkhachhangMouseClicked(java.awt.event.MouseEvent evt) {
        setColor(btnkhachhang);
        resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep2, crep3, crep5, crepp1, crepp2, crepp3});
        crep4.setOpaque(true);
        setpanal(new frm_Khachhang());
    }

    private void btnnhanvienMouseClicked(java.awt.event.MouseEvent evt) {
        setColor(btnnhanvien);
        resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnkhachhang, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep2, crep3, crep4, crepp1, crepp2, crepp3});
        crep5.setOpaque(true);
        setpanal(new frm_Nhanvien());
    }

    private void btnthongkeMouseClicked(java.awt.event.MouseEvent evt) {
        setColor(btnthongke);
        resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep2, crep3, crep4, crep5, crepp2, crepp3});
        crepp1.setOpaque(true);
        setpanal(new frm_Thongke());
    }

    private void btnhoadonMouseClicked(java.awt.event.MouseEvent evt) {
        setColor(btnhoadon);
        resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnthongke, btndangxuat}, new JPanel[]{crep1, crep2, crep3, crep4, crep5, crepp1, crepp3});
        crepp2.setOpaque(true);
        setpanal(new frm_Hoadon());
    }

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void btnbanhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbanhangMousePressed
        setColor(btnbanhang);
        crep1.setOpaque(true);
        resetColor(new JPanel[]{btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep2, crep3, crep4, crep5, crepp1, crepp2, crepp3});
        setpanal(new frm_Banhang(id, TenNV));
        lbltieude.setText("Bán hàng");
    }//GEN-LAST:event_btnbanhangMousePressed

    private void btnsanphamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsanphamMousePressed
        setColor(btnsanpham);
        crep2.setOpaque(true);
        resetColor(new JPanel[]{btnbanhang, btnkhuyenmai, btnkhachhang, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep3, crep4, crep5, crepp1, crepp2, crepp3});
        setpanal(new frm_Sanpham());
        lbltieude.setText("Sản phẩm");
    }//GEN-LAST:event_btnsanphamMousePressed

    private void btnkhuyenmaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkhuyenmaiMousePressed
     
            setColor(btnkhuyenmai);
            crep3.setOpaque(true);
            resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhachhang, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep2, crep4, crep5, crepp1, crepp2, crepp3});
            setpanal(new frm_Khuyenmai());
            lbltieude.setText("Khuyến mãi");
        
    }//GEN-LAST:event_btnkhuyenmaiMousePressed

    private void btnkhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkhachhangMousePressed
        setColor(btnkhachhang);
        crep4.setOpaque(true);
        resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnnhanvien, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep2, crep3, crep5, crepp1, crepp2, crepp3});
        setpanal(new frm_Khachhang());
        lbltieude.setText("Khách hàng");
    }//GEN-LAST:event_btnkhachhangMousePressed

    private void btnnhanvienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnnhanvienMousePressed
        if (TenCV.equalsIgnoreCase("quản lý")) {
            setColor(btnnhanvien);
            crep5.setOpaque(true);
            resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnkhachhang, btnthongke, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep2, crep3, crep4, crepp1, crepp2, crepp3});
            setpanal(new frm_Nhanvien());
            lbltieude.setText("Nhân viên");
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập !");
            setdashboad();
            return;
        }
    }//GEN-LAST:event_btnnhanvienMousePressed

    private void btnhoadonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhoadonMousePressed
        setColor(btnhoadon);
        crepp2.setOpaque(true);
        resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnthongke, btndangxuat}, new JPanel[]{crep1, crep2, crep3, crep4, crep5, crepp1, crepp3});
        setpanal(new frm_Hoadon());
        lbltieude.setText("Hóa đơn");
    }//GEN-LAST:event_btnhoadonMousePressed

    private void btnthongkeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnthongkeMousePressed
      
            setColor(btnthongke);
            crepp1.setOpaque(true);
            resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnhoadon, btndangxuat}, new JPanel[]{crep1, crep2, crep3, crep4, crep5, crepp2, crepp3});
            setpanal(new frm_Thongke());
            lbltieude.setText("Thống kê");
        
    }//GEN-LAST:event_btnthongkeMousePressed

    private void btndangxuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndangxuatMousePressed
        int x = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất khỏi sever không ", "đăng xuất", JOptionPane.YES_NO_OPTION);
        if (x == 1) {
            return;
        }
        setColor(btndangxuat);
        crepp3.setOpaque(true);
        resetColor(new JPanel[]{btnbanhang, btnsanpham, btnkhuyenmai, btnkhachhang, btnnhanvien, btnthongke, btnhoadon}, new JPanel[]{crep1, crep2, crep3, crep4, crep5, crepp1, crepp2});
        new frm_Login().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btndangxuatMousePressed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new dashboard().setVisible(true);
//            }
//        });
//    }

}


