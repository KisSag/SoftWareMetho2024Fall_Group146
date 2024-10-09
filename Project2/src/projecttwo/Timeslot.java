package projecttwo;

public class Timeslot implements Comparable<Timeslot>{
    private int hour;
    private int minuts;

    /**
     * constructor
     * @param h
     * @param m
     */
    public Timeslot(int h, int m){
        hour = h;
        minuts = m;
    }
    
    /**
     * deep cp constructor
     * @param ti
     */
    public Timeslot(Timeslot ti){
        hour = ti.getHour();
        minuts = ti.getMinuts();
    }


    /**
     * get hour
     * @return hour
     */
    public int getHour(){
        return hour;
    }

    /**
     * get minuts
     * @return minuts
     */
    public int getMinuts(){
        return minuts;
    }

    @Override public String toString(){
        String result = Integer.toString(hour) + ":" + Integer.toString(minuts);
        if(hour >= 12){
            result += " PM";
        }else{
            result += " AM";
        }
        return result;
    }
    @Override public boolean equals(Object other){
        if(other.getClass() != this.getClass()){
            return false;
        }

        Timeslot TargetTimeSlot = (Timeslot)other;

        if(TargetTimeSlot.getHour() == hour && TargetTimeSlot.getMinuts() == minuts){
            return true;
        }

        return false;
    }
    @Override public int compareTo(Timeslot TargetTimeSlot) {

        //check hour
        if(hour < TargetTimeSlot.getHour()){
            return -1;
        }else if(hour > TargetTimeSlot.getHour()){
            return 1;
        }

        //check minuts
        if(minuts < TargetTimeSlot.getMinuts()){
            return -1;
        }else if(minuts > TargetTimeSlot.getMinuts()){
            return 1;
        }

        return 0;
    }
}
