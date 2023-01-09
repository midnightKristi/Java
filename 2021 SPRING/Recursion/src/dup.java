public class dup {
    public static void main(String[] args){
        System.out.println(dup("dog", "cat", 5));
    }
    public static String dup(String a, String b, int n){
        // base cases
        if (n == 0)
            return "";
        else if (n == 1)
            return a;
        else if (n == 2)
            return a + b;
        // recursive case
        else
            return (a + b + dup(a, b, n-2));

    }
}
