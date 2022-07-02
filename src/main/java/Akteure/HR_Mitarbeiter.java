package Akteure;

public class HR_Mitarbeiter {

    // Loginspezifische Angaben

    private String userType;
    private String vorName;
    private String zweitName;
    private String nachName;

    public HR_Mitarbeiter(String userType, String vorName, String zweitName, String nachName) {
        this.userType = userType;
        this.vorName = vorName;
        this.zweitName = zweitName;
        this.nachName = nachName;
    }
}
