package com.example.start02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class interfaceEtuController extends Window implements Initializable {

    @FXML
    private ToggleButton btnPage1;

    @FXML
    private ToggleButton btnPage2;

    @FXML
    private ToggleButton btnPage3;

    @FXML
    private ToggleButton btnPage4;

    @FXML
    private ToggleGroup goTo;

    @FXML
    private AnchorPane page1;

    @FXML
    private AnchorPane page2;

    @FXML
    private AnchorPane page3;

    @FXML
    private AnchorPane page4;

    @FXML
    private Circle circlePhoto;

    @FXML
    private Label labelNameUser;

    @FXML
    private TextField outputFiliere;
    @FXML
    private TextField outputGmail;

    @FXML
    private DatePicker outputNaissence;

    @FXML
    private TextField outputNom;

    @FXML
    private TextField outputPassword;

    @FXML
    private TextField outputPrenom;

    @FXML
    private TextField outputTel;

    @FXML
    private TextField outputUserName;

    @FXML
    private TextField outputAppoge;

    @FXML
    private TableView<?> tableNote;

    @FXML
    private ComboBox<String> comboBoxSemestre;
    @FXML
    private TextField outputAppogeNote;


    @FXML
    void changeMenu(ActionEvent event) {


        if (event.getSource() == btnPage1)
        {
            page1.setVisible(true);
            getInfo();
            page2.setVisible(false);
            page3.setVisible(false);
            page4.setVisible(false);

        } else
        if (btnPage2.isSelected()) {
            page1.setVisible(false);
            page2.setVisible(true);
            page3.setVisible(false);
            page4.setVisible(false);

        } else
        if (btnPage3.isSelected()) {
            page1.setVisible(false);
            page2.setVisible(false);
            page3.setVisible(true);
            page4.setVisible(false);
        }
        else {
            page1.setVisible(false);
            page2.setVisible(false);
            page3.setVisible(false);
            page4.setVisible(true);
        }

    }

    @FXML
    void changePhoto(MouseEvent event)  {

        circlePhoto.setStroke(Color.SEAGREEN);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            Image image = new Image("file:" + selectedFile.getAbsolutePath());
            circlePhoto.setFill(new ImagePattern(image));

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      getInfo();
       // prepateComboBox() ;
    }



    private void getInfo()
    {
        String userName = LoginController.etudiant.getUserName() ;
        labelNameUser.setText(userName);

     //   Image photoProfile = LoginController.etudiant.getImg() ;
      //  circlePhoto.setFill(new ImagePattern(photoProfile));

     /*  outputNom.setText(LoginController.etudiant.getNom());
        outputPrenom.setText(LoginController.etudiant.getPrenom());
        outputUserName.setText(LoginController.etudiant.getUserName());
        outputGmail.setText(LoginController.etudiant.getGmail());
        outputTel.setText(LoginController.etudiant.getTel());
        outputFiliere.setText(LoginController.etudiant.getFiliere());
        outputAppoge.setText(LoginController.etudiant.getAppoge());

        // Date ;
        Date dN = LoginController.etudiant.getDateNaissence();
        LocalDate ld = LocalDate.of(((java.sql.Date) dN).toLocalDate().getYear(),((java.sql.Date) dN).toLocalDate().getMonthValue(),((java.sql.Date) dN).toLocalDate().getDayOfMonth()) ;
        outputNaissence.setValue(ld);

        //Password   ; MN bead fkr fhadi mzn
     String password = LoginController.etudiant.getPassword();
        final String secretKey = "donotspeakAboutThis";
        String encPassword = MyAESApp.encrypt(password,secretKey) ;
        String dec = MyAESApp.decrypt(encPassword,secretKey) ;


       */
        outputPassword.setText(LoginController.etudiant.getPassword());

        outputNom.setText(LoginController.etudiant.getNom());
        outputPrenom.setText(LoginController.etudiant.getPrenom());
        outputUserName.setText(LoginController.etudiant.getUserName());
        outputGmail.setText(LoginController.etudiant.getGmail());
        outputTel.setText(LoginController.etudiant.getTel());
        outputPassword.setText(LoginController.etudiant.getPassword());
        Date dN = LoginController.etudiant.getDateNaissence();
        LocalDate ld = LocalDate.of(((java.sql.Date) dN).toLocalDate().getYear(),((java.sql.Date) dN).toLocalDate().getMonthValue(),((java.sql.Date) dN).toLocalDate().getDayOfMonth()) ;
        outputNaissence.setValue(ld);











    }
    private  void prepateComboBox()
    {
        comboBoxSemestre.getItems().addAll("S1","S2","S3","S4","S5","S6") ;
        outputAppogeNote.setText(LoginController.etudiant.getAppoge());

    }


    @FXML
    void afficherNote(ActionEvent event) {

        if (comboBoxSemestre.getValue().equals("S1"))
        {
            //AFFIcher note de semsetre S1
        }

    }

    private void prepareNote()
    {


    }




}
