package fun.pullock.incentive.core.enums;

import java.util.Arrays;

public enum AfterCompleteType {

    DO_NOTHING(0, "无操作"),
    POINTS(1, "发积分"),
    BADGE(2, "发勋章"),
    MQ(3, "发MQ消息")
    ;
    private int type;

    private String desc;

    AfterCompleteType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static AfterCompleteType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
