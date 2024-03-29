import request from '@/utils/request'

// 根据商品ID获取评论列表
export function listCommentsByProductId(productId) {
  return request({
    url: '/tea/comment/listByProductId',
    method: 'get',
    params: { productId }
  })
}

// 获取评论详细信息
export function getComment(commentId) {
  return request({
    url: '/tea/comment/detail',
    method: 'get',
    params: { commentId }
  })
}

// 添加评论
export function addComment(data) {
  return request({
    url: '/tea/comment/add',
    method: 'post',
    data: data
  })
}

// 删除评论
export function deleteComment(commentId) {
  return request({
    url: '/tea/comment/delete',
    method: 'get',
    params: { commentId }
  })
}
