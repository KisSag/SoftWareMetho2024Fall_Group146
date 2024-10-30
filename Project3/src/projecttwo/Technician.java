package projecttwo;

import projectone.*;

/**
 * {@code @author:} Tianxiang Huang
 * The Technician class represents a technician in the clinic system who provides services.
 * It extends the Provider class and provides methods to retrieve the technician's rate, adjust credits,
 * compare with other persons, and check equality.
 * The class overrides methods from the Provider class and implements custom behavior for technicians.
 */
public class Technician extends Provider {
    private int ratePerVisit;

    /**
     * Constructor to initialize a Technician with profile, location, and rate per visit.
     * @param pro Profile of the technician
     * @param loc Location of the technician
     * @param ratePrice Rate per visit as a string
     */
    public Technician(Profile pro, Location loc, String ratePrice) {
        profile = pro;
        location = loc;
        ratePerVisit = Integer.parseInt(ratePrice);
    }

    /**
     * Get the rate per visit of the technician.
     * @return The rate per visit
     */
    public int getRatePerVisit() {
        return ratePerVisit;
    }

    /**
     * Get the price per visit of the technician.
     * @return The price per visit
     */
    @Override
    public int getPrice() {
        return ratePerVisit;
    }

    /**
     * Change the credit of the technician.
     * @param scaler The amount to scale the credit by
     */
    @Override
    public void changeCredit(int scaler) {
        Credit += scaler * ratePerVisit;
    }

    /**
     * Rate the technician.
     * @return The rating of the technician
     */
    @Override
    public int rate() {
        return -1;
    }

    /**
     * Compare the current Technician with another Person.
     * @param TargetPerson The person to compare to
     * @return The result of the comparison
     */
    @Override
    public int compareTo(Person TargetPerson) {
        return profile.compareTo(TargetPerson.getProfile());
    }

    /**
     * Check if the current Technician is equal to another object.
     * @param other The object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Technician TargetDoctor = (Technician) other;
        return this.profile.equals(TargetDoctor.getProfile());
    }

    /**
     * Returns a string representation of the Technician.
     * @return String representation of the Technician
     */
    @Override
    public String toString() {
        return "[" + profile.toString() + ", " + location.toString() + "] [rate: $" + ratePerVisit + "]";
    }
}
