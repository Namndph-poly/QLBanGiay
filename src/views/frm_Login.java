package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import swing.PanelGradiente;
import swing.PanelSlide;

/**
 * Login Frame - Pure Java Swing Implementation
 * Main login window with slide animation between login and password reset forms
 * @author hungh
 */
public class frm_Login extends JFrame {

    // UI Components
    private PanelGradiente panelGradiente1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private PanelSlide Slide;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;

    // Forms
    private frm_Dangnhap dangnhap;
    private frm_Quenmatkhau quenmatkhau;

    public frm_Login() {
        dangnhap = new frm_Dangnhap();
        quenmatkhau = new frm_Quenmatkhau();
        initComponents();
        setLocationRelativeTo(null);
        animation();
    }

    private void initComponents() {
        // Create components
        panelGradiente1 = new PanelGradiente();
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jPanel2 = new JPanel();
        Slide = new PanelSlide();

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 450));
        setResizable(false);
        setTitle("Login - Shoes Store");

        // Left Panel (Welcome Info)
        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(null);

        // Welcome Title
        jLabel1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 28));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("WELCOME TO ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(55, 37, 153, 31);

        // Store Name
        jLabel3.setFont(new Font("Sylfaen", Font.BOLD, 24));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SHOES STORE");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(127, 74, 162, 48);

        // Logo Icon
        java.net.URL logoUrl = getClass().getResource("/images/chaneo48.png");
        if (logoUrl != null) {
            jLabel4.setIcon(new javax.swing.ImageIcon(logoUrl));
        }
        jPanel1.add(jLabel4);
        jLabel4.setBounds(61, 74, 48, 48);

        // Background Image
        java.net.URL bgUrl = getClass().getResource("/images/HD-wallpaper-city-shoes-nike-heights-wristwatch-parkour.jpg");
        if (bgUrl != null) {
            jLabel5.setIcon(new javax.swing.ImageIcon(bgUrl));
        }
        jLabel5.setText("jLabel5");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 0, 380, 450);

        panelGradiente1.add(jPanel1);
        jPanel1.setBounds(0, 0, 380, 450);

        // Right Panel (Login/Password Reset Forms)
        GroupLayout SlideLayout = new GroupLayout(Slide);
        Slide.setLayout(SlideLayout);
        SlideLayout.setHorizontalGroup(
            SlideLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        SlideLayout.setVerticalGroup(
            SlideLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Slide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Slide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelGradiente1.add(jPanel2);
        jPanel2.setBounds(380, 0, 420, 450);

        // Main Frame Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        pack();
    }

    private void animation() {
        // Setup slide animation between login and password reset forms
        Slide.setAnimate(20);
        Slide.init(dangnhap, quenmatkhau);

        // Event: Forget password button - show password reset form
        dangnhap.addEventquenmatkhau(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Slide.show(1);
                quenmatkhau.quenmatkhau();
            }
        });

        // Event: Back to login button - show login form
        quenmatkhau.addEventBackLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Slide.show(0);
                dangnhap.dangnhap();
            }
        });

        // Event: Login button - dispose frame if login successful
        dangnhap.btndangnhapEven(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dangnhap.isdangnhap()) {
                    dispose();
                }
            }
        });
    }
}
