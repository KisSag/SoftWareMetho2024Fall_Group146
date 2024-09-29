package clinic;

/**
 * Last Modified: 9/16/2024
 * {@code @author:} Tianxiang Huang
 * Test: No Test Yet
 * 
 */
public class Appointment implements Comparable<Appointment>{
    private Date date; 
    private Timeslot timeslot; 
    private Profile patient; 
    private Provider provider; 

    /*Constructor */
    public Appointment(){
        date = null;
        timeslot = null;
        patient = null;
        provider = null;
    }

    public Appointment(Date da, Timeslot ti, Profile pa, Provider pr){
        date = new Date(da);
        timeslot = ti;
        patient = new Profile(pa);
        provider = pr;
    }
    //Deep Copy Already
    public Appointment(Appointment TargetAppontment){
        date = new Date(TargetAppontment.getDate());
        timeslot = TargetAppontment.getTimeslot();
        patient = new Profile(TargetAppontment.getPatient());
        provider = TargetAppontment.getProvider();
    }

    
    /** 
     * @return Date
     */
    /*Get Information */
    public Date getDate(){
        return date;
    }
    public Timeslot getTimeslot(){
        return timeslot;
    }
    public Profile getPatient(){
        return patient;
    }
    public Provider getProvider(){
        return provider;
    }


    @Override public String toString(){
        String result = "";
        result += date.toString() + " ";
        result.toLowerCase();
        result += timeslot.getString_TimeSlot() + " ";
        result += patient.toString() + " ";
        result += provider.getString_ProviderInfo();

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

}
