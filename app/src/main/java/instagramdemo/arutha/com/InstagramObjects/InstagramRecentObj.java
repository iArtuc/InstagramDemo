package instagramdemo.arutha.com.InstagramObjects;

/**
 * Created by ilkinartuc on 23/08/15.
 */
public class InstagramRecentObj {


    private PaginationObj pagination;
    private MetaObj meta;
    private DataObj data;


    public InstagramRecentObj() {

    }

    public PaginationObj getPagination() {
        return pagination;
    }

    public void setPagination(PaginationObj pagination) {
        this.pagination = pagination;
    }

    public MetaObj getMeta() {
        return meta;
    }

    public void setMeta(MetaObj meta) {
        this.meta = meta;
    }
}
