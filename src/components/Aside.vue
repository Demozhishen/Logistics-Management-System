<template>
  <div>
    <el-menu
        :default-active= "path"
        router
        class="el-menu-vertical-demo"
        style="min-height: calc(100vh - 50px)"
    >

      <div v-for="item in menu" :key="item.id">
        <div v-if="!item.path">
          <el-sub-menu :index="item.id+''">
            <template #title>

              <span>{{ item.name }}</span>
            </template>
            <el-menu-item-group >
              <div v-for="subitem in item.children">
              <el-menu-item :index="subitem.path">{{ subitem.name }}</el-menu-item>
              </div>
            </el-menu-item-group>
          </el-sub-menu>
        </div>

      </div>


<!--      <el-sub-menu index="2">
        <template #title>
          <el-icon><user/></el-icon>
          <span>员工管理</span>
        </template>
        <el-menu-item-group >
          <el-menu-item index="/staffInfo" >员工信息</el-menu-item>
          <el-menu-item index="/staff">员工管理</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>

      <el-sub-menu index="3">
        <template #title>
          <el-icon><setting /></el-icon>
          <span>货物管理</span>
        </template>
        <el-menu-item-group >
          <el-menu-item index="/item" >货物揽收</el-menu-item>
          <el-menu-item index="/order">货物运输</el-menu-item>
          <el-menu-item index="/sign">货物签收</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>-->
    </el-menu>
  </div>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
  User,
  UserFilled,
} from '@element-plus/icons-vue'
import {SortUp} from "@element-plus/icons";



export default {
  name: "Aside",
  components:{
    SortUp,
    UserFilled,
    Setting,
    IconMenu,
    Location,
    Document,
    User,


  },
  data(){
    return{
      path:this.$route.path,
      menu:localStorage.getItem("menu") ? JSON.parse(localStorage.getItem("menu")) : [],
    }
  }
}
</script>

<style scoped>

</style>