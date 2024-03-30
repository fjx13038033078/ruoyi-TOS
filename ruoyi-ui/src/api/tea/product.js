// product.js

import request from '@/utils/request'

// 查询所有商品列表
export function listProducts(query) {
  return request({
    url: '/tea/product/listAll',
    method: 'get',
    params: query
  })
}

// 根据店铺ID获取商品列表
export function listProductsByShopId(shopId) {
  return request({
    url: '/tea/product/listByShopId',
    method: 'get',
    params: { shopId }
  })
}

// 查询商品详细信息
export function getProduct(productId) {
  return request({
    url: '/tea/product/detail',
    method: 'get',
    params: { productId }
  })
}

// 添加商品
export function addProduct(data) {
  return request({
    url: '/tea/product/add',
    method: 'post',
    data: data
  })
}

// 更新商品信息
export function updateProduct(data) {
  return request({
    url: '/tea/product/update',
    method: 'post',
    data: data
  })
}

// 删除商品
export function deleteProduct(productId) {
  return request({
    url: '/tea/product/delete',
    method: 'get',
    params: { productId }
  })
}
