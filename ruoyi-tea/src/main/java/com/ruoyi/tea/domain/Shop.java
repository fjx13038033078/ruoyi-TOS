package com.ruoyi.tea.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 店铺实体类
 *
 * @Author 范佳兴
 * @date 2024/3/29 11:10
 */
@TableName("tos_shop")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Shop implements Serializable {
    /**
     * 主键
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店主ID
     */
    private Long ownerId;

    /**
     * 店主姓名
     */
    @TableField(exist = false)
    private Long ownerName;

    /**
     * 店铺描述
     */
    private String shopDescription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
