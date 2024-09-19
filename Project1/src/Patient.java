/**
 * Last Modified: 9/17/2024
 * Name: Tianxiang Huang
 * Test: No Test Yet
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
        result += profile.toString() + " ";
        result += Integer.toString(charge());
        return result;
    }
}