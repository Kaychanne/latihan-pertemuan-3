package empatbalas;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class lat14 {
    // ArrayList untuk menyimpan daftar produk
    private static ArrayList<Product> products = new ArrayList<>();
    
    // Model tabel untuk menyimpan data
    private static DefaultTableModel tableModel;

    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Daftar Produk");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat tabel dengan empat kolom: Nama Produk, Harga, Update, Delete
        String[] columnNames = {"Nama Produk", "Harga", "Update", "Delete"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Membuat field input untuk menambah produk
        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JButton addButton = new JButton("Tambah");

        // Event Listener untuk tombol Tambah
        addButton.addActionListener(e -> {
            String name = nameField.getText(); // Mengambil input nama produk
            try {
                double price = Double.parseDouble(priceField.getText()); // Konversi harga ke double
                Product product = new Product(name, price); // Membuat objek produk baru
                products.add(product); // Menambah produk ke daftar
                tableModel.addRow(new Object[]{name, price, "Update", "Delete"}); // Menampilkan di tabel
                nameField.setText(""); // Mengosongkan input nama
                priceField.setText(""); // Mengosongkan input harga
            } catch (NumberFormatException ex) {
                // Menampilkan pesan error jika input harga tidak valid
                JOptionPane.showMessageDialog(frame, "Masukkan harga yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Membuat panel untuk menampung input dan tombol
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nama:"));
        panel.add(nameField);
        panel.add(new JLabel("Harga:"));
        panel.add(priceField);
        panel.add(addButton);

        // Menambahkan tabel ke frame dengan scroll
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH); // Menempatkan panel di bagian bawah

        // Menggunakan ButtonColumn untuk menangani aksi Update
        TableColumn updateColumn = table.getColumnModel().getColumn(2);
        updateColumn.setCellRenderer(new ButtonRenderer("Update"));
        updateColumn.setCellEditor(new ButtonEditor(new JCheckBox(), table, "Update"));

        // Menggunakan ButtonColumn untuk menangani aksi Delete
        TableColumn deleteColumn = table.getColumnModel().getColumn(3);
        deleteColumn.setCellRenderer(new ButtonRenderer("Delete"));
        deleteColumn.setCellEditor(new ButtonEditor(new JCheckBox(), table, "Delete"));

        // Menampilkan GUI
        frame.setVisible(true);
    }

    // Renderer untuk menampilkan tombol pada kolom Aksi
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        private String action;

        public ButtonRenderer(String action) {
            this.action = action;
            setText(action);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor untuk menangani aksi tombol Update dan Delete
    static class ButtonEditor extends DefaultCellEditor {
        private String action;
        private int selectedRow;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table, String action) {
            super(checkBox);
            this.table = table;
            this.action = action;
        }

        @Override
        public Object getCellEditorValue() {
            return action;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.selectedRow = row;
            JButton button = new JButton(action);
            button.addActionListener(e -> {
                if ("Update".equalsIgnoreCase(action)) {
                    // Memunculkan pop-up untuk mengubah nama produk dan harga
                    String newName = JOptionPane.showInputDialog("Masukkan nama produk baru:", table.getValueAt(selectedRow, 0));
                    String newPriceStr = JOptionPane.showInputDialog("Masukkan harga produk baru:", table.getValueAt(selectedRow, 1));
                    try {
                        double newPrice = Double.parseDouble(newPriceStr);
                        products.get(selectedRow).setName(newName);
                        products.get(selectedRow).setPrice(newPrice);
                        table.setValueAt(newName, selectedRow, 0);
                        table.setValueAt(newPrice, selectedRow, 1);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Harga tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if ("Delete".equalsIgnoreCase(action)) {
                    // Menghapus baris produk dari daftar dan tabel
                    products.remove(selectedRow);
                    tableModel.removeRow(selectedRow);
                }
            });
            return button;
        }
    }

}

// Kelas untuk merepresentasikan produk
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
