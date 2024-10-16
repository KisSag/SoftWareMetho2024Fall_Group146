package projecttwo;

import projectone.Appointment;
import projectone.Location;
import projectone.Provider;

/**
 * The Sort class provides sorting utilities for appointments and persons.
 * It supports sorting appointments based on date, location, or patient, and 
 * persons based on their profile using the bubble sort algorithm.
 * 
 * The sorting is controlled by a key that determines the sorting criteria 
 * for appointments, and by profile attributes for persons.
 * 
 * {@code @author:} J-JHsu, Tianxiang Huang
 */

public class Sort {

    /**
     * Sort appointments based on the key provided using bubble sort.
     * @param list The list of appointments to sort
     * @param key The key to sort by ('D' for date, 'L' for location, 'P' for patient)
     */
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

    /**
     * Helper method to determine if two appointments should be swapped based on the key.
     * @param a1 The first appointment
     * @param a2 The second appointment
     * @param key The key to sort by
     * @return true if the appointments should be swapped, false otherwise
     */
    private static boolean shouldSwapAppointments(Appointment a1, Appointment a2, char key) {
        switch (key) {
            case 'D': // Sort by date
                if (a1.getDate().compareTo(a2.getDate()) > 0 ||
                        (a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0) ||
                        (a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) == 0 && a1.getProvider().compareTo(a2.getProvider()) > 0)) {
                    return true;
                } else {
                    return false;
                }
            case 'L': // Sort by Location
                Location loc1 = ((Provider) (a1.getProvider())).getLocation();
                Location loc2 = ((Provider) (a2.getProvider())).getLocation();
                if (loc1.getCountry().compareTo(loc2.getCountry()) > 0 ||
                        (loc1.getCountry().compareTo(loc2.getCountry()) == 0 && a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (loc1.getCountry().compareTo(loc2.getCountry()) == 0 && a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    return true;
                } else {
                    return false;
                }
            case 'P': // Sort by patient profile
                if (a1.getPatient().getProfile().getName_LF().compareTo(a2.getPatient().getProfile().getName_LF()) > 0 ||
                        (a1.getPatient().getProfile().getName_LF().compareTo(a2.getPatient().getProfile().getName_LF()) == 0 && a1.getPatient().getProfile().getBirthDay().compareTo(a2.getPatient().getProfile().getBirthDay()) > 0) ||
                        (a1.getPatient().getProfile().getName_LF().compareTo(a2.getPatient().getProfile().getName_LF()) == 0 && a1.getPatient().getProfile().getBirthDay().compareTo(a2.getPatient().getProfile().getBirthDay()) == 0 && a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (a1.getPatient().getProfile().getName_LF().compareTo(a2.getPatient().getProfile().getName_LF()) == 0 && a1.getPatient().getProfile().getBirthDay().compareTo(a2.getPatient().getProfile().getBirthDay()) == 0 && a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    return true;
                } else {
                    return false;
                }
            default:
                throw new IllegalArgumentException("Invalid sorting key: " + key);
        }
    }

    /**
     * Sort persons by their profile using bubble sort.
     * @param list The list of persons to sort
     */
    public static void Person(List<Person> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getProfile().getName_LF().compareTo(list.get(j + 1).getProfile().getName_LF()) > 0 ||
                        (list.get(j).getProfile().getName_LF().compareTo(list.get(j + 1).getProfile().getName_LF()) == 0 && list.get(j).getProfile().getBirthDay().compareTo(list.get(j + 1).getProfile().getBirthDay()) > 0)) {
                    Person temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}

