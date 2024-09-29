package clinic;

/**
 * Last Modified: 9/29/2024
 * {@code @author:} Tianxiang Huang, Jayden Hsu
 * Test: Done
 * 
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int Charge;

    Specialty(int Charge){
        this.Charge = Charge;
    }

    public int getCharge(){
        return Charge;
    }

    public static void main(String[] args) {

        //Test case 1: Verify charge for FAMILY specialty
        System.out.println("Test case 1: " + Specialty.FAMILY.getCharge()); //Expected output: 250

        //Test case 2: Verify charge for PEDIATRICIAN specialty
        System.out.println("Test case 2: " + Specialty.PEDIATRICIAN.getCharge()); //Expected output: 300

        //Test case 3: Verify charge for ALLERGIST specialty
        System.out.println("Test case 3: " + Specialty.ALLERGIST.getCharge()); // Expected output: 350

        //Test case 4: Compare FAMILY and ALLERGIST specialties
        boolean comparisonResult = Specialty.FAMILY.getCharge() < Specialty.ALLERGIST.getCharge();
        System.out.println("Test case 4: Is FAMILY less expensive than ALLERGIST? " + comparisonResult); //Expected output: true

        //Test case 5: Verify that PEDIATRICIAN has a higher charge than FAMILY
        boolean pediatricsMoreExpensive = Specialty.PEDIATRICIAN.getCharge() > Specialty.FAMILY.getCharge();
        System.out.println("Test case 5: Is PEDIATRICIAN more expensive than FAMILY? " + pediatricsMoreExpensive); //Expected output: true
    }
}
