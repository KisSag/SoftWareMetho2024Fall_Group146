public class Visit { 
    private Appointment appointment; //a reference to an appointment object
    private Visit next; //a ref. to the next appointment object in the list

    //constructor
    public Visit(){
        appointment = null;
        next = null;
    }
    public Visit(Appointment app){
        appointment = app;
        next = null;
    }

    public void assignNextFinishedVisit(Visit visit){
        next = visit;
    }


    //get data
    public Appointment getCurrentAppointment(){
        return appointment;
    }
    public Visit getNextFinishedVisit(){
        return next;
    }
    //it will always return the end of the linkList;
    public Visit getLastedFinishedVisit(){
        Visit VisitRunenr = this;
        while(VisitRunenr.next != null){
            VisitRunenr = VisitRunenr.next;
        }
        return VisitRunenr;
    }

   
} 
