package com.ruoyi.tea.service.impl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.tea.domain.Product;
import com.ruoyi.tea.domain.Shop;
import com.ruoyi.tea.mapper.ProductMapper;
import com.ruoyi.tea.service.ProductService;
import com.ruoyi.tea.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private final ShopService shopService;

    private final ISysRoleService iSysRoleService;

    /**
     * 获取所有商品列表
     *
     * @return 商品列表
     */
    @Override
    public List<Product> getAllProducts() {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equals("owner")){
            List<Shop> shops = shopService.getShopsByOwnerId();
            Set<Product> products = new HashSet<>();
            for (Shop shop : shops){
                List<Product> productsByShopId = productMapper.getProductsByShopId(shop.getShopId());
                products.addAll(productsByShopId);
            }
            ArrayList<Product> products1 = new ArrayList<>(products);
            fillShopName(products1);
            return products1;
        } else {
            List<Product> allProducts = productMapper.getAllProducts();
            fillShopName(allProducts);
            return allProducts;
        }
    }

    @Override
    public List<Product> getProductsByShopId(Long shopId){
        return productMapper.getProductsByShopId(shopId);
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

    @Override
    public boolean deleteProductByShopId(Long shopId){
        return productMapper.deleteProductByShopId(shopId) > 0;
    }

    /**
     * 填充商品名称
     * @param products
     */
    private void fillShopName(List<Product> products){
        for (Product product : products){
            Long shopId = product.getShopId();
            Shop shopById = shopService.getShopById(shopId);
            product.setShopName(shopById.getShopName());
        }
    }
}
