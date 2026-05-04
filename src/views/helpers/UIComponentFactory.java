package views.helpers;

import swing.MyButton;
import swing.MyTextField;
import swing.PanelGradiente;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

/**
 * Helper class for creating UI components in views
 * Provides utility methods for creating styled components
 *
 * @author UTC_JAVA
 */
public class UIComponentFactory {

    /**
     * Create a styled label
     */
    public static JLabel createLabel(String text, int x, int y, int width, int height, Font font) {
        JLabel label = new JLabel(text);
        if (font != null) {
            label.setFont(font);
        }
        label.setBounds(x, y, width, height);
        return label;
    }

    /**
     * Create a styled text field
     */
    public static MyTextField createMyTextField(int x, int y, int width, int height) {
        MyTextField field = new MyTextField();
        field.setBackground(new java.awt.Color(227, 255, 255));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBounds(x, y, width, height);
        return field;
    }

    /**
     * Create a styled button
     */
    public static MyButton createMyButton(String text, int x, int y, int width, int height) {
        MyButton button = new MyButton();
        button.setText(text);
        button.setBackground(new java.awt.Color(125, 224, 237));
        button.setForeground(new java.awt.Color(0, 51, 102));
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBounds(x, y, width, height);
        return button;
    }

    /**
     * Create a JTable with scrollpane
     */
    public static JScrollPane createTableScrollPane(JTable table, int x, int y, int width, int height) {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(x, y, width, height);
        return scrollPane;
    }

    /**
     * Create a styled combo box
     */
    public static JComboBox<?> createComboBox(int x, int y, int width, int height) {
        JComboBox<?> comboBox = new JComboBox<>();
        comboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        comboBox.setBounds(x, y, width, height);
        return comboBox;
    }

    /**
     * Create a checkbox
     */
    public static JCheckBox createCheckBox(String text, int x, int y) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBackground(new java.awt.Color(230, 230, 250));
        checkBox.setBounds(x, y, 100, 25);
        return checkBox;
    }

    /**
     * Create a radio button
     */
    public static JRadioButton createRadioButton(String text, int x, int y) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBackground(new java.awt.Color(230, 230, 250));
        radioButton.setBounds(x, y, 100, 25);
        return radioButton;
    }

    /**
     * Add row to table model
     */
    public static void addRowToTable(DefaultTableModel model, Object[] data) {
        model.addRow(data);
    }

    /**
     * Clear table data
     */
    public static void clearTable(DefaultTableModel model) {
        model.setRowCount(0);
    }
}
