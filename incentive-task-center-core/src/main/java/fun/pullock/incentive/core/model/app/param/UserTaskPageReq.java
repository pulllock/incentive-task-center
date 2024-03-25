package fun.pullock.incentive.core.model.app.param;

import java.io.Serializable;

public class UserTaskPageReq implements Serializable {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserTaskPageReq{" +
                "userId=" + userId +
                '}';
    }
}
