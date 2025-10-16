package calculator;

public enum Delimiter {

    COMMA(","),
    COLON(":"),
    CUSTOM_PREFIX("//"),
    CUSTOM_SUFFIX("\\n");

    private String value;

    Delimiter(String value) {
        this.value = value;
    }

    public static boolean isCustom(String value) {
        return value.startsWith(CUSTOM_PREFIX.value) && value.contains(CUSTOM_SUFFIX.value);
    }

    public int getIndexIn(String value) {
        return value.indexOf(this.value);
    }

    public int getLength() {
        return this.value.length();
    }
}
