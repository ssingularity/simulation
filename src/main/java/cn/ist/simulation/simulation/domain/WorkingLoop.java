package cn.ist.simulation.simulation.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 13:45
 */
@Slf4j
@RequiredArgsConstructor
public abstract class WorkingLoop implements Runnable{
    final private long sliceTime;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            doTask();
            try {
                Thread.sleep(sliceTime);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public abstract void doTask();
}
