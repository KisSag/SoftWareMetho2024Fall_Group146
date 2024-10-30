package projecttwo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import projectone.*;
public class ListProviderTest {

    @Test
    public void testAdd() {
        List<Provider> providerList = new List<>();
        Doctor doctor = new Doctor(new Profile("Huang", "Tianxiang", new Date(5, 15, 1990)), Location.CLARK, Specialty.PEDIATRICIAN, "12345");
        Technician technician = new Technician(new Profile("Eric", "Wang", new Date(6, 10, 1985)), Location.CLARK, "50");

        // Test adding a Doctor object
        providerList.add(doctor);
        assertTrue(providerList.contains(doctor), "Doctor should be added to the list.");

        // Test adding a Technician object
        providerList.add(technician);
        assertTrue(providerList.contains(technician), "Technician should be added to the list.");
    }

    @Test
    public void testRemove() {
        List<Provider> providerList = new List<>();
        Doctor doctor = new Doctor(new Profile("Huang", "Tianxiang", new Date(5, 15, 1990)), Location.CLARK, Specialty.PEDIATRICIAN, "12345");
        Technician technician = new Technician(new Profile("Eric", "Wang", new Date(6, 10, 1985)), Location.CLARK, "50");

        // Add doctor and technician to the list
        providerList.add(doctor);
        providerList.add(technician);

        // Test removing a Doctor object
        providerList.remove(doctor);
        assertFalse(providerList.contains(doctor), "Doctor should be removed from the list.");

        // Test removing a Technician object
        providerList.remove(technician);
        assertFalse(providerList.contains(technician), "Technician should be removed from the list.");
    }
}