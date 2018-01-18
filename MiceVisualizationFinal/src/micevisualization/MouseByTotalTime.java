/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micevisualization;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 *
 * @author Christian
 */
public class MouseByTotalTime {
    private String name;
    private int[] readers;
    
    public MouseByTotalTime(String name){
        this.name = name;
        readers = new int[25];
    }
    
    public String getName(){
        return name;
    }
    public int getReader(int index){
        return readers[index];
    }
    public void incrementTime(int label, int duration){
        readers[label-1] += duration;
    }
    
}
