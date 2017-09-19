package com.wizeup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wizeup.register.Register;

/**
 * A login screen that offers login via email/password.
 */
public class SignInActivity extends AppCompatActivity implements OnClickListener
{
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private TextView tvReg;
    private View mProgressView;
    private View mLoginFormView;

    Button mEmailSignInButton;


    private static boolean reg_success;
    private static boolean login_success;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        tvReg = (TextView) findViewById(R.id.tv_signup);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        mProgressView = findViewById(R.id.login_progress);

        tvReg.setOnClickListener(this);
        mEmailSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();



        if(id == R.id.tv_signup)
        {


            String back = tvReg.getText().toString();

            if (back.equalsIgnoreCase("back"))
            {
                mEmailSignInButton.setText("Sign In");
                tvReg.setText("No account yet? Create one");
            }
            else
            {
                mEmailSignInButton.setText("Sign Up");

                tvReg.setText("Back");
            }

        }
        else
        {
            Register reg = new Register();

            String txt = mEmailSignInButton.getText().toString();

            if(txt.equalsIgnoreCase("Sign up"))
            {
                //sign up here
                reg.registerUser(email,password, SignInActivity.this);

                boolean success = isReg_success();

                if(success)
                {
                    startActivity(new Intent(this,MainActivity.class));
                }
                else
                {
                    Toast.makeText(SignInActivity.this,"Error creating account",Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                //log in here
                reg.signIn(email,password,SignInActivity.this);

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        boolean success = isLogin_success();
                        Log.e("Login :", ""+success);

                        if(success)
                        {
                            startActivity(new Intent(SignInActivity.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(SignInActivity.this,"Incorrect details",Toast.LENGTH_LONG).show();
                        }
                    }
                },3000);
            }
        }
    }

    public boolean isReg_success()
    {
        return reg_success;
    }

    public void setReg_success(boolean reg_success)
    {
        this.reg_success = reg_success;
    }

    public boolean isLogin_success()
    {
        Log.e("Returning :", ""+login_success);
        return login_success;
    }

    public void setLogin_success(boolean login_success)
    {
        Log.e("LogCheck :", ""+login_success);
        this.login_success = login_success;
    }
}

