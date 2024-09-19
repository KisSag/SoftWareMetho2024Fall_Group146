public class MedicalRecord { 
    public static final int LENGTH_INITIALLIST = 4; 
    public static final int LENGTH_INCREASECAPACITY = 4; 
    
    private Patient[] patients; 
    private int size; //number of patient objects in the array

    public MedicalRecord(){
        patients = new Patient[LENGTH_INITIALLIST];
        size = 0;
    }


    public void addPatient(Patient pa){

        //Patien already exist
        if(getIndexPatient(pa) != -1){
            return;
        }

        //add to blankspot
        for(int i = 0; i < patients.length; i += 1){
            if(patients[i] == null){
                patients[i] = pa;
                return;
            }
        }

        //if list is full
        grow();
        patients[patients.length - LENGTH_INCREASECAPACITY] = pa;

    }

    public int countPatientNumber(){
        return size;
    }

    public int getIndexPatient(Patient pa){
        
        for(int i = 0; i < patients.length; i += 1){
            if(patients[i] != null && patients[i].equals(pa)){
                return i;
            }
        }

        return -1;
    }

    public Patient getPatient_byIndex(int index){
        return patients[index];
    }

    private void grow(){

        Patient[] new_patients = new Patient[patients.length + LENGTH_INCREASECAPACITY];
        for(int i = 0; i < patients.length; i += 1){
            new_patients[i] = patients[i];
        }
        patients = new_patients;
    } //helper method to increase the capacity by 4 
} 
