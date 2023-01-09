// Interface provides the list of method without the method body
// The class who implements Interface need fill in the body of these methods
// Interface represents the contract which the class implements it must honor
// Interfaces specify what a class must do and not how to do

// Interface for objects that can be sold
public interface Sellable {
    // description of the object
    // this method has no body
    public String description();

    // list int listPrice();
    public int listPrice();

    // lowest price in cents which we can accept
    // method in the interface can have the parameter
    public int lowestPrice();
}
