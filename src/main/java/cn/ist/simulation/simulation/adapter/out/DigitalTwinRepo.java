package cn.ist.simulation.simulation.adapter.out;

import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StoreDigitalTwinPort;
import cn.ist.simulation.simulation.domain.DT.DigitalTwin;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:16
 */
@Repository
public class DigitalTwinRepo implements FetchDigitalTwinPort, StoreDigitalTwinPort {
    final private Map<Integer, DigitalTwin> repo = new ConcurrentHashMap<>();

    @Override
    public DigitalTwin fetchDigitalTwin(Integer index) {
        return repo.get(index);
    }

    @Override
    public Boolean existsByIndex(Integer index) {
        return repo.containsKey(index);
    }

    @Override
    public DigitalTwin storeDigitalTwin(DigitalTwin digitalTwin) {
        return repo.put(digitalTwin.getIndex(), digitalTwin);
    }
}
