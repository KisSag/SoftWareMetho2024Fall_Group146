import java.util.Scanner;
public class Scheduler {

    public static final int INDEX_COMMAND = 0;
    public static final int INDEX_APPONTMENTDATE = 1;
    public static final int INDEX_TIMESLOT = 2;
    public static final int INDEX_FIRSTNAME = 3;
    public static final int INDEX_LASTNAME = 4;
    public static final int INDEX_BIRTHDAY = 5;
    public static final int INDEX_PROVIDER = 6;

    public static final int VALID_STRINGLENGTH_S_COMMAND = 7;


    public static boolean ProgrammeRunner = true;
    public static List AppointmentList = new List();
    public static MedicalRecord medicalRecord = new MedicalRecord();


    public void run(){
        Scanner CommandScanner = new Scanner(System.in);
        System.out.println("Scheduler is running.");

        while(ProgrammeRunner){
            readCommand(CommandScanner.nextLine());
        }

        CommandScanner.close();
        System.out.println("Scheduler terminated");
    }











    private void readCommand(String CommandLine){

        if(CommandLine.length() == 0){
            return;
        }
        String[] CommandList = CommandLine.split(",");

        //get first command
        switch (CommandList[INDEX_COMMAND]) {
            case "Q"://Quit Program
                ProgrammeRunner = false;
                break;
            case "S"://add new appointment
                Appointment NewAppointment = generateAppointment(CommandList);
                if(NewAppointment == null){
                    System.out.println("Invalid Input");
                }else{
                    addAppointmentToList(NewAppointment);
                }
                break;
            case "C"://Cancel a appointment
                if(cancelAppointment(CommandList)){
                    System.out.println("Cancel Successful");
                }else{
                    System.out.println("Cancel Failed");
                }
                break;
            case "PA":
                AppointmentList.printByAppointment();
                break;
            case "PP":
                AppointmentList.printByPatient();
                break;
            case "PL":
                AppointmentList.printByLocation();
                break;

            default:
                break;
        }
    }


    boolean addAppointmentToList(Appointment appointment){
        AppointmentList.add(appointment);
        System.out.println(appointment.toString());
        return true;
    }

    boolean cancelAppointment(String[] commandArray){
        Appointment TargetAppointment = null;
        try{
            TargetAppointment = AppointmentList.getAppointment_byCondition(generateDate_FromString(commandArray[1].split("/")[0], commandArray[1].split("/")[1], commandArray[1].split("/")[2]),generateTimeSlot_FromString(commandArray[2]), generateProfile_FromString(commandArray[3], commandArray[4], commandArray[5]));
        }catch(Exception e){
            return false;
        }
        
        if(TargetAppointment == null){
            return false;
        }else{
            AppointmentList.remove(TargetAppointment);
            return true;
        }
    }


    Appointment generateAppointment(String[] commandArray){
        if(commandArray.length != VALID_STRINGLENGTH_S_COMMAND){
            return null;
        }

        Date date = null;
        Timeslot slot = null;
        Profile patient = null;
        Provider provider = null;

        try{
            date = generateDate_FromString(commandArray[INDEX_APPONTMENTDATE].split("/")[0], commandArray[INDEX_APPONTMENTDATE].split("/")[1], commandArray[INDEX_APPONTMENTDATE].split("/")[2]);
            slot = generateTimeSlot_FromString(commandArray[INDEX_TIMESLOT]);
            patient = generateProfile_FromString(commandArray[INDEX_FIRSTNAME], commandArray[INDEX_LASTNAME], commandArray[INDEX_BIRTHDAY]);
            provider = generateProvider_FromString(commandArray[INDEX_PROVIDER]);
        }catch(Exception e){
            return null;
        }
        
        //Invalid Generate
        if(date == null || slot == null || patient == null || provider == null){
            return null;
        }

        return new Appointment(date, slot, patient, provider);
    }

    private Date generateDate_FromString(String Month, String Day, String Year){
        Date date;
        try{
            date = new Date(Integer.parseInt(Month), Integer.parseInt(Day), Integer.parseInt(Year));
        }catch(Exception e){
            return null;
        }
        return date;
    }
    private Profile generateProfile_FromString(String fn, String ln, String Date_String){
        Date date = generateDate_FromString(Date_String.split("/")[0], Date_String.split("/")[1], Date_String.split("/")[2]);
        if(date == null){
            return null;
        }

        Profile profile;
        try{
            profile = new Profile(fn, ln, date);
        }catch(Exception e){
            return null;
        }

        return profile;
    }
    private Timeslot generateTimeSlot_FromString(String Slot){
        try{
            return Timeslot.values()[Integer.parseInt(Slot)-1];
        }catch(Exception e){
            return null;
        }
    }
    private Provider generateProvider_FromString(String provider_String){
        for (Provider provider_Iterator : Provider.values()) {
            if(provider_Iterator.name().toLowerCase().equals(provider_String.toLowerCase())){
                return provider_Iterator;
            }
        }
        return null;
    }
}
