package cn.ist.simulation.simulation.adapter.config;

import cn.ist.simulation.simulation.application.port.in.CreateDigitalTwinUseCase;
import cn.ist.simulation.simulation.application.port.in.CreatePhysicalTwinUseCase;
import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.application.port.out.StoreDigitalTwinPort;
import cn.ist.simulation.simulation.application.service.CreateTwinUseCaseImpl;
import cn.ist.simulation.simulation.application.service.DigitalTwinUseCaseImpl;
import cn.ist.simulation.simulation.application.service.PhysicalTwinUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:11
 */
@Configuration
public class TwinUseCaseConfig {
    @Bean
    DigitalTwinUseCaseImpl digitalTwinUseCase(FetchDigitalTwinPort fetchDigitalTwinPort,
                                              StoreDigitalTwinPort storeDigitalTwinPort) {
        return new DigitalTwinUseCaseImpl(fetchDigitalTwinPort, storeDigitalTwinPort);
    }

    @Bean
    PhysicalTwinUseCaseImpl physicalTwinUseCase() {
        return new PhysicalTwinUseCaseImpl();
    }

    @Bean
    CreateTwinUseCaseImpl createTwinUseCase(CreateDigitalTwinUseCase createDigitalTwinUseCase,
                                            CreatePhysicalTwinUseCase createPhysicalTwinUseCase) {
        return new CreateTwinUseCaseImpl(createDigitalTwinUseCase, createPhysicalTwinUseCase);
    }
}
