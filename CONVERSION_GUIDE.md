/**
 * CONVERSION GUIDE: NetBeans Form Editor to Pure Java Swing
 * 
 * Project: UTC_JAVA - Shoes Store Management System
 * Status: In Progress
 * 
 * COMPLETED CONVERSIONS (Pure Java Swing):
 * =========================================
 * ✓ frm_Dangnhap.java - Login form (JPanel)
 * ✓ frm_Quenmatkhau.java - Password reset form (JPanel)
 * ✓ frm_Login.java - Main login window (JFrame)
 * 
 * PENDING CONVERSIONS:
 * ====================
 * [ ] frm_Nhanvien.java - Employee management (JPanel with JTable)
 * [ ] frm_Khachhang.java - Customer management (JPanel with JTable)
 * [ ] frm_Sanpham.java - Product management (JPanel with JTable)
 * [ ] frm_Banhang.java - Sales (JPanel)
 * [ ] frm_Hoadon.java - Invoice management (JPanel)
 * [ ] frm_Khuyenmai.java - Promotions (JPanel with JTable)
 * [ ] frm_Dashboard.java - Dashboard (JPanel)
 * [ ] frm_Thongke.java - Statistics (JPanel)
 * [ ] frm_themthuoctinh.java - Add attributes (JPanel)
 * [ ] KhachHangForm.java - Customer form (JPanel)
 * 
 * CONVERSION PATTERN:
 * ==================
 * 1. Remove @SuppressWarnings("unchecked") annotation
 * 2. Remove auto-generated comments (//GEN-BEGIN, //GEN-END, etc.)
 * 3. Convert initComponents() method:
 *    - Create components in declarations (private fields)
 *    - Set properties using Java code (setFont, setColor, setBounds, etc.)
 *    - Use null layout (setLayout(null)) for absolute positioning
 *    - Attach listeners using method references (this::methodName)
 * 4. Extract event handlers to private methods (keep logic clean)
 * 5. Keep business logic (services, models dependencies) unchanged
 * 6. Use Font.BOLD, Font.PLAIN instead of magic number 1, 0
 * 7. Add null checks for resource loading (getClass().getResource())
 * 
 * KEY COMPONENTS USED:
 * ====================
 * - swing.MyButton - Custom styled button
 * - swing.MyPassword - Custom password field with styling
 * - swing.MyTextField - Custom text field with styling
 * - swing.PanelGradiente - Gradient panel
 * - swing.PanelSlide - Sliding panel for animations
 * - swing.PanelBorder - Border panel
 * - swing.SearchText - Search text field
 * 
 * NAMING CONVENTIONS:
 * ===================
 * - JButton: btn* (e.g., btnSave, btnDelete)
 * - JLabel: lbl* (e.g., lblTitle, lblStatus)
 * - JTextField: txt* (e.g., txtName, txtEmail)
 * - JTable: tbl* (e.g., tblEmployees, tblProducts)
 * - JComboBox: cbo* (e.g., cboStatus, cboCategory)
 * - JCheckBox: chk* (e.g., chkActive, chkSelected)
 * - JPanel: pnl* (e.g., pnlTop, pnlLeft)
 * 
 * BEST PRACTICES:
 * ===============
 * 1. Null layout (setBounds) used for precise positioning matching design
 * 2. All UI initialization in initComponents() method
 * 3. Public methods for external event listener attachment
 * 4. Private methods for internal event handlers
 * 5. Resource URLs checked for null before use
 * 6. Use meaningful variable names (no field1, field2, etc.)
 * 7. Comment complex layout calculations
 * 8. Keep component references for later access/update
 * 
 * FILES TO DELETE AFTER CONVERSION:
 * ==================================
 * Once all .java files are converted, all .form files can be safely deleted:
 * - *.form (NetBeans form definition files)
 * 
 * These are only needed by NetBeans Form Editor and are not used at runtime.
 * The pure Java code replaces all GUI functionality.
 * 
 * TESTING CHECKLIST:
 * ==================
 * [ ] UI renders correctly (all components visible)
 * [ ] All event listeners work (buttons, menus, etc.)
 * [ ] Text fields accept input
 * [ ] Tables populate with data
 * [ ] Custom components (MyButton, MyTextField) display styling
 * [ ] Images load correctly
 * [ ] No console errors or warnings
 * [ ] Application flow works (Login -> Dashboard)
 * 
 * COMPILATION:
 * ============
 * All converted files should compile with:
 * - No errors
 * - Minimal warnings (mainly about deprecated methods)
 * 
 * RUN TESTS:
 * ==========
 * Start with: java application.main
 * This launches frm_Login which loads converted forms
 */
