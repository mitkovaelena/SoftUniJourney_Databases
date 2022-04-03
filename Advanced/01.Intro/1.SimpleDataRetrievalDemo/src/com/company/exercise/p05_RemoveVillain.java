package com.company.exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class p05_RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/miniondb", props);

        try {
            connection.setAutoCommit(false);
            PreparedStatement deletePK =
                    connection.prepareStatement(
                            "DELETE FROM minions_villains " +
                                   "WHERE villain_id = ? ;"
                    );
            PreparedStatement deleteVillain =
                    connection.prepareStatement(
                            "DELETE FROM villains " +
                                    "WHERE id = ? ;"
                    );
            PreparedStatement selectVillainsName =
                    connection.prepareStatement(
                            "SELECT name FROM villains " +
                                    "WHERE id = ?; "
                    );
            PreparedStatement selectReleasedMinionsCount =
                    connection.prepareStatement(
                            "SELECT count(minion_id) AS minions_count FROM minions_villains " +
                                    "WHERE villain_id = ?;"
                    );
            int villainId = Integer.parseInt(sc.nextLine());

            deletePK.setInt(1,villainId);
            deleteVillain.setInt(1,villainId);
            selectVillainsName.setInt(1,villainId);
            selectReleasedMinionsCount.setInt(1,villainId);
            ResultSet r1 = selectVillainsName.executeQuery();
            ResultSet r2 = selectReleasedMinionsCount.executeQuery();

            deletePK.execute();
            deleteVillain.execute();

            if(r1.next() && r2.next()) {
                System.out.println(r1.getString("name") + " was deleted.");
                System.out.println(r2.getInt("minions_count") + " minion/s released.");
            }
            connection.commit();
        } catch(SQLException se){
            se.printStackTrace();
            connection.rollback();
        }
        finally {
            connection.close();
        }
    }
}
