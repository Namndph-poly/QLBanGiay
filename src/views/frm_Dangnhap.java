package views;

import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import services.IUserServiec;
import services.imp.UserServiec;
import swing.MyButton;
import swing.MyPassword;
import swing.MyTextField;

/**
 * Login Form - Pure Java Swing Implementation
 * @author hungh
 */
public class frm_Dangnhap extends JPanel {

    // UI Components
    private MyTextField txtUser;
    private MyPassword txtPass;
    private MyButton myButton1;
    private MyButton myButton2;
    private JLabel hideshow;
    private JButton quenmatkhau1;

    // Business logic
    boolean hish = false;
    private final IUserServiec userServiec;

    public frm_Dangnhap() {
        userServiec = new UserServiec();
        initComponents();
    }

    private void initComponents() {
        // Set panel properties
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(328, 373));
        setMinimumSize(new java.awt.Dimension(328, 373));
        setPreferredSize(new java.awt.Dimension(328, 373));
        setLayout(null);

        // Login Title Label
        JLabel jLabel2 = new JLabel();
        jLabel2.setFont(new Font("Times New Roman", Font.BOLD, 32));
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LOGIN");
        add(jLabel2);
        jLabel2.setBounds(70, 30, 257, 40);

        // Username Label
        JLabel jLabel1 = new JLabel();
        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jLabel1.setText("Tài khoản");
        add(jLabel1);
        jLabel1.setBounds(60, 100, 90, 24);

        // Username TextField
        txtUser = new MyTextField();
        txtUser.setBackground(new java.awt.Color(227, 255, 255));
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUser.setMinimumSize(new java.awt.Dimension(300, 40));
        txtUser.setPreferredSize(new java.awt.Dimension(300, 40));
        add(txtUser);
        txtUser.setBounds(60, 130, 280, 41);

        // Password Label
        JLabel jLabel3 = new JLabel();
        jLabel3.setBackground(new java.awt.Color(102, 102, 255));
        jLabel3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        jLabel3.setText("Mật khẩu");
        add(jLabel3);
        jLabel3.setBounds(60, 190, 80, 24);

        // Password Field
        txtPass = new MyPassword();
        txtPass.setBackground(new java.awt.Color(227, 255, 255));
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPass.setMinimumSize(new java.awt.Dimension(300, 40));
        txtPass.setPreferredSize(new java.awt.Dimension(300, 40));
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });
        add(txtPass);
        txtPass.setBounds(60, 220, 280, 40);

        // Show/Hide Password Icon
        hideshow = new JLabel();
        java.net.URL hideIconUrl = getClass().getResource("/images/hide.png");
        if (hideIconUrl != null) {
            hideshow.setIcon(new javax.swing.ImageIcon(hideIconUrl));
        }
        hideshow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideshowMouseClicked();
            }
        });
        add(hideshow);
        hideshow.setBounds(360, 220, 30, 40);

        // Forget Password Button
        quenmatkhau1 = new JButton();
        quenmatkhau1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
        quenmatkhau1.setForeground(new java.awt.Color(204, 0, 0));
        quenmatkhau1.setText("Forget password?");
        quenmatkhau1.setBorder(null);
        quenmatkhau1.setContentAreaFilled(false);
        quenmatkhau1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(quenmatkhau1);
        quenmatkhau1.setBounds(240, 300, 120, 19);

        // Notification Label (hidden by default)
        JButton lbl_thongBao = new JButton();
        lbl_thongBao.setForeground(new java.awt.Color(255, 51, 51));
        lbl_thongBao.setBorder(null);
        lbl_thongBao.setContentAreaFilled(false);
        lbl_thongBao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(lbl_thongBao);
        lbl_thongBao.setBounds(240, 270, 0, 0);

        // Login Button
        myButton2 = new MyButton();
        myButton2.setBackground(new java.awt.Color(125, 229, 251));
        myButton2.setForeground(new java.awt.Color(0, 51, 102));
        myButton2.setText("Đăng nhập");
        myButton2.setFont(new Font("Times New Roman", Font.BOLD, 12));
        myButton2.addActionListener(this::myButton2ActionPerformed);
        add(myButton2);
        myButton2.setBounds(60, 350, 120, 48);

        // Exit Button
        myButton1 = new MyButton();
        myButton1.setBackground(new java.awt.Color(125, 229, 251));
        myButton1.setForeground(new java.awt.Color(0, 51, 102));
        myButton1.setText("Thoát");
        myButton1.setFont(new Font("Times New Roman", Font.BOLD, 12));
        myButton1.addActionListener(this::myButton1ActionPerformed);
        add(myButton1);
        myButton1.setBounds(230, 350, 120, 48);
    }

    public void dangnhap() {
        txtUser.grabFocus();
    }

    public void addEventquenmatkhau(ActionListener event) {
        quenmatkhau1.addActionListener(event);
    }

    public void btndangnhapEven(ActionListener event) {
        myButton2.addActionListener(event);
    }

    public boolean isdangnhap() {
        String TaiKhaon = txtUser.getText().trim();
        String MatKhau = txtPass.getText().trim();
        return userServiec.getUser(TaiKhaon, MatKhau);
    }

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Thoát Không !") != JOptionPane.YES_OPTION) {
            return;
        }
        System.exit(0);
    }

    private void hideshowMouseClicked() {
        if (!hish) {
            hish = true;
            java.net.URL hideIconUrl = getClass().getResource("/images/hide.png");
            if (hideIconUrl != null) {
                hideshow.setIcon(new javax.swing.ImageIcon(hideIconUrl));
            }
            txtPass.setEchoChar((char) 0);
        } else {
            hish = false;
            java.net.URL showIconUrl = getClass().getResource("/images/show.png");
            if (showIconUrl != null) {
                hideshow.setIcon(new javax.swing.ImageIcon(showIconUrl));
            }
            txtPass.setEchoChar('●');
        }
    }

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Login button action - can be extended with more logic
    }

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (isdangnhap()) {
                Component comp = SwingUtilities.getRoot(this);
                ((Window) comp).dispose();
            }
        }
    }
}
