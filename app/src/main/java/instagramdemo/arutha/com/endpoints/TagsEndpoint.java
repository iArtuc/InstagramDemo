package instagramdemo.arutha.com.endpoints;


import instagramdemo.arutha.com.instagrammodels.RecentByTag;
import instagramdemo.arutha.com.instagrammodels.SearchTagResponse;
import instagramdemo.arutha.com.instagrammodels.TagInfo;
import instagramdemo.arutha.com.instagrammodels.TagInfoResponse;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class TagsEndpoint extends BaseEndpoint {

    private static interface TagsService {

        @GET("/tags/{tag_name}")
        public TagInfoResponse getTag(
                @Path("tag_name") String tagName,
                @Query("access_token") String accessToken
        );

        @GET("/tags/{tag_name}/media/recent")
        public RecentByTag getRecent(
                @Path("tag_name") String tagName,
                @Query("access_token") String accessToken,
                @Query("min_id") String minId,
                @Query("max_id") String maxId,
                @Query("max_tag_id") String maxTagId
        );

        @GET("/tags/search")
        public SearchTagResponse search(
                @Query("access_token") String accessToken,
                @Query("q") String query);

    }

    private final TagsService tagsService;

    public TagsEndpoint(final String accessToken, final RestAdapter.LogLevel logLevel) {
        super(accessToken, logLevel);
        final RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(logLevel).setEndpoint(BASE_URL).build();
        tagsService = restAdapter.create(TagsService.class);
    }

    public TagInfo getTag(final String tagName) {
        return tagsService.getTag(tagName, accessToken).getTagInfo();
    }

    public RecentByTag getRecent(final String tagName) {
        return tagsService.getRecent(tagName, accessToken, null, null, null);
    }

    public RecentByTag getRecent(final String tagName, final String minId, final String maxId, final String maxTagId) {
        return tagsService.getRecent(tagName, accessToken, minId, maxId, maxTagId);
    }

    public SearchTagResponse search(final String query) {
        return tagsService.search(accessToken, query);
    }

}
