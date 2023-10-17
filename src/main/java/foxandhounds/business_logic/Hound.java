package foxandhounds.business_logic;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * A hound is a figure on the table. 
 * It has two coordinates, one for row (y) and one for column (x).
 * It can take only forwards steps.
 */

@XmlType
public class Hound implements Figure {
    private int row,
            col;
    public Hound(int r, int c) 
    {
        col = c;  row = r;
    }
       
    public int getCol() {
        return col ;
    }

    public void setCol(int p) {
        this.col = p;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int p) {
        this.row = p;
    }

    @Override
    public String toString() {
        return "Hound{" + "row=" + row + ", col=" + col + '}';
    }
    
}
