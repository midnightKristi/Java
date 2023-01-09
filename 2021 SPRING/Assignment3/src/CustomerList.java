import java.io.*;
import java.util.*;

public class CustomerList {
    private int count;
    private CustomerRecord[] data;

    // Constructor
    public CustomerList() {
        count = 0;
        data = new CustomerRecord[100];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: getCustomerList
    //BEHAVIOR: reads a text file containing customer data, fills the data array with the records from the file.
    //         file format: customerNumber firstName lastName balance
    //PARAMETERS: text file name
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void getCustomerList(String fileName) throws IOException {
        Scanner scan = new Scanner(new File(fileName));
        //read text file, line by line
        while (scan.hasNext()) {
            int n = scan.nextInt();
            String f = scan.next();
            String l = scan.next();
            double d = scan.nextDouble();
            // create new record
            CustomerRecord record = new CustomerRecord(n, f, l, d);
            System.out.println(record);
            // store the record in the database
            enterCustomerRecord(record);
        }
        scan.close();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: getCustomer
    //BEHAVIOR: returns the object corresponding to the customer with customerNumber,
    //          returns null if number is not in the array
    //PARAMETERS: customer number
    //RETURNS: the corresponding object or null
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CustomerRecord getCustomer(int customerNumber) {
        for (int index = 0; index < data.length; index++) {
            if (data[index] != null && data[index].getCustomerNumber() == customerNumber) {
                return data[index];
            }
        }
        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: enterCustomerRecord
    //BEHAVIOR: stores the customer record in the data array
    //PARAMETERS: a new record (type customerRecord)
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void enterCustomerRecord(CustomerRecord new_record) {
        for (int index = 0; index < data.length; index++) {
            if (data[index] == null) {
                data[index] = new_record;
                count++;
                break;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: saveCustomerList
    //BEHAVIOR: save the information stored in the data array to the file called filename
    //PARAMETERS: filename (a string)
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void saveCustomerList(String filename) {
        try {
            FileWriter file = new FileWriter(filename);
            file.write(Arrays.toString(data));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} // end of class
