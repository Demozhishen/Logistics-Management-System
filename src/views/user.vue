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
      <el-input v-model="search" placeholder="请输入姓名" style="width:200px" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>


    <!--    功能区-->


    <div style="margin: 10px">
      <el-button type="primary" @click="add">新增<i class="el-icon-plus"></i></el-button>
<!--      <el-button type="danger">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>-->
      <el-button type="primary" @click="exp">导出 <i class="el-icon-top"></i></el-button>

    </div>

<!--    搜索区-->



    <el-table :data="tableData" border stripe  style="width: 100%" >
      <el-table-column prop="userId" label="用户编号" sortable width="110" />
      <el-table-column prop="username" label="用户名"  />
      <el-table-column prop="password" label="密码" />
      <el-table-column prop="fullName" label="姓名"   />
      <el-table-column prop="sex" label="性别" width="100"/>
      <el-table-column prop="phoneNumber" label="电话" />
      <el-table-column prop="address" label="地址" />
      <el-table-column prop="email" label="邮箱" />
      <el-table-column fixed="right" label="Operations" >
        <template #default="scope">
        <el-button type="success" @click="handleEdit(scope.row)" >编辑</el-button>
          <el-popconfirm title="确认删除？" @confirm="handleDelete(scope.row.userId)">
            <template #reference>
              <el-button type="danger">删除 </el-button>
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
        title="新增用户"
        width="30%"
    >
      <el-form :model="form" label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.fullName"></el-input>
        </el-form-item>

        <el-form-item label="性别">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
          <el-radio v-model="form.sex" label="未知">未知</el-radio>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">
          Confirm
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>




import request from "@/utils/request";

export default {
  name: 'HomeView',
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
      tableData:[]
    }
    },
  created() {
    this.load()
  },
  methods:{
    load(){
       request.get("http://localhost:8081/user", {
         params:{
           pageNum:this.currentPage,
           pageSize:this.pageSize,
           search:this.search
         }
       }).then(res=>{
         if(res.code==="401")
         {
           //this.$router.push('/404')
         }
         console.log(res)
         console.log(this.search)
         this.tableData=res.data.records
         this.total=res.data.total
       })
    },
    exp(){
      window.open("http://localhost:8081/user/export")
    },

    add(){
      this.dialogVisible=true
      this.form={}
    },
    save(){
      console.log(this.form.id)
      if(this.form.userId)
      {
        request.put("http://localhost:8081/user",this.form).then(res=>{

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
          request.post("http://localhost:8081/user", this.form).then(res => {
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

    handleEdit(row){
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
      console.log(this.form.userId)
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
      request.delete("http://localhost:8081/user/"+id).then(res=>{
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
.headerBg {
  background: #eee!important;
}
</style>
