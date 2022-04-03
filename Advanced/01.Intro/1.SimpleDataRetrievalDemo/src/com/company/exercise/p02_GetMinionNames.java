package com.company.exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class p02_GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/miniondb", props);


        PreparedStatement stmt1 =
                connection.prepareStatement(
                        "SELECT v.name " +
                                "FROM villains AS v\n" +
                                "WHERE id = ? ;"
                );
        PreparedStatement stmt2 =
                connection.prepareStatement(
                        "SELECT m.name, m.age " +
                                "FROM minions_villains AS mv\n" +
                                "JOIN minions AS m\n" +
                                "ON m.id=mv.minion_id\n" +
                                "WHERE villain_id = ? ;"
                );

        int id = Integer.parseInt(sc.nextLine());
        stmt1.setInt(1, id);
        stmt2.setInt(1, id);
        ResultSet rs1 = stmt1.executeQuery();
        ResultSet rs2 = stmt2.executeQuery();

        if(!rs1.next()){
            System.out.println("No villain with ID "+ id +" exists in the database.");
        } else {
                System.out.println("Villain: " + rs1.getString("name"));
                int i = 1;
                while (rs2.next()) {
                    System.out.println(i + ". " + rs2.getString("name") + " " + rs2.getInt("age"));
                    i++;
                }
        }
        connection.close();
    }
}
