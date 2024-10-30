
package projectone;
/**
 * {@code @author:} Tianxiang Huang
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int[] Big_Month = {1,3,5,7,8,10,12};

    public static final int LASTDAY_FEB_LEAPYEAR = 29;
    public static final int LASTDAY_FEB_NOTLEAPYEAR = 28;
    public static final int LASTDAY_NORMALMONTH_BIG = 31;
    public static final int LASTDAY_NORMALMONTH_SMALL = 30;

    /**
     * default constructor
     */
    public Date(){
        year = -1;
        month = -1;
        day = -1;
    }
    /**
     * constructor with m, d, y
     */
    public Date(int m, int d, int y){
        year = y;
        month = m;
        day = d;
    }

    /**
     * constructor with deep copy of date
     * @param da
     */
    public Date(Date da){
        this.month = da.month;
        this.day = da.day;
        this.year = da.year;
    }


    /**
     * check if the date given is a valid date in calendar
     * @return true if is a valid day, false else
     */
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

    /**
     * this method return month of date
     * @return month
     */
    public int getMonth(){
        return month;
    }
     /**
     * this method return day of date
     * @return day
     */
    public int getDay(){
        return day;
    }
     /**
     * this method return year of date
     * @return year
     */
    public int getYear(){
        return year;
    }

    /**
     * this method check if the day is valid day, which is possible in calendar
     * @param month
     * @param days
     * @param year
     * @return true if day is valid, false else.
     */
    private boolean checkDayValid(int month, int days, int year){
        int LastDayLimit = LASTDAY_NORMALMONTH_SMALL;

        if(month > 12 || year <= 0){
            return false;
        }

        long ZeroNegative_Checker = month * days * year;
        if(ZeroNegative_Checker <= 0){
            return false;
        }

        if(month == 2 && checkLeapYear(year)){
            LastDayLimit = LASTDAY_FEB_LEAPYEAR;
        }else if(month == 2 && !checkLeapYear(year)){
            LastDayLimit = LASTDAY_FEB_NOTLEAPYEAR;
        }else if(checkBig_Month(month)){
            LastDayLimit = LASTDAY_NORMALMONTH_BIG;
        }
        if(days > LastDayLimit){
            return false;
        }

        return true;

    }

    /**
     * Helper method of method: checkDayvalid
     * @param month
     * @return true if month is in range, false else
     */
    private boolean checkBig_Month(int month){
        for(int i = 0; i < Big_Month.length; i += 1){
            if(month == Big_Month[i]){
                return true;
            }
        }

        return false;
    }

    /**
     * check if the year is leap year
     * @param year
     * @return true if is leap year, false else
     */
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
