package com.ruoyi.tea.service;

import com.ruoyi.tea.domain.Comment;

import java.util.List;

/**
 * 商品评价管理 Service 接口
 *
 * @Author 范佳兴
 * @date 2024/3/29 14:27
 */
public interface CommentService {
    /**
     * 根据商品ID获取商品评价列表
     *
     * @param productId 商品ID
     * @return 商品评价列表
     */
    List<Comment> listCommentsByProductId(Long productId);

    /**
     * 根据评价ID获取评价信息
     *
     * @param commentId 评价ID
     * @return 评价信息
     */
    Comment getCommentById(Long commentId);

    /**
     * 添加评价
     *
     * @param comment 待添加的评价信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addComment(Comment comment);

    /**
     * 删除评价
     *
     * @param commentId 待删除的评价ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteComment(Long commentId);
}
