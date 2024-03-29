package com.ruoyi.web.controller.tea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.tea.domain.Comment;
import com.ruoyi.tea.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 * 商品评价管理 Controller
 *
 * @Author 范佳兴
 * @date 2024/3/29 14:42
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/tea/comment")
public class CommentController extends BaseController {

    private final CommentService commentService;

    // 根据商品ID获取商品评价列表
    @GetMapping("/list")
    public TableDataInfo listCommentsByProductId(@RequestParam Long productId) {
        startPage();
        List<Comment> comments = commentService.listCommentsByProductId(productId);
        return getDataTable(comments);
    }

    // 根据评价ID获取评价信息
    @GetMapping("/detail")
    public AjaxResult getCommentById(@RequestParam Long commentId) {
        return success(commentService.getCommentById(commentId));
    }

    // 添加评价
    @PostMapping("/add")
    public AjaxResult addComment(@RequestBody Comment comment) {
        return toAjax(commentService.addComment(comment));
    }

    // 删除评价
    @GetMapping("/delete")
    public AjaxResult deleteComment(@RequestParam Long commentId) {
        return toAjax(commentService.deleteComment(commentId));
    }
}
