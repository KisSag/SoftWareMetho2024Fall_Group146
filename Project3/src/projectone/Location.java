package projectone;

/**
 * {@code @author:} Tianxiang Huang, Jayden Hsu
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

    /**
     * Constructor for Location enum
     * @param Country the county of the location
     * @param Zip the zip code of the location
     */
    Location(String Country, String Zip) {
        this.Country = Country;
        this.Zip = Zip;
    }

    /**
     * Get the county of the location
     * @return the county of the location
     */
    public String getCountry() {
        return Country;
    }

    /**
     * Get the zip code of the location
     * @return the zip code of the location
     */
    public String getZip() {
        return Zip;
    }

    /**
     * Returns a string representation of the location
     * @return the name of the location, followed by the county and zip code
     */
    @Override
    public String toString() {
        return this.name() + ", " + Country + " " + Zip;
    }
}
