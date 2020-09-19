package cn.ist.simulation.simulation.application.workingloop;

import cn.ist.simulation.simulation.application.port.out.FetchDigitalTwinPort;
import cn.ist.simulation.simulation.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 14:57
 */
@Slf4j
public class DigitalTwinWorkingLoop extends WorkingLoop {
    final private FetchDigitalTwinPort fetchDigitalTwinPort;

    final private Integer index;

    final private AbstractIndividualDTBehavior individualDTBehavior;

    final private List<Product> physicalTwinInputList = new CopyOnWriteArrayList<>();

    final private List<DTInput> digitalTwinInputList = new CopyOnWriteArrayList<>();

    final private List<Task> onGoingTask = new CopyOnWriteArrayList<>();

    final private List<DTInput> finishedProducts = new CopyOnWriteArrayList<>();

    public DigitalTwinWorkingLoop(long sliceTime, FetchDigitalTwinPort fetchDigitalTwinPort, Integer index, AbstractIndividualDTBehavior individualDTBehavior) {
        super(sliceTime);
        this.fetchDigitalTwinPort = fetchDigitalTwinPort;
        this.index = index;
        this.individualDTBehavior = individualDTBehavior;
    }



    @Override
    public void doTask() {
        log.info("Digital Twin[index = {}] is working", this.index);
        //检查因果关系以及是否存在可以开始处理的Task
        checkCausality();
        //完成业务场景
        processTasks();
        //处理输出
        doOutPut();

    }

    void checkCausality() {
        Iterator<DTInput> dtInputIterator = digitalTwinInputList.iterator();
        while (dtInputIterator.hasNext()) {
            DTInput dtInput = dtInputIterator.next();
            String dtId = dtInput.getId();
            boolean canRemove = false;
            Iterator<Product> productIterator = physicalTwinInputList.iterator();
            while (productIterator.hasNext()) {
                Product product = productIterator.next();
                if (product.getId().equals(dtId)) {
                    canRemove = true;
                    productIterator.remove();
                    break;
                }
            }
            if (canRemove) {
                dtInputIterator.remove();
            }
        }
    }

    void processTasks() {
        Iterator<Task> it = onGoingTask.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.canFinish()) {
                it.remove();
                finishedProducts.add(task.getProduct());
            }
            else {
                individualDTBehavior.doTask(task);
            }
        }
    }

    private void doOutPut() {
        Iterator<DTInput> it = finishedProducts.iterator();
        DigitalTwin digitalTwin = fetchDigitalTwinPort.fetchDigitalTwin(index);
        while (it.hasNext()) {
            DTInput product = it.next();
            individualDTBehavior.doOutput(digitalTwin, product);
            it.remove();
        }
    }

    public void handleInputFromNeighbor(DTInput dtInput) {
        this.digitalTwinInputList.add(dtInput);
    }

    public void handleInputFromPt(Product product) {
        this.physicalTwinInputList.add(product);
    }

    public void handleUpdate() {

    }

}
