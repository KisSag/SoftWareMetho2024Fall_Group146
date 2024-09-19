/**
 * Last Modified: 9/16/2024
 * Name: Tianxiang Huang
 * Test: No Test Yet
 * 
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int[] Big_Month = {1,3,5,7,8,10,12};

    //constructor
    //default
    public Date(){
        year = -1;
        month = -1;
        day = -1;
    }
    public Date(int m, int d, int y){
        year = y;
        month = m;
        day = d;
    }
    public Date(Date da){
        this.month = da.month;
        this.day = da.day;
        this.year = da.year;
    }

    //method
    public boolean isValid(){

        //check month
        if(month < 1 || month > 12){
            return false;
        }
        //check days
        if(!checkDayValid(month, day, year)){
            return false;
        }
        

        return true;
    }

    //these methods can get year, month and day
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public int getYear(){
        return year;
    }

    private boolean checkDayValid(int month, int days, int year){
        int LastDayLimit = 30;
        

        if(month > 12 || year <= 0){
            return false;
        }

        long ZeroNegative_Checker = month * days * year;
        if(ZeroNegative_Checker <= 0){
            return false;
        }

        if(month == 2 && checkLeapYear(year)){
            LastDayLimit = 29;
        }else if(month == 2 && !checkLeapYear(year)){
            LastDayLimit = 28;
        }else if(checkBig_Month(month)){
            LastDayLimit = 31;
        }
        if(days > LastDayLimit){
            return false;
        }

        return true;

    }

    private boolean checkBig_Month(int month){
        for(int i = 0; i < Big_Month.length; i += 1){
            if(month == Big_Month[i]){
                return true;
            }
        }

        return false;
    }

    private boolean checkLeapYear(int year){

        if(year % QUADRENNIAL != 0){
            return false;
        }
        if(year % CENTENNIAL != 0){
            return true;
        }
        if(year % QUATERCENTENNIAL != 0){
            return false;
        }

        return true;
    }

    //Override
    @Override public int compareTo(Date TargetData){

        if(this.month == TargetData.month && this.day == TargetData.day && this.year == TargetData.year){
            return 0;
        }

        if(this.year > TargetData.getYear()){
            return 1;
        }else if(this.year == TargetData.getYear() && this.month > TargetData.getMonth()){
            return 1;
        }else if(this.year == TargetData.getYear() && this.month == TargetData.getMonth() && this.day > TargetData.getDay()){
            return 1;
        }

        return -1;
    }

    @Override public String toString(){
        String result = "";
        result += Integer.toString(month) + "/" + Integer.toString(day) + "/" +Integer.toString(year);
        result.toLowerCase();
        return result;
    }

    @Override public boolean equals(Object other){
        Date TargetData = (Date)other;
        if(this.month == TargetData.month && this.day == TargetData.day && this.year == TargetData.year){
            return true;
        }
        return false;
    }
    

}
