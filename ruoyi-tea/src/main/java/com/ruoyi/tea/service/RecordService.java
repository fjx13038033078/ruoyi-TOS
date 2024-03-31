package com.ruoyi.tea.service;
import com.ruoyi.tea.domain.Record;

import java.util.List;

/**
 * 交易记录 Service 接口
 *
 * @Author fanjaixing
 * @Date 2024/3/31 11:05
 */
public interface RecordService {

    /**
     * 查询全部订单
     *
     * @return 全部订单列表
     */
    List<Record> getAllRecords();

    /**
     * 添加交易记录
     *
     * @param record 交易记录对象
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addBalanceRecord(Record record);

    /**
     * 根据记录ID删除交易记录
     *
     * @param recordId 记录ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteBalanceRecord(Long recordId);

    /**
     * 根据记录ID查询交易记录信息
     *
     * @param recordId 记录ID
     * @return 交易记录对象
     */
    Record getBalanceRecordById(Long recordId);
}
