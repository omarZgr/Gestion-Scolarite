package com.example.start02;

import com.example.start02.Acteur.Ensigement;
import com.example.start02.Acteur.Etudiant;
import com.example.start02.CryptoInfo.MyAESApp;
import com.example.start02.Gere_BD.Gere_BD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.sql.* ;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label wrongMsgEns;

    @FXML
    private Label wrongMsgEtu;
    @FXML
    private CheckBox checkAdminPourEns;

    @FXML
    private TextField inputAppogeEtu;

    @FXML
    private TextField inputMatriculeEns;

    @FXML
    private PasswordField inputPasswordEns;

    @FXML
    private PasswordField inputPasswordEtu;

    @FXML
    private TextField inputUserNameEns;

    @FXML
    private TextField inputUserNameEtu;

    @FXML
    private AnchorPane loginEnseignement;

    @FXML
    private AnchorPane loginEtudiant;

    @FXML
    void goToEleve(MouseEvent event) {

        loginEnseignement.setVisible(false);
        loginEtudiant.setVisible(true);

    }

    @FXML
    void goToEnseignement(MouseEvent event) {

        loginEnseignement.setVisible(true);
        loginEtudiant.setVisible(false);


    }

    @FXML
    void resetEns(MouseEvent event) {

        inputMatriculeEns.setText("");
        inputUserNameEns.setText("");
        inputPasswordEns.setText("");
        wrongMsgEns.setVisible(false);


    }

    @FXML
    void resetEtu(MouseEvent event) {

        inputAppogeEtu.setText("");
        inputUserNameEtu.setText("");
        inputPasswordEtu.setText("");
        wrongMsgEtu.setVisible(false);


    }

    @FXML
    void submitEns(MouseEvent event) throws IOException {

        checkExistEns() ;

    }

    @FXML
    void submitEtu(MouseEvent event) throws IOException {

        checkExistEtu();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pripareBD() ;

    }

    private void pripareBD()
    {
        if (Gere_BD.getConnection() == null)
        {
            Alert err = new Alert(Alert.AlertType.ERROR) ;
            err.setTitle("ERROR");
            err.setContentText("Connexion echoue avec BD");
            err.showAndWait() ;
            System.exit(1);
        }
    }


    static Etudiant etudiant = new Etudiant();
    static Ensigement ensigement = new Ensigement();
    private void checkExistEtu() throws IOException {
        String appoge = inputAppogeEtu.getText() ;
        String userName = inputUserNameEtu.getText() ;
        String password = inputPasswordEtu.getText() ;

        if (appoge.isEmpty() || userName.isEmpty() || password.isEmpty())
        {
            wrongMsgEtu.setText("Champs Empty");
            wrongMsgEtu.setStyle("-fx-text-fill : red ;");
            wrongMsgEtu.setVisible(true);
        }
        else {

            final String secretKey = "donotspeakAboutThis";
            String hashPassword = MyAESApp.encrypt(password, secretKey);
            String decPassword = MyAESApp.decrypt(hashPassword, secretKey);

            int key = Gere_BD.checkEtudiant(appoge,userName,decPassword) ;
            System.out.println("KEY ------------ "+key);

            if ( key != -1) //Khasni ndir hash ms mhm tl mn bead !!!!!
            {
                wrongMsgEtu.setText("Biennevue");
                wrongMsgEtu.setStyle("-fx-text-fill : green ;");
                wrongMsgEtu.setVisible(true);

                //Prepare Persone ;
                preparePersoneEtu(key) ;

               Stage stage = (Stage) wrongMsgEtu.getScene().getWindow();
               Parent root = FXMLLoader.load(getClass().getResource("interfaceEtu.fxml")) ;
                stage.setTitle("ESPACE Etudiants");
                stage.setScene(new Scene(root));
                stage.show();



            }
            else
            {
                wrongMsgEtu.setText("information Incorrect");
                wrongMsgEtu.setStyle("-fx-text-fill : red ;");
                wrongMsgEtu.setVisible(true);
            }

        }
    }

    private void checkExistEns() throws IOException {
        String matricule = inputMatriculeEns.getText() ;
        String userName = inputUserNameEns.getText() ;
        String password = inputPasswordEns.getText() ;

        if (matricule.isEmpty() || userName.isEmpty() || password.isEmpty())
        {
            wrongMsgEns.setText("Champs Empty");
            wrongMsgEns.setStyle("-fx-text-fill : red ;");
            wrongMsgEns.setVisible(true);
        }
        else {

            final String secretKey = "donotspeakAboutThis";
            String hashPassword = MyAESApp.encrypt(password, secretKey);
            String decPassword = MyAESApp.decrypt(hashPassword, secretKey);


            {

                int key = Gere_BD.checkEnseignement(matricule, userName, decPassword) ;//Khasni ndir hash ms mhm tl mn bead !!!!!
                if ( key !=-1)
                {
                    wrongMsgEns.setText("Biennevue Professeur " +key);
                    wrongMsgEns.setStyle("-fx-text-fill : green ;");
                    wrongMsgEns.setVisible(true);

                    preparePersoneEns(key) ;

                    Stage stage = (Stage) wrongMsgEtu.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("interfaceEns.fxml")) ;
                    stage.setTitle("ESPACE Enseigment");
                    stage.setScene(new Scene(root));
                    stage.show();

                } else {
                    wrongMsgEns.setText("information Incorrect");
                    wrongMsgEns.setStyle("-fx-text-fill : red ;");
                    wrongMsgEns.setVisible(true);
                }

            }



        }

    }

    void preparePersoneEtu(int key) {


        Connection etat = Gere_BD.getConnection();

        try {
            if (!etat.isClosed()) {
                etudiant.setIdPersone(key);
                PreparedStatement ps;
                ResultSet rs;
              //  String query = "SELECT * FROM persons JOIN etudiant on persons.idPersone = etudiant.idEtu JOIN filiere on filiere.idFiliere=etudiant.idEtu WHERE persons.idPersone=? ;";
                String query = "Select * from persons  WHERE persons.idPersone=?" ;
                ps = etat.prepareStatement(query);
                ps.setString(1, String.valueOf(key));
                rs = ps.executeQuery();
                if (rs.next()) {
                  /*  etudiant.setIdPersone(key);
                    etudiant.setNom(rs.getString("nom"));
                    etudiant.setPrenom(rs.getString("prenom"));
                    etudiant.setGmail(rs.getString("gmail"));
                    etudiant.setTel(rs.getString("tel"));
                    etudiant.setDateNaissence(rs.getDate("DateNaissence"));
                    etudiant.setUserName(rs.getString("userName"));
                    etudiant.setPassword(rs.getString("passwordd")); // decrypte
                    byte[] imgData = rs.getBytes("imag");
                    Image img = new Image(new ByteArrayInputStream(imgData));
                    etudiant.setImg(img);
                    etudiant.setAppoge(rs.getString("appoge"));
                    etudiant.setFiliere(rs.getString("nomFiliere"));

                   */

                    //TEST

                    etudiant.setIdPersone(key);
                    etudiant.setNom(rs.getString("nom"));
                    etudiant.setPrenom(rs.getString("prenom"));
                    etudiant.setGmail(rs.getString("gmail"));
                    etudiant.setTel(rs.getString("tel"));
                    etudiant.setDateNaissence(rs.getDate("DateNaissence"));
                    etudiant.setUserName(rs.getString("userName"));
                    etudiant.setPassword(rs.getString("passwordd")); // decrypte
                    byte[] imgData = rs.getBytes("imag");
                    Image img = new Image(new ByteArrayInputStream(imgData));
                    etudiant.setImg(img);

                    System.out.println(etudiant.getKey());
                    System.out.println(etudiant.getNom());
                    System.out.println(etudiant.getPrenom());
                    System.out.println(etudiant.getGmail());
                    System.out.println(etudiant.getTel());
                    System.out.println(etudiant.getDateNaissence());
                    System.out.println(etudiant.getUserName());
                    System.out.println(etudiant.getPassword());
                    System.out.println(etudiant.getImg());


                } else {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("ERROR");
                    err.setContentText("mushkil f get indo dans methode *Prepare Perssone*");
                    err.showAndWait();
                    System.exit(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void preparePersoneEns(int key) {


        Connection etat = Gere_BD.getConnection();


        try {
            if (!etat.isClosed()) {
                ensigement.setIdPersone(key);
                PreparedStatement ps;
                ResultSet rs;
                String query = "Select * from persons  WHERE persons.idPersone=?";
                ps = etat.prepareStatement(query);
                ps.setString(1, String.valueOf(key));
                rs = ps.executeQuery();
                if (rs.next()) {


                    ensigement.setIdPersone(key);
                    ensigement.setNom(rs.getString("nom"));
                    ensigement.setPrenom(rs.getString("prenom"));
                    ensigement.setGmail(rs.getString("gmail"));
                    ensigement.setTel(rs.getString("tel"));
                    ensigement.setDateNaissence(rs.getDate("DateNaissence"));
                    ensigement.setUserName(rs.getString("userName"));
                    ensigement.setPassword(rs.getString("passwordd")); // decrypte
                    byte[] imgData = rs.getBytes("imag");
                    Image img = new Image(new ByteArrayInputStream(imgData));
                    ensigement.setImg(img);

                } else {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setTitle("ERROR");
                    err.setContentText("mushkil f get indo dans methode *Prepare Perssone*");
                    err.showAndWait();
                    //System.exit(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}