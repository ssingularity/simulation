package cn.ist.simulation.simulation.domain.PT;

import cn.ist.simulation.simulation.domain.product.Product;
import cn.ist.simulation.simulation.domain.Task;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * @Author: ssingualrity
 * @Date: 2020/9/19 16:16
 */
public class PTTask extends Task {
    @NotNull
    @Getter
    final Product product;


    public PTTask(Product product) {
        super();
        this.product = product;
        this.validateSelf();
    }
}
