package io.shelfy.presentation.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


/**
 * class for help to detect Connectivity isConnected
 * and Connectivity isConnected change
 */
public class NetworkConnectivityHelperImpl implements NetworkConnectivityHelper {

    //could be cellular or Wi-Fi
    private final Set<Network> activeNetworks = new HashSet<>();
    private final BehaviorSubject<Boolean> connectivitySubject = BehaviorSubject.createDefault(false);
    private final ConnectivityManager connectivityManager;
    private final ConnectivityManager.NetworkCallback networkCallback;

    public NetworkConnectivityHelperImpl(Context application) {
        connectivityManager = (ConnectivityManager) application.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkCallback = new ConnectivityManager.NetworkCallback() {

            @Override
            public void onAvailable(@NonNull Network network) {
                activeNetworks.add(network);
                connectivitySubject.onNext(true);
            }

            @Override
            public void onLost(@NonNull Network network) {
                activeNetworks.remove(network);
                // when you remove one of the types of networks the other can still be active
                connectivitySubject.onNext(activeNetworks.size() > 0);
            }
        };
        NetworkRequest networkRequest = new NetworkRequest.Builder().build();
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
    }

    @Override
    public boolean isConnected() {
        return activeNetworks.size() > 0;
    }

    public Observable<Boolean> asObservable() {
        return connectivitySubject.distinctUntilChanged();
    }

    public void unregisterFromNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }
}
