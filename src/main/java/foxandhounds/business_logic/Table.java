package foxandhounds.business_logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A table represents the actual state of the fox-and-houds game. Its
 * representation way is doubled with respect to the figures. One, it stores a
 * Fox and a list of hounds, second, it stores a matrix that stores the
 * positions as a character matrix. 'f' for fox, 'h' for hound, and ' ' for an
 * empty position. This representation is somehow redundant.
 * The list of possible moves of both kind of figures is also
 * stored for the following reason: the list is used not only while determining
 * the next step of the machine player (in a random way) but while determining
 * if the hounds party has won (there is no possible move for the fox, it is
 * locked, it cannot ecape even through the gutter).
 */
@XmlType(propOrder = {"tableSize", "matrix", "isFoxOnMove"})
public class Table {

    /**
     * tableSize is the size of the table, of course.
     */
    int tableSize;
    /**
     * There is exactly one fox on the field.
     */
    Fox fox;
    /**
     * There are some hounds on the field.
     */
    List<Hound> hounds = new ArrayList<>();
    /**
     * is the fox on the move?
     */
    @XmlElement
    Boolean isFoxOnMove;
    /**
     * stores a matrix that stores the positions as a character matrix. 'f' for
     * fox, 'h' for hound, and ' ' for an empty position.
     */
    @XmlElement
    Character[][] matrix;
    /**
     * The list of possible moves of both kind of figures is also stored for the
     * following reason: the list is used not only while determining the next
     * step of the machine player (in a random way) but while determining if the
     * hounds party has won (there is no possible move for the fox, it is
     * locked, it cannot escape even through the gutter).
     */
    List<Move> foxPossibleMoves = null;
    List<Move> houndsPossibleMoves = null;
    /**
     * this boolean is true if and only if the fox is winning.
     */
    Boolean isFoxWinning = null;

//    public Table() {
//        matrix = new Character[tableSize][tableSize];
//    }
    public void addFox(Fox newFox) {
        fox = newFox;
        matrix[fox.getRow()][fox.getCol()] = 'f';
    }

    public Fox getFox() {
        return fox;
    }

    public List<Hound> getHoundList() {

        return hounds;
    }

    public Hound getHound(int row, int col) {
        if (matrix[row][col] != 'h') {
            return null;
        } else {
            Hound answer = null;
            for (Hound h : hounds) {
                if (h.getRow() == row && h.getCol() == col) {
                    answer = h;
                }
            }
            return answer;
        }
    }

    public void addHound(Hound newHound) {
        hounds.add(newHound);
        matrix[newHound.getRow()][newHound.getCol()] = 'h';
    }

    public List<Move> getFoxPossibleMoves() {
        return foxPossibleMoves;
    }

    public List<Move> getHoundsPossibleMoves() {
        return houndsPossibleMoves;
    }

    public Boolean isFoxOnMOve() {
        return isFoxOnMove;
    }

    public void setFoxOnMove(boolean b) {
        isFoxOnMove = b;
    }

    public void setTableSize(int x) {
        tableSize = x;
    }

    @XmlElement
    public int getTableSize() {
        return tableSize;
    }

    /**
     * This method creates and returns the known starter state of the
     * fox-and-hounds game.
     *
     * @param size is the size
     * @return the starter table object
     */
    public static Table getStarterTable(int size) {

        if (size <= 3 || size > 12 || size % 2 != 0) {
            return null;
        }
        Table answer = new Table();
        answer.setTableSize(size);
        answer.matrix = new Character[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                answer.matrix[i][j] = ' ';
            }
        }
        answer.fox = new Fox(size - 1, 0);
        answer.matrix[size - 1][0] = 'f';
        answer.hounds = new ArrayList<>();
        for (int i = 0; i < size / 2; i++) {
            answer.hounds.add(new Hound(0, 2 * i + 1));
            answer.matrix[0][2 * i + 1] = 'h';
        }
        answer.isFoxOnMove = false;

        return answer;
    }

    /**
     * This method creates and returns the empty state of the fox-and-hounds
     * game.
     *
     * @param size is the size
     * @return the empty table object
     */
    public static Table getEmptyTable(int size) {

        if (size <= 3 || size > 12 || size % 2 != 0) {
            return null;
        }
        Table answer = new Table();
        answer.setTableSize(size);
        answer.matrix = new Character[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                answer.matrix[i][j] = ' ';
            }
        }
        answer.isFoxOnMove = false;
        return answer;

    }

    /**
     * This method determines the possible moves of the fox with respect to a
     * actual table. It don't return with null by no means,, at least with an
     * existing but empty list. It works according to the content of the matrix,
     * not the FOx and Hound objects.
     */
    public void determineFoxPossibleMoves() {
        foxPossibleMoves = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            int row = fox.getRow() + dir.getRowStep();
            int col = fox.getCol() + dir.getColStep();
            if (fits(row) && fits(col)
                    && matrix[row][col].equals(' ')) {
                foxPossibleMoves.add(new Move(fox, dir));
            }
        }

    }

    /**
     * This method determines the possible moves of the fox with respect to a
     * actual table. It don't return with null by no means,, at least with an
     * existing but empty list. It works according to the content of the matrix,
     * not the FOx and Hound objects.
     */
    public void determineHoundsPossibleMoves() {
        houndsPossibleMoves = new ArrayList<>();

        for (Hound hound : hounds) {
            for (Direction dir : Arrays.asList(Direction.SOUTHEAST, Direction.SOUTHWEST)) {
                if (fits(hound.getRow() + dir.getRowStep())
                        && fits(hound.getCol() + dir.getColStep())
                        && matrix[hound.getRow() + dir.getRowStep()][hound.getCol() + dir.getColStep()].equals(' ')) {
                    houndsPossibleMoves.add(new Move(hound, dir));
                }
            }
        }
    }

    /** This method is to determine if the hounds party is actually winning or not.
     * @return the truth value
    */
    public boolean winHounds() {
        //if (foxPossibleMoves == null) {
        determineFoxPossibleMoves();
        //}
        return !(getFox().getRow()<=0) && foxPossibleMoves.isEmpty();
    }

    /** This method is to determine if the fox party is actually winning or not.
     * As a side effect, it sets the value of isFOxWinning.
     * @return the truth value
    */
    public boolean winFox() {
        isFoxWinning = (getFox().getRow() <= 0);
        return isFoxWinning;
    }
    /** This method executes a move on this table. The possibility of the move is not checked.*/
    public void doMove(Move move) {
        Figure fig = move.getMover();
        int row = fig.getRow();
        int col = fig.getCol();
        fig.setRow(row + move.getDirection().getRowStep());
        fig.setCol(col + move.getDirection().getColStep());
        if (isFoxOnMove != null) {
            isFoxOnMove = !isFoxOnMove;
        }
        matrix[row][col] = ' ';
        if (fig instanceof Fox) {
            matrix[fig.getRow()][fig.getCol()] = 'f'; // these are the new values
        } else { // The mover is a hound
            matrix[fig.getRow()][fig.getCol()] = 'h'; // these are the new values
        }
        isFoxOnMove = !isFoxOnMove;
    }

    @Override
    public String toString() {
        int size = tableSize;
        char[][] tableAsCharArray = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                tableAsCharArray[row][col] = '0';
            }
        }
        tableAsCharArray[fox.getRow()][fox.getCol()] = 'f';
        for (Hound h : hounds) {
            tableAsCharArray[h.getRow()][h.getCol()] = 'h';
        }
        StringBuilder sb = new StringBuilder("[\n");
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sb.append(tableAsCharArray[row][col] + " ");
            }
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    /** This method choose a random hound move and executes it. 
     * If there is no possible hound move then
     * does nothing.
     */
        public void doARandomHoundMove() {
        determineHoundsPossibleMoves();
        int numberOfPossibleMoves = houndsPossibleMoves.size();
        if (numberOfPossibleMoves > 0) {
            int index = new Random().nextInt(numberOfPossibleMoves);
            doMove(houndsPossibleMoves.get(index));
        }

    }
    /** In this method the AI (namely, FoxAndHoundsGame) are called to determine the best move and then this method executes it. 
     * If there is no possible hound move then
     * does nothing.

        public void doAIMove(int depth) {
            FoxAndHoundsPosition aPosition = new FoxAndHoundsPosition();
            aPosition.setN(tableSize);
            aPosition.setMatrix(matrix);
            FoxAndHoundsGame fahg = new FoxAndHoundsGame();
            Vector v;
            if (isFoxOnMove == false) {v = fahg.alphaBeta(depth, aPosition, PROGRAM);}
            else {v = fahg.alphaBeta(depth, aPosition, HUMAN);}
            if (v == null || !(v.size()>=2)) return; //XXX //TODO ide nem kellene vmi excepiion???
            aPosition = (FoxAndHoundsPosition)v.elementAt(1); 
            Move actualMove = whatMoveWasDone(aPosition);
            doMove(actualMove);
    }
     */
        
        /**This method decides for an integer that it fits into the table indices, 
     * that is non-negative and less than the table size.
     * @param n an index
     * @return the condition if n it fits into the table indices
     */
    private boolean fits(int n) {
        return 0 <= n && n < tableSize;
    }

    /** This method choose a random hound move and executes it. 
     * If there is no possible hound move then
     * does nothing.
     */
    public void doARandomFoxMove() {
        determineFoxPossibleMoves();
        int numberOfPossibleMoves = foxPossibleMoves.size();
        if (numberOfPossibleMoves > 0) {
            int index = new Random().nextInt(numberOfPossibleMoves);
            doMove(foxPossibleMoves.get(index));
        }

    }

    /** This method determines what move was found as best by AI that resulted the given new Position.
    private Move whatMoveWasDone(FoxAndHoundsPosition newPosition) {
        Character[][] newMatrix = new Character[tableSize][tableSize];
        for (int row = 0; row < tableSize; row++)  for (int col = 0; col < tableSize; col++)  
                                {newMatrix[row][col] = newPosition.matrix[row][col];}
        if (!isFoxOnMove) {
            for (Hound hound : hounds) {
                for (Direction dir : Arrays.asList(Direction.SOUTHEAST, Direction.SOUTHWEST)) {
                    int row = hound.getRow(), col = hound.getCol();
                    if (newMatrix[row][col] == ' '
                            && fits(row + dir.getRowStep())
                            && fits(col + dir.getColStep())
                            && newMatrix[row + dir.getRowStep()][col + dir.getColStep()].equals('h')
                            && matrix[row + dir.getRowStep()][col + dir.getColStep()].equals(' ')) {
                        return (new Move(hound, dir));
                    }
                }
            }
        } else {
                for (Direction dir : Arrays.asList(Direction.values())) {
                    int row = fox.getRow(), col = fox.getCol();
                    if (newMatrix[row][col] == ' '
                            && fits(row + dir.getRowStep())
                            && fits(col + dir.getColStep())
                            && newMatrix[row + dir.getRowStep()][col + dir.getColStep()].equals('f')
                            && matrix[row + dir.getRowStep()][col + dir.getColStep()].equals(' ')) {
                        return (new Move(fox, dir));
                    }
                }
        }
        return null; // XXX this row cannot be reached while execution
    }
     */

    public void clear() {
        fox = null;
        hounds = new ArrayList<>();
        matrix = new Character[tableSize][tableSize];
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                matrix[i][j] = ' ';
            }
        }
        }

}
