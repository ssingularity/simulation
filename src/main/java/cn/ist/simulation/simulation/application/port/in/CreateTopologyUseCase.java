package cn.ist.simulation.simulation.application.port.in;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 15:08
 */
public interface CreateTopologyUseCase {
    /**
     * 创建拓扑结构
     * @param createTopologyCommand 拓扑结构
     * @return 是否创建成功
     */
    Boolean createTopology(CreateTopologyCommand createTopologyCommand);
}
