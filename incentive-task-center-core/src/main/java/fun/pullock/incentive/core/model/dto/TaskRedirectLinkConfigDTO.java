package fun.pullock.incentive.core.model.dto;

import lombok.Data;

import java.util.Map;

@Data
public class TaskRedirectLinkConfigDTO {

    Map<Integer, String> url;
}
