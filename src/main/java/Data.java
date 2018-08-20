
import java.util.ArrayList;


public class Data {
    private ArrayList numberSetOne = new ArrayList();
    private ArrayList numberSetTwo = new ArrayList();
    private ArrayList wordSetOne = new ArrayList();
    
    public void setNumberSetOne(int value) {
        this.numberSetOne.add(value);
    }
    
    public void setNumberSetTwo(int value) {
        this.numberSetTwo.add(value);
    }
    
    public void setWordSetOne(String value) {
        this.wordSetOne.add(value);
    }
    
    public ArrayList getNumberSetOne() {
        return this.numberSetOne;
    }
    
    public ArrayList getNumberSetTwo() {
        return this.numberSetTwo;
    }
    
    public ArrayList getWordSetOne() {
        return this.wordSetOne;
    }
    
    public int getNumberSetOne(int index) {
        return (int) this.numberSetOne.get(index);
    }
    
    public int getNumberSetTwo(int index) {
        return (int) this.numberSetTwo.get(index);
    }
    
    public String getWordSetOne(int index) {
        return (String) this.wordSetOne.get(index);
    }
    
}
