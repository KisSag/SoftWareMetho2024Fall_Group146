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
                return;
            }
        }

        grow();
        patients[patients.length - LENGTH_INCREASECAPACITY] = new Patient(appointment);
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
} 
