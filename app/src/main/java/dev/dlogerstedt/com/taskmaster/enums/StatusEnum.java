package dev.dlogerstedt.com.taskmaster.enums;

public enum StatusEnum {
    Available("Available"), Assigned("Available"), Accepted("Available"), Accomplished("Available");

    private final String text;

    StatusEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
