package com.estudo.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private static final String MY_SHARED_PREFERENCE = "sharedPreference";
    private Button buttonSave;
    private TextInputEditText editName;
    private TextView textShowNameSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSave = findViewById(R.id.button_save_name);
        editName = findViewById(R.id.text_input_edit_name);
        textShowNameSaved = findViewById(R.id.text_view_name_saved);

        saveName();
    }

    private void saveName() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(MY_SHARED_PREFERENCE, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if(editName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.toast_name, Toast.LENGTH_LONG).show();
                } else {
                    String name = editName.getText().toString();
                    editor.putString("name", name);
                    editor.apply();
                    showNameSaved();
                }
            }
        });
    }

    private void showNameSaved() {
        SharedPreferences preferences = getSharedPreferences(MY_SHARED_PREFERENCE, 0);

        if (preferences.contains("name")) {
            String name = preferences.getString("name", getString(R.string.undefined_key));
            textShowNameSaved.setText(name);
        } else {
            textShowNameSaved.setText(getString(R.string.undefined_key));
        }
    }
}

