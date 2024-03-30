package com.ruoyi.web.controller.tea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.tea.domain.Product;
import com.ruoyi.tea.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 商品管理 Controller
 *
 * @Author 范佳兴
 * @date 2024/3/18 17:02
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/tea/product")
public class ProductController extends BaseController {

    private final ProductService productService;

    // 获取所有商品列表
    @GetMapping("/listAll")
    public TableDataInfo listAllProducts() {
        startPage();
        List<Product> allProducts = productService.getAllProducts();
        return getDataTable(allProducts);
    }

    //根据店铺ID获取商品列表
    @GetMapping("/list")
    public AjaxResult listProductsByShopId(@RequestParam Long shopId) {
        return success(productService.getProductsByShopId(shopId));
    }

    // 根据商品ID获取商品信息
    @GetMapping("/detail")
    public AjaxResult getProductById(@RequestParam Long productId) {
        return success(productService.getProductById(productId));
    }

    // 添加商品
    @PostMapping("/add")
    public AjaxResult addProduct(@RequestBody Product product) {
        return toAjax(productService.addProduct(product));
    }

    // 更新商品信息
    @PostMapping("/update")
    public AjaxResult updateProduct(@RequestBody Product product) {
        return toAjax(productService.updateProduct(product));
    }

    // 删除商品
    @GetMapping("/delete")
    public AjaxResult deleteProduct(@RequestParam Long productId) {
        return toAjax(productService.deleteProduct(productId));
    }
}
