package fun.pullock.incentive.api.enums;

import java.util.Arrays;

public enum TaskPeriodType {
    TYPE_1(1, "相对周期"),
    TYPE_2(2, "自然周期"),
    ;


    private int type;

    private String desc;

    TaskPeriodType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static TaskPeriodType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
