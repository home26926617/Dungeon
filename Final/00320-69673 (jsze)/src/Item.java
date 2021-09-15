public class Item extends Displayable{
    public int room;
    public int serial;
    public String name;
    
    public Item(){
        
    }
    
    public void setOwner(Creature owner){
        System.out.println("set owner" + owner);
        
    }

    public void setID(int _room, int _serial){
        System.out.println("set ID: room " + _room + ", serial: " + _serial);
        room = _room;
        serial = _serial;
        
    }
}