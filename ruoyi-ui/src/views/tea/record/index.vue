<template>
  <div>
    <!-- 商品购买订单记录页面内容 -->
    <div class="app-container">
      <!-- 商品购买订单记录列表 -->
      <el-table :data="recordList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="记录ID" prop="recordId" align="center"></el-table-column>
        <el-table-column label="姓名" prop="userName" align="center"></el-table-column>
        <el-table-column label="商品名称" prop="productName" align="center"></el-table-column>
        <el-table-column label="店铺名称" prop="shopName" align="center"></el-table-column>
        <el-table-column label="交易额" prop="transactionAmount" align="center"></el-table-column>
        <el-table-column label="交易类型" prop="transactionType" align="center">
          <template slot-scope="scope">
            {{ scope.row.transactionType === 0 ? '收入' : '支出' }}
          </template>
        </el-table-column>
        <el-table-column label="交易时间" prop="transactionTime" align="center"></el-table-column>
        <el-table-column label="余额" prop="balance" align="center"></el-table-column>
      </el-table>

      <!-- 分页组件 -->
      <pagination
        v-show="totalRecords > 0"
        :total="totalRecords"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="fetchRecords"
      />
    </div>
  </div>
</template>

<script>
import { listAllRecords } from "@/api/tea/Record"

export default {
  data() {
    return {
      loading: true,
      recordList: [],
      totalRecords: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10
      }
    };
  },
  created() {
    this.fetchRecords();
  },
  methods: {
    fetchRecords() {
      this.loading = true;
      listAllRecords(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.totalRecords = response.total;
        this.loading = false;
      });
    }
  }
};
</script>

<style scoped>

</style>

