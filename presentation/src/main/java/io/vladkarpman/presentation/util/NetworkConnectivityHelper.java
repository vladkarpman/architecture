package io.vladkarpman.presentation.util;

public interface NetworkConnectivityHelper {
    /**
     * check the connectivity isConnected
     * (check if device connected to the internet or not)
     *
     * @return true if user connected to the internet else false
     */
    boolean isConnected();
}
