<template>
    <div class="home">
      <h1>Welcome to the Exchange Rate Dashboard</h1>
      <ExchangeRateChart/>
    </div>
  </template>
  
  <script>
  import ExchangeRateChart from './ExchangeRateChart.vue'
  
  export default {
    name: 'HomePage',
    components: {
      ExchangeRateChart
    }
  }
  </script>
  