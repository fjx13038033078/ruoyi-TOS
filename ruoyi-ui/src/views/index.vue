<template>
  <div>
<!--    <div id="curtain">-->
<!--      <h1 data-heading="管">茶商城管理系统</h1>-->
<!--    </div>-->
    <div id="curtain" style="width: 100%; height: 100px">
      <el-row justify="center" align="middle" style="height: 100px" class="background-title">
        <el-col :span="24">
          <h1 class="centered-title" style="margin-top: -8px" data-text="校园二手商品交易系统">{{ titleText }}</h1>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-row style="margin-top: 20px;">
        <el-col :span="12">
          <el-card style="margin-right: 20px; height: 420px;">
            <h3 slot="header">站外导航</h3>
            <el-carousel :interval="4000" height="300px">
              <el-carousel-item>
                <a href="https://www.baidu.com" target="_blank">
                  <img src="../assets/images/01.jpg" alt="Image 1" style="width: 100%;">
                </a>
              </el-carousel-item>
              <el-carousel-item>
                <a href="https://www.jd.com" target="_blank">
                  <img src="../assets/images/02.jpg" alt="Image 2" style="width: 100%;">
                </a>
              </el-carousel-item>
              <el-carousel-item>
                <a href="https://www.taobao.com" target="_blank">
                  <img src="../assets/images/03.jpg" alt="Image 3" style="width: 100%;">
                </a>
              </el-carousel-item>
            </el-carousel>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card style="margin-right: 20px; height: 420px;">
            <h3 slot="header">商城简介</h3>
            <p>请在这里填写更多的文字说明</p>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import {listNotice, getNotice} from "@/api/system/notice";
import * as echarts from 'echarts'


export default {
  name: "Notice",
  dicts: ['sys_notice_status', 'sys_notice_type'],
  data() {
    return {
      titleText : '校园二手商品交易系统',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      venueCourtMapData: {}, // 存储场馆场地数量映射的数据
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        noticeTitle: undefined,
        createBy: undefined,
        status: undefined
      },
      selectedNotice: {
        title: '',
        content: ''
      },
      showNoticeDialog: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        noticeTitle: [
          {required: true, message: "公告标题不能为空", trigger: "blur"}
        ],
        noticeType: [
          {required: true, message: "公告类型不能为空", trigger: "change"}
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.initVenueCourtChart(); // 初始化 echarts 柱状图
  },
  methods: {
    /** 查询公告列表 */
    getList() {
      this.loading = true;
      listNotice(this.queryParams).then(response => {
        this.noticeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 获取场馆场地数量映射的数据并更新到图表中
    fetchVenueCourtMapData() {
      getVenueCourtCountMap().then(response => {
        this.venueCourtMapData = response.data;
        this.updateVenueCourtChart(); // 获取到数据后更新图表
      });
    },
    // 初始化 echarts 柱状图
    initVenueCourtChart() {
      this.venueCourtChart = echarts.init(document.getElementById("venueCourtChart"));
      this.fetchVenueCourtMapData(); // 获取数据并更新图表
    },
    // 更新 echarts 柱状图
    updateVenueCourtChart() {
      // 使用获取到的数据更新图表
      const venueNames = Object.keys(this.venueCourtMapData);
      const courtCounts = Object.values(this.venueCourtMapData);
      const option = {
        // echarts 配置项
        xAxis: {
          type: "category",
          data: venueNames
        },
        yAxis: {
          type: "value"
        },
        series: [{
          data: courtCounts,
          type: "bar"
        }]
      };
      this.venueCourtChart.setOption(option);
    }
  }
};
</script>

<style scoped lang="scss">

#curtain {
  position: relative;
}

.background-title {
  background-color: #4158d0;
  background-image: linear-gradient(43deg, #4158d0 0%, #c850c0 46%, #ffcc70 100%);
}

.centered-title {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 60px;
  white-space: nowrap;
  font-weight: bold;
  font-family: 'Microsoft YaHei';
}

.centered-title::before {
  content: attr(data-text);
  position: absolute;
  transform: rotateX(180deg);
  transform-origin: bottom;
  line-height: 52px;
  background: linear-gradient(0deg, #000 0, transparent 80%);
  -webkit-background-clip: text;
  color: transparent;
  opacity: 0.5;
}

@keyframes slideInLeft {
  0% {
    transform: translateX(-100%);
  }
  100% {
    transform: translateX(100%);
  }
}

@keyframes slideInRight {
  0% {
    transform: translateX(100%);
  }
  100% {
    transform: translateX(-100%);
  }
}

</style>
