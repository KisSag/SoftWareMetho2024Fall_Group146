/**
 * Last Modified: 9/29/2024
 * @author: Tianxiang Huang, Jayden Hsu
 * Test: Done
 * 
 */
public class MedicalRecord { 
    public static final int LENGTH_INITIALLIST = 4; 
    public static final int LENGTH_INCREASECAPACITY = 4; 
    
    private Patient[] patients; 
    private int size; //number of patient objects in the array

    public MedicalRecord(){
        patients = new Patient[LENGTH_INITIALLIST];
        size = 0;
    }


    public void add(Appointment appointment){
        //check if patient is exist
        int TargetIndex = getIndexPatient(appointment.getPatient());

        if(TargetIndex == -1){
            generateNewPatient(appointment);
        }else{
            patients[TargetIndex].addFinishedAppointment(appointment);
        }

    }

    private void generateNewPatient(Appointment appointment){
        for(int i = 0; i < patients.length; i += 1){
            if(patients[i] == null){
                patients[i] = new Patient(appointment);
                size++; //Increment the size when a new patient is added
                return;
            }
        }

        grow();
        patients[patients.length - LENGTH_INCREASECAPACITY] = new Patient(appointment);
        size++; //Increment the size when the array grows and a new patient is added
    }

    public int countPatientNumber(){
        return size;
    }

    public int getIndexPatient(Profile pa){
        
        for(int i = 0; i < patients.length; i += 1){
            if(patients[i] != null && patients[i].getProfile().equals(pa)){
                return i;
            }
        }

        return -1;
    }

    public Patient getPatient_byIndex(int index){
        return patients[index];
    }

    public Patient[] getPatientsList(){
        return patients;
    }
    
    private void grow(){

        Patient[] new_patients = new Patient[patients.length + LENGTH_INCREASECAPACITY];
        for(int i = 0; i < patients.length; i += 1){
            new_patients[i] = patients[i];
        }
        patients = new_patients;
    } //helper method to increase the capacity by 4 

        public static void main(String[] args) {
        MedicalRecord record = new MedicalRecord();

        // Create test data
        Profile profile1 = new Profile("Jayden", "Hsu", new Date(1, 1, 1990));
        Profile profile2 = new Profile("Evan", "Shwartz", new Date(2, 2, 1992));
        Profile profile3 = new Profile("Andy", "Zhu", new Date(3, 3, 1985));
        Profile profile4 = new Profile("Dana", "Chan", new Date(4, 4, 1988));
        Profile profile5 = new Profile("William", "Lee", new Date(5, 5, 1983));

        Appointment appointment1 = new Appointment(new Date(11, 21, 2024), Timeslot.SLOT1, profile1, Provider.PATEL);
        Appointment appointment2 = new Appointment(new Date(12, 25, 2024), Timeslot.SLOT2, profile2, Provider.LIM);
        Appointment appointment3 = new Appointment(new Date(1, 1, 2025), Timeslot.SLOT3, profile3, Provider.PATEL);
        Appointment appointment4 = new Appointment(new Date(2, 2, 2025), Timeslot.SLOT4, profile4, Provider.LIM);
        Appointment appointment5 = new Appointment(new Date(3, 3, 2025), Timeslot.SLOT5, profile5, Provider.PATEL);


        //Test case 1: Add a patient via an appointment
        record.generateNewPatient(appointment1);
        System.out.println("Test case 1: Number of patients after adding 1st patient: " + record.countPatientNumber()); //Expected: 1

        //Test case 2: Add second patient
        record.generateNewPatient(appointment2);
        System.out.println("Test case 2: Number of patients after adding 2nd patient: " + record.countPatientNumber()); //Expected: 2

        //Test case 3: Add third patient
        record.generateNewPatient(appointment3);
        System.out.println("Test case 3: Number of patients after adding 3rd patient: " + record.countPatientNumber()); //Expected: 3

        //Test case 4: Add fourth patient (should not trigger growth, array is now full)
        record.generateNewPatient(appointment4);
        System.out.println("Test case 4: Number of patients after adding 4th patient: " + record.countPatientNumber()); //Expected: 4

        //Test case 5: Add fifth patient (should trigger array growth)
        record.generateNewPatient(appointment5);
        System.out.println("Test case 5: Number of patients after adding 5th patient: " + record.countPatientNumber()); //Expected: 5

        //Test case 7: Retrieve patient by index
        System.out.println("Test case 2: " + record.getPatient_byIndex(1).getProfile().getFirstName()); //Expected: Evan
    }

} 
