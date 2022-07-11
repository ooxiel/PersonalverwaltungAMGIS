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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVorName() {
        return vorName;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getZweitName() {
        return zweitName;
    }

    public void setZweitName(String zweitName) {
        this.zweitName = zweitName;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }
}
