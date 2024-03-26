package fun.pullock.incentive.core.strategy.task.complete.after;

import fun.pullock.incentive.core.enums.AfterCompleteType;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AfterCompleteHandlerFactory {

    @Resource
    private List<AfterCompleteHandler> handlers;

    public AfterCompleteHandler getHandler(AfterCompleteType type) {
        return handlers.stream()
                .filter(handler -> handler.type() == type)
                .findFirst()
                .orElse(null);
    }
}
