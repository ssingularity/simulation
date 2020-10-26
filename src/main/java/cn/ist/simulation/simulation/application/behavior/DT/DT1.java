package cn.ist.simulation.simulation.application.behavior.DT;

import cn.ist.simulation.simulation.application.port.out.CallDTFromNeighborApi;
import cn.ist.simulation.simulation.domain.DT.AbstractIndividualDTBehavior;
import cn.ist.simulation.simulation.domain.DT.DTInput;
import cn.ist.simulation.simulation.domain.DT.DTTask;
import cn.ist.simulation.simulation.domain.DT.Instruction;
import cn.ist.simulation.simulation.domain.product.Product;
import cn.ist.simulation.simulation.domain.product.ProductColor;
import cn.ist.simulation.simulation.domain.product.ProductState;
import cn.ist.simulation.simulation.domain.product.ProductType;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/23 14:55
 */
@Slf4j
public class DT1 extends AbstractIndividualDTBehavior {
    final private CallDTFromNeighborApi callDTFromNeighborApi;

    final private Map<Integer, Integer> V = new HashMap<>();

    boolean primary = true;

    public DT1(Integer index, Long taskTime, CallDTFromNeighborApi callDTFromNeighborApi) {
        super(index, taskTime);
        this.callDTFromNeighborApi = callDTFromNeighborApi;
    }

    @Override
    protected void preStartTask(DTTask dtTask) {
        log.info("DT{} Start Task {}", index, dtTask.getDtInput().getId());
    }

    @Override
    protected void handleInstruction(Instruction instruction) {
        this.primary = (boolean) instruction.get("primary");
    }

    @Override
    public void doOutput(Product product) {
        product.setSender("DT" + index);
        if (this.primary) {
            if (product.getType() == ProductType.TYPE1) {
                product.setColor(ProductColor.RED);
            }
            else if (product.getType() == ProductType.TYPE2) {
                product.setColor(ProductColor.BLUE);
            }
        }
        else {
            if (product.getType() == ProductType.TYPE1) {
                product.setColor(ProductColor.YELLOW);
            }
            else if (product.getType() == ProductType.TYPE2) {
                product.setColor(ProductColor.GREEN);
            }
        }
        product.setState(ProductState.Empty);
        DTInput dtInput = new DTInput(product, V);
        callDTFromNeighborApi.call(2, dtInput);
    }
}
