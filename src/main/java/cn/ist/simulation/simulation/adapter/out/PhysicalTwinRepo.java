package cn.ist.simulation.simulation.adapter.out;

import cn.ist.simulation.simulation.application.port.out.FetchPhysicalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StorePhysicalTwinPort;
import cn.ist.simulation.simulation.domain.PT.PhysicalTwin;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:16
 */
@Repository
public class PhysicalTwinRepo implements FetchPhysicalTwinPort, StorePhysicalTwinPort {
    final private Map<Integer, PhysicalTwin> repo = new ConcurrentHashMap<>();

    @Override
    public Boolean existsByIndex(Integer index) {
        return repo.containsKey(index);
    }

    @Override
    public PhysicalTwin fetchPhysicalTwin(Integer index) {
        return repo.get(index);
    }

    @Override
    public PhysicalTwin storePhysicalTwin(PhysicalTwin physicalTwin) {
        return repo.put(physicalTwin.getIndex(), physicalTwin);
    }
}
