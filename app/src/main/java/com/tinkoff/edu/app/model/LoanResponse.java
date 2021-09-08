package com.tinkoff.edu.app.model;

public class LoanResponse {
    private final int margin;
    private final ResponseType resolution;

    public LoanResponse(int margin, ResponseType resolution) {
        this.margin = margin;
        this.resolution = resolution;
    }

    public int getMargin() {
        return margin;
    }

    public ResponseType getResolution() {
        return resolution;
    }
}
