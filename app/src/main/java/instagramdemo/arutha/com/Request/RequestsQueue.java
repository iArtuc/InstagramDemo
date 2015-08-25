package instagramdemo.arutha.com.Request;

public enum RequestsQueue {
    Search_Request(1);

    private int value;

    private RequestsQueue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}