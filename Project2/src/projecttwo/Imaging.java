package projecttwo;
import projectone.*;

public class Imaging extends Appointment {
    private Radiology room; // CATSCAN, ULTRASOUND, XRAY

    // Constructor to initialize Imaging appointments
    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider, Radiology room) {
        super(date, timeslot, patient, provider); // Call the Appointment constructor
        this.room = room; // Assign the radiology room
    }

    /**
     * Getter for the radiology room
     * @return the radiology room
     */
    public Radiology getRoom() {
        return this.room;
    }

    // Override toString() to include the room type
    @Override
    public String toString() {
        return super.toString() + ", Imaging Room: " + room;
    }

    // Override compareTo to compare Imaging appointments
    @Override
    public int compareTo(Appointment otherAppointment) {
        if (otherAppointment instanceof Imaging) {
            Imaging otherImaging = (Imaging) otherAppointment;
            int result = super.compareTo(otherImaging); // Compare by date and timeslot first
            if (result == 0) {
                return this.room.compareTo(otherImaging.getRoom()); // Compare by room type if everything else is the same
            }
            return result;
        }
        return super.compareTo(otherAppointment); // Use base class compareTo if not an Imaging instance
    }
}
