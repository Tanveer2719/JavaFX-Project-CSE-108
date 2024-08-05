package Part1;

import java.util.Scanner;

public class InputIndex {
    Scanner scanner = new Scanner(System.in);

    int getIndex(int n){
        System.out.println("Enter any index (1-"+ n + ") to continue....");
        int index;
        do {
            index = scanner.nextInt();

            if (index > n || index < 0) System.out.println("Invalid Input." + " Enter again...");
        } while (index > n);

        return index;
    }

}
