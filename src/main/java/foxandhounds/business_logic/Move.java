package foxandhounds.business_logic;

/** A Move is an operator. It is determined by a figure and a direction.
 * 
 * @author valyis
 */
public class Move {
    private Direction direction;
    private Figure mover;
    public Move(Figure m) {
        mover = m;
    }

    public Move(Figure m, Direction dir) {
        mover = m;
        direction = dir;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Figure getMover() {
        return mover;
    }

    public void setMover(Figure mover) {
        this.mover = mover;
    }

    @Override
    public String toString() {
        return "Move{" + "direction=" + direction + ", mover=" + mover + '}';
    }
    
}
