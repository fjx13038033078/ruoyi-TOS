package com.ruoyi.tea.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.tea.domain.Shop;
import com.ruoyi.tea.mapper.ProductMapper;
import com.ruoyi.tea.mapper.ShopMapper;
import com.ruoyi.tea.service.ProductService;
import com.ruoyi.tea.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 店铺管理 Service 实现类
 *
 * @Author 范佳兴
 * @date 2024/3/29 14:23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopMapper shopMapper;

    private final ISysUserService iSysUserService;

    private final ProductMapper productMapper;

    private final ISysRoleService iSysRoleService;

    /**
     * 获取所有店铺列表
     *
     * @return 店铺列表
     */
    @Override
    public List<Shop> getAllShops() {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equalsIgnoreCase("owner")){
            startPage();
            List<Shop> shopsByOwnerId = getShopsByOwnerId();
            fillShopOwnerName(shopsByOwnerId);
            return shopsByOwnerId;
        } else {
            startPage();
            List<Shop> allShops = shopMapper.getAllShops();
            fillShopOwnerName(allShops);
            return allShops;
        }
    }

    @Override
    public List<Shop> getShopsByOwnerId() {
        Long ownerId = SecurityUtils.getUserId();
        List<Shop> shopsByOwnerId = shopMapper.getShopsByOwnerId(ownerId);
        fillShopOwnerName(shopsByOwnerId);
        return shopsByOwnerId;
    }

    /**
     * 根据店铺ID查询店铺信息
     *
     * @param shopId 店铺ID
     * @return 店铺对象
     */
    @Override
    public Shop getShopById(Long shopId) {
        Shop shop = shopMapper.getShopById(shopId);
        fillShopOwnerName(Collections.singletonList(shop));
        return shop;
    }

    /**
     * 添加店铺
     *
     * @param shop 店铺对象
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addShop(Shop shop) {
        //获取当前登录用户的ID
        Long userId = SecurityUtils.getUserId();
        shop.setOwnerId(userId);
        int rows = shopMapper.addShop(shop);
        return rows > 0;
    }

    /**
     * 更新店铺信息
     *
     * @param shop 店铺对象
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateShop(Shop shop) {
        Long ownerId = shop.getOwnerId();
        Long userId = SecurityUtils.getUserId();
        if (!ownerId.equals(userId)) {
            throw new RuntimeException("您没有权限修改该店铺信息");
        }
        int rows = shopMapper.updateShop(shop);
        return rows > 0;
    }

    /**
     * 删除店铺
     *
     * @param shopId 店铺ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteShop(Long shopId) {
        int rows = shopMapper.deleteShop(shopId);
        productMapper.deleteProductByShopId(shopId);
        return rows > 0;
    }

    //填充店铺店主姓名
    public void fillShopOwnerName(List<Shop> shops) {
        if (shops != null && !shops.isEmpty()) {
            log.info("shops： "+ shops);
            for (Shop shop : shops) {
                Long ownerId = shop.getOwnerId();
                String ownerName = iSysUserService.selectUserById(ownerId).getNickName();
                shop.setOwnerName(ownerName);
            }
        }
    }
}
