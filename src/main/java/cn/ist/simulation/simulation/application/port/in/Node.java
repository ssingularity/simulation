package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.common.SelfValidating;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 19:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Node extends SelfValidating<Node> {
    @NotNull
    Integer index;

    @NotNull
    List<Integer> inputs;

    @NotNull
    List<Integer> outputs;

    public Node(Integer index, List<Integer> inputs, List<Integer> outputs) {
        this.index = index;
        this.inputs = inputs;
        this.outputs = outputs;
        this.validateSelf();
    }
}
