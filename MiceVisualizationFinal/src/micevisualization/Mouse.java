/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micevisualization;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 *
 * @author parker
 */
public class Mouse {
    ArrayList<MouseLocTime> locTimeData;
    String IdRFID;
    String IdLabel;
    Color mouse_color;
    double x;
    double y;
    //Whitney Post 11/15/17 adding 3 fields for mouse object,
    //their current RFID grid position, the timestamp they first appear and the grid RFID they first appear on
    int currentPosition;
    Date firstAppearance;
    int firstGrid;
    
    public Mouse(String idr, String idl, Color clr, double X, double Y, int initPOS, Date fa) {
        this.IdRFID = idr;
        this.IdLabel = idl;
        this.locTimeData = new ArrayList<>();
         //Whitney Post 5/29/17: Removed randomly generated mouse colors
        this.mouse_color = clr;
        this.x = X;
        this.y = Y;
        this.currentPosition = initPOS;
        this.firstAppearance = fa;
        this.firstGrid = initPOS;
    }
    
    public Boolean addLocTime(MouseLocTime mlt) {
        this.currentPosition = mlt.unitLabel;
        return locTimeData.add(mlt);
    }
       /**
        * whitney post 5/23/17 adding method to return the mouse color
        * @return the color assigned to the mouse.
        * */
    public Color getColor() {
        return this.mouse_color;
    }
    
}
