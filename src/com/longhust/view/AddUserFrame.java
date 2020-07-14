package com.longhust.view;

import com.longhust.model.User;
import com.longhust.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserFrame extends JFrame {

    private JPanel addUserPanel;
    private JTextField nameTextField;
    private JTextField phoneTextField;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JTextArea aboutTextArea;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JButton submitButton;
    private JButton backButton;
    private JCheckBox musicCheckBox;
    private JCheckBox movieCheckBox;

    UserService userService;
    User user;

    public AddUserFrame(String title) {
        super(title);

        this.setContentPane(addUserPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.pack();

        userService = new UserService();
        user = new User();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonActionPerformed(e);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonActionPerformed(e);
            }
        });
    }

    private void submitButtonActionPerformed(ActionEvent e) {
        user.setName(nameTextField.getText());
        user.setPhone(phoneTextField.getText());
        user.setUsername(usernameTextField.getText());
        user.setPassword(String.valueOf(passwordField.getPassword()));
        user.setAbout(aboutTextArea.getText());

        String favourites = "";
        if(musicCheckBox.isSelected()) {
            favourites += "Music ";
        }
        if(movieCheckBox.isSelected()) {
            favourites += "Movie";
        }
        user.setFavourites(favourites);

        String role = "ROLE_USER";
        if(adminRadioButton.isSelected()) {
            role = "ROLE_ADMIN";
        }
        if(userRadioButton.isSelected()) {
            role = "ROLE_USER";
        }
        user.setRole(role);

        userService.addUser(user);

        new ListUserFrame("App").setVisible(true);
        this.dispose();
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new ListUserFrame("App").setVisible(true);
        this.dispose();
    }

}
