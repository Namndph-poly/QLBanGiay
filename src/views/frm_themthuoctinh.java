package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.ChatLieu;
import models.DanhMucSP;
import models.KichCo;
import models.MauSac;
import models.NSX;
import models.ThuongHieu;
import services.IChatLieuServices;
import services.IDanhMucSPServices;
import services.IKichCoServices;
import services.IMauSacServices;
import services.INSXServices;
import services.IThuongHieuServices;
import services.imp.ChatLieuServices;
import services.imp.DanhMucSPServices;
import services.imp.KichCoServices;
import services.imp.MauSacServices;
import services.imp.NSXServices;
import services.imp.ThuongHieuServices;
import swing.MyButton;
import swing.PanelBorder;
import swing.PanelGradiente;
import swing.SearchText;
import viewmodels.Objecttt;

public class frm_themthuoctinh extends JDialog {

    private INSXServices iNSXServices;
    private IMauSacServices iMauSacServices;
    private IDanhMucSPServices iDanhMucSPServices;
    private IKichCoServices iKichCoServices;
    private IChatLieuServices iChatLieuServices;
    private IThuongHieuServices iThuongHieuServices;

    private PanelGradiente panelGradiente1;
    private PanelBorder panelBorder1, panelBorder2, panelBorder3, panelBorder4, panelBorder5;
    private JLabel jLabel1, jLabel2, jLabel3;
    private JPanel jPanel1;
    private JRadioButton rdoChatlieu, rdoNSX, rdoMausac, rdoSize, rdodanhmuc, rdoThuonghieu;
    private ButtonGroup buttonGroup1;
    private JTextField jTextField1;
    private MyButton btn_them, btn_LamMoi, Btn_capNhat;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private SearchText searchText1;
    private int xx, yy;

    public frm_themthuoctinh(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        iChatLieuServices = new ChatLieuServices();
        iDanhMucSPServices = new DanhMucSPServices();
        iKichCoServices = new KichCoServices();
        iMauSacServices = new MauSacServices();
        iNSXServices = new NSXServices();
        iThuongHieuServices = new ThuongHieuServices();
        initComponents();
        setLocationRelativeTo(null);
        inittable();
        rdoChatlieu.setSelected(true);
        loadtablechatlieu();
    }

    private void inittable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnCount(0);
        model.addColumn("ID");
        model.addColumn("Tên");
    }

    private void loadtablechatlieu() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (ChatLieu x : iChatLieuServices.getAll()) {
            model.addRow(new Object[]{x.getId(), x.getTen()});
        }
    }

    private void loadtablensx() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (NSX x : iNSXServices.getAll()) {
            model.addRow(new Object[]{x.getId(), x.getTen()});
        }
    }

    private void loadtablemausac() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (MauSac x : iMauSacServices.getAll()) {
            model.addRow(new Object[]{x.getId(), x.getTen()});
        }
    }

    private void loadtablesize() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (KichCo x : iKichCoServices.getAll()) {
            model.addRow(new Object[]{x.getId(), x.getTen()});
        }
    }

    private void loadtabledanhmuc() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (DanhMucSP x : iDanhMucSPServices.getAll()) {
            model.addRow(new Object[]{x.getId(), x.getTen()});
        }
    }

    private void loadtablethuonghieu() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (ThuongHieu x : iThuongHieuServices.getAll()) {
            model.addRow(new Object[]{x.getId(), x.getTen()});
        }
    }

    private Objecttt getdatdtb(int row) {
        if (row == -1) {
            return null;
        }
        int id = (int) jTable1.getValueAt(row, 0);
        String ten = (String) jTable1.getValueAt(row, 1);
        return new Objecttt(id, ten);
    }

    private void initComponents() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Thêm thuộc tính phụ");
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        setSize(1178, 623);
        setLayout(null);

        // Main gradient panel
        panelGradiente1 = new PanelGradiente();
        panelGradiente1.setColorPrimario(new Color(204, 255, 255));
        panelGradiente1.setColorSecundario(new Color(204, 204, 255));
        panelGradiente1.setLayout(null);
        panelGradiente1.setBounds(0, 0, 1178, 623);
        add(panelGradiente1);

        // Top panel
        panelBorder1 = new PanelBorder();
        panelBorder1.setBackground(new Color(204, 204, 255));
        panelBorder1.setLayout(null);
        panelBorder1.setBounds(10, 50, 1160, 230);
        panelGradiente1.add(panelBorder1);

        // Title label
        jLabel1 = new JLabel(" Nhập Tên");
        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
        jLabel1.setBounds(60, 60, 250, 30);
        panelBorder1.add(jLabel1);

        // Radio buttons panel
        panelBorder3 = new PanelBorder();
        panelBorder3.setBackground(Color.WHITE);
        panelBorder3.setLayout(null);
        panelBorder3.setBounds(370, 20, 450, 170);

        buttonGroup1 = new ButtonGroup();
        createRadioButton("Chất liệu", 30, 30, this::rdoChatlieuActionPerformed);
        createRadioButton("Nhà sản xuất", 150, 30, this::rdoNSXActionPerformed);
        createRadioButton("Màu sắc", 270, 30, this::rdoMausacActionPerformed);
        createRadioButton("Size", 30, 100, this::rdoSizeActionPerformed);
        createRadioButton("Danh mục", 150, 100, this::rdodanhmucActionPerformed);
        createRadioButton("Thương hiệu", 270, 100, this::rdoThuonghieuActionPerformed);

        panelBorder1.add(panelBorder3);

        // Input field panel
        panelBorder4 = new PanelBorder();
        panelBorder4.setBackground(Color.WHITE);
        panelBorder4.setLayout(null);
        panelBorder4.setBounds(60, 90, 250, 50);

        jTextField1 = new JTextField();
        jTextField1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jTextField1.setBorder(null);
        jTextField1.setBounds(10, 0, 230, 50);
        panelBorder4.add(jTextField1);
        panelBorder1.add(panelBorder4);

        // Buttons
        btn_LamMoi = new MyButton();
        btn_LamMoi.setBackground(new Color(125, 224, 237));
        btn_LamMoi.setText("Làm Mới");
        btn_LamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_LamMoi.setBounds(860, 50, 120, 31);
        btn_LamMoi.addActionListener(e -> btn_LamMoiActionPerformed(e));
        panelBorder1.add(btn_LamMoi);

        Btn_capNhat = new MyButton();
        Btn_capNhat.setBackground(new Color(125, 224, 237));
        Btn_capNhat.setText("Cập nhật");
        Btn_capNhat.setFont(new Font("Tahoma", Font.BOLD, 12));
        Btn_capNhat.setBounds(860, 130, 120, 31);
        Btn_capNhat.addActionListener(e -> Btn_capNhatActionPerformed(e));
        panelBorder1.add(Btn_capNhat);

        btn_them = new MyButton();
        btn_them.setBackground(new Color(125, 224, 237));
        btn_them.setText("Thêm");
        btn_them.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_them.setBounds(1010, 50, 120, 31);
        btn_them.addActionListener(e -> btn_themActionPerformed(e));
        panelBorder1.add(btn_them);

        // Table panel
        panelBorder2 = new PanelBorder();
        panelBorder2.setBackground(new Color(204, 204, 255));
        panelBorder2.setLayout(null);
        panelBorder2.setBounds(10, 300, 1160, 320);

        jTable1 = new JTable();
        jTable1.setBackground(new Color(255, 245, 255));
        jTable1.setGridColor(new Color(204, 204, 255));
        jTable1.setRowHeight(25);
        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setBackground(Color.WHITE);
        jScrollPane1.setBounds(0, 70, 1160, 250);
        panelBorder2.add(jScrollPane1);

        // Search panel
        panelBorder5 = new PanelBorder();
        panelBorder5.setBackground(Color.WHITE);
        panelBorder5.setLayout(null);
        panelBorder5.setBounds(790, 10, 300, 50);

        searchText1 = new SearchText();
        searchText1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchText1.setBounds(10, 0, 240, 50);
        panelBorder5.add(searchText1);

        jLabel2 = new JLabel();
        jLabel2.setBounds(264, 0, 40, 50);
        panelBorder5.add(jLabel2);

        panelBorder2.add(panelBorder5);
        panelGradiente1.add(panelBorder2);

        // Close button
        jLabel3 = new JLabel();
        jLabel3.setBounds(1140, 0, 40, 40);
        jLabel3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        panelGradiente1.add(jLabel3);

        // Title bar panel
        jPanel1 = new JPanel();
        jPanel1.setBackground(Color.WHITE);
        jPanel1.setLayout(null);
        jPanel1.setBounds(0, 0, 1130, 40);
        jPanel1.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        panelGradiente1.add(jPanel1);
    }

    private void createRadioButton(String text, int x, int y, java.awt.event.ActionListener listener) {
        JRadioButton rdo = new JRadioButton(text);
        rdo.setBackground(Color.WHITE);
        rdo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        rdo.addActionListener(listener);
        rdo.setBounds(x, y, 110, 40);
        buttonGroup1.add(rdo);
        panelBorder3.add(rdo);

        if (text.equals("Chất liệu")) rdoChatlieu = rdo;
        else if (text.equals("Nhà sản xuất")) rdoNSX = rdo;
        else if (text.equals("Màu sắc")) rdoMausac = rdo;
        else if (text.equals("Size")) rdoSize = rdo;
        else if (text.equals("Danh mục")) rdodanhmuc = rdo;
        else if (text.equals("Thương hiệu")) rdoThuonghieu = rdo;
    }

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {
        clearfrom();
        if (rdoChatlieu.isSelected()) {
            loadtablechatlieu();
        } else if (rdoNSX.isSelected()) {
            loadtablensx();
        } else if (rdoMausac.isSelected()) {
            loadtablemausac();
        } else if (rdoSize.isSelected()) {
            loadtablesize();
        } else if (rdodanhmuc.isSelected()) {
            loadtabledanhmuc();
        } else if (rdoThuonghieu.isSelected()) {
            loadtablethuonghieu();
        }
    }

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {
        if (getdatafrom() == null) {
            return;
        }
        if (rdoChatlieu.isSelected()) {
            JOptionPane.showMessageDialog(this, iChatLieuServices.Add(getdatafrom()));
            loadtablechatlieu();
        } else if (rdoNSX.isSelected()) {
            JOptionPane.showMessageDialog(this, iNSXServices.Add(getdatafrom()));
            loadtablensx();
        } else if (rdoMausac.isSelected()) {
            JOptionPane.showMessageDialog(this, iMauSacServices.Add(getdatafrom()));
            loadtablemausac();
        } else if (rdoSize.isSelected()) {
            JOptionPane.showMessageDialog(this, iKichCoServices.Add(getdatafrom()));
            loadtablesize();
        } else if (rdodanhmuc.isSelected()) {
            JOptionPane.showMessageDialog(this, iDanhMucSPServices.Add(getdatafrom()));
            loadtabledanhmuc();
        } else if (rdoThuonghieu.isSelected()) {
            JOptionPane.showMessageDialog(this, iThuongHieuServices.Add(getdatafrom()));
            loadtablethuonghieu();
        }
    }

    private void Btn_capNhatActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn 1 dòng để sửa");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không") != JOptionPane.YES_OPTION) {
            return;
        }
        int id = (int) jTable1.getValueAt(row, 0);
        if (rdoChatlieu.isSelected()) {
            JOptionPane.showMessageDialog(this, iChatLieuServices.Update(getdatafrom(), id));
            loadtablechatlieu();
        } else if (rdoNSX.isSelected()) {
            JOptionPane.showMessageDialog(this, iNSXServices.Update(getdatafrom(), id));
            loadtablensx();
        } else if (rdoMausac.isSelected()) {
            JOptionPane.showMessageDialog(this, iMauSacServices.Update(getdatafrom(), id));
            loadtablemausac();
        } else if (rdoSize.isSelected()) {
            JOptionPane.showMessageDialog(this, iKichCoServices.Update(getdatafrom(), id));
            loadtablesize();
        } else if (rdodanhmuc.isSelected()) {
            JOptionPane.showMessageDialog(this, iDanhMucSPServices.Update(getdatafrom(), id));
            loadtabledanhmuc();
        } else if (rdoThuonghieu.isSelected()) {
            JOptionPane.showMessageDialog(this, iThuongHieuServices.Update(getdatafrom(), id));
            loadtablethuonghieu();
        }
    }

    private void rdoChatlieuActionPerformed(java.awt.event.ActionEvent evt) {
        if (rdoChatlieu.isSelected()) {
            loadtablechatlieu();
        }
    }

    private void rdoNSXActionPerformed(java.awt.event.ActionEvent evt) {
        if (rdoNSX.isSelected()) {
            loadtablensx();
        }
    }

    private void rdoMausacActionPerformed(java.awt.event.ActionEvent evt) {
        if (rdoMausac.isSelected()) {
            loadtablemausac();
        }
    }

    private void rdoSizeActionPerformed(java.awt.event.ActionEvent evt) {
        if (rdoSize.isSelected()) {
            loadtablesize();
        }
    }

    private void rdodanhmucActionPerformed(java.awt.event.ActionEvent evt) {
        if (rdodanhmuc.isSelected()) {
            loadtabledanhmuc();
        }
    }

    private void rdoThuonghieuActionPerformed(java.awt.event.ActionEvent evt) {
        if (rdoThuonghieu.isSelected()) {
            loadtablethuonghieu();
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            return;
        }
        Objecttt x = getdatdtb(row);
        jTextField1.setText(x.getTen());
    }

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {
        this.dispose();
        new frm_Sanpham().initcbo();
    }

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {
        xx = evt.getX();
        yy = evt.getY();
    }

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - yy);
    }
    private Objecttt getdatafrom() {
        Pattern p = Pattern.compile("^[0-9]+$");
        if (jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, " Bạn chưa nhập tên thuộc tính!");
            jTextField1.requestFocus();
            return null;
        }

        if (jTextField1.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính sản phẩm không quá 30 kí tự!");
            jTextField1.requestFocus();
            return null;
        }

        if (p.matcher(jTextField1.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính sản phẩm phải là chữ!");
            jTextField1.requestFocus();
            return null;
        }
        return new Objecttt(0, jTextField1.getText());

    }

    private void clearfrom() {
        jTextField1.setText("");
    }
}
