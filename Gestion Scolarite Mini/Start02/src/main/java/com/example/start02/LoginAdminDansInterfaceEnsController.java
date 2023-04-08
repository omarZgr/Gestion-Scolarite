package com.example.start02;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginAdminDansInterfaceEnsController {

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUser;

    static String  userName,password ;
    @FXML
    void submit(MouseEvent event) {

        if ( !inputUser.getText().isEmpty() && !inputPassword.getText().isEmpty())
        {
            userName= inputUser.getText() ;
            password= inputPassword.getText() ;
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    private TableView<Person> table;

    @FXML
    private TableColumn<Person, String> nom;

    @FXML
    private TableColumn<Person, String> prenom;

    private ArrayList<Person> data = new ArrayList<>() ;

    ObservableList<Person> list = FXCollections.observableArrayList(data) ;






}
