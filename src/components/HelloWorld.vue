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
            <div class="text-danger" v-if="!$v.simulation.daysToSimulate.maxValue">Debe ingresar un valor menor o igual a 100000</div>
            <div class="text-danger" v-if="!$v.simulation.daysToSimulate.minValue">Debe ingresar un valor mayor o igual a 1</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label>Utilidad adquirida por trabajos tipo 1:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="workType1.utility" size="sm" type="number"></b-form-input>
            <div class="text-danger" v-if="!$v.workType1.utility.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.workType1.utility.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.workType1.utility.maxValue">Debe ingresar un valor menor o igual a 100000</div>
            <div class="text-danger" v-if="!$v.workType1.utility.minValue">Debe ingresar un valor mayor o igual a 0</div>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label>Utilidad adquirida por trabajos tipo 2:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model.number="workType2.utility" size="sm" type="number"></b-form-input>
            <div class="text-danger" v-if="!$v.workType2.utility.required">Debe ingresar un valor</div>
            <div class="text-danger" v-if="!$v.workType2.utility.integer">Debe ingresar un entero</div>
            <div class="text-danger" v-if="!$v.workType2.utility.maxValue">Debe ingresar un valor menor o igual a 100000</div>
            <div class="text-danger" v-if="!$v.workType2.utility.minValue">Debe ingresar un valor mayor o igual a 0</div>
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
        enableType2Work: true
      },
      columns: [
        {
          label: 'Día',
          field: 'day',
          type: 'number'
        },
        {
          label: 'RND',
          field: 'rndEvent',
          type: 'decimal',
          sortable: false
        },
        {
          label: 'Evento',
          field: 'event',
          type: 'text',
          sortable: false
        },
        {
          label: 'RND',
          field: 'rndDelay',
          type: 'text',
          sortable: false
        },
        {
          label: 'Demora',
          field: 'delay',
          type: 'number',
          sortable: false
        },
        {
          label: 'P1 - Estado',
          field: 'press1State',
          type: 'text',
          sortable: false
        },
        {
          label: 'P1 - Dia fin',
          field: 'press1EndDay',
          type: 'number',
          sortable: false
        },
        {
          label: 'P2 - Estado',
          field: 'press2State',
          type: 'text',
          sortable: false
        },
        {
          label: 'P2 - Dia fin',
          field: 'press2EndDay',
          type: 'number',
          sortable: false
        },
        {
          label: 'Utilidad',
          field: 'profit',
          type: 'number',
          sortable: false
        },
        {
          label: 'Utilidad Ac.',
          field: 'acProfit',
          type: 'number',
          sortable: false
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
        acProfit: 0,
        press1: {
          state: 'L',
          endDay: '-'
        },
        press2: {
          state: 'L',
          endDay: '-'
        }
      },
      workType1: {
        utility: 400,
        name: 'Trabajo tipo 1',
        workFinishedDesc: 'Fin trabajo tipo 1'
      },
      workType2: {
        utility: 200,
        name: 'Trabajo tipo 2',
        workFinishedDesc: 'Fin trabajo tipo 2'
      },
      noWorkArrived: {
        utility: 0,
        name: 'Sin trabajo'
      }
    };
  },
  validations: {
    simulation: {
      daysToSimulate: {
        required,
        maxValue: maxValue(100000),
        minValue: minValue(1),
        numeric,
        integer
      },
      enableType2Work: {
        required,
      }
    },
    workType1: {
      utility: {
      required,
      maxValue: maxValue(100000),
      minValue: minValue(1),
      numeric,
      integer
      }
    },
    workType2: {
      utility: {
      required,
      maxValue: maxValue(100000),
      minValue: minValue(1),
      numeric,
      integer
      }
    }
  },
  methods: {
    simulate () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        alert('Uno de los campos no contiene valores correctos')
      } else {
        this.hasBeenSimulated = true;
        this.rows = []
        this.rows.push({ day: this.day.number, rndEvent: 0, event: '-', rndDelay: 0, delay: 0, press1State: 'L', press1EndDay: '-', press2State: 'L', press2EndDay: '-', profit: 0, acProfit: 0 })

        for (let i = 1; i <= this.simulation.daysToSimulate; i++) {
          let dayToPush = Object.assign({}, this.day)
          let previousDay = this.getPreviousDay(this.rows[i-1]);
          dayToPush.press1 = previousDay.press1;
          dayToPush.press2 = previousDay.press2;
          dayToPush.number = i;
          dayToPush.rndEvent = this.getRandom();
          let event = this.getIncomingEvent(dayToPush.rndEvent, previousDay, dayToPush.number);
          dayToPush.event = event;

          if (event === this.workType1.name || event === this.workType2.name) {
            let delay;
            if (previousDay.press1.state === 'L' || previousDay.press2.state === 'L') {
              dayToPush.rndDelay = this.getRandom()
              delay = this.getDelay(dayToPush.rndDelay)
              dayToPush.delay = delay;
            }
            
            if (previousDay.press1.state === 'L') {
              dayToPush.press1.state = 'O'
              dayToPush.press1.endDay = i + delay;
              dayToPush.press2.state = previousDay.press2.state;
              dayToPush.press2.endDay = previousDay.press2.endDay;
            } else if (previousDay.press2.state === 'L') {
              dayToPush.press2.state = 'O';
              dayToPush.press2.endDay = i + delay;
              dayToPush.press1.state = previousDay.press1.state;
              dayToPush.press1.endDay = previousDay.press1.endDay;
            } else if (previousDay.press1.state === 'O' && previousDay.press2.state === 'O') {
              dayToPush.press1.state = previousDay.press1.state;
              dayToPush.press1.endDay = previousDay.press1.endDay;
              dayToPush.press2.state = previousDay.press2.state;
              dayToPush.press2.endDay = previousDay.press2.endDay;
            }

            dayToPush.profit = 0;
            dayToPush.acProfit = previousDay.acProfit;

          } else if (event === this.workType1.workFinishedDesc) {
            dayToPush.rndDelay = '-';
            dayToPush.delay = '-';
            dayToPush.press1.state = 'L';
            dayToPush.press1.endDay = '-';
            dayToPush.press2.state = previousDay.press2.state;
            dayToPush.press2.endDay = previousDay.press2.endDay;
            dayToPush.profit = this.workType1.utility;
            dayToPush.acProfit = dayToPush.profit + previousDay.acProfit;
          } else if (event === this.workType2.workFinishedDesc) {
            dayToPush.rndDelay = '-';
            dayToPush.delay = '-';
            dayToPush.press2.state = 'L';
            dayToPush.press2.endDay = '-';
            dayToPush.press1.state = previousDay.press1.state;
            dayToPush.press1.endDay = previousDay.press1.endDay;
            dayToPush.profit = this.workType2.utility;
            dayToPush.acProfit = dayToPush.profit + previousDay.acProfit;
          } else if (event === this.noWorkArrived.name) {
            dayToPush.rndDelay = '-';
            dayToPush.delay = '-';
            dayToPush.press2.state = previousDay.press2.state;
            dayToPush.press2.endDay = previousDay.press2.endDay;
            dayToPush.press1.state = previousDay.press1.state;
            dayToPush.press1.endDay = previousDay.press1.endDay;
            dayToPush.profit = 0;
            dayToPush.acProfit = previousDay.acProfit;
          }

        this.rows.push({ 
        day: dayToPush.number, 
        rndEvent: dayToPush.rndEvent, 
        event: dayToPush.event, 
        rndDelay: dayToPush.rndDelay, 
        delay: dayToPush.delay, 
        press1State: dayToPush.press1.state, press1EndDay: dayToPush.press1.endDay, 
        press2State: dayToPush.press2.state, press2EndDay: dayToPush.press2.endDay, 
        profit: dayToPush.profit, 
        acProfit: dayToPush.acProfit 
        })


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
      (r < 0.25) ? delay = 2 : (r > 0.24 && r < 0.5) ? delay = 3 : (r > 0.49 && r < 0.7) ? delay = 4 : (r > 0.69) ? delay = 5 : null;
      return delay;
    },
    getIncomingEvent (r, previousDay, dayToPushNumber) {
      let event = '-';
      if (dayToPushNumber === previousDay.press1.endDay) {
        event = this.workType1.workFinishedDesc
      } else if (dayToPushNumber === previousDay.press2.endDay) {
        event = this.workType2.workFinishedDesc
      } else if (this.simulation.enableType2Work) {
      (r < 0.5) ? event = this.workType1.name : (r > 0.49 && r < 0.7) ? event = this.workType2.name  : (r > 0.69) ? event = this.noWorkArrived.name : null;
      } else {
        (r < 0.5) ? event = this.workType1.name  : (r > 0.49) ? event = this.noWorkArrived.name : null;
      }
      return event;
    },
    getPreviousDay (row) {
      let previousDay =  Object.assign({}, this.day);
      previousDay.number = row.day;
      previousDay.rndEvent = row.rndEvent;
      previousDay.event = row.event;
      previousDay.rndDelay = row.rndDelay
      previousDay.delay = row.delay;
      previousDay.press1.state = row.press1State;
      previousDay.press1.endDay = row.press1EndDay;
      previousDay.press2.state = row.press2State;
      previousDay.press2.endDay = row.press2EndDay;
      previousDay.profit = row.profit;
      previousDay.acProfit = row.acProfit;
      return previousDay;
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
