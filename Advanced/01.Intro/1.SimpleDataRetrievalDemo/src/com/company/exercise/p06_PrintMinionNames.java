package com.company.exercise;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.Scanner;

public class p06_PrintMinionNames {
    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/miniondb", props);


        PreparedStatement stmt =
                connection.prepareStatement(
                        "SELECT name " +
                                "FROM minions;"
                );

        ResultSet rs = stmt.executeQuery();
        Deque names = new ArrayDeque();
        while (rs.next()) {
            names.add(rs.getString("name"));
        }
        int i = 0;
        while (!names.isEmpty()) {
            System.out.println(i % 2 == 0 ? names.pollFirst() : names.pollLast());
            i++;
        }
        connection.close();
    }
}
