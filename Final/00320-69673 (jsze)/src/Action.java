public class Action{
    String name;
    String message;
    
    public Action(){
        
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMessage(String _message){
        System.out.println("set message: " + _message);
        message = _message;
    }

    public void setIntValue(int v){
        System.out.println("set int value: " + v);

    }

    public void setCharValue(char c){
        System.out.println("set char value: " + c);
        
    }
}