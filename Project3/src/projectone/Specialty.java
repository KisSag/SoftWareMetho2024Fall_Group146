package projectone;

/**
 * {@code @author:} Tianxiang Huang, Jayden Hsu
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int Charge;

    /**
     * Constructor for Specialty enum
     * @param Charge the charge associated with the specialty
     */
    Specialty(int Charge) {
        this.Charge = Charge;
    }

    /**
     * Get the charge for the specialty.
     * @return the charge for the specialty
     */
    public int getCharge() {
        return Charge;
    }

}
