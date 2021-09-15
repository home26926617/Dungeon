import java.util.*;

public class Passage extends Structure{
    ArrayList<Integer> xAxis = new ArrayList<Integer>();
    ArrayList<Integer> yAxis = new ArrayList<Integer>();
    public int room1;
    public int room2;
    
    public Passage(){
        
    }

    public void setName(String name){
        System.out.println("set name: " + name);
        
    }

    public void setID(int _room1, int _room2){
        System.out.println("set ID: room1: " + _room1 + ", room2: " + _room2);
        room1 = _room1;
        room2 = _room2;
        System.out.println("room1, room2: " + room1 + room2);
    }

    public void setPosX(int _x){
        xAxis.add(_x);
        
    }
 
    public void setPosY(int _y){
        yAxis.add(_y);
        
    }
}