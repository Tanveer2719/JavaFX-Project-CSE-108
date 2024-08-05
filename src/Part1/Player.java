

/**
 * Name, Country, and Club are Strings.
 * Age and Position are positive integers.
 * Position is a String with no space inside.
 * Height and WeeklySalary are positive doubles.
 */

package Part1;


import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int jerseyNo;
    private double weeklySalary;
    private double marketPrice;

    public Player(){

    }

    Player(String name,String country,int age,double height, String club,String position,int jerseyNo,double weeklySalary){
        this.name = name;
        this.country = country;
        this.age = age;
        this.weeklySalary = weeklySalary;
        this.jerseyNo = jerseyNo;
        this.height = height;
        this.club = club;
        this.position = position;
    }

    void printPlayer()
    {
        System.out.println("Name: "+name);
        System.out.println("Country: " + country);
        System.out.println(("Age: "+age));
        System.out.println("Height: "+height);
        System.out.println("Club: " + club);
        System.out.println("Position: "+ position);
        System.out.println("Jersey No: "+ jerseyNo);
        System.out.println("Weekly Salary: "+ weeklySalary);
    }


    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getJerseyNo() {
        return jerseyNo;
    }

    public void setJerseyNo(int jerseyNo) {
        this.jerseyNo = jerseyNo;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }
}
