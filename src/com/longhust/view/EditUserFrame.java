package com.longhust.view;

import com.longhust.model.User;
import com.longhust.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserFrame extends JFrame {

    private JPanel editUserPanel;
    private JButton backButton;
    private JTextField nameTextField;
    private JTextField phoneTextField;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JCheckBox musicCheckBox;
    private JCheckBox movieCheckBox;
    private JTextArea aboutTextArea;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JTextField idTextField;
    private JButton saveButton;

    private User user;
    private UserService userService;

    public EditUserFrame(String title, int userId) {
        super(title);

        this.setContentPane(editUserPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.pack();

        userService = new UserService();

        user = userService.getUserById(userId);

        idTextField.setText(String.valueOf(user.getId()));
        nameTextField.setText(user.getName());
        phoneTextField.setText(user.getPhone());
        usernameTextField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
        aboutTextArea.setText(user.getAbout());
        passwordField.setText(user.getPassword());

        String favourites = user.getFavourites();
        if(favourites != null) {
            String[] favs = favourites.split(" ");
            for(String f : favs) {
                if(f.equals("Music")) {
                    musicCheckBox.setSelected(true);
                }
                if(f.equals("Movie")) {
                    movieCheckBox.setSelected(true);
                }
            }
        }

        String role = user.getRole();
        if(role != null) {
            if(role.equals("ROLE_ADMIN")) {
                adminRadioButton.setSelected(true);
            }
            if(role.equals("ROLE_USER")) {
                userRadioButton.setSelected(true);
            }
        }

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(e);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonActionPerformed(e);
            }
        });
    }

    private void backButtonActionPerformed(ActionEvent e) {
        new ListUserFrame("App").setVisible(true);
        this.dispose();
    }

    private void saveButtonActionPerformed(ActionEvent e) {
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

        userService.updateUser(user);

        new ListUserFrame("App").setVisible(true);
        this.dispose();
    }
}
