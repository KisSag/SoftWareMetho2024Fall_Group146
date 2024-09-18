/**
 * Last Modified: 9/16/2024
 * Name: Tianxiang Huang
 * Test: No Test Yet
 * 
 */
public enum Location {
    BRIDGEWATER ("Somerset", "08807"),
    EDISON ("Middlesex", "08817"),
    PISCATAWAY ("Middlesex", "08817"),
    PRINCETON ("Mercer", "08542"),
    MORRISTOWN ("Morris", "07960"),
    CLARK ("Union", "07066");

    private final String Country; 
    private final String Zip;

    Location(String Country, String Zip){
        this.Country = Country;
        this.Zip = Zip;
    }

    public String getCountry(){
        return Country;
    }
    public String getZip(){
        return Zip;
    }
}
