import request from '@/utils/request'

// 查询购物车列表
export function listShoppingCart(query) {
  return request({
    url: '/tea/shoppingCart/list',
    method: 'get',
    params: query
  })
}

// 查询购物车详细信息
export function getShoppingCart(cartId) {
  return request({
    url: '/tea/shoppingCart/detail',
    method: 'get',
    params: { cartId }
  })
}

// 添加商品到购物车
export function addProductToShoppingCart(data) {
  return request({
    url: '/tea/shoppingCart/add',
    method: 'post',
    data: data
  })
}

// 更新购物车商品数量
export function updateProductQuantity(data) {
  return request({
    url: '/tea/shoppingCart/update',
    method: 'post',
    data: data
  })
}

// 从购物车删除商品
export function deleteProductFromShoppingCart(cartId) {
  return request({
    url: '/tea/shoppingCart/delete',
    method: 'get',
    params: { cartId }
  })
}
