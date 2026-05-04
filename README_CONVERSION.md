## ✅ UTC_JAVA - NetBeans Form → Pure Swing Conversion Project

Dự án này hướng dẫn hoàn thành conversion toàn bộ giao diện từ **NetBeans Form Editor** (`.form` files) sang **Pure Java Swing Code** tay.

---

## 📝 Tóm Tắt Công Việc

### Mục Tiêu
Loại bỏ phụ thuộc vào NetBeans Form Editor, chuyển đổi tất cả views thành clean Java Swing code có thể bảo trì dễ dàng.

### Tại Sao
- ✅ Code rõ ràng, dễ hiểu
- ✅ Không cần IDE để edit views  
- ✅ Version control thân thiện (no binary .form files)
- ✅ Hỗ trợ refactoring tốt hơn

### Views Đã Hoàn Thành (3)
```
✓ frm_Dangnhap.java    - Login Form (Simple)
✓ frm_Quenmatkhau.java - Password Reset (Medium)  
✓ frm_Login.java       - Main Window (Complex with Animation)
```

---

## 🚀 Cách Chạy / Test

### 1. Build Project
```bash
cd E:\UTC_JAVA
# Build bằng Ant hoặc IDE của bạn
```

### 2. Run Application
```bash
java application.main
# Hoặc từ IDE: Run Main.java
```

### 3. Test Login Flow
- Username: `admin`
- Password: `123`
- Click "Đăng nhập" → Should close login window if successful
- Click "Forget password?" → Should slide to password reset form
- Click "Đăng nhập ?" → Should slide back to login form

---

## 📚 Tài Liệu Tham Khảo

| File | Nội Dung |
|------|----------|
| `CONVERSION_GUIDE.md` | Hướng dẫn chi tiết conversion pattern |
| `CONVERSION_PROGRESS.md` | Tiến độ + checklist + code examples |
| `src/views/helpers/UIComponentFactory.java` | Utility helper cho tạo components |

---

## 🎯 Giai Đoạn Conversion

### Phase 1: ✅ COMPLETED
- Login forms (3 views)
- Simple forms với custom components

**Files:**
- ✓ frm_Login.java
- ✓ frm_Dangnhap.java  
- ✓ frm_Quenmatkhau.java

### Phase 2: TODO (Simple)
- Small utility forms
- Est. Time: 1-2 giờ

**Files:**
- [ ] frm_themthuoctinh.java
- [ ] KhachHangForm.java

### Phase 3: TODO (Medium)
- Forms with basic layouts
- Est. Time: 4-6 giờ

**Files:**
- [ ] frm_Banhang.java
- [ ] frm_Dashboard.java
- [ ] frm_Thongke.java

### Phase 4: TODO (Complex - JTable)
- Management forms with data tables
- Est. Time: 8-12 giờ

**Files:**
- [ ] frm_Nhanvien.java (Employee Management)
- [ ] frm_Khachhang.java (Customer Management)
- [ ] frm_Sanpham.java (Product Management)
- [ ] frm_Hoadon.java (Invoice Management)
- [ ] frm_Khuyenmai.java (Promotions Management)

### Phase 5: CLEANUP
- Delete all .form files
- Final testing

---

## 📊 Progress Tracker

```
Converted:  ███                    3/13 (23%)
Remaining:  ███████████████████   10/13 (77%)
```

---

## 💻 Tech Stack

**Language**: Java 17+  
**Frameworks**: Swing  
**Custom Components**:
- `swing.MyButton` - Styled button with hover effects
- `swing.MyTextField` - Text field with icon support
- `swing.MyPassword` - Password field with styling
- `swing.PanelGradiente` - Gradient background panel
- `swing.PanelSlide` - Slide animation panel

**External Libraries**:
- JCalendar - Date picker (`JDateChooser`)
- JFreeChart - Charts (if used in Dashboard)
- Other libs in `thuvienjava/`

---

## 📋 Checklist Before Submitting

After completing each phase:

- [ ] All views compile successfully
- [ ] No `@SuppressWarnings` annotations
- [ ] No auto-generated comments (//GEN-*)
- [ ] Consistent naming conventions
- [ ] Proper error handling
- [ ] Resource files loaded safely
- [ ] Event listeners functional
- [ ] UI renders without distortion
- [ ] Table data populates (if applicable)
- [ ] Images display correctly
- [ ] Application launches without errors

---

## 🔍 Key Code Patterns

### Creating UI Components
```java
// Label
JLabel label = new JLabel("Text");
label.setFont(new Font("Segoe UI", Font.BOLD, 14));
label.setBounds(x, y, width, height);
add(label);

// MyTextField
MyTextField field = new MyTextField();
field.setBackground(new Color(227, 255, 255));
field.setBounds(x, y, width, height);
add(field);

// MyButton  
MyButton button = new MyButton();
button.setText("Click Me");
button.setBounds(x, y, width, height);
button.addActionListener(this::onButtonClick);
add(button);

// Table
JTable table = new JTable();
DefaultTableModel model = (DefaultTableModel) table.getModel();
model.addColumn("Column Name");
JScrollPane scroll = new JScrollPane(table);
scroll.setBounds(x, y, width, height);
add(scroll);
```

### Event Handling
```java
// Modern way (Java 8+)
button.addActionListener(evt -> handleClick());
button.addActionListener(this::handleClick);

// Old way (avoid)
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        handleClick();
    }
});
```

### Loading Resources Safely
```java
java.net.URL imageUrl = getClass().getResource("/images/icon.png");
if (imageUrl != null) {
    label.setIcon(new ImageIcon(imageUrl));
}
```

---

## 🐛 Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| `NullPointerException` on image load | Check resource exists + use null check |
| Layout looks wrong | Check setBounds values match .form design |
| Components not visible | Verify add() called + correct z-order |
| Table empty | Ensure loadData() called in constructor |
| Button not responding | Check addActionListener() + method name |

---

## 📞 Need Help?

1. **Read the guides** - Check `CONVERSION_GUIDE.md` for patterns
2. **Check examples** - Look at `frm_Dangnhap.java` for simple example
3. **Use helper class** - `UIComponentFactory` has utility methods
4. **Test incrementally** - Convert one section, test, then next

---

## 📄 Files Overview

```
UTC_JAVA/
├── src/
│   ├── application/
│   │   └── main.java          ← Entry point (unchanged)
│   ├── views/
│   │   ├── frm_Login.java     ✓ Converted
│   │   ├── frm_Dangnhap.java  ✓ Converted
│   │   ├── frm_Quenmatkhau.java ✓ Converted
│   │   ├── *.java             [10 more to convert]
│   │   ├── *.form             [13 files - can delete after conversion]
│   │   └── helpers/
│   │       └── UIComponentFactory.java
│   ├── services/              [Unchanged - Business Logic]
│   ├── models/                [Unchanged - Domain Objects]
│   ├── swing/                 [Unchanged - Custom Components]
│   └── ...
├── CONVERSION_GUIDE.md        [Detailed conversion patterns]
├── CONVERSION_PROGRESS.md     [Phase breakdown + examples]
└── README.md                  [This file]
```

---

## ⚡ Next Actions

1. **Phase 2**: Convert simple views (2 files)
2. **Phase 3**: Convert medium views (3 files)  
3. **Phase 4**: Convert complex views (5 files)
4. **Phase 5**: Final cleanup + testing
5. **Delete**: All `.form` files

---

**Project Status**: 🟡 In Progress  
**Completion**: 23% (3/13)  
**Last Updated**: 2026-04-17  

Estimated Completion: 1-2 weeks (depending on developer availability)
