/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micevisualization;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author parker
 * 
 * MouseLocTime stands for "Mouse Location and Timestamp" information.
 */
public class MouseLocTime implements Comparable<MouseLocTime> {
    Date timestamp;
    int unitLabel;
    int eventDuration;
    ArrayList<Neighbor> neighbors;  //11/15/2017 Whitney Post added additional data member to the MLT object. An array list of neighbor mice.

    
    public MouseLocTime(Date ts, int ul, int ed, ArrayList<Neighbor> n) {
        this.timestamp = ts;
        this.unitLabel = ul;
        this.eventDuration = ed;
        this.neighbors = n;
    }
    
    @Override
    public int compareTo(MouseLocTime other) {
        return this.timestamp.compareTo(other.timestamp);
    }
}
