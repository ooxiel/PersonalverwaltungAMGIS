package com.AMGIS.Facade;

import com.AMGIS.Akteure.Mitarbeiter;
import com.AMGIS.Login.LoginGenerate;
import GUI.ProofServices.LoginProof;

public class LoginFacade {

    public String username;
    public char[] password;


    public boolean testLogin(String username, char[] password) {

        // Es fehlt noch personalIDProof

        LoginProof loginToProof = new LoginProof();

        if(!loginToProof.checkNull(username, password)){
            return false;

            }else if (!loginToProof.username(username)){
                return false;

            }else if (!loginToProof.passwordProof(password)){
                    return false;

            }

        return true;
    }
    public String newUsernamePassword (Mitarbeiter mitarbeiter){

        LoginGenerate generate = new LoginGenerate();

            String newUsername = generate.generateUsername(mitarbeiter);
            String newPassword = generate.passwordGenerate();

        return "Username: "+newUsername+" | Password: "+newPassword;
    }
}
