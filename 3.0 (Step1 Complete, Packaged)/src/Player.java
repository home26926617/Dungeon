package src;

public class Player extends Creature{
    public Player(){
        
    }
    
    public void setName(String name){
        System.out.println("set name: " + name);
        
    }

    public void setID(int room, int serial){
        System.out.println("set ID: room " + room + ", serial: " + serial);
        
    }
    public void setWeapon(Item sword){
        System.out.println("set weapon: " + sword);
        
    }

    public void setArmor(Item armor){
        System.out.println("set armor: " + armor);
        
    }
}