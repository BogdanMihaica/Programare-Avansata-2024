
public class Main {
    //Mihaica Bogdan 2B2
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int a, b, k, ri=0;
        String reset = "\u001B[0m";
        String yellow = "\u001B[33m";

        StringBuilder kReducibleNumbers = new StringBuilder();
        a = Integer.parseInt(args[0]);
        b = Integer.parseInt(args[1]);
        k = Integer.parseInt(args[2]);
        if (a < 0 || b < 0) {
            System.err.println("Error: Invalid arguments for the first 2 numbers (they need to be of type int).");
        } else if (a > b) {
            System.err.println("Error: The lower bound is set higher than the higher bound");
        } else if (k < 0 || k > 9) {
            System.err.println("Error: Third parameter is out of bounds (must be a digit)");
        }
        else {
            for (int i = a; i <= b; i++) {
                int sum;
                int y = i;
                while (y >= 10) {
                    sum = 0;
                    while (y != 0) {
                        sum += (y % 10) * (y % 10);
                        y /= 10;
                    }
                    y = sum;


                }
                if (y == k) {

                    kReducibleNumbers.append(i);
                    kReducibleNumbers.append(" ");
                }
            }
            System.out.println(kReducibleNumbers);

        }


        long endTime = System.nanoTime();
        float duration = (float) (endTime - startTime) / 1_000_000;
        System.out.println("Program ran for: " + yellow+duration+reset + " milliseconds.");
    }
}
