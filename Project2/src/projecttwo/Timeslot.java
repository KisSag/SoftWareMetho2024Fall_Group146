package projecttwo;

/**
 * {@code @author:} Tianxiang Huang
 */
public class Timeslot implements Comparable<Timeslot> {
    private int hour;
    private int minutes;

    /**
     * Constructor to initialize the Timeslot.
     * @param h The hour
     * @param m The minutes
     */
    public Timeslot(int h, int m) {
        hour = h;
        minutes = m;
    }

    /**
     * Deep copy constructor.
     * @param ti The Timeslot to copy
     */
    public Timeslot(Timeslot ti) {
        hour = ti.getHour();
        minutes = ti.getMinutes();
    }

    /**
     * Get the hour of the timeslot.
     * @return The hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Get the minutes of the timeslot.
     * @return The minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Returns a string representation of the Timeslot.
     * @return String representation of the Timeslot
     */
    @Override
    public String toString() {
        String result = Integer.toString(hour) + ":" + Integer.toString(minutes);
        if (hour >= 12) {
            result += " PM";
        } else {
            result += " AM";
        }
        return result;
    }

    /**
     * Check if the current Timeslot is equal to another object.
     * @param other The object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Timeslot TargetTimeSlot = (Timeslot) other;
        if (TargetTimeSlot.getHour() == hour && TargetTimeSlot.getMinutes() == minutes) {
            return true;
        }
        return false;
    }

    /**
     * Compare the current Timeslot with another Timeslot.
     * @param TargetTimeSlot The Timeslot to compare to
     * @return The result of the comparison
     */
    @Override
    public int compareTo(Timeslot TargetTimeSlot) {
        // Check hour
        if (hour < TargetTimeSlot.getHour()) {
            return -1;
        } else if (hour > TargetTimeSlot.getHour()) {
            return 1;
        }
        // Check minutes
        if (minutes < TargetTimeSlot.getMinutes()) {
            return -1;
        } else if (minutes > TargetTimeSlot.getMinutes()) {
            return 1;
        }
        return 0;
    }
}
