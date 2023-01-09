// CSCI 521 - Fall 2021
// Assignment 1
// Kristi LaVigne
// Credit to: https://www.developer.com/design/how-to-handle-transactions-with-the-jdbc-api/

package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    private static final String URL =
            "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "t123456";
    private static final String DRIVER =
            "com.mysql.jdbc.Driver";

    public void transferFund(String fromTable, String toTable,
                             String ofAccNumber, int amount)
            throws ClassNotFoundException,
            InsufficientFundException, SQLException{
        Class.forName(DRIVER);
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER,
                    PASSWORD);
            connection.setAutoCommit(false);

            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery("SELECT balance FROM " + fromTable
                            + " WHERE account number =" + ofAccNumber);
            resultSet.next();
            int balance1 = resultSet.getInt(1) - amount;
            if (balance1 < 0)
                throw new InsufficientFundException("Insufficient Fund.");
            resultSet.close();
            resultSet = statement.executeQuery("SELECT balance FROM "
                    + toTable + " WHERE account number =" + ofAccNumber);
            resultSet.next();
            int balance2 = resultSet.getInt(1);
            statement.executeUpdate(
                    "UPDATE " + fromTable + " SET balance=" + (balance1)
                            + " WHERE account number =" + ofAccNumber);
            statement.executeUpdate(
                    "UPDATE " + toTable + " SET balance="
                            + (balance2 + amount)
                            + " WHERE account number =" + ofAccNumber);
            connection.commit();
        }catch(SQLException ex){
            connection.rollback();
            throw ex;
        }finally{
            if (resultSet!= null)
                resultSet.close();
            if (statement != null)
                statement.close();
            connection.close();
        }
    }

    public void showBalance(String table, String accno)
            throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);

        try (Connection connection =
                     DriverManager.getConnection(URL, USER,
                             PASSWORD)) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT balance FROM "
                    + table + "To account number: " + accno);
            while (resultSet.next())
                System.out.println(table + " "
                        + accno + " " + resultSet.getInt(1));
        }
    }

    class InsufficientFundException extends Exception {
        private static final long serialVersionUID = 1L;

        public InsufficientFundException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws Exception {

        Test t = new Test();

        t.showBalance("savings_acc", "1001");
        t.showBalance("current_acc", "1001");
        t.transferFund("savings_acc", "current_acc", "1001", 8000);
        t.showBalance("savings_acc", "1001");
        t.showBalance("current_acc", "1001");
    }
}