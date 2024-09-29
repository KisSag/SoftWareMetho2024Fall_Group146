/**
 * Last Modified: 9/29/2024
 * Name: Tianxiang Huang, Jayden Hsu
 * Test: Done
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

    public static void main(String[] args) {
        // Test case 1: Verify location for provider PATEL
        System.out.println("Test case 1: Provider PATEL location: " + Provider.PATEL.getLocation().name()); 
        // Expected output: BRIDGEWATER

        // Test case 2: Verify specialty for provider PATEL
        System.out.println("Test case 2: Provider PATEL specialty: " + Provider.PATEL.getSpecialty().name()); // Expected output: FAMILY

        // Test case 3: Verify full provider information string for KAUR
        System.out.println("Test case 3: Provider KAUR full info: " + Provider.KAUR.getString_ProviderInfo()); // Expected output: [KAUR, PRINCETON, USA 08540, ALLERGIST]

        // Test case 4: Verify charge for the provider LIM (Pediatrician)
        System.out.println("Test case 4: Provider LIM specialty charge: " + Provider.LIM.getSpecialty().getCharge()); // Expected output: 300

        // Test case 5: Compare two providers by location and specialty
        boolean isSameSpecialty = Provider.PATEL.getSpecialty().equals(Provider.LIM.getSpecialty());
        System.out.println("Test case 5: Do PATEL and LIM have the same specialty? " + isSameSpecialty); // Expected output: false (PATEL is FAMILY, LIM is PEDIATRICIAN)

        // Test case 6: Compare two providers from different locations
        boolean isSameLocation = Provider.KAUR.getLocation().equals(Provider.RAMESH.getLocation());
        System.out.println("Test case 6: Do KAUR and RAMESH work in the same location? " + isSameLocation); // Expected output: false (KAUR works in PRINCETON, RAMESH works in MORRISTOWN)
    }
    
}
