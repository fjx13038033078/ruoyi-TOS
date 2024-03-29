package com.ruoyi.tea.mapper;

import com.ruoyi.tea.domain.Record;
import org.apache.ibatis.annotations.Mapper;

/**
 * 交易记录 Mapper 接口
 *
 * @Author 范佳兴
 * @date 2024/3/29 11:31
 */
@Mapper
public interface RecordMapper {

    /**
     * 添加交易记录
     *
     * @param record 交易记录对象
     * @return 添加成功的记录数量
     */
    int addBalanceRecord(Record record);

    /**
     * 根据记录ID删除交易记录
     *
     * @param recordId 记录ID
     * @return 删除成功的记录数量
     */
    int deleteBalanceRecord(Long recordId);

    /**
     * 根据记录ID查询交易记录信息
     *
     * @param recordId 记录ID
     * @return 交易记录对象
     */
    Record getBalanceRecordById(Long recordId);
}
