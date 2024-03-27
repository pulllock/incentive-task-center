package fun.pullock.incentive.core.enums;

import java.util.Arrays;

public enum CompleteType {

    MANUAL(1, "手动完成"),
    AUTO(2, "自动完成"),
    ;
    private int type;

    private String desc;

    CompleteType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static CompleteType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
