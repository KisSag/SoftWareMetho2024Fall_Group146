package projecttwo;
import projectone.*;


public class Person implements Comparable<Person>{
    protected Profile profile;



    /**
     * get Profile of this person
     * @return profile of Person
     */
    public Profile getProfile(){
        return profile;
    }

    @Override public int compareTo(Person TargetPerson) {
        return profile.compareTo(TargetPerson.getProfile());
    }
    @Override public boolean equals(Object other){
        Person TargetProfile = (Person)other;
        if(profile.equals(TargetProfile.getProfile())){
            return true;
        }
        return false;
    }
    @Override public String toString(){
        return profile.toString();
    }


}
