package instagramdemo.arutha.com.InstagramEnums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ilkinartuc on 18/06/15.
 */
public enum mainPageEnum {


    @SerializedName("0")
    GetToken(0),
    @SerializedName("1")
    getPhotosWithTag(1),
    @SerializedName("2")
    getPhotosWithTagPaging(2),
    @SerializedName("3")
    PopularSearchPage(3);


    private int value;

    private mainPageEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static mainPageEnum fromKey(int val) {
        for (mainPageEnum type : mainPageEnum.values()) {
            if (type.getValue() == val) {
                return type;
            }
        }
        return null;
    }
}
