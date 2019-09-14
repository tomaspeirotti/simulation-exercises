comprar
<template>
    <div class="mx-2">
        <b-row>
            <b-col class="px-4" cols="12">
                <b-row class="mb-2">
                    <b-col sm="12" class="text-center"><h4>Configurar</h4></b-col>
                </b-row>
                <b-row class="justify-content-md-center mb-4">
                    <b-col class="px-3" sm="2">
                        <label>Arrivos</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="asd">Cantidad</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.arrivos">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.arrivos.required">Debe ingresar un valor</div>
                        <div class="text-danger" v-if="!$v.simulation.arrivos.integer">Debe ingresar un entero</div>
                        <div class="text-danger" v-if="!$v.simulation.arrivos.maxValue">Debe ingresar un valor menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.arrivos.minValue">Debe ingresar un valor mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Frec. de atención</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="asd0">Media y varianza</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="atencion.media">
                            <input type="text" class="form-control" v-model.number="atencion.varianza">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.arrivos.required">Debe ingresar un valor</div>
                        <div class="text-danger" v-if="!$v.simulation.arrivos.integer">Debe ingresar un entero</div>
                        <div class="text-danger" v-if="!$v.simulation.arrivos.maxValue">Debe ingresar un valor menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.arrivos.minValue">Debe ingresar un valor mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Frec. de arrivos, de 7hs a 8hs</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="asd2">Media y varianza</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="intervaloUno.media">
                            <input type="text" class="form-control" v-model.number="intervaloUno.varianza">
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloUno.media.required">Debe ingresar una media</div>
                        <div class="text-danger" v-if="!$v.intervaloUno.media.integer">Debe ingresar un valor entero
                            para la media
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloUno.media.maxValue">Debe ingresar una media menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloUno.media.minValue">Debe ingresar una media mayor o
                            igual a 1
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloUno.varianza.required">Debe ingresar una varianza
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloUno.varianza.integer">Debe ingresar un valor entero
                            para la varianza
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloUno.varianza.maxValue">Debe ingresar una varianza
                            menor o igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloUno.varianza.minValue">Debe ingresar una varianza
                            mayor o igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Frec. de arrivos, de 8hs a 9hs</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Media y varianza</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="intervaloDos.media">
                            <input type="text" class="form-control" v-model.number="intervaloDos.varianza">
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloDos.media.required">Debe ingresar una media</div>
                        <div class="text-danger" v-if="!$v.intervaloDos.media.integer">Debe ingresar un valor entero
                            para la media
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloDos.media.maxValue">Debe ingresar una media menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloDos.media.minValue">Debe ingresar una media mayor o
                            igual a 1
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloDos.varianza.required">Debe ingresar una varianza
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloDos.varianza.integer">Debe ingresar un valor entero
                            para la varianza
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloDos.varianza.maxValue">Debe ingresar una varianza
                            menor o igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloDos.varianza.minValue">Debe ingresar una varianza
                            mayor o igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Frec. de arrivos, después de 9hs</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="">Media y varianza</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="intervaloTres.media">
                            <input type="text" class="form-control" v-model.number="intervaloTres.varianza">
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloTres.media.required">Debe ingresar una media</div>
                        <div class="text-danger" v-if="!$v.intervaloTres.media.integer">Debe ingresar un valor entero
                            para la media
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloTres.media.maxValue">Debe ingresar una media menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloTres.media.minValue">Debe ingresar una media mayor o
                            igual a 1
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloTres.varianza.required">Debe ingresar una varianza
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloTres.varianza.integer">Debe ingresar un valor entero
                            para la varianza
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloTres.varianza.maxValue">Debe ingresar una varianza
                            menor o igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.intervaloTres.varianza.minValue">Debe ingresar una varianza
                            mayor o igual a 1
                        </div>
                    </b-col>
                </b-row>
                <b-row class="justify-content-md-center mb-2">
                    <b-col class="px-1" sm="3">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Longitud máxima de cola observada</span>
                            </div>
                            <input type="text" class="form-control" readonly v-model.number="simulation.longitudMaxima">
                        </div>
                    </b-col>
                    <b-col class="px-1" sm="3">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Hora de comienzo (HH)</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.horaDeComienzo">
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
        <vue-good-table :pagination-options="{enabled: false, mode: 'pages'}" styleClass="vgt-table condensed"
                        theme="nocturnal" :columns="columns" :rows="rows"/>
    </div>
</template>

<script>
    import {integer, maxValue, minValue, numeric, required} from 'vuelidate/lib/validators'

    export default {
        name: 'simulation',
        data() {
            return {
                hasBeenSimulated: false,
                simulation: {
                    arrivos: 4500,
                    longitudMaxima: 0,
                    horaDeComienzo: 7
                },
                atencion: {
                    media: 10,
                    varianza: 4
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
                cliente: {
                    label: 'Cliente',
                    field: 'cliente',
                    type: 'text',
                    sortable: false
                },
                columns: [
                    {
                        label: 'Tiempo',
                        field: 'tiempo',
                        type: 'text',
                        sortable: false
                    },
                    {
                        label: 'Frec. de arrivos',
                        field: 'frecArrivos',
                        type: 'text',
                        sortable: false
                    },
                    {
                        label: 'Evento',
                        field: 'evento',
                        type: 'text',
                        sortable: false
                    },
                    {
                        label: 'RND',
                        field: 'rnd',
                        type: 'text',
                        sortable: false
                    },
                    {
                        label: 'Prox. arrivo',
                        field: 'proxArrivo',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Emp 1 - Estado',
                        field: 'emp1Estado',
                        type: 'text',
                        sortable: false
                    },
                    {
                        label: 'Emp 1 - Tiempo fin At.',
                        field: 'emp1FinAt',
                        type: 'text',
                        sortable: false
                    },
                    {
                        label: 'Emp 1 - Cola',
                        field: 'emp1Cola',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Emp 2 - Estado',
                        field: 'emp2Estado',
                        type: 'text',
                        sortable: false
                    },
                    {
                        label: 'Emp 2 - Tiempo fin At.',
                        field: 'emp2FinAt',
                        type: 'text',
                        sortable: false
                    },
                    {
                        label: 'Emp 2 - Cola',
                        field: 'emp2Cola',
                        type: 'number',
                        sortable: false
                    }
                ],
                tempRows: [],
                tempColumns: [],
                rows: [],
                row: {
                    tiempo: "",
                    frecArrivos: "",
                    evento: "",
                    rnd: "",
                    proxArrivo: "",
                    emp1Estado: "",
                    emp1FinAt: "",
                    emp1Cola: 0,
                    emp2Estado: "",
                    emp2FinAt: "",
                    emp2Cola: 0
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
            },
            atencion: {
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
            simulate() {
                this.$v.$touch()
                if (this.$v.$invalid) {
                    alert('Uno de los campos no contiene valores correctos')
                } else {
                    this.hasBeenSimulated = true;

                    // call api

                    this.hasBeenSimulated = false;
                }

            },
            getRandom() {
                let random = Math.random()
                return Math.round(random * 100) / 100
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
