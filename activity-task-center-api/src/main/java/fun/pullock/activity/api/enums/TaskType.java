package fun.pullock.activity.api.enums;

import java.util.Arrays;

public enum TaskType {
    TYPE_10(10, "简单类型"),
    TYPE_11(11, "可设置目标的简单类型"),
    TYPE_12(12, "设置目标的简单类型，带额外奖励"),
    TYPE_20(20, "连续累计型"),
    TYPE_21(21, "连续累计型带额外奖励"),
    ;


    private int type;

    private String desc;

    TaskType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static TaskType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
