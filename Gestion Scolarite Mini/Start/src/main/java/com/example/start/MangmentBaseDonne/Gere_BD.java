package com.example.start.MangmentBaseDonne;
import java.sql.* ;

public class Gere_BD {

    public static Connection getConnection()
    {
        Connection con = null ;
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost/applicationversion2","root","") ;
            System.out.println("bien");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return con ;
    }

    public static int checkEtudiant(String appoge,String username,String password)
    {
        Connection etat = Gere_BD.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                String query = "SELECT etudiant.idEtudiant from persons join etudiant on etudiant.idEtudiant = persons.idPersone WHERE etudiant.appoge=?  and  persons.usrName=? and persons.passwod =?" ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,appoge);
                ps.setString(2,username);
                ps.setString(3,password);
                ps.
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return Integer.parseInt(rs.getString(1));
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;


    }

    public static int checkEnseignement(String matricule,String username,String password)
    {
        Connection etat = Gere_BD.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                //mn bead nzid ta matricule
                String query = "SELECT persons.idPersone FROM persons join professeur on professeur.idProf = persons.idPersone WHERE persons.usrName =? and persons.passwod=? " ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,username);
                ps.setString(2,password);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return Integer.parseInt(rs.getString(1));
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;


    }

    public static int checkDiricteur(String matricule,String username,String password)
    {
        Connection etat = Gere_BD.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                //mn bead nzid ta matricule
                String query = "SELECT persons.idPersone FROM persons JOIN directeur on directeur.idDirecteur = persons.idPersone WHERE persons.usrName=? and persons.passwod=? ;" ;
                ps = etat.prepareStatement(query) ;
                ps.setString(1,username);
                ps.setString(2,password);
                rs = ps.executeQuery() ;
                if (rs.next()){
                    return Integer.parseInt(rs.getString(1));
                }
            }
        } catch (SQLException e) {
        }

        return -1 ;


    }




}
