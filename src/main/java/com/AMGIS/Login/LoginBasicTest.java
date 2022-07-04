package com.AMGIS.Login;

public class LoginBasicTest {

    public static void main(String[] args) {
        LoginProof proof = new LoginProof();

        char [] password = new char[]{1,2,3,4,5};

        proof.checkNull("Farnz",password);
    }
}
