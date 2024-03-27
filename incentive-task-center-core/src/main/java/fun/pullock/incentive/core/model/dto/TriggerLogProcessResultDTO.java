package fun.pullock.incentive.core.model.dto;

import lombok.Data;

@Data
public class TriggerLogProcessResultDTO {

    public TriggerLogProcessResultDTO() {
    }

    public TriggerLogProcessResultDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;
}
