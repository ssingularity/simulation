package cn.ist.simulation.simulation.domain;

import lombok.RequiredArgsConstructor;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 14:26
 */
@RequiredArgsConstructor
public abstract class AbstractIndividualDTBehavior {
    public void doTask(Task task, Long sliceTime) {
        if (task.getTaskState() == TaskState.Waiting) {
            startTask(task);
            task.running();
        }
        task.elapse(sliceTime);
    }

    /**
     * 设置task的剩余时间并且根据各自实现完成内部状态的变更
     * @param task 任务
     */
    protected abstract void startTask(Task task);

    /**
     * 根据各自状态完成对应的输出
     * @param product 完成的产物
     */
    public abstract void doOutput(DTInput product);
}
