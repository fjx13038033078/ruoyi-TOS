<template>
  <div>
    <!-- 店铺管理页面内容 -->
    <div>
      <!-- 添加店铺按钮 -->
      <el-row :gutter="20" class="mb-20" style="margin-bottom: 10px;margin-left: 10px;margin-top: 10px">
        <el-col>
          <el-button type="primary"
                     plain
                     icon="el-icon-plus"
                     size="medium"
                     @click="handleAddShop"
                     v-hasPermi="['shop:shop:add']">新增店铺
          </el-button>
        </el-col>
      </el-row>

      <!-- 店铺列表 -->
      <el-table :data="shopList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="店铺ID" prop="shopId" align="center"></el-table-column>
        <el-table-column label="店铺名称" prop="shopName" align="center"></el-table-column>
        <el-table-column label="店主姓名" prop="ownerName" align="center"></el-table-column>
        <el-table-column label="店铺简介" prop="shopDescription" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="210px">
          <template slot-scope="scope">
            <el-button type="success" size="mini" @click="handleView(scope.row)" v-hasPermi="['shop:shop:view']">查看</el-button>
            <el-button type="primary" size="mini" @click="handleEdit(scope.row)" v-hasPermi="['shop:shop:edit']">编辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row)" v-hasPermi="['shop:shop:delete']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <pagination
      v-show="totalShops > 0"
      :total="totalShops"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="fetchShops"
    />

    <!-- 添加/编辑店铺对话框 -->
    <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="30%" @close="handleCloseDialog">
      <!-- 对话框内容 -->
      <div>
        <el-form :model="shopForm" label-width="100px">
          <el-form-item label="店铺名称">
            <el-input v-model="shopForm.shopName" :disabled="isReadOnly"></el-input>
          </el-form-item>
          <el-form-item label="店主名称">
            <el-input v-model="shopForm.ownerName" :disabled="isReadOnly"></el-input>
          </el-form-item>
          <el-form-item label="店铺描述">
            <el-input v-model="shopForm.shopDescription" type="textarea" autosize :disabled="isReadOnly"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <!-- 对话框按钮 -->
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button @click="handleCloseDialog">取消</el-button>
        <el-button type="primary" @click="handleSubmit">{{ dialogButtonText }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listShops, addShop, updateShop, deleteShop, getShop } from '@/api/tea/shop'

export default {
  data() {
    return {
      loading: false,
      shopList: [],
      dialogVisible: false,
      dialogTitle: '',
      dialogButtonText: '',
      totalShops: 0,
      isReadOnly: false, // 是否只读模式
      shopForm: {
        shopName: '',
        ownerId: '',
        ownerName:'',
        shopDescription: ''
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
    }
  },

  created() {
    this.fetchShops()
  },

  methods: {
    fetchShops() {
      this.loading = true
      listShops().then(response => {
        this.shopList = response.rows
        this.totalShops = response.total
        this.loading = false
      })
    },

    handleView(row) {
      this.dialogTitle = '查看店铺'
      this.dialogButtonText = '关闭'
      this.loading = true // 开启加载状态
      // 将表单设置为只读模式
      this.isReadOnly = true;
      getShop(row.shopId).then(response => {
        this.shopForm = response.data // 填充表单数据
        this.dialogVisible = true // 打开对话框
        this.loading = false // 关闭加载状态
      }).catch(error => {
        console.error('获取店铺详情失败：', error)
        this.$message.error('获取店铺详情失败，请稍后重试')
        this.loading = false // 关闭加载状态
      })
    },

    handleAddShop() {
      this.dialogTitle = '新增店铺'
      this.dialogButtonText = '添加'
      this.dialogVisible = true
      this.isReadOnly = false;
    },

    handleEdit(row) {
      this.dialogTitle = '编辑店铺'
      this.dialogButtonText = '更新'
      this.shopForm = Object.assign({}, row)
      this.dialogVisible = true
      this.isReadOnly = false;
    },

    handleDelete(row) {
      this.$confirm('确认删除该店铺吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteShop(row.shopId).then(() => {
          this.$message.success('删除成功')
          this.fetchShops()
        })
      })
    },

    handleCloseDialog() {
      this.dialogVisible = false
      this.clearForm()
    },

    clearForm() {
      this.shopForm = {
        shopName: '',
        ownerId: '',
        shopDescription: ''
      }
    },

    handleSubmit() {
      if (this.dialogButtonText === '添加') {
        addShop(this.shopForm).then(() => {
          this.$message.success('添加成功')
          this.dialogVisible = false
          this.clearForm()
          this.fetchShops()
        })
      } else if (this.dialogButtonText === '更新') {
        updateShop(this.shopForm).then(() => {
          this.$message.success('更新成功')
          this.dialogVisible = false
          this.clearForm()
          this.fetchShops()
        })
      } else {
        this.handleCloseDialog()
      }
    }
  }
}
</script>

<style scoped>
/* 样式可以根据需求进行调整 */
</style>

