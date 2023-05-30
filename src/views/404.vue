<template>
<div class="_404" >
<img src="../assets/img.png" alt="页面未找到" style="position: center">
</div>
</template>
<script>
export default {
  name: "NF404",
  data() {
    return {
      countDown: 5
    }
  },
  methods: {
    goHome() {
      this.$router.push('/login')
    }
  },
  created() {
 /*   setInterval(() => {
      this.countDown > 0 ? this.countDown-- : this.$router.push('/login')
    }, 1000)*/
  }
}
</script>

<style>
body {

  background-position: center center;
}

._404 {
  width: 30%;
  margin: 5rem auto;
}

._404 img {
  width: 30rem;
}

._404 a {
  color: #010101;
}

._404 a:hover {
  color: skyblue;
}
</style>
