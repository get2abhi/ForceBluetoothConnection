package labs.forcebluetoothconnection;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by abhinav.srivastava on 20/04/17.
 */

public class ForceBluetoothConnection extends AsyncTask {
    private final Context context;

    public ForceBluetoothConnection(MainActivity mainActivity) {
        this.context = mainActivity.getApplicationContext();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        String desiredDeviceName = "Sound Blaster JAM";
        BluetoothDevice result = null;
        Set<BluetoothDevice> devices = adapter.getBondedDevices();
        if (devices != null) {
            for (BluetoothDevice device : devices) {
                String deviceName = device.getName();
                if (desiredDeviceName.equals(deviceName)) {
                    result = device;
                    break;
                }
            }
        }
        if(result != null){
            try {
                boolean proxy = adapter.getProfileProxy(context, null, BluetoothProfile.A2DP);
                Method connect = BluetoothA2dp.class.getDeclaredMethod("connect", BluetoothDevice.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
