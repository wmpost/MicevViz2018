package micevisualization;

import org.junit.Test;

import static org.junit.Assert.*;

public class CSVLineTest {

    @Test
    public void DateStuff(){
        String strTest = "43021.6322563426;041940CA08;Cohort1-3;5;293;;;1;";
        CSVLine line = new CSVLine(strTest);
        System.out.println(line.dts.toString());
        System.out.println(line.mouseID);
        System.out.println(line.mouseLabel);
        System.out.println(line.gridID);
        System.out.println(line.duration);
    }
}