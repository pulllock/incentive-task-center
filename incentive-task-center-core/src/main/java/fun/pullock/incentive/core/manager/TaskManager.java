package fun.pullock.incentive.core.manager;

import fun.pullock.incentive.core.dao.mapper.TaskMapper;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskManager {

    @Resource
    private TaskMapper taskMapper;

    public List<TaskDTO> queryByEvent(String eventCode) {
        List<TaskDTO> tasks = taskMapper.selectByEvent(eventCode);

        // 排序
        tasks = tasks.stream()
                .sorted(Comparator.comparing(TaskDTO::getSequenceWeight)
                        .thenComparing(TaskDTO::getId)
                )
                .collect(Collectors.toList());

        return tasks;
    }
}
