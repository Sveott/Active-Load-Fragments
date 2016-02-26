package com.nordicsemi.nrfUARTv2.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nordicsemi.nrfUARTv2.R;
import com.nordicsemi.nrfUARTv2.activity.MainActivity;
import com.nordicsemi.nrfUARTv2.activity.UartService;

import java.io.UnsupportedEncodingException;

public class OneFragment extends Fragment implements View.OnClickListener{
    Button sendVolt;


    public static UartService uartService;

    public OneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home, container, false);


        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sendVolt = (Button) getActivity().findViewById(R.id.sendButtonVolt);

        sendVolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) getActivity().findViewById(R.id.setVolt);
                String combine = editText.getText().toString();
                String voltSend = "1" + combine;
                sendVolt(voltSend);

            }
        });

    }

    public void sendVolt(String v) {

        try {
            byte[] getByte = v.getBytes("UTF-8");
            uartService.writeRXCharacteristic(getByte);
        }
        catch (UnsupportedEncodingException e){
        }
    }

    public void sendAmp(View view) {
        EditText editText = (EditText) getActivity().findViewById(R.id.setAmp);
        String combine = editText.getText().toString();
        String ampSend = "0" + combine;
        try {
            byte[] getByte = ampSend.getBytes("UTF-8");
            uartService.writeRXCharacteristic(getByte);
        }
        catch (UnsupportedEncodingException e){
        }
    }

    @Override
    public void onClick(View v) {

    }
}
