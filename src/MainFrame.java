
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame {

    protected static void render() {
        // remove already existing frames cuz else new frame will popup every time you select this option
        for (Frame something : JFrame.getFrames()) {
            something.dispose();
        }
        MainFrame frame = new MainFrame();
        frame.show();
    }

    MainFrame() {

        // centers the window (not useful for tiling window managers) [I use dwm btw]
        this.setLocationRelativeTo(null);

        this.setTitle("Menu");
        this.setResizable(false);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout card_layout = new CardLayout();

        JPanel wrapper_panel = new JPanel(card_layout);
        wrapper_panel.add("1", new MenuPanel(card_layout));
        wrapper_panel.add("2", new PendingOrdersPanel(card_layout));
        this.add(wrapper_panel);

        this.setVisible(true);

    }

}

class MenuPanel extends JPanel implements ActionListener {

    private CardLayout parent_layout;

    Font title_font = new Font("Agave Nerd Font Mono", Font.BOLD, 40);
    Font main_font = new Font("Agave Nerd Font Mono", Font.PLAIN, 16);
    Font button_font = new Font("Agave Nerd Font Mono", Font.PLAIN, 20);

    MenuPanel(CardLayout parent_layout) {
        this.parent_layout = parent_layout;

        this.setLayout(new BorderLayout());

        JLabel top_label = new JLabel("MENU");
        top_label.setFont(title_font);
        top_label.setHorizontalAlignment(JLabel.CENTER);
        top_label.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] table_header = { "Index", "Item Name", "Price", "Category", "Availability" };
        DefaultTableModel model = new DefaultTableModel(table_header, 0);

        for (Food _food : Menu.get_list()) {

            Object[] row = { 
                _food.get_index(),
                _food.get_title(),
                _food.get_price(),
                _food.get_category(),
                _food.get_availability() ? "✓" : "✗"
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setRowHeight(40);
        table.setFont(main_font);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // not editable
        table.setDefaultEditor(Object.class, null);
        table.setDefaultRenderer(Object.class, new PaddedCellRenderer());

        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(320);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(160);
        table.getColumnModel().getColumn(4).setPreferredWidth(142);

        JScrollPane scroll_pane = new JScrollPane(table);

        JButton pending_orders_button = new JButton("Show Pending Orders 󰁔");
        pending_orders_button.setFont(button_font);
        pending_orders_button.setBorder(new EmptyBorder(10, 10, 10, 10));
        pending_orders_button.addActionListener(this);

        this.add(top_label, BorderLayout.PAGE_START);
        this.add(scroll_pane, BorderLayout.CENTER);
        this.add(pending_orders_button, BorderLayout.PAGE_END);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        parent_layout.show(this.getParent(), "2");
    }

}

class PendingOrdersPanel extends JPanel implements ActionListener {

    private CardLayout parent_layout;

    Font title_font = new Font("Agave Nerd Font Mono", Font.BOLD, 40);
    Font main_font = new Font("Agave Nerd Font Mono", Font.PLAIN, 20);
    Font button_font = new Font("Agave Nerd Font Mono", Font.PLAIN, 20);

    PendingOrdersPanel(CardLayout parent_layout) {
        this.parent_layout = parent_layout;

        this.setLayout(new BorderLayout());

        JLabel top_label = new JLabel("PENDING ORDERS");
        top_label.setFont(title_font);
        top_label.setHorizontalAlignment(JLabel.CENTER);
        top_label.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] table_header = { "Order ID", "Quantity", "Total Amount", "Status" };
        DefaultTableModel model = new DefaultTableModel(table_header, 0);

        for (Order _order : Order.get_list()) {

            Object[] row = {
                    _order.get_id(),
                    _order.get_qty(),
                    _order.get_amt(),
                    _order.get_status()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        table.setRowHeight(50);
        table.setFont(main_font);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // not editable
        table.setDefaultEditor(Object.class, null);

        JScrollPane scroll_pane = new JScrollPane(table);

        JButton pending_orders_button = new JButton("Show Menu 󰁔");
        pending_orders_button.setFont(button_font);
        pending_orders_button.setBorder(new EmptyBorder(10, 10, 10, 10));
        pending_orders_button.addActionListener(this);

        this.add(top_label, BorderLayout.PAGE_START);
        this.add(scroll_pane, BorderLayout.CENTER);
        this.add(pending_orders_button, BorderLayout.PAGE_END);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        parent_layout.show(this.getParent(), "1");
    }

}

class PaddedCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
            int arg5) {
        JLabel comp = (JLabel)super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
        comp.setBorder(new EmptyBorder(10, 10, 10, 10));
        comp.setHorizontalAlignment(CENTER);
        return comp;
    }
}
