public class Creature extends Displayable{
    public Creature(){
        
    }

    public void setMaxHit(int maxHit){
        System.out.println("set max hit: " + maxHit);
        
    }

    public void setHp(int hp){
        System.out.println("set hp: " + hp);
        
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
}