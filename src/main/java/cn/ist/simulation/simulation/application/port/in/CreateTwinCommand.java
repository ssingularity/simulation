package cn.ist.simulation.simulation.application.port.in;

import cn.ist.simulation.simulation.common.SelfValidating;
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

    public CreateTwinCommand(Integer index, String url) {
        this.index = index;
        this.url = url;
        this.validateSelf();
    }
}
