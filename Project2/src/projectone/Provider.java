package projectone;
import projecttwo.Person;

public abstract class Provider extends Person{
    protected Location location;
    protected int Credit;
    /**
     * get provider's location
     * @return return provider's location.
     */
    public Location getLocation(){
        return location;
    }
    /**
     * get provider's credit
     * @return return credit
     */
    public int getCredit(){
        return Credit;
    }

    /**
     * change Provider's credit
     * @param amount
     */
    public abstract void changeCredit(int scaler);
    
    /**
     * get price per visit of provider
     * @return price ver visit
     */
    public abstract int getPrice();

    public abstract int rate();
}
