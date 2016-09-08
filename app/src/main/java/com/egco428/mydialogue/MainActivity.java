package com.egco428.mydialogue;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String[] CLUBS = {"Gummy", "Candy", "Chocolate", "Cookies", "Ice-cream"};
    String msgSelected;
    ArrayList<Integer> msgMultiSelected;

    Button buttonSimple;
    Button buttonList;
    Button buttonSingle;
    Button buttonMulti;
    Button buttonCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSimple = (Button)findViewById(R.id.btnSimple);
        buttonList = (Button)findViewById(R.id.btnList);
        buttonSingle = (Button)findViewById(R.id.btnSingle);
        buttonMulti = (Button)findViewById(R.id.btnMulti);
        buttonCustom = (Button)findViewById(R.id.btnCustom);

        buttonSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you hungry?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();

            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select your favorite...");

                builder.setItems(CLUBS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String selected = CLUBS[i];
                        Toast.makeText(getApplicationContext(), "You liked "+selected, Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();

            }
        });

        buttonSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select your favorite...");

                builder.setSingleChoiceItems(CLUBS, 0, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selected = CLUBS[i];
                        Toast.makeText(getApplicationContext(), "You liked "+selected, Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();                    }
                });

                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();

            }
        });

        buttonMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                msgMultiSelected = new ArrayList<Integer>();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select your favorite...");
                builder.setMultiChoiceItems(CLUBS, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                        if(isChecked){
                            msgMultiSelected.add(i);
                        }
                        else if(msgMultiSelected.contains(i)){
                            msgMultiSelected.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer buffer = new StringBuffer();
                        for(Integer dessert:msgMultiSelected){
                            buffer.append(" ");
                            buffer.append(CLUBS[dessert]);
                        }
                        Toast.makeText(getApplicationContext(), "You liked "+buffer.toString(), Toast.LENGTH_LONG).show();
                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();

            }
        });

        buttonCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Login Dialog");
                dialog.setContentView(R.layout.dialog_custom);
                final EditText username = (EditText)dialog.findViewById(R.id.username);
                final EditText password = (EditText)dialog.findViewById(R.id.password);
                Button buttonLogin = (Button)dialog.findViewById(R.id.login);
                Button buttonCancel = (Button)dialog.findViewById(R.id.cancel);

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                buttonLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(username.getText().toString().equals("admin") && password.getText().toString().equals("1234")){
                            Toast.makeText(getApplicationContext(),"Login successful.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Login failed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dialog.show();
            }
        });

    }
}
