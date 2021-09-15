
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;

    private ArrayList<Room> rooms = null;
    private ArrayList<Player> players = null;
    private ArrayList<Item> items = null;
    static boolean scroll = false;

    Random random = new Random();
    Boolean fight = false;

    public KeyStrokePrinter(ObjectDisplayGrid _grid, ArrayList<Room> _rooms, ArrayList<Player> _players,
            ArrayList<Item> _items) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = _grid;
        rooms = _rooms;
        players = _players;
        items = _items;
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);

            // System.out.println(players.get(0).posX + " " + players.get(0).posY);
        }
        inputQueue.add(ch);
    }

    private void rest(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean processInput() {
        char ch;
        boolean processing = true;
        int x = players.get(0).posX;
        int y = players.get(0).posY;

        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }
                // displayGrid.initializeInfo();

                if (ch == 'j') {
                    players.get(0).posY++;
                    if ((displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == ' ')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'X')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'T')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'S')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'H')) {
                        if ((displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'T')
                        || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'S')
                        || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'H')) {
                            displayGrid.fight();
                        }
                        players.get(0).posY--;
                    }
                } else if (ch == 'h') {
                    players.get(0).posX--;
                    if ((displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == ' ')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'X')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'T')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'S')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'H')) {
                        if ((displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'T')
                        || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'S')
                        || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'H')) {
                            displayGrid.fight();
                        }
                        players.get(0).posX++;
                    }
                } else if (ch == 'l') {
                    players.get(0).posX++;
                    if ((displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == ' ')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'X')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'T')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'S')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'H')) {
                        if ((displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'T')
                        || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'S')
                        || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'H')) {
                            displayGrid.fight();
                        }
                        players.get(0).posX--;
                    }
                }else if (ch == 'k') {
                    players.get(0).posY--;
                    if ((displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == ' ')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'X')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'T')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'S')
                    || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'H')) {
                        if ((displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'T')
                        || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'S')
                        || (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar() == 'H')) {
                            displayGrid.fight();
                        }
                        players.get(0).posY++;
                    }
                }else if(ch == 'p'){
                    // System.out.println("test: " + (displayGrid.objectGrid[players.get(0).posX + rooms.get(players.get(0).room - 1).posX][players.get(0).posY + rooms.get(players.get(0).room - 1).posY].getChar()));
                    displayGrid.pick();  
                }else if(ch == 'd'){
                    displayGrid.drop();
                }else if(ch == 'i'){
                    displayGrid.ShowPack();
                }else if(ch == 't'){
                    takeWeapon();
                }else if(ch == 'w'){
                    wearArmor();
                }else if(ch == 'c'){
                    if (players.get(0).armor != null) {
                        displayGrid.DisplayInfo("Take of " + players.get(0).armor.name, 0, 0);
                        players.get(0).armor = null;
                    } else{
                        displayGrid.DisplayInfo("No armor was worn", 0, 0);
                    }
                    displayGrid.initializeInfo();
                }
            }
            displayGrid.initializeDisplay();
        }
        return true;
    }

    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        boolean working = true;
        while (working) {
            rest(20);
            working = (processInput());
        }
    }

    boolean namecheck(String type, String name) {
        Pattern pattern = Pattern.compile(type, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    void takeWeapon() {
        int a;
        boolean processing = true;
        displayGrid.ShowPack();
        displayGrid.repaint();
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = true;
            } else {
                a = Character.getNumericValue(inputQueue.poll()) - 1;
                displayGrid.initializeInfo();
                if (a >= 0 && a < players.get(0).items.size()) {
                    if (!namecheck("sword", players.get(0).items.get(a).name))
                        displayGrid.DisplayInfo("Item is not a sword", 0, 0);
                    else {
                        displayGrid.DisplayInfo("Wield " + players.get(0).items.get(a).name, 0, 0);
                        players.get(0).sword = players.get(0).items.get(a);
                    }
                } else
                    displayGrid.DisplayInfo("There is no such input", 0, 0);
                processing = false;
            }
        }
    }

    void wearArmor() {
        int a;
        boolean processing = true;
        displayGrid.ShowPack();
        displayGrid.repaint();
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = true;
            } else {
                a = Character.getNumericValue(inputQueue.poll()) - 1;
                displayGrid.initializeInfo();
                if (a >= 0 && a < players.get(0).items.size()) {
                    if (!namecheck("armor", players.get(0).items.get(a).name))
                        displayGrid.DisplayInfo("Item is not a armor", 0, 0);
                    else {
                        displayGrid.DisplayInfo("Wear " + players.get(0).items.get(a).name, 0, 0);
                        players.get(0).armor = players.get(0).items.get(a);
                    }
                } else
                    displayGrid.DisplayInfo("There is no such input", 0, 0);
                processing = false;
            }
        }
    }

    // void pickup() {

    // for (int i = items.size() - 1; i >= 0; i--) {
    // if (items.get(i).posX == players.get(0).posX && items.get(i).posY ==
    // players.get(0).posY) {
    // displayGrid.DisplayInfo("Pick up " + items.get(i).name, 0, 0);
    // players.get(0).items.add(items.get(i));
    // items.remove(i);
    // break;
    // }
    // }
    // }

    // void drop() {
    // int a;
    // boolean processing = true;
    // displayGrid.ShowPack();
    // displayGrid.repaint();
    // while (processing) {
    // if (inputQueue.peek() == null) {
    // processing = true;
    // } else {
    // a = Character.getNumericValue(inputQueue.poll()) - 1;
    // displayGrid.initializeInfo();
    // if (a >= 0 && a < players.get(0).items.size()) {
    // displayGrid.DisplayInfo("drop " + players.get(0).items.get(a).name, 0, 0);
    // players.get(0).items.get(a).setPosX(players.get(0).posX);
    // players.get(0).items.get(a).setPosY(players.get(0).posY);
    // items.add(players.get(0).items.get(a));
    // players.get(0).items.remove(a);
    // } else
    // displayGrid.DisplayInfo("There is no such input", 0, 0);
    // processing = false;
    // }
    // }
    // }
}
