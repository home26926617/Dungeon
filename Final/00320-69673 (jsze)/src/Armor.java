public class Armor extends Item{
    // public String name;
    
    public Armor(String name){
        
    }

    public void setName(String _name){
        System.out.println("set name: " + name);
        name = _name;
        
    }

    public void setID(int room, int serial){
        System.out.println("set ID: room " + room + ", serial " + serial);
        
    }
}