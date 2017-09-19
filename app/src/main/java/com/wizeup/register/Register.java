package com.wizeup.register;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.wizeup.Connect.Connect;
import com.wizeup.MainActivity;
import com.wizeup.SignInActivity;

/**
 * Created by drmaf on 2017/09/18.
 */

public class Register
{
    private String email;
    private String password;

    private FirebaseAuth mAuth;

    public Register()
    {
        Connect cnt = new Connect();

        mAuth = cnt.checkStatus();
    }

    //[START: Sign up user]
    public void registerUser(String e, String p, Activity a)
    {

        this.email = e;
        this.password = p;

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(a, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                SignInActivity sa =new SignInActivity();;

                if (!task.isSuccessful())
                {
                    sa.setReg_success(false);
                    Log.e("REG ERROR:","Unsuccessful");
                }
                else
                {
                    sa.setReg_success(true);
                    Log.e("REG Success:","successful");
                }
            }
        });


    }
    //[END: Sign up user]

    //[START: Sign in]
    public void signIn(String e, String p, Activity a)
    {
        this.email = e;
        this.password = p;

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(a, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {

                SignInActivity sa =new SignInActivity();

                if(task.isSuccessful())
                {
                    sa.setLogin_success(true);
                    Log.e("SIGN_IN", "Success");
                }
                else
                {
                    sa.setLogin_success(false);
                    Log.e("SIGN_IN", "Failure");
                }
            }
        });
    }
    //[END: Sign in]
}
