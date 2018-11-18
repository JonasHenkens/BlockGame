/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 *
 * @author Souhaib El Habti
 */
public class Time {
    private int min;
    private int uren;
    private int sec;
    private boolean gestopt;

    /**
     *  Constructor of the class Time
     */
    public Time() {
        sec=0;
        min=0;
        uren=0;
        gestopt=true;
    }

    /**
     *  Adds 1 sec. to the sec
     *  Checks if the min or the uren has to change 
     */
    public void secPlusEen() {
        sec++;
        setMin();
        setUren();
    }

    @Override
    public String toString() {
        return "PlayTime: "+uren+":"+min+":"+sec;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the uren
     */
    public int getUren() {
        return uren;
    }

    /**
     *  If there is 60 sec: change it to 
     *  1 min and 0 sec
     */
    public void setMin() {
        if(sec==60){
            min++;
            sec=0;
        }
    }

    /**
     *  If there is 60 min: change it to 
     *  1 uren and 0 min
     */
    public void setUren() {
        if(min==60){
            uren++;
            min=0;
        }
    }

    /**
     * @return the sec
     */
    public int getSec() {
        return sec;
    }

    /**
     * @return the gestopt: true if the clock is
     * stopped and false if it isn't stopped
     */
    public boolean isGestopt() {
        return gestopt;
    }

    public void setGestopt() {
        gestopt=!gestopt;
    }

    /**
     *  Reset the sec, min and uren
     */
    public void reset() {
        uren=0;
        sec=0;
        min=0;
    }
    
}
