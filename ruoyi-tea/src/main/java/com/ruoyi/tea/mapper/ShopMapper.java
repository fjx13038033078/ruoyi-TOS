package com.ruoyi.tea.mapper;

import com.ruoyi.tea.domain.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 店铺管理 Mapper 接口
 *
 * @Author 范佳兴
 * @date 2024/3/29 11:21
 */
@Mapper
public interface ShopMapper {
    /**
     * 获取所有店铺列表
     *
     * @return 店铺列表
     */
    List<Shop> getAllShops();

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
    int addShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop 店铺对象
     * @return 更新成功的店铺数量
     */
    int updateShop(Shop shop);

    /**
     * 删除店铺
     *
     * @param shopId 店铺ID
     * @return 删除成功的店铺数量
     */
    int deleteShop(Long shopId);

    /**
     * 根据店主ID查询店铺数量
     *
     * @param ownerId 店主ID
     * @return 店铺数量
     */
    int countByOwnerId(Long ownerId);
}
