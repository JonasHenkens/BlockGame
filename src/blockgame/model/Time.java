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

    public Time() {
        sec=0;
        min=0;
        uren=0;
        gestopt=true;
    }

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

    public void setMin() {
        if(sec==60){
            min++;
            sec=0;
        }
    }

    public void setUren() {
        if(min==60){
            uren++;
            min=0;
        }
    }

    /**
     * @return the ms
     */
    public int getSec() {
        return sec;
    }

    /**
     * @return the gestopt
     */
    public boolean isGestopt() {
        return gestopt;
    }

    public void setGestopt() {
        gestopt=!gestopt;
    }

    void reset() {
        uren=0;
        sec=0;
        min=0;
    }
    
}
