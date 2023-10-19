package foxandhounds.business_logic;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A hound is a figure on the table.
 * It has two coordinates, one for row  and one for column.
 * It can take only forwards steps.
 */

@XmlType
public class Hound implements Figure {
    private int row,
            col;

    public Hound(int p_row, int p_col) {
        col = p_col;
        row = p_row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int p_col) {
        this.col = p_col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int p_row) {this.row = p_row;}

    @Override
    public String toString() {
        return "Hound{" + "row=" + row + ", col=" + col + '}';
    }

}
