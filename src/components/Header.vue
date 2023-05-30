<template>
  <div style="height: 50px;line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 200px; padding-left: 30px;font-weight: bold;color: black;font-size: 27px">物流管理系统</div>
    <div style="flex: 1"></div>
    <div style="width: 100px; ">
      <el-dropdown style="top: 15px">
    <span class="el-dropdown-link" style="font-size: 12px;color: crimson">
        {{ name }}
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>

import {ArrowDown} from "@element-plus/icons";
export default {
  name: "Header",

  data(){
    return{
      name:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")).username:null,

    }
  },

  components:{
    ArrowDown
  }
}
</script>

<style scoped>

</style>