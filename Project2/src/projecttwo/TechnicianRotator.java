package projecttwo;
import projectone.Provider;

public class TechnicianRotator {
    Technician[] TechnicianList;
    private int Rotator = 0;

    public TechnicianRotator(Technician[] TechList){
        TechnicianList = TechList;
        if(TechnicianList.length == 0){
            Rotator = -1;
        }
    }

    public TechnicianRotator(List<Provider> providerList){
        int count = 0;
        for(Provider p : providerList){
            if(p instanceof Technician){
                count += 1;
            }
      }

      if(count != 0){
        TechnicianList = new Technician[count];
        int index = 1;

        for(Provider p : providerList){
            if(p instanceof Technician){
                TechnicianList[TechnicianList.length - index] = (Technician)p;
                System.out.println(TechnicianList[TechnicianList.length - index].toString());
                index += 1;
            }
        }
      }
      
    }

    /**
     * get Current Rotate Technician
     * @return
     */
    public Technician getTechnician(){

        Technician target;
        try{
            target = TechnicianList[Rotator];
        }catch(Exception e){
            return null;
        }

        return target;
    }

    /**
     * jump to next Rotation
     */
    public void jumpNext(){
        if(Rotator == -1){
            return;
        }

        Rotator += 1;
        if(Rotator >= TechnicianList.length){
            Rotator = 0;
        }
    }


}
