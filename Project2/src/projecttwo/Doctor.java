package projecttwo;
import projectone.*;

/**
 * The Doctor class represents a doctor as a porvider in the clinic system
 * It includes the doctor's specialty, NPI, and location
 * 
 * {@code @author:} Tianxiang Huang
 */

public class Doctor extends Provider {
    private Specialty specialty;
    private String npi;

    /**
     * Constructor to initialize a Doctor with profile, location, specialty, and NPI.
     * @param pro Profile of the doctor
     * @param loc Location of the doctor
     * @param spe Specialty of the doctor
     * @param n NPI of the doctor
     */
    public Doctor(Profile pro, Location loc, Specialty spe, String n) {
        profile = pro;
        location = loc;
        specialty = spe;
        npi = n;
    }

    /**
     * Get the specialty of the doctor.
     * @return The specialty of the doctor
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Get the NPI of the doctor.
     * @return The NPI of the doctor
     */
    public String getNPI() {
        return npi;
    }

    /**
     * Get the NPI of the doctor in integer format.
     * @return The NPI in integer format
     */
    public int getNPI_InNumber() {
        return Integer.parseInt(npi);
    }

    /**
     * Get the location of the doctor.
     * @return The location of the doctor
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Change the credit of the doctor.
     * @param scaler The amount to scale the credit by
     */
    @Override
    public void changeCredit(int scaler) {
        Credit += scaler * specialty.getCharge();
    }

    /**
     * Get the price per visit of the doctor.
     * @return The price per visit
     */
    @Override
    public int getPrice() {
        return specialty.getCharge();
    }

    /**
     * Rate the doctor.
     * @return The rating of the doctor
     */
    @Override
    public int rate() {
        return -1;
    }

    /**
     * Compare the current Doctor with another Person.
     * @param TargetPerson The person to compare to
     * @return The result of the comparison
     */
    @Override
    public int compareTo(Person TargetPerson) {
        return profile.compareTo(TargetPerson.getProfile());
    }

    /**
     * Check if the current Doctor is equal to another object.
     * @param other The object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Doctor TargetDoctor = (Doctor) other;
        return this.profile.equals(TargetDoctor.getProfile());
    }

    /**
     * Returns a string representation of the Doctor.
     * @return String representation of the Doctor
     */
    @Override
    public String toString() {
        return "[" + profile.toString() + ", " + location.toString() + " ] [" + specialty.toString() + " #" + npi + "]";
    }
}
