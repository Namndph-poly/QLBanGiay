# Hướng Dẫn Hoàn Thành Conversion: NetBeans Form Editor → Pure Java Swing

## 📊 Tiến Độ Conversion

### ✅ Đã Hoàn Thành (3/13)
1. **frm_Dangnhap.java** - Login form ✓
2. **frm_Quenmatkhau.java** - Password reset form ✓  
3. **frm_Login.java** - Main login window with slide animation ✓

### ⏳ Cần Hoàn Thành (10/13)
1. **frm_Nhanvien.java** - Employee management (Complex: JTable + JDateChooser)
2. **frm_Khachhang.java** - Customer management (Complex: JTable)
3. **frm_Sanpham.java** - Product management (Complex: JTable)
4. **frm_Banhang.java** - Sales (Medium)
5. **frm_Hoadon.java** - Invoice management (Complex: JTable)
6. **frm_Khuyenmai.java** - Promotions (Complex: JTable + Radio buttons)
7. **frm_Dashboard.java** - Dashboard (Medium)
8. **frm_Thongke.java** - Statistics (Medium)
9. **frm_themthuoctinh.java** - Add attributes (Simple)
10. **KhachHangForm.java** - Customer form (Simple)

---

## 🎯 Quick Start Guide - Cách Thực Hiện Conversion

### Bước 1: Chuẩn Bị
```bash
# Sao lưu file .form gốc (tùy chọn)
cp frm_Nhanvien.form frm_Nhanvien.form.bak
```

### Bước 2: Template Conversion
Các views phức tạp (có JTable, JDateChooser) có thể được convert từng bước:

**Ví dụ: frm_Nhanvien.java**

```java
package views;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import services.IChucvuService;
import services.IUsersService;
import services.imp.ChucVuService;
import services.imp.UsersService;
import swing.MyButton;
import swing.MyTextField;
import swing.PanelGradiente;
import swing.SearchText;
import viewmodels.UsersViewmodel;

/**
 * Employee Management Form - Pure Java Swing Implementation
 * @author hungh
 */
public class frm_Nhanvien extends JPanel {

    // UI Components - Form Input
    private MyTextField txtten;
    private MyTextField txttendem;
    private MyTextField txtho;
    private MyTextField txtsdt;
    private MyTextField txtTaikhoan;
    private MyTextField txtemail;
    private JPasswordField txtPass;
    private JDateChooser datengaysinh;
    
    // UI Components - Selection
    private ButtonGroup buttonGroup1;
    private JRadioButton rd_nam;
    private JRadioButton rd_nu;
    private JComboBox<Chucvu> cbochucvu;
    private JCheckBox chk_tt;
    
    // UI Components - Buttons
    private MyButton btnthem;
    private MyButton btncapnhat;
    private MyButton btnlmmoi;
    
    // UI Components - Table & Search
    private JTable tblnhanvien;
    private JLabel lblTongnv;
    private SearchText searchtxt;
    
    // Business Logic
    private DefaultTableModel defaultTableModel;
    private final IUsersService nhanVienService;
    private final IChucvuService CVService;

    public frm_Nhanvien() {
        nhanVienService = new UsersService();
        CVService = new ChucVuService();
        initComponents();
        setupTable();
        setupComboBox();
        loadData();
    }

    private void initComponents() {
        // Set panel
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 640));
        setLayout(null);

        // ... Create components here (fields, buttons, etc.)
        // Use UIComponentFactory helper methods where applicable
        
        // Create and add scroll pane with table
        tblnhanvien = new JTable();
        tblnhanvien.setBackground(new java.awt.Color(255, 245, 255));
        tblnhanvien.setRowHeight(25);
        tblnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanvienMouseClicked(evt);
            }
        });
        JScrollPane scrollPane = new JScrollPane(tblnhanvien);
        scrollPane.setBounds(10, 380, 990, 250);
        add(scrollPane);

        // ... Add other components to panel
    }

    private void setupTable() {
        defaultTableModel = (DefaultTableModel) tblnhanvien.getModel();
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Họ");
        defaultTableModel.addColumn("Tên đệm");
        defaultTableModel.addColumn("Tên");
        defaultTableModel.addColumn("Ngày sinh");
        defaultTableModel.addColumn("Giới tính");
        defaultTableModel.addColumn("SĐT");
        defaultTableModel.addColumn("Tài Khoản");
        defaultTableModel.addColumn("Mật Khẩu");
        defaultTableModel.addColumn("Email");
        defaultTableModel.addColumn("Chức Vụ");
        defaultTableModel.addColumn("Trạng thái");
    }

    private void setupComboBox() {
        List<Chucvu> cvList = CVService.getAllChucVu();
        cbochucvu.setModel(new DefaultComboBoxModel<>(cvList.toArray(new Chucvu[0])));
    }

    public void loadData() {
        defaultTableModel.setRowCount(0);
        List<UsersViewmodel> employees = nhanVienService.getAllNhanVien();
        int stt = 1;
        for (UsersViewmodel emp : employees) {
            defaultTableModel.addRow(new Object[]{
                stt,
                emp.getHo(),
                emp.getTendem(),
                emp.getTen(),
                emp.getNgaysinh(),
                emp.getGioitinh() == 1 ? "Nam" : "Nữ",
                emp.getSdt(),
                emp.getTk(),
                emp.getMk(),
                emp.getEmail(),
                emp.getChucVu().getTen(),
                emp.getTT() == 1 ? "Làm việc" : "Nghỉ Làm"
            });
            stt++;
        }
        lblTongnv.setText("Tổng nhân viên : " + employees.size());
    }

    public void clearForm() {
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
        loadData();
    }

    private void tblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {
        // Load selected row data into form fields
        int index = tblnhanvien.getSelectedRow();
        // ... populate form fields
    }

    private void btnthem_Click() {
        // Validate input
        UsersViewmodel emp = getFormData();
        if (emp == null) return;
        
        // Add to database
        if (nhanVienService.add(emp)) {
            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            loadData();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
        }
    }

    private UsersViewmodel getFormData() {
        // Validate and collect form data
        // Return UsersViewmodel or null if validation fails
        return null;
    }
}
```

### Bước 3: Testing
```bash
# Compile từng view mới
javac -cp ".:../thuvienjava/*" frm_Nhanvien.java

# Run application
java application.main
```

### Bước 4: Cleanup
```bash
# Xóa file .form sau khi convert xong
rm frm_Nhanvien.form
```

---

## 💡 Mẹo Chuyên Nghiệp

### 1. **Conversion Strategy**
- **Simple views** (như frm_Dangnhap): Convert thủ công, 15-20 phút/view
- **Medium views** (như frm_Dashboard): Convert từng section, 30-45 phút/view
- **Complex views** (như frm_Khachhang): Tách components, convert parallel, 1-2 giờ/view

### 2. **Layout Strategy**
- Giữ nguyên absolute layout (null + setBounds) cho consistency
- Nếu cần, có thể refactor sang GridBagLayout sau
- Comment rõ vị trí/kích thước: `// Top-left form section`

### 3. **Component Naming**
Tuân theo convention để dễ maintain:
- `btn*` - JButton / MyButton
- `lbl*` - JLabel
- `txt*` - JTextField / MyTextField / MyPassword
- `tbl*` - JTable
- `cbo*` - JComboBox
- `chk*` - JCheckBox
- `rd_*` - JRadioButton

### 4. **Event Handling**
```java
// ❌ Tránh: Anonymous inner class cũ
button.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        doSomething();
    }
});

// ✅ Dùng: Method reference (Java 8+)
button.addActionListener(evt -> doSomething());
// hoặc
button.addActionListener(this::doSomething);
```

### 5. **Resource Loading**
```java
// ✅ Đúng: Check null
java.net.URL iconUrl = getClass().getResource("/images/icon.png");
if (iconUrl != null) {
    label.setIcon(new javax.swing.ImageIcon(iconUrl));
}

// ❌ Sai: Có thể NullPointerException
label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png")));
```

---

## 📋 Checklist Hoàn Thành

Sau khi convert một view:
- [ ] Không có auto-generated comments (//GEN-BEGIN, etc.)
- [ ] Không có @SuppressWarnings("unchecked")
- [ ] Tất cả imports cần thiết
- [ ] Font sử dụng Font.BOLD, Font.PLAIN
- [ ] Resource URLs checked for null
- [ ] Event listeners sử dụng method reference
- [ ] Compiles without errors
- [ ] Runs without runtime errors
- [ ] UI renders correctly
- [ ] All buttons/inputs work
- [ ] Table data loads (nếu có table)
- [ ] Xóa .form file

---

## 🔧 Công Cụ Hỗ Trợ

### Helper Classes
- `views.helpers.UIComponentFactory` - Factory methods cho components

### Utilities
- `swing.MyButton` - Styled button
- `swing.MyTextField` - Styled text input
- `swing.MyPassword` - Styled password input
- `swing.PanelGradiente` - Gradient panel
- `swing.PanelSlide` - Sliding animation

---

## 📞 FAQs

**Q: Có thể dùng lại auto-generated code không?**
A: Có, nhưng phải loại bỏ auto-generated comments. Chỉnh sửa để tuân theo code style.

**Q: Phải convert toàn bộ hay có thể từng view?**
A: Có thể từng view. Nhưng recommended là làm login forms trước (frm_Login, frm_Dangnhap, frm_Quenmatkhau) để test flow.

**Q: Liệu .form files có thể xóa được không?**
A: Có, sau khi convert sang Java code. Chúng không cần cho runtime.

**Q: Làm sao để tránh compile errors?**
A: Tuân theo import statements, dùng từ keywords đúng (Font.BOLD), check null khi load resources.

---

## 🎓 Next Steps

1. **Phase 1**: Convert 3 views hoàn thành (DONE: frm_Login, frm_Dangnhap, frm_Quenmatkhau)
2. **Phase 2**: Convert views đơn giản (frm_themthuoctinh, KhachHangForm)
3. **Phase 3**: Convert views với JTable (frm_Nhanvien, frm_Khachhang, frm_Sanpham)
4. **Phase 4**: Convert views phức tạp (frm_Khuyenmai, frm_Hoadon)
5. **Phase 5**: Convert Dashboard & Statistics (frm_Dashboard, frm_Thongke)
6. **Phase 6**: Testing & QA - Xác nhận toàn bộ flows hoạt động
7. **Phase 7**: Xóa tất cả .form files + Cleanup

---

**Status**: In Progress  
**Last Updated**: 2026-04-17
**Completed**: 3/13 (23%)
