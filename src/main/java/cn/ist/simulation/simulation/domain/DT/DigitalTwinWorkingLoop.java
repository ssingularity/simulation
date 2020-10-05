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
    final private Integer index;

    final private AbstractIndividualDTBehavior individualDTBehavior;

    final private List<Product> physicalTwinInputList = Collections.synchronizedList(new ArrayList<>());

    final private List<DTInput> digitalTwinInputList = Collections.synchronizedList(new ArrayList<>());

    final private List<Task> onGoingTask = Collections.synchronizedList(new ArrayList<>());

    final private List<DTInput> finishedProducts = Collections.synchronizedList(new ArrayList<>());

    public DigitalTwinWorkingLoop(long sliceTime, Integer index, AbstractIndividualDTBehavior individualDTBehavior) {
        super(sliceTime);
        this.index = index;
        this.individualDTBehavior = individualDTBehavior;
    }


    @Override
    public void doTask() {
        //检查因果关系以及是否存在可以开始处理的Task
        checkCausality();
        //完成业务场景
        processTasks();
        //处理输出
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
                finishedProducts.add(task.getProduct());
            }
            else {
                individualDTBehavior.doTask(task, super.getSliceTime());
            }
        }
    }

    private void doOutPut() {
        Iterator<DTInput> it = finishedProducts.iterator();
        while (it.hasNext()) {
            DTInput product = it.next();
            individualDTBehavior.doOutput(product);
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
