package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import models.Chucvu;
import models.Users;
import repositorys.IUsersReposytory;
import repositorys.imp.UsersReposytory;
import services.IChucvuService;
import services.IUsersService;
import services.imp.ChucVuService;
import services.imp.UsersService;
import swing.MyButton;
import swing.MyTextField;
import swing.PanelBorder;
import swing.PanelGradiente;
import swing.SearchText;
import viewmodels.UsersViewmodel;

public class frm_Nhanvien extends JPanel {

    private DefaultTableModel defaultTableModel;
    private final IUsersService nhanVienService;
    private final IChucvuService CVService;

    private PanelGradiente panelGradiente1;
    private PanelBorder panelBorder2;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, lblTongnv, lbl_search;
    private MyTextField txtten, txttendem, txtho, txtsdt, txtTaikhoan, txtemail;
    private JPasswordField txtPass;
    private JScrollPane jScrollPane1;
    private JTable tblnhanvien;
    private JRadioButton rd_nam, rd_nu;
    private JCheckBox chk_tt;
    private MyButton btnthem, btncapnhat, btnlmmoi;
    private JComboBox<Object> cbochucvu;
    private JDateChooser datengaysinh;
    private SearchText searchtxt;
    private ButtonGroup buttonGroup1;

    public frm_Nhanvien() {
        initComponents();
        nhanVienService = new UsersService();
        CVService = new ChucVuService();
        inittable();
        List<Chucvu> cvv = CVService.getAllChucVu();
        cbochucvu.setModel(new DefaultComboBoxModel<>(cvv.toArray()));
        defaultTableModel = (DefaultTableModel) tblnhanvien.getModel();
        Loaddata();
    }

    private void inittable() {
        DefaultTableModel model = (DefaultTableModel) tblnhanvien.getModel();
        model.addColumn("STT");
        model.addColumn("Họ");
        model.addColumn("Tên đệm");
        model.addColumn("Tên");
        model.addColumn("Ngày sinh");
        model.addColumn("Giới tính");
        model.addColumn("SĐT");
        model.addColumn("Tài Khoán");
        model.addColumn("Mật Khẩu");
        model.addColumn("Email");
        model.addColumn("Chức Vụ");
        model.addColumn("Trạng thái");
    }

    public void Loaddata() {
        defaultTableModel.setRowCount(0);
        List<UsersViewmodel> nvv = nhanVienService.getAllNhanVien();
        int stt = 1;
        for (UsersViewmodel x : nvv) {
            defaultTableModel.addRow(new Object[]{
                stt, x.getHo(), x.getTendem(), x.getTen(), x.getNgaysinh(),
                x.getGioitinh() == 1 ? "Nam" : "Nữ", x.getSdt(), x.getTk(), x.getMk(),
                x.getEmail(), x.getChucVu().getTen(), x.getTT() == 1 ? "Làm việc" : "Nghỉ Làm"
            });
            stt++;
        }
        lblTongnv.setText("Tổng nhân viên : " + nvv.size());
    }

    public void ClearForm() {
        txtten.setText("");
        txttendem.setText("");
        txtho.setText("");
        txtTaikhoan.setText("");
        txtPass.setText("");
        txtemail.setText("");
        txtsdt.setText("");
        buttonGroup1.clearSelection();
        chk_tt.setSelected(false);
        cbochucvu.setSelectedIndex(0);
        datengaysinh.setDate(null);
        Loaddata();
    }

    private UsersViewmodel getInputForm() {
        String sdt = "(0\\d{9})";
        String mail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        UsersViewmodel nv = new UsersViewmodel();
        Pattern p = Pattern.compile("^[0-9]+$");

        if (txtten.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
            return null;
        }
        if (p.matcher(txtten.getText()).find()) {
            JOptionPane.showMessageDialog(this, "Tên không được nhập số");
            return null;
        }
        if (txtten.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên không được quá 30 kí tự");
            return null;
        }
        if (txttendem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đệm");
            return null;
        }
        if (p.matcher(txttendem.getText()).find()) {
            JOptionPane.showMessageDialog(this, "Tên đệm không được nhập số");
            return null;
        }
        if (txttendem.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên đệm không được quá 30 kí tự");
            return null;
        }
        if (txtho.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ");
            return null;
        }
        if (p.matcher(txtho.getText()).find()) {
            JOptionPane.showMessageDialog(this, "Họ không được nhập số");
            return null;
        }
        if (txtho.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Họ không được quá 30 kí tự");
            return null;
        }
        if (datengaysinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh");
            return null;
        }
        if (txtsdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
            return null;
        }
        if (!txtsdt.getText().matches(sdt)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng");
            return null;
        }
        if (nhanVienService.kiemtrasdt(txtsdt.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
            return null;
        }
        if (txtTaikhoan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản");
            return null;
        }
        if (nhanVienService.kiemtratk(txtTaikhoan.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại");
            return null;
        }
        if (String.valueOf(txtPass.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
            return null;
        }
        if (txtemail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email");
            return null;
        }
        if (!txtemail.getText().matches(mail)) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
            return null;
        }
        if (nhanVienService.kiemtra(txtemail.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại");
            return null;
        }

        nv.setTen(txtten.getText());
        nv.setTendem(txttendem.getText());
        nv.setHo(txtho.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(datengaysinh.getDate());
        nv.setNgaysinh(date);
        int gt = rd_nam.isSelected() ? 1 : 0;
        nv.setGioitinh(gt);
        nv.setSdt(txtsdt.getText());
        nv.setTk(txtTaikhoan.getText());
        nv.setMk(String.valueOf(txtPass.getPassword()));
        nv.setEmail(txtemail.getText());
        Chucvu cvv = (Chucvu) cbochucvu.getSelectedItem();
        nv.setChucVu(cvv);
        nv.setTT(chk_tt.isSelected() ? 1 : 0);
        return nv;
    }

    public Integer getNhanVienSelectTedRow() {
        Integer row = tblnhanvien.getSelectedRow();
        return (Integer) tblnhanvien.getValueAt(row, 0);
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setMinimumSize(new java.awt.Dimension(1010, 640));
        setLayout(null);

        panelGradiente1 = new PanelGradiente();
        panelGradiente1.setColorPrimario(new Color(204, 255, 255));
        panelGradiente1.setColorSecundario(new Color(204, 204, 255));
        panelGradiente1.setLayout(null);
        panelGradiente1.setBounds(0, 0, 1010, 640);
        add(panelGradiente1);

        jLabel1 = createLabel("Tên", 70, 0);
        jLabel2 = createLabel("Tên đệm", 70, 70);
        jLabel3 = createLabel("Họ", 70, 140);
        jLabel4 = createLabel("Ngày sinh", 70, 210);
        jLabel5 = createLabel("SĐT", 360, 0);
        jLabel6 = createLabel("Tài Khoán", 360, 70);
        jLabel7 = createLabel("Mật khẩu", 360, 140);
        jLabel8 = createLabel("Giới tính", 80, 280);
        jLabel10 = createLabel("Email", 360, 210);
        jLabel11 = createLabel("Chức vụ", 360, 280);
        jLabel9 = createLabel("Trạng thái", 80, 320);

        txtten = new MyTextField();
        txtten.setBounds(70, 20, 220, 40);
        panelGradiente1.add(txtten);

        txttendem = new MyTextField();
        txttendem.setBounds(70, 90, 220, 40);
        panelGradiente1.add(txttendem);

        txtho = new MyTextField();
        txtho.setBounds(70, 160, 220, 40);
        panelGradiente1.add(txtho);

        txtsdt = new MyTextField();
        txtsdt.setBounds(360, 20, 220, 40);
        panelGradiente1.add(txtsdt);

        txtTaikhoan = new MyTextField();
        txtTaikhoan.setBounds(360, 90, 220, 40);
        panelGradiente1.add(txtTaikhoan);

        txtemail = new MyTextField();
        txtemail.setBounds(360, 230, 220, 40);
        panelGradiente1.add(txtemail);

        txtPass = new JPasswordField();
        txtPass.setBounds(360, 160, 220, 40);
        panelGradiente1.add(txtPass);

        datengaysinh = new JDateChooser();
        datengaysinh.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204), 2));
        datengaysinh.setDateFormatString("yyyy-MM-dd");
        datengaysinh.setBounds(70, 230, 220, 40);
        panelGradiente1.add(datengaysinh);

        buttonGroup1 = new ButtonGroup();
        rd_nam = new JRadioButton("Nam");
        rd_nam.setBackground(new Color(230, 230, 250));
        rd_nam.setSelected(true);
        rd_nam.setBounds(150, 280, 60, 20);
        buttonGroup1.add(rd_nam);
        panelGradiente1.add(rd_nam);

        rd_nu = new JRadioButton("Nữ");
        rd_nu.setBackground(new Color(230, 230, 250));
        rd_nu.setBounds(220, 280, 60, 20);
        buttonGroup1.add(rd_nu);
        panelGradiente1.add(rd_nu);

        chk_tt = new JCheckBox("Làm việc");
        chk_tt.setBackground(new Color(230, 230, 250));
        chk_tt.setBounds(160, 320, 90, 20);
        panelGradiente1.add(chk_tt);

        cbochucvu = new JComboBox<>();
        cbochucvu.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204), 2));
        cbochucvu.setBounds(360, 300, 220, 40);
        panelGradiente1.add(cbochucvu);

        btnthem = new MyButton();
        btnthem.setBackground(new Color(125, 224, 237));
        btnthem.setText("Thêm");
        btnthem.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnthem.setBounds(720, 20, 120, 40);
        btnthem.addActionListener(e -> btnthemActionPerformed(e));
        panelGradiente1.add(btnthem);

        btncapnhat = new MyButton();
        btncapnhat.setBackground(new Color(125, 224, 237));
        btncapnhat.setText("Cập nhật");
        btncapnhat.setFont(new Font("Tahoma", Font.BOLD, 12));
        btncapnhat.setBounds(720, 90, 120, 40);
        btncapnhat.addActionListener(e -> btncapnhatActionPerformed(e));
        panelGradiente1.add(btncapnhat);

        btnlmmoi = new MyButton();
        btnlmmoi.setBackground(new Color(125, 224, 237));
        btnlmmoi.setText("Làm Mới");
        btnlmmoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnlmmoi.setBounds(720, 160, 120, 40);
        btnlmmoi.addActionListener(e -> btnlmmoiActionPerformed(e));
        panelGradiente1.add(btnlmmoi);

        tblnhanvien = new JTable();
        tblnhanvien.setBackground(new Color(255, 245, 255));
        tblnhanvien.setGridColor(Color.WHITE);
        tblnhanvien.setRowHeight(25);
        tblnhanvien.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                tblnhanvienMouseClicked(evt);
            }
        });

        jScrollPane1 = new JScrollPane(tblnhanvien);
        jScrollPane1.setBounds(10, 380, 990, 250);
        panelGradiente1.add(jScrollPane1);

        lblTongnv = new JLabel("Tổng nhân viên :");
        lblTongnv.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblTongnv.setForeground(Color.RED);
        lblTongnv.setBounds(20, 360, 200, 20);
        panelGradiente1.add(lblTongnv);

        panelBorder2 = new PanelBorder();
        panelBorder2.setBackground(Color.WHITE);
        panelBorder2.setLayout(null);
        panelBorder2.setBounds(630, 320, 300, 40);

        searchtxt = new SearchText();
        searchtxt.setBounds(10, 0, 240, 40);
        searchtxt.addCaretListener(evt -> searchtxtCaretUpdate(evt));
        panelBorder2.add(searchtxt);

        lbl_search = new JLabel();
        lbl_search.setBounds(260, 0, 40, 40);
        panelBorder2.add(lbl_search);

        panelGradiente1.add(panelBorder2);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setBounds(x, y, 220, 20);
        panelGradiente1.add(label);
        return label;
    }

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {
        UsersViewmodel nv = getInputForm();
        if (nv == null) return;
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm ?", "Add", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (nhanVienService.add(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                Loaddata();
                ClearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
            }
        }
    }

    private void tblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            int index = tblnhanvien.getSelectedRow();
            String gt = (String) tblnhanvien.getValueAt(index, 5);
            String tt = (String) tblnhanvien.getValueAt(index, 11);
            int trangthai = tt.equals("Làm việc") ? 1 : 0;
            int gioitinh = gt.equals("Nam") ? 1 : 0;

            txtho.setText(tblnhanvien.getValueAt(index, 1) + "");
            txtten.setText(tblnhanvien.getValueAt(index, 3) + "");
            txttendem.setText(tblnhanvien.getValueAt(index, 2) + "");
            txtsdt.setText(tblnhanvien.getValueAt(index, 6) + "");
            txtemail.setText(tblnhanvien.getValueAt(index, 9) + "");
            txtTaikhoan.setText(tblnhanvien.getValueAt(index, 7) + "");
            txtPass.setText(tblnhanvien.getValueAt(index, 8) + "");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse((String) tblnhanvien.getValueAt(index, 4));
            datengaysinh.setDate(date1);

            chk_tt.setSelected(trangthai == 1);
            if (gioitinh == 1) {
                rd_nam.setSelected(true);
            } else {
                rd_nu.setSelected(true);
            }

            String chucVu = tblnhanvien.getValueAt(index, 10).toString();
            if (chucVu.equals("quản lý")) {
                cbochucvu.setSelectedIndex(0);
            } else {
                cbochucvu.setSelectedIndex(1);
            }
        } catch (ParseException ex) {
            Logger.getLogger(frm_Nhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {
        Integer row = tblnhanvien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn dòng cần sửa !");
            return;
        }

        UsersViewmodel nv = new UsersViewmodel();
        nv.setTen(txtten.getText());
        nv.setTendem(txttendem.getText());
        nv.setHo(txtho.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(datengaysinh.getDate());
        nv.setNgaysinh(date);
        int gt = rd_nam.isSelected() ? 1 : 0;
        nv.setGioitinh(gt);
        nv.setSdt(txtsdt.getText());
        nv.setTk(txtTaikhoan.getText());
        nv.setMk(String.valueOf(txtPass.getPassword()));
        nv.setEmail(txtemail.getText());
        Chucvu cvv = (Chucvu) cbochucvu.getSelectedItem();
        nv.setChucVu(cvv);
        nv.setTT(chk_tt.isSelected() ? 1 : 0);

        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa ?", "Update", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            IUsersReposytory usersReposytory = new UsersReposytory();
            List<Users> lst = usersReposytory.getAllNhanVien();
            if (nhanVienService.update(nv, lst.get(row).getId())) {
                JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                Loaddata();
                ClearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa Thất Bại");
            }
        }
    }

    private void btnlmmoiActionPerformed(java.awt.event.ActionEvent evt) {
        ClearForm();
    }

    private void searchtxtCaretUpdate(javax.swing.event.CaretEvent evt) {
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (UsersViewmodel x : nhanVienService.SearchNVV(searchtxt.getText())) {
            defaultTableModel.addRow(new Object[]{
                stt, x.getHo(), x.getTendem(), x.getTen(), x.getNgaysinh(),
                x.getGioitinh() == 1 ? "Nam" : "Nữ", x.getSdt(), x.getTk(), x.getMk(),
                x.getEmail(), x.getChucVu().getTen(), x.getTT() == 1 ? "Làm việc" : "Nghỉ Làm"
            });
            stt++;
        }
    }
}
