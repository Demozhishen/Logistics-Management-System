<template>
  <div style="padding: 10px;width: 100%" >




    <div style="margin: 10px">
      <el-input v-model="search" placeholder="请输入物品名称" style="width:200px" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>


    <!--    功能区-->


    <div style="margin: 10px">


      <el-button type="primary" @click="exp" v-if="role">导出 <i class="el-icon-top"></i></el-button>

    </div>

    <!--    搜索区-->



    <el-table :data="tableData" border stripe  style="width: 100%" >
      <el-table-column prop="id" label="订单编号" sortable width="110" />
      <el-table-column label="订单日期" width="180">
        <template #default="scope">
          <el-popover effect="light" trigger="hover" placement="top" width="auto">
            <template #default>
              <div>提交日期: {{ scope.row.date }}</div>
              <div>订单日期: {{ scope.row.orderDate }}</div>
            </template>
            <template #reference>
              <el-tag type="info">{{ scope.row.orderDate }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="物品名称" />
      <el-table-column prop="quantity" label="物品数量" width="100"  />
      <el-table-column prop="weight" label="物品重量" width="100"/>
      <el-table-column label="收件人信息" width="180">
        <template #default="scope">
          <el-popover effect="light" trigger="hover" placement="top" width="auto">
            <template #default>
              <div>收件人: {{ scope.row.recipient }}</div>
              <div>收件人电话: {{ scope.row.recipientPhone }}</div>
              <div>收件人地址: {{ scope.row.recipientAddress }}</div>
            </template>
            <template #reference>
              <el-tag type="success">{{ scope.row.recipient }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="寄件人信息" width="180">
        <template #default="scope">
          <el-popover effect="light" trigger="hover" placement="top" width="auto">
            <template #default>
              <div>寄件人: {{ scope.row.senderName }}</div>
              <div>寄件人电话: {{ scope.row.senderPhone }}</div>
            </template>
            <template #reference>
              <el-tag type="info">{{ scope.row.senderName }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="物流状态" />
      <el-table-column label="揽收人信息" width="180">
        <template #default="scope">
          <el-popover effect="light" trigger="hover" placement="top" width="auto">
            <template #default>
              <div>揽收人: {{ scope.row.courierName }}</div>
              <div>揽收人电话: {{ scope.row.courierPhone }}</div>
            </template>
            <template #reference>
              <el-tag type="warning">{{ scope.row.courierName }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="Operations" >
        <template #default="scope">
          <el-popconfirm title="是否签收？" @confirm="handleEdit(scope.row)">
            <template #reference>
              <el-button type="success" v-if="!role">签收</el-button>
            </template>
          </el-popconfirm>

          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" v-if="role">取消 </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px">
      <el-pagination
          v-model:current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </div>


  </div>
</template>

<script>





import request from "@/utils/request";

export default {
  name: 'order',
  components: {

  },
  data(){
    return {
      form:{},
      dialogVisible:false,
      total:10,
      currentPage:1,
      pageSize:10,
      search:'',
      tableData:[],
      test:{},
      role:false,
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.form = JSON.parse( localStorage.getItem("user"));
      request.post("/order/get", this.form,{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
      }).then(res=>{

        this.role=!(JSON.parse(localStorage.getItem("user")).role === 1)
        this.tableData=res.data.records
        this.total=res.data.total
      })
    },



  exp(){
      window.open("http://localhost:8081/order/export")
  },
    handleEdit(row){
      this.form=row
      console.log(this.form)
      request.post("/manage/sign",this.form).then(res=>{
        if(res.code==='0'){
          this.$message({
            type:"success",
            message:"签收成功"
          })
          this.load();
        }
        else {
          this.$message({
            type:"error",
            message:"签收失败"
          })
        }
      })
    },

    handleSizeChange(pageSize){
      this.pageSize=pageSize;
      this.load()
    },
    handleCurrentChange(pageNum){
      this.currentPage=pageNum;
      this.load()
    },
    handleDelete(id){
      console.log(id)
      request.delete("/order/"+id).then(res=>{
        if(res.code==='0')
        {
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }
        else {
          this.$message({
            type:"error",
            message: res.msg
          })
        }
        this.load()
      })
    },
  }
}
</script>


<style>

</style>
