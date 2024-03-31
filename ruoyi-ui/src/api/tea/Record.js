import request from '@/utils/request'

// 查询所有订单记录信息列表
export function listAllRecords(query) {
  return request({
    url: '/tea/record/listAll',
    method: 'get',
    params: query
  })
}

// 添加交易记录
export function addRecord(data) {
  return request({
    url: '/tea/record/add',
    method: 'post',
    data: data
  })
}

// 删除交易记录
export function deleteRecord(recordId) {
  return request({
    url: '/tea/record/delete',
    method: 'get',
    params: { recordId }
  })
}

// 根据记录ID查询交易记录信息
export function getRecord(recordId) {
  return request({
    url: '/tea/record/get',
    method: 'get',
    params: { recordId }
  })
}
