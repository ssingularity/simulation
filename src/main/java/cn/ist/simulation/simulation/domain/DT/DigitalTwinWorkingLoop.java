package cn.ist.simulation.simulation.domain.DT;

import cn.ist.simulation.simulation.domain.Product;
import cn.ist.simulation.simulation.domain.Task;
import cn.ist.simulation.simulation.domain.WorkingLoop;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 14:57
 */
@Slf4j
public class DigitalTwinWorkingLoop extends WorkingLoop {
    final private AbstractIndividualDTBehavior individualDTBehavior;

    final private List<Product> physicalTwinInputList = Collections.synchronizedList(new ArrayList<>());

    final private List<Product> physicalTwinOutputList = Collections.synchronizedList(new ArrayList<>());

    final private List<DTInput> digitalTwinInputList = Collections.synchronizedList(new ArrayList<>());

    final private List<Task> onGoingTask = Collections.synchronizedList(new ArrayList<>());

    final private List<DTInput> finishedDTInputList = Collections.synchronizedList(new ArrayList<>());

    public DigitalTwinWorkingLoop(long sliceTime, AbstractIndividualDTBehavior individualDTBehavior) {
        super(sliceTime);
        this.individualDTBehavior = individualDTBehavior;
    }


    /**
     * 完成基于切片方式的任务处理
     *
     * 大致流程如下：
     * 1. 检查因果关系以及是否存在可以开始处理的Task
     * 2. 完成业务场景
     * 3. 处理输出
     */
    @Override
    public void doTask() {
        checkCausality();
        processTasks();
        doOutPut();
    }

    private void checkCausality() {
        Iterator<DTInput> dtInputIterator = digitalTwinInputList.iterator();
        while (dtInputIterator.hasNext()) {
            DTInput dtInput = dtInputIterator.next();
            String dtId = dtInput.getId();
            boolean causalityMatched = false;
            Iterator<Product> productIterator = physicalTwinInputList.iterator();
            while (productIterator.hasNext()) {
                Product product = productIterator.next();
                //FIXME 不能简单地通过Id进行验证
                if (product.getId().equals(dtId)) {
                    causalityMatched = true;
                    productIterator.remove();
                    break;
                }
            }
            if (causalityMatched) {
                dtInputIterator.remove();
                onGoingTask.add(new Task(dtInput));
            }
            else {
                break;
            }
        }
    }

    private void processTasks() {
        Iterator<Task> it = onGoingTask.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.canFinish()) {
                it.remove();
                finishedDTInputList.add(task.getDtInput());
            }
            else {
                individualDTBehavior.doTask(task, super.getSliceTime());
            }
        }
    }

    private void doOutPut() {
        Iterator<DTInput> it = finishedDTInputList.iterator();
        while (it.hasNext()) {
            DTInput finishedDTInput = it.next();
            Iterator<Product> ptIt = physicalTwinOutputList.iterator();
            while (ptIt.hasNext()) {
                Product ptOutput = ptIt.next();
                if (ptOutput.getId().equals(finishedDTInput.getId())) {
                    individualDTBehavior.doOutput(finishedDTInput);
                    ptIt.remove();
                    it.remove();
                    break;
                }
            }
        }
    }

    public void handleInputFromNeighbor(DTInput dtInput) {
        this.digitalTwinInputList.add(dtInput);
    }

    public void handleInputFromPt(Product product) {
        this.physicalTwinInputList.add(product);
    }

    public void handleOutputFromPt(Product product) {
        this.physicalTwinOutputList.add(product);
    }
}
