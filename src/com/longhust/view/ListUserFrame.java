package com.longhust.view;

import com.longhust.model.User;
import com.longhust.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.awt.EventQueue.invokeLater;

public class ListUserFrame extends JFrame {

    private JTable userTable;
    private JPanel mainPanel;
    private JScrollPane scrollPanel;
    private JButton addUserButton;
    private JButton refreshButton;
    private JButton deleteUserButton;
    private JButton editUserButton;

    UserService userService;
    DefaultTableModel defaultTableModel;

    public ListUserFrame(String title) {
        super(title);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 350));
        this.pack();

        userService = new UserService();
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable.setModel(defaultTableModel);

        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Ten");
        defaultTableModel.addColumn("SDT");
        defaultTableModel.addColumn("Tai khoan");
        defaultTableModel.addColumn("Mat khau");
        defaultTableModel.addColumn("Vai tro");
        defaultTableModel.addColumn("So thich");
        defaultTableModel.addColumn("Gioi thieu");

        setTableData(userService.getAllUsers());

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshButtonActionPerformed(e);
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserButtonActionPerformed(e);
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUserButtonActionPerformed(e);
            }
        });
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUserButtonActionPerformed(e);
            }
        });
    }

    private void setTableData(List<User> users) {
        for(User user : users) {
            defaultTableModel.addRow(new Object[]{user.getId(), user.getName(), user.getPhone(), user.getUsername(), user.getPassword(), user.getRole(), user.getFavourites(), user.getAbout()});
        }
    }

    private void refreshButtonActionPerformed(ActionEvent e) {
        defaultTableModel.setRowCount(0);
        setTableData(userService.getAllUsers());
    }

    private void addUserButtonActionPerformed(ActionEvent e) {
        new AddUserFrame("Add New User").setVisible(true);
        this.dispose();
    }

    private void deleteUserButtonActionPerformed(ActionEvent e) {
        int row = userTable.getSelectedRow();

        if(row == -1) {
            JOptionPane.showMessageDialog(ListUserFrame.this, "Please select user first!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(ListUserFrame.this, "Are you sure to delete?");
            if(confirm == JOptionPane.YES_OPTION) {
                int userId = (int) userTable.getValueAt(row, 0);

                userService.deleteUser(userId);
                defaultTableModel.setRowCount(0);
                setTableData(userService.getAllUsers());
            }
        }
    }

    private void editUserButtonActionPerformed(ActionEvent e) {
        int row = userTable.getSelectedRow();

        if(row == -1) {
            JOptionPane.showMessageDialog(ListUserFrame.this, "Please select user first!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int userId = (int) userTable.getValueAt(row, 0);

            new EditUserFrame("Edit User", userId).setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        /* Create and display the form */
        invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListUserFrame("App").setVisible(true);
            }
        });
    }

}
