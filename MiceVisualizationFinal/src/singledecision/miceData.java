/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singledecision;

/**
 *
 * @author jenniferpolack-wahladmin
 */
public class miceData{
        public int time;
        public int location;
        public String mouse;
        
        public miceData(int t, int l, String m){
            time=t;
            location=l;
            mouse=m;
        }
        
        public int getTime(){
            return time;
        }
        public void setTime(int t){
            time = t;
        }
        public int getLocation(){
            return location;
        }
        public void setLocation(int l){
            location =l;
        }
        public String getMouse(){
            return mouse;
        }
        public void setMouse(String m){
            mouse=m;
        }
        
    }
