package com.ruoyi.tea.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商品实体类
 *
 * @Author 范佳兴
 * @date 2024/3/18 17:02
 */
@TableName("tos_product")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Product implements Serializable {

    /**
     * 商品主键
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商家ID
     */
    private Long merchantId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
