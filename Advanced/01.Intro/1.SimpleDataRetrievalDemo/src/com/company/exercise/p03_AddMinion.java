package com.company.exercise;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class p03_AddMinion {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/miniondb", props);

        try {
            connection.setAutoCommit(false);
            PreparedStatement selectVillain =
                    connection.prepareStatement(
                            "SELECT v.id " +
                                    "FROM villains AS v " +
                                    "WHERE v.name = ? ;"
                    );

            PreparedStatement selectTown =
                    connection.prepareStatement(
                            "SELECT t.id " +
                                    "FROM towns AS t " +
                                    "WHERE t.name = ? ;"
                    );

            PreparedStatement addMinion =
                    connection.prepareStatement(
                            "INSERT INTO minions(name, age, town_id) " +
                                    "VALUES(?, ?, " +
                                    "(SELECT id FROM towns WHERE name = ?));"
                    );

            PreparedStatement connectMinionToVillain =
                    connection.prepareStatement(
                            "INSERT INTO minions_villains(minion_id, villain_id) " +
                                    "VALUES(" +
                                    "(SELECT id FROM minions WHERE name = ?), " +
                                    "(SELECT id FROM villains WHERE name = ?));"
                    );

            String[] minionInfo = sc.nextLine().split(" ");
            String[] villainInfo = sc.nextLine().split(" ");

            selectVillain.setString(1, villainInfo[1]);
            selectTown.setString(1, minionInfo[3]);

            addMinion.setString(1, minionInfo[1]);
            addMinion.setInt(2, Integer.parseInt(minionInfo[2]));
            addMinion.setString(3, minionInfo[3]);
            connectMinionToVillain.setString(1, minionInfo[1]);
            connectMinionToVillain.setString(2, villainInfo[1]);


            ResultSet rs1 = selectTown.executeQuery();
            ResultSet rs2 = selectVillain.executeQuery();

            if (!rs1.next()) {
                PreparedStatement addTown =
                        connection.prepareStatement(
                                "INSERT INTO towns(name) " +
                                        "VALUES(?);"
                        );
                addTown.setString(1, minionInfo[3]);
                addTown.executeUpdate();
                System.out.println("Town " + minionInfo[3] + " was added to the database.");
            }

            if (!rs2.next()) {
                PreparedStatement addVillain =
                        connection.prepareStatement(
                                "INSERT INTO villains(name, evilness_factor) " +
                                        "VALUES(?, ?);"
                        );
                addVillain.setString(1, villainInfo[1]);
                addVillain.setString(2, "evil");
                addVillain.executeUpdate();
                System.out.println("Villain " + villainInfo[1] + " was added to the database.");
            }

            addMinion.executeUpdate();
            connectMinionToVillain.executeUpdate();
            System.out.println("Successfully added " + minionInfo[1] + " to be minion of " + villainInfo[1] + ".");
            connection.commit();
        } catch(SQLException se){
            connection.rollback();
        }
        finally {
            connection.close();
        }
    }
}
