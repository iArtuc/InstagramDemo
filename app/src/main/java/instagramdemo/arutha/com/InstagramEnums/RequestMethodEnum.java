package instagramdemo.arutha.com.InstagramEnums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ilkinartuc on 25/08/15.
 */
public enum RequestMethodEnum {

    @SerializedName("0")
    getTag(0),
    @SerializedName("1")
    getPopular(1);


    private int value;

    private RequestMethodEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RequestMethodEnum fromKey(int val) {
        for (RequestMethodEnum type : RequestMethodEnum.values()) {
            if (type.getValue() == val) {
                return type;
            }
        }
        return null;
    }
}
