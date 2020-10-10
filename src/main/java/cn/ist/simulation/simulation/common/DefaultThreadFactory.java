package cn.ist.simulation.simulation.common;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ssingualrity
 * @Date: 2020/10/9 19:56
 */
@RequiredArgsConstructor
public class DefaultThreadFactory implements ThreadFactory {
    final private String basicName;

    final private AtomicInteger threadNumber = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(basicName + "-" + threadNumber.getAndIncrement());
        return thread;
    }
}
