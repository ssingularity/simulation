package cn.ist.simulation.simulation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/17 13:45
 */
@Slf4j
@RequiredArgsConstructor
public abstract class WorkingLoop implements Runnable{
    @Getter
    final private long sliceTime;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                doTask();
                Thread.sleep(sliceTime);
            } catch (InterruptedException e) {
                log.info("Exit From WorkingLoop");
                break;
            } catch (Exception e) {
                log.error("", e);
                log.warn("Reset WorkingLoop");
                reset();
            }
        }
    }

    public abstract void doTask();

    protected abstract void reset();
}
