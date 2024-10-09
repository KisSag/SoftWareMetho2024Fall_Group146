package projectone;
import projecttwo.Person;

public abstract class Provider extends Person{
    protected Location location;
    
    /**
     * get provider's location
     * @return return provider's location.
     */
    public Location getLocation(){
        return location;
    }

    public abstract int rate();
}
