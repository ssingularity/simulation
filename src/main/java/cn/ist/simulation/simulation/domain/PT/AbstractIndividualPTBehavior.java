package cn.ist.simulation.simulation.domain.PT;

import cn.ist.simulation.simulation.domain.Product;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/22 16:30
 */
public abstract class AbstractIndividualPTBehavior {
    void doTask(PTTask ptTask, Long sliceTime) {
        if (ptTask.isWaiting()) {
            preStartTask(ptTask);
            ptTask.runWith(getTaskTime());
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

    /**
     * 返回task需要执行的时间
     * @return long task需要执行的时间
     */
    protected abstract long getTaskTime();
}
