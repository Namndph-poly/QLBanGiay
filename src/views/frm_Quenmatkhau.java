package views;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.Users;
import services.IUsersService;
import services.imp.UsersService;
import swing.MyButton;
import swing.MyPassword;
import swing.MyTextField;
import viewmodels.UsersViewmodel;

/**
 * Forget Password Form - Pure Java Swing Implementation
 * @author hungh
 */
public class frm_Quenmatkhau extends JPanel {

    // UI Components
    private MyTextField txt_email;
    private MyTextField txt_code;
    private MyPassword txtPass;
    private MyPassword txtPass1;
    private MyButton btn_doimk;
    private JButton btn_send;
    private JButton cmdBackLogin;
    private MyButton myButton1;
    private JLabel hideshow;
    private JLabel hideshow1;

    // Business logic
    boolean hish = false;
    boolean hish1 = false;
    IUsersService ius;
    ArrayList lstma;
    int ran;

    public frm_Quenmatkhau() {
        ius = new UsersService();
        lstma = new ArrayList();
        initComponents();
    }

    private void initComponents() {
        // Set panel properties
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(410, 450));
        setMinimumSize(new java.awt.Dimension(410, 450));
        setPreferredSize(new java.awt.Dimension(410, 450));
        setLayout(null);

        // Background Image
        JLabel jLabel6 = new JLabel();
        java.net.URL bgImageUrl = getClass().getResource("/images/HD-wallpaper-city-shoes-nike-heights-wristwatch-parkour.jpg");
        if (bgImageUrl != null) {
            jLabel6.setIcon(new javax.swing.ImageIcon(bgImageUrl));
        }
        add(jLabel6);
        jLabel6.setBounds(-360, -110, 940, 610);

        // Forget Password Title
        JLabel jLabel2 = new JLabel();
        jLabel2.setFont(new Font("Times New Roman", Font.BOLD, 24));
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quên mật khẩu");
        add(jLabel2);
        jLabel2.setBounds(70, 20, 257, 30);

        // Email Label
        JLabel jLabel1 = new JLabel();
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Tài khoản");
        add(jLabel1);
        jLabel1.setBounds(50, 70, 80, 24);

        // Email TextField
        txt_email = new MyTextField();
        txt_email.setBackground(new java.awt.Color(227, 255, 255));
        txt_email.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        add(txt_email);
        txt_email.setBounds(50, 90, 280, 40);

        // Code Label
        JLabel jLabel5 = new JLabel();
        jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Mã bảo mật");
        add(jLabel5);
        jLabel5.setBounds(50, 130, 100, 24);

        // Code TextField
        txt_code = new MyTextField();
        txt_code.setBackground(new java.awt.Color(227, 255, 255));
        txt_code.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        add(txt_code);
        txt_code.setBounds(50, 150, 280, 40);

        // Send Button (Email)
        btn_send = new JButton();
        java.net.URL emailIconUrl = getClass().getResource("/images/email.png");
        if (emailIconUrl != null) {
            btn_send.setIcon(new javax.swing.ImageIcon(emailIconUrl));
        }
        btn_send.setBackground(new java.awt.Color(125, 229, 251));
        btn_send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_sendMouseClicked(evt);
            }
        });
        add(btn_send);
        btn_send.setBounds(340, 150, 40, 40);

        // Password Label
        JLabel jLabel3 = new JLabel();
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");
        add(jLabel3);
        jLabel3.setBounds(50, 190, 100, 24);

        // Password Field
        txtPass = new MyPassword();
        txtPass.setBackground(new java.awt.Color(227, 255, 255));
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(txtPass);
        txtPass.setBounds(50, 210, 280, 40);

        // Show/Hide Password Icon
        hideshow = new JLabel();
        java.net.URL showIconUrl = getClass().getResource("/images/show.png");
        if (showIconUrl != null) {
            hideshow.setIcon(new javax.swing.ImageIcon(showIconUrl));
        }
        hideshow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideshowMouseClicked(evt);
            }
        });
        add(hideshow);
        hideshow.setBounds(350, 220, 30, 30);

        // Confirm Password Label
        JLabel jLabel4 = new JLabel();
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Confirm Password");
        add(jLabel4);
        jLabel4.setBounds(50, 250, 150, 24);

        // Confirm Password Field
        txtPass1 = new MyPassword();
        txtPass1.setBackground(new java.awt.Color(227, 255, 255));
        txtPass1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(txtPass1);
        txtPass1.setBounds(50, 270, 280, 40);

        // Show/Hide Confirm Password Icon
        hideshow1 = new JLabel();
        java.net.URL show1IconUrl = getClass().getResource("/images/show.png");
        if (show1IconUrl != null) {
            hideshow1.setIcon(new javax.swing.ImageIcon(show1IconUrl));
        }
        hideshow1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideshow1MouseClicked(evt);
            }
        });
        add(hideshow1);
        hideshow1.setBounds(350, 280, 30, 30);

        // Change Password Button
        btn_doimk = new MyButton();
        btn_doimk.setBackground(new java.awt.Color(125, 229, 251));
        btn_doimk.setForeground(new java.awt.Color(0, 51, 102));
        btn_doimk.setText("Đổi mật khẩu");
        btn_doimk.setColor(new java.awt.Color(125, 229, 251));
        btn_doimk.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn_doimk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_doimkMouseClicked(evt);
            }
        });
        add(btn_doimk);
        btn_doimk.setBounds(50, 340, 120, 40);

        // Exit Button
        myButton1 = new MyButton();
        myButton1.setBackground(new java.awt.Color(125, 229, 251));
        myButton1.setForeground(new java.awt.Color(0, 51, 102));
        myButton1.setText("Thoát");
        myButton1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        myButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });
        add(myButton1);
        myButton1.setBounds(220, 340, 120, 40);

        // Back to Login Button
        cmdBackLogin = new JButton();
        cmdBackLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
        cmdBackLogin.setForeground(new java.awt.Color(30, 122, 236));
        cmdBackLogin.setText("Đăng nhập ?");
        cmdBackLogin.setBorder(null);
        cmdBackLogin.setContentAreaFilled(false);
        cmdBackLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(cmdBackLogin);
        cmdBackLogin.setBounds(160, 400, 100, 25);
    }

    public void quenmatkhau() {
        txt_email.grabFocus();
    }

    public void addEventBackLogin(ActionListener event) {
        cmdBackLogin.addActionListener(event);
    }

    private void myButton1ActionPerformed(java.awt.event.MouseEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát không !") != JOptionPane.YES_OPTION) {
            return;
        }
        System.exit(0);
    }

    private void hideshowMouseClicked(java.awt.event.MouseEvent evt) {
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

    private void hideshow1MouseClicked(java.awt.event.MouseEvent evt) {
        if (!hish1) {
            hish1 = true;
            java.net.URL hideIconUrl = getClass().getResource("/images/hide.png");
            if (hideIconUrl != null) {
                hideshow1.setIcon(new javax.swing.ImageIcon(hideIconUrl));
            }
            txtPass1.setEchoChar((char) 0);
        } else {
            hish1 = false;
            java.net.URL showIconUrl = getClass().getResource("/images/show.png");
            if (showIconUrl != null) {
                hideshow1.setIcon(new javax.swing.ImageIcon(showIconUrl));
            }
            txtPass1.setEchoChar('●');
        }
    }

    private void btn_doimkMouseClicked(java.awt.event.MouseEvent evt) {
        int code = 0;
        if (txt_email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tài khoản!");
            return;
        }

        if (txt_code.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã bảo mật!");
            return;
        }

        try {
            code = Integer.parseInt(txt_code.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mã bảo mật phải là số!");
            return;
        }

        if (txtPass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời bạn nhập mật khẩu mới!");
            return;
        }

        if (txtPass.getText().length() < 6) {
            JOptionPane.showMessageDialog(this, "Độ dài mật khẩu yêu cầu 6 kí tự trở lên!");
            return;
        }

        if (txtPass1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời bạn xác nhận mật khẩu mới!");
            return;
        }
        if (!txtPass.getText().equals(txtPass1.getText())) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới và mật khẩu xác nhận chưa giống nhau!");
            return;
        }
        if (ius.getUserbytk(txt_email.getText()) == null) {
            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại!");
            return;
        }
        if (code == ran) {
            UsersViewmodel us = new UsersViewmodel();
            us.setMk(txtPass.getText());
            ius.updateMK(us, txt_email.getText());
            JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu thành công");
            return;
        }
        JOptionPane.showMessageDialog(this, "Sai mã bảo mật");
    }

    private void btn_sendMouseClicked(java.awt.event.MouseEvent evt) {
        String taikhoan = txt_email.getText().trim();

        Users user = ius.getUserbytk(taikhoan);
        if (user.getTk() == null) {
            JOptionPane.showMessageDialog(this, "không có tài khoản này ( " + taikhoan + " ) vui lòng nhập tài khoản khác!");
            return;
        }
        Random random = new Random();
        ran = random.nextInt(99999);
        String sub = "Verification code";
        String messsage = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title></title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <h3 style=\"color: blue;\">Verification code</h3>\n"
                + "    <div>Your verification code is : " + ran + "</div>\n"
                + "    <h3 style=\"color: blue;\">Thank you very much</h3>\n"
                + "</body>\n"
                + "</html>";
        utilconnext.SendMail.send(user.getEmail(), sub, messsage);
        JOptionPane.showMessageDialog(this, " Mã đã gửi vào gmail : " + user.getEmail());
        txt_email.setEnabled(false);
    }
}
