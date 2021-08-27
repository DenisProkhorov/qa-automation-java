package com.tinkoff.edu.app;

public class LoanResponce {
    private final int margin;
    private final boolean resolution;

    public LoanResponce(int margin, boolean resolution) {
        this.margin = margin;
        this.resolution = resolution;
    }

    public int getMargin() {
        return margin;
    }

    public boolean isResolution() {
        return resolution;
    }
}
