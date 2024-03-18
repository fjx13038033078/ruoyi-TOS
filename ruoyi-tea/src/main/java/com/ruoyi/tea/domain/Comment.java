package com.ruoyi.tea.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品评论表
 *
 * @Author 范佳兴
 * @date 2024/3/18 17:09
 */
@TableName("tos_comment")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Comment implements Serializable {

    /**
     * 评论主键
     */
    private Long commentId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
