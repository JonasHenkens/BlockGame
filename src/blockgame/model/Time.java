/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 * Gives the played time
 * @author Souhaib El Habti
 */
public class Time {
    private int min;
    private int hour;
    private int sec;

    /**
     * Constructor of the class Time
     * @param sec The current second.
     * @param min The current minute.
     * @param hour The current hour.
     */
    public Time(int sec, int min, int hour) {
        this.sec=sec;
        this.min=min;
        this.hour=hour;
    }

    /**
     *  Adds 1 seconds.
     *  Checks if the min or the uren has to change 
     */
    public void secPlusEen() {
        sec++;
        setMin();
        setHour();
    }

    /**
     *  Reset the sec, min and hour
     */
    public void reset() {
        hour=0;
        sec=0;
        min=0;
    }
  
    // setters
    
    /**
     *  If there is 60 sec: change it
     *  to 1 min and 0 sec
     */
    public void setMin() {
        if(sec==60){
            min++;
            sec=0;
        }
    }

    /**
     *  If there is 60 min: change it 
     *  to 1 uren and 0 min
     */
    public void setHour() {
        if(min==60){
            hour++;
            min=0;
        }
    }

    
    // getters
    
    /**
     * @return The current hour.
     */
    public int getHour() {
        return hour;
    }

    /**
     * @return The current minute.
     */
    public int getMin() {
        return min;
    }

    /**
     * @return The current second.
     */
    public int getSec() {
        return sec;
    }
    
}
