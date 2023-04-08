package com.example.start.MangmentBaseDonne;
import java.sql.* ;
public class Gere_BD2 {

    public static Connection getConnection()
    {
        Connection con = null ;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/applicationversion2","root","") ;
            System.out.println("bien");


        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return con ;
    }

    public static void main(String[] args) {

        System.out.println(getConnection());
    }

}
