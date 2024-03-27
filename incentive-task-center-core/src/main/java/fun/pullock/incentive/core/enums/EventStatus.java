package fun.pullock.incentive.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EventStatus {
    DISABLE(0, "禁用"),
    ENABLE(1, "启用"),
    ;

    private int status;

    private String desc;

    EventStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static EventStatus of(int status) {
        return Arrays.stream(values()).filter(t -> t.status == status).findFirst().orElse(null);
    }

}
