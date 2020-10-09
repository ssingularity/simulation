package cn.ist.simulation.simulation.domain.DT;

import cn.ist.simulation.simulation.domain.Product;
import lombok.RequiredArgsConstructor;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 14:26
 */
@RequiredArgsConstructor
public abstract class AbstractIndividualDTBehavior {
    final protected Integer index;

    final private Long taskTime;

    void doTask(DTTask dtTask, Long sliceTime) {
        if (dtTask.isWaiting()) {
            preStartTask(dtTask);
            dtTask.runWith(taskTime);
        }
        dtTask.elapse(sliceTime);
    }

    /**
     * 设置根据各自实现完成内部状态的变更
     * @param dtTask 任务
     */
    protected abstract void preStartTask(DTTask dtTask);

    /**
     * 根据各自状态完成对应的输出
     * @param product 完成的产物
     */
    public abstract void doOutput(Product product);
}
