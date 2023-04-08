package com.example.start02.Gere_BD;
import java.sql.* ;

public class Gere_BD {

    public static Connection getConnection()
    {
        Connection con = null ;
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost/mangment","root","") ;



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
               // String query = "SELECT etudiant.idEtu from etudiant join persons on  etudiant.idEtu = persons.idPersone WHERE etudiant.appoge=?  and  persons.userName=? and persons.passwordd =?" ;
                String query = "Select * from persons  WHERE persons.userName=? and persons.passwordd =? and persons.activeCmpte = false ;" ;
                ps = etat.prepareStatement(query) ;
             /*   ps.setString(1,appoge);
                ps.setString(2,username);
                ps.setString(3,password);

              */
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

    public static int checkEnseignement(String matricule,String username,String password)
    {
        Connection etat = Gere_BD.getConnection() ;

        try {
            if(  !etat.isClosed())
            {
                PreparedStatement ps ;
                ResultSet rs ;
                //mn bead nzid ta matricule
              //  String query = "SELECT persons.idPersone FROM persons join  professeur on  professeur.idProf = persons.idPersone WHERE persons.userName =? and persons.passwordd=? " ;
                String query = "Select * from persons  WHERE persons.userName=? and persons.passwordd =? and persons.activeCmpte = true ;" ;
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
                String query = "SELECT persons.idPersone FROM persons join  professeur on  professeur.idProf = persons.idPersone WHERE persons.userName =? and persons.passwordd=?  where etat=true " ;
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
    public static void main(String[] args) {

      //  System.out.println(checkEtudiant("20033922","jamalZe","admin"));
        //System.out.println(checkEnseignement("20033922","fatnaZoot","1234"));
    }



}
