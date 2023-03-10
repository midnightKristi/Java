// PhoneRecord.java
// This class represents a record containing a name and a phone number

public class PhoneRecord {
    private String name;
    private String number;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: PhoneRecord
    //BEHAVIOR: Constructs a phone record with the persons name and phone number
    //PARAMETERS: personName and phoneNumber
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public PhoneRecord(String personName, String phoneNumber) {
        name = personName;
        number = phoneNumber;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: getName
    //BEHAVIOR: Returns the persons name
    //PARAMETERS: none
    //RETURNS: name
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getName() {
        return name;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: getNumber
    //BEHAVIOR: Returns the person phone number
    //PARAMETERS: none
    //RETURNS: number
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getNumber() {
        return number;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: setName
    //BEHAVIOR: Modifies the persons name to given name
    //PARAMETERS: personName
    //RETURNS: nothing
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setName(String personName) {
        name = personName;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: setNumber
    //BEHAVIOR: Modifies the persons number to the given number
    //PARAMETERS: phoneNumber
    //RETURNS: number
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setNumber(String phoneNumber) {
        number = phoneNumber;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NAME: toString
    //BEHAVIOR: Returns the phone record as a formatted string
    //PARAMETERS: none
    //RETURNS: phone record string
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                "}";
    }

} // end of class




