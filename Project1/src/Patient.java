/**
 * Last Modified: 9/29/2024
 * @author: Tianxiang Huang, Jayden Hsu
 * Test: Done
 * 
 */
public class Patient implements Comparable<Patient> { 
    private Profile profile; 
    //visits should always equal to the head of the linklist
    private Visit visits; //a linked list of visits (completed appt.)

    public Patient(){
        profile = null;
        visits = null;
    }
    public Patient(Appointment appointment){
        profile = appointment.getPatient();
        visits = new Visit(appointment);
    }
    public Patient(Profile pro){
        profile = pro;
        visits = null;
    }

    public int charge() {
        Visit VisitRunner = visits;
        int FinialCharge = 0;

        while(VisitRunner != null){
            FinialCharge += VisitRunner.getCurrentAppointment().getProvider().getSpecialty().getCharge();
            VisitRunner = VisitRunner.getNextFinishedVisit();
        }

        return FinialCharge;
    } //traverse the linked list to compute the charge

    public void addFinishedAppointment(Appointment appointment){
        Visit NewFinishedVisit = new Visit(appointment);
        NewFinishedVisit.assignNextFinishedVisit(visits);
        visits = NewFinishedVisit;
    }

    //get Data
    public Profile getProfile(){
        return profile;
    }
    public Visit getFirstFinihedVisit(){
        return visits;
    }
    
    @Override public int compareTo(Patient TargetPatient){
        return this.profile.compareTo(TargetPatient.getProfile());
    }

    @Override public boolean equals(Object other){
        Patient TargetPatient = (Patient)other;
        return this.profile.equals(TargetPatient.getProfile());
    }

    @Override public String toString(){
        String result = "";
        result += profile.getFirstName() + " " + profile.getLastName() + " " + profile.getBirthDay().toString() + " ";
        result += "[Amount due: $" + Float.toString(charge()) + "]";
        return result;
    }

    public static void main(String[] args) {
        Profile profile1 = new Profile("Jayden", "Hsu", new Date(1, 1, 1990));
        Appointment appointment1 = new Appointment(new Date(11, 21, 2024), Timeslot.SLOT1, profile1, Provider.PATEL);
        Patient patient = new Patient(appointment1);

        //Test case 1: Get profile of patient
        System.out.println("Test case 1: " + patient.getProfile().getFirstName()); //Expected: Jayden

        //Test case 2: Add finished appointment
        patient.addFinishedAppointment(appointment1);
        System.out.println("Test case 2: First finished visit: " + patient.getFirstFinihedVisit().getCurrentAppointment().getDate()); //Expected: 11/21/2024
    }
    
}
