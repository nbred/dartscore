package com.rdeluca118.dartscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.media.ToneGenerator;
import android.media.AudioManager;

public class GameActivity extends AppCompatActivity {

    private int dartOrd, tTotal;
    private final int[] darts = {0, 0, 0, 0};
    private int col1Value, col2Value;
    private TextView totView, curdartview;
    private TextView col1Score, col2Score,curCol, startCol;
    private RadioGroup theRadioGroup;
    private boolean haveNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ViewGroup layout = findViewById(R.id.left_pane);
        disableEnableControls(false, layout);

        theRadioGroup = findViewById(R.id.rg_dt);
        col1Score = findViewById(R.id.p1score);
        col2Score = findViewById(R.id.p2score);
        curCol = col1Score;
        haveNumber = false;
    }

    public void numberClicked(View v) {
        Button b = (Button) v;
        String buttonText = b.getText().toString();
        try {
            int buttonValue = Integer.parseInt(buttonText);
            darts[dartOrd] = buttonValue;
            haveNumber = true;
            Button child = findViewById(R.id.button_count);
            child.setEnabled(true);

        } catch (NumberFormatException nfe) {
            Log.i("NUMBER", "Couldn't parse.");
            nfe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doNewLeg(View v) {
        String player1;
        String player2;

        EditText etp1 = findViewById(R.id.p1);
        player1 = etp1.getText().toString();
        if (player1.isEmpty()) {
            Log.i("Name", "Provide name for Player 1");
            return;
        }

        EditText etp2 = findViewById(R.id.p2);
        player2 = etp2.getText().toString();
        if (player2.isEmpty()) {
            Log.i("Name", "Provide name for Player 2");
            return;
        }

        //etp1.setActivated(false);
        etp1.setEnabled(false);
        //etp2.setActivated(false);
        etp2.setEnabled(false);

        ViewGroup group1 = findViewById(R.id.left_pane);
        disableEnableControls(true, group1);
        ViewGroup group2 = findViewById(R.id.option_pane);
        disableEnableControls(true, group2);

        tTotal = 0;
        dartOrd = 1;
        col1Value = 501;
        col2Value = 501;
        if (startCol == col1Score) {
            curCol = col2Score;
        } else {
            curCol = col1Score;
        }
        startCol = curCol;

        Button child = findViewById(R.id.button_count);
        child.setEnabled(false);
    }


    public void taskFailed() {
        Toast.makeText(getApplicationContext(),
                "Can not have TRIPLE 25",
                Toast.LENGTH_SHORT).show();

        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 500);
    }
    public void countShot(View v) {
        if(haveNumber) {
            //isDouble = true;
            RadioButton douldeOpt = findViewById(R.id.radio_double);
            if (douldeOpt.isChecked()) {
                int x = darts[dartOrd] * 2;
                darts[dartOrd] = x;
                theRadioGroup.clearCheck();
            }

            //isTriple = true;
            RadioButton tripleOpt = findViewById(R.id.radio_triple);
            if (tripleOpt.isChecked()) {
                if (darts[dartOrd] == 25) {
                    // no TRIPLE 25
                    taskFailed();
                    theRadioGroup.clearCheck();
                    return;
                } else {
                    int x = darts[dartOrd] * 3;
                    darts[dartOrd] = x;
                    theRadioGroup.clearCheck();
                }
            }

            TextView view;
            totView = findViewById(R.id.turn);

            switch (dartOrd) {
                case 1:
                    view = findViewById(R.id.dart1);
                    view.setText(String.valueOf(darts[dartOrd]));
                    curdartview = view;
                    tTotal = darts[1];
                    totView.setText(String.valueOf(tTotal));
                    break;
                case 2:
                    view = findViewById(R.id.dart2);
                    view.setText(String.valueOf(darts[dartOrd]));
                    curdartview = view;
                    tTotal = darts[1] + darts[2];
                    totView.setText(String.valueOf(tTotal));
                    break;
                case 3:
                    view = findViewById(R.id.dart3);
                    view.setText(String.valueOf(darts[dartOrd]));
                    curdartview = view;
                    tTotal = darts[1] + darts[2] + darts[3];
                    totView.setText(String.valueOf(tTotal));
                    break;
            }
            curdartview.setBackgroundColor(0xFF00FF00);
            dartOrd++;
            haveNumber = false;
            Button child = findViewById(R.id.button_count);
            child.setEnabled(false);
        }
    }

    public void doReset(View v) {
        darts[dartOrd] = 0;
        theRadioGroup.clearCheck();
    }

    public void doScratch(View v) {
        TextView view;
        switch (dartOrd) {
            case 1:
                view = findViewById(R.id.dart1);
                view.setBackgroundColor(0xFF00FF00);
                break;
            case 2:
                view = findViewById(R.id.dart2);
                view.setBackgroundColor(0xFF00FF00);
                break;
            case 3:
                view = findViewById(R.id.dart3);
                view.setBackgroundColor(0xFF00FF00);
        }
        darts[dartOrd] = 0;
        dartOrd++;
    }

    public void doBust(View v) {
        tTotal = 0;
        switchPlayer(v);
    }

    public void switchPlayer(View v) {
        TextView view;

        darts[1] = 0;
        darts[2] = 0;
        darts[3] = 0;
        dartOrd = 1;
        view = findViewById(R.id.dart1);
        view.setBackgroundColor(0x00000000);
        view.setText("0");
        view = findViewById(R.id.dart2);
        view.setBackgroundColor(0x00000000);
        view.setText("0");
        view = findViewById(R.id.dart3);
        view.setBackgroundColor(0x00000000);
        view.setText("0");
        totView.setText("0");

        if (curCol == col1Score) {
            col1Value -= tTotal;
            curCol.append("\n" + tTotal + " : " + col1Value);
            curCol = col2Score;
        } else {
            col2Value -= tTotal;
            curCol.append("\n" + tTotal + " : " + col2Value);
            curCol = col1Score;
        }
        tTotal = 0;
    }

    private void disableEnableControls(boolean enable, ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup) {
                disableEnableControls(enable, (ViewGroup) child);
            }
        }
    }

    public void shutDown(View v) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
