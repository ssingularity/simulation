package cn.ist.simulation.simulation.domain;

import lombok.RequiredArgsConstructor;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 14:26
 */
@RequiredArgsConstructor
public abstract class AbstractIndividualDTBehavior {
    final private Long sliceTime;

    public void doTask(Task task) {
        if (task.getTaskState() == TaskState.Running) {
            task.elapse(sliceTime);
        }
        else {
            startTask(task);
            task.running();
        }
    }

    /**
     * 设置task的剩余时间并且根据各自实现完成内部状态的变更
     * @param task 任务
     */
    abstract void startTask(Task task);

    /**
     * 根据各自状态完成对应的输出
     * @param dt 对应的DigitalTwin
     * @param product 完成的产物
     */
    public abstract void doOutput(DigitalTwin dt, DTInput product);
}
