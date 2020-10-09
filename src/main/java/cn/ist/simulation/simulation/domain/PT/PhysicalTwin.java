package cn.ist.simulation.simulation.domain.PT;

import cn.ist.simulation.simulation.domain.Neighbor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/16 14:34
 */
@Data
public class PhysicalTwin {
    final private Integer index;

    final private Set<Neighbor> inputNeighbor = new HashSet<>();

    final private Set<Neighbor> outputNeighbor = new HashSet<>();

    public PhysicalTwin(Integer index) {
        this.index = index;
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
