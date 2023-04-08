package com.example.start;

import com.example.start.Acteur.Etudiant;
import com.example.start.CryptoInfo.MyAESApp;
import com.example.start.MangmentBaseDonne.Gere_BD;
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
    void submitEns(MouseEvent event) {

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

           if ( key != -1) //Khasni ndir hash ms mhm tl mn bead !!!!!
           {
               wrongMsgEtu.setText("Biennevue");
               wrongMsgEtu.setStyle("-fx-text-fill : green ;");
               wrongMsgEtu.setVisible(true);

               //Prepare Persone ;
               preparePersone(key) ;

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

    private void checkExistEns()
    {
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

            if (! checkAdminPourEns.isSelected())
            {

                int key = Gere_BD.checkEnseignement(matricule, userName, decPassword) ;//Khasni ndir hash ms mhm tl mn bead !!!!!
                if ( key !=-1)
                {
                    wrongMsgEns.setText("Biennevue Professeur " +key);
                    wrongMsgEns.setStyle("-fx-text-fill : green ;");
                    wrongMsgEns.setVisible(true);
                } else {
                    wrongMsgEns.setText("information Incorrect");
                    wrongMsgEns.setStyle("-fx-text-fill : red ;");
                    wrongMsgEns.setVisible(true);
                }

            }
            else
            {
                int key = Gere_BD.checkDiricteur(matricule, userName, decPassword) ;
                if (key != -1) //Khasni ndir hash ms mhm tl mn bead !!!!!
                {
                    wrongMsgEns.setText("Biennevue Directeur "+key);
                    wrongMsgEns.setStyle("-fx-text-fill : green ;");
                    wrongMsgEns.setVisible(true);
                } else {
                    wrongMsgEns.setText("information Incorrect");
                    wrongMsgEns.setStyle("-fx-text-fill : red ;");
                    wrongMsgEns.setVisible(true);
                }

            }
        }

    }

   void preparePersone(int key)
   {


           Connection etat = Gere_BD.getConnection() ;

           try {
               if(  !etat.isClosed())
               {
                   PreparedStatement ps ;
                   ResultSet rs ;
                   String query = "SELECT * FROM persons JOIN etudiant on persons.idPersone = etudiant.idEtudiant JOIN filiere on filiere.idFiliere=etudiant.idFiliere WHERE persons.idPersone=? ;" ;
                   ps = etat.prepareStatement(query) ;
                   ps.setString(1, String.valueOf(key));
                   rs = ps.executeQuery() ;
                   if (rs.next())
                   {
                       etudiant.setIdPersone(key);
                       etudiant.setNom(rs.getString("nom"));
                       etudiant.setPrenom(rs.getString("prenom"));
                       etudiant.setGmail(rs.getString("gmail"));
                       etudiant.setTel(rs.getString("tel"));
                       etudiant.setDateNaissence(rs.getDate("dateNaissance"));
                       etudiant.setUserName(rs.getString("usrName"));
                       etudiant.setPassword(rs.getString("passwod")); // decrypte
                       byte[] imgData = rs.getBytes("image");
                       Image img = new Image(new ByteArrayInputStream(imgData)) ;
                       etudiant.setImg(img);
                       etudiant.setAppoge(rs.getString("appoge"));
                       etudiant.setFiliere(rs.getString("nomFiliere"));

                       //TEST




                   }
                   else
                   {
                       Alert err = new Alert(Alert.AlertType.ERROR) ;
                       err.setTitle("ERROR");
                       err.setContentText("mushkil f get indo dans methode *Prepare Perssone*");
                       err.showAndWait() ;
                       System.exit(1);
                   }
               }

           } catch (SQLException e) {
               throw new RuntimeException(e);
           }



       }
}
