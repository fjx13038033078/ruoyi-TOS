package com.ruoyi.tea.service.impl;

import com.ruoyi.tea.domain.Comment;
import com.ruoyi.tea.mapper.CommentMapper;
import com.ruoyi.tea.service.CommentService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * @Author 范佳兴
 * @date 2024/3/29 14:29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    /**
     * 根据商品ID获取商品评价列表
     *
     * @param productId 商品ID
     * @return 商品评价列表
     */
    @Override
    public List<Comment> listCommentsByProductId(Long productId) {
        return commentMapper.listCommentsByProductId(productId);
    }

    /**
     * 根据评价ID获取评价信息
     *
     * @param commentId 评价ID
     * @return 评价信息
     */
    @Override
    public Comment getCommentById(Long commentId) {
        return commentMapper.getCommentById(commentId);
    }

    /**
     * 添加评价
     *
     * @param comment 待添加的评价信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addComment(Comment comment) {
        int rows = commentMapper.addComment(comment);
        return rows > 0;
    }

    /**
     * 删除评价
     *
     * @param commentId 待删除的评价ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteComment(Long commentId) {
        int rows = commentMapper.deleteComment(commentId);
        return rows > 0;
    }
}
