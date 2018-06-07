package com.wllmbr.rocketcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class mainCalculator extends AppCompatActivity {

    private int i = 0;
    private double pLength = 1;
    private double pRadius =1;
    private double launchAltitude =1;
    private double apogee = 1;
    private double frictionForce = 1;
    private double sheerPinCount = 1;
    private double sheerPinType = 1;
    private double ejectCompMass = 1;
    private double couplerShoulderLength = 1;
    private double ejectionVelocity = 1;
    private double safetyFactor = 1;

    private double chargeSize = 1;

    public final static double PI = 3.14159265479;
    public final static double G = 9.807;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator);

        final EditText pLenNum = (EditText) findViewById(R.id.presLenNum);
        pLenNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0){
                    return;
                }
                pLength = Double.parseDouble(s.toString());
                calculateCharge();
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    private void calculateCharge(){
        double pinForceMax = sheerPinCount * PI * java.lang.Math.pow(sheerPinType,2) * 80000000;
        double pinForceMin = sheerPinCount * PI * java.lang.Math.pow(sheerPinType,2) * 65000000;

        double eFric = pLength * frictionForce;
        double ePin = PI * java.lang.Math.pow(sheerPinType,3) * 80000000;
        double eTotal = (0.5 * ejectCompMass * java.lang.Math.pow(ejectionVelocity,2)) + eFric + ePin;

        double fric = 1.2 * (PI * java.lang.Math.pow(pRadius,2)) * (pressureFromAlt(launchAltitude) - pressureFromAlt(apogee + launchAltitude));
        return;
    }

    private double pressureFromAlt(double altitude){
        double term1 = 101325;
        double term2 = altitude* 13.06;
        double term3 = 0.0007354 *( java.lang.Math.pow(altitude,2));
        double term4 = java.lang.Math.pow(altitude,4) * 0.000000000000602;

        double term5 = term1 + term2 + term3 + term4;

        return term5 * java.lang.Math.exp(-0.0002465 * altitude);
    }

}
