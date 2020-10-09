package cn.ist.simulation.simulation.adapter.in.web;

import cn.ist.simulation.simulation.application.port.in.CreateTwinCommand;
import cn.ist.simulation.simulation.application.port.in.CreateTwinUseCase;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.PT.AbstractIndividualPTBehavior;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:04
 */
@RestController
@Slf4j
public class TwinController {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CreateTwinUseCase createTwinUseCase;

    @PostMapping("/twin/{index}")
    public void createDT(@PathVariable(name = "index") Integer index) {
        AbstractIndividualDTBehavior individualDTBehavior = applicationContext.getBean("DT" + index, AbstractIndividualDTBehavior.class);
        AbstractIndividualPTBehavior individualPTBehavior = applicationContext.getBean("PT" + index, AbstractIndividualPTBehavior.class);
        if (individualDTBehavior == null || individualPTBehavior == null) {
            log.error("index 为 {} 的Twin不存在", index);
            return;
        }
        CreateTwinCommand command = new CreateTwinCommand(index, individualDTBehavior, individualPTBehavior);
        createTwinUseCase.createTwin(command);
    }
}
