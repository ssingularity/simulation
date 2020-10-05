package cn.ist.simulation.simulation.domain.DT;

import cn.ist.simulation.simulation.domain.Task;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 16:16
 */
public class DTTask extends Task {
    @NotNull
    @Getter
    final DTInput dtInput;

    public DTTask(DTInput dtInput) {
        super();
        this.dtInput = dtInput;
        this.validateSelf();
    }
}
