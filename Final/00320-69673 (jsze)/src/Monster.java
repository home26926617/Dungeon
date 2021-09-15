public class Monster extends Creature{
    public int room;
    public int serial;
    public String name;
    public boolean fight = false;
    
    public Monster(){
        
    }

    public void setName(String _name){
        System.out.println("set name: " + name);
        name = _name;
        
    }

    public void setID(int _room, int _serial){
        System.out.println("set ID: room " + _room + ", serial: " + _serial);
        room = _room;
        serial = _serial;
        
    }

    public void setFight(boolean _fight){
        fight = _fight;
        
    }
}