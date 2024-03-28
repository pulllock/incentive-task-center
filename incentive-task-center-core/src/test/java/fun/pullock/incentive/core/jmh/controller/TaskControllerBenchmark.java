package fun.pullock.incentive.core.jmh.controller;

import fun.pullock.incentive.core.controller.app.TaskController;
import fun.pullock.incentive.core.jmh.BaseBenchmarkSpring;
import fun.pullock.incentive.core.model.app.vo.UserTaskVO;
import jakarta.annotation.Resource;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(value = Scope.Benchmark)
public class TaskControllerBenchmark extends BaseBenchmarkSpring {

    @Resource
    private TaskController taskController;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TaskControllerBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public List<UserTaskVO> list() {
        return taskController.list();
    }
}
