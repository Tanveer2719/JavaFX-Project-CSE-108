package Part1;

import java.util.ArrayList;
import java.util.List;

public class Count {
    private String countryName;
    private int count;

    public Count(){

    }
    public Count(String name, int c){
        countryName = name;
        count = c;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
