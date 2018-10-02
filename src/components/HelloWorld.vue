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
    <vue-good-table :pagination-options="{enabled: false, mode: 'pages'}" styleClass="vgt-table condensed" theme="nocturnal" :columns="columns" :rows="rows"/>
  </div>
</template>

<script>
import { required, minValue, maxValue, numeric, integer } from 'vuelidate/lib/validators' //decimal, maxLength

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
        enableType2Work: true,
        daysWithoutWorkAndEmptyPresses: 0,
        rejectedWork: 0
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
        event: {
          desc: '-'
        },
        rndDelay: 0,
        delay: '-',
        profit: 0,
        acProfit: 0,
        press1: {
          name: 'Prensa 1',
          state: 'L',
          endDay: '-',
          currentWorkType: null
        },
        press2: {
          name: 'Prensa 2',
          state: 'L',
          endDay: '-',
          currentWorkType: null
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

          if (event.desc === this.workType1.name || event.desc === this.workType2.name) {
            let delay;
            if (previousDay.press1.state === 'L' || previousDay.press2.state === 'L') {
              dayToPush.rndDelay = this.getRandom()
              delay = this.getDelay(dayToPush.rndDelay)
              dayToPush.delay = delay;
            }
            
            if (previousDay.press1.state === 'L') {
              dayToPush.press1.state = 'O'
              dayToPush.press1.endDay = i + delay;
              dayToPush.press1.currentWorkType = event.workType;
              dayToPush.press2.state = previousDay.press2.state;
              dayToPush.press2.endDay = previousDay.press2.endDay;
            } else if (previousDay.press2.state === 'L') {
              dayToPush.press2.state = 'O';
              dayToPush.press2.endDay = i + delay;
              dayToPush.press2.currentWorkType = event.workType;
              dayToPush.press1.state = previousDay.press1.state;
              dayToPush.press1.endDay = previousDay.press1.endDay;
            } else if (previousDay.press1.state === 'O' && previousDay.press2.state === 'O') {
              dayToPush.press1.state = previousDay.press1.state;
              dayToPush.press1.endDay = previousDay.press1.endDay;
              dayToPush.press1.currentWorkType = previousDay.press1.currentWorkType;
              dayToPush.press2.state = previousDay.press2.state;
              dayToPush.press2.endDay = previousDay.press2.endDay;
              dayToPush.press2.currentWorkType = previousDay.press2.currentWorkType;

              this.simulation.rejectedWork += 1;
            }

            dayToPush.profit = 0;
            dayToPush.acProfit = previousDay.acProfit;
          
          } else if (event.desc === this.workType1.workFinishedDesc || event.desc === this.workType2.workFinishedDesc || event.desc === 'Fin trabajo tipo 1 y 2') {
            dayToPush.rndDelay = '-';
            dayToPush.delay = '-';
            let press = this.getPressWithWorkFinished(dayToPush);
            switch (press.name) {
              case this.day.press1.name: 
                dayToPush.press1.state = 'L';
                dayToPush.press1.endDay = '-';
                // dayToPush.press1.currentWorkType = null;
                dayToPush.press2.state = previousDay.press2.state;
                dayToPush.press2.endDay = previousDay.press2.endDay;
                dayToPush.press2.currentWorkType = previousDay.press2.currentWorkType;
                dayToPush.profit = press.currentWorkType.utility;
                dayToPush.acProfit = dayToPush.profit + previousDay.acProfit;
                break;
              case this.day.press2.name: 
                dayToPush.press2.state = 'L';
                dayToPush.press2.endDay = '-';
                // dayToPush.press2.currentWorkType = null;
                dayToPush.press1.state = previousDay.press1.state;
                dayToPush.press1.endDay = previousDay.press1.endDay;
                dayToPush.press1.currentWorkType = previousDay.press1.currentWorkType;
                dayToPush.profit = press.currentWorkType.utility;
                dayToPush.acProfit = dayToPush.profit + previousDay.acProfit;
                break;
              case 'Prensa 1 y 2': 
                dayToPush.press1.state = 'L';
                dayToPush.press1.endDay = '-';
                // dayToPush.press1.currentWorkType = null;
                dayToPush.press2.state = 'L';
                dayToPush.press2.endDay = '-';
                // dayToPush.press2.currentWorkType = null;
                dayToPush.profit = previousDay.press1.currentWorkType.utility + previousDay.press2.currentWorkType.utility;
                dayToPush.acProfit = dayToPush.profit + previousDay.acProfit;
                break;
            }
          } else if (event.desc === this.noWorkArrived.name) {
            dayToPush.rndDelay = '-';
            dayToPush.delay = '-';
            dayToPush.press2.state = previousDay.press2.state;
            dayToPush.press2.endDay = previousDay.press2.endDay;
            dayToPush.press2.currentWorkType = previousDay.press2.currentWorkType;
            dayToPush.press1.state = previousDay.press1.state;
            dayToPush.press1.endDay = previousDay.press1.endDay;
            dayToPush.press1.currentWorkType = previousDay.press1.currentWorkType;
            dayToPush.profit = 0;
            dayToPush.acProfit = previousDay.acProfit;

            (previousDay.press1.state === 'L' && previousDay.press2.state === 'L') ? this.simulation.daysWithoutWorkAndEmptyPresses += 1 : null;
          }

        this.rows.push({ 
          day: dayToPush.number, 
          rndEvent: dayToPush.rndEvent, 
          event: dayToPush.event.desc, 
          rndDelay: dayToPush.rndDelay, 
          delay: dayToPush.delay, 
          press1State: dayToPush.press1.state, press1EndDay: dayToPush.press1.endDay, 
          press2State: dayToPush.press2.state, press2EndDay: dayToPush.press2.endDay, 
          profit: dayToPush.profit, 
          acProfit: dayToPush.acProfit 
        })


        if (i === this.simulation.daysToSimulate) {
            let rejectedWorkPerc = Math.round((this.simulation.rejectedWork / this.simulation.daysToSimulate) * 100) * 100 / 100;
            let daysWithoutWorkAndEmptyPressesPerc = Math.round((this.simulation.daysWithoutWorkAndEmptyPresses / this.simulation.daysToSimulate) * 100) * 100 / 100;
            console.log('[Trabajos rechazado: % ' + rejectedWorkPerc + ']')
            console.log('[Dias sin trabajo y prensas vacías: % '+ daysWithoutWorkAndEmptyPressesPerc + ']')
            console.log('[Ganancia acumulada: ' + dayToPush.acProfit + ']')
            console.log('[Ganancia promedio por dia: ' + dayToPush.acProfit / this.simulation.daysToSimulate + ']')
        } 


        }
      //  this.$emit('clicked', this.rows[this.rows.length - 1].totalCostAcum)
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

      let event = {};
      event.desc = '-';
      event.workType = null;

      if (dayToPushNumber === previousDay.press2.endDay && dayToPushNumber === previousDay.press1.endDay) {
        event.desc = 'Fin trabajo tipo 1 y 2'
      } else if (dayToPushNumber === previousDay.press2.endDay) {
        event.desc = previousDay.press2.currentWorkType.workFinishedDesc
      } else if (dayToPushNumber === previousDay.press1.endDay) {
        event.desc = previousDay.press1.currentWorkType.workFinishedDesc
      } else if (this.simulation.enableType2Work) {
        if (r < 0.5) { 
          event.workType = this.workType1
          event.desc = this.workType1.name
        } else if (r > 0.49 && r < 0.7) { 
          event.desc = this.workType2.name 
          event.workType = this.workType2
        } else if (r > 0.69) {
           event.desc = this.noWorkArrived.name
           event.workType = this.noWorkArrived
        }
      } else {
        if (r < 0.5) {
          event.desc = this.workType1.name
          event.workType = this.workType1
        } else if (r > 0.49) { 
          event.desc = this.noWorkArrived.name
          event.workType = this.noWorkArrived
        }

      }
      return event;
    },
    getPreviousDay (row) {
      let previousDay =  Object.assign({}, this.day);
      previousDay.number = row.day;
      previousDay.rndEvent = row.rndEvent;
      previousDay.event.desc = row.event.desc;
      previousDay.rndDelay = row.rndDelay
      previousDay.delay = row.delay;
      previousDay.press1.state = row.press1State;
      previousDay.press1.endDay = row.press1EndDay;
      previousDay.press2.state = row.press2State;
      previousDay.press2.endDay = row.press2EndDay;
      previousDay.profit = row.profit;
      previousDay.acProfit = row.acProfit;
      return previousDay;
    },
    getPressWithWorkFinished (dayToPush) {
      let press = {};
      let number = dayToPush.number;
      let press1 = Object.assign({}, dayToPush.press1);
      let press2 = Object.assign({}, dayToPush.press2);
      
        if (number === press1.endDay && number === press2.endDay) {
        press.name = 'Prensa 1 y 2'
        press.press1 = {}
        press.press2 = {}
        press.press1.utility = press1.currentWorkType.utility
        press.press2.utility = press2.currentWorkType.utility
        } else if (number === press1.endDay) {
        press.name = 'Prensa 1'
        press.currentWorkType = Object.assign({}, press1.currentWorkType);
        } else if (number === press2.endDay) {
        press.name = 'Prensa 2'
        press.currentWorkType = Object.assign({}, press2.currentWorkType);
        }

      return press;
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
