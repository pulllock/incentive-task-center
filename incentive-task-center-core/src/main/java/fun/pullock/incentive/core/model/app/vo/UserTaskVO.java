package fun.pullock.incentive.core.model.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title = "用户任务")
@Data
public class UserTaskVO {

    @Schema(title = "任务ID")
    private Long id;

    @Schema(title = "名称")
    private String name;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "状态：0-未完成 1-待领取 2-已完成")
    private Integer status;

    @Schema(title = "按钮文案")
    private String buttonText;

    @Schema(title = "链接")
    private String url;

    @Schema(title = "可以完成的次数")
    private Integer limitNumber;

    @Schema(title = "已完成的次数")
    private Integer completedNumber;

    @Schema(title = "待领取的次数")
    private Integer toBeClaimedNumber;
}
