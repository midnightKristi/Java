// examples found online for how to do this were referenced and similarities thus may occur.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        Connection conn=null;

        try {
            //load and register driver
            Class.forName("oracle.jdbc.OracleDriver");

            //get connection object here we are using oracle database
            conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:8000:xe","system","vibhu");
            conn.setAutoCommit(false);

            Statement st=conn.createStatement();
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Source Account:");
            String source_Account=br.readLine();
            System.out.print("Target Account:");
            String target_Account=br.readLine();
            System.out.print("Amount To Transfer :");
            int trans_Amt=Integer.parseInt(br.readLine());

            //Checking if sufficient fund is available or not
            ResultSet rs = st.executeQuery("Select * from bank account where accnum="+source_Account);
            if(rs.next()) {
                int balance=rs.getInt(2);
                if(balance<trans_Amt) {
                    System.out.println("Insufficient fund");
                    return;
                }
            }
            //UPDATING BALANCE
            int rowCount1=st.executeUpdate("update bank account set balance=balance-"+trans_Amt+" where accnum='"+source_Account+"'");
            int rowCount2=st.executeUpdate("update bank account set balance=balance+"+trans_Amt+" where accnum='"+target_Account+"'");
            //if both record updated successfully commit it otherwise rollback
            if((rowCount1==1) && (rowCount2==1)) {
                conn.commit();
                System.out.println("Transaction Success");
            }
            else {
                conn.rollback();
                System.out.println("Transaction Fail");
            }

        }
        // ROLLBACK if any error occur during transaction
        catch(Exception e) {
            try {
                conn.rollback();
                System.out.println("Transaction Fail");

            } catch (Exception e2) {
                e.printStackTrace();
            }
        }
    }
}