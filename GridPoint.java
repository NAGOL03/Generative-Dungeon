/**
 * GridPoint is a dynamic marker used to point at a specified row and column.
 * @author NAGOL03
 */
public class GridPoint {
    private int row;
    private int column;

    public static GridPoint key(int row, int column) {
        return new GridPoint(row, column);
    }

    public static GridPoint key(GridPoint position) {
        return new GridPoint(position.row, position.column);
    }

    /**
     * GridPoint constructor
     * @param row initial row
     * @param column initial column
     */
    public GridPoint(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Empty GridPoint constructor
     */
    public GridPoint() {};

    public GridPoint(GridPoint position) {
        this.row = position.row;
        this.column = position.column;
    }

    /**
     * Copies GridPoint object's position
     * @param gridPoint GridPoint to copy
     */
    public void copy(GridPoint gridPoint) {
        this.row = gridPoint.get("row");
        this.column = gridPoint.get("column");
    }

    /**
     * Clones GridPoint
     * @param gridPoint GridPoint to clone
     * @return returns a clone of the passed in GridPoint
     */
    public GridPoint clone(GridPoint gridPoint) {
        return new GridPoint(gridPoint.get("row"), gridPoint.get("column"));
    }

    /**
     * Get row or column
     * @param axis row or column?
     * @return return row or column
     */
    public int get(String axis) {
        if (axis.equals("row")) return this.row;
        else if (axis.equals("column")) return this.column;
        else throw new IllegalArgumentException("Invalid axis: " + axis);
    }

    /**
     * Set row or column
     * @param axis set row or column?
     * @param value set row or column to value
     */
    public void set(String axis, int value) {
        if (axis.equals("row")) this.row = value;
        else if (axis.equals("column")) this.column = value;
        else throw new IllegalArgumentException("Invalid axis: " + axis);
    }

    /**
     * Change row or column by amount
     * @param axis Change row or column?
     * @param amount Change row or column by amount
     */
    public void change(String axis, int amount) {
        if (axis.equals("row")) {
            this.row += amount;
        }
        else if (axis.equals("column")) {
            this.column += amount;
        }
        else throw new IllegalArgumentException("Invalid axis: " + axis);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GridPoint other = (GridPoint) obj;
        return this.row == other.row && this.column == other.column;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    // /**
    //  * @return row
    //  */
    // public int getRow() {
    //     return this.row;
    // }

    // /**
    //  * @return column
    //  */
    // public int getColumn() {
    //     return this.column;
    // }

    // /**
    //  * Sets the row of the GridPoint to the specified row
    //  * 
    //  * @param row set to this row
    //  */
    // public void setRow(int row) {
    //     this.row = row;
    // }

    // /**
    //  * Sets the column of the GridPoint to specified column
    //  * 
    //  * @param column set column
    //  */
    // public void setColumn(int column) {
    //     this.column = column;
    // }

    // /**
    //  * Changes the row of the GridPoint by the specified amount
    //  * 
    //  * @param amount change row by amount
    //  */
    // public void changeRow(int amount) {
    //     this.row += amount;
    // }

    // /**
    //  * Changes the column of the GridPoint by the specified amount
    //  * 
    //  * @param amount the amount by which to change the column
    //  */
    // public void changeColumn(int amount) {
    //     this.column += amount;
    // }
}