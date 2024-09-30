package projecttwo;

import projectone.*;

public class Technician extends Provider {

    private int ratePerVisit;

    /**
     * Constructor
     * @param pro
     * @param loc
     * @param ratePrice
     */
    public Technician(Profile pro, Location loc, String ratePrice){
        profile = pro;
        location = loc;
        ratePerVisit = Integer.parseInt(ratePrice);
    }

    /**
     * get ratePerVisit
     * @return ratePervisit
     */
    public int getRatePerVisit(){
        return ratePerVisit;
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

        Technician TargetDoctor = (Technician)other; 
        return this.profile.equals(TargetDoctor.getProfile());
    }
    @Override public String toString(){
        return profile.toString() + location.toString() + Integer.toString(ratePerVisit);
    }
}
