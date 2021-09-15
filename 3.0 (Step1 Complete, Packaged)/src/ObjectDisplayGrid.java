package src;

public class ObjectDisplayGrid{
    public ObjectDisplayGrid(){
        
    }

    public void setObjectDisplayGrid(int bottomHeight, int gameHeight, int topHeight, int width, String name){
        System.out.println("set object display grid: bottom height: " + bottomHeight + ", game height: " + gameHeight + ", top height: " + topHeight + ", width: " + width + ", name: " + name);
        
    }

    public void getObjectDisplayGrid(int bottomHeight, int gameHeight, int topHeight, int width, String name){
        System.out.println("get object display grid: bottom height: " + bottomHeight + ", game height: " + gameHeight + ", top height: " + topHeight + ", width: " + width + ", name: " + name);
        
    }

    public void setTopMessageHeight(int topHeight){
        System.out.println("set top message height: " + topHeight);
        
    }

    public void setBottomMessageHeight(int bottomHeight){
        System.out.println("set bottom message height: " + bottomHeight);
        
    }
}