package fun.pullock.incentive.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TriggerLogStatus {
    PROCESSING(1, "处理中"),
    FAILED(2, "失败"),
    SUCCESS(3, "成功")
    ;

    private int status;

    private String desc;

    TriggerLogStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static TriggerLogStatus of(int status) {
        return Arrays.stream(values()).filter(t -> t.status == status).findFirst().orElse(null);
    }

}
