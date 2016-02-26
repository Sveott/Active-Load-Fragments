package com.nordicsemi.nrfUARTv2.fragments;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.nordicsemi.nrfUARTv2.R;
import com.nordicsemi.nrfUARTv2.activity.DeviceListActivity;
import com.nordicsemi.nrfUARTv2.activity.UartService;


public class TwoFragment extends Fragment {

    public static BluetoothAdapter mBtAdapter;
    Button btnConnectDisconnect;
    public static UartService mService;


    private static final int REQUEST_SELECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private static final int UART_PROFILE_READY = 10;
    public static final String TAG = "nRFUART";
    private static final int UART_PROFILE_CONNECTED = 20;
    private static final int UART_PROFILE_DISCONNECTED = 21;
    private static final int STATE_OFF = 10;
    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnConnectDisconnect = (Button) getView().findViewById(R.id.btn_select);
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        btnConnectDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBtAdapter.isEnabled()) {
                    Log.i("ggomo", "onClick - BT not enabled yet");
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                } else {
                    if (btnConnectDisconnect.getText().equals("Connect")) {

                        //Connect button pressed, open DeviceListActivity class, with popup windows that scan for devices

                        Intent newIntent = new Intent(getActivity(), DeviceListActivity.class);
                        startActivityForResult(newIntent, REQUEST_SELECT_DEVICE);
                    } else {
                        //Disconnect button pressed
                            mService.disconnect();

                        }
                    }
                }

        });

    }




}
