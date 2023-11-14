
package foxandhounds.play_on_terminal;

import foxandhounds.business_logic.Direction;
import foxandhounds.business_logic.Fox;
import foxandhounds.business_logic.Hound;
import foxandhounds.business_logic.Move;
import foxandhounds.business_logic.Table;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author valyis
 */
public class PlayOnTerminal {

    static final Logger logger = Logger.getLogger(PlayOnTerminal.class.getName());
    static int size = -1;
    static Table table = null;
    static Boolean humanPlaysWithFox = null; 
    static Scanner scanner = null;

    
    public static void main(String[] a) {
            loadASavedGame();
            readInSize();
            editing();
            System.out.println("The edited table is: "+table);
            readInSide();
            playing();
            saveThePlayedGame();
        }


    public static void loadASavedGame() {}


    public static void readInSize() {
        scanner = new Scanner(System.in);
        System.out.print("Give the size of the table as an even integer between 4 and 12: ");
        String read = null;
        while (size <= 0) {
            try {
                read = scanner.nextLine();
                size = Integer.parseInt(read);
            } catch (NumberFormatException ex) {
                logger.warning("not integer is given as size");
                System.out.print("Give integer as size \n ");
            }
            if (!(size <= 12 && size >= 4 && size % 2 == 0)) {
                System.out.print("\nOnce again give the size of the table as an even integer between 4 and 12: ");
            }
            System.out.print("Ok, the size of the table: " + size + "\n");
        }
    }


    public static void readInSide() {
            scanner = new Scanner(System.in);
            String read = null;
            System.out.print("\nWould you lead fox (f) or hounds (h) or will you exit (x)?: ");
            boolean player_determined = false;
            read = null;
            while (!player_determined) {
                String line = scanner.nextLine();
                char c = line.charAt(0);
                if (Arrays.asList('f','h','x','F','H','X').contains(c)) {
                    player_determined=true;
                    switch (Character.toLowerCase(c)) {
                        case ('f'): humanPlaysWithFox = true;
                            System.out.println("You will play with the fox");
                            break;
                        case ('h'): humanPlaysWithFox = false;
                            System.out.println("You will play with the hounds");
                            break;
                        case ('x'): humanPlaysWithFox = null;
                            System.exit(0);
                            break;
                    }
                } else {
                    System.out.println("Once again, would you lead fox (f) or hounds (h) or will you exit (x)?: ");

                }

            }
    }



    public static void editing() {
        Table table = Table.getStarterTable(size);
        System.out.println("Editing happens now from empty table.\n"+
                "How do you wish the editing? From empty table (e)\n"+
                "or from the starter table by steps(s) "+"or with a random table? (r)\n"+"x -exit(x)"+
                "random does not work yet");
        Character userInput = ' ';
        scanner = new Scanner(System.in);
        while (!Arrays.asList('e','s','r','x').contains(Character.toLowerCase(userInput))) {
            System.out.println("e/s/r/x: ");
            userInput = scanner.nextLine().charAt(0);
        }
        switch (Character.toLowerCase(userInput)) {

            case 'e': {
                editingFromEmptyTable(); break;
            } 
            case 's': {
                editingFromStartingPosition(); break;
            }
            case 'r': {
                setRandomTable();
            }
            default: System.exit(0);
                
            }
        return;
        }
    
    public static void editingFromEmptyTable() {
        scanner = new Scanner(System.in);
        table = Table.getEmptyTable(size);  
        System.out.println("Input row and column coordinates of the fox  indexed starting with 0: ");
        String line = scanner.nextLine();
        Scanner lineScanner = new Scanner(line);
        int row = Integer.parseInt(lineScanner.next());
        int col= Integer.parseInt(lineScanner.next());
        table.addFox(new Fox(row,col));
        for (int i=1; i<=size/2; i++) {
            System.out.println("Input the "+i+". hounds row and column positions  indexed starting with 0: ");
            line = scanner.nextLine();
            lineScanner = new Scanner(line);
            row = Integer.parseInt(lineScanner.next());
            col= Integer.parseInt(lineScanner.next());
            table.addHound(new Hound(row,col));
        }
        return;
    }

    public static void editingFromStartingPosition() {
        scanner = new Scanner(System.in);
        boolean exit = false;
        Character c;
        Hound activeHound = null;
        table = Table.getStarterTable(size);
        System.out.println("THe starting table: "+table);
        c = 'a';
        while (Arrays.asList('a','d','q','e','x').contains(Character.toLowerCase(c))) {
        System.out.println("GIve a step of the fox: a/d/q/e, p - out from editing");
        c = scanner.nextLine().charAt(0);
        switch (Character.toLowerCase(c)) {
            case 'a': table.doMove(new Move(table.getFox(),Direction.SOUTHWEST)); break;
            case 'd': table.doMove(new Move(table.getFox(),Direction.SOUTHEAST)); break;
            case 'q': table.doMove(new Move(table.getFox(),Direction.NORTHWEST)); break;
            case 'e': table.doMove(new Move(table.getFox(),Direction.NORTHEAST)); break;
            case 'p': exit = true; break;
            default: break;
            }
        if (exit) return;
        System.out.println("THe table is now: "+table); //DEBUG

        boolean isHoundFound = false;
        while (!isHoundFound) {
        System.out.println("Which hound is to move? A row and a column should be given: ");
        int y = scanner.nextInt(); int x = scanner.nextInt();
        activeHound = table.getHound(y, x);
        if (activeHound == null)  {
            System.out.println("THere is no hound.");
            } else isHoundFound=true;
        }
        System.out.println("What is the direction of the move?: a/d/q/e, p - out from editing");
        
        scanner.nextLine();
        c = scanner.nextLine().charAt(0);
        switch (Character.toLowerCase(c)) {
            case 'a': table.doMove(new Move(activeHound,Direction.SOUTHWEST)); break;
            case 'd': table.doMove(new Move(activeHound,Direction.SOUTHEAST)); break;
            case 'q': table.doMove(new Move(activeHound,Direction.NORTHWEST)); break;
            case 'e': table.doMove(new Move(activeHound,Direction.NORTHEAST)); break;
            case 'p': exit = true; break;
            default: break;
            }
        if (exit) return;
        System.out.println("The table is edited by moves:  "+table); //DEBUG
        }
    }

    private static void setRandomTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    
               
    public static void playing() {
        System.out.println("The starting table is: ");
        if (humanPlaysWithFox) {
            playWithFox();
        
        }
        else {
            playWithHounds();
        }
        
    }
    
    private static Direction directionByChar(Character c){
        switch (Character.toLowerCase(c.charValue())  ) { 
            case 'a': return Direction.SOUTHWEST;
            case 'q': return Direction.NORTHWEST;
            case 'd': return Direction.SOUTHEAST;
            case 'e': return Direction.NORTHEAST;
            default: return null;
        }
    }

    public static void playWithFox() {
        char c = ' ';
        scanner = new Scanner(System.in);
        while (true) {
            if (table.winFox()) {System.out.println("You won"); System.exit(0); return;}
            if (table.winHounds()) {System.out.println("You loose"); System.exit(0); return; }
            System.out.print("\nYour move: a/d/q/e, exit: x ");
            String s = scanner.nextLine();
            c = Character.toLowerCase(s.charAt(0));
            while(!Arrays.asList('a','d','q','e','x').contains(c )) {}
            if (c == 'x') System.exit(0);
            table.doMove(new Move(table.getFox(),directionByChar(c)));
            table.doARandomHoundMove();
            System.out.println("The table is now: "+table);
        }
    }

    private static void playWithHounds() {
        scanner = new Scanner(System.in);
        char c = ' ';
        Hound activeHound = null;
        boolean exit = false;
        while (true) {
            if (table.winFox()) {System.out.println("You loose"); return;}
            if (table.winHounds()) {System.out.println("You win"); return; }
            boolean isHoundFound = false;
            while (!isHoundFound) {
                System.out.println("Which hound is to move? A row and a column should be given: (exit: -1)");
                int y = scanner.nextInt(); int x = scanner.nextInt();
                if (y == -1) System.exit(0);
                activeHound = table.getHound(y, x);
                if (activeHound == null)  {
                    System.out.println("THere is no hound.");
                    } else isHoundFound=true;
                }
        System.out.println("Give a step of that hound: a/d, x - exit");
        
        scanner.nextLine(); // a nextInt() után beveszi az újsor-karaktert
        c = scanner.nextLine().charAt(0);
        switch (Character.toLowerCase(c)) {
            case 'a': table.doMove(new Move(activeHound,Direction.SOUTHWEST)); break;
            case 'd': table.doMove(new Move(activeHound,Direction.SOUTHEAST)); break;
            case 'x': exit = true; break;
            default: break;
            }
        if (exit) return;
        table.doARandomFoxMove();
        System.out.println("The table is now: "+table); //DEBUG
        }
    }

    private static void saveThePlayedGame() {
    }


}
    

