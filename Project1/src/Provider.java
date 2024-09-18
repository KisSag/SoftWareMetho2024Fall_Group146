/**
 * Last Modified: 9/16/2024
 * Name: Tianxiang Huang
 * Test: No Test Yet
 * 
 */
public enum Provider {
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN);


    private final Location location;
    private final Specialty specialty; 

    Provider(Location location, Specialty specialty){
        this.location = location;
        this.specialty = specialty;
    }

    public Location getLocation(){
        return location;
    }
    public Specialty getSpecialty(){
        return specialty;
    }
    public String getString_ProviderInfo(){
        String result = "[" + this.name() + ", " + location.name() + ", " + location.getCountry() + " " + location.getZip() + ", " + specialty.name() + "]";
        return result;
    }
}
