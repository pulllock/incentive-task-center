package fun.pullock.incentive.core.enums;

import java.util.Arrays;

public enum CompleteEngageWay {
    PUSH(1, "PUSH"),
    POPUP(2, "弹窗"),
    ;

    private int type;

    private String desc;

    CompleteEngageWay(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static CompleteEngageWay of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
