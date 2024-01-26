<template>
    <div class="exchange-rate-chart">
        <canvas ref="chart"></canvas>
    </div>
</template>

<script>
import { defineComponent, onMounted, ref } from 'vue';
import { Chart, registerables } from 'chart.js';
import axios from 'axios';

Chart.register(...registerables);

export default defineComponent({
    setup() {
        const chartRef = ref(null);

        const transformResponseData = (data) => {
            if (!data.conversion_rates) {
                throw new Error('No conversion rates data found');
            }

            return {
                labels: Object.keys(data.conversion_rates),
                datasets: [
                    {
                        label: `Exchange Rates for ${data.base_code}`,
                        backgroundColor: 'rgba(54, 162, 235, 0.5)',
                        borderColor: 'rgb(54, 162, 235)',
                        data: Object.values(data.conversion_rates),
                        fill: false,
                    }
                ]
            };
        };

        onMounted(async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/exchange-rates/latest');
                const chartData = transformResponseData(response.data);

                // Ensure the chartRef is not null
                if (chartRef.value) {
                    const ctx = chartRef.value.getContext('2d');
                    new Chart(ctx, {
                        type: 'line',
                        data: chartData,
                        options: {
                            responsive: true,
                            maintainAspectRatio: false,
                        },
                    });
                }
            } catch (error) {
                console.error('Error fetching exchange rate data:', error);
            }
        });

        return {
            chartRef,
        };
    },
});
</script>

<style scoped>
.exchange-rate-chart {
    width: 100%;
    max-width: 600px;
    margin: auto;
}
</style>
