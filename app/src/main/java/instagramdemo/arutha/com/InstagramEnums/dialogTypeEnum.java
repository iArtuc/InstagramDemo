package instagramdemo.arutha.com.InstagramEnums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ilkinartuc on 18/06/15.
 */
public enum dialogTypeEnum {


    @SerializedName("0")
    LogoutDialog(0),
    @SerializedName("1")
    FullSizePictureDialog(1);


    private int value;

    dialogTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static dialogTypeEnum fromKey(int val) {
        for (dialogTypeEnum type : dialogTypeEnum.values()) {
            if (type.getValue() == val) {
                return type;
            }
        }
        return null;
    }
}
