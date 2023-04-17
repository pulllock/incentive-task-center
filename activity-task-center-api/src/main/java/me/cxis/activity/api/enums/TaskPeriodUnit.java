package me.cxis.activity.api.enums;

import java.util.Arrays;

public enum TaskPeriodUnit {
    UNIT_1(1, "秒"),
    UNIT_2(2, "分"),
    UNIT_3(3, "小时"),
    UNIT_4(4, "天"),
    UNIT_5(5, "周"),
    UNIT_6(6, "月"),
    UNIT_7(7, "年"),
    ;


    private int unit;

    private String desc;

    TaskPeriodUnit(int unit, String desc) {
        this.unit = unit;
        this.desc = desc;
    }

    public static TaskPeriodUnit of(int unit) {
        return Arrays.stream(values()).filter(t -> t.unit == unit).findFirst().orElse(null);
    }

    public int getUnit() {
        return unit;
    }

    public String getDesc() {
        return desc;
    }
}
