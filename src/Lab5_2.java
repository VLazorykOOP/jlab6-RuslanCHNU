//Лабораторна робота 5
//Завдання 2

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lab5_2 extends JFrame {

    private JTextField textField;
    private JButton button;
    private JTable table;

    private Lab5_2() {
        setTitle("Лаб 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Введіть шлях до файлу:"));
        textField = new JTextField(40);
        topPanel.add(textField);
        button = new JButton("Завантажити дані");
        button.addActionListener(e -> loadData());
        topPanel.add(button);
        panel.add(topPanel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        setVisible(true);
    }

    private void loadData() {
        String filePath = textField.getText();
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Будь ласка, введіть шлях до файлу");
            return;
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Ім'я");
        model.addColumn("Вік");
        model.addColumn("Місто");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    throw new InvalidDataException("Невірний формат даних");
                }
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                String city = parts[2];
                model.addRow(new Object[]{name, age, city});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Помилка при зчитуванні файлу: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Невірний формат віку");
            return;
        } catch (InvalidDataException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        table.setModel(model);
    }

    public static void main(String[] args) {
        new Lab5_2();
    }

    private static class InvalidDataException extends ArithmeticException {
        public InvalidDataException(String message) {
            super(message);
        }
    }
}