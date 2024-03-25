package com.ruoyi.tea.mapper;

import com.ruoyi.tea.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2024/3/18 17:57
 */
@Mapper
public interface CommentMapper {
    /**
     * 根据场馆ID获取商品评价列表
     *
     * @param productId 商品ID
     * @return 商品评价列表
     */
    List<Comment> listCommentsByProductId(Long productId);

    /**
     * 根据评价ID获取评价信息
     *
     * @param CommentId 评价ID
     * @return 评价信息
     */
    Comment getCommentById(Long CommentId);

    /**
     * 添加评价
     *
     * @param Comment 待添加的评价信息
     * @return 添加成功返回影响的行数，否则返回 0
     */
    int addComment(Comment Comment);

    /**
     * 删除评价
     *
     * @param CommentId 待删除的评价ID
     * @return 删除成功返回影响的行数，否则返回 0
     */
    int deleteComment(Long CommentId);
}
