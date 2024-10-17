package projectone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DateTest {

    @Test
    void isValid() {
        // Invalid test cases
        Date invalidDate1 = new Date(2, 30, 2021); // February 30 is invalid
        Date invalidDate2 = new Date(4, 31, 2021); // April 31 is invalid
        Date invalidDate3 = new Date(13, 15, 2021); // Month 13 is invalid
        Date invalidDate4 = new Date(0, 15, 2021); // Month 0 is invalid

        assertFalse(invalidDate1.isValid(), "February 30 should be invalid.");
        assertFalse(invalidDate2.isValid(), "April 31 should be invalid.");
        assertFalse(invalidDate3.isValid(), "Month 13 should be invalid.");
        assertFalse(invalidDate4.isValid(), "Month 0 should be invalid.");

        // Valid test cases
        Date validDate1 = new Date(2, 28, 2021); // February 28 is valid
        Date validDate2 = new Date(12, 31, 2021); // December 31 is valid

        assertTrue(validDate1.isValid(), "February 28 should be valid.");
        assertTrue(validDate2.isValid(), "December 31 should be valid.");
    }
}