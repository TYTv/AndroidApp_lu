package com.example.student.crypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    final static String password = "ABC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String message = "hello world";
        Log.d("LOG", message);
        String encryptedMsg = null;

        //        Encrypt
        try {
            encryptedMsg = AESCrypt.encrypt(password, message);
            Log.d("LOG", encryptedMsg);
        } catch (GeneralSecurityException e) {
            //handle error
        }

        //        Decrypt
        try {
            String messageAfterDecrypt = AESCrypt.decrypt(password, encryptedMsg);
            Log.d("LOG", messageAfterDecrypt);
        } catch (GeneralSecurityException e) {
            //handle error - could be due to incorrect password or tampered encryptedMsg
        }


    }
}
