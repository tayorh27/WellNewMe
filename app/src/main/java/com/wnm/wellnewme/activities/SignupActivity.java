package com.wnm.wellnewme.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wnm.wellnewme.LocalDatabase.User;
import com.wnm.wellnewme.LocalDatabase.UserLocalStorage;
import com.wnm.wellnewme.R;
import com.wnm.wellnewme.ServiceBase;
import com.wnm.wellnewme.network.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends ActionBarActivity implements View.OnClickListener{

    TextView login;
    Button register;
    TextInputLayout tf,tl,te,tp,trp;
    EditText first,last,email,password,rpassword;

    UserLocalStorage userLocalStorage;
    ProgressDialog progressDialog;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userLocalStorage = new UserLocalStorage(SignupActivity.this);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        login = (TextView)findViewById(R.id.below_register_button);
        register = (Button)findViewById(R.id.btnRegister);
        tf = (TextInputLayout)findViewById(R.id.TFN);
        tl = (TextInputLayout)findViewById(R.id.TLN);
        te = (TextInputLayout)findViewById(R.id.TEmail);
        tp = (TextInputLayout)findViewById(R.id.TPassword);
        trp = (TextInputLayout)findViewById(R.id.TRPassword);
        first = (EditText)findViewById(R.id.firstname);
        last = (EditText)findViewById(R.id.lastname);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        rpassword = (EditText)findViewById(R.id.rpassword);

        register.setOnClickListener(this);
        login.setOnClickListener(this);

        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setMessage("Registering user...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.below_register_button:
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                break;
            case R.id.btnRegister:
                RegisterUser();
                break;
        }
    }

    private void RegisterUser() {
        final String fn = first.getText().toString();
        final String ln = last.getText().toString();
        final String uEmail = email.getText().toString();
        final String uPassword = password.getText().toString();
        final String urPassword = rpassword.getText().toString();

        if(fn.contentEquals("")){
            tf.setError("provide first name");
            return;
        }
        if(ln.contentEquals("")){
            tl.setError("provide last name");
            return;
        }
        if(uEmail.contentEquals("")){
            te.setError("provide email");
            return;
        }
        if(uPassword.contentEquals("")){
            tp.setError("provide password");
            return;
        }
        if(urPassword.contentEquals("")){
            trp.setError("re-type password");
            return;
        }
        if(!uPassword.contentEquals(urPassword)){
            Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.show();
        User user = new User(0,fn,ln,uEmail,uPassword);
        userLocalStorage.storeUserData(user);


        String web_url = "http://vbbitads.azurewebsites.net/api/account/Register";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,web_url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SignupActivity.this,"Registered successfully", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            public String getPostBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("FirstName",fn);
                params.put("LastName",ln);
                params.put("CompanyName","Vacantboards");
                params.put("Email",uEmail);
                params.put("Password",uPassword);
                params.put("ConfirmPassword",urPassword);
                params.put("PhoneNumber","08166076456");
                params.put("UserName",uEmail);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
