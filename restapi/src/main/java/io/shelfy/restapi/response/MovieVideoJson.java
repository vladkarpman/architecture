package io.shelfy.restapi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieVideoJson {

    @Expose
    @SerializedName("key")
    private String key;

    public String getKey() {
        return key;
    }
}
