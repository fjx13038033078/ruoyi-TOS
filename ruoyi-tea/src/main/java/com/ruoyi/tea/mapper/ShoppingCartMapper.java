package com.ruoyi.tea.mapper;

import com.ruoyi.tea.domain.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 购物车管理 Mapper 接口
 *
 * @Author 范佳兴
 * @date 2024/3/29 11:26
 */
@Mapper
public interface ShoppingCartMapper {
    /**
     * 获取购物车列表
     *
     * @return 购物车列表
     */
    List<ShoppingCart> getShoppingCartList();

    /**
     * 根据购物车ID查询购物车信息
     *
     * @param cartId 购物车ID
     * @return 购物车对象
     */
    ShoppingCart getShoppingCartById(Long cartId);

    /**
     * 添加商品到购物车
     *
     * @param shoppingCart 购物车对象
     * @return 添加成功的购物车数量
     */
    int addProductToShoppingCart(ShoppingCart shoppingCart);

    /**
     * 更新购物车商品数量
     *
     * @param shoppingCart 购物车对象
     * @return 更新成功的购物车数量
     */
    int updateProductQuantity(ShoppingCart shoppingCart);

    /**
     * 从购物车删除商品
     *
     * @param cartId 购物车ID
     * @return 删除成功的购物车数量
     */
    int deleteProductFromShoppingCart(Long cartId);
}
