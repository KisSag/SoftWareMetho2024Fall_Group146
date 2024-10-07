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
