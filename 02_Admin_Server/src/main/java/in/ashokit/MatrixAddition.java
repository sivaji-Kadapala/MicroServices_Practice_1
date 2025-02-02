package in.ashokit;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MatrixAddition {
    public static int[][] readMatrix(Scanner scanner){
        return IntStream.range ( 0,3 )
                .mapToObj ( i->IntStream.range ( 0,3 )
                        .map(j->
                                scanner.nextInt ()
                                        ).toArray ())
                .toArray (int[][]::new);

    }
    public  static  void printMatrix(int[][] matrix){
        Arrays.stream ( matrix )
                .map ( row->
                        Arrays.toString ( row ))
                .forEach ( System.out::println );
    }
    public static void main(String[] args) {

        Scanner scanner=new Scanner ( System.in );
        System.out.println ("enter elements for first 3*3");
        int[][] matrix1=readMatrix(scanner);
        System.out.println ("enter elements for second 3*3");
        int[][] matrix2=readMatrix(scanner);
        int[][] result= IntStream.range ( 0,3 ).
                mapToObj ( i->IntStream.range ( 0,3 )
                        .map(j->matrix1[i][j]+matrix2[i][j])
                        .toArray ()).toArray (int[][]::new);
        System.out.println ("Matrix after Addition");
        printMatrix(result);
    }
}
