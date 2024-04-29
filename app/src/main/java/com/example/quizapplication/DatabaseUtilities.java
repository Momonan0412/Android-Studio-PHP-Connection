package com.example.quizapplication;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quizapplication.record.DatabaseConfig;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class DatabaseUtilities {
    DatabaseConfig DATABASE_CONFIG = DatabaseConfig.createWithDefaults();
    String[] messageCreateTableToast;
    Context context;
    DatabaseUtilities(Context context){
        this.context = context;
        messageCreateTableToast = new String[3];
    }
    public void createTable(){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = DATABASE_CONFIG.getCreateTableURL();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the PHP script
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                // Tables created successfully
                                messageCreateTableToast[0] = jsonResponse.getString("messageOne");
                                messageCreateTableToast[1] = jsonResponse.getString("messageTwo");
                                messageCreateTableToast[2] = jsonResponse.getString("messageThree");
                                // Show the first message
                                showToastForCreateTable(0);
                            } else {
                                // Tables creation failed
                                String errorMessage = jsonResponse.getString("errorMessage");
                                // Handle the error
                            }
                        } catch (JSONException e) {
                            // Error parsing JSON response
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);

    }
    private void showToastForCreateTable(final int index) {
        if (index < messageCreateTableToast.length) {
            Toast.makeText(context, messageCreateTableToast[index], Toast.LENGTH_SHORT).show();
            // Schedule the next Toast after a short delay (e.g., 1000 milliseconds)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showToastForCreateTable(index + 1); // Show the next message
                }
            }, 1000); // Delay in milliseconds between each Toast
        }
    }
    public void insertNewUsers(String username, String password){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = DATABASE_CONFIG.getInsertNewUsersURL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the PHP script
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(context, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            // Error parsing JSON response
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Check if the error is an instance of ServerError, indicating an issue with the server response
                if (error instanceof ServerError) {
                    // Handle server error
                    Toast.makeText(context, "Server error occurred", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    // Handle network error
                    Toast.makeText(context, "Network error occurred", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    // Handle parsing error
                    Toast.makeText(context, "Parse error occurred", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle other types of errors
                    Toast.makeText(context, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("username", username);
                paramV.put("password", password);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
    public void checkIfUserExist(String username, String password){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = DATABASE_CONFIG.getCheckIfUserExistURL();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the PHP script
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(context, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            // Error parsing JSON response
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Check if the error is an instance of ServerError, indicating an issue with the server response
                if (error instanceof ServerError) {
                    // Handle server error
                    Toast.makeText(context, "Server error occurred", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    // Handle network error
                    Toast.makeText(context, "Network error occurred", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    // Handle parsing error
                    Toast.makeText(context, "Parse error occurred", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle other types of errors
                    Toast.makeText(context, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("username", username);
                paramV.put("password", password);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
//    public void insertJapaneseKanjiData(String level, String kanji, String furigana, String english){
//        RequestQueue queue = Volley.newRequestQueue(context);
//        String url = DATABASE_CONFIG.getInsertJapaneseKanjiDataURL();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Handle the response from the PHP script
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//                            if (success) {
//                                Toast.makeText(context, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(context, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            // Error parsing JSON response
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // Check if the error is an instance of ServerError, indicating an issue with the server response
//                if (error instanceof ServerError) {
//                    // Handle server error
//                    Toast.makeText(context, "Server error occurred", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof NetworkError) {
//                    // Handle network error
//                    Toast.makeText(context, "Network error occurred", Toast.LENGTH_SHORT).show();
//                } else if (error instanceof ParseError) {
//                    // Handle parsing error
//                    Toast.makeText(context, "Parse error occurred", Toast.LENGTH_SHORT).show();
//                } else {
//                    // Handle other types of errors
//                    Toast.makeText(context, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        }){
//            protected Map<String, String> getParams(){
//                Map<String, String> paramV = new HashMap<>();
//                paramV.put("level", level);
//                paramV.put("kanji", kanji);
//                paramV.put("furigana", furigana);
//                paramV.put("english", english);
//                return paramV;
//            }
//        };
//        queue.add(stringRequest);
//    }
}