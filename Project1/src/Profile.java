/**
 * Last Modified: 9/16/2024
 * Name: Tianxiang Huang
 * Test: No Test Yet
 * 
 */
public class Profile implements Comparable<Profile>{
    private String FirstName;
    private String LastName;
    private Date DateOfBirth;

    //constructor
    public Profile(){
        FirstName = "";
        LastName = "";
        DateOfBirth = null;
    }
    public Profile(String fn, String ln, Date DoB){
        FirstName = fn;
        LastName = ln;
        DateOfBirth = new Date(DoB.getMonth(), DoB.getDay(), DoB.getYear());
    }
    public Profile(String fn, String ln, int m, int d, int y){
        FirstName = fn;
        LastName = ln;
        DateOfBirth = new Date(m, d, y);
    }
    public Profile(Profile pa){
        FirstName = pa.getFirstName();
        LastName = pa.getLastName();
        DateOfBirth = new Date(pa.getBirthDay());
    }

    //get information
    public String getFirstName(){
        return FirstName;
    }
    public String getLastName(){
        return LastName;
    }
    public Date getBirthDay(){
        return DateOfBirth;
    }
    
    //Override
    @Override public int compareTo(Profile TargetProfile){
        return this.toString().compareTo(TargetProfile.toString());
    }
    @Override public String toString(){
        String result = "";
        result += FirstName.toLowerCase() + " " + LastName.toLowerCase() + " " + DateOfBirth.toString();
        result.toLowerCase();
        return result;
    }
    @Override public boolean equals(Object other){
        Profile TargetProfile = (Profile)other;
        if(TargetProfile.getFirstName().equals(this.getFirstName()) && TargetProfile.getLastName().equals(this.getLastName()) && this.DateOfBirth.equals(TargetProfile.getBirthDay())){
            return true;
        }
        return false;
    }
}
