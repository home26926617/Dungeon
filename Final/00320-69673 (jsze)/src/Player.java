import java.util.*;

public class Player extends Creature{
    public int room;
    public int serial;
    public ArrayList<Item> items = new ArrayList<Item>();
    public Item sword;
    public Item armor;
    
    public Player(){
        
    }
    
    public void setName(String name){
        System.out.println("set name: " + name);
        
    }

    public void setID(int _room, int _serial){
        System.out.println("set ID: room " + _room + ", serial: " + _serial);
        room = _room;
        serial = _serial;
        
    }
    public void setWeapon(Item _sword){
        System.out.println("set weapon: " + sword);
        sword = _sword;
        
    }

    public void setArmor(Item _armor){
        System.out.println("set armor: " + armor);
        armor = _armor;
        
    }

    public void addItem(Item _item){
        items.add(_item);
    }
}