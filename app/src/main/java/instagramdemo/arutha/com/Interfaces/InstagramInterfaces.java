package instagramdemo.arutha.com.Interfaces;

import java.util.List;

import instagramdemo.arutha.com.InstagramObjects.InstagramRecentObj;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public interface InstagramInterfaces {

    @GET("/tags/nofilter/media/recent?client_id={user}")
    List<InstagramRecentObj> INSTAGRAM_RECENT_OBJ_LIST(
            @Path("user") String user
    );

}
