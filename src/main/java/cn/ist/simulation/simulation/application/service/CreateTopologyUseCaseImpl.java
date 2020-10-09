package cn.ist.simulation.simulation.application.service;

import cn.ist.simulation.simulation.application.port.in.CreateTopologyCommand;
import cn.ist.simulation.simulation.application.port.in.CreateTopologyUseCase;
import cn.ist.simulation.simulation.application.port.in.Node;
import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.application.port.out.FetchPhysicalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StoreDigitalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StorePhysicalTwinPort;
import cn.ist.simulation.simulation.domain.DT.DigitalTwin;
import cn.ist.simulation.simulation.domain.PT.PhysicalTwin;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:23
 */
@RequiredArgsConstructor
public class CreateTopologyUseCaseImpl implements CreateTopologyUseCase {
    final private FetchPhysicalTwinPort fetchPhysicalTwinPort;

    final private FetchDigitalTwinPort fetchDigitalTwinPort;

    final private StorePhysicalTwinPort storePhysicalTwinPort;

    final private StoreDigitalTwinPort storeDigitalTwinPort;

    @Override
    public Boolean createTopology(CreateTopologyCommand createTopologyCommand) {
        // Verify Topology
        for (Node node : createTopologyCommand.getTopology()) {
            Integer index = node.getIndex();
            Assert.isTrue(fetchDigitalTwinPort.existsByIndex(index), "index为" + index + "的DT不存在");
            Assert.isTrue(fetchPhysicalTwinPort.existsByIndex(index), "index为" + index + "的PT不存在");
            node.getInputs().forEach(x -> {
                Assert.isTrue(!x.equals(index), "有输入自身的回环");
                Assert.isTrue(fetchDigitalTwinPort.existsByIndex(x), "index为" + x + "的DT不存在");
                Assert.isTrue(fetchPhysicalTwinPort.existsByIndex(x), "index为" + x + "的PT不存在");
            });
            node.getOutputs().forEach(x -> {
                Assert.isTrue(!x.equals(index), "有输出自身的回环");
                Assert.isTrue(fetchDigitalTwinPort.existsByIndex(x), "index为" + x + "的DT不存在");
                Assert.isTrue(fetchPhysicalTwinPort.existsByIndex(x), "index为" + x + "的PT不存在");
            });
        }

        for (Node node: createTopologyCommand.getTopology()) {
            Integer id = node.getIndex();

            DigitalTwin digitalTwin = fetchDigitalTwinPort.fetchDigitalTwin(node.getIndex());
            digitalTwin.resetNeighbor();
            node.getInputs().forEach(digitalTwin::addInputNeighbor);
            node.getOutputs().forEach(digitalTwin::addOutputNeighbor);
            storeDigitalTwinPort.storeDigitalTwin(digitalTwin);

            PhysicalTwin physicalTwin = fetchPhysicalTwinPort.fetchPhysicalTwin(id);
            physicalTwin.resetNeighbor();
            node.getInputs().forEach(physicalTwin::addInputNeighbor);
            node.getOutputs().forEach(physicalTwin::addOutputNeighbor);
            storePhysicalTwinPort.storePhysicalTwin(physicalTwin);
        }
        return true;
    }
}
