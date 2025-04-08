package main.najah.code;

public class Calculator {
    public int add(int... numbers) {
        int sum = 0;
        for (int n : numbers) sum += n;
        return sum;
    }

    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }

    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative input");
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
}
