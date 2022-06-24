package Akteure;

public class HR_Mitarbeiter extends Mitarbeiter{

    public HR_Mitarbeiter(String userType, String vorName, String zweitName, String nachName, String geschlecht, String straße, String hausnummer, int postLeitZahl, String land, String jobBezeichnung, String pid, String bueroNummer, String abteilung, String bereich, String position, String vorgesetzter, String abteilungsLeiter) {
        super(userType, vorName, zweitName, nachName, geschlecht, straße, postLeitZahl, land, jobBezeichnung, pid, bueroNummer, abteilung, bereich, position, vorgesetzter, abteilungsLeiter);
    }
}
