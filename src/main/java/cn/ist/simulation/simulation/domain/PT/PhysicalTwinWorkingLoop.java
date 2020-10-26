package cn.ist.simulation.simulation.domain.PT;

import cn.ist.simulation.simulation.domain.product.Product;
import cn.ist.simulation.simulation.domain.WorkingLoop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 14:33
 */
public class PhysicalTwinWorkingLoop extends WorkingLoop {
    final private AbstractIndividualPTBehavior individualPTBehavior;

    final private List<PTTask> onGoingPTTask = Collections.synchronizedList(new ArrayList<>());

    public PhysicalTwinWorkingLoop(long sliceTime, AbstractIndividualPTBehavior individualPTBehavior) {
        super(sliceTime);
        this.individualPTBehavior = individualPTBehavior;
    }

    /**
     * 完成基于切片方式的任务处理，其中任务的处理通过Sleep的方式切片模拟
     */
    @Override
    public void doTask() {
        Iterator<PTTask> it = onGoingPTTask.iterator();
        while (it.hasNext()) {
            PTTask ptTask = it.next();
            individualPTBehavior.doTask(ptTask, super.getSliceTime());
            if (ptTask.canFinish()) {
                individualPTBehavior.doOutput(ptTask.getProduct());
                it.remove();
            }
        }
    }

    @Override
    protected void reset() {
        this.onGoingPTTask.clear();
    }

    public void handleInput(Product product) {
        this.onGoingPTTask.add(new PTTask(product));
    }
}
