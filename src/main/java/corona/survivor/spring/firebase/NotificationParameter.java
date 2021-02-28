package corona.survivor.spring.firebase;

public enum NotificationParameter {
    SOUND("default"),
    COLOR("#FC312F");

    private String value;

    NotificationParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
