package projectone;
import projecttwo.Person;

public abstract class Provider extends Person{
    protected Location location;
    
    public abstract int rate();
}
