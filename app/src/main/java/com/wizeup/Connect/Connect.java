package com.wizeup.Connect;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by CS on 09/18/17.
 */

public class Connect
{
    private FirebaseAuth fba;
    private FirebaseAuth.AuthStateListener listener;

    private final static String TAG =  " connection status ";


    public Connect()
    {

    }

    public FirebaseAuth checkStatus ()
    {
        fba = FirebaseAuth.getInstance();

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null )
                {

                    // sign up
                    Log.d(TAG,"Sign in " + user.getUid());
                }else
                {
                    // sign off
                    Log.d(TAG, "Not correct successfully sign in" + user.getUid());

                }

            }
        };

        return fba;
    }
}
