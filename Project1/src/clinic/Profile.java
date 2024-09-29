package clinic;
/**
 * Last Modified: 9/29/2024
 * {@code @author:} Tianxiang Huang, Jayden Hsu
 * Test: Done
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

    
    /** 
     * @return String
     */
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

    public static void main(String[] args) {
        //Create testing data 
        Date dob1 = new Date(1, 1, 1990);
        Date dob2 = new Date(1, 1, 1990);
        Date dob3 = new Date(1, 1, 1985);
        Date dob4 = new Date(1, 1, 1995);
    
        //Test case 1: Comparing profiles by last name 
        //Since "Hsu" is lexicographically smaller than "Huang"
        Profile profile1 = new Profile("Jayden", "Hsu", dob1);
        Profile profile2 = new Profile("Tianxiang", "Huang", dob2);
        System.out.println("Test case 1 (last name): " + profile1.compareTo(profile2)); //Expected: -1
    
        //Test case 2: Same last name, different first names 
        //Since the last names are the same, it compares the first names.
        //"Jayden" is lexicographically smaller than "Tianxiang"
        Profile profile3 = new Profile("Jayden", "Huang", dob1);
        Profile profile4 = new Profile("Tianxiang", "Huang", dob2);
        System.out.println("Test case 2 (first name): " + profile3.compareTo(profile4)); //Expected: -1
    
        //Test case 3: Same first and last names, different date of birth 
        //Since both the last and first names are the same, it compares the date of birth.
        //1985 is earlier than 1990
        Profile profile5 = new Profile("Jayden", "Hsu", dob3);
        System.out.println("Test case 3 (earlier dob): " + profile5.compareTo(profile1)); //Expected: -1
    
        //Test case 4: Comparing profiles by last name
        //Since "Huang" is lexicographically greater than "Hsu"
        Profile profile6 = new Profile("Tianxiang", "Huang", dob1);
        System.out.println("Test case 4 (last name): " + profile6.compareTo(profile1)); //Expected: 1
    
        //Test case 5: Same last name, different first names
        //Since the last names are the same, it compares the first names.
        //"Tianxiang" is lexicographically greater than "Jayden"
        Profile profile7 = new Profile("Tianxiang", "Hsu", dob1);
        Profile profile8 = new Profile("Jayden", "Hsu", dob1);
        System.out.println("Test case 5 (first name): " + profile7.compareTo(profile8)); //Expected: 1
    
        //Test case 6: Testing date of birth comparison.
        //Last name and first name are the same, so compare dates of birth.
        //1995 is later than 1990
        Profile profile9 = new Profile("Jayden", "Hsu", dob4);
        System.out.println("Test case 6 (later dob): " + profile9.compareTo(profile1)); //Expected: 1
    
        //Test case 7: Testing identical profiles.
        //Both last names, first names, and dates of birth are the same     
        Profile profile10 = new Profile("Jayden", "Hsu", dob1);
        Profile profile11 = new Profile("Jayden", "Hsu", dob1);
        System.out.println("Test case 7 (same profile): " + profile10.compareTo(profile11)); //Expected: 0
    }
    
}
