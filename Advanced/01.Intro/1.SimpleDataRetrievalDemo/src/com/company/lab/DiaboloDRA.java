package com.company.lab;

import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class DiaboloDRA {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = sc.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt =
                connection.prepareStatement(
                        "SELECT COUNT(ug.game_id)  AS games_count, CONCAT(u.first_name, ' ', u.last_name) AS full_name " +
                                "FROM users as u\n" +
                                "JOIN users_games AS ug\n" +
                                "ON u.id=ug.user_id\n" +
                                "WHERE u.user_name =  ?"
                );

        String username = sc.nextLine();
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String count = rs.getString("games_count");
            if (!count.equals("0")) {
                System.out.println("User: " + username);
                System.out.println(rs.getString("full_name") + " has played " + count + " games");
            } else {
                System.out.println("No such user exists");
            }
        }
        connection.close();
    }
}
