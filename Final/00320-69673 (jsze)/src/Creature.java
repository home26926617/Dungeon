import java.util.*;

public class Creature extends Displayable{
    public int hp;
    public int maxHit;
    public String name;

    ArrayList<CreatureAction> actions = new ArrayList<CreatureAction>();
    
    public Creature(){
        
    }

    public void setMaxHit(int _maxHit){
        System.out.println("set max hit: " + maxHit);
        maxHit = _maxHit;
        
    }

    public void setHp(int _hp){
        System.out.println("set hp: " + hp);
        hp = _hp;
        
    }

    public void setHpMove(int hpMove){
        System.out.println("set hp moves: " + hpMove);
    }

    public void setDeathAction(CreatureAction deathAction){
        System.out.println("set death action: " + deathAction);
        
    }

    public void setHitAction(CreatureAction hitAction){
        System.out.println("set hit action: " + hitAction);
        
    }

    public void setName(String name){
        System.out.println("Creature: setName() to " + name);
        this.name = name;
    }

    String GetAction(String str) {
        for (int i = 0; i < actions.size(); i++) {
            if (str.equals(actions.get(i).name)){
                return actions.get(i).message;
            }  
        }

        return " ";
        // return "No Such action";
    }
}