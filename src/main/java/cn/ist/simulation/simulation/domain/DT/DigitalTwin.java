package cn.ist.simulation.simulation.domain.DT;

import cn.ist.simulation.simulation.domain.Neighbor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 14:33
 */
@Data
public class DigitalTwin {
    final private Integer index;

    final private String selfUrl;

    final private Set<Neighbor> inputNeighbor = new HashSet<>();

    final private Set<Neighbor> outputNeighbor = new HashSet<>();

    public DigitalTwin(Integer index, String selfUrl) {
        this.index = index;
        this.selfUrl = selfUrl;
    }

    public void resetNeighbor() {
        this.inputNeighbor.clear();
        this.outputNeighbor.clear();
    }

    public void addInputNeighbor(Integer id) {
        Neighbor neighbor = new Neighbor(id);
        this.inputNeighbor.add(neighbor);
    }

    public void addOutputNeighbor(Integer id) {
        Neighbor neighbor = new Neighbor(id);
        this.outputNeighbor.add(neighbor);
    }
}
