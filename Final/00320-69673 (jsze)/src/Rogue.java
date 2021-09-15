import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import java.io.*;
import java.util.*;

public class Rogue implements Runnable {
    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;

    private static Dungeon dungeon = new Dungeon();
    private static ArrayList<Room> rooms = new ArrayList<Room>();
    private static ArrayList<Passage> passages = new ArrayList<Passage>();
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static ArrayList<Monster> monsters = new ArrayList<Monster>();
    private static ArrayList<Item> items = new ArrayList<Item>();

    public Rogue(int width, int height) {
        displayGrid = new ObjectDisplayGrid(width, height, dungeon, rooms, passages, players, monsters, items);
    }

    @Override
    public void run() {
        // displayGrid.fireUp();

        // for (int step = 1; step < WIDTH / 2; step *= 2) {
        //     for (int i = 0; i < WIDTH; i += step) {
        //         for (int j = 0; j < HEIGHT; j += step) {
        //             displayGrid.addObjectToDisplay(new Char('X'), i, j);
        //         }
        //     }
            
        //     try {
        //         Thread.sleep(2000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace(System.err);
        //     }
        //     displayGrid.initializeDisplay();
        // }
    }

    public static void main(String[] args) throws Exception {
        // check if a filename is passed in.  If not, print a usage message.
	    // If it is, open the file
        String fileName = null;
        switch (args.length) {
            case 1:
                // note that the relative file path may depend on what IDE you are
                // using.  This worked for NetBeans.
                fileName = "xmlFiles/" + args[0];
                break;
            default:
                System.out.println("java Rogue <xmlfilename>");
            return;
        }

        // Create a saxParserFactory, that will allow use to create a parser
        // Use this line unchanged
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        // We haven't covered exceptions, so just copy the try { } catch {...}
        // exactly, // filling in what needs to be changed between the open and 
        // closed braces.
        try {
	        // just copy this
            SAXParser saxParser = saxParserFactory.newSAXParser();
	        // just copy this
            DungeonXMLHandler handler = new DungeonXMLHandler();
	        // just copy this.  This will parse the xml file given by fileName
            saxParser.parse(new File(fileName), handler);
	        // This will change depending on what kind of XML we are parsing
            dungeon = handler.getDungeon();
            rooms = handler.getRooms();
            passages = handler.getPassages();
            players = handler.getPlayers();
            monsters = handler.getMonsters();
            items = handler.getItems();

            System.out.println(rooms);

	    // these lines should be copied exactly.
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }

        Rogue rogue = new Rogue(WIDTH, HEIGHT);
        Thread rogueThread = new Thread(rogue);
        rogueThread.start();

        rogue.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid, rooms, players, items));
        rogue.keyStrokePrinter.start();

        rogueThread.join();
        rogue.keyStrokePrinter.join();
    }
}