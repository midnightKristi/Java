public class BoxDriver {
    public static void main(String[] args) {
        System.out.println("\nUSING INTERFACE" + "\n------------------------------------");

        Sellable item1 = new BoxedItem("book", 235, 500, false);
        Transportable item2 = new BoxedItem("cleaner", 9876, 10000, true);

        System.out.println("Sellable boxed item: ");
        System.out.println("    Item description: " + item1.description());
        System.out.println("    Item price: $" + item1.listPrice());
        System.out.println("    Lowest price: $" + item1.lowestPrice());
        System.out.println("-------------------------------------");
        System.out.println("Transportable boxed item:");
        System.out.println("    Item weight: " + item2.weight());
        System.out.println("    Is it hazardous? " + item2.isHazardous());
        System.out.println("-------------------------------------");
        System.out.println("Item1: " + item1.toString());
        System.out.println("-------------------------------------");
        System.out.println("Item2: " + item2.toString());
        System.out.println("-------------------------------------");

        BoxedItem box_item1 = (BoxedItem) item1;
        BoxedItem box_item2 = (BoxedItem) item2;
        System.out.println("Insured value for book: $" + box_item1.insuredValue());
        System.out.println("Insured value for cleaner: $" + box_item2.insuredValue());

        box_item1.setBox(5, 5, 5);
        box_item2.setBox(10, 10, 10);
    }

}
