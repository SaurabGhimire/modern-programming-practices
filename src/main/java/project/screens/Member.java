package project.screens;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Member implements Component{
    private JButton button1;

    private static Member instance;
    private JPanel contentPane;
    private JTable dataTable;

    private Member() {
    }
    public static Member getInstance() {
        if(instance == null){
            instance = new Member();
        }
        return instance;
    }

    @Override
    public JPanel getMainPanel() {
        return contentPane;
    }

    void paintTableData(){
        String[] columnNames = { "ID", "Name", "Address" };
        String[][] data = {
                { "20","Kundan Kumar Jha", "Fairfield 4031" },
                { "30","Anand Jha", "Ottomwa 6014", }
        };

        dataTable = new JTable(data, columnNames);
        dataTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

        //dataTable.setBounds(30, 40, 200, 300);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        paintTableData();
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.putIfAbsent("Table.alternateRowColor", Color.LIGHT_GRAY);
    }
}