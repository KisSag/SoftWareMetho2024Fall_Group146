package clinic;

/**
 * {@code @author:} Tianxiang Huan, Jayden Hsu
 * Last Modified: 9/29/2024
 * Test: DOne
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

     public static void main(String[] args) {
        //Test cases: Get time slot details
        System.out.println("Test case 1: " + Timeslot.SLOT1.getString_TimeSlot()); //Expected: 9:0 AM
        System.out.println("Test case 2: " + Timeslot.SLOT4.getString_TimeSlot()); //Expected: 13:30 PM
        System.out.println("Test case 3: " + Timeslot.SLOT5.getString_TimeSlot()); //Expected: 15:0 PM
    }
    
}
