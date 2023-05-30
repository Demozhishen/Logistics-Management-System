<template>
  <div style="padding: 10px;width: 100%" >


    <!--    搜索区-->
    <!--    <div style="margin: 10px 0">
          <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search"></el-input>
          <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" class="ml-5"></el-input>
          <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5"></el-input>
          <el-button class="ml-5" type="primary">搜索</el-button>
        </div>-->

    <div style="margin: 10px">
      <el-input v-model="search" placeholder="请输入物品名称" style="width:200px" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>


    <!--    功能区-->


    <div style="margin: 10px">


      <el-button type="primary" @click="exp">导出 <i class="el-icon-top"></i></el-button>

    </div>

    <!--    搜索区-->



    <el-table :data="tableData" border stripe  style="width: 100%" >
      <el-table-column prop="id" label="订单编号" sortable width="150" />
      <el-table-column label="货物信息" width="180">
        <template #default="scope">
          <el-popover effect="light" trigger="hover" placement="top" width="auto">
            <template #default>
              <div>物品名称: {{ scope.row.name }}</div>
              <div>物品数量: {{ scope.row.quantity }}</div>
              <div>物品重量: {{ scope.row.weight }}</div>
              <div>揽收时间: {{ scope.row.date }}</div>
              <div>运输时间: {{ scope.row.orderDate }}</div>
              <div>签收时间: {{ scope.row.signTime }}</div>
            </template>
            <template #reference>
              <el-tag type="">{{ scope.row.name }}</el-tag>
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
      <el-table-column label="派件人信息" width="180">
        <template #default="scope">
          <el-popover effect="light" trigger="hover" placement="top" width="auto">
            <template #default>
              <div>派件人: {{ scope.row.courierName }}</div>
              <div>派件人电话: {{ scope.row.courierPhone }}</div>
              <div>派件人工号: {{ scope.row.dispatchNumber }}</div>
            </template>
            <template #reference>
              <el-tag type="warning">{{ scope.row.courierName }}</el-tag>
            </template>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="物流状态" />
<!--      <el-table-column fixed="right" label="Operations" >
        <template #default="scope">
          <el-button type="success" @click="handleEdit(scope.row)" >揽收</el-button>
          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger">删除 </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>-->
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
  name: 'sign',
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
      test:{}
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.form = JSON.parse( localStorage.getItem("user"));
      request.post("/sign/get", this.form,{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
      }).then(res=>{

        this.tableData=res.data.records
        this.total=res.data.total
      })
    },

    add(){
      this.dialogVisible=true
      this.form={}
    },
    save(){
      console.log(this.form.id)
      if(this.form.employeeId)
      {
        request.put("/sign",this.form).then(res=>{

          if(res.code==='0')
          {
            this.$message({
              type:"success",
              message:"修改成功"
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
      }else
      {


        request.post("/sign", this.form).then(res => {
          console.log(res)
          this.$message({
            type:"success",
            message:"新增成功"
          })
          this.load();
        })
      }

      this.dialogVisible=false
    },
  exp(){
      window.open("http://localhost:8081/sign/export")
  },
    handleEdit(row){
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
      console.log(this.form.employeeId)
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
      request.delete("/sign/"+id).then(res=>{
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
