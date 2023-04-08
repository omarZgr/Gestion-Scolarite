package com.example.start02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class interfaceEnsController extends Window implements Initializable {

    @FXML
    private AnchorPane addEtudiants;

    @FXML
    private AnchorPane addEtudiantsMethodeDirect;

    @FXML
    private AnchorPane addEtudiantsMethodeExcel;

    @FXML
    private AnchorPane addEnsMethodeDirect;

    @FXML
    private AnchorPane addEnsMethodeExcel;

    @FXML
    private AnchorPane addEtudiantsMethodeExecl;

    @FXML
    private AnchorPane addEtudiantsMethodeExecl1;

    @FXML
    private AnchorPane addEtudiantsMethodeExecl11;

    @FXML
    private AnchorPane addEtudiantsMethodeExecl111;

    @FXML
    private AnchorPane addProfs;

    @FXML
    private ToggleButton btnPage1;

    @FXML
    private ToggleButton btnPage2;

    @FXML
    private ToggleButton btnPage3;

    @FXML
    private ToggleButton btnPage4;

    @FXML
    private Circle circlePhoto;

    @FXML
    private ComboBox<?> comboBoxSemestre;

    @FXML
    private ToggleGroup goTo;

    @FXML
    private Label labelNameUser;

    @FXML
    private AnchorPane listeEtudiants;

    @FXML
    private AnchorPane listeFilire;

    @FXML
    private AnchorPane listeModule;

    @FXML
    private AnchorPane listeProfs;

    @FXML
    private TextField outputAppogeNote;

    @FXML
    private TextField outputGmail;

    @FXML
    private TextField outputMatricule;

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
    private AnchorPane page1;

    @FXML
    private AnchorPane page2;

    @FXML
    private AnchorPane page3;

    @FXML
    private AnchorPane page4;

    @FXML
    private AnchorPane pageAdmin;



    @FXML
    private TableView<?> tableNote;

    @FXML
    private RadioButton radioDirectEtu;

    @FXML
    private RadioButton radioDirectProf;

    @FXML
    private RadioButton radioExcelEtu;

    @FXML
    private RadioButton radioExcelProf;

    @FXML
    void choisirMethodeDajouteEtudiant(ActionEvent event) {


        perpareAjouteEtud() ;

    }

    @FXML
    void choisirMethodeDajouteProf(ActionEvent event) {

        perpareAjouteEns() ;

    }

    void perpareAjouteEtud()
    {
        if (radioDirectEtu.isSelected())
        {
            addEtudiantsMethodeDirect.setVisible(true);
            addEtudiantsMethodeExcel.setVisible(false);
        }
        else
        {
            addEtudiantsMethodeDirect.setVisible(false);
            addEtudiantsMethodeExcel.setVisible(true);

        }
    }

    void perpareAjouteEns()
    {
        if (radioDirectProf.isSelected())
        {
            addEnsMethodeDirect.setVisible(true);
            addEnsMethodeExcel.setVisible(false);
        }
        else
        {
            addEnsMethodeDirect.setVisible(false);
            addEnsMethodeExcel.setVisible(true);

        }
    }




    @FXML
    void changeMenu(ActionEvent event) throws IOException {


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
        else
        {
            addElement() ;
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



    @FXML
    void goToAddEtudiants(MouseEvent event) {
        pageAdmin.setVisible(false);
        addEtudiants.setVisible(true);

    }

    @FXML
    void goToAddProfs(MouseEvent event) {

        pageAdmin.setVisible(false);
        addProfs.setVisible(true);

    }

    @FXML
    void goToFiliere(MouseEvent event) {

        pageAdmin.setVisible(false);
        listeFilire.setVisible(true);

    }

    @FXML
    void goToModule(MouseEvent event) {
        pageAdmin.setVisible(false);
        listeModule.setVisible(true);

    }

    @FXML
    void goTolListeEtudiants(MouseEvent event) {
        pageAdmin.setVisible(false);
        listeEtudiants.setVisible(true);

    }

    @FXML
    void goTolListeProfs(MouseEvent event) {
        pageAdmin.setVisible(false);
        listeProfs.setVisible(true);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

         getInfo();
        perpareAjouteEtud() ;
        perpareAjouteEns() ;
    }



    private void getInfo()
    {
        String userName = LoginController.ensigement.getUserName() ;
        labelNameUser.setText(userName);

        outputPassword.setText(LoginController.ensigement.getPassword());

        outputNom.setText(LoginController.ensigement.getNom());
        outputPrenom.setText(LoginController.ensigement.getPrenom());
        outputUserName.setText(LoginController.ensigement.getUserName());
        outputGmail.setText(LoginController.ensigement.getGmail());
        outputTel.setText(LoginController.ensigement.getTel());
        outputPassword.setText(LoginController.ensigement.getPassword());
        Date dN = LoginController.ensigement.getDateNaissence();
        LocalDate ld = LocalDate.of(((java.sql.Date) dN).toLocalDate().getYear(),((java.sql.Date) dN).toLocalDate().getMonthValue(),((java.sql.Date) dN).toLocalDate().getDayOfMonth()) ;
        outputNaissence.setValue(ld);

    }


    void addElement() throws IOException {
        Stage stage = new Stage() ;
        Parent root = FXMLLoader.load(getClass().getResource("LoginAdminDansInterfaceEns.fxml")) ;
        stage.setTitle("Admin");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        checkAdmin() ;
    }

    void checkAdmin() {
        String userName = "";
        String password = "";

        userName=LoginAdminDansInterfaceEnsController.userName ;
        password = LoginAdminDansInterfaceEnsController.password ;

        if (!userName.isEmpty() || !password.isEmpty())
        {
            if (userName.equals("fatnaZoot") && password.equals("1234"))
            {
                page1.setVisible(false);
                page2.setVisible(false);
                page3.setVisible(false);
                page4.setVisible(true);
            }
            else
            {
                Alert err = new Alert(Alert.AlertType.WARNING);
                err.setTitle("Warrning");
                err.setContentText("Vous n'avez pas le droit de acces");
                err.showAndWait();
            }
        }

    }

    @FXML
    void retour(MouseEvent event) {
        retourTopageAdmin() ;

    }


    void retourTopageAdmin()
    {
        pageAdmin.setVisible(true);
        listeEtudiants.setVisible(false);
        listeProfs.setVisible(false);
        listeModule.setVisible(false);
        listeFilire.setVisible(false);
        addEtudiants.setVisible(false);
        addProfs.setVisible(false);
    }







}
