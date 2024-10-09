package projecttwo;
import projectone.*;

public class Doctor extends Provider {

    private Specialty specialty;
    private String npi; 

    /**
     * constructor
     * @param pro
     * @param spe
     * @param n
     */
    public Doctor(Profile pro, Location loc, Specialty spe, String n){
        profile = pro;
        location = loc;
        specialty = spe;
        npi = n;
    }


    /**
     * get specialty
     * @return specialty
     */
    public Specialty getSpecialty(){
        return specialty;
    }

    /**
     * get NPI of doctor
     * @return npi
     */
    public String getNPI(){
        return npi;
    }
    
    /**
     * get NPI of doctor in interger format
     * @return npi interger
     */
    public int getNPI_InNumber(){
        return Integer.parseInt(npi);
    }

    /**
     * get Location
     * @return Location
     */
    public Location getLocation(){
        return location;
    }
    @Override public int rate() {
        return -1;
    }
    @Override public int compareTo(Person TargetPerson){
        return profile.compareTo(TargetPerson.getProfile());
    }
    @Override public boolean equals(Object other){
        if(other.getClass() != this.getClass()){
            return false;
        }

        Doctor TargetDoctor = (Doctor)other; 
        return this.profile.equals(TargetDoctor.getProfile());
    }
    @Override public String toString(){
        return "[" + profile.toString() + ", " + location.toString() + " ] [" + specialty.toString() + " #" + npi + "]";
    }
}
