public class FibRecursion {
    public static void main(String[] args) {
        int value = Fib(5);
        System.out.println(value);
    } // end of main


    static int Fib(int n) {
        if (n < 0) {
            System.out.println("argument cannot be negative");
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return Fib(n - 2) + Fib(n - 1);
    }
}
