import request from '@/utils/request'

// 查询辅导室预约信息列表
export function listOrder(query) {
  return request({
    url: '/system/order/list',
    method: 'get',
    params: query
  })
}

// 查询辅导室预约信息详细
export function getOrder(timeId) {
  return request({
    url: '/system/order/' + timeId,
    method: 'get'
  })
}

// 新增辅导室预约信息
export function addOrder(data) {
  return request({
    url: '/system/order',
    method: 'post',
    data: data
  })
}

// 修改辅导室预约信息
export function updateOrder(data) {
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}

// 删除辅导室预约信息
export function delOrder(timeId) {
  return request({
    url: '/system/order/' + timeId,
    method: 'delete'
  })
}


//查找使用辅导室编号
export function getlocationList() {
  return request({
    url: '/system/order/aall',
    method: 'get'
  })
}
