public class Dungeon{
    public Dungeon(){
        
    }

    public void getDungeon(String name, int width, int gameHeight){
        System.out.println("get dungeon: name: " + name + ", width: " + width + ", game height: " + gameHeight);
        
    }

    public void addRoom(Room room){
        System.out.println("add room: " + room);
        
    }

    public void addCreature(Creature creature){
        System.out.println("add creature: " + creature);
        
    }

    public void addPassage(Passage passage){
        System.out.println("add passage: " + passage);
        
    }

    public void addItem(Item item){
        System.out.println("add item: " + item);
        
    }
}