package com.ruoyi.tea.service;

import com.ruoyi.tea.domain.Shop;

import java.util.List;

/**
 * 店铺管理 Service 接口
 *
 * @Author 范佳兴
 * @date 2024/3/29 14:21
 */
public interface ShopService {

    /**
     * 查询所有店铺
     *
     * @return 店铺列表
     */
    List<Shop> getAllShops();

    /**
     * 根据用户ID查询店铺列表
     *
     * @return 店铺列表
     * @param ownerId 店主ID
     */
    List<Shop> getShopsByOwnerId(Long ownerId);

    /**
     * 根据店铺ID查询店铺信息
     *
     * @param shopId 店铺ID
     * @return 店铺对象
     */
    Shop getShopById(Long shopId);

    /**
     * 添加店铺
     *
     * @param shop 店铺对象
     * @return 添加成功的店铺数量
     */
    boolean addShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop 店铺对象
     * @return 更新成功的店铺数量
     */
    boolean updateShop(Shop shop);

    /**
     * 删除店铺
     *
     * @param shopId 店铺ID
     * @return 删除成功的店铺数量
     */
    boolean deleteShop(Long shopId);
}
