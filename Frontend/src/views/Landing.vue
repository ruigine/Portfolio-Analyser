<template>
  <div>
    <div class="relative h-fit bg-gs">
      <div class="px-8 sm:px-12 py-28 text-black-50">
        <p class="text-sm font-medium">INVESTMENT ADVISORS & FINANCIAL PROFESSIONALS</p>
        <p class="text-black text-5xl font-medium">
          Investment <br>
          Portfolio
        </p>
        <p class="text-sm font-normal">
          We provide a platform to analyse investment portfolios’ <br>
          performance and make informed investment decisions.
        </p>
      </div>
    </div>
    <div class="px-8 sm:px-12 py-11 my-11 text-white font-medium">
      <p class="mb-8 text-sm">FEATURES</p>
      <div class="grid grid-cols-1 gap-y-5 sm:grid-cols-2">
        <div class="mx-auto">
          <img src="@/assets/chart.png" alt="">
          <p class="mt-5">Interactive Charts and <br> Dashboard for the best risk <br> insights.</p>
        </div>
        <div class="mx-auto">
          <img src="@/assets/secure.png" alt="">
          <p class="mt-5">Secure Platform for your <br> portfolio management.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
  .bg-gs {
    background-image: url("@/assets/hero.png");
    background-color: #fff;
    background-repeat: no-repeat;
    background-size: cover;
  }
</style>