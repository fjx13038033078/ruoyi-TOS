package com.ruoyi.tea.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author 范佳兴
 * @date 2024/3/18 17:12
 */
@TableName("tos_shopping_cart")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ShoppingCart implements Serializable {

    /**
     * 购物车主键
     */
    private Long cartId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品数量
     */
    private int quantity;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
