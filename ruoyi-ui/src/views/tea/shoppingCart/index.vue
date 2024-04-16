<template>
  <div>
    <!-- 购物车管理页面内容 -->
    <div>
      <div class="app-container">
        <!-- 商品列表 -->
        <el-table :data="cartList" v-loading="loading" style="width: 100%" border>
          <el-table-column label="购物车ID" prop="cartId" align="center"></el-table-column>
          <el-table-column label="用户名" prop="userName" align="center"></el-table-column>
          <el-table-column label="商品名" align="center">
            <template slot-scope="scope">
              {{ scope.row.product.productName }}
            </template>
          </el-table-column>
<!--          <el-table-column label="商品数量" prop="quantity" align="center"></el-table-column>-->
          <el-table-column label="商品单价" align="center">
            <template slot-scope="scope">
              {{ scope.row.product.price }}
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="200px">
            <template slot-scope="scope">
              <el-button type="info" size="mini" @click="handleRemoveFromCart(scope.row)" v-hasPermi="['tea:shopCart:remove']">
                移除购物车
              </el-button>
              <el-button type="success" size="mini" @click="handlePurchase(scope.row)" v-hasPermi="['tea:shopCart:purchase']">
                购买
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页组件 -->
        <pagination
          v-show="totalCartItems > 0"
          :total="totalCartItems"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="fetchShoppingCart"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { listShoppingCart, deleteProductFromShoppingCart } from '@/api/tea/shoppingCart'
import { addRecord } from '@/api/tea/Record'

export default {
  data() {
    return {
      loading: true,
      cartList: [],
      totalCartItems: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10
      }
    }
  },
  created() {
    this.fetchShoppingCart()
  },
  methods: {
    fetchShoppingCart() {
      this.loading = true
      listShoppingCart(this.queryParams).then(response => {
        this.cartList = response.rows
        this.totalCartItems = response.total
        this.loading = false
      })
    },
    handleRemoveFromCart(row) {
      // 调用删除购物车商品接口
      deleteProductFromShoppingCart(row.cartId).then(() => {
        // 移除成功后重新获取购物车列表
        this.fetchShoppingCart()
        this.$message.success('商品已从购物车移除！')
      }).catch(error => {
        // 处理移除失败的情况
        console.error('移除购物车商品失败:', error)
        this.$message.error('移除购物车商品失败，请重试！')
      })
    },
    handlePurchase(row) {
      // 获取购买商品信息
      const { productId, product: {price, shopId} } = row;
      // 调用添加订单记录接口
      addRecord({
        productId: productId,
        transactionAmount: price, // 使用购买商品的数量作为交易金额
        shopId: shopId,
        transactionType: 1 // 购买类型为 1
      }).then(() => {
        // 添加订单记录成功后的处理逻辑
        this.$message.success('购买成功！');
      });
    }
  }
}
</script>

<style scoped>
/* 样式可以根据需要自行添加 */
</style>
