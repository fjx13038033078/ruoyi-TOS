package com.ruoyi.web.controller.tea;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.tea.domain.Record;
import com.ruoyi.tea.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 交易记录管理 Controller
 *
 * @Author fanjaixing
 * @Date 2024/3/31 11:10
 */

@RestController
@RequestMapping("/tea/record")
@RequiredArgsConstructor
public class RecordController extends BaseController {

    private final RecordService recordService;

    // 获取所有订单记录信息列表
    @GetMapping("/listAll")
    public TableDataInfo listAllRecords() {
        List<Record> allRecords = recordService.getAllRecords();
        return getDataTable(allRecords);
    }

    /**
     * 添加交易记录
     *
     * @param record 交易记录对象
     * @return 添加结果
     */
    @PostMapping("/add")
    public AjaxResult addBalanceRecord(@RequestBody Record record) {
        return toAjax(recordService.addBalanceRecord(record));
    }

    /**
     * 根据记录ID删除交易记录
     *
     * @param recordId 记录ID
     * @return 删除结果
     */
    @GetMapping("/delete")
    public AjaxResult deleteBalanceRecord(@RequestParam Long recordId) {
        return toAjax(recordService.deleteBalanceRecord(recordId));
    }

    /**
     * 根据记录ID查询交易记录信息
     *
     * @param recordId 记录ID
     * @return 交易记录信息
     */
    @GetMapping("/get")
    public AjaxResult getBalanceRecordById(@RequestParam Long recordId) {
        Record record = recordService.getBalanceRecordById(recordId);
        return AjaxResult.success(record);
    }
}
