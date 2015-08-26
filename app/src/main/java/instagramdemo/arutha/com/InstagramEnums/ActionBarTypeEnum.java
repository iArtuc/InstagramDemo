package instagramdemo.arutha.com.InstagramEnums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ilkinartuc on 27/08/15.
 */
public enum ActionBarTypeEnum {

    @SerializedName("0")
    beforeLogin(0),
    @SerializedName("1")
    AfterLogin(1);


    private int value;

    ActionBarTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ActionBarTypeEnum fromKey(int val) {
        for (ActionBarTypeEnum type : ActionBarTypeEnum.values()) {
            if (type.getValue() == val) {
                return type;
            }
        }
        return null;
    }
}


