package debugging;
public class Fibonacci {
    public static void main(String[] args) {
        for (int i=0; 10>i;i++)
        System.out.println("fib("+i+")= "+fib(i));
    }

    static int fib(int n) {
        int f = 0;
        int f0 = 1;
        int f1 = 1;
        while (n > 1) {
             n--;
             f = f0 +f1;
             f0 = f1;
             f1 = f; }
        return f; }

}
