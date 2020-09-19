package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.common.SelfValidating;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:58
 */
@Data
public class CreateTopologyCommand extends SelfValidating<CreateTopologyCommand> {

    @NotNull
    List<Node> topology;

    public CreateTopologyCommand(List<Node> topology) {
        this.topology = topology;
        this.validateSelf();
    }
}
