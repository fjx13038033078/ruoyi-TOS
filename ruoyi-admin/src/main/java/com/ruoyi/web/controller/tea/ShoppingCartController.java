package com.ruoyi.web.controller.tea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.tea.domain.ShoppingCart;
import com.ruoyi.tea.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车管理 Controller
 *
 * @Author 范佳兴
 * @date 2024/3/29 14:59
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/tea/shoppingCart")

public class ShoppingCartController extends BaseController {

    private final ShoppingCartService shoppingCartService;

    /**
     * 获取购物车列表
     */
    @GetMapping("/list")
    public TableDataInfo listShoppingCart() {
        startPage();
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartList();
        return getDataTable(shoppingCartList);
    }

    /**
     * 根据购物车ID获取购物车信息
     */
    @GetMapping("/detail")
    public AjaxResult getShoppingCartById(@RequestParam Long cartId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(cartId);
        return AjaxResult.success(shoppingCart);
    }

    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    public AjaxResult addProductToShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return toAjax(shoppingCartService.addProductToShoppingCart(shoppingCart));
    }

    /**
     * 更新购物车商品数量
     */
    @PostMapping("/update")
    public AjaxResult updateProductQuantity(@RequestBody ShoppingCart shoppingCart) {
        return toAjax(shoppingCartService.updateProductQuantity(shoppingCart));
    }

    /**
     * 从购物车删除商品
     */
    @GetMapping("/delete")
    public AjaxResult deleteProductFromShoppingCart(@RequestParam Long cartId) {
        return toAjax(shoppingCartService.deleteProductFromShoppingCart(cartId));
    }
}
