package com.AMGIS.Akteure;

public class HR_Mitarbeiter {

    // Loginspezifische Angaben

    public String userType;
    public String vorName;
    public String zweitName;
    public String nachName;

    public HR_Mitarbeiter(String userType, String vorName, String zweitName, String nachName) {
        this.userType = userType;
        this.vorName = vorName;
        this.zweitName = zweitName;
        this.nachName = nachName;
    }
}
