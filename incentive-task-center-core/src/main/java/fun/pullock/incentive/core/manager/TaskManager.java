package fun.pullock.incentive.core.manager;

import fun.pullock.incentive.core.model.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskManager {

    public List<TaskDTO> queryByEvent(String eventCode) {
        // TODO
        List<TaskDTO> tasks = new ArrayList<>();

        // 排序
        tasks = tasks.stream()
                .sorted(Comparator.comparing(TaskDTO::getSequenceWeight)
                        .thenComparing(TaskDTO::getId)
                )
                .collect(Collectors.toList());

        return tasks;
    }
}
