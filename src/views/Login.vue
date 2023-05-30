<template>
  <div class="loginbody">
    <div class="logindata">
      <div class="logintext">
        <h2>物流管理系统</h2>
      </div>
      <div class="formdata">
        <el-form ref="form" :model="form" :rules="rules1">
          <el-form-item prop="username">
            <el-input
                v-model="form.username"
                clearable
                placeholder="请输入账号"
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
                v-model="form.password"
                clearable
                placeholder="请输入密码"
                show-password
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="tool">
        <div>
          <el-checkbox v-model="checked" @change="remenber"
          >记住密码</el-checkbox
          >
        </div>

      </div>
      <div class="butt">
        <el-button type="primary" @click.native.prevent="login('form')"
        >登录</el-button
        >
        <el-button class="shou" @click="register">注册</el-button>
      </div>
    </div>
  </div>

<div class="dialog">
  <el-dialog
      v-model="dialogVisible"
      title="注册成为新用户"
      width="30%"
  >
    <el-form ref="form" :model="form" label-width="120px" :rules="rules">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码"  prop="password" >
        <el-input v-model="form.password" show-password></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="fullName">
        <el-input v-model="form.fullName"></el-input>
      </el-form-item>

      <el-form-item label="性别" >
        <el-radio v-model="form.sex" label="男">男</el-radio>
        <el-radio v-model="form.sex" label="女">女</el-radio>
        <el-radio v-model="form.sex" label="未知">未知</el-radio>
      </el-form-item>
      <el-form-item label="电话" prop="phoneNumber">
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
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          注册
        </el-button>
      </span>
    </template>
  </el-dialog>
</div>
</template>

<script>


import request from "@/utils/request";
import {setRoutes} from "@/router";

export default {
  name: "login",
  data() {
    return {
      dialogVisible:false,
      form: {
        password: "",
        username: "",
      },
      checked: false,
      rules1:{
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { max: 11, message: "不能大于11个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
        phoneNumber: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { validator: this.validateNumber, trigger: 'blur' }
        ],
        fullName: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 10, message: '姓名长度不可超过10或低于1', trigger: 'blur' },
        ],
      },
    };
  },
  mounted() {
    /*if(localStorage.getItem("news")){
      this.form=JSON.parse(localStorage.getItem("news"))
      this.checked=true
    }*/
  },
  methods: {
    validateNumber(rule, value, callback) {
      if (value === '') {
        callback(new Error('请输入数字'));
      } else if (!/^\d+$/.test(value)) {
        callback(new Error('请输入有效的数字'));
      } else {
        callback();
      }
    },
    login() {
      this.$refs["form"].validate((valid)=>{
        if(valid){
          request.post("/login",this.form).then(res=>{
            if(res.code==='0')
            {
              this.$message({
                type:"success",
                message:"登录成功"
              })
              localStorage.setItem("user",JSON.stringify(res.data))
              localStorage.setItem("menu",JSON.stringify(res.data.menu))
              setRoutes()
              this.$router.push('/')
            }
            else {
              this.$message({
                type:"error",
                message: res.msg
              })}
          })
        }
        else
        {
          alert("用户名或者密码不合法")
        }
      })

    },
    remenber(data){
      this.checked=data
      if(this.checked){
        localStorage.setItem("news",JSON.stringify(this.form))
      }else{
        localStorage.removeItem("news")
      }
    },

    register() {
      this.dialogVisible=true
    },
    save(){
      this.$refs["form"].validate((valid)=>{
        if(valid){

          request.post("/user", this.form).then(res => {
            if(res.code==='0')
            {
              this.$message({
                type:"success",
                message:"注册成功"
              })
              this.dialogVisible=false
            }
            else {
              this.$message({
                type:"error",
                message: res.msg
              })}
          })
        }
        else
        {
          alert("表单不合法")
        }
      })

    }
  },
};
</script>

<style scoped>
.loginbody {
  width: 100%;
  height: 100%;
  min-width: 1000px;
  background-image: url("../assets/4.jpg");
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  background-repeat: no-repeat;
  position: fixed;
  line-height: 100%;
  padding-top: 150px;
}

.logintext {
  margin-bottom: 20px;
  line-height: 50px;
  text-align: center;
  font-size: 30px;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 4px #000000;
}

.logindata {
  width: 400px;
  height: 300px;
  transform: translate(-50%);
  margin-left: 50%;
}

.tool {
  display: flex;
  justify-content: space-between;
  color: #606266;
}

.butt {
  margin-top: 10px;
  text-align: center;
}

.shou {
  cursor: pointer;
  color: #606266;
}
.dialog{
  background-image: url("../assets/4.jpg");
  background-size: 100% 100%;
}

/*ui*/
/* /deep/ .el-form-item__label {
  font-weight: bolder;
  font-size: 15px;
  text-align: left;
}

/deep/ .el-button {
  width: 100%;
  margin-bottom: 10px;

} */
</style>

