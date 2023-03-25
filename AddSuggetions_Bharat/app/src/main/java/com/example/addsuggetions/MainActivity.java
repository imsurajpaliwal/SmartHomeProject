package com.example.addsuggetions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.net.wifi.WifiNetworkSuggestion;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void connectToBharat(View view){
        Context context = this;
        Toast.makeText(this, "Hi...", Toast.LENGTH_SHORT).show();
                 
        final WifiNetworkSuggestion suggestion1 =
                new WifiNetworkSuggestion.Builder()
                        .setSsid("bharat")
                        .setWpa2Passphrase("123456789")
                        .setIsAppInteractionRequired(true) // Optional (Needs location permission)
                        .build();


        final List<WifiNetworkSuggestion> suggestionsList =
                new ArrayList<WifiNetworkSuggestion>() {};
        suggestionsList.add(suggestion1);

        final WifiManager wifiManager;
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        final int status = wifiManager.addNetworkSuggestions(suggestionsList);
        if (status != WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
// do error handling here…
        }

// Optional (Wait for post connection broadcast to one of your suggestions)
        final IntentFilter intentFilter =
                new IntentFilter(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION);


        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String IntentAction= intent.getAction();
                Log.d("DebugByxxxxxxxxxxxxxxxxxxxxxx", "Massage"+IntentAction);
                Toast.makeText(MainActivity.this, "Recived", Toast.LENGTH_SHORT).show();
                if (!intent.getAction().equals(
                        WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION)) {
                    return;
                }
                // do post connect processing here...
            }
        };
        context.registerReceiver(broadcastReceiver, intentFilter);

    }
    public void connectToBharat2(View view){
        Context context = this;

        WifiNetworkSpecifier specifier = new WifiNetworkSpecifier.Builder()
                .setSsid("bharat")
                .setWpa2Passphrase("123456789")
                .build();

        NetworkRequest request = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .setNetworkSpecifier(specifier)
                .build();
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);


        connectivityManager.requestNetwork(request, networkCallback);

        final IntentFilter intentFilter =
                new IntentFilter(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION);
        registerReceiver(connectivityReceiver, intentFilter);
        context.registerReceiver(connectivityReceiver, intentFilter);



    }
    public ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            Toast.makeText(MainActivity.this, "onAvailable", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            Toast.makeText(MainActivity.this, "onList", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            Toast.makeText(MainActivity.this, "onCapabilitiesChanged", Toast.LENGTH_SHORT).show();
            final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
        }
    };

    public BroadcastReceiver connectivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "connectivityReceiver", Toast.LENGTH_SHORT).show();
            if (!intent.getAction().equals(
                    WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION)) {
                return;
            }
            // do post connect processing here...
        }
    };
}

