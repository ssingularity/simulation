package cn.ist.simulation.simulation.domain.DT;

import cn.ist.simulation.simulation.domain.product.Product;
import cn.ist.simulation.simulation.domain.WorkingLoop;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 14:57
 */
@Slf4j
public class DigitalTwinWorkingLoop extends WorkingLoop {
    final private AbstractIndividualDTBehavior individualDTBehavior;

    final private List<Product> physicalTwinInputList = Collections.synchronizedList(new ArrayList<>());

    final private List<Product> physicalTwinOutputList = Collections.synchronizedList(new ArrayList<>());

    final private List<DTInput> digitalTwinInputList = Collections.synchronizedList(new ArrayList<>());

    final private List<DTTask> onGoingDTTask = Collections.synchronizedList(new ArrayList<>());

    final private List<DTInput> finishedDTInputList = Collections.synchronizedList(new ArrayList<>());

    final private List<Instruction> instructionList = Collections.synchronizedList(new ArrayList<>());

    //TODO 模拟故障

    public DigitalTwinWorkingLoop(long sliceTime, AbstractIndividualDTBehavior individualDTBehavior) {
        super(sliceTime);
        this.individualDTBehavior = individualDTBehavior;
    }


    /**
     * 完成基于切片方式的任务处理
     *
     * 大致流程如下：
     * 1. 检查因果关系以及是否存在可以开始处理的Task
     * 2. 通过Sleep的方式切片模拟完成业务场景
     * 3. 处理输出
     * 4. 处理Agent发来的指令
     */
    @Override
    public void doTask() {
        checkCausality();
        processTasks();
        processFinishedDTInput();
        processInstructionList();
    }

    @Override
    protected void reset() {
        this.physicalTwinInputList.clear();
        this.physicalTwinOutputList.clear();
        this.digitalTwinInputList.clear();
        this.onGoingDTTask.clear();
        this.finishedDTInputList.clear();
    }

    private void checkCausality() {
        Iterator<DTInput> dtInputIterator = digitalTwinInputList.iterator();
        while (dtInputIterator.hasNext()) {
            DTInput dtInput = dtInputIterator.next();
            String dtId = dtInput.getId();
            boolean causalityMatched = false;
            Iterator<Product> productIterator = physicalTwinInputList.iterator();
            while (productIterator.hasNext()) {
                Product product = productIterator.next();
                //FIXME 不能简单地通过Id进行比较应该增加From属性
                if (product.getId().equals(dtId)) {
                    causalityMatched = true;
                    productIterator.remove();
                    break;
                }
            }
            if (causalityMatched) {
                dtInputIterator.remove();
                onGoingDTTask.add(new DTTask(dtInput));
            }
            else {
                break;
            }
        }
    }

    private void processTasks() {
        Iterator<DTTask> it = onGoingDTTask.iterator();
        while (it.hasNext()) {
            DTTask DTTask = it.next();
            if (DTTask.canFinish()) {
                it.remove();
                finishedDTInputList.add(DTTask.getDtInput());
            }
            else {
                individualDTBehavior.doTask(DTTask, super.getSliceTime());
            }
        }
    }

    private void processFinishedDTInput() {
        Iterator<DTInput> it = finishedDTInputList.iterator();
        while (it.hasNext()) {
            DTInput finishedDTInput = it.next();
            Iterator<Product> ptIt = physicalTwinOutputList.iterator();
            while (ptIt.hasNext()) {
                Product ptOutput = ptIt.next();
                //FIXME 不能简单地通过Id进行比较应该增加From属性
                if (ptOutput.getId().equals(finishedDTInput.getId())) {
                    individualDTBehavior.doOutput(finishedDTInput.getProduct());
                    ptIt.remove();
                    it.remove();
                    break;
                }
            }
        }
    }

    private void processInstructionList() {
        Iterator<Instruction> it = instructionList.iterator();
        while (it.hasNext()) {
            Instruction instruction = it.next();
            individualDTBehavior.handleInstruction(instruction);
            it.remove();
        }
    }

    public void handleNeighborInput(DTInput dtInput) {
        this.digitalTwinInputList.add(dtInput);
    }

    public void handlePTInput(Product input) {
        this.physicalTwinInputList.add(input);
    }

    public void handlePTOutput(Product output) {
        this.physicalTwinOutputList.add(output);
    }

    public void handleInstruction(Instruction instruction) {
        this.instructionList.add(instruction);
    }

    public void handleStateUpdateFromNeighbor() {
        //TODO 处理来自Neighbor的状态更新
    }

    public void handleStateUpdateFromPT() {
        //TODO 处理来自PT的状态更新
    }
}
