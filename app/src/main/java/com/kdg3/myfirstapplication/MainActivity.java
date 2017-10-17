package com.kdg3.myfirstapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class  MainActivity extends AppCompatActivity {

    private EditText txtNumber = null;
    private Button btnCompare;
    private TextView txtResult;
    private ProgressBar pgbScore;
    private TextView txtHistory;

    private int searchedValue;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber = (EditText) findViewById( R.id.txtNumber);
        btnCompare = (Button) findViewById( R.id.btnCompare);
        txtResult = (TextView) findViewById( R.id.txtResult);
        pgbScore = (ProgressBar) findViewById( R.id.pgbScore);
        txtHistory = (TextView) findViewById( R.id.txtHistory);

        btnCompare.setOnClickListener(btnCompareListener);

        init();
    }

    private void init(){
        score = 0;
        searchedValue = 1 + (int) (Math.random() * 100);
        Log.i("DEBUG", "Valeur cherché : " + searchedValue );


        txtNumber.setText( "" );
        pgbScore.setProgress( score );
        txtResult.setText( "" );
        txtHistory.setText( "" );

        txtNumber.requestFocus();
    }

    private void  congratulations(){
        txtResult.setText(R.string.strongratulations);

        AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle( R.string.app_name);
        retryAlert.setMessage(getString(R.string.strMessage, score));

        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                init();
            }

        });

        retryAlert.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.strNo), new AlertDialog.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                finish();
            }

        });

        retryAlert.show();
    }

    private View.OnClickListener btnCompareListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DEBUG", "Bouton cliqué");

            String strNumber = txtNumber.getText().toString();
            if (strNumber.equals(""))return;

            txtHistory.append( strNumber + "\r\n");
            pgbScore.incrementProgressBy(1);
            score++;

            int enteredValue = Integer.parseInt( strNumber);
            if( enteredValue == searchedValue){
                congratulations();
            }else if ( enteredValue < searchedValue){
                txtResult.setText(R.string.strGreater);
            }else {
                txtResult.setText(R.string.strLower);
            }

            txtNumber.setText("");
            txtNumber.requestFocus();
        }
    };
}
