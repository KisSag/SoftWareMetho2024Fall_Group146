package projectone;
import projecttwo.Person;

/**
 * {@code @author:} Tianxiang Huang
 */
public abstract class Provider extends Person {
    protected Location location;
    protected int Credit;

    /**
     * Get provider's location.
     * @return Provider's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Get provider's credit.
     * @return Provider's credit.
     */
    public int getCredit() {
        return Credit;
    }

    /**
     * Change provider's credit.
     * @param scaler The amount to change the credit by
     */
    public abstract void changeCredit(int scaler);

    /**
     * Get the price per visit of the provider.
     * @return Price per visit
     */
    public abstract int getPrice();

    /**
     * Rate the provider.
     * @return Rating of the provider
     */
    public abstract int rate();
}
