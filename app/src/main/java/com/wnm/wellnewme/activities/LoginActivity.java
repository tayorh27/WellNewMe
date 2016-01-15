package com.wnm.wellnewme.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.wnm.wellnewme.LocalDatabase.User;
import com.wnm.wellnewme.LocalDatabase.UserLocalStorage;
import com.wnm.wellnewme.R;
import com.wnm.wellnewme.ServiceBase;
import com.wnm.wellnewme.network.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    Button login;
    TextView signup;
    TextInputLayout te,tp;
    EditText email,password;
    UserLocalStorage userLocalStorage;

    ProgressDialog progressDialog;
    AlertDialog alertDialog;

    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLocalStorage = new UserLocalStorage(LoginActivity.this);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        login = (Button)findViewById(R.id.btnLogin);
        signup = (TextView)findViewById(R.id.below_login_button);
        te = (TextInputLayout)findViewById(R.id.TEmail);
        tp = (TextInputLayout)findViewById(R.id.TPassword);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("validating user...");
        progressDialog.setCancelable(false);

        alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        alertDialog.setTitle("Well New Me");
        alertDialog.setMessage("Incorrect email or password");
        alertDialog.setButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                LogUserIn();
                break;
            case R.id.below_login_button:
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                break;
        }
    }

    private void LogUserIn() {


        final String user_email = email.getText().toString();
        final String user_password = password.getText().toString();

        if(user_email.contentEquals("")){
            te.setError("provide email");
            return;
        }
        if(user_password.contentEquals("")){
            tp.setError("provide password");
            return;
        }

        progressDialog.show();
        //User user = userLocalStorage.getLoggedInUser();

//        if(user_email.contentEquals(user.email) && user_password.contentEquals(user.password)) {
//            //userLocalStorage.storeUserData(user1);
//
//        }else{
//            //alertDialog.show();
//        }
        String web_url = "http://vbbitads.azurewebsites.net/token";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, web_url,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String token = "";
                try {
                    token = response.getString("access_token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                User user = new User(0,"Well", "NewMe",user_email,token);
                userLocalStorage.storeUserData(user);
                userLocalStorage.setUserLoggedIn(true);
                progressDialog.dismiss();
                startActivity(new Intent(LoginActivity.this,StartActivity.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
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
                params.put("username",user_email);
                params.put("password",user_password);
                params.put("grant_type","password");
                return params;
            }
        };

        requestQueue.add(jsonObjectRequest);

    }
}
