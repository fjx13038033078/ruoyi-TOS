package com.ruoyi.tea.service.impl;

import com.ruoyi.tea.domain.ShoppingCart;
import com.ruoyi.tea.mapper.ShoppingCartMapper;
import com.ruoyi.tea.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ShoppingCart> getShoppingCartList() {
        return shoppingCartMapper.getShoppingCartList();
    }

    @Override
    public ShoppingCart getShoppingCartById(Long cartId) {
        return shoppingCartMapper.getShoppingCartById(cartId);
    }

    @Override
    public boolean addProductToShoppingCart(ShoppingCart shoppingCart) {
        int rows = shoppingCartMapper.addProductToShoppingCart(shoppingCart);
        return rows > 0;
    }

    @Override
    public boolean updateProductQuantity(ShoppingCart shoppingCart) {
        int rows = shoppingCartMapper.updateProductQuantity(shoppingCart);
        return rows > 0;
    }

    @Override
    public boolean deleteProductFromShoppingCart(Long cartId) {
        int rows = shoppingCartMapper.deleteProductFromShoppingCart(cartId);
        return rows > 0;
    }
}
