package com.ruoyi.tea.service.impl;
import com.ruoyi.tea.domain.Product;
import com.ruoyi.tea.mapper.ProductMapper;
import com.ruoyi.tea.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理 Service 实现类
 *
 * @Author 范佳兴
 * @date 2024/3/18 17:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    /**
     * 获取所有商品列表
     *
     * @return 商品列表
     */
    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    /**
     * 根据商品ID查询商品信息
     *
     * @param productId 商品ID
     * @return 商品对象
     */
    @Override
    public Product getProductById(Long productId) {
        return productMapper.getProductById(productId);
    }

    /**
     * 添加商品
     *
     * @param product 商品对象
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addProduct(Product product) {
        int rows = productMapper.addProduct(product);
        return rows > 0;
    }

    /**
     * 更新商品信息
     *
     * @param product 商品对象
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateProduct(Product product) {
        int rows = productMapper.updateProduct(product);
        return rows > 0;
    }

    /**
     * 删除商品
     *
     * @param productId 商品ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteProduct(Long productId) {
        int rows = productMapper.deleteProduct(productId);
        return rows > 0;
    }

    /**
     * 根据商家ID查询商品数量
     *
     * @param merchantId 商家ID
     * @return 商品数量
     */
    @Override
    public int countByMerchantId(Long merchantId) {
        return productMapper.countByMerchantId(merchantId);
    }
}
