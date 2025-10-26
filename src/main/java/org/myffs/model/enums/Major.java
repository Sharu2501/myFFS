package org.myffs.model.enums;

public enum Major {
    I1, I2, I3, ALUMNI;

    public Major next() {
        return switch (this) {
            case I1 -> I2;
            case I2 -> I3;
            case I3 -> ALUMNI;
            default -> this;
        };
    }
}
