package cn.ist.simulation.simulation.application.behavior.PT;

import cn.ist.simulation.simulation.application.port.out.CallDTFromPTInputApi;
import cn.ist.simulation.simulation.application.port.out.CallDTFromPTOutputApi;
import cn.ist.simulation.simulation.application.port.out.CallPTFromNeighborApi;
import cn.ist.simulation.simulation.domain.PT.AbstractIndividualPTBehavior;
import cn.ist.simulation.simulation.domain.PT.PTTask;
import cn.ist.simulation.simulation.domain.Product;
import cn.ist.simulation.simulation.domain.ProductType;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/5 15:21
 */
@Slf4j
public class PT1 extends AbstractIndividualPTBehavior {
    final private CallDTFromPTInputApi callDTFromPTInputApi;

    final private CallDTFromPTOutputApi callDTFromPTOutputApi;

    final private CallPTFromNeighborApi callPTFromNeighborApi;

    private Boolean primary = true;

    public PT1(Integer index, Long taskTime,
               CallDTFromPTInputApi callDTFromPTInputApi,
               CallDTFromPTOutputApi callDTFromPTOutputApi,
               CallPTFromNeighborApi callPTFromNeighborApi) {
        super(index, taskTime);
        this.callDTFromPTInputApi = callDTFromPTInputApi;
        this.callDTFromPTOutputApi = callDTFromPTOutputApi;
        this.callPTFromNeighborApi = callPTFromNeighborApi;
    }

    @Override
    protected void preStartTask(PTTask ptTask) {
        log.info("PT{} start Task {}", index, ptTask.getProduct().getId());
        callDTFromPTInputApi.call(index, ptTask.getProduct());
    }

    @Override
    public void doOutput(Product product) {
        product.setSender("PT" + index);
        product.setTimestamp(System.currentTimeMillis());
        if (product.getType() == ProductType.TYPE1 || (product.getType() == ProductType.TYPE2 && this.primary)) {
            callPTFromNeighborApi.call(3, product);
        }
        else {
            callPTFromNeighborApi.call(4, product);
        }
        callDTFromPTOutputApi.call(index, product);
    }
}
