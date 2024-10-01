package projecttwo;
import projectone.*;
////////delete java.util after List class is done, these are just for test//////////
import java.util.ArrayList;
import java.util.List;
////////////////////////////////////////////////////////////////////////////////////

/////Necessary Lib class to read from file/////
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
///////////////////////////////////////////////

public class ClinicManager {

    static List<Provider> providerList = new ArrayList<Provider>();

    public static void main(String[] args){
        Date da = new Date(10, 16, 2001);
        Patient pa1 = new Patient(new Profile("Tianxiang", "Huang", da));

        readProvider_FromFile();

        Appointment app1 = new Appointment(da, generateTimeSlot_ByIndex(Integer.toString(2)), pa1, providerList.get(2));
        
        System.out.println(app1.toString());
    }


    /**
     * try to read Provider File;
     */
    private static void readProvider_FromFile(){
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
    private static void decodeProviderLine(String Provider_String){

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
    private static Date generateDate_FromString(String DateString){
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

      return date;
    }

    /**
     * generate location ENUM from string
     * @param Location_String
     * @return location ENUM if exist, return null else
     */
    private static Location generateLocation_FromString(String Location_String){
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
    private static Specialty getSpecialty_FromString(String Specialty_String){
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
    private static Timeslot generateTimeSlot_ByIndex(String index_String){
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
}
