/**
 * Last Modified: 9/16/2024
 * @author: Tianxiang Huang
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

    public static final int LASTDAY_FEB_LEAPYEAR = 29;
    public static final int LASTDAY_FEB_NOTLEAPYEAR = 28;
    public static final int LASTDAY_NORMALMONTH_BIG = 31;
    public static final int LASTDAY_NORMALMONTH_SMALL = 30;

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

    public static void main(String[] args) {
        //Test case 1: January 1, Year -2000 (invalid negative year)
        //Testing: The system should reject negative years. In most date systems, there is no concept of negative years.
        Date date1 = new Date(1, 1, -2000);
        System.out.println("Test case 1: " + date1 + " is valid? " + date1.isValid()); //Expected: false
    
        //Test case 2: Non-leap year, invalid February 29
        //Testing: February 29 is only valid in leap years. 2018 is a common year, not a leap year, so February 29 is invalid.
        Date date2 = new Date(2, 29, 2018); 
        System.out.println("Test case 2: " + date2 + " is valid? " + date2.isValid()); //Expected: false
    
        //Test case 3: Invalid month (outside valid range)
        //Testing: The month value should be between 1 and 12. Month 13 is invalid.
        Date date3 = new Date(13, 5, 2024);
        System.out.println("Test case 3: " + date3 + " is valid? " + date3.isValid()); //Expected: false
    
        //Test case 4: Valid date
        //Testing: November 21, 2024, is a valid date. This test confirms that the `isValid()` method works for a standard valid date.
        Date date4 = new Date(11, 21, 2024);
        System.out.println("Test case 4: " + date4 + " is valid? " + date4.isValid()); //Expected: true
    
        //Test case 5: Invalid day for June (June has 30 days, but testing for 31)
        //Testing: June only has 30 days. The `isValid()` method should return false for June 31, as it exceeds the valid day range for June.
        Date date5 = new Date(6, 31, 2024); 
        System.out.println("Test case 5: " + date5 + " is valid? " + date5.isValid()); //Expected: false
    
        //Test case 6: Valid leap year date for February 29
        //Testing: February 29 is valid in leap years. 2020 is a leap year, so this test ensures the system correctly handles leap year validation.
        Date date6 = new Date(2, 29, 2020); // 2020 is a leap year
        System.out.println("Test case 6: " + date6 + " is valid? " + date6.isValid()); //Expected: true
    }    

}
