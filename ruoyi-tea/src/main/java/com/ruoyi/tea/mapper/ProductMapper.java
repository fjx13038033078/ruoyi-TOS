package com.ruoyi.tea.mapper;

import com.ruoyi.tea.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品管理 Mapper 接口
 *
 * @Author 范佳兴
 * @date 2024/3/18 17:15
 */
@Mapper
public interface ProductMapper {

    /**
     * 获取所有商品列表
     *
     * @return 商品列表
     */
    List<Product> getAllProducts();

    /**
     * 根据商品ID查询商品信息
     *
     * @param productId 商品ID
     * @return 商品对象
     */
    Product getProductById(Long productId);

    /**
     * 添加商品
     *
     * @param product 商品对象
     * @return 添加成功的商品数量
     */
    int addProduct(Product product);

    /**
     * 更新商品信息
     *
     * @param product 商品对象
     * @return 更新成功的商品数量
     */
    int updateProduct(Product product);

    /**
     * 删除商品
     *
     * @param productId 商品ID
     * @return 删除成功的商品数量
     */
    int deleteProduct(Long productId);

    /**
     * 根据商家ID查询商品数量
     *
     * @param merchantId 商家ID
     * @return 商品数量
     */
    int countByMerchantId(Long merchantId);

    /**
     * 根据店铺ID查询商品列表
     *
     * @param shopId 店主ID
     * @return 店铺列表
     */
    List<Product> getProductsByShopId(Long shopId);
}
