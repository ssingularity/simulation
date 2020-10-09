package cn.ist.simulation.simulation.domain.PT;

import cn.ist.simulation.simulation.domain.Product;
import lombok.RequiredArgsConstructor;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/22 16:30
 */
@RequiredArgsConstructor
public abstract class AbstractIndividualPTBehavior {
    final protected Integer index;

    final private Long taskTime;

    void doTask(PTTask ptTask, Long sliceTime) {
        if (ptTask.isWaiting()) {
            preStartTask(ptTask);
            ptTask.runWith(this.taskTime);
        }
        ptTask.elapse(sliceTime);
    }
    /**
     * 设置根据各自实现完成内部状态的变更
     * @param ptTask 任务
     */
    protected abstract void preStartTask(PTTask ptTask);

    /**
     * 根据各自状态完成对应的输出
     * @param product 完成的产物
     */
    public abstract void doOutput(Product product);
}
