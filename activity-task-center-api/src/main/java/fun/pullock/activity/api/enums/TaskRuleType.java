package fun.pullock.activity.api.enums;

import java.util.Arrays;

public enum TaskRuleType {
    TYPE_10(10, "一次性"),
    TYPE_20(20, "每一次"),
    TYPE_30(30, "周期性（M天N次，每次都算完成）"),
    TYPE_31(31, "周期性（M天N次，完成最后一次才算完成）"),
    ;


    private int type;

    private String desc;

    TaskRuleType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static TaskRuleType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
