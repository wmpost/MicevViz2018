package micevisualization;

import org.apache.poi.ss.usermodel.DateUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CSVLine implements Comparable<CSVLine>{
    private Date dts;
    private String mouseID;
    private String mouseLabel;
    private int gridID;
    private int duration;

    CSVLine(String fileLine){
        List<String> items;
        int TIMESTAMP = 0;
        int ID_RFID = 1;
        int ID_LABEL = 2;
        int UNIT_LABEL = 3;
        int EVENT_DURATION = 4;

        if (fileLine.contains(";")) {
            items = Arrays.asList(fileLine.split(";"));
        }
        else
            items = Arrays.asList(fileLine.split(","));
        if(!items.get(TIMESTAMP).contains("ID"))
            this.dts = DateUtil.getJavaDate((Double.parseDouble(items.get(TIMESTAMP))));
        else
            this.dts = null;
        if(!items.get(UNIT_LABEL).contains("RFID"))
            this.gridID = Integer.parseInt(items.get(UNIT_LABEL));
        else
            this.gridID = Integer.parseInt(items.get(UNIT_LABEL).substring(4));

        this.mouseLabel = items.get(ID_LABEL);
        this.mouseID = items.get(ID_RFID);
        this.duration = Integer.parseInt(items.get(EVENT_DURATION));
    }

    @Override
    public int compareTo(CSVLine other) {
        return this.dts.compareTo(other.dts);
    }

    public Date getDTS(){
        return dts;
    }
    public String getMouseID() {
        return mouseID;
    }

    public String getMouseLabel() {
        return mouseLabel;
    }

    public int getGridID() {
        return gridID;
    }

    public int getDuration() {
        return duration;
    }
}
