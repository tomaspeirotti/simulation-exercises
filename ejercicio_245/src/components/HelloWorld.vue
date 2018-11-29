<template>
  <div class="mx-2">
    <b-row>
      <b-col class="px-4" cols="12">
        <b-row class="mb-2">
          <b-col sm="12" class="text-center"><h4>Configurar</h4></b-col>
        </b-row>
        <b-row class="mb-2">
            <b-col class="px-4" sm="3">
              <label>Arrivos</label>
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Cantidad</span>
                </div>
                <input type="text" class="form-control" v-model.number="simulation.arrivos">
              </div>
              <div class="text-danger" v-if="!$v.simulation.arrivos.required">Debe ingresar un valor</div>
              <div class="text-danger" v-if="!$v.simulation.arrivos.integer">Debe ingresar un entero</div>
              <div class="text-danger" v-if="!$v.simulation.arrivos.maxValue">Debe ingresar un valor menor o igual a 100000</div>
              <div class="text-danger" v-if="!$v.simulation.arrivos.minValue">Debe ingresar un valor mayor o igual a 1</div>
            </b-col>
            <b-col class="px-4" sm="3">
              <label>7hs a 8hs</label>
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Media y varianza</span>
                </div>
                <input type="text" class="form-control" v-model.number="intervaloUno.media">
                <input type="text" class="form-control" v-model.number="intervaloUno.varianza">
              </div>
              <div class="text-danger" v-if="!$v.intervaloUno.media.required">Debe ingresar una media</div>
              <div class="text-danger" v-if="!$v.intervaloUno.media.integer">Debe ingresar un valor entero para la media</div>
              <div class="text-danger" v-if="!$v.intervaloUno.media.maxValue">Debe ingresar una media menor o igual a 100000</div>
              <div class="text-danger" v-if="!$v.intervaloUno.media.minValue">Debe ingresar una media mayor o igual a 1</div>
              <div class="text-danger" v-if="!$v.intervaloUno.varianza.required">Debe ingresar una varianza</div>
              <div class="text-danger" v-if="!$v.intervaloUno.varianza.integer">Debe ingresar un valor entero para la varianza</div>
              <div class="text-danger" v-if="!$v.intervaloUno.varianza.maxValue">Debe ingresar una varianza menor o igual a 100000</div>
              <div class="text-danger" v-if="!$v.intervaloUno.varianza.minValue">Debe ingresar una varianza mayor o igual a 1</div>
            </b-col>
            <b-col class="px-4" sm="3">
              <label>8hs a 9hs</label>
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Media y varianza</span>
                </div>
                <input type="text" class="form-control" v-model.number="intervaloDos.media">
                <input type="text" class="form-control" v-model.number="intervaloDos.varianza">
              </div>
              <div class="text-danger" v-if="!$v.intervaloDos.media.required">Debe ingresar una media</div>
              <div class="text-danger" v-if="!$v.intervaloDos.media.integer">Debe ingresar un valor entero para la media</div>
              <div class="text-danger" v-if="!$v.intervaloDos.media.maxValue">Debe ingresar una media menor o igual a 100000</div>
              <div class="text-danger" v-if="!$v.intervaloDos.media.minValue">Debe ingresar una media mayor o igual a 1</div>
              <div class="text-danger" v-if="!$v.intervaloDos.varianza.required">Debe ingresar una varianza</div>
              <div class="text-danger" v-if="!$v.intervaloDos.varianza.integer">Debe ingresar un valor entero para la varianza</div>
              <div class="text-danger" v-if="!$v.intervaloDos.varianza.maxValue">Debe ingresar una varianza menor o igual a 100000</div>
              <div class="text-danger" v-if="!$v.intervaloDos.varianza.minValue">Debe ingresar una varianza mayor o igual a 1</div>
            </b-col>
            <b-col class="px-4" sm="3">
              <label>Después de 9hs</label>
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text" id="">Media y varianza</span>
                </div>
                <input type="text" class="form-control" v-model.number="intervaloTres.media">
                <input type="text" class="form-control" v-model.number="intervaloTres.varianza">
              </div>
              <div class="text-danger" v-if="!$v.intervaloTres.media.required">Debe ingresar una media</div>
              <div class="text-danger" v-if="!$v.intervaloTres.media.integer">Debe ingresar un valor entero para la media</div>
              <div class="text-danger" v-if="!$v.intervaloTres.media.maxValue">Debe ingresar una media menor o igual a 100000</div>
              <div class="text-danger" v-if="!$v.intervaloTres.media.minValue">Debe ingresar una media mayor o igual a 1</div>
              <div class="text-danger" v-if="!$v.intervaloTres.varianza.required">Debe ingresar una varianza</div>
              <div class="text-danger" v-if="!$v.intervaloTres.varianza.integer">Debe ingresar un valor entero para la varianza</div>
              <div class="text-danger" v-if="!$v.intervaloTres.varianza.maxValue">Debe ingresar una varianza menor o igual a 100000</div>
              <div class="text-danger" v-if="!$v.intervaloTres.varianza.minValue">Debe ingresar una varianza mayor o igual a 1</div>
            </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col class="px-4" sm="3">
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">Longitud máxima de cola observada</span>
            </div>
            <input type="text" class="form-control" readonly v-model.number="simulation.longitudMaxima">
          </div>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="3" class="text-left mb-3">
        <b-button class="btn btn-success" @click="simulate" :disabled="hasBeenSimulated">Simular</b-button>
      </b-col>
    </b-row>
    <vue-good-table :pagination-options="{enabled: false, mode: 'pages'}" styleClass="vgt-table condensed" theme="nocturnal" :columns="columns" :rows="rows"/>
  </div>
</template>

<script>
import { required, minValue, maxValue, numeric, integer, sameAs } from 'vuelidate/lib/validators'

export default {
  name: 'my-component',
  data () {
    return {
      hasBeenSimulated: false,
      simulation: {
        arrivos: 4500,
        longitudMaxima: 0
      },
      intervaloUno: {
        numero: 1,
        media: 6,
        varianza: 1
      },
      intervaloDos: {
        numero: 2,
        media: 5,
        varianza: 1
      },
      intervaloTres: {
        numero: 3,
        media: 7,
        varianza: 1
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
          type: 'text',
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
          type: 'text',
          sortable: false
        },
        {
          label: 'Prensa 1 - Estado',
          field: 'press1State',
          type: 'text',
          sortable: false
        },
        {
          label: 'Prensa 1 - Dia fin',
          field: 'press1EndDay',
          type: 'text',
          sortable: false
        },
        {
          label: 'Prensa 2 - Estado',
          field: 'press2State',
          type: 'text',
          sortable: false
        },
        {
          label: 'Prensa 2 - Dia fin',
          field: 'press2EndDay',
          type: 'text',
          sortable: false
        },
        {
          label: 'Utilidad',
          field: 'profit',
          type: 'text',
          sortable: false
        },
        {
          label: 'Utilidad Ac.',
          field: 'acProfit',
          type: 'text',
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
          state: 'Libre',
          endDay: '-',
          currentWorkType: null
        },
        press2: {
          name: 'Prensa 2',
          state: 'Libre',
          endDay: '-',
          currentWorkType: null
        }
      }
    };
  },
  validations: {
    simulation: {
      arrivos: {
        required,
        maxValue: maxValue(100000),
        minValue: minValue(1),
        numeric,
        integer
      }
    },
    intervaloUno: {
      media: {
      required,
      maxValue: maxValue(100000),
      minValue: minValue(1),
      numeric,
      integer
      },
      varianza: {
      required,
      maxValue: maxValue(100000),
      minValue: minValue(1),
      numeric,
      integer
      }
    },
    intervaloDos: {
      media: {
      required,
      maxValue: maxValue(100000),
      minValue: minValue(1),
      numeric,
      integer
      },
      varianza: {
      required,
      maxValue: maxValue(100000),
      minValue: minValue(1),
      numeric,
      integer
      }
    },
    intervaloTres: {
      media: {
      required,
      maxValue: maxValue(100000),
      minValue: minValue(1),
      numeric,
      integer
      },
      varianza: {
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
        this.rows.push({ day: this.day.number, rndEvent: 0, event: '-', rndDelay: 0, delay: 0, press1State: 'Libre', press1EndDay: '-', press2State: 'Libre', press2EndDay: '-', profit: 0, acProfit: 0 })

        for (let i = 1; i <= this.simulation.peopleToSimulate; i++) {}
          
        this.rows.push({ 
          // day: dayToPush.number, 
          // rndEvent: dayToPush.rndEvent, 
          // event: dayToPush.event.desc, 
          // rndDelay: dayToPush.rndDelay, 
          // delay: dayToPush.delay, 
          // press1State: dayToPush.press1.state, press1EndDay: dayToPush.press1.endDay, 
          // press2State: dayToPush.press2.state, press2EndDay: dayToPush.press2.endDay, 
          // profit: dayToPush.profit, 
          // acProfit: dayToPush.acProfit 
        })

        }
      }
    },
    getRandom () {
      let random = Math.random()
      return Math.round(random * 100) / 100
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
