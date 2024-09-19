import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
            System.out.println(getSystemDate(6,0,0).toString());
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

        if(checkAppointmentValid(appointment)){
            AppointmentList.add(appointment);
            System.out.println(appointment.toString());
            return true;
        }

        return false;
        
    }
    private boolean checkAppointmentValid(Appointment appointment){
        //if same Appointment already exist
        if(AppointmentList.contains(appointment)){
            System.out.println("Date not avaliable: Same Appointment exist");
            return false;
        }
        //if slot is not avaliable
        if(AppointmentList.getAppointment_byCondition(appointment.getDate(), appointment.getTimeslot(), appointment.getProvider()) != null){
            System.out.println("Date not avaliable: Appointment conflict at current date");
            return false;
        }
        if(!checkAppointmentValid_DatePart(appointment)){
            return false;
        }
        return true;
    }
    private boolean checkAppointmentValid_DatePart(Appointment appointment){
        //if Appointment day is is correct format
        if(!appointment.getDate().isValid()){
            System.out.println("Date not avaliable: Invalid Date Input");
            return false;
        }
        //if Appointment day is before today
        if(checkDateValid(appointment.getDate()) <= 0){
            System.out.println("Date not avaliable: Date can not before today or today");
            return false;
        }
        //if Appointment day is out of six month
        if(checkDateValid(appointment.getDate()) == 2){
            System.out.println("Date not avaliable: Date should within 6 month");
            return false;
        }
        //check if Appointment day is weekend
        Calendar calndr = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        try{
            calndr.setTime(sdf.parse(appointment.getDate().toString()));
        }catch(Exception e){
            System.out.println("Date can not be convert to Calendar object");
            return false;
        }
        int day = calndr.get(Calendar.DAY_OF_WEEK);
        if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
            System.out.println("Date not avaliable: Date is Weekend");
            return false;
        }
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
        if(date == null || checkDateValid(date) > 0){
            System.out.println("Invalid Patient Birthday");
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

    private int checkDateValid(Date TargetDate){
        int six_month_offset = 6;
        
        //if TargetDate is today
        if(TargetDate.compareTo(getSystemDate(0,0,0)) == 0){
            return 0;
        }

        //if TargetDate is before today, return -1
        if(TargetDate.compareTo(getSystemDate(0,0,0)) == -1){
            return -1;
        }
        //if TargetDate is after today within 6 month, return 1
        if(TargetDate.compareTo(getSystemDate(six_month_offset,0,0)) != 1){
            return 1;
        }else{
            return 2;
        }
        
    }

    public Date getSystemDate(int MonthOffset, int DayOffset, int YearOffset){

        Calendar calndr = Calendar.getInstance();
        calndr.add(Calendar.MONTH, MonthOffset);
        calndr.add(Calendar.DATE, DayOffset);
        calndr.add(Calendar.YEAR, YearOffset);

        String DateString =  new SimpleDateFormat("MM/dd/YYYY").format(calndr.getTime());
        return generateDate_FromString(DateString.split("/")[0], DateString.split("/")[1], DateString.split("/")[2]);
    }
}
