/**
 * Last Modified: 9/16/2024
 * Name: Tianxiang Huang
 * Test: No Test Yet
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
}
