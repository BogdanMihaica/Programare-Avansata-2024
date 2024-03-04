public class Wheel {
    //Mihaica Bogdan 2B2
    public static void main(String[] args) {
        int n;
        int cycles = 0;
        n = Integer.parseInt(args[0]);
        if (n <= 3) {
            System.err.println("Error: Size too small to be a wheel graph.");
        } else {
            String green = "\u001B[32m";
            String reset = "\u001B[0m";
            int[][] m = new int[n + 1][n + 1];
            System.out.println();
            System.out.println("Adjacency matrix: \n");
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    m[i][j] = 0;
                }
            }
            for (int i = 2; i <= n; i++) {
                m[1][i] = m[i][1] = 1;
            }
            for (int i = 2; i <= n - 1; i++) {
                m[i][i + 1] = m[i + 1][i] = 1;
            }
            m[2][n - 1] = m[n - 1][2] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (m[i][j] == 1) {
                        System.out.print(green + m[i][j] + " " + reset);
                    } else System.out.print(m[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("Cycles: ");
            System.out.println();
            for (int i = 2; i <= n; i++) {
                for (int j = i + 1; j <= i + n - 2; j++) {
                    System.out.print(1 + " " + i + " ");
                    for (int k = i+1; k <= j; k++) {
                        if (k > n) {
                            System.out.print((k % n + 1) + " ");
                        } else System.out.print(k + " ");
                    }
                    System.out.print(1 + "\n");
                    cycles++;
                }
            }
            for(int i=2; i<=n; i++)
            {
                System.out.print(i+" ");
            }
            System.out.print(2);
            cycles++;
            System.out.println();
            int exp = (n * n - 3 * n + 3);
            System.out.println();
            System.out.println("n^2 - 3n + 3 = " + exp);
            System.out.println("Counted cycles: " + cycles);
            if ((exp) == cycles) {
                System.out.println("Indeed, n^2 - 3n + 3 is equal to the number of cycles of this wheel graph.");
            } else {
                System.out.println("Somehow, n^2 - 3n + 3 is not equal to the number of cycles of this wheel graph.");
            }
        }
    }
}
