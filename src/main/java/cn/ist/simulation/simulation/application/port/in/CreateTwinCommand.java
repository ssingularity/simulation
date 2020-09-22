package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.common.SelfValidating;
import cn.ist.simulation.simulation.domain.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.AbstractIndividualPTBehavior;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:49
 */
@Data
public class CreateTwinCommand extends SelfValidating<CreateTwinCommand> {
    @NotNull
    final Integer index;

    @NotNull
    final String url;

    @NotNull
    final AbstractIndividualDTBehavior individualDTBehavior;

    @NotNull
    final AbstractIndividualPTBehavior individualPTBehavior;

    public CreateTwinCommand(Integer index, String url, AbstractIndividualDTBehavior individualDTBehavior, AbstractIndividualPTBehavior individualPTBehavior) {
        this.index = index;
        this.url = url;
        this.individualDTBehavior = individualDTBehavior;
        this.individualPTBehavior = individualPTBehavior;
        this.validateSelf();
    }
}
