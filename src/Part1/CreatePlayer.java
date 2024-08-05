package Part1;

import java.util.Scanner;

public class CreatePlayer {
    Scanner scan = new Scanner(System.in);

    // take the information of a new player and return a Player object
    Player newPlayer(){
        System.out.println("Enter new Player data ");
        System.out.print("Name: ");
        String name = scan.nextLine();

        System.out.print("Country: ");
        String country = scan.nextLine();

        System.out.print("Age: ");
        int age = scan.nextInt();

        System.out.print("height: ");
        double height = scan.nextDouble();

        System.out.print("Club: ");
        String clubN = scan.nextLine();
        clubN = scan.nextLine();

        System.out.print("Position: ");
        String pos = scan.nextLine();

        System.out.print("Jersey No: ");
        int jersyNo = scan.nextInt();

        System.out.print("Weekly Salary: ");
        double salary = scan.nextDouble();

        Player temp = new Player(name,country,age,height,clubN,pos,jersyNo,salary);
        return temp;
    }
}
