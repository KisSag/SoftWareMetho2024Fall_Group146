package projecttwo;
import projectone.*;

/////Necessary Lib class to read from file/////
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
///////////////////////////////////////////////

import java.util.Calendar;
/**
 *  {@code @author:} Tianxiang Huang
 */
public class ClinicManager {

    private List<Provider> providerList = new List<Provider>();
    private List<Appointment> AppointmentList = new List<Appointment>();
    private TechnicianRotator technicianRotator;
    private static MedicalRecord medicalRecord = new MedicalRecord();
    private boolean ProgrammeRunner = true;

    /**
     * Main Loop of the programme
     */
    public void run(){

      initializePreLoadData();
      printProviderInfo();
      
      Scanner CommandScanner = new Scanner(System.in);
        System.out.println("\nClinic Manager is running.");
        while(ProgrammeRunner){
            readCommand(CommandScanner.nextLine());
        }

        CommandScanner.close();
        System.out.println("Clinic Manager terminated");
    }

    /**
     * process each line of Command
     * @param CommandLine
     */
    void readCommand(String CommandLine){
      if(CommandLine.isBlank()){return;}

      String[] Command_Seped = CommandLine.split(",");
      //if NO INPUT
      if(CommandLine.length() < 1 || Command_Seped.length < 1){return;}

      switch (Command_Seped[0]) {
        case "D":scheduleAppointment_Doctor(CommandLine);break;
        case "Q":ProgrammeRunner = false;break;
        case "T":scheduleAppointment_Imaging(CommandLine);break;
        case "C":cancelAppointment(CommandLine);break;
        case "R":rescheduleAppointment(CommandLine);break;
        case "PA":displayAppointment_Sorted_Date(); break;
        case "PP":displayAppointment_Sorted_Patient(); break;
        case "PL":displayAppointment_Sorted_Location(); break;
        case "PC":printProviderCredit();break;
        case "PS":calculateBill();break;
        case "PI":displayAppointment_Imaging(); break;
        case "PO":displayAppointment_Office(); break;
        default:System.out.println("Invalid Command");break;
      }

    }

    /**
     * read and decode all the info from string with command schedule Imaging
     * @param CommandLine
     */
    void scheduleAppointment_Imaging(String CommandLine){
      final int ProperCommandLine_Length = 7;
      String[] CommandLine_Sp = CommandLine.split(",");
      //check length
      if(CommandLine_Sp.length != ProperCommandLine_Length){System.out.println("Missing date tokens."); return;}
      //check appointmentDate
      Date AppointmentDate = generateDate_FromString(CommandLine_Sp[1]);
      if(AppointmentDate == null){System.out.println(CommandLine_Sp[1] + " is not a valid calendar date"); return;}
      //check timeslot
      Timeslot timeslot = generateTimeSlot_ByIndex(CommandLine_Sp[2]);
      if(timeslot == null){System.out.println(CommandLine_Sp[2] + " is not a valid Timeslot.");return;}
      //check PatientDob
      Date PatientDob = generateDate_FromString(CommandLine_Sp[5]);
      if(PatientDob == null){System.out.println("Patient dob: " + CommandLine_Sp[5] + " is not a valid calendar date");return;}
      //check patient
      Patient patient = generatePatient_ByString(CommandLine_Sp[3], CommandLine_Sp[4], PatientDob);
      if(patient == null){return;}
      //check radiology
      Radiology radio = generateRadiology_ByString(CommandLine_Sp[6]);
      if(radio == null){System.out.println(CommandLine_Sp[6] + " - imaging service not provided.");return;}
      //get technician
      Technician technician = generatePossibleTechnician(AppointmentDate, timeslot, radio);
      if(technician == null){System.out.println("Cannot find an available technician at all locations for " + CommandLine_Sp[6] + "at slot " + CommandLine_Sp[2]);return;}
      //final check
      Appointment finalcheckAppointment = scheduleAppointment_finalCheck(new Imaging(AppointmentDate, timeslot, patient, technician, radio));
      if(finalcheckAppointment != null){
        System.out.println(finalcheckAppointment.toString() + " booked.");
      }
    }

    /**
     * read and decode all the info from string with command schedule doctor
     * @param CommandLine
     */
    void scheduleAppointment_Doctor(String CommandLine){
      final int ProperCommandLine_Length = 7;
      String[] CommandLine_Sp = CommandLine.split(",");
      //check length
      if(CommandLine_Sp.length != ProperCommandLine_Length){
        System.out.println("Missing date tokens.");
        return;
      }
      //check appointmentDate
      Date AppointmentDate = generateDate_FromString(CommandLine_Sp[1]);
      if(AppointmentDate == null){
        System.out.println(CommandLine_Sp[1] + " is not a valid calendar date");
        return;
      }
      //check timeslot
      Timeslot timeslot = generateTimeSlot_ByIndex(CommandLine_Sp[2]);
      if(timeslot == null){
        System.out.println(CommandLine_Sp[2] + " is not a valid Timeslot.");
        return;
      }
      //check PatientDob
      Date PatientDob = generateDate_FromString(CommandLine_Sp[5]);
      if(PatientDob == null){
        System.out.println("Patient dob: " + CommandLine_Sp[5] + " is not a valid calendar date");
        return;
      }
      //check patient
      Patient patient = generatePatient_ByString(CommandLine_Sp[3], CommandLine_Sp[4], PatientDob);
      if(patient == null){
        return;
      }
      //check npi
      Doctor doc = (Doctor)generateProvider_npi_ByString(CommandLine_Sp[6]);
      if(doc == null){
        return;
      }
      //final check
      Appointment finalcheckAppointment = scheduleAppointment_finalCheck(new Appointment(AppointmentDate, timeslot, patient, doc));
      if(finalcheckAppointment != null){System.out.println(finalcheckAppointment.toString() + " booked.");}
    }

    /**
     * try to cancel a appointment
     * @param CommandLine
     */
    void cancelAppointment(String CommandLine){
      final int ProperCommandLine_Length = 6;
      String[] CommandLine_Sp = CommandLine.split(",");
      //check length
      if(CommandLine_Sp.length != ProperCommandLine_Length){
        System.out.println("Missing date tokens.");
        return;
      }
      //check appointmentDate
      Date AppointmentDate = generateDate_FromString(CommandLine_Sp[1]);
      if(AppointmentDate == null){
        System.out.println(CommandLine_Sp[1] + " is not a valid calendar date");
        return;
      }
      //check timeslot
      Timeslot timeslot = generateTimeSlot_ByIndex(CommandLine_Sp[2]);
      if(timeslot == null){
        System.out.println(CommandLine_Sp[2] + " is not a valid Timeslot.");
        return;
      }
      //check PatientDob
      Date PatientDob = generateDate_FromString(CommandLine_Sp[5]);
      if(PatientDob == null){
        System.out.println("Patient dob: " + CommandLine_Sp[5] + " is not a valid calendar date");
        return;
      }
      //check patient
      Patient patient = generatePatient_ByString(CommandLine_Sp[3], CommandLine_Sp[4], PatientDob);
      if(patient == null){return;}
      //find appointment
      Appointment TargetAppontment = findAppointment(patient, AppointmentDate, timeslot);
      if(TargetAppontment == null){
        System.out.println(CommandLine_Sp[1] + " " + timeslot.toString() + " " + patient.toString() + " - appointment does not exist.");
        return;
      }
      AppointmentList.remove(TargetAppontment);
      Provider provider = (Provider)TargetAppontment.getProvider();
      provider.changeCredit(-1);
      System.out.println(CommandLine_Sp[1] + " " + timeslot.toString() + " " + patient.toString() + " - appointment has been canceled.");
    }

    /**
     * try to reschedule a appointment
     * @param CommandLine
     */
    void rescheduleAppointment(String CommandLine){
      final int ProperCommandLine_Length = 7;
      String[] CommandLine_Sp = CommandLine.split(",");
      //check length
      if(CommandLine_Sp.length != ProperCommandLine_Length){
        System.out.println("Missing date tokens.");
        return;
      }
      //check appointmentDate
      Date AppointmentDate = generateDate_FromString(CommandLine_Sp[1]);
      if(AppointmentDate == null){System.out.println(CommandLine_Sp[1] + " is not a valid calendar date"); return;}
      //check timeslot
      Timeslot timeslot = generateTimeSlot_ByIndex(CommandLine_Sp[2]);
      if(timeslot == null){System.out.println(CommandLine_Sp[2] + " is not a valid Timeslot.");return;}
      //check PatientDob
      Date PatientDob = generateDate_FromString(CommandLine_Sp[5]);
      if(PatientDob == null){System.out.println("Patient dob: " + CommandLine_Sp[5] + " is not a valid calendar date");return;}
      //check patient
      Patient patient = generatePatient_ByString(CommandLine_Sp[3], CommandLine_Sp[4], PatientDob);
      if(patient == null){return;}
      //find appointment
      Appointment TargetAppontment = findAppointment(patient, AppointmentDate, timeslot);
      if(TargetAppontment == null){System.out.println(CommandLine_Sp[1] + " " + timeslot.toString() + " " + patient.toString() + " - appointment does not exist.");return;}
      //get new Timeslot
      Timeslot timeslot_new = generateTimeSlot_ByIndex(CommandLine_Sp[6]);
      if(timeslot_new == null){System.out.println(CommandLine_Sp[6] + " is not a valid Timeslot.");return;}
      //find new imag
      //schedule new appointment
      Appointment modifiedAppointment = new Appointment(TargetAppontment.getDate(), timeslot_new, TargetAppontment.getPatient(), TargetAppontment.getProvider());
      //cancel old appointment
      Appointment finalcheckAppointment = scheduleAppointment_finalCheck(modifiedAppointment);
      if(finalcheckAppointment != null){AppointmentList.remove(TargetAppontment); Provider pro = (Provider)TargetAppontment.getProvider(); pro.changeCredit(-1); System.out.println("Rescheduled to " + finalcheckAppointment.toString());}
    }

    /**
     * print providerCredit
     */
    void printProviderCredit(){
      System.out.println("** Credit amount ordered by provider. **");
      int providerIndex = 1;

      List<Person> PersonList = new List<Person>();
      for(Provider pro : providerList){
        PersonList.add(pro);
      }
      Sort.Person(PersonList);

      for(Person per : PersonList){
        Provider pro = (Provider)per;
        String output = "(" + Integer.toString(providerIndex) + ") " + pro.getProfile().toString() + " [Credit amount: $" + Integer.toString(pro.getCredit()) + ".00]";
        System.out.println(output);
        providerIndex += 1;
      }
      System.out.println("** end of list **\n");
    }

    /**
     * calculate all the bill for patient and remove ALL appointment
     */
    void calculateBill(){
        if(AppointmentList.size() != 0){medicalRecord.add(AppointmentList);}
        Patient[] PatientList = medicalRecord.getPatientsList();
        if(PatientList == null || PatientList.length == 0){System.out.println("No Medical Record");return;}

        List<Person> PersonList = new List<Person>();
        for(int i = 0; i < PatientList.length; i += 1){
          if(PatientList[i] != null){
            PersonList.add(PatientList[i]);
          }
        }
        Sort.Person(PersonList);
        System.out.println("** Billing statement **");
        int index = 1;
        for(Person per : PersonList){
          Patient pa = (Patient)per;
          for(int i = 0; i < PatientList.length; i += 1){
            if(PatientList[i] == null){
              continue;
            }

            if(pa.equals(PatientList[i])){
              System.out.println("(" +Integer.toString(index) +  ") " + PatientList[i].toString() + " [due: $" + PatientList[i].charge() + ".00]");
              index += 1;
            }
          }
        }

        System.out.println("** End of List **");
        AppointmentList = new List<Appointment>();
    }
    
    /**
     * display the imaging appointment
     */
    void displayAppointment_Imaging(){
      List<Appointment> Appointment_Filted = new List<Appointment>();
      for(Appointment app : AppointmentList){
        if(app instanceof Imaging){
          Appointment_Filted.add(app);
        }
      }
      if(Appointment_Filted.size() == 0){
        System.out.println("Radiology Schedule is empty.");
        return;
      }
      Sort.appointment(Appointment_Filted, 'L');

      System.out.println("** List of radiology appointments ordered by county/date/time. **");
      for(Appointment ima : Appointment_Filted){
        System.out.println(ima.toString());
      }
      System.out.println("** end of list **\n");

      
    }

    /**
     * display the office appointment
     */
    void displayAppointment_Office(){
      List<Appointment> Appointment_Filted = new List<Appointment>();
      for(Appointment app : AppointmentList){
        if(!(app instanceof Imaging)){
          Appointment_Filted.add(app);
        }
      }
      if(Appointment_Filted.size() == 0){
        System.out.println("Office Schedule is empty.");
        return;
      }
      Sort.appointment(Appointment_Filted, 'L');

      System.out.println("** List of office appointments ordered by county/date/time. **");
      for(Appointment app : Appointment_Filted){
        System.out.println(app.toString());
      }
      System.out.println("** end of list **\n");
    }
    
    /**
     * sort appointment by date
     */
    void displayAppointment_Sorted_Date(){
      if(AppointmentList.size() == 0){
        System.out.println("Schedule Calendar is empty.");
        return;
      }
      System.out.println("** List of appointments, ordered by date/time/provider. **");
      Sort.appointment(AppointmentList, 'D');
      displayALLAppointment();
      System.out.println("** end of list **\n");
    }

    /**
     * sort appointment by patient
     */
    void displayAppointment_Sorted_Patient(){
      if(AppointmentList.size() == 0){
        System.out.println("Schedule Calendar is empty.");
        return;
      }
      System.out.println("** List of appointments, ordered by patient/date/time. **");
      Sort.appointment(AppointmentList, 'P');
      displayALLAppointment();
      System.out.println("** end of list **\n");
    }

    /**
     * sort appointment by Location
     */
    void displayAppointment_Sorted_Location(){
      if(AppointmentList.size() == 0){
        System.out.println("Schedule Calendar is empty.");
        return;
      }
      System.out.println("** List of appointments, ordered by county/date/time. **");
      Sort.appointment(AppointmentList, 'L');
      displayALLAppointment();
      System.out.println("** end of list **\n");
    }

    /**
     * simply print all appointment from list
     */
    private void displayALLAppointment(){
      for(Appointment app : AppointmentList){
        System.out.println(app.toString());
      }
    }

    /**
     * finale check if an appointment is able to add into appointment List.
     * @param appointment
     */
    private Appointment scheduleAppointment_finalCheck(Appointment appointment){

      //check if date is valid in calendar
      if(!checkvalidAppointmentDate_Calendar(appointment.getDate())){
        return null;
      }

      //check if appointment is conflict
      if(!checkvalidAppointment_Conflict(appointment)){
        return null;
      }

      AppointmentList.add(appointment);
      
      //change potential credit for provider
      Provider provider = (Provider)appointment.getProvider();
      provider.changeCredit(1);
      if(appointment instanceof Imaging){
        technicianRotator.jumpNext();
      }
      
      return appointment;
    }

    /**
     * check if the appointment date is valid in calendar(not check conflict)
     * @param TargetDate
     * @return true if date is valid in calendar, false else
     */
    private boolean checkvalidAppointmentDate_Calendar(Date TargetDate){

      //check date is possible in calendar
      if(!TargetDate.isValid()){
        System.out.println("Appointment date: " + TargetDate.toString() + " is not a valid calendar date.");
        return false;
      }
      //check if is befor or today
      if(TargetDate.compareTo(getSystemDate(0,0,0)) == 0 || TargetDate.compareTo(getSystemDate(0,0,0)) == -1){
        System.out.println("Appointment date: " + TargetDate.toString() + " is today or a date before today.");
        return false;
      } 
      
      //check if is in six month
      if(TargetDate.compareTo(getSystemDate(6,0,0)) == 1){
        System.out.println("Appointment date: " + TargetDate.toString() + " is not within six months.");
        return false;
      }

      //check if is weekend.
      Calendar calndr = Calendar.getInstance();
      try{
        calndr.set(TargetDate.getYear(), TargetDate.getMonth() - 1, TargetDate.getDay());
      }catch(Exception e){
        System.out.println("Date can not be convert to Calendar object");
        return false;
      }
      int day = calndr.get(Calendar.DAY_OF_WEEK);
      if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
        System.out.println("Appointment date: " + TargetDate.toString() + " is Saturday or Sunday.");
        return false;
      }

      return true;
    }

    /**
     * check if Appointment is conflict with exist appointment, it will iterate through the list and check one by one
     * @param TargetApp
     * @return true if no conflict, false else
     */
    private boolean checkvalidAppointment_Conflict(Appointment TargetApp){

      for (Appointment app : AppointmentList) {
        if(app.checkAppointmentConflict_Provider(TargetApp)){
          System.out.println(TargetApp.toString() + " is conflict");
          return false;
        }

        if(app.checkAppointmentConflict_Patient(TargetApp)){
          System.out.println(TargetApp.getPatient().toString() + " has an existing appointment at the same time slot.");
          return false;
        }
      }

      return true;
    }

    /**
     * Pre load neccessary data for programme
     */
    private void initializePreLoadData(){
      readProvider_FromFile();
      technicianRotator = new TechnicianRotator(providerList);
    }

    /**
     * print preload Info of Provider List
     */
    private void printProviderInfo(){
      System.out.println("Providers loaded to the list.");
      for(Provider pro : providerList){
        System.out.println(pro.toString());
      }

      System.out.println("\nRotation list for the technicians.");
      String tech_Result = "";
      for(int i = 0; i < technicianRotator.getTechnicianList().length; i += 1){
        if(i != 0){
          tech_Result += " --> ";
        }

        tech_Result += technicianRotator.getTechnicianList()[i].getProfile().getFirstName() + " " +technicianRotator.getTechnicianList()[i].getProfile().getLastName() + " (" + technicianRotator.getTechnicianList()[i].getLocation().name() + ")";
      }

      System.out.println(tech_Result);
    }

    /**
     * try to read Provider File;
     */
    private void readProvider_FromFile(){
        try {
            File myFile = new File("providers.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              decodeProviderLine(data);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred: ");
            e.printStackTrace();
          }
    }

    /**
     * try to get all the info from one line of providerFile
     * @param Provider_String
     */
    private void decodeProviderLine(String Provider_String){

      //if line is empty
      if(Provider_String == ""){
        return;
      }
      String[] Provider_String_Seped = Provider_String.split("\\s+");

      switch (Provider_String_Seped[0]) {
        case "D":
          if(Provider_String_Seped.length != 7){
            return;
          }
          try{
            providerList.add(new Doctor(new Profile(Provider_String_Seped[1], Provider_String_Seped[2], generateDate_FromString(Provider_String_Seped[3])), generateLocation_FromString(Provider_String_Seped[4]), getSpecialty_FromString(Provider_String_Seped[5]), Provider_String_Seped[6]));
          }catch(Exception e){
            System.out.println("Invalid Provider information");
          }
          break;
        case "T":
        if(Provider_String_Seped.length != 6){
          return;
        }
        try{
          providerList.add(new Technician(new Profile(Provider_String_Seped[1], Provider_String_Seped[2], generateDate_FromString(Provider_String_Seped[3])), generateLocation_FromString(Provider_String_Seped[4]), Provider_String_Seped[5]));
        }catch(Exception e){
          System.out.println("Invalid Provider information");
        }
          break;
        default:
          System.out.println("Invalid Provider information");
          return;
      }
    }

    /**
     * generate Date from string, format: m/d/y
     * @param DateString
     * @return date if success, return null if failed.
     */
    private Date generateDate_FromString(String DateString){
      String[] dateString_Sep = DateString.split("/");
      if(dateString_Sep.length != 3){
        return null;
      }

      int month = -1;
      try{
        month = Integer.parseInt(dateString_Sep[0]);
      }catch(Exception e){
        return null;
      }

      int day = -1;
      try{
        day = Integer.parseInt(dateString_Sep[1]);
      }catch(Exception e){
        return null;
      }

      int year = -1;
      try{
        year = Integer.parseInt(dateString_Sep[2]);
      }catch(Exception e){
        return null;
      }

      Date date = new Date(month, day, year);

      if(date.isValid()){
        return date;
      }else{
        return null;
      }

    }

    /**
     * generate location ENUM from string
     * @param Location_String
     * @return location ENUM if exist, return null else
     */
    private Location generateLocation_FromString(String Location_String){

      Location_String = Location_String.trim();

      for(Location loc_iterator : Location.values()){
        if(loc_iterator.name().toLowerCase().equals(Location_String.toLowerCase())){
          return loc_iterator;
        }
      }
      
      return null;
    }

    /**
     * generate Specialty ENUM from string
     * @param Specialty_String
     * @return Specialty ENUM if exist, return null else.
     */
    private Specialty getSpecialty_FromString(String Specialty_String){

      Specialty_String = Specialty_String.trim();

      for(Specialty spe_iterator : Specialty.values()){
        if(spe_iterator.name().toLowerCase().equals(Specialty_String.toLowerCase())){
          return spe_iterator;
        }
      }

      return null;
    }

    /**
     * generate a TimeSlot with index in range 1-12
     * @param index_String
     * @return timeslop if success, return null else
     */
    private Timeslot generateTimeSlot_ByIndex(String index_String){
      int index = -1;
      try{
        index = Integer.parseInt(index_String);
      }catch(Exception e){
        System.out.println("Invalid TimeSlot: " + index_String);
        return null;
      }

      //if out of range
      if(index < 1 || index > 12){
        return null;
      }

      int hour = 0;
      //generate Hour
      if(index <= 6){
        hour = 9 + (index - 1) / 2;
      }else{
        hour = 14 + (index - 7) / 2;
      }

      int minus = 30 * (1 - index % 2);

      return new Timeslot(hour, minus);
    }

    /**
     * generatePatient from string, it will check first name, last name, and if birthday is befor today
     * @param fn
     * @param ln
     * @param Dob
     * @return return patient if all info correct, return null else.
     */
    private Patient generatePatient_ByString(String fn, String ln, Date Dob){

      //check first name
      if(fn.matches(".*\\\\d.*") || fn == ""){
        System.out.println(fn + " " + ln + " is not a valid name");
        return null;
      }
      //check last name
      if(ln.matches(".*\\\\d.*") || ln == ""){
        System.out.println(fn + " " + ln + " is not a valid name");
        return null;
      }
      //check birthday
      if(Dob.compareTo(getSystemDate(0, 0, 0)) > 0){
        System.out.println("Patient dob: " + Dob.toString() + " is not a valid date.");
        return null;
      }

      return new Patient(new Profile(fn, ln, Dob));
    }

    /**
     * try to find Doctor in provider list
     * @param npi_String
     * @return Doctor as Provider class, return null if not found or npi format wrong
     */
    private Provider generateProvider_npi_ByString(String npi_String){
      int npi;
      try{
        npi = Integer.parseInt(npi_String);
      }catch(Exception e){
        System.out.println(npi_String + " is not a valid NPI");
        return null;
      }

      for(Provider pro : providerList){
        if(pro.getClass() == projecttwo.Doctor.class){
          Doctor providerTemp = (Doctor)pro;
          if(providerTemp.getNPI_InNumber() == npi){
            return pro;
          }
        }
      }

      System.out.println("provider not found with npi: " + npi_String);
      return null;

    }

    /**
     * get Date by system, all offset with 0 means current date
     * @param MonthOffset
     * @param DayOffset
     * @param YearOffset
     * @return the Date with offset, that use system date as pivot 
     */
    private Date getSystemDate(int MonthOffset, int DayOffset, int YearOffset){
      int INDEX_OFFSET = 1;

      Calendar calndr = Calendar.getInstance();
      //calndr.setLenient(false);
      calndr.add(Calendar.MONTH, MonthOffset + INDEX_OFFSET);
      calndr.add(Calendar.DATE, DayOffset);
      calndr.add(Calendar.YEAR, YearOffset);

      return new Date(calndr.get(Calendar.MONTH), calndr.get(Calendar.DAY_OF_MONTH), calndr.get(Calendar.YEAR));
    }
    
    /**
     * get radiology from string
     * @param Radio_String
     * @return radiology class if founded, return null else.
     */
    private Radiology generateRadiology_ByString(String Radio_String){

      Radio_String = Radio_String.trim();

      for(Radiology rad : Radiology.values()){
        if(Radio_String.toLowerCase().equals(rad.name().toLowerCase())){
          return rad;
        }
      }

      return null;
    }
    
    /**
     * generate valid technician from providerList
     * @param date
     * @param timeslot
     * @param rad
     * @return return possible technician, return null if not found
     */
    private Technician generatePossibleTechnician(Date date, Timeslot timeslot, Radiology rad){

      Technician init = technicianRotator.getTechnician();
      if(checkTechnicianValid(date, timeslot, init, rad)){
        return init;
      }

      technicianRotator.jumpNext();

      while(technicianRotator.getTechnician() != init){

        if(checkTechnicianValid(date, timeslot, technicianRotator.getTechnician(), rad)){
          init = technicianRotator.getTechnician();
          //System.out.println("good: " + init.toString());
          return init;
        }else{
          technicianRotator.jumpNext();
        }

      }
      return null;
    }
    
    /**
     * check if a technician is valid
     * @param date
     * @param timeslot
     * @param tech
     * @param rad
     * @return return true if technician is valid, return false else
     */
    private boolean checkTechnicianValid(Date date, Timeslot timeslot, Technician tech, Radiology rad){
      for (Appointment appointment : AppointmentList) {
        if(appointment instanceof Imaging){
          Imaging imag_app = (Imaging)appointment;
          if(imag_app.checkAppointmentConflict(date, timeslot, tech, rad)){
            return false;
          }
        }
      }
      return true;
    }

    /**
     * find appointment with given condition
     * @param pa
     * @param da
     * @param ts
     * @return return appointment class if founded, return null else.
     */
    private Appointment findAppointment(Patient pa, Date da, Timeslot ts){
      for(Appointment app : AppointmentList){
        if(app.findAppointment(pa, da, ts)){
          return app;
        }
      }
      return null;
    }
}
