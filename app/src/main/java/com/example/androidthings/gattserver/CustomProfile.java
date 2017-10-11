package com.example.androidthings.gattserver;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import java.util.UUID;

/**
 * A custom profile to hold Characteristics of your choice
 * Created by bjoern on 10/11/17.
 */

public class CustomProfile {
    private static final String TAG = CustomProfile.class.getSimpleName();
    private static int mCounter = 0;

    /* Generate your own with www.uuidgenerator.net */
    public static UUID CUSTOM_SERVICE = UUID.fromString("a9f34d86-2b08-4fc3-9f79-ff176bf6c8b8");
    public static UUID WRITE_COUNTER = UUID.fromString("64cd5877-b525-415b-bd04-573c18613f04");
    public static UUID READ_COUNTER = UUID.fromString("38c753cc-2078-4c18-9151-7fa7e5150eaa");


    /**
     * Return a configured {@link BluetoothGattService} instance for the
     * a custom Service.
     */
    public static BluetoothGattService createCustomService() {
        BluetoothGattService service = new BluetoothGattService(CUSTOM_SERVICE,
                BluetoothGattService.SERVICE_TYPE_PRIMARY);

        // Input Characteristic
        BluetoothGattCharacteristic inputCharacteristic = new BluetoothGattCharacteristic(READ_COUNTER,
                //Read-only characteristic, supports notifications
                BluetoothGattCharacteristic.PROPERTY_READ,
                BluetoothGattCharacteristic.PERMISSION_READ);

        // Output Characteristic
        BluetoothGattCharacteristic outputCharacteristic = new BluetoothGattCharacteristic(WRITE_COUNTER,
                //write characteristic,
                BluetoothGattCharacteristic.PROPERTY_WRITE,
                BluetoothGattCharacteristic.PERMISSION_WRITE);

        service.addCharacteristic(inputCharacteristic);
        service.addCharacteristic(outputCharacteristic);

        return service;
    }

    public static byte[] getInputValue() {

        byte[] field = new byte[1];
        field[0] = (byte) mCounter;
        mCounter = (mCounter+1) %128;
        return field;


    }

    public static void setOutputValue (byte[] value) {
        //handle output here
        mCounter = value[0];
    }



}
