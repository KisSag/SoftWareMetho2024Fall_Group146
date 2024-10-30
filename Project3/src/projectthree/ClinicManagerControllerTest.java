package projectthree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectone.*;
import projecttwo.*;

public class ClinicManagerControllerTest {

    private ClinicManagerController controller;

    @Before
    public void setUp() {
        controller = new ClinicManagerController();
        controller.initializePreLoadData();
    }

    @Test
    public void testGenerateDate_FromString_ValidDate() {
        Date date = controller.generateDate_FromString("12/25/2023");
        assertNotNull(date);
        assertEquals(12, date.getMonth());
        assertEquals(25, date.getDay());
        assertEquals(2023, date.getYear());
    }

    @Test
    public void testGenerateDate_FromString_InvalidDate() {
        Date date = controller.generateDate_FromString("13/32/2023");
        assertNull(date);
    }

    @Test
    public void testGenerateTimeSlot_ByIndex_ValidIndex() {
        Timeslot timeslot = controller.generateTimeSlot_ByIndex("3");
        assertNotNull(timeslot);
        assertEquals(10, timeslot.getHour());
        assertEquals(0, timeslot.getMinutes());
    }

    @Test
    public void testGenerateTimeSlot_ByIndex_InvalidIndex() {
        Timeslot timeslot = controller.generateTimeSlot_ByIndex("13");
        assertNull(timeslot);
    }

    @Test
    public void testGeneratePatient_ByString_ValidPatient() {
        Date dob = new Date(5, 15, 1990);
        Patient patient = controller.generatePatient_ByString("John", "Doe", dob);
        assertNotNull(patient);
        assertEquals("John", patient.getProfile().getFirstName());
        assertEquals("Doe", patient.getProfile().getLastName());
        assertEquals(dob, patient.getProfile().getBirthDay());
    }

    @Test
    public void testGeneratePatient_ByString_InvalidName() {
        Date dob = new Date(5, 15, 1990);
        Patient patient = controller.generatePatient_ByString("", "Doe", dob);
        assertNull(patient);
    }

    @Test
    public void testGenerateProvider_npi_ByString_ValidNPI() {
        Provider provider = controller.generateProvider_npi_ByString("01");
        assertNotNull(provider);
        assertTrue(provider instanceof Doctor);
    }

    @Test
    public void testGenerateProvider_npi_ByString_InvalidNPI() {
        Provider provider = controller.generateProvider_npi_ByString("999");
        assertNull(provider);
    }

    @Test
    public void testGenerateRadiology_ByString_ValidRadiology() {
        Radiology radiology = controller.generateRadiology_ByString("xray");
        assertNotNull(radiology);
        assertEquals(Radiology.XRAY, radiology);
    }

    @Test
    public void testGenerateRadiology_ByString_InvalidRadiology() {
        Radiology radiology = controller.generateRadiology_ByString("mri");
        assertNull(radiology);
    }

    @Test
    public void testCheckvalidAppointmentDate_Calendar_ValidDate() {
        Date validDate = new Date(4, 14 , 2025);
        assertTrue(controller.checkvalidAppointmentDate_Calendar(validDate));
    }

    @Test
    public void testCheckvalidAppointmentDate_Calendar_PastDate() {
        Date pastDate = new Date(controller.getSystemDate(-1, 0, 0).getMonth(),
                controller.getSystemDate(-1, 0, 0).getDay(),
                controller.getSystemDate(-1, 0, 0).getYear());
        assertFalse(controller.checkvalidAppointmentDate_Calendar(pastDate));
    }

    @Test
    public void testCheckvalidAppointmentDate_Calendar_WeekendDate() {
        // Assuming 2023-07-15 is a Saturday
        Date weekendDate = new Date(7, 15, 2023);
        assertFalse(controller.checkvalidAppointmentDate_Calendar(weekendDate));
    }

    @Test
    public void testCheckvalidAppointment_Conflict_NoConflict() {
        Date appointmentDate = new Date(8, 1, 2023);
        Timeslot timeslot = new Timeslot(9, 0);
        Patient patient = new Patient(new Profile("John", "Doe", new Date(1, 1, 1990)));
        Provider provider = new Doctor(new Profile("Dr", "Smith", new Date(1, 1, 1980)),
                Location.BRIDGEWATER, Specialty.FAMILY, "120");
        Appointment newAppointment = new Appointment(appointmentDate, timeslot, patient, provider);
        assertTrue(controller.checkvalidAppointment_Conflict(newAppointment));
    }

    @Test
    public void testCheckvalidAppointment_Conflict_Conflict() {
        Date appointmentDate = new Date(8, 1, 2023);
        Timeslot timeslot = new Timeslot(9, 0);
        Patient patient = new Patient(new Profile("John", "Doe", new Date(1, 1, 1990)));
        Provider provider = new Doctor(new Profile("Dr", "Smith", new Date(1, 1, 1980)),
                Location.BRIDGEWATER, Specialty.FAMILY, "120");
        Appointment existingAppointment = new Appointment(appointmentDate, timeslot, patient, provider);
        controller.AppointmentList.add(existingAppointment);

        Appointment newAppointment = new Appointment(appointmentDate, timeslot, patient, provider);
        assertFalse(controller.checkvalidAppointment_Conflict(newAppointment));
    }
}
