<template>
  <div class="mx-2">
    <b-row>
      <b-col class="px-4" cols="12">
        <b-row class="mb-2">
          <b-col sm="12" class="text-center"><h4>Configurar politica A</h4></b-col>
        </b-row>
        <div class="form-group" :class="{ 'form-group--error': $v.configPolicyA.daysToSimulate.$error }">
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Cantidad de dias a simular:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="configPolicyA.daysToSimulate" size="sm" type="number"></b-form-input>
            <div class="text-danger" v-if="!$v.configPolicyA.daysToSimulate.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.configPolicyA.daysToSimulate.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.configPolicyA.daysToSimulate.maxValue">Debe ingresar un valor menor o igual a 5000</div>
            <div class="text-danger" v-if="!$v.configPolicyA.daysToSimulate.minValue">Debe ingresar un valor mayor o igual a 1</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Stock inicial:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="week.stock" size="sm" type="number"></b-form-input>
            <div class="text-danger" v-if="!$v.week.stock.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.week.stock.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.week.stock.maxValue">Debe ingresar un valor menor o igual a 10000000000</div>
            <div class="text-danger" v-if="!$v.week.stock.minValue">Debe ingresar un valor mayor o igual a 0</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Cantidad Pedida:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="configPolicyA.amountToOrder" size="sm" type="number"></b-form-input>
            <div class="text-danger" v-if="!$v.configPolicyA.amountToOrder.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.configPolicyA.amountToOrder.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.configPolicyA.amountToOrder.maxValue">Debe ingresar un valor menor o igual a 100</div>
            <div class="text-danger" v-if="!$v.configPolicyA.amountToOrder.minValue">Debe ingresar un valor mayor o igual a 1</div>
          </b-col>
        </b-row>
        <b-row v-if="configPolicyA.orderFirstDay  " class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Cantidad Pedida Primer Dia:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="configPolicyA.amountOrderFirstDay" size="sm" type="number"></b-form-input>
            <div class="text-danger" v-if="!$v.configPolicyA.amountOrderFirstDay.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.configPolicyA.amountOrderFirstDay.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.configPolicyA.amountOrderFirstDay.maxValue">Debe ingresar un valor menor o igual a 100</div>
            <div class="text-danger" v-if="!$v.configPolicyA.amountOrderFirstDay.minValue">Debe ingresar un valor mayor o igual a 1</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">El pedido se realiza cada (dias):</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" size="sm" v-model.number="configPolicyA.daysToOrder" type="number"></b-form-input>
            <div class="text-danger" v-if="!$v.configPolicyA.daysToOrder.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.configPolicyA.daysToOrder.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.configPolicyA.daysToOrder.maxValue">Debe ingresar un valor menor o igual a 830 (2 años)</div>
            <div class="text-danger" v-if="!$v.configPolicyA.daysToOrder.minValue">Debe ingresar un valor mayor o igual a 1</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Costo Almacenamiento por Unidad:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="configPolicyA.km" size="sm"></b-form-input>
            <div class="text-danger" v-if="!$v.configPolicyA.km.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.configPolicyA.km.maxValue">Debe ingresar un valor menor o igual a 100</div>
            <div class="text-danger" v-if="!$v.configPolicyA.km.minValue">Debe ingresar un valor mayor o igual a 0.01</div>
            <div class="text-danger" v-if="!$v.configPolicyA.km.maxLength">Debe ingresar menos digitos</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Costo de stock-out por Unidad:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="configPolicyA.ks" size="sm"></b-form-input>
            <div class="text-danger" v-if="!$v.configPolicyA.ks.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.configPolicyA.ks.maxValue">Debe ingresar un valor menor o igual a 100</div>
            <div class="text-danger" v-if="!$v.configPolicyA.ks.minValue">Debe ingresar un valor mayor o igual a 0.01</div>
            <div class="text-danger" v-if="!$v.configPolicyA.ks.maxLength">Debe ingresar menos digitos</div>
          </b-col>
        </b-row>
        <b-form-group label="Realiza pedido el primer dia?">
          <b-form-radio-group v-model="configPolicyA.orderFirstDay" :options="optionsFirstDayA" name="radioInline">
          </b-form-radio-group>
        </b-form-group>
        </div>
      </b-col>
      <b-col cols="3" class="text-left mb-3">
        <b-button class="btn btn-success" @click="simulatePolicyA" :disabled="hasBeenSimulated">Simular A</b-button>
      </b-col>
    </b-row>
    <vue-good-table :pagination-options="{enabled: true, mode: 'pages'}" styleClass="vgt-table condensed" theme="black-rhino" :columns="columns" :rows="rows"/>
  </div>
</template>

<script>
import { required, minValue, maxValue, numeric, integer, decimal, maxLength } from 'vuelidate/lib/validators'

export default {
  name: 'my-component',
  data () {
    return {
      hasBeenSimulated: false,
      optionsFirstDayA: [
        { text: 'Si', value: true },
        { text: 'No', value: false }
      ],
      configPolicyA: {
        daysToSimulate: 300,
        initialStock: 20,
        amountToOrder: 15,
        daysToOrder: 10,
        km: 5,
        ks: 9,
        orderFirstDay: true,
        amountOrderFirstDay: 15
      },
      demands: [
        {amount: 0, interval: 4},
        {amount: 1, interval: 16},
        {amount: 2, interval: 34},
        {amount: 3, interval: 59},
        {amount: 4, interval: 81},
        {amount: 5, interval: 100},
      ],
      delays: [
        {daysAmount: 1, interval: 14},
        {daysAmount: 2, interval: 34},
        {daysAmount: 3, interval: 74},
        {daysAmount: 4, interval: 100},
      ],

      KOCost: [
        {amount: 16, cost: 20},
        {amount: 41, cost: 30},
        {amount: Infinity, cost: 40}
      ],
      currentWeek: 1,
      columns: [
        {
          label: 'i',
          field: 'iteration',
          type: 'number'
        },
        {
          label: 'RND',
          field: 'rndDemand',
          type: 'number'
        },
        {
          label: 'Demanda (decenas)',
          field: 'demand',
          type: 'number'
        },
        {
          label: 'Stock',
          field: 'stock',
          type: 'number',
        },
        {
          label: 'Pedido',
          field: 'order',
          type: 'boolean',
        },
        // {
        //   label: 'Pedido 1er dia',
        //   field: 'orderFirstDay',
        //   type: 'boolean',
        // },
        {
          label: 'RND',
          field: 'rndArrive',
          type: 'number',
        },
        {
          label: 'Demora',
          field: 'delayArrive',
          type: 'number',
        },
        {
          label: 'Llegada Pedido',
          field: 'arrive',
          type: 'number',
        },
        {
          label: 'KO',
          field: 'ko',
          type: 'number',
        },
        {
          label: 'KM',
          field: 'km',
          type: 'number',
        },
        {
          label: 'KS',
          field: 'ks',
          type: 'number',
        },
        {
          label: 'CT',
          field: 'totalCost',
          type: 'number',
        },
        {
          label: 'CT Acum',
          field: 'totalCostAcum',
          type: 'number',
        }
      ],
      missingAmount: 0,
      rows: [],
      week: {
        iteration: 0,
        rndDemand: null, 
        demand: '-',
        stock: 20,
        order: 'Si',
        orderFirstDay: 'Si',
        rndArrive: 0, 
        delayArrive: '-',
        arrive: '-',
        ko: 0,
        km: 0,
        ks: 0,
        totalCost: 0,
        totalCostAcum: 0
      }
    };
  },
  validations: {
    configPolicyA: {
      daysToSimulate: {
        required,
        maxValue: maxValue(5000),
        minValue: minValue(1),
        numeric,
        integer
      },
      amountToOrder: {
        required,
        maxValue: maxValue(100),
        minValue: minValue(1),
        numeric,
        integer
      },
      amountOrderFirstDay: {
        required,
        maxValue: maxValue(100),
        minValue: minValue(1),
        numeric,
        integer
      },
      daysToOrder: {
        required,
        maxValue: maxValue(830),
        minValue: minValue(1),
        numeric,
        integer
      },
      km: {
        required,
        decimal,
        maxLength: maxLength(4),
        maxValue: maxValue(100),
        minValue: minValue(0.01)
      },
      ks: {
        required,
        decimal,
        maxLength: maxLength(4),
        maxValue: maxValue(100),
        minValue: minValue(0.01)
      },
      orderFirstDay: {
        required,
      }
    },
    week: {
      stock: {
        required,
        numeric,
        maxValue: maxValue(10000000000),
        minValue: minValue(0),
        integer
      }
    }
  },
  methods: {
    simulatePolicyA () {
      if (!this.configPolicyA.orderFirstDay) {
        this.week.orderFirstDay = 'No'
      }
      this.$v.$touch()
      if (this.$v.$invalid) {
        alert('Uno de los campos no contiene los valores o valores correctos')
      } else {
      this.hasBeenSimulated = true
      this.rows = []
      this.currentWeek = 1
      this.rows.push({ iteration: 0, rndDemand:'-', demand: '-', stock: this.week.stock, order: '-', rndArrive: 0, delayArrive: '-', arrive: '-', ko: 0, km: 0, ks: 0, totalCost: 0, totalCostAcum: 0})
      while (this.currentWeek <= this.configPolicyA.daysToSimulate) {
        let weekToPush = Object.assign({}, this.week)
        if (weekToPush.arrive === this.currentWeek) {
          if (weekToPush.orderFirstDay === 'No'){
          weekToPush.stock += this.configPolicyA.amountToOrder
          weekToPush.arrive = '-'
          } else {
            weekToPush.orderFirstDay = "No"
            weekToPush.stock += this.configPolicyA.amountOrderFirstDay
            weekToPush.arrive = '-'
          }
        }
        weekToPush.iteration = this.currentWeek
        weekToPush.rndDemand = this.generateRandomDemand()
        weekToPush.demand = this.getDemand(weekToPush.rndDemand)
        weekToPush.stock = this.sale(weekToPush.stock, weekToPush.demand)
        if(((weekToPush.iteration % this.configPolicyA.daysToOrder) == 0) || (this.currentWeek === 1 && this.configPolicyA.orderFirstDay)) {
          weekToPush.order = 'Si'
        } else {
          weekToPush.order = 'No'
          weekToPush.rndArrive = '-'
          weekToPush.delayArrive = '-'
          weekToPush.ko = 0
        }
        // if (weekToPush.order === 'Si' || (this.currentWeek === 1 && this.configPolicyA.orderFirstDay)) {
        if (weekToPush.order === 'Si') {
          // weekToPush.order = 'Si'
          weekToPush.rndArrive = this.generateRandomArrive()
          weekToPush.delayArrive = this.getDelayArrive(weekToPush.rndArrive)
          weekToPush.arrive = this.currentWeek + weekToPush.delayArrive
          weekToPush.ko = 20
        }
        weekToPush.km = weekToPush.stock * this.configPolicyA.km
        weekToPush.ks = this.missingAmount * this.configPolicyA.ks * -1
        weekToPush.totalCost = weekToPush.ko + weekToPush.km + weekToPush.ks
        weekToPush.totalCostAcum += weekToPush.totalCost
        this.rows.push(weekToPush)
        this.missingAmount = 0
        this.week = weekToPush
        this.currentWeek += 1
      }
      this.$emit('clicked', this.rows[this.rows.length - 1].totalCostAcum)
    }
    },
    generateRandomDemand () {
      let random = Math.random()
      return Math.round(random * 100) / 100
    },
    getDemand (random) {
      let selectedDemand = this.demands.find(demand => demand.interval / 100 >= random)
      return selectedDemand.amount
    },
    sale (stock, demand) {
      if (stock - demand >= 0) {
        return stock - demand
      } else {
        this.missingAmount = stock - demand
        return 0
      }
    },
    generateRandomArrive () {
      let random = Math.random()
      return Math.round(random * 100) / 100
    },
    getDelayArrive (random) {
      let selectedDelay = this.delays.find(delay => delay.interval / 100 >= random)
      return selectedDelay.daysAmount
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
