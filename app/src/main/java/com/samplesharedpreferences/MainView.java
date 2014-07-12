package com.samplesharedpreferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by relly on 7/12/2014.
 */
public class MainView extends Activity {

    private boolean isEnabled;
    private Button mvMainButton;
    private TextView mvMainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        mvMainButton = (Button) findViewById(R.id.mvMainButton);
        mvMainTextView = (TextView) findViewById(R.id.mvMainTextView);

        init();
    }


    private void init() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.bluemate.sharedpreferences", MODE_PRIVATE);
        isEnabled = sharedPreferences.getBoolean("isEnabled", false);
        if (isEnabled) {
            mvMainTextView.setText("Status: Enabled");
            mvMainTextView.setTextColor(Color.BLACK);
        }
        else {
            mvMainTextView.setText("Status: Disabled");
            mvMainTextView.setTextColor(Color.RED);
        }

    }

    private void saveIsEnabled() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.bluemate.sharedpreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isEnabled", isEnabled);
        editor.commit();
    }

    public void onMvMainButtonClick(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirm")
                .setMessage(isEnabled ? "This feature is currently enabled. Do you wish to disable this feature ?"
                        : "This feature is currently disabled. Do you wish to enable this feature ?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isEnabled)
                            isEnabled = false;
                        else
                            isEnabled = true;

                        saveIsEnabled();
                        init();
                    }
                });
        alertDialogBuilder.create().show();
    }

}
