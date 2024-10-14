package projecttwo;

import projectone.Appointment;
import projectone.Provider;

public class Sort {

    // Sort appointments based on the key provided using bubble sort
    public static void appointment(List<Appointment> list, char key) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (shouldSwapAppointments(list.get(j), list.get(j + 1), key)) {
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    // Helper method to determine if two appointments should be swapped based on the key
    private static boolean shouldSwapAppointments(Appointment a1, Appointment a2, char key) {
        switch (key) {
            case 'D': // Sort by date
                return a1.getDate().compareTo(a2.getDate()) > 0;
            case 'T': // Sort by timeslot
                return a1.getTimeslot().compareTo(a2.getTimeslot()) > 0;
            case 'P': // Sort by patient profile
                return a1.getPatient().getProfile().compareTo(a2.getPatient().getProfile()) > 0;
            default:
                throw new IllegalArgumentException("Invalid sorting key: " + key);
        }
    }

    // Sort providers by their profile using bubble sort
    public static void provider(List<Provider> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getProfile().compareTo(list.get(j + 1).getProfile()) > 0) {
                    Provider temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
