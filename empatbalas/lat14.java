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

    // Mode update
    private static boolean isUpdateMode = false;
    private static int rowBeingEdited = -1;

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
        JButton cancelButton = new JButton("Batal");
        cancelButton.setVisible(false); // Awalnya disembunyikan

        // Event Listener untuk tombol Tambah / Simpan Perubahan
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                double price = Double.parseDouble(priceField.getText());

                if (isUpdateMode) {
                    // Simpan perubahan
                    Product product = products.get(rowBeingEdited);
                    product.setName(name);
                    product.setPrice(price);

                    tableModel.setValueAt(name, rowBeingEdited, 0);
                    tableModel.setValueAt(price, rowBeingEdited, 1);

                    isUpdateMode = false;
                    rowBeingEdited = -1;
                    addButton.setText("Tambah");
                    cancelButton.setVisible(false);
                } else {
                    // Tambah produk baru
                    Product product = new Product(name, price);
                    products.add(product);
                    tableModel.addRow(new Object[]{name, price, "Update", "Delete"});
                }

                nameField.setText("");
                priceField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan harga yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Event Listener untuk tombol Batal
        cancelButton.addActionListener(e -> {
            isUpdateMode = false;
            rowBeingEdited = -1;
            nameField.setText("");
            priceField.setText("");
            addButton.setText("Tambah");
            cancelButton.setVisible(false);
        });

        // Panel untuk input dan tombol
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nama:"));
        panel.add(nameField);
        panel.add(new JLabel("Harga:"));
        panel.add(priceField);
        panel.add(addButton);
        panel.add(cancelButton); // Tambahkan tombol batal

        // Menambahkan tabel ke frame
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        // Menambahkan kolom tombol Update
        TableColumn updateColumn = table.getColumnModel().getColumn(2);
        updateColumn.setCellRenderer(new ButtonRenderer("Update"));
        updateColumn.setCellEditor(new ButtonEditor(new JCheckBox(), table, "Update", nameField, priceField, addButton, cancelButton));

        // Menambahkan kolom tombol Delete
        TableColumn deleteColumn = table.getColumnModel().getColumn(3);
        deleteColumn.setCellRenderer(new ButtonRenderer("Delete"));
        deleteColumn.setCellEditor(new ButtonEditor(new JCheckBox(), table, "Delete", nameField, priceField, addButton, cancelButton));

        frame.setVisible(true);
    }

    // Renderer tombol
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        private String action;

        public ButtonRenderer(String action) {
            this.action = action;
            setText(action);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor untuk tombol Update dan Delete
    static class ButtonEditor extends DefaultCellEditor {
        private String action;
        private int selectedRow;
        private JTable table;
        private JTextField nameField;
        private JTextField priceField;
        private JButton addButton;
        private JButton cancelButton;

        public ButtonEditor(JCheckBox checkBox, JTable table, String action,
                            JTextField nameField, JTextField priceField,
                            JButton addButton, JButton cancelButton) {
            super(checkBox);
            this.table = table;
            this.action = action;
            this.nameField = nameField;
            this.priceField = priceField;
            this.addButton = addButton;
            this.cancelButton = cancelButton;
        }

        @Override
        public Object getCellEditorValue() {
            return action;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.selectedRow = row;
            JButton button = new JButton(action);
            button.addActionListener(e -> {
                if ("Update".equalsIgnoreCase(action)) {
                    Product product = products.get(selectedRow);
                    nameField.setText(product.getName());
                    priceField.setText(String.valueOf(product.getPrice()));
                    isUpdateMode = true;
                    rowBeingEdited = selectedRow;
                    addButton.setText("Simpan Perubahan");
                    cancelButton.setVisible(true);
                } else if ("Delete".equalsIgnoreCase(action)) {
                    products.remove(selectedRow);
                    tableModel.removeRow(selectedRow);

                    // Reset state jika sedang update data yang terhapus
                    if (isUpdateMode && rowBeingEdited == selectedRow) {
                        isUpdateMode = false;
                        rowBeingEdited = -1;
                        nameField.setText("");
                        priceField.setText("");
                        addButton.setText("Tambah");
                        cancelButton.setVisible(false);
                    }
                }
            });
            return button;
        }
    }
}

// Kelas Produk
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
