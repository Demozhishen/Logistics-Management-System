<template>
  <div class="personBody">
    <el-card style="width: 100%; margin: 10px">
      <el-form ref="form" :model="form" label-width="80px">


        <el-form :model="form" label-width="120px">
          <el-form-item label="员工编号">
            <el-input v-model="form.employeeId" disabled></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.employeePassword"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="form.employeeName" disabled></el-input>
          </el-form-item>
          <el-form-item label="身份证">
            <el-input v-model="form.employeeIdCard" disabled></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.employeePhone"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.employeeAddress"></el-input>
          </el-form-item>

        </el-form>
      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="update">保存</el-button>
      </div>
    </el-card>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Person",
  data() {
    return {
      form: {}
    }
  },
  created() {
    let str = localStorage.getItem("user") || "{}"
    this.form = JSON.parse(str)
  },
  methods: {

    update() {
      request.put("/staff", this.form).then(res => {
        console.log(res)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "更新成功"
          })
          localStorage.setItem("user", JSON.stringify(this.form))
          // 触发Layout更新用户信息
          this.$emit("userInfo")
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.personBody{





  line-height: 100%;
  padding-top: 50px;
}
</style>