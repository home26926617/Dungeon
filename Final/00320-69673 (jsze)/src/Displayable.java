public class Displayable{
    public int visible;
    public char type;
    public int intValue;
    public int posX;
    public int posY;
    public int width;
    public int height;
    
    public Displayable(){
        
    }

    public void setInvisible(){
        System.out.println("set invisible");
        
    }

    public void setVisible(){
        System.out.println("set visible: 1");
        
    }

    public void setType(char _t){
        System.out.println("set type: " + _t);
        type = _t;
        
    }

    public void setIntValue(int _v){
        System.out.println("set int value: " + _v);
        intValue = _v;
        
    }

    public void setPosX(int _x){
        System.out.println("set pos x: " + _x);
        posX = _x;
        
    }

    public void setPosY(int _y){
        System.out.println("set pos y: " + _y);
        posY = _y;
        
    }

    public void setWidth(int _x){
        System.out.println("set width: " + _x);
        width = _x;
        
    }

    public void setHeight(int _y){
        System.out.println("set height: " + _y);
        height = _y;

    }
}