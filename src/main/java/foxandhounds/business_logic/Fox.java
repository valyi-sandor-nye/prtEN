package foxandhounds.business_logic;

import javax.xml.bind.annotation.XmlType;

/** 
 * A fox is a figure on the table. 
 * It has two coordinates, one for row (y) and one for column (x).
 */

@XmlType
public class Fox implements Figure{

    private int  row,col;
    public Fox(int p_row, int p_col) {
       row = p_row;  col = p_col; 
    }
    public int getCol() {
        return col;
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
        return "Fox{" + "row=" + row + ", col=" + col + '}';
    }
    
} // end class Fox
