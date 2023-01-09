
public class valueData {
    private String variable;
    private double value;

    // define constructor
    public valueData(String name, Double value) {
        this.variable = name;
        this.value = value;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: getVariable
    // BEHAVIOR: Returns the variable name
    // PARAMETERS: none
    // RETURNS: Variable
    /////////////////////////////////////////////////////////////////////////////
    public String getVariable() {
        return variable;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: setVariable
    // BEHAVIOR: Sets the variable name to the string provided
    // PARAMETERS: name
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public void setVariable(String name) {
        this.variable = name;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: getValue
    // BEHAVIOR: Returns the variable's value
    // PARAMETERS: none
    // RETURNS: value
    /////////////////////////////////////////////////////////////////////////////
    public double getValue() {
        return value;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: setValue
    // BEHAVIOR: Sets the variable's value to the double provided
    // PARAMETERS: value
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public void setValue(double value) {
        this.value = value;
    }

    // define toString
    public String toString(String name) {
        // WOULD RATHER FIX AND USE THE FIND METHOD IN ASSIGNMENT5.JAVA
        // and then RETURN THE STRING

        /*
        LinkedListNode theNode = head;
        while (theNode != null) {
            String var = ((valueData) theNode.getData()).getVariable();
            double val = ((valueData) theNode.getData()).getValue();
            if (var.equals(name)) {
                return var + " = " + value;
                break;
            }
            theNode = theNode.getNext();
        }
         */
    } // end of toString

} // end of class
