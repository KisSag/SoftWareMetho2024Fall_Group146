package projecttwo;
import projectone.*;

/**
 * The Person class represents a general person in the clinic system.
 * 
 * The class provides methods for accessing the person's profile, comparing Person objects, 
 * checking equality, and generating a string representation of the person.
 * 
 * {@code @author:} Tianxiang Huang
 */

public class Person implements Comparable<Person> {
    protected Profile profile;

    /**
     * Get the profile of this person.
     * @return Profile of the person
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Compare the current Person with another Person.
     * @param TargetPerson The person to compare to
     * @return Result of the comparison
     */
    @Override
    public int compareTo(Person TargetPerson) {
        return profile.compareTo(TargetPerson.getProfile());
    }

    /**
     * Check if the current Person is equal to another object.
     * @param other The object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        Person TargetProfile = (Person) other;
        if (profile.equals(TargetProfile.getProfile())) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the Person.
     * @return String representation of the Person
     */
    @Override
    public String toString() {
        return profile.toString();
    }
}
