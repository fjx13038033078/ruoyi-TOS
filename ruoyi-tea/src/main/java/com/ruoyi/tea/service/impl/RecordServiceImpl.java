package com.ruoyi.tea.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.tea.domain.Product;
import com.ruoyi.tea.domain.Record;
import com.ruoyi.tea.domain.Shop;
import com.ruoyi.tea.mapper.RecordMapper;
import com.ruoyi.tea.service.ProductService;
import com.ruoyi.tea.service.RecordService;
import com.ruoyi.tea.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 交易记录 Service 实现类
 *
 * @Author fanjaixing
 * @Date 2024/3/31 11:06
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RecordServiceImpl implements RecordService {

    private final RecordMapper recordMapper;

    private final ProductService productService;

    private final ISysRoleService iSysRoleService;

    private final ISysUserService iSysUserService;

    private final ShopService shopService;

    /**
     * 查询全部交易记录
     *
     * @return 全部交易记录列表
     */
    @Override
    public List<Record> getAllRecords() {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equalsIgnoreCase("admin")) {
            startPage();
            List<Record> allRecords = recordMapper.getAllRecords();
            fillShopProductUserName(allRecords);
            return allRecords;
        } else {
            startPage();
            List<Record> recordsByUserId = recordMapper.getRecordsByUserId(userId);
            fillShopProductUserName(recordsByUserId);
            return recordsByUserId;
        }
    }

    /**
     * 添加交易记录
     *
     * @param record 交易记录对象
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBalanceRecord(Record record) {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        // 获取当前用户的余额
        BigDecimal userBalance = iSysUserService.selectUserById(userId).getBalance();
        // 获取当前用户的角色
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if ("admin".equals(role) || "owner".equals(role)) {
            throw new RuntimeException("管理员或店主无法购买商品");
        }
        BigDecimal transactionAmount = record.getTransactionAmount();
        // 判断余额是否足够支付课程费用
        if (userBalance.compareTo(transactionAmount) < 0) {
            throw new RuntimeException("您的余额不足");
        }
        //获取店主当前余额
        Long shopId = record.getShopId();
        Long ownerId = shopService.getShopById(shopId).getOwnerId();
        BigDecimal ownerBalance = iSysUserService.selectUserById(ownerId).getBalance();
        // 更新用户余额信息
        BigDecimal newBalance = userBalance.subtract(transactionAmount);
        SysUser currentUser = iSysUserService.selectUserById(userId);
        currentUser.setBalance(newBalance);
        iSysUserService.updateUserBalance(currentUser);
        // 设置用户消费记录信息
        record.setUserId(userId);
        record.setTransactionTime(LocalDateTime.now());
        record.setBalance(newBalance);
        // 添加用户消费记录
        recordMapper.addBalanceRecord(record);
        // 更新店主的余额信息
        BigDecimal newOwnerBalance = ownerBalance.add(transactionAmount);
        SysUser owner = iSysUserService.selectUserById(ownerId);
        owner.setBalance(newOwnerBalance);
        iSysUserService.updateUserBalance(owner);
        // 设置店主收益记录信息
        Record ownerRecord = new Record();
        ownerRecord.setUserId(ownerId);
        ownerRecord.setTransactionAmount(transactionAmount);
        ownerRecord.setTransactionTime(LocalDateTime.now());
        ownerRecord.setBalance(newOwnerBalance);
        ownerRecord.setTransactionType(0);
        ownerRecord.setProductId(record.getProductId());
        ownerRecord.setShopId(record.getShopId());
        // 添加管理员收益记录
        recordMapper.addBalanceRecord(ownerRecord);
        return true;
    }

    /**
     * 根据记录ID删除交易记录
     *
     * @param recordId 记录ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteBalanceRecord(Long recordId) {
        int rows = recordMapper.deleteBalanceRecord(recordId);
        return rows > 0;
    }

    /**
     * 根据记录ID查询交易记录信息
     *
     * @param recordId 记录ID
     * @return 交易记录对象
     */
    @Override
    public Record getBalanceRecordById(Long recordId) {
        return recordMapper.getBalanceRecordById(recordId);
    }

    /**
     * 填充用户，商品和店铺名称
     *
     * @param records
     */
    private void fillShopProductUserName(List<Record> records) {
        for (Record record : records) {
            Long productId = record.getProductId();
            Long shopId = record.getShopId();
            Long userId = record.getUserId();
            Product productById = productService.getProductById(productId);
            Shop shopById = shopService.getShopById(shopId);
            SysUser sysUser = iSysUserService.selectUserById(userId);
            if (record.getUserId() != null && StringUtils.isNotBlank(record.getUserName())) {
                record.setProductName(productById.getProductName());
            }
            if (record.getShopId() != null && StringUtils.isNotBlank(record.getShopName())) {
                record.setShopName(shopById.getShopName());
            }
            if (record.getUserId() != null && StringUtils.isNotBlank(record.getUserName())) {
                record.setUserName(sysUser.getNickName());
            }
        }
    }
}
