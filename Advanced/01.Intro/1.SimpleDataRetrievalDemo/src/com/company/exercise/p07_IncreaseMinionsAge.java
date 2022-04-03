package com.company.exercise;

import java.sql.*;
import java.util.*;

public class p07_IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/miniondb", props);


        PreparedStatement updateAge =
                connection.prepareStatement(
                        "UPDATE minions " +
                                "SET age = age + 1 " +
                                "WHERE id = ?;"
                );
        PreparedStatement selectMinion =
                connection.prepareStatement(
                        "SELECT * FROM minions " +
                                "WHERE id = ?;"
                );
        int[] ids = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int id : ids) {
            updateAge.setInt(1,id);
            selectMinion.setInt(1, id);
            updateAge.executeUpdate();
            ResultSet rs = selectMinion.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getInt("age"));
            }
        }
        connection.close();
    }
}
