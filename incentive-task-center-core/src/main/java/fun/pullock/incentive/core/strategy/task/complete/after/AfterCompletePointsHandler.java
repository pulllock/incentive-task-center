package fun.pullock.incentive.core.strategy.task.complete.after;

import feign.FeignException;
import fun.pullock.api.model.param.GrantParam;
import fun.pullock.general.model.ServiceException;
import fun.pullock.incentive.core.enums.ErrorCode;
import fun.pullock.incentive.core.model.reqeust.TriggerParam;
import fun.pullock.incentive.core.enums.AfterCompleteType;
import fun.pullock.incentive.core.model.dto.TaskCompleteResult;
import fun.pullock.incentive.core.model.dto.TaskDTO;
import fun.pullock.incentive.core.proxy.points.PointsClientService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static fun.pullock.incentive.core.enums.AfterCompleteType.POINTS;
import static fun.pullock.incentive.core.enums.ErrorCode.SYSTEM_ERROR;

@Component
public class AfterCompletePointsHandler implements AfterCompleteHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AfterCompletePointsHandler.class);

    @Resource
    private PointsClientService pointsClientService;

    @Override
    public AfterCompleteType type() {
        return POINTS;
    }

    @Override
    public TaskCompleteResult complete(TriggerParam param, TaskDTO task) {
        try {
            GrantParam grantParam = new GrantParam();
            grantParam.setUserId(param.getUserId());
            grantParam.setConfigId(task.getAfterCompleteRule().getId());
            grantParam.setChannelCode("incentive");
            grantParam.setNumber(task.getAfterCompleteRule().getNumber());
            grantParam.setSource(param.getSource());
            grantParam.setUniqueSourceId(String.format("%s_%s", param.getUniqueSourceId(), task.getId()));
            grantParam.setBizId(task.getId());
            grantParam.setBizDescription(task.getName());
            boolean result = pointsClientService.grant(grantParam);
            return new TaskCompleteResult(0, String.valueOf(result));
        } catch (ServiceException e) {
            LOGGER.error("After complete task to grant points error, param: {}, task: {}, cause: ", param, task, e);
            return new TaskCompleteResult(e.getErrorCode(), e.getErrorMsg());
        } catch (FeignException e) {
            LOGGER.error("After complete task to grant points error, param: {}, task: {}, cause: ", param, task, e);
            return new TaskCompleteResult(e.status(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error("After complete task to grant points error, param: {}, task: {}, cause: ", param, task, e);
            return new TaskCompleteResult(SYSTEM_ERROR.getErrorCode(), e.getMessage());
        }
    }
}
