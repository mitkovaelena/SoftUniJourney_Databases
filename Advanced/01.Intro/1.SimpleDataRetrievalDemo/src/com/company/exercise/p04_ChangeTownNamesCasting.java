package com.company.exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class p04_ChangeTownNamesCasting {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/miniondb", props);

        PreparedStatement changeNames =
                connection.prepareStatement(
                        "UPDATE towns " +
                                "SET name =  UPPER(name) " +
                                "WHERE country = ? ;"
                );


        String countryName = sc.nextLine();

        changeNames.setString(1, countryName);

        int result = changeNames.executeUpdate();

        if (result > 0) {
            System.out.println(result + " town names were affected");

            PreparedStatement selectTowns =
                    connection.prepareStatement(
                            "SELECT t.name " +
                                    "FROM towns AS t " +
                                    "WHERE t.country = ? ;"
                    );
            selectTowns.setString(1, countryName);
            ResultSet rs = selectTowns.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } else{
            System.out.println("No town names were affected.");
        }

        connection.close();
    }
}
