package fun.pullock.incentive.core.strategy.task.complete.engage;

import fun.pullock.incentive.core.enums.CompleteEngageWay;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompleteEngageHandlerFactory {

    @Resource
    private List<CompleteEngageHandler> handlers;

    public CompleteEngageHandler getHandler(CompleteEngageWay type) {
        return handlers.stream()
                .filter(handler -> handler.type() == type)
                .findFirst()
                .orElse(null);
    }
}
