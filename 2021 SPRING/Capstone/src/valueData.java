
public class valueData {
    private String variable;
    private float value;

    // define constructor
    public valueData(String name, float value) {
        this.variable = name;
        this.value = value;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: getValue
    // BEHAVIOR: Returns the variable's value
    // PARAMETERS: none
    // RETURNS: value
    /////////////////////////////////////////////////////////////////////////////
    public float getValue() {
        return value;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: setValue
    // BEHAVIOR: Sets the variable's value to the double provided
    // PARAMETERS: value
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public void setValue(float value) {
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

    @Override
    public String toString(){
        return String.valueOf(getValue());
    }


} // end of class
