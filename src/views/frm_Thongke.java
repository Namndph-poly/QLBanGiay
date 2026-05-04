package views;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import repositorys.IThongkeRepository;
import repositorys.imp.ThongkeRepository;
import models.Thongke;
import swing.PanelBorder;
import swing.PanelGradiente;

public class frm_Thongke extends JPanel {

    private IThongkeRepository repository;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;
    private DecimalFormat df = new DecimalFormat("#,###");

    private PanelGradiente panelGradiente1;
    private PanelBorder panelBorder2, panelBorder3, panelBorder4, panelBorder1, panelBorder5;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9;
    private JLabel lbl_doanhthu, lbl_loinhuanYear, lbl_sanpham, lbl_hd, lbl_kh, lbl_tu, lbl_den;
    private JRadioButton rd_ngay, rd_thang, rd_nam, rd_khoang;
    private ButtonGroup buttonGroup1;
    private JTabbedPane Click_Nv;
    private JScrollPane jScrollPane2;
    private JTable Tb_Sp;
    private JDayChooser date_day;
    private JMonthChooser date_month, date_tu, Date_den;
    private JYearChooser date_year;
    private javax.swing.JButton btn_bieudo;

    public frm_Thongke() {
        initComponents();
        repository = new ThongkeRepository();
        defaultTableModel1 = (DefaultTableModel) Tb_Sp.getModel();
        loadSP();
        date_day.setVisible(false);
        date_month.setVisible(false);
        date_year.setVisible(false);
        date_tu.setVisible(false);
        Date_den.setVisible(false);
        lbl_tu.setVisible(false);
        lbl_den.setVisible(false);
        loadhd();
        doanhthu();
        loadkh();
    }

    void loadSP() {
        defaultTableModel1.setRowCount(0);
        int stt = 1;
        for (Thongke x : repository.getsp()) {
            defaultTableModel1.addRow(new Object[]{ stt, x.getChiTietSP(), x.getSoSP() });
            stt++;
        }
    }

    void loadhd() {
        lbl_hd.setText(String.valueOf(repository.gethdday()));
    }

    void loadkh() {
        lbl_kh.setText(String.valueOf(repository.getkhday()));
    }

    void doanhthu() {
        lbl_doanhthu.setText(df.format(repository.getdtday()));
        lbl_sanpham.setText(String.valueOf(repository.getbyday()));
    }

    private void initComponents() {
        setLayout(null);
        setBackground(Color.WHITE);
        setMinimumSize(new java.awt.Dimension(1010, 640));

        panelGradiente1 = new PanelGradiente();
        panelGradiente1.setColorPrimario(new Color(204, 255, 255));
        panelGradiente1.setColorSecundario(new Color(204, 204, 255));
        panelGradiente1.setLayout(null);
        panelGradiente1.setBounds(0, 0, 1010, 640);
        add(panelGradiente1);

        panelBorder2 = new PanelBorder();
        panelBorder2.setBackground(new Color(204, 204, 255));
        panelBorder2.setLayout(null);
        panelBorder2.setBounds(10, 0, 230, 190);
        panelGradiente1.add(panelBorder2);

        jLabel1 = new JLabel("Doanh Thu");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel1.setForeground(Color.RED);
        jLabel1.setBounds(80, 0, 110, 40);
        panelBorder2.add(jLabel1);

        lbl_doanhthu = new JLabel("0");
        lbl_doanhthu.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl_doanhthu.setHorizontalAlignment(JLabel.CENTER);
        lbl_doanhthu.setBounds(10, 50, 210, 90);
        panelBorder2.add(lbl_doanhthu);

        panelBorder3 = new PanelBorder();
        panelBorder3.setBackground(new Color(204, 204, 255));
        panelBorder3.setLayout(null);
        panelBorder3.setBounds(260, 0, 230, 190);
        panelGradiente1.add(panelBorder3);

        jLabel7 = new JLabel("Sản Phẩm");
        jLabel7.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel7.setForeground(Color.RED);
        jLabel7.setBounds(80, 0, 130, 50);
        panelBorder3.add(jLabel7);

        lbl_sanpham = new JLabel("0");
        lbl_sanpham.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl_sanpham.setHorizontalAlignment(JLabel.CENTER);
        lbl_sanpham.setBounds(30, 60, 160, 100);
        panelBorder3.add(lbl_sanpham);

        panelBorder4 = new PanelBorder();
        panelBorder4.setBackground(new Color(204, 204, 255));
        panelBorder4.setLayout(null);
        panelBorder4.setBounds(510, 0, 230, 190);
        panelGradiente1.add(panelBorder4);

        jLabel2 = new JLabel("Hóa Đơn");
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel2.setForeground(Color.RED);
        jLabel2.setBounds(70, 0, 110, 40);
        panelBorder4.add(jLabel2);

        lbl_hd = new JLabel("0");
        lbl_hd.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl_hd.setHorizontalAlignment(JLabel.CENTER);
        lbl_hd.setBounds(40, 50, 150, 100);
        panelBorder4.add(lbl_hd);

        panelBorder5 = new PanelBorder();
        panelBorder5.setBackground(new Color(204, 204, 255));
        panelBorder5.setLayout(null);
        panelBorder5.setBounds(760, 0, 230, 190);
        panelGradiente1.add(panelBorder5);

        jLabel3 = new JLabel("Khách Hàng");
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel3.setForeground(Color.RED);
        jLabel3.setBounds(60, 0, 130, 50);
        panelBorder5.add(jLabel3);

        lbl_kh = new JLabel("0");
        lbl_kh.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl_kh.setHorizontalAlignment(JLabel.CENTER);
        lbl_kh.setBounds(40, 50, 170, 100);
        panelBorder5.add(lbl_kh);

        panelBorder1 = new PanelBorder();
        panelBorder1.setBackground(new Color(204, 204, 255));
        panelBorder1.setLayout(null);
        panelBorder1.setBounds(10, 210, 990, 430);
        panelGradiente1.add(panelBorder1);

        buttonGroup1 = new ButtonGroup();

        rd_ngay = new JRadioButton("Ngày");
        rd_ngay.setBackground(Color.WHITE);
        rd_ngay.setBounds(30, 0, 60, 21);
        rd_ngay.addActionListener(e -> rd_ngayActionPerformed(e));
        buttonGroup1.add(rd_ngay);
        panelBorder1.add(rd_ngay);

        rd_thang = new JRadioButton("Tháng");
        rd_thang.setBackground(Color.WHITE);
        rd_thang.setBounds(30, 20, 60, 21);
        rd_thang.addActionListener(e -> rd_thangActionPerformed(e));
        buttonGroup1.add(rd_thang);
        panelBorder1.add(rd_thang);

        rd_nam = new JRadioButton("Năm");
        rd_nam.setBackground(Color.WHITE);
        rd_nam.setBounds(30, 40, 60, 20);
        rd_nam.addActionListener(e -> rd_namActionPerformed(e));
        buttonGroup1.add(rd_nam);
        panelBorder1.add(rd_nam);

        rd_khoang = new JRadioButton("Tìm theo khoảng");
        rd_khoang.setBackground(Color.WHITE);
        rd_khoang.setBounds(30, 60, 120, 21);
        rd_khoang.addActionListener(e -> rd_khoangActionPerformed(e));
        buttonGroup1.add(rd_khoang);
        panelBorder1.add(rd_khoang);

        rd_ngay.setSelected(true);

        date_day = new JDayChooser();
        date_day.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204)));
        date_day.setBounds(160, 0, 280, 110);
        panelBorder1.add(date_day);

        date_month = new JMonthChooser();
        date_month.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204), 2));
        date_month.setBounds(200, 10, 110, 30);
        panelBorder1.add(date_month);

        date_year = new JYearChooser();
        date_year.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204), 2));
        date_year.setBounds(200, 30, 100, 30);
        panelBorder1.add(date_year);

        date_tu = new JMonthChooser();
        date_tu.setBounds(200, 60, 125, 22);
        panelBorder1.add(date_tu);

        Date_den = new JMonthChooser();
        Date_den.setBounds(360, 60, 125, 22);
        panelBorder1.add(Date_den);

        lbl_tu = new JLabel("Từ");
        lbl_tu.setBounds(170, 60, 20, 20);
        panelBorder1.add(lbl_tu);

        lbl_den = new JLabel("Đến");
        lbl_den.setBounds(330, 60, 30, 20);
        panelBorder1.add(lbl_den);

        btn_bieudo = new javax.swing.JButton();
        btn_bieudo.setBackground(new Color(153, 255, 204));
        btn_bieudo.setText("Biểu đồ");
        btn_bieudo.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204), 2));
        btn_bieudo.setBounds(780, 30, 140, 40);
        btn_bieudo.addActionListener(e -> btn_bieudoActionPerformed(e));
        panelBorder1.add(btn_bieudo);

        Tb_Sp = new JTable();
        Tb_Sp.setBackground(new Color(255, 245, 255));
        Tb_Sp.setRowHeight(25);
        DefaultTableModel model = (DefaultTableModel) Tb_Sp.getModel();
        model.addColumn("STT");
        model.addColumn("Tên Sản Phẩm");
        model.addColumn("Số Lượt Mua");

        jScrollPane2 = new JScrollPane(Tb_Sp);
        jScrollPane2.setBounds(0, 90, 990, 340);
        panelBorder1.add(jScrollPane2);

        Click_Nv = new JTabbedPane();
        Click_Nv.addTab("Sản Phẩm", jScrollPane2);
        Click_Nv.setBounds(0, 90, 990, 340);
        panelBorder1.add(Click_Nv);
    }

    private void rd_ngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_ngayActionPerformed
        date_day.setVisible(true);
        date_month.setVisible(false);
        date_year.setVisible(false);
        date_tu.setVisible(false);
        Date_den.setVisible(false);
        lbl_tu.setVisible(false);
        lbl_den.setVisible(false);
        lbl_doanhthu.setText(df.format(repository.getdtday(String.valueOf(date_day.getDay()))));
        lbl_hd.setText(String.valueOf(repository.gethdday(String.valueOf(date_day.getDay()))));
        lbl_kh.setText(String.valueOf(repository.getkhday(String.valueOf(date_day.getDay()))));
        lbl_sanpham.setText(String.valueOf(repository.getbyday(String.valueOf(date_day.getDay()))));
        defaultTableModel1.setRowCount(0);
        int stt = 1;
        for (Thongke x : repository.getspday(String.valueOf(date_day.getDay()))) {
            defaultTableModel1.addRow(new Object[]{
                stt,
                x.getChiTietSP(),
                x.getSoSP()
            });
            stt++;
        }
    }//GEN-LAST:event_rd_ngayActionPerformed

    private void rd_thangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_thangActionPerformed
        date_day.setVisible(false);
        date_month.setVisible(true);
        date_year.setVisible(false);
        date_tu.setVisible(false);
        Date_den.setVisible(false);
        lbl_tu.setVisible(false);
        lbl_den.setVisible(false);
        lbl_doanhthu.setText(df.format(repository.getdtmonth(String.valueOf(date_month.getMonth() + 1))));
        lbl_hd.setText(String.valueOf(repository.gethdmonth(String.valueOf(date_month.getMonth() + 1))));
        lbl_kh.setText(String.valueOf(repository.getkhmonth(String.valueOf(date_month.getMonth() + 1))));
        lbl_sanpham.setText(String.valueOf(repository.getbymonth(String.valueOf(date_month.getMonth() + 1))));
        defaultTableModel1.setRowCount(0);
        int stt = 1;
        for (Thongke x : repository.getspmonth(String.valueOf(date_month.getMonth() + 1))) {
            defaultTableModel1.addRow(new Object[]{
                stt,
                x.getChiTietSP(),
                x.getSoSP()
            });
            stt++;
        }
    }//GEN-LAST:event_rd_thangActionPerformed

    private void rd_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_namActionPerformed
        date_day.setVisible(false);
        date_month.setVisible(false);
        date_year.setVisible(true);
        date_tu.setVisible(false);
        Date_den.setVisible(false);
        lbl_tu.setVisible(false);
        lbl_den.setVisible(false);
        lbl_doanhthu.setText(df.format(repository.getdtyear(String.valueOf(date_year.getYear()))));
        lbl_hd.setText(String.valueOf(repository.gethdyear(String.valueOf(date_year.getYear()))));
        lbl_kh.setText(String.valueOf(repository.getkhyear(String.valueOf(date_year.getYear()))));
        lbl_sanpham.setText(String.valueOf(repository.getbyyear(String.valueOf(date_year.getYear()))));
        defaultTableModel1.setRowCount(0);
        int stt = 1;
        for (Thongke x : repository.getspyear(String.valueOf(date_year.getYear()))) {
            defaultTableModel1.addRow(new Object[]{
                stt,
                x.getChiTietSP(),
                x.getSoSP()
            });
            stt++;
        }
    }//GEN-LAST:event_rd_namActionPerformed

    private void btn_bieudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bieudoActionPerformed
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(repository.getmonth1(), "VND", "1");
        dataset.addValue(repository.getmonth2(), "VND", "2");
        dataset.addValue(repository.getmonth3(), "VND", "3");
        dataset.addValue(repository.getmonth4(), "VND", "4");
        dataset.addValue(repository.getmonth5(), "VND", "5");
        dataset.addValue(repository.getmonth6(), "VND", "6");
        dataset.addValue(repository.getmonth7(), "VND", "7");
        dataset.addValue(repository.getmonth8(), "VND", "8");
        dataset.addValue(repository.getmonth9(), "VND", "9");
        dataset.addValue(repository.getmonth10(), "VND", "10");
        dataset.addValue(repository.getmonth11(), "VND", "11");
        dataset.addValue(repository.getmonth12(), "VND", "12");
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU",
                "THÁNG", "VND",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot categoryPlot = new CategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLACK);
        ChartFrame chartFrame = new ChartFrame("Biều đồ", barChart);
        chartFrame.setVisible(true);
        chartFrame.setSize(1000, 500);
        chartFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_btn_bieudoActionPerformed

    private void rd_khoangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_khoangActionPerformed
        date_day.setVisible(false);
        date_month.setVisible(false);
        date_year.setVisible(false);
        date_tu.setVisible(true);
        Date_den.setVisible(true);
        lbl_tu.setVisible(true);
        lbl_den.setVisible(true);
        if (date_tu.getMonth() + 1 > Date_den.getMonth() + 1) {
            JOptionPane.showMessageDialog(this, "Tháng bắt đầu phải nhỏ hơn tháng kết thúc");
            return;
        }
        lbl_doanhthu.setText(df.format(repository.getdtkhoang1(String.valueOf(date_tu.getMonth() + 1), String.valueOf(Date_den.getMonth() + 1))));
        lbl_hd.setText(String.valueOf(repository.gethdkhoang1(String.valueOf(date_tu.getMonth() + 1), String.valueOf(Date_den.getMonth() + 1))));
        lbl_kh.setText(String.valueOf(repository.getkhkhoang1(String.valueOf(date_tu.getMonth() + 1), String.valueOf(Date_den.getMonth() + 1))));
        lbl_sanpham.setText(String.valueOf(repository.getbykhoang1(String.valueOf(date_tu.getMonth() + 1), String.valueOf(Date_den.getMonth() + 1))));
        defaultTableModel1.setRowCount(0);
        int stt = 1;
        for (Thongke x : repository.getspkhoang(String.valueOf(date_tu.getMonth() + 1), String.valueOf(Date_den.getMonth() + 1))) {
            defaultTableModel1.addRow(new Object[]{
                stt,
                x.getChiTietSP(),
                x.getSoSP()
            });
            stt++;
        }
    }//GEN-LAST:event_rd_khoangActionPerformed

}


