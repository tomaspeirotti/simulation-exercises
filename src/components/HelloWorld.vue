<template>
  <div class="mx-2">
    <b-row>
      <b-col class="px-4" cols="12">
        <b-row class="mb-2">
          <b-col sm="12" class="text-center"><h4>Configurar imprenta</h4></b-col>
        </b-row>
        <div class="form-group" :class="{ 'form-group--error': $v.simulation.daysToSimulate.$error }">
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Cantidad de dias a simular:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="simulation.daysToSimulate" size="sm" type="number"></b-form-input>
            <div class="text-danger" v-if="!$v.simulation.daysToSimulate.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.simulation.daysToSimulate.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.simulation.daysToSimulate.maxValue">Debe ingresar un valor menor o igual a 5000</div>
            <div class="text-danger" v-if="!$v.simulation.daysToSimulate.minValue">Debe ingresar un valor mayor o igual a 1</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label>Utilidad adquirida por trabajos tipo 1:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="works[0].utility" size="sm" type="number"></b-form-input>
            <div class="text-danger" v-if="$v.simulation.works.utility.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.simulation.works.utility.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.simulation.works.utility.maxValue">Debe ingresar un valor menor o igual a 10000000000</div>
            <div class="text-danger" v-if="!$v.simulation.works.utility.minValue">Debe ingresar un valor mayor o igual a 0</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label>Utilidad adquirida por trabajos tipo 2:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="works[1].utility" size="sm" type="number"></b-form-input>
           <div class="text-danger" v-if="$v.simulation.works.utility.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.simulation.works.utility.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.simulation.works.utility.maxValue">Debe ingresar un valor menor o igual a 10000000000</div>
            <div class="text-danger" v-if="!$v.simulation.works.utility.minValue">Debe ingresar un valor mayor o igual a 0</div>
          </b-col>
        </b-row>
        <b-form-group label="¿Permitir pedidos tipo 2?">
          <b-form-radio-group v-model="simulation.enableType2Work" :options="enableType2" name="radioInline">
          </b-form-radio-group>
        </b-form-group>
        </div>
      </b-col>
      <b-col cols="3" class="text-left mb-3">
        <b-button class="btn btn-success" @click="simulate" :disabled="hasBeenSimulated">Simular  </b-button>
      </b-col>
    </b-row>
    <vue-good-table :pagination-options="{enabled: true, mode: 'pages'}" styleClass="vgt-table condensed" theme="nocturnal" :columns="columns" :rows="rows"/>
  </div>
</template>

<script>
import { required, minValue, maxValue, numeric, integer, decimal, maxLength } from 'vuelidate/lib/validators'

export default {
  name: 'my-component',
  data () {
    return {
      hasBeenSimulated: false,
      enableType2: [
        { text: 'Si', value: true },
        { text: 'No', value: false }
      ],
      simulation: {
        daysToSimulate: 700,
        enableType2Orders: true
      },
      currentDay: 1,
      columns: [
        {
          label: 'Día',
          field: 'day',
          type: 'number'
        },
        {
          label: 'RND',
          field: 'rndEvent',
          type: 'number'
        },
        {
          label: 'Evento',
          field: 'event',
          type: 'number'
        },
        {
          label: 'RND',
          field: 'rndDelay',
          type: 'number',
        },
        {
          label: 'Demora',
          field: 'delay',
          type: 'number',
        },
        {
          label: 'P1 - Estado',
          field: 'press1State',
          type: 'String',
        },
        {
          label: 'P1 - Dia fin',
          field: 'press1EndDay',
          type: 'number',
        },
        {
          label: 'P2 - Estado',
          field: 'press2State',
          type: 'String',
        },
        {
          label: 'P2 - Dia fin',
          field: 'press2EndDay',
          type: 'number',
        },
        {
          label: 'Utilidad',
          field: 'profit',
          type: 'number',
        },
        {
          label: 'Utilidad Ac.',
          field: 'acProfit',
          type: 'number',
        }
      ],
      rows: [],
      day: {
        number: 0,
        rndEvent: 0,
        event: '-',
        rndDelay: 0,
        delay: '-',
        profit: 0,
        acProfit: 0
      },
      works: [
        { type: 1, utility: 400},
        { type: 2, utility: 200}
      ],
      press1: {
        state: null,
        endDay: null
      },
      press2: {
        state: null,
        endDay: null
      }
    };
  },
  validations: {
    simulation: {
      daysToSimulate: {
        required,
        maxValue: maxValue(5000),
        minValue: minValue(1),
        numeric,
        integer
      },
      enableType2Works: {
        required,
      },
      works: {
        utility: {
          required,
          maxValue: maxValue(5000),
          minValue: minValue(1),
          numeric,
          integer
        }
      }
    }
  },
  methods: {
    simulate () {
      if (!this.simulation.enableType2Orders) {
        this.day.enableType2Orders = 'No'
      }
      this.$v.$touch()
      if (this.$v.$invalid) {
        alert('Uno de los campos no contiene los valores o valores correctos')
      } else {
      this.hasBeenSimulated = true;
      this.rows = []
      this.rows.push({ day: this.day.number, rndEvent: 0, event: '-', rndDelay: 0, delay: 0, press1State: 'L', press1EndDay: '-', press2State: 'L', press2EndDay: '-', profit: 0, acProfit: 0 })
      while (this.currentDay <= this.simulation.daysToSimulate) {
        let dayToPush = Object.assign({}, this.day)
        
      }
      // this.$emit('clicked', this.rows[this.rows.length - 1].totalCostAcum)
    }
    },
    getRandom () {
      let random = Math.random()
      return Math.round(random * 100) / 100
    },
    getDelay (r) {
      let delay = 0;
      (r < 0.25) ? delay = 2 : null;
      (r > 0.24 && r < 0.5) ? delay = 3 : null;
      (r > 0.49 && r < 0.7) ? delay = 4 : null;
      (r > 0.69) ? delay = 5 : null;
      return delay;
    },
    getEvent (r) {
      let event
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
