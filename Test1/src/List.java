/**
 * Last Modified: 9/17/2024
 * Name: Tianxiang Huang
 * Test: basic Test
 * 
 */
public class List { 
    public static final int LENGTH_INITIALLIST = 4; 
    public static final int LENGTH_INCREASECAPACITY = 4; 
    private Appointment[] appointments; 
    private int size;//number of appointments in the array 

    //Constructor
    public List(){
        appointments = new Appointment[LENGTH_INITIALLIST];
        updateValidAppointmentsSize();
    }
    public List(Appointment Initial_App){
        appointments = new Appointment[LENGTH_INITIALLIST];
        appointments[0] = Initial_App;
        updateValidAppointmentsSize();
    }

    private void updateValidAppointmentsSize(){
        int SizeTemp = 0;
        for(int i = 0; i < appointments.length; i += 1){
            if(appointments[i] != null){
                SizeTemp += 1;
            }
        }

        size = SizeTemp;
    }

    private int find(Appointment appointment){
        for(int i = 0; i < appointments.length; i += 1){
            if(appointments[i] != null && appointment.equals(appointments[i])){
                return i;
            }
        }
        return -1;
    } //helper method
    
    private void grow(){

        Appointment[] new_Appointments = new Appointment[appointments.length + LENGTH_INCREASECAPACITY];
        for(int i = 0; i < appointments.length; i += 1){
            new_Appointments[i] = appointments[i];
        }
        appointments = new_Appointments;
    } //helper method to increase the capacity by 4 
    
    public boolean contains(Appointment appointment){
        if(find(appointment) == -1){
            return false;
        }

        return true;
    }//check before add/remove 
    
    public void add(Appointment appointment){
        //if appointment already exist
        if(contains(appointment)){
            return;
        }

        //find a empty index in array
        for(int i = 0; i < appointments.length; i += 1){
            if(appointments[i] == null){
                appointments[i] = appointment;
                updateValidAppointmentsSize();
                return;
            }
        }

        //if array is fill
        grow();
        appointments[appointments.length - LENGTH_INCREASECAPACITY] = appointment;
        updateValidAppointmentsSize();
    }
    
    public void remove(Appointment appointment){
        //if appointment not exist
        if(!contains(appointment)){
            return;
        }
        //Remove
        appointments[find(appointment)] = null;
        updateValidAppointmentsSize();
    }

    public int getSize_ValidAppointments(){
        return size;
    }
    public Appointment getAppointment(int index){
        if(index < 0 || index >= appointments.length){
            return null;
        }

        return appointments[index];
    }
    


    public void printByPatient(){//ordered by patient profile, date/timeslot 
        Appointment[] arrTemp = generateCleanAppointmentArray(appointments);
        printByPatient_helper_bubbleSort(arrTemp, arrTemp.length);
    
    }
    //bubble sort, if there's better one?
    void printByPatient_helper_bubbleSort(Appointment arr[], int n)
    {
        Appointment temp;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getPatient().compareTo(arr[j + 1].getPatient()) > 0) {//check name
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }else if(arr[j].getPatient().compareTo(arr[j + 1].getPatient()) == 0 && arr[j].getDate().compareTo(arr[j + 1].getDate()) > 0){//if name same, check date
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }else if(arr[j].getPatient().compareTo(arr[j + 1].getPatient()) == 0 && arr[j].getDate().compareTo(arr[j + 1].getDate()) == 0 && arr[j].getTimeslot().ordinal() > arr[j + 1].getTimeslot().ordinal()){//if name and date same, check day slot
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false){
                break;
            }
        }
        outputSortedResult(arr); //output the result
    }

    public void printByLocation(){//ordered by county, date/timeslot
        Appointment[] arrTemp = generateCleanAppointmentArray(appointments);
        printByLocation_helper_bubbleSort(arrTemp, arrTemp.length);
    } 
    void printByLocation_helper_bubbleSort(Appointment arr[], int n){
        Appointment temp;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getProvider().getLocation().getCountry().compareTo(arr[j + 1].getProvider().getLocation().getCountry()) > 0) {//check country
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }else if(arr[j].getProvider().getLocation().getCountry().compareTo(arr[j + 1].getProvider().getLocation().getCountry()) == 0 && arr[j].getDate().compareTo(arr[j + 1].getDate()) > 0){//if country same, check date
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }else if(arr[j].getProvider().getLocation().getCountry().compareTo(arr[j + 1].getProvider().getLocation().getCountry()) == 0 && arr[j].getDate().compareTo(arr[j + 1].getDate()) == 0 && arr[j].getTimeslot().ordinal() > arr[j + 1].getTimeslot().ordinal()){//if country and date same, check day slot
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false){
                break;
            }
        }
        outputSortedResult(arr); //output the result
    }

    public void printByAppointment(){
        Appointment[] arrTemp = generateCleanAppointmentArray(appointments);
        printByAppointment_helper_bubbleSort(arrTemp, arrTemp.length);

    }
    void printByAppointment_helper_bubbleSort(Appointment arr[], int n){
        Appointment temp;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getDate().compareTo(arr[j + 1].getDate()) > 0) {//check date
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }else if(arr[j].getDate().compareTo(arr[j + 1].getDate()) == 0 && arr[j].getTimeslot().ordinal() > arr[j + 1].getTimeslot().ordinal()){//if date same, check slot
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }else if(arr[j].getProvider().getLocation().getCountry().compareTo(arr[j + 1].getProvider().getLocation().getCountry()) == 0 && arr[j].getTimeslot().ordinal() == arr[j + 1].getTimeslot().ordinal() && arr[j].getProvider().name().compareTo(arr[j + 1].getProvider().name()) > 1){//if date and slot same, check provider name
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false){
                break;
            }
        }
        outputSortedResult(arr); //output the result
    }









    /*
    public void printByAppointment()//ordered by date/timeslot, provider name
    */

    private void outputSortedResult(Appointment[] AppontArr){
        for(int i = 0; i < AppontArr.length; i += 1){
            System.out.println(AppontArr[i].toString() + "");
        }
    }

    private Appointment[] generateCleanAppointmentArray(Appointment[] oldArr){//this method will return a new appointmentArray with no empty plot
        Appointment[] arrTemp = new Appointment[size];
        int index_Temp = 0;
        for(int i = 0; i < oldArr.length; i += 1){
            if(oldArr[i] != null){
                arrTemp[index_Temp] = oldArr[i];
                index_Temp += 1;
            }
        }

        return arrTemp;
    }


    ///////////////////////////////
    //////////Test below///////////
    ///////////////////////////////

    /*
    public static void main(String[] args)
    {
        Date da = new Date(10, 16, 2001);
        Profile patient1 = new Profile("Tianxiang", "Huang", da);
        Date appDa = new Date(12, 31, 2024);
        Appointment app1 = new Appointment(appDa, Timeslot.SLOT1, patient1, Provider.KAUR);

        Date da2 = new Date(8, 12, 1984);
        Profile patient2 = new Profile("Atton", "Klan", da2);
        Date appDa2 = new Date(8, 15, 4098);
        Appointment app2 = new Appointment(appDa2, Timeslot.SLOT3, patient2, Provider.ZIMNES);

        Date da3 = new Date(8, 12, 1984);
        Profile patient3 = new Profile("Atton", "Foor", da3);
        Date appDa3 = new Date(2, 1, 2036);
        Appointment app3 = new Appointment(appDa3, Timeslot.SLOT4, patient3, Provider.CERAVOLO);

        
        //System.out.println(app2.toString());


        List appList = new List();
        appList.add(app1);
        appList.add(app2);
        appList.add(app3);

        appList.printByAppointment();
    }
    */
} 
