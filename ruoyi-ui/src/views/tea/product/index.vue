<template>
  <div>
    <!-- 商品管理页面内容 -->
    <div v-if="activeTab === 'product'">
      <div class="app-container">
        <!-- 添加商品按钮 -->
        <el-row :gutter="20" class="mb-20" style="margin-bottom: 20px;">
          <el-col>
            <el-button type="primary" @click="handleAddProduct" v-hasPermi="['tea:product:add']">新增商品</el-button>
          </el-col>
        </el-row>

        <!-- 商品列表 -->
        <el-table :data="productList" v-loading="loading" style="width: 100%" border>
          <el-table-column label="商品ID" prop="productId" align="center"></el-table-column>
          <el-table-column label="店铺名称" prop="shopName" align="center"></el-table-column>
          <el-table-column label="商品名称" prop="productName" align="center"></el-table-column>
          <el-table-column label="商品描述" prop="description" align="center"></el-table-column>
          <el-table-column label="商品价格" prop="price" align="center"></el-table-column>
          <el-table-column label="操作" align="center" width="380px">
            <template slot-scope="scope">
              <el-button type="warning" size="mini" @click="handleAddToCart(scope.row)">
                加入购物车
              </el-button>
              <el-button type="info" size="mini" @click="handlePurchase(scope.row)" v-hasPermi="['tea:product:purchase']">
                购买
              </el-button>
              <el-button type="success" size="mini" @click="handleView(scope.row)" v-hasPermi="['tea:product:detail']">
                查看
              </el-button>
              <el-button type="primary" size="mini" @click="handleEdit(scope.row)" v-hasPermi="['tea:product:edit']">
                编辑
              </el-button>
              <el-button type="danger" size="mini" @click="handleDelete(scope.row)" v-hasPermi="['tea:product:delete']">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页组件 -->
        <pagination
          v-show="totalProducts > 0"
          :total="totalProducts"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="fetchProducts"
        />

        <!-- 添加/编辑商品对话框 -->
        <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="30%" @close="handleCloseDialog">
          <!-- 对话框内容 -->
          <div>
            <el-form :model="productForm" label-width="100px">
              <!-- 店铺名称下拉选择 -->
              <el-form-item label="店铺名称">
                <el-select v-model="productForm.shopId" placeholder="请选择店铺" :disabled="isReadOnly">
                  <el-option
                    v-for="shop in shopOptions"
                    :key="shop.shopId"
                    :label="shop.shopName"
                    :value="shop.shopId"
                  ></el-option>
                </el-select>
              </el-form-item>
              <!-- 商品名称 -->
              <el-form-item label="商品名称">
                <el-input v-model="productForm.productName" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <!-- 商品描述 -->
              <el-form-item label="商品描述">
                <el-input v-model="productForm.description" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <!-- 商品价格 -->
              <el-form-item label="商品价格">
                <el-input v-model="productForm.price" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <!-- 根据当前模式决定显示 ImageUpload 或 ImagePreview -->
              <el-form-item label="商品图片">
                <template v-if="!isReadOnly">
                  <ImageUpload v-model="productForm.productImage" :disabled="isReadOnly"><</ImageUpload>
                </template>
                <template v-else>
                  <ImagePreview :src="productForm.productImage" :disabled="isReadOnly"></ImagePreview>
                </template>
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
    </div>
  </div>
</template>

<script>
import {listProducts, addProduct, updateProduct, deleteProduct, getProduct} from '@/api/tea/product'
import {getShopsByOwnerId} from "@/api/tea/shop";
import {addRecord} from "@/api/tea/Record";
import {addProductToShoppingCart} from "@/api/tea/shoppingCart";

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      activeTab: 'product', // 当前激活的选项卡，默认为商品管理
      productList: [], // 商品列表数据
      shopOptions: [], //店铺选项列表
      // 总条数
      totalProducts: 0,
      dialogVisible: false, // 控制新增/编辑商品对话框的显示与隐藏
      dialogTitle: '', // 对话框标题
      dialogButtonText: '', // 对话框按钮文本
      productForm: { // 新增/编辑商品表单
        shopId: '',
        shopName: '',
        productName: '',
        description: '',
        price: null,
        productImage: ''
      },
      isReadOnly: false, // 是否只读模式
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      }
    }
  },
  created() {
    // 在页面加载时获取商品列表
    this.fetchProducts()
    this.fetchShops()
  },
  methods: {
    // 获取商品列表
    fetchProducts() {
      this.loading = true
      listProducts(this.queryParams).then(response => {
        this.productList = response.rows
        this.totalProducts = response.total
        this.loading = false
      })
    },

    // 获取店铺列表
    fetchShops() {
      getShopsByOwnerId().then(response => {
        this.shopOptions = response.data.map(shop => ({
          shopId: shop.shopId,
          shopName: shop.shopName
        }));
      });
    },

    // 清空表单数据
    clearForm() {
      this.productForm = {
        productName: '',
        description: '',
        price: null,
        merchantId: null
      }
    },

    // 处理购买操作
    handlePurchase(row) {
      // 获取购买商品信息
      const {productId, price, shopId} = row;
      // 调用添加订单记录接口
      addRecord({
        shopId: shopId,
        productId: productId,
        transactionAmount: price, // 使用购买商品的费用作为交易金额
        transactionType: 1
      }).then(() => {
        // 添加订单记录成功后的处理逻辑
        this.$message.success('购买成功！');
      });
    },

    // 添加商品
    handleAddProduct() {
      this.dialogTitle = "新增商品"
      this.dialogButtonText = "添加"
      this.isReadOnly = false // 设置为可编辑模式
      this.dialogVisible = true // 打开对话框
    },

    // 添加商品
    addProduct() {
      // 调用后端接口添加商品
      addProduct(this.productForm).then(() => {
        // 添加成功后重新获取商品列表
        this.fetchProducts()
        this.dialogVisible = false // 关闭对话框
        // 清空表单数据
        this.clearForm()
      })
    },

    // 更新商品
    updateProduct() {
      // 调用后端接口更新商品
      updateProduct(this.productForm).then(() => {
        // 更新成功后重新获取商品列表
        this.fetchProducts()
        this.dialogVisible = false // 关闭对话框
        // 清空表单数据
        this.clearForm()
        // 将对话框按钮文本设置为其他值，避免再次触发更新操作
        this.dialogButtonText = '更新成功'
      })
    },
    // 查看商品详情
    handleView(row) {
      // 设置对话框标题为查看商品详情
      this.dialogTitle = '查看商品详情';
      // 设置对话框按钮文本为关闭
      this.dialogButtonText = '关闭';
      // 将表单设置为只读模式
      this.isReadOnly = true;
      // 调用后端接口获取商品详细信息
      getProduct(row.productId).then(response => {
        // 将获取到的商品详情填充到表单中
        this.productForm = response.data;
        // 打开对话框
        this.dialogVisible = true;
      }).catch(error => {
        // 处理获取商品详情失败的情况
        console.error('获取商品详情失败:', error);
      });
    },
    // 删除商品
    handleDelete(row) {
      // 弹出确认框，确认删除后调用删除商品方法
      this.$confirm('确认删除该商品吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 调用后端接口删除商品
        deleteProduct(row.productId).then(() => {
          // 删除成功后重新获取商品列表
          this.fetchProducts();
          // 弹出删除成功提示
          this.$message.success('商品删除成功！');
        }).catch(error => {
          // 处理删除商品失败的情况
          console.error('删除商品失败:', error);
          // 弹出删除失败提示
          this.$message.error('商品删除失败，请重试！');
        });
      }).catch(() => {
        // 用户取消删除操作，不做任何处理
      });
    },

    // 编辑按钮点击事件
    handleEdit(row) {
      // 将编辑的商品数据填充到表单中
      this.productForm = Object.assign({}, row)
      this.dialogTitle = '编辑商品' // 设置对话框标题为编辑商品
      this.dialogButtonText = '更新' // 设置对话框按钮文本为更新
      this.isReadOnly = false // 设置为可编辑模式
      this.dialogVisible = true // 打开对话框
    },

    // 提交表单
    handleSubmit() {
      // 根据表单是否有商品ID来判断是添加还是更新
      if (this.productForm.productId) {
        this.updateProduct() // 更新商品
      } else {
        this.addProduct() // 添加商品
      }
    },

    // 添加商品到购物车
    handleAddToCart(row) {
      // 调用添加商品到购物车接口
      addProductToShoppingCart({
        productId: row.productId, // 商品ID
      }).then(() => {
        // 添加成功提示
        this.$message.success('商品已成功加入购物车！');
      });
    },

    // 关闭对话框
    handleCloseDialog() {
      this.dialogVisible = false
      // 清空表单数据
      this.clearForm()
    }
  }
}
</script>
