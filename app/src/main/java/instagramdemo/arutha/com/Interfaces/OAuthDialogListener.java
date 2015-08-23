package instagramdemo.arutha.com.Interfaces;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public interface OAuthDialogListener {

    public abstract void onComplete(String accessToken);

    public abstract void onError(String error);
}
