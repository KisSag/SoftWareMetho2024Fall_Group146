package projecttwo;

/**
 * {@code @author:} J-JHsu
 * The Radiology enum represents different types of radiology services provided in the clinic.
 * This enum also overrides the toString() method to return the name of the radiology service.
 *
 */
public enum Radiology {
    XRAY,  // X-ray service
    ULTRASOUND,  // Ultrasound service
    CATSCAN;  // CAT scan service

    // Override toString()
    @Override
    public String toString() {
        return name();
    }
}
