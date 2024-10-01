package projectone;
import projecttwo.*;
/**
 * Last Modified: 9/30/2024
 * {@code @author:} Tianxiang Huang
 * Test: No Test Yet
 * 
 */
public class Appointment implements Comparable<Appointment>{
    private Date date; 
    private Timeslot timeslot; 
    private Person patient; 
    private Person provider; 

    /**
     * Default Constructor
     */
    public Appointment(){
        date = null;
        timeslot = null;
        patient = null;
        provider = null;
    }

    /**
     * Full constructor
     * @param da
     * @param ti
     * @param pa
     * @param pr
     */
    public Appointment(Date da, Timeslot ti, Person pa, Person pr){
        date = new Date(da);
        timeslot = ti;
        patient = pa;
        provider = pr;
    }
    
    /**
     * Deep copy of a appointment
     * @param TargetAppontment
     */
    public Appointment(Appointment TargetAppontment){
        date = new Date(TargetAppontment.getDate());
        timeslot = new Timeslot(TargetAppontment.getTimeslot());
        patient = new Patient((Patient)TargetAppontment.getPatient());
        provider = TargetAppontment.getProvider();
    }

    
    /** 
     * get date
     * @return Date
     */
    public Date getDate(){
        return date;
    }
    /**
     * get Timeslot
     * @return timeslot
     */
    public Timeslot getTimeslot(){
        return timeslot;
    }
    /**
     * get patient Info
     * @return patient
     */
    public Person getPatient(){
        return patient;
    }
    /**
     * get Provider Info
     * @return provider
     */
    public Person getProvider(){
        return provider;
    }


    @Override public String toString(){
        String result = "";
        result += date.toString() + " ";
        result.toLowerCase();
        result += timeslot.toString() + " ";
        result += patient.toString() + " ";
        result += provider.toString();

        return result;
    }
    @Override public int compareTo(Appointment TargetAppontment){
        return this.toString().compareTo(TargetAppontment.toString());
    }
    @Override public boolean equals(Object other){
        Appointment TargetAppontment = (Appointment)other;
        if(this.date.equals(TargetAppontment.getDate()) && this.timeslot == TargetAppontment.getTimeslot() && this.patient.equals(TargetAppontment.getPatient()) && this.provider == TargetAppontment.getProvider()){
            return true;
        }

        return false;
    }


    //Test
    /*
    public static void main(String[] args)
    {
        Date da = new Date(10, 16, 2001);
        Profile patient1 = new Profile("Tianxiang", "Huang", da);
        

        Date appDa = new Date(12, 31, 2024);
        Appointment app1 = new Appointment(appDa, Timeslot.SLOT1, patient1, Provider.KAUR);

        System.out.println(app1.toString());

        Date da2 = new Date(8, 12, 1984);
        Profile patient2 = new Profile("Atton", "Foor", da2);
        

        Date appDa2 = new Date(2, 1, 2036);
        Appointment app2 = new Appointment(appDa2, Timeslot.SLOT4, patient2, Provider.CERAVOLO);

        System.out.println(app1.equals(app2));
    }
    */

    /*
    public static void main(String[] args){
        Date da = new Date(10, 16, 2001);
        Patient pa1 = new Patient(new Profile("Tianxiang", "Huang", da));


    }
    */

}
