package com.company.exercise;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class p08_IncreaseMinionsAgeProcedure {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/miniondb", props);


        PreparedStatement callUpdateAgeProcedure =
                connection.prepareStatement(
                        "CALL usp_get_older(?);"
                );
        PreparedStatement selectMinion =
                connection.prepareStatement(
                        "SELECT * FROM minions " +
                                "WHERE id = ?;"
                );
        int[] ids = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int id : ids) {
            callUpdateAgeProcedure.setInt(1,id);
            selectMinion.setInt(1, id);
            callUpdateAgeProcedure.executeQuery();
            ResultSet rs = selectMinion.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getInt("age"));
            }
        }
        connection.close();
    }
}
