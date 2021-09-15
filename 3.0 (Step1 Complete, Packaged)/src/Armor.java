package src;

public class Armor extends Item{
    public Armor(String name){
        
    }

    public void setName(String name){
        System.out.println("set name: " + name);
        
    }

    public void setID(int room, int serial){
        System.out.println("set ID: room " + room + ", serial " + serial);
        
    }
}