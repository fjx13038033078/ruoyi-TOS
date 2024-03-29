package com.ruoyi.tea.service;

import com.ruoyi.tea.domain.Product;
import java.util.List;

/**
 * 商品管理 Service 接口
 *
 * @Author 范佳兴
 * @date 2024/3/18 17:18
 */
public interface ProductService {
    /**
     * 获取所有商品列表
     *
     * @return 商品列表
     */
    List<Product> getAllProducts();

    /**
     * 根据店铺ID查询商品列表
     *
     * @param shopId 店铺ID
     * @return 店铺列表
     */
    List<Product> getProductsByShopId(Long shopId);

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
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addProduct(Product product);

    /**
     * 更新商品信息
     *
     * @param product 商品对象
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateProduct(Product product);

    /**
     * 删除商品
     *
     * @param productId 商品ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteProduct(Long productId);

    /**
     * 根据商家ID查询商品数量
     *
     * @param merchantId 商家ID
     * @return 商品数量
     */
    int countByMerchantId(Long merchantId);
}
