package src;

public class Passage extends Structure{
    public Passage(){
        
    }

    public void setName(String name){
        System.out.println("set name: " + name);
        
    }

    public void setID(int room1, int room2){
        System.out.println("set ID: room1: " + room1 + ", room2: " + room2);
        
    }
}