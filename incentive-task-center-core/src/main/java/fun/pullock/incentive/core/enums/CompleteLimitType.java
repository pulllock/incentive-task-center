package fun.pullock.incentive.core.enums;

import java.util.Arrays;

public enum CompleteLimitType {
    DAY(1, "天（M天N次）"),
    WEEK(2, "自然周（M周N次）"),
    MONTH(3, "自然月（M个月N次）"),
    YEAR(4, "自然年（M年N次）"),
    WEEK_RELATIVE(5, "相对周（M周N次，相当于M*7天N次）"),
    MONTH_RELATIVE(6, "相对月（M月N次，相当于M*30天N次）"),
    YEAR_RELATIVE(7, "相对年（M年N次，相当于M*365天N次）"),
    ONE_TIME(8, "一次性"),
    UNLIMITED(9, "无限制")
    ;

    private int type;

    private String desc;

    CompleteLimitType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static CompleteLimitType of(int type) {
        return Arrays.stream(values()).filter(t -> t.type == type).findFirst().orElse(null);
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
