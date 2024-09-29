/**
 * Last Modified: 9/29/2024
 * Name: Tianxiang Huang, Jayden Hsu
 * Test: Done
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

    public static void main(String[] args) {
            //Test case 1: Verify country for BRIDGEWATER
            System.out.println("Test case 1: " + Location.BRIDGEWATER.getCountry()); // Expected output: "Somerset"
            
            //Test case 2: Verify zip code for EDISON
            System.out.println("Test case 2: " + Location.EDISON.getZip()); // Expected output: "08817"
            
            //Test case 3: Verify location exists and is correct (PISCATAWAY)
            System.out.println("Test case 3: " + Location.PISCATAWAY.getCountry() + " " + Location.PISCATAWAY.getZip());//Expected output: "Middlesex 08817"
            
            //Test case 4: Ensure two locations with the same zip return correct results (EDISON and PISCATAWAY)
            boolean sameZip = Location.EDISON.getZip().equals(Location.PISCATAWAY.getZip());
            System.out.println("Test case 4: EDISON and PISCATAWAY have same zip? " + sameZip); //Expected output: true
            
            //Test case 5: Verify country for a different location (MORRISTOWN)
            System.out.println("Test case 5: " + Location.MORRISTOWN.getCountry()); //Expected output: "Morris"
        }
}
