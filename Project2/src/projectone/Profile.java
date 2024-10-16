package projectone;
/**
 * {@code @author:} Tianxiang Huang, Jayden Hsu
 */
public class Profile implements Comparable<Profile> {
    private String FirstName;
    private String LastName;
    private Date DateOfBirth;

    // Constructor
    /**
     * Default constructor initializing fields to default values.
     */
    public Profile() {
        FirstName = "";
        LastName = "";
        DateOfBirth = null;
    }

    /**
     * Constructor initializing Profile with first name, last name, and date of birth.
     * @param fn First name
     * @param ln Last name
     * @param DoB Date of birth
     */
    public Profile(String fn, String ln, Date DoB) {
        FirstName = fn;
        LastName = ln;
        DateOfBirth = new Date(DoB.getMonth(), DoB.getDay(), DoB.getYear());
    }

    /**
     * Constructor initializing Profile with first name, last name, and date of birth components.
     * @param fn First name
     * @param ln Last name
     * @param m Month of birth
     * @param d Day of birth
     * @param y Year of birth
     */
    public Profile(String fn, String ln, int m, int d, int y) {
        FirstName = fn;
        LastName = ln;
        DateOfBirth = new Date(m, d, y);
    }

    /**
     * Copy constructor for Profile.
     * @param pa The profile to copy
     */
    public Profile(Profile pa) {
        FirstName = pa.getFirstName();
        LastName = pa.getLastName();
        DateOfBirth = new Date(pa.getBirthDay());
    }

    /**
     * Get first name.
     * @return First name
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Get last name.
     * @return Last name
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Get date of birth.
     * @return Date of birth
     */
    public Date getBirthDay() {
        return DateOfBirth;
    }

    /**
     * Get patient's name in the order: last name -> first name.
     * @return Patient's name in the order: last name -> first name
     */
    public String getName_LF() {
        String result = "";
        result += LastName.toLowerCase() + " " + FirstName.toLowerCase();
        result.toLowerCase();
        return result;
    }

    /**
     * Compare the current Profile with another Profile.
     * @param TargetProfile The profile to compare to
     * @return Result of the comparison
     */
    @Override
    public int compareTo(Profile TargetProfile) {
        return this.toString().compareTo(TargetProfile.toString());
    }

    /**
     * Returns a string representation of the Profile.
     * @return String representation of the Profile
     */
    @Override
    public String toString() {
        String result = "";
        result += FirstName.toLowerCase() + " " + LastName.toLowerCase() + " " + DateOfBirth.toString();
        result.toLowerCase();
        return result;
    }

    /**
     * Check if the current Profile is equal to another object.
     * @param other The object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        Profile TargetProfile = (Profile) other;
        if (TargetProfile.getFirstName().equalsIgnoreCase(this.getFirstName()) &&
                TargetProfile.getLastName().equalsIgnoreCase(this.getLastName()) &&
                this.DateOfBirth.equals(TargetProfile.getBirthDay())) {
            return true;
        }
        return false;
    }
}

