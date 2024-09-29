/**
 * Last Modified: 9/29/2024
 * @author: Tianxiang Huang, Jayden Hsu
 * Test: Done
 * 
 */

public class Visit { 
    private Appointment appointment; //a reference to an appointment object
    private Visit next; //a ref. to the next appointment object in the list

    //constructor
    public Visit(){
        appointment = null;
        next = null;
    }
    public Visit(Appointment app){
        appointment = app;
        next = null;
    }

    public void assignNextFinishedVisit(Visit visit){
        next = visit;
    }
    //get data
    public Appointment getCurrentAppointment(){
        return appointment;
    }
    public Visit getNextFinishedVisit(){
        return next;
    }
    //it will always return the end of the linkList;
    public Visit getLastedFinishedVisit(){
        Visit VisitRunenr = this;
        while(VisitRunenr.next != null){
            VisitRunenr = VisitRunenr.next;
        }
        return VisitRunenr;
    }
    public static void main(String[] args) {
        //Create test data for testing
        Profile profile1 = new Profile("Huang", "Tianxiang", new Date(1, 1, 1990));
        Profile profile2 = new Profile("Jayden", "Hsu", new Date(2, 2, 1992));
        Appointment appointment1 = new Appointment(new Date(11, 21, 2024), Timeslot.SLOT1, profile1, Provider.PATEL);
        Appointment appointment2 = new Appointment(new Date(12, 25, 2024), Timeslot.SLOT2, profile2, Provider.LIM);
        Appointment appointment3 = new Appointment(new Date(1, 1, 2025), Timeslot.SLOT3, profile1, Provider.PATEL);

        //Test case 1: Create a Visit with an appointment
        Visit visit1 = new Visit(appointment1);
        System.out.println("Test case 1: " + (visit1.getCurrentAppointment() == appointment1)); //Expected: true

        //Test case 2: Create a Visit and link it to the next visit
        Visit visit2 = new Visit(appointment2);
        visit1.assignNextFinishedVisit(visit2);
        System.out.println("Test case 2: " + (visit1.getNextFinishedVisit() == visit2)); //Expected: true

        //Test case 3: Check the current appointment of the linked visit
        System.out.println("Test case 3: " + visit1.getNextFinishedVisit().getCurrentAppointment().getPatient().getFirstName()); // Expected: Jayden

        //Test case 4: Check if the linked list goes to the end and retrieves the last visit
        Visit visit3 = new Visit(appointment3);
        visit2.assignNextFinishedVisit(visit3);
        System.out.println("Test case 4: " + (visit1.getLastedFinishedVisit() == visit3)); // Expected: true

        //Test case 5: Check if the visit chain works when adding multiple visits
        System.out.println("Test case 5: " + visit1.getLastedFinishedVisit().getCurrentAppointment().getPatient().getFirstName()); //Expected: Huang (from appointment3)
    }
   
} 
