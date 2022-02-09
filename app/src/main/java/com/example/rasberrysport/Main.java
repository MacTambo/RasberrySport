package com.example.rasberrysport;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    public static final int BLUETOOTH_REQ_CODE =1;
    Button button;
    BluetoothAdapter bluetoothAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main);

        button = findViewById(R.id.button);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter==null){
            Toast.makeText(Main.this,"Cet appareil n'a pas de bluetooth",Toast.LENGTH_LONG).show();
        }
        if(!bluetoothAdapter.isEnabled()){
            button.setText("COMMENCER OFF");
        }else{
            button.setText("COMMENCER ON");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bluetoothAdapter.isEnabled()){
                    Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(bluetoothIntent,BLUETOOTH_REQ_CODE);
                }else{
                    bluetoothAdapter.disable();
                    button.setText("COMMENCER OFF");
                }
            }
        });
    }
}
