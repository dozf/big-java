package com.zf.java8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * 公共测试Bean
 *
 * @author zf
 * @create 2019-05-01 10:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComBean {

    private String name;

    private Integer age;

    private InnerBean innerBean;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InnerBean{

        private String bname;

        private BigDecimal price;

    }

    /**
     * 次方法在 ComBean 根据内部类InnerBean 的price 字段排序时用
     * @return
     */
    public static BigDecimal getSort(ComBean b){
        Assert.notNull(b.getInnerBean(),"内部类不能为空");
        return b.getInnerBean().price;
    }

}
