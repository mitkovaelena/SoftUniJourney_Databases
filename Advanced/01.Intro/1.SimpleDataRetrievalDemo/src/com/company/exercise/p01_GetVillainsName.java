package com.company.exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class p01_GetVillainsName {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/miniondb", props);

        PreparedStatement stmt =
                connection.prepareStatement(
                        "SELECT v.name, COUNT(mv.minion_id)  AS minions_count " +
                                "FROM villains AS v\n" +
                                "JOIN minions_villains AS mv\n" +
                                "ON v.id=mv.villain_id\n" +
                                "GROUP BY villain_id " +
                                "HAVING minions_count >= 3 " +
                                "ORDER BY minions_count DESC;"
                );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("minions_count"));
        }
        connection.close();
    }
}
