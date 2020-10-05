package cn.ist.simulation.simulation.domain.PT;

import cn.ist.simulation.simulation.domain.Product;
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

    public void handleInput(Product product) {
        this.onGoingPTTask.add(new PTTask(product));
    }
}
