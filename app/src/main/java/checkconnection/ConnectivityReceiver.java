package checkconnection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityReceiver extends BroadcastReceiver {

    ConnectivityReceiverListener listener;

    public ConnectivityReceiver(ConnectivityReceiverListener connectionListener){
        this.listener = connectionListener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = false;
        if (activeNetwork != null){

            if (activeNetwork.getState() == NetworkInfo.State.CONNECTING || activeNetwork.getState() == NetworkInfo.State.DISCONNECTING){
                return;
            }
            isConnected = activeNetwork.isConnected();
        }

        if (listener != null) {
            listener.onNetworkConnectionChanged(isConnected);
        }
    }

}




