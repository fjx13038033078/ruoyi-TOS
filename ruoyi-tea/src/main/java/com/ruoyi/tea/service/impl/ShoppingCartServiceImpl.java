package com.ruoyi.tea.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.tea.domain.Product;
import com.ruoyi.tea.domain.ShoppingCart;
import com.ruoyi.tea.mapper.ProductMapper;
import com.ruoyi.tea.mapper.ShoppingCartMapper;
import com.ruoyi.tea.service.ProductService;
import com.ruoyi.tea.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 购物车管理 Service 实现类
 *
 * @Author 范佳兴
 * @date 2024/3/29 14:54
 */
@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartMapper shoppingCartMapper;

    private final ISysRoleService iSysRoleService;

    private final ProductMapper productMapper;

    private final ISysUserService iSysUserService;

    /**
     * 获取购物车列表
     *
     * @return 购物车列表
     */
    @Override
    public List<ShoppingCart> getShoppingCartList() {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equalsIgnoreCase("admin")) {
            startPage();
            List<ShoppingCart> shoppingCartList = shoppingCartMapper.getShoppingCartList();
            fillUserName(shoppingCartList);
            fillProduct(shoppingCartList);
            return shoppingCartList;
        } else {
            startPage();
            List<ShoppingCart> shoppingCarts = shoppingCartMapper.listCartItemsByUserId(userId);
            fillUserName(shoppingCarts);
            fillProduct(shoppingCarts);
            return shoppingCarts;
        }
    }

    /**
     * 获取购物车详情
     * @param cartId 购物车ID
     * @return
     */
    @Override
    public ShoppingCart getShoppingCartById(Long cartId) {
        return shoppingCartMapper.getShoppingCartById(cartId);
    }

    /**
     * 添加商品到购物车
     * @param shoppingCart 购物车对象
     * @return
     */
    @Override
    public boolean addProductToShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setQuantity(1);
        shoppingCart.setUserId(SecurityUtils.getUserId());
        int rows = shoppingCartMapper.addProductToShoppingCart(shoppingCart);
        return rows > 0;
    }

    /**
     * 更新购物车商品数量
     * @param shoppingCart 购物车对象
     * @return
     */
    @Override
    public boolean updateProductQuantity(ShoppingCart shoppingCart) {
        int rows = shoppingCartMapper.updateProductQuantity(shoppingCart);
        return rows > 0;
    }

    /**
     *
     * @param cartId 购物车ID
     * @return
     */
    @Override
    public boolean deleteProductFromShoppingCart(Long cartId) {
        int rows = shoppingCartMapper.deleteProductFromShoppingCart(cartId);
        return rows > 0;
    }

    /**
     * 填充购物车商品信息
     * @param shoppingCarts 购物车列表
     */
    private void fillProduct(List<ShoppingCart> shoppingCarts) {
        shoppingCarts.forEach(shoppingCart -> {
            Product productById = productMapper.getProductById(shoppingCart.getProductId());
            if (productById != null) {
                shoppingCart.setProduct(productById);
            }
        });
    }

    /**
     * @param shoppingCarts
     */
    private void fillUserName(List<ShoppingCart> shoppingCarts){
        shoppingCarts.forEach(shoppingCart -> {
            String nickName = iSysUserService.selectUserById(shoppingCart.getUserId()).getNickName();
            if (nickName != null) {
                shoppingCart.setUserName(nickName);
            }
        });
    };
}
