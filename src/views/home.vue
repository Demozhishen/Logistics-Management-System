<template xmlns="http://www.w3.org/1999/html">
  <div style="font-family: 'Arial', sans-serif; font-size: 15px; color: black; width: 100%"  >
   <br> <br>
    Welcome to the Logistics Management System
    <el-divider></el-divider>
    功能介绍
    <el-divider></el-divider>
    <div style="color: cadetblue">普通用户:可以进行货物提交或取消，以及货物签收，可以进行个人信息修改，账号自行注册，只能查看自己添加的货物的信息</div>
    <el-divider></el-divider>
    <div style="color: burlywood"> 员工：可以进行货物揽收，个人信息修改，账号必须由管理员添加，无法注册，可以查看所有待揽收的货物，只能查看自己揽收的订单</div>
    <el-divider></el-divider>
    <div style="color: red"> 管理员：可以管理普通用户和员工的一切信息，可以查看所有货物派送信息</div>
    <el-divider></el-divider>
    <div style="color: blue">超级管理员账号为 88888888888 & 123</div>

  </div>

</template>

<script>
import {setRoutes} from "@/router";

export default {

  created() {
    setRoutes();

  }
}
</script>

<style scoped>

</style>