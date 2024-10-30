package projectone;

/**
 * {@code @author:} Tianxiang Huang, Jayden Hsu
 */
public class Visit {
    private Appointment appointment; // a reference to an appointment object
    private Visit next; // a reference to the next appointment object in the list

    // Constructor
    /**
     * Default constructor initializing appointment and next to null.
     */
    public Visit() {
        appointment = null;
        next = null;
    }

    /**
     * Constructor initializing Visit with an Appointment.
     * @param app The appointment to initialize the visit with
     */
    public Visit(Appointment app) {
        appointment = app;
        next = null;
    }

    /**
     * Assign the next finished visit in the linked list.
     * @param visit The visit to assign as the next visit
     */
    public void assignNextFinishedVisit(Visit visit) {
        next = visit;
    }

    /**
     * Get current appointment.
     * @return Current appointment
     */
    public Appointment getCurrentAppointment() {
        return appointment;
    }

    /**
     * Get next visit node.
     * @return Next visit node
     */
    public Visit getNextFinishedVisit() {
        return next;
    }

    /**
     * Get the last visit node.
     * @return Last visit node
     */
    public Visit getLastedFinishedVisit() {
        Visit VisitRunner = this;
        while (VisitRunner.next != null) {
            VisitRunner = VisitRunner.next;
        }
        return VisitRunner;
    }
}
