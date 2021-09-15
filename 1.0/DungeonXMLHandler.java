import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// import jdk.internal.vm.compiler.word.SignedWord;

public class DungeonXMLHandler extends DefaultHandler{

    // the two lines that follow declare a DEBUG flag to control
    // debug print statements and to allow the class to be easily
    // printed out.  These are not necessary for the parser.
    private static final int DEBUG = 1;
    private static final String CLASSID = "DungeonXMLHandler";

    // data can be called anything, but it is the variables that
    // contains information found while parsing the xml file
    private StringBuilder data = null;

    // When the parser parses the file it will add references to
    // Student objects to this array so that it has a list of 
    // all specified students.  Had we covered containers at the
    // time I put this file on the web page I would have made this
    // an ArrayList of Students (ArrayList<Student>) and not needed
    // to keep tract of the length and maxStudents.  You should use
    // an ArrayList in your project.
    private Dungeon[] dungeons; //???

    private ObjectDisplayGrid[] objectDisplayGrids;
    private int maxobjectDisplayGrids = 0;
    private int objectDisplayGridCount = 0;

    private Room[] rooms;
    private int maxRooms = 0;
    private int roomCount = 0;

    private Passage[] passages;
    private int maxPassages = 0;
    private int passageCount = 0;

    private int bottomHeight = 0;
    private int gameHeight = 0;
    private int topHeight = 0;
    private int width = 0;
    private int dungeonName = 0;
    
    // The XML file contains a list of Students, and within each 
    // Student a list of activities (clubs and classes) that the
    // student participates in.  When the XML file initially
    // defines a student, many of the fields of the object have
    // not been filled in.  Additional lines in the XML file 
    // give the values of the fields.  Having access to the 
    // current Student and Activity allows setters on those 
    // objects to be called to initialize those fields.
    private Dungeon dungeonBeingParsed = null; //???

    private ObjectDisplayGrid objectDisplayGridBeingParsed = null;

    private Room roomBeingParsed = null;
    private Passage passageBeingParsed = null;
    
    private Monster monsterBeingParsed = null;
    private Player playerBeingParsed = null;
    private Armor armorBeingParsed = null;
    private Sword swordBeingParsed = null;
    private Scroll scrollBeingParsed = null;

    private CreatureAction creatureActionBeingParsed = null;
    private ItemAction itemActionBeingParsed = null;

    // The bX fields here indicate that at corresponding field is
    // having a value defined in the XML file.  In particular, a
    // line in the xml file might be:
    // <instructor>Brook Parke</instructor> 
    // The startElement method (below) is called when <instructor>
    // is seen, and there we would set bInstructor.  The endElement
    // method (below) is called when </instructor> is found, and
    // in that code we check if bInstructor is set.  If it is,
    // we can extract a string representing the instructor name 
    // from the data variable above.
    private boolean bVisible = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bWidth = false;
    private boolean bHeight = false;
    private boolean bActionMessage = false;
    private boolean bActionIntValue = false;
    private boolean bActionCharValue = false;
    private boolean bHp = false;
    private boolean bMaxHit = false;
    private boolean bHpMoves = false;
    private boolean bType = false;
    private boolean bItemIntValue = false;

    private boolean isRoom = false;
    private boolean isPassage = false;
    private boolean isPlayer = false;
    private boolean isMonster = false;
    private boolean isArmor = false;
    private boolean isSword = false;
    private boolean isScroll = false;
    private boolean isCreatureAction = false;
    private boolean isItemAction = false;    
    
    // Used by code outside the class to get the list of Student objects
    // that have been constructed.
    public Dungeon[] getDungeons(){ //???
        return dungeons;
    }

    public Room[] getrooms(){
        return rooms;
    }
    public Passage[] getpassages(){
        return passages;
    }

    // A constructor for this class.  It makes an implicit call to the
    // DefaultHandler zero arg constructor, which does the real work
    // DefaultHandler is defined in org.xml.sax.helpers.DefaultHandler;
    // imported above, and we don't need to write it.  We get its 
    // functionality by deriving from it!
    public DungeonXMLHandler(){
    }

    // startElement is called when a <some element> is called as part of 
    // <some element> ... </some element> start and end tags.
    // Rather than explain everything, look at the xml file in one screen
    // and the code below in another, and see how the different xml elements
    // are handled.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        if (qName.equalsIgnoreCase("Dungeon")) {
            bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            width = Integer.parseInt(attributes.getValue("width"));
            dungeonName = Integer.parseInt(attributes.getValue("name"));

            ObjectDisplayGrid objectDisplayGrid = new ObjectDisplayGrid();
            addObjectDisplayGrid(bottomHeight, gameHeight, width, topHeight);
            objectDisplayGridBeingParsed = objectDisplayGrid;
        } else if (qName.equalsIgnoreCase("Room")) {
            isRoom = true;
            String name = attributes.getValue("room");
            Room room = new Room(name);
            addRoom(room);
            // setID(name);
            roomBeingParsed = room;
        } else if (qName.equalsIgnoreCase("Passage")) {
            isPassage = true;
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            Passage passage = new Passage();
            addPassage(passage);
            // setID(room2, room1);
            passageBeingParsed = passage;
        } else if (qName.equalsIgnoreCase("Player")) {
            isPlayer = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Player player = new Player();
            // setName(name);
            // setID(room, serial);
            playerBeingParsed = player;
        } else if (qName.equalsIgnoreCase("Monster")) {
            isMonster = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster();
            // setName(name);
            // setID(room, serial);
            monsterBeingParsed = monster;
        } else if (qName.equalsIgnoreCase("Armor")) {
            isArmor = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(name);
            // setName(name);
            // setID(room, serial);
            armorBeingParsed = armor;
        } else if (qName.equalsIgnoreCase("Sword")) {
            isSword = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(name);
            // setID(room, serial);
            swordBeingParsed = sword;
        } else if (qName.equalsIgnoreCase("Scroll")) {
            isScroll = true;
            String name = attributes.getValue("name");
            int room = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(name);
            // setID(room, serial);
            scrollBeingParsed = scroll;
        } else if (qName.equalsIgnoreCase("CreatureAction")) {
            isCreatureAction = true;
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            CreatureAction  creatureAction = new CreatureAction(type);
            creatureActionBeingParsed = creatureAction;
        } else if (qName.equalsIgnoreCase("ItemAction")) {
            isItemAction = true;
            String name = attributes.getValue("name");
            int type = Integer.parseInt(attributes.getValue("type"));
            ItemAction itemAction = new ItemAction(type);
            itemActionBeingParsed = itemAction;
        } else if (qName.equalsIgnoreCase("visible")) {
            bVisible = true;
        } else if (qName.equalsIgnoreCase("posX")) {
            bPosX = true;
        } else if (qName.equalsIgnoreCase("posY")) {
            bPosY = true;
        } else if (qName.equalsIgnoreCase("width")) {
            bWidth = true;
        } else if (qName.equalsIgnoreCase("height")) {
            bHeight = true;
        } else if (qName.equalsIgnoreCase("hp")) {
            bHp = true;
        } else if (qName.equalsIgnoreCase("maxhit")) {
            bMaxHit = true;
        } else if (qName.equalsIgnoreCase("hpMoves")) {
            bHpMoves = true;
        } else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        } else if (qName.equalsIgnoreCase("ItemIntValue")) {
            bItemIntValue = true;
        } else if (qName.equalsIgnoreCase("actionMessage")) {
            bActionMessage = true;
        } else if (qName.equalsIgnoreCase("actionIntValue")) {
            bActionIntValue = true;
        } else if (qName.equalsIgnoreCase("actionCharValue")) {
            bActionCharValue = true;
        } else {
            System.out.println("Unknown qname: " + qName);
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(isRoom){
            if(isPlayer){
                if(isSword){
                    if(bVisible){
                        Sword.setVisible(Integer.parseint(data.toString()));
                        bVisible = false;
                    }else if(bPosX){
                        Sword.setPosX(Integer.parseint(data.toString()));
                        bPosX = false;
                    }else if(bPosY){
                        Sword.setPosY(Integer.parseint(data.toString()));
                        bPosY = false;
                    }else if(bItemIntValue){
                        Sword.setIntValue(Integer.parseint(data.toString()));
                        bItemIntValue = false;
                    }
                }else if(isArmor){
                    if(bVisible){
                        Armor.setVisible(Integer.parseint(data.toString()));
                        bVisible = false;
                    }else if(bPosX){
                        Armor.setPosX(Integer.parseint(data.toString()));
                        bPosX = false;
                    }else if(bPosY){
                        Armor.setPosY(Integer.parseint(data.toString()));
                        bPosY = false;
                    }else if(bItemIntValue){
                        Armor.setIntValue(Integer.parseint(data.toString()));
                        bItemIntValue = false;
                    }
                }else if(isCreatureAction){
                    if(bActionMessage){
                        CreatureAction.setMessage(data.toString());
                        bActionMessage = false;
                    }else if(bActionIntValue){
                        CreatureAction.setIntValue(Integer.parseint(data.toString()));
                        bActionIntValue = false;
                    }else if(bActionCharValue){
                        CreatureAction.setCharValue(data.toString());
                        bActionCharValue = false;
                    }
                }else if(bVisible){
                    Player.setVisible(Integer.parseint(data.toString()));
                    bVisible = false;
                }else if(bPosX){
                    Player.setPosX(Integer.parseint(data.toString()));
                    bPosX = false;
                }else if(bPosY){
                    Player.setPosY(Integer.parseint(data.toString()));
                    bPosY = false;
                }else if(bHp){
                    Player.setHp(Integer.parseint(data.toString()));
                    bHp = false;
                }else if(bMaxHit){
                    Player.setMaxHit(Integer.parseint(data.toString()));
                    bMaxHit = false;
                }else if(bHpMoves){
                    Player.setHpMove(Integer.parseint(data.toString()));
                    bHpMoves = false;
                }
            }else if(isMonster){
                if(isCreatureAction){
                    if(bActionMessage){
                        CreatureAction.setMessage(data.toString());
                        bActionMessage = false;
                    }else if(bActionIntValue){
                        CreatureAction.setIntValue(Integer.parseint(data.toString()));
                        bActionIntValue = false;
                    }else if(bActionCharValue){
                        CreatureAction.setCharValue(data.toString());
                        bActionCharValue = false;
                    }
                }else if(bVisible){
                    Monster.setVisible(Integer.parseint(data.toString()));
                    bVisible = false;
                }else if(bPosX){
                    Monster.setPosX(Integer.parseint(data.toString()));
                    bPosX = false;
                }else if(bPosY){
                    Monster.setPosY(Integer.parseint(data.toString()));
                    bPosY = false;
                }else if(bHp){
                    Monster.setHp(Integer.parseint(data.toString()));
                    bHp = false;
                }else if(bMaxHit){
                    Monster.setMaxHit(Integer.parseint(data.toString()));
                    bMaxHit = false;
                }else if(bHpMoves){
                    Monster.setHpMove(Integer.parseint(data.toString()));
                    bHpMoves = false;
                }else if(bType){
                    Monster.setType(data.toString());
                    bType = false;
                }
            }else if(isArmor){
                if(bVisible){
                    Armor.setVisible(Integer.parseint(data.toString()));
                    bVisible = false;
                }else if(bPosX){
                    Armor.setPosX(Integer.parseint(data.toString()));
                    bPosX = false;
                }else if(bPosY){
                    Armor.setPosY(Integer.parseint(data.toString()));
                    bPosY = false;
                }else if(bItemIntValue){
                    Armor.setIntValue(Integer.parseint(data.toString()));
                    bItemIntValue = false;
                }
            }else if(isSword){
                if(bVisible){
                    Sword.setVisible(Integer.parseint(data.toString()));
                    bVisible = false;
                }else if(bPosX){
                    Sword.setPosX(Integer.parseint(data.toString()));
                    bPosX = false;
                }else if(bPosY){
                    Sword.setPosY(Integer.parseint(data.toString()));
                    bPosY = false;
                }else if(bItemIntValue){
                    Sword.setIntValue(Integer.parseint(data.toString()));
                    bItemIntValue = false;
                }
            }else if(isScroll){
                if(isItemAction){
                    if(bActionMessage){
                        Scroll.setMessage(data.toString());
                        bActionMessage = false;
                    }else if(bActionIntValue){
                        Scroll.setIntValue(Integer.parseint(data.toString()));
                        bActionIntValue = false;
                    }else if(bActionCharValue){
                        Scroll.setCharValue(data.toString());
                        bActionCharValue = false;
                    }
                }else if(bVisible){
                    Scroll.setVisible(Integer.parseint(data.toString()));
                    bVisible = false;
                }else if(bPosX){
                    Scroll.setPosX(Integer.parseint(data.toString()));
                    bPosX = false;
                }else if(bPosY){
                    Scroll.setPosY(Integer.parseint(data.toString()));
                    bPosY = false;
                }
            }
        }else if(isPassage){
            if(bVisible){
                Passage.setVisible(Integer.parseint(data.toString()));
                bVisible = false;
            }else if(bPosX){
                Passage.setPosX(Integer.parseint(data.toString()));
                bPosX = false;
            }else if(bPosY){
                Passage.setPosY(Integer.parseint(data.toString()));
                bPosY = false;
            }
        }

        if (qName.equalsIgnoreCase("Rooms")) {
            if (roomCount != maxRooms) {
                System.out.println("wrong number of rooms parsed, should be " + maxRooms + ", is " + roomCount);
            }
        } else if (qName.equalsIgnoreCase("Armor")) {
            roomBeingParsed = null;
            isArmor = false;
        } else if (qName.equalsIgnoreCase("Sword")) {
            roomBeingParsed = null;
            isSword = false;
        } else if (qName.equalsIgnoreCase("Scroll")) {
            roomBeingParsed = null;
            isScroll = false;
            isItemAction = false;
        } else if (qName.equalsIgnoreCase("Player")) {
            roomBeingParsed = null;
            isPlayer = false;
            isArmor = false;
            isSword = false;
            isCreatureAction = false;
        } else if (qName.equalsIgnoreCase("Monster")) {
            roomBeingParsed = null;
            isMonster = false;
            isCreatureAction = false;
        } else if (qName.equalsIgnoreCase("Passage")) {
            passageBeingParsed = null;
        }
    }

    private void addobjectDisplayGrid(ObjectDisplayGrid objectDisplayGrid) {
        objectDisplayGrids[objectDisplayGrid++] = objectDisplayGrid;
    }

    private void addRoom(Room room) {
        rooms[roomCount++] = room;
    }

    private void addPassage(Passage passage) {
        passages[passageCount++] = passage;
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }

    @Override
    public String toString() {
        String str = "DungeonXMLHandler\n";
        str += "   bottomHeight: " + bottomHeight + "\n";
        str += "   gameHeight: " + gameHeight + "\n";
        str += "   width: " + width + "\n";
        str += "   topHeight: " + topHeight + "\n";
        for (Dungeon dungeon : dungeons) {
            str += dungeon.toString() + "\n";
        }
        str += "   dungeonBeingParsed: " + dungeonBeingParsed.toString() + "\n";
        str += "   objestDisplayGridBeingParsed: " + objestDisplayGridBeingParsed.toString() + "\n";
        str += "   roomBeingParsed: " + roomBeingParsed.toString() + "\n";
        str += "   passageBeingParsed: " + passageBeingParsed.toString() + "\n";
        str += "   monsterBeingParsed: " + monsterBeingParsed.toString() + "\n";
        str += "   playerBeingParsed: " + playerBeingParsed.toString() + "\n";
        str += "   armorBeingParsed: " + armorBeingParsed.toString() + "\n";
        str += "   swordBeingParsed: " + swordBeingParsed.toString() + "\n";
        str += "   scrollBeingParsed: " + scrollBeingParsed.toString() + "\n";
        str += "   creatureActionBeingParsed: " + creatureActionBeingParsed.toString() + "\n";
        str += "   itemActionBeingParsed: " + itemActionBeingParsed.toString() + "\n";
        
        str += "   bVisible: " + bVisible + "\n";
        str += "   bPosX: " + bPosX + "\n";
        str += "   bPosY: " + bPosY + "\n";
        str += "   bWidth: " + bWidth + "\n";
        str += "   bHeight: " + bHeight + "\n";
        str += "   bActionMessage: " + bActionMessage + "\n";
        str += "   bActionIntValue: " + bActionIntValue + "\n";
        str += "   bActionCharValue: " + bActionCharValue + "\n";
        str += "   bHp: " + bHp + "\n";
        str += "   bMaxHit: " + bMaxHit + "\n";
        str += "   bHpMoves: " + bHpMoves + "\n";
        str += "   bType: " + bType + "\n";
        str += "   bItemIntValue: " + bItemIntValue + "\n";
        return str;
    }
}