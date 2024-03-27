package fun.pullock.incentive.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CompleteRecordStatus {
    TO_BE_CLAIMED(1, "待领取"),
    DONE(2, "已完成"),
    ;

    private int status;

    private String desc;

    CompleteRecordStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static CompleteRecordStatus of(int status) {
        return Arrays.stream(values()).filter(t -> t.status == status).findFirst().orElse(null);
    }

}
