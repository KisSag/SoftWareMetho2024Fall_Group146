/**
 * Last Modified: 9/16/2024
 * Name: Tianxiang Huang
 * Test: No Test Yet
 * 
 */
public enum Timeslot { 
    SLOT1 (9, 0), 
    SLOT2 (10, 45), 
    SLOT3 (11, 15), 
    SLOT4 (13, 30), 
    SLOT5 (15, 0), 
    SLOT6 (16, 15); 

    private final int Hour;
    private final int Minus;

    Timeslot(int Hour, int Minus){
        this.Hour = Hour;
        this.Minus = Minus;
    }

    public int getHour(){
        return Hour;
    }
    public int getMinus(){
        return Minus;
    }
    public String getString_TimeSlot(){
        String result = "";
        result += Integer.toString(this.Hour) + ":" + Integer.toString(this.Minus);

        if(this.Hour > 12){
            result += "PM";
        }else{
            result += "AM";
        }

        return result;
    }
}