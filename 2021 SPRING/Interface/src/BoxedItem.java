// Implementing the interfaces

public class BoxedItem implements Sellable, Transportable {
    // instance variables
    private String descript;
    private int price;
    private int weight;
    private boolean haz;

    private int height = 0;
    private int width = 0;
    private int depth = 0;

    /////////////////////////////////////////////////////////////////////////////
    // NAME: BoxedItem
    // BEHAVIOR: Constructs the boxed item with a description, price, weight, and
    //           whether it is hazardous or not
    // PARAMETERS: descript, price, weight, haz
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public BoxedItem(String descript, int price, int weight, boolean haz) {
        this.descript = descript;
        this.price = price;
        this.weight = weight;
        this.haz = haz;
    }

    // implement five methods in two interfaces

    /////////////////////////////////////////////////////////////////////////////
    // NAME: description
    // BEHAVIOR: returns the item description
    // PARAMETERS: none
    // RETURNS: descript
    /////////////////////////////////////////////////////////////////////////////
    public String description() {
        return descript;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: listPrice
    // BEHAVIOR: returns the price of the item
    // PARAMETERS: none
    // RETURNS: price
    /////////////////////////////////////////////////////////////////////////////
    public int listPrice() {
        return price;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: lowestPrice
    // BEHAVIOR: returns the lowest price for the item (half price)
    // PARAMETERS: none
    // RETURNS: half price
    /////////////////////////////////////////////////////////////////////////////
    public int lowestPrice() {
        return price / 2;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: weight
    // BEHAVIOR: returns the weight of the item
    // PARAMETERS: none
    // RETURNS: weight
    /////////////////////////////////////////////////////////////////////////////
    public int weight() {
        return weight;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: isHazardous
    // BEHAVIOR: returns boolean true or false for the item
    // PARAMETERS: none
    // RETURNS: haz
    /////////////////////////////////////////////////////////////////////////////
    public boolean isHazardous() {
        return haz;
    }

    // add new methods

    /////////////////////////////////////////////////////////////////////////////
    // NAME: insuredValue
    // BEHAVIOR: returns double the items price
    // PARAMETERS: none
    // RETURNS: price * 2
    /////////////////////////////////////////////////////////////////////////////
    public int insuredValue() {
        return price * 2;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: setBox
    // BEHAVIOR: sets the box height, width, and depth
    // PARAMETERS: h, w, d
    // RETURNS: nothing
    /////////////////////////////////////////////////////////////////////////////
    public void setBox(int h, int w, int d) {
        height = h;
        weight = w;
        depth = d;
    }

    /////////////////////////////////////////////////////////////////////////////
    // NAME: toString
    // BEHAVIOR: returns the box item descript, price, weight, and haz in format
    // PARAMETERS: none
    // RETURNS: formatted string
    /////////////////////////////////////////////////////////////////////////////
    public String toString() {
        return "BoxedItem{" +
                "descript=" + descript + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", haz=" + haz +
                "}";
    }

} // end of class
