import request from '@/utils/request'

// 查询所有店铺列表
export function listShops(query) {
  return request({
    url: '/tea/shop/listAll',
    method: 'get',
    params: query
  })
}

// 根据店主ID获取店铺列表
export function getShopsByOwnerId(ownerId) {
  return request({
    url: '/listByOwner',
    method: 'get',
    params: { ownerId }
  })
}

// 查询店铺详细信息
export function getShop(shopId) {
  return request({
    url: '/tea/shop/detail',
    method: 'get',
    params: { shopId }
  })
}

// 添加店铺
export function addShop(data) {
  return request({
    url: '/tea/shop/add',
    method: 'post',
    data: data
  })
}

// 更新店铺信息
export function updateShop(data) {
  return request({
    url: '/tea/shop/update',
    method: 'post',
    data: data
  })
}

// 删除店铺
export function deleteShop(shopId) {
  return request({
    url: '/tea/shop/delete',
    method: 'get',
    params: { shopId }
  })
}
