package projecttwo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    @Test
    public void testCompareTo() {
        Profile profile1 = new Profile("Jayden", "Hsu", new Date(5, 15, 1990));
        Profile profile2 = new Profile("Evan", "Shwartz", new Date(5, 15, 1985));
        Profile profile3 = new Profile("Eric", "Wang", new Date(6, 10, 1990));
        Profile profile4 = new Profile("Jayden", "Hsu", new Date(5, 15, 1990)); 

        // Test cases where compareTo() should return -1
        assertEquals(-1, profile1.compareTo(profile3), "Profile1 should come before Profile3 (different last name).");
        assertEquals(-1, profile1.compareTo(profile2), "Profile1 should come after Profile2 (same last name, earlier birthdate).");
        assertEquals(-1, profile2.compareTo(profile3), "Profile2 should come before Profile3.");

        // Test cases where compareTo() should return 1
        assertEquals(1, profile3.compareTo(profile1), "Profile3 should come after Profile1.");
        assertEquals(1, profile2.compareTo(profile1), "Profile2 should come before Profile1.");
        assertEquals(1, profile3.compareTo(profile2), "Profile3 should come after Profile2.");

        // Test case where compareTo() should return 0
        assertEquals(0, profile1.compareTo(profile4), "Profile1 and Profile4 should be equal.");
    }
}
