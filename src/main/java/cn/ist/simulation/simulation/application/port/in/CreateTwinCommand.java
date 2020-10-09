package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.common.SelfValidating;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.PT.AbstractIndividualPTBehavior;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 12:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateTwinCommand extends SelfValidating<CreateTwinCommand> {
    @NotNull
    final Integer index;

    @NotNull
    final AbstractIndividualDTBehavior individualDTBehavior;

    @NotNull
    final AbstractIndividualPTBehavior individualPTBehavior;

    public CreateTwinCommand(Integer index, AbstractIndividualDTBehavior individualDTBehavior, AbstractIndividualPTBehavior individualPTBehavior) {
        this.index = index;
        this.individualDTBehavior = individualDTBehavior;
        this.individualPTBehavior = individualPTBehavior;
        this.validateSelf();
    }
}
