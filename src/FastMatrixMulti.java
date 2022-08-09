import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FastMatrixMulti {

    // This is fast matrix multiplication
    public static int matrix_chain_order(int[] arr){
       // array length
       int n = arr.length;
       // variables
        int i, h, j, k, q;
        // new array that will store all the time cost of each matrices together
        int[][] m = new int[n][n];

        int[][] s = new int[n][n];

        for(i = 1; i < n; i++)

            m[i][i] = 0;
            // h is determining the space between i and j
            for (h = 2; h < n; h++) {

                for (i = 1; i < n - h + 1; i++) {

                    j = i + h - 1;

                    m[i][j] = Integer.MAX_VALUE;

                    for (k = i; k <= j - 1; k++) {

                        q = m[i][k] + m[k + 1][j] + (arr[i - 1] * arr[k] * arr[j]);

                        if (q < m[i][j]) {
                            m[i][j] = q;
                            s[i][j] = k;
                        }
                    }
                }
            }
            print_optimal_parens(s, 1, n-1);
            System.out.println("");
        return m[1][n-1];
    }

    public static void print_optimal_parens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A_"+i);
            return;
        } else {
            System.out.print("(");
            print_optimal_parens(s, i, s[i][j]);
            print_optimal_parens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File(args[0]));
        int count = 0;
        while(s.hasNext()){
            count++;
            s.nextLine();
        }

        s.close();
        s = new Scanner(new File(args[0]));

        int[] data = new int[count];

        for(int i = 0 ; i < count; i++) {
            data[i] = Integer.parseInt(s.nextLine().trim());
        }
        // test array to fill array with
        for(int i = 0 ; i < data.length; i++){
            System.out.println(data[i]);
        }

        int arrSize = data.length;
        System.out.println("Fast Matrix Multi: " + matrix_chain_order(data));
    }

}
