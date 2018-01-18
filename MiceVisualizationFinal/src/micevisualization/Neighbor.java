package micevisualization;

import javafx.scene.paint.Color;

/**
 * @author Whitney
 * This class will be used to help determine the proximity of one mosue to any other mouse that is also
 * in the data set.
 */

public class Neighbor implements Comparable<Neighbor>{

    private String MouseID;

    public String getMouseLabel() {
        return MouseLabel;
    }

    public Color getColor() {
        return color;
    }

    private String MouseLabel;
    private Color color;
    private int Grid;

    public Neighbor(String mouseID, String lbl, Color clr, int Grid){
        this.MouseID = mouseID;
        this.Grid = Grid;
        this.color = clr;
        this.MouseLabel = lbl;
    }
    public Neighbor(Neighbor n){
        this.MouseID = n.MouseID;
        this.Grid = n.Grid;
        this.MouseLabel = n.MouseLabel;
        this.color = n.color;
    }
@Override
public int compareTo(Neighbor other) {
    return this.MouseID.compareTo(other.MouseID);
}

    public String getMouseID() {
        return MouseID;
    }

    public int getGrid() {
        return Grid;
    }
}
