package projecttwo;
import projectone.Provider;

/**
 * The TechnicianRotator class manages the rotation of technicians.
 * It supports initializing with an array or list of technicians, retrieving the current technician, 
 * and moving to the next technician in the rotation.
 * 
 * It also provides methods to access the list of technicians and handles edge cases, such as empty lists.
 * 
 * {@code @author:}Tianxiang Huang
 * The TechnicianRotator class manages the rotation of technicians.
 * It supports initializing with an array or list of technicians, retrieving the current technician,
 * and moving to the next technician in the rotation.
 * It also provides methods to access the list of technicians and handles edge cases, such as empty lists.
 */

public class TechnicianRotator {
    Technician[] TechnicianList;
    private int Rotator = 0;

    /**
     * Constructor to initialize TechnicianRotator with an array of Technicians.
     * @param TechList The array of Technicians
     */
    public TechnicianRotator(Technician[] TechList) {
        TechnicianList = TechList;
        if (TechnicianList.length == 0) {
            Rotator = -1;
        }
    }

    /**
     * Constructor to initialize TechnicianRotator with a list of Providers.
     * @param providerList The list of Providers
     */
    public TechnicianRotator(List<Provider> providerList) {
        int count = 0;
        for (Provider p : providerList) {
            if (p instanceof Technician) {
                count += 1;
            }
        }
        if (count != 0) {
            TechnicianList = new Technician[count];
            int index = 1;
            for (Provider p : providerList) {
                if (p instanceof Technician) {
                    TechnicianList[TechnicianList.length - index] = (Technician) p;
                    index += 1;
                }
            }
        }
    }

    /**
     * Get the current rotated Technician.
     * @return The current Technician, or null if none
     */
    public Technician getTechnician() {
        Technician target;
        try {
            target = TechnicianList[Rotator];
        } catch (Exception e) {
            return null;
        }
        return target;
    }

    /**
     * Jump to the next rotation.
     */
    public void jumpNext() {
        if (Rotator == -1) {
            return;
        }
        Rotator += 1;
        if (Rotator >= TechnicianList.length) {
            Rotator = 0;
        }
    }

    /**
     * Get the list of Technicians.
     * @return The array of Technicians
     */
    public Technician[] getTechnicianList() {
        return TechnicianList;
    }
}
