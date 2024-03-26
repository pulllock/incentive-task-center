package fun.pullock.incentive.core.strategy.task.complete.limit;

import fun.pullock.incentive.core.enums.CompleteLimitType;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompleteLimitHandlerFactory {

    @Resource
    private List<CompleteLimitHandler> handlers;

    public CompleteLimitHandler getHandler(CompleteLimitType type) {
        return handlers.stream()
                .filter(handler -> handler.type() == type)
                .findFirst()
                .orElse(null);
    }
}
