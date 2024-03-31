package com.ruoyi.tea.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易记录实体类
 *
 * @Author 范佳兴
 * @date 2024/3/29 11:01
 */
@TableName("tos_record")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Record implements Serializable {

    /**
     * 交易记录ID，主键
     */
    private Long recordId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 购买者ID
     */
    private Long userId;

    /**
     * 购买者姓名
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    @TableField(exist = false)
    private String shopName;

    /**
     * 交易额
     */
    private BigDecimal transactionAmount;

    /**
     * 交易类型（0：收入，1：支出）
     */
    private Integer transactionType;

    /**
     * 交易时间
     */
    private LocalDateTime transactionTime;

    /**
     * 余额
     */
    private BigDecimal balance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
