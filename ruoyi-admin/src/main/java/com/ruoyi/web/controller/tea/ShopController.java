package com.ruoyi.web.controller.tea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.tea.domain.Shop;
import com.ruoyi.tea.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 店铺管理 Controller
 *
 * @Author 范佳兴
 * @date 2024/3/29 14:32
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/tea/shop")
public class ShopController extends BaseController {
    private final ShopService shopService;

    // 获取所有店铺列表
    @GetMapping("/listAll")
    public TableDataInfo listAllShops() {
        startPage();
        List<Shop> allShops = shopService.getAllShops();
        return getDataTable(allShops);
    }

    // 根据店主ID获取店铺列表
    @GetMapping("/listByOwner")
    public AjaxResult getShopsByOwnerId() {
        return success(shopService.getShopsByOwnerId());
    }

    // 根据店铺ID获取店铺信息
    @GetMapping("/detail")
    public AjaxResult getShopById(@RequestParam Long shopId) {
        return success(shopService.getShopById(shopId));
    }

    // 添加店铺
    @PostMapping("/add")
    public AjaxResult addShop(@RequestBody Shop shop) {
        return toAjax(shopService.addShop(shop));
    }

    // 更新店铺信息
    @PostMapping("/update")
    public AjaxResult updateShop(@RequestBody Shop shop) {
        return toAjax(shopService.updateShop(shop));
    }

    // 删除店铺
    @GetMapping("/delete")
    public AjaxResult deleteShop(@RequestParam Long shopId) {
        return toAjax(shopService.deleteShop(shopId));
    }
}
