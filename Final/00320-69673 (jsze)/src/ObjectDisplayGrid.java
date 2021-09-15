import asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    public Char[][] objectGrid = null;

    private List<InputObserver> inputObservers = null;

    private static int height;
    private static int width;
    private static Dungeon dungeon;
    private static ArrayList<Room> rooms = new ArrayList<Room>();
    private static ArrayList<Passage> passages = new ArrayList<Passage>();
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<Monster> monsters = new ArrayList<Monster>();
    private static ArrayList<Item> items = new ArrayList<Item>();

    Random rand = new Random();
    private boolean exit = false;
    public boolean fight = false;

    public ObjectDisplayGrid(int _width, int _height, Dungeon _dungeon, ArrayList<Room> _rooms,
            ArrayList<Passage> _passages, ArrayList<Player> _players, ArrayList<Monster> _monsters,
            ArrayList<Item> _items) {
        width = _width;
        height = _height;
        dungeon = _dungeon;
        rooms = _rooms;
        passages = _passages;
        players = _players;
        monsters = _monsters;
        items = _items;

        // System.out.println("test: " + rooms.get(0).posX);
        // System.out.println("test item: " + items.get(0).posX);
        // System.out.println(width);
        // System.out.println(height);
        // System.out.println(dungeon.bottomHeight);
        // System.out.println(rooms);

        // System.out.println(players.size());
        // System.out.println(monsters.size());
        // System.out.println(items.size());

        // for(int s=0; s<items.size(); s++){
        // System.out.println("item X,Y: " + items.get(s).posX + "," +
        // items.get(s).posY);
        // //how to print out type eg: sword, armor, scroll?
        // //how to make set the position of item on player?
        // }

        // for(int s=0; s<monsters.size(); s++){
        // System.out.println("monster X,Y: " + monsters.get(s).posX + "," +
        // monsters.get(s).posY);
        // }

        // for(int s=0; s<players.size(); s++){
        // System.out.println("player X,Y: " + players.get(s).posX + ","
        // +players.get(s).posY);
        // }

        terminal = new AsciiPanel(width, height);

        objectGrid = new Char[width][height];

        Char ch = new Char(' ');
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                addObjectToDisplay(ch, i, j);
            }
        }

        initializeDisplay();

        super.add(terminal);
        super.setSize(width * 9, height * 16);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.repaint();
        // terminal.repaint( );
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    public void setObjectDisplayGrid(int bottomHeight, int gameHeight, int topHeight, int width, String name) {
        System.out.println("set object display grid: bottom height: " + bottomHeight + ", game height: " + gameHeight
                + ", top height: " + topHeight + ", width: " + width + ", name: " + name);

    }

    public void getObjectDisplayGrid(int bottomHeight, int gameHeight, int topHeight, int width, String name) {
        System.out.println("get object display grid: bottom height: " + bottomHeight + ", game height: " + gameHeight
                + ", top height: " + topHeight + ", width: " + width + ", name: " + name);

    }

    public void setTopMessageHeight(int topHeight) {
        System.out.println("set top message height: " + topHeight);

    }

    public void setBottomMessageHeight(int bottomHeight) {
        System.out.println("set bottom message height: " + bottomHeight);

    }

    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        Char ch = new Char(' ');
        // for (int i = 0; i < width; i++) {
        // for (int j = 0; j < height; j++) {
        // addObjectToDisplay(ch, i, j);
        // }
        // }

        // terminal.repaint();

        for (int s = 0; s < rooms.size(); s++) {
            for (int i = rooms.get(s).posX; i < (rooms.get(s).posX + rooms.get(s).width); i++) {
                for (int j = rooms.get(s).posY; j < (rooms.get(s).posY + rooms.get(s).height); j++) {
                    if ((i == rooms.get(s).posX) || (i == (rooms.get(s).posX + rooms.get(s).width - 1))
                            || (j == rooms.get(s).posY) || (j == (rooms.get(s).posY + rooms.get(s).height - 1))) {
                        ch = new Char('X');
                        addObjectToDisplay(ch, i, j);
                    } else {
                        ch = new Char('.');
                        addObjectToDisplay(ch, i, j);
                    }
                }
            }

            for (int s1 = 0; s1 < items.size(); s1++) {
                if (items.get(s1).type == ')') {
                    ch = new Char(')');
                } else if (items.get(s1).type == ']') {
                    ch = new Char(']');
                } else if (items.get(s1).type == '?') {
                    ch = new Char('?');
                }
                if (s == (items.get(s1).room)) {
                    addObjectToDisplay(ch, (items.get(s1).posX + rooms.get(s).posX),
                            (items.get(s1).posY + rooms.get(s).posY));
                }
                // how to make set the position of item on player?
            }

            for (int s1 = 0; s1 < monsters.size(); s1++) {
                if (monsters.get(s1).type == 'T') {
                    ch = new Char('T');
                } else if (monsters.get(s1).type == 'S') {
                    ch = new Char('S');
                } else if (monsters.get(s1).type == 'H') {
                    ch = new Char('H');
                }
                if (s == (monsters.get(s1).room) - 1) {
                    addObjectToDisplay(ch, (monsters.get(s1).posX + rooms.get(s).posX),
                            (monsters.get(s1).posY + rooms.get(s).posY));
                }
            }

            for (int s1 = 0; s1 < players.size(); s1++) {
                ch = new Char('@');
                if (s == (players.get(s1).room) - 1) {
                    addObjectToDisplay(ch, (players.get(s1).posX + rooms.get(s).posX),
                            (players.get(s1).posY + rooms.get(s).posY));

                }
            }
        }

        for (int i = 0; i < passages.size(); i++) {
            for (int j = 0; j < passages.get(i).xAxis.size() - 1; j++) {
                int left = min(passages.get(i).xAxis.get(j), passages.get(i).xAxis.get(j + 1));
                int right = max(passages.get(i).xAxis.get(j), passages.get(i).xAxis.get(j + 1));
                int up = max(passages.get(i).yAxis.get(j), passages.get(i).yAxis.get(j + 1));
                int down = min(passages.get(i).yAxis.get(j), passages.get(i).yAxis.get(j + 1));
                for (int x = left; x <= right; x++) {
                    for (int y = down; y <= up; y++) {
                        if (objectGrid[x][y].getChar() == 'X') {
                            addObjectToDisplay(new Char('+'), x, y);
                        } else {
                            addObjectToDisplay(new Char('#'), x, y);
                        }
                    }
                }
            }
        }
        for (int s = 0; s < rooms.size(); s++) {
            for (int s1 = 0; s1 < players.size(); s1++) {
                ch = new Char('@');
                if (s == (players.get(s1).room) - 1) {
                    addObjectToDisplay(ch, (players.get(s1).posX + rooms.get(s).posX),
                            (players.get(s1).posY + rooms.get(s).posY));
                }
            }
        }
        terminal.repaint();
    }

    boolean fight() {
        int dmg = rand.nextInt(players.get(0).maxHit + 1);
        int i;
        initializeInfo();
        for (i = 0; i < monsters.size(); i++)
            if ((monsters.get(i).posX + rooms.get(monsters.get(i).room-1).posX == players.get(0).posX + rooms.get(players.get(0).room-1).posX)
            && (monsters.get(i).posY + rooms.get(monsters.get(i).room-1).posY == players.get(0).posY + rooms.get(players.get(0).room-1).posY)){
                break;
            }
        monsters.get(i).hp -= dmg;
        DisplayInfo("You dealt " + dmg + " dmg to " + monsters.get(i).name, 0, 0);
        if (monsters.get(i).hp <= 0) {
            DisplayInfo(monsters.get(i).GetAction("YouWin"), 0, 1);
            monsters.remove(i);
            return false;
        }
        dmg = rand.nextInt(monsters.get(i).maxHit + 1);
        DisplayInfo(monsters.get(i).name + " dealt " + dmg + " dmg to you", 0, 1);
        players.get(0).hp -= dmg;
        if (players.get(0).hp <= 0) {
            DisplayInfo(players.get(0).GetAction("YouWin"), 0, 2);
            exit = true;
        }
        if (players.get(0).hp <= 0) {
            DisplayInfo(players.get(0).GetAction("EndGame"), 0, 2);
            addObjectToDisplay(new Char('+'), players.get(0).posX, players.get(0).posY);
            exit = true;
        }
        return true;
    }

    void pick(){
        int i = 0;
        
        for (i = 0; i < items.size(); i++){
            if ((items.get(i).posX + rooms.get(items.get(i).room).posX == players.get(0).posX + rooms.get(players.get(0).room-1).posX)
            && (items.get(i).posY + rooms.get(items.get(i).room).posY == players.get(0).posY + rooms.get(players.get(0).room-1).posY)){
                players.get(0).addItem(items.get(i));
                items.remove(i);
                break;
            }
        }
        ShowPack();
    }

    void drop(){
        if(players.get(0).items.size() > 0){
            players.get(0).items.get(players.get(0).items.size()-1).posX = players.get(0).posX + rooms.get(players.get(0).room-1).posX;
            players.get(0).items.get(players.get(0).items.size()-1).posY = players.get(0).posY + rooms.get(players.get(0).room-1).posY;

            items.add(players.get(0).items.get(players.get(0).items.size()-1));
            players.get(0).items.remove(players.get(0).items.size()-1);
        }
        
        initializeInfo();
        ShowPack();
    }

    void ShowPack() {
        int y = 0;
        int x = 11;
        DisplayInfo("Inventory :", 0, y);
        for (int i = 0; i < players.get(0).items.size(); i++) {
            if (x + players.get(0).items.get(i).name.length() + 3 > width) {
                x = 11;
                y++;
            }
            if (players.get(0).items.get(i) == players.get(0).armor) {
                DisplayInfo(" " + (i + 1) + ")" + players.get(0).items.get(i).name + "(a)", x, y);
                if (KeyStrokePrinter.scroll)
                    DisplayInfo(" " + (i + 1) + ")" + "+0 Armor", x, y);
                x += players.get(0).items.get(i).name.length() + 3 + 4;
                continue;
            }
            if (players.get(0).items.get(i) == players.get(0).sword) {
                DisplayInfo(" " + (i + 1) + ")" + players.get(0).items.get(i).name + "(w)", x, y);
                x += players.get(0).items.get(i).name.length() + 3 + 4;
                continue;
            }
            DisplayInfo(" " + (i + 1) + ")" + players.get(0).items.get(i).name, x, y);
            x += players.get(0).items.get(i).name.length() + 3;
        }
        
    }
    

    void initializeInfo() {
        Char ch = new Char(' ');
        for (int i = 0; i < width; i++) {
            for (int j = dungeon.gameHeight; j < height; j++) {
                addObjectToDisplay(ch, i, j);
            }
        }
    }

    void DisplayInfo(String str, int x, int y) {
        for (int i = 0; i < str.length(); i++) {
            addObjectToDisplay(new Char(str.charAt(i)), x + i, y + dungeon.gameHeight);
        }
        terminal.repaint();
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Char ch, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y] = ch;
                writeToTerminal(x, y);
            }
        }
    }

    private void writeToTerminal(int x, int y) {
        char ch = objectGrid[x][y].getChar();
        terminal.write(ch, x, y);
        // terminal.repaint();
    }

    int max(int x, int y) {
        if (x > y)
            return x;
        else
            return y;
    }

    int min(int x, int y) {
        if (x < y)
            return x;
        else
            return y;
    }
}
