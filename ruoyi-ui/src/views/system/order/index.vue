<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="节数" prop="timeId">
        <el-input
          v-model="queryParams.timeId"
          placeholder="请输入节数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="日期" prop="orderDate">
        <el-date-picker clearable
                        v-model="queryParams.orderDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="科目" prop="subjectName">
        <el-input
          v-model="queryParams.subjectName"
          placeholder="请输入科目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账号" prop="uId">
        <el-input
          v-model="queryParams.uId"
          placeholder="请输入账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="辅导室编号" prop="roomId">
        <el-input
          v-model="queryParams.roomId"
          placeholder="请输入辅导室编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="节数" align="center" prop="timeId" />
      <el-table-column label="日期" align="center" prop="orderDate" />
      <el-table-column label="科目" align="center" prop="subjectName" />
      <el-table-column label="账号" align="center" prop="uId" />
      <el-table-column label="课程" align="center" prop="course" />
      <el-table-column label="学生人数" align="center" prop="studentNum" />
      <el-table-column label="辅导室编号" align="center" prop="roomId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:order:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改辅导室预约信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="节数" prop="timeId">
          <el-input v-model="form.timeId" placeholder="请输入节数" />
        </el-form-item>

        <el-form-item label="日期" prop="orderDate">
          <el-date-picker clearable
                          v-model="form.orderDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          :picker-options="pickerOptions"
                          placeholder="请选择日期">
          </el-date-picker>
          <!--          <el-input v-model="form.orderDate" placeholder="请输入日期" />-->
        </el-form-item>




        <el-form-item label="科目" prop="subjectName">
          <el-input v-model="form.subjectName" placeholder="请输入科目" />
        </el-form-item>

<!--        <el-form-item label="账号" prop="uId">-->
<!--          <el-input  v-model="form.uId" placeholder="请输入账号" />-->
<!--        </el-form-item>-->

        <el-form-item label="课程" prop="course">
          <el-input v-model="form.course" placeholder="请输入课程" />
        </el-form-item>

<!--        <el-form-item label="辅导室" prop="roomId">-->
<!--          <el-select v-model="form.roomId" style="width: 120px">-->
<!--            <el-option-->
<!--              v-for="item in locationOptions"-->
<!--              :key="item.roomId"-->
<!--              :label="item.location"-->
<!--              :value="item.roomId">-->
<!--            </el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->


        <el-form-item label="辅导室" prop="roomId">
          <el-select v-model="form.roomId" placeholder="请选择辅导室" filterable clearable
                     :style="{width: '100%'}"  >
            <el-option v-for="item in dict.type.croom_location" :key="item.value" :label="item.label"
                       :value="item.value" ></el-option>
          </el-select>
        </el-form-item>



      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder,getlocationList } from "@/api/system/order";

export default {
  name: "Order",
  dicts:['croom_location'],
  data() {
    return {
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
      // 辅导室预约信息表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        timeId: null,
        orderDate: null,
        subjectName: null,
        uId: null,
        roomId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        location: [{
          required: true,
          message: '请选择辅导室',
          trigger: 'change'
        }],
      },
      locationOptions: [],
    pickerOptions: {
      disabledDate(time) {
        const times = new Date(new Date().toLocaleDateString()).getTime() + 4 * 8.64e7 - 1
        return time.getTime() < Date.now() || time.getTime() > times// 如果没有后面的-8.64e7就是不可以选择今天的
      }
    },
    };
  },

  created() {
    this.getList();
    this.getlocation();
  },
  methods: {
    /** 查询辅导室预约信息列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        timeId: null,
        orderDate: null,
        subjectName: null,
        uId: null,
        course: null,
        studentNum: null,
        roomId: null
      };
      this.resetForm("form");
    },

    //获得辅导室编号
    getlocation(){
      getlocationList().then(response => {
        //response      form.location.
        this.locationOptions = response.ccroom;
      });
    },
//     getitem1(val){
// //val即是传进来的id值，也即value值
//       var obj = {};
//       obj = this.chioce.find((item) =>{
//         return item.id ===val;
//       });
//       console.log(obj.typeName)
//       console.log("id的值为："+val)
//     },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.timeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加辅导室预约信息";
      this.form.uId=this.$store.state.user.name;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const timeId = row.timeId || this.ids
      getOrder(timeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改辅导室预约信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.timeId != null&& this.title == "修改辅导室预约信息") {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {

            addOrder(this.form).then(response => {

              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const timeIds = row.timeId || this.ids;
      this.$modal.confirm('是否确认删除辅导室预约信息编号为"' + timeIds + '"的数据项？').then(function() {
        return delOrder(timeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
