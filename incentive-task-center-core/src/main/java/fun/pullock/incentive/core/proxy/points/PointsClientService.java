package fun.pullock.incentive.core.proxy.points;

import fun.pullock.api.client.PointsClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${rpc.points-center.name}", url = "${rpc.points-center.url}")
public interface PointsClientService extends PointsClient {
}
