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
      <el-button type="primary" @click="add" v-if="!role">新增</el-button>

      <el-button type="primary" @click="exp" v-if="role">导出 <i class="el-icon-top"></i></el-button>

    </div>

    <!--    搜索区-->



    <el-table :data="tableData" border stripe  style="width: 100%" >
      <el-table-column prop="id" label="物品编号" sortable width="110" />
      <el-table-column prop="name" label="物品名称" />
      <el-table-column prop="quantity" label="物品数量"  width="100" />
      <el-table-column prop="weight" label="物品重量" width="100" />
      <el-table-column prop="date" label="提交时间" />
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
      <el-table-column prop="status" label="物流状态" />
      <el-table-column fixed="right" label="Operations" >

        <template #default="scope">
          <el-popconfirm title="是否揽收？" @confirm="handleEdit(scope.row)">
            <template #reference>
              <el-button type="success" v-if="role">揽收</el-button>
            </template>
          </el-popconfirm>

          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" v-if="!role">删除 </el-button>
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

    <el-dialog
        v-model="dialogVisible"
        title="新增货物"
        width="30%"
    >
      <el-form :model="form" label-width="120px">

        <el-form-item label="物品名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="物品数量">
          <el-input v-model="form.quantity"></el-input>
        </el-form-item>
        <el-form-item label="物品重量">
          <el-input v-model="form.weight"></el-input>
        </el-form-item>
        <el-form-item label="收件人">
          <el-input v-model="form.recipient"></el-input>
        </el-form-item>
        <el-form-item label="收件人电话">
          <el-input v-model="form.recipientPhone"></el-input>
        </el-form-item>
        <el-form-item label="收件人地址">
          <el-input v-model="form.recipientAddress"></el-input>
        </el-form-item>




      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          添加
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>





import request from "@/utils/request";

export default {
  name: 'item',
  components: {

  },
  data(){
    return {
      form:{},
      dialogVisible:false,
      total:10,
      currentPage:1,
      pageSize:5,
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
      console.log(this.form)
      request.post("/item/get", this.form,{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search,
        },

      }).then(res=>{
        console.log(localStorage.getItem("user"))
        this.role=!(JSON.parse(localStorage.getItem("user")).role === 1)
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

        this.form.sender = JSON.parse( localStorage.getItem("user")).userId;
        console.log(this.form.sender)

        request.post("/item", this.form).then(res => {
          console.log(res)
          this.$message({
            type:"success",
            message:"新增成功"
          })
          this.load();
        })


      this.dialogVisible=false
    },
  exp(){
      window.open("http://localhost:8081/item/export")
  },
    handleEdit(row){
      this.form=row
      this.form.employeeId=JSON.parse(localStorage.getItem("user")).employeeId
      console.log(this.form)
      request.post("/manage/collect",this.form).then(res=>{
        if(res.code==='0'){
          this.$message({
            type:"success",
            message:"揽收成功"
          })
          this.load();
        }
        else {
          this.$message({
            type:"error",
            message:"揽收失败"
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
      request.delete("/item/"+id).then(res=>{
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
