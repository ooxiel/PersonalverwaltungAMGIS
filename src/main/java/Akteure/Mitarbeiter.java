package Akteure;

import java.io.Serializable;



public class Mitarbeiter implements Serializable {

    /*
        Variablendeklarierung
     */

    // Loginspezifische Angaben

        private String userType;

    // Personenspezifische Angaben

        //Name und Geschlecht

            private String vorName;
            private String zweitName;
            private String nachName;

            private String geschlecht;

        //Adresse

            private String straße;
            private String hausnummer;
            private int postLeitZahl;
            private String land;

    // Jobspezifische Angaben

        private String jobBezeichnung;
        private String pid;
        private String bueroNummer;
        private String abteilung;
        private String bereich;
        private String position;
        private String vorgesetzter;
        private String abteilungsLeiter;

     /*
        Konstruktor
     */

    public Mitarbeiter(String userType, String vorName, String zweitName, String nachName, String geschlecht, String straße, int postLeitZahl, String land, String jobBezeichnung, String pid, String bueroNummer, String abteilung, String bereich, String position, String vorgesetzter, String abteilungsLeiter) {
        this.userType = userType;
        this.vorName = vorName;
        this.zweitName = zweitName;
        this.nachName = nachName;
        this.geschlecht = geschlecht;
        this.straße = straße;
        this.hausnummer = hausnummer;
        this.postLeitZahl = postLeitZahl;
        this.land = land;
        this.jobBezeichnung = jobBezeichnung;
        this.pid = pid;
        this.bueroNummer = bueroNummer;
        this.abteilung = abteilung;
        this.bereich = bereich;
        this.position = position;
        this.vorgesetzter = vorgesetzter;
        this.abteilungsLeiter = abteilungsLeiter;
    }

       /*
        Getter- und Setter-Methoden
      */

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

        public String getGeschlecht() {
            return geschlecht;
        }

        public void setGeschlecht(String geschlecht) {
            this.geschlecht = geschlecht;
        }

        public String getStraße() {
            return straße;
        }

        public void setStraße(String straße) {
            this.straße = straße;
        }

        public String getHausnummer() {
            return hausnummer;
        }

        public void setHausnummer(String hausnummer) {
            this.hausnummer = hausnummer;
        }

        public int getPostLeitZahl() {
            return postLeitZahl;
        }

        public void setPostLeitZahl(int postLeitZahl) {
            this.postLeitZahl = postLeitZahl;
        }

        public String getLand() {
            return land;
        }

        public void setLand(String land) {
            this.land = land;
        }

        public String getJobBezeichnung() {
            return jobBezeichnung;
        }

        public void setJobBezeichnung(String jobBezeichnung) {
            this.jobBezeichnung = jobBezeichnung;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getBueroNummer() {
            return "Büronummer: "+bueroNummer;
        }

        public void setBueroNummer(String bueroNummer) {
            this.bueroNummer = bueroNummer;
        }

        public String getAbteilung() {
            return abteilung;
        }

        public void setAbteilung(String abteilung) {
            this.abteilung = abteilung;
        }

        public String getBereich() {
            return bereich;
        }

        public void setBereich(String bereich) {
            this.bereich = bereich;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getVorgesetzter() {
            return vorgesetzter;
        }

        public void setVorgesetzter(String vorgesetzter) {
            this.vorgesetzter = vorgesetzter;
        }

        public String getAbteilungsLeiter() {
            return abteilungsLeiter;
        }

        public void setAbteilungsLeiter(String abteilungsLeiter) {
            this.abteilungsLeiter = abteilungsLeiter;
        }

    /*
        Mitarbeiter-Info -> Abruf aller Getter-Methoden in einem Methodenaufruf
    */

    public void info (){

        getVorName();
        getZweitName();
        getNachName();

        getGeschlecht();

        getStraße();
        getHausnummer();
        getPostLeitZahl();
        getLand();

        //___________________________

        getJobBezeichnung();
        getPid();
        getBueroNummer();
        getAbteilung();
        getBereich();
        getPosition();
        getVorgesetzter();
        getAbteilungsLeiter();

    }
}
