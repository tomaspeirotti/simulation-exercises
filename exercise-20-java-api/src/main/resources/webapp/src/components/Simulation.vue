comprar
<template>
    <div class="mx-2">
        <b-row>
            <b-col class="px-4" cols="12">
                <b-row class="mb-2">
                    <b-col sm="12" class="text-center"><h4>Configurar</h4></b-col>
                </b-row>
                <!--                <b-row class="justify-content-md-center mb-4">-->
                <!--                    <b-col class="px-3" sm="2">-->
                <!--                        <label>Arrivos</label>-->
                <!--                        <div class="input-group">-->
                <!--                            <div class="input-group-prepend">-->
                <!--                                <span class="input-group-text" id="asd">Cantidad</span>-->
                <!--                            </div>-->
                <!--                            <input type="text" class="form-control" v-model.number="simulation.arrivos">-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.simulation.arrivos.required">Debe ingresar un valor</div>-->
                <!--                        <div class="text-danger" v-if="!$v.simulation.arrivos.integer">Debe ingresar un entero</div>-->
                <!--                        <div class="text-danger" v-if="!$v.simulation.arrivos.maxValue">Debe ingresar un valor menor o-->
                <!--                            igual a 100000-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.simulation.arrivos.minValue">Debe ingresar un valor mayor o-->
                <!--                            igual a 1-->
                <!--                        </div>-->
                <!--                    </b-col>-->
                <!--                    <b-col class="px-3" sm="2">-->
                <!--                        <label>Frec. de atención</label>-->
                <!--                        <div class="input-group">-->
                <!--                            <div class="input-group-prepend">-->
                <!--                                <span class="input-group-text" id="asd0">Media y varianza</span>-->
                <!--                            </div>-->
                <!--                            <input type="text" class="form-control" v-model.number="atencion.media">-->
                <!--                            <input type="text" class="form-control" v-model.number="atencion.varianza">-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.simulation.arrivos.required">Debe ingresar un valor</div>-->
                <!--                        <div class="text-danger" v-if="!$v.simulation.arrivos.integer">Debe ingresar un entero</div>-->
                <!--                        <div class="text-danger" v-if="!$v.simulation.arrivos.maxValue">Debe ingresar un valor menor o-->
                <!--                            igual a 100000-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.simulation.arrivos.minValue">Debe ingresar un valor mayor o-->
                <!--                            igual a 1-->
                <!--                        </div>-->
                <!--                    </b-col>-->
                <!--                    <b-col class="px-3" sm="2">-->
                <!--                        <label>Frec. de arrivos, de 7hs a 8hs</label>-->
                <!--                        <div class="input-group">-->
                <!--                            <div class="input-group-prepend">-->
                <!--                                <span class="input-group-text" id="asd2">Media y varianza</span>-->
                <!--                            </div>-->
                <!--                            <input type="text" class="form-control" v-model.number="intervaloUno.media">-->
                <!--                            <input type="text" class="form-control" v-model.number="intervaloUno.varianza">-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloUno.media.required">Debe ingresar una media</div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloUno.media.integer">Debe ingresar un valor entero-->
                <!--                            para la media-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloUno.media.maxValue">Debe ingresar una media menor o-->
                <!--                            igual a 100000-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloUno.media.minValue">Debe ingresar una media mayor o-->
                <!--                            igual a 1-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloUno.varianza.required">Debe ingresar una varianza-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloUno.varianza.integer">Debe ingresar un valor entero-->
                <!--                            para la varianza-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloUno.varianza.maxValue">Debe ingresar una varianza-->
                <!--                            menor o igual a 100000-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloUno.varianza.minValue">Debe ingresar una varianza-->
                <!--                            mayor o igual a 1-->
                <!--                        </div>-->
                <!--                    </b-col>-->
                <!--                    <b-col class="px-3" sm="2">-->
                <!--                        <label>Frec. de arrivos, de 8hs a 9hs</label>-->
                <!--                        <div class="input-group">-->
                <!--                            <div class="input-group-prepend">-->
                <!--                                <span class="input-group-text">Media y varianza</span>-->
                <!--                            </div>-->
                <!--                            <input type="text" class="form-control" v-model.number="intervaloDos.media">-->
                <!--                            <input type="text" class="form-control" v-model.number="intervaloDos.varianza">-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloDos.media.required">Debe ingresar una media</div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloDos.media.integer">Debe ingresar un valor entero-->
                <!--                            para la media-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloDos.media.maxValue">Debe ingresar una media menor o-->
                <!--                            igual a 100000-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloDos.media.minValue">Debe ingresar una media mayor o-->
                <!--                            igual a 1-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloDos.varianza.required">Debe ingresar una varianza-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloDos.varianza.integer">Debe ingresar un valor entero-->
                <!--                            para la varianza-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloDos.varianza.maxValue">Debe ingresar una varianza-->
                <!--                            menor o igual a 100000-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloDos.varianza.minValue">Debe ingresar una varianza-->
                <!--                            mayor o igual a 1-->
                <!--                        </div>-->
                <!--                    </b-col>-->
                <!--                    <b-col class="px-3" sm="2">-->
                <!--                        <label>Frec. de arrivos, después de 9hs</label>-->
                <!--                        <div class="input-group">-->
                <!--                            <div class="input-group-prepend">-->
                <!--                                <span class="input-group-text" id="">Media y varianza</span>-->
                <!--                            </div>-->
                <!--                            <input type="text" class="form-control" v-model.number="intervaloTres.media">-->
                <!--                            <input type="text" class="form-control" v-model.number="intervaloTres.varianza">-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloTres.media.required">Debe ingresar una media</div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloTres.media.integer">Debe ingresar un valor entero-->
                <!--                            para la media-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloTres.media.maxValue">Debe ingresar una media menor o-->
                <!--                            igual a 100000-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloTres.media.minValue">Debe ingresar una media mayor o-->
                <!--                            igual a 1-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloTres.varianza.required">Debe ingresar una varianza-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloTres.varianza.integer">Debe ingresar un valor entero-->
                <!--                            para la varianza-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloTres.varianza.maxValue">Debe ingresar una varianza-->
                <!--                            menor o igual a 100000-->
                <!--                        </div>-->
                <!--                        <div class="text-danger" v-if="!$v.intervaloTres.varianza.minValue">Debe ingresar una varianza-->
                <!--                            mayor o igual a 1-->
                <!--                        </div>-->
                <!--                    </b-col>-->
                <!--                </b-row>-->
                <!--                <b-row class="justify-content-md-center mb-2">-->
                <!--                    <b-col class="px-1" sm="3">-->
                <!--                        <div class="input-group">-->
                <!--                            <div class="input-group-prepend">-->
                <!--                                <span class="input-group-text">Longitud máxima de cola observada</span>-->
                <!--                            </div>-->
                <!--                            <input type="text" class="form-control" readonly v-model.number="simulation.longitudMaxima">-->
                <!--                        </div>-->
                <!--                    </b-col>-->
                <!--                    <b-col class="px-1" sm="3">-->
                <!--                        <div class="input-group">-->
                <!--                            <div class="input-group-prepend">-->
                <!--                                <span class="input-group-text">Hora de comienzo (HH)</span>-->
                <!--                            </div>-->
                <!--                            <input type="text" class="form-control" v-model.number="simulation.horaDeComienzo">-->
                <!--                        </div>-->
                <!--                    </b-col>-->
                <!--                </b-row>-->
            </b-col>
        </b-row>
        <b-row>
            <b-col cols="3" class="text-left mb-3">
                <b-button class="btn btn-success" @click="simulate" :disabled="hasBeenSimulated">Simular</b-button>
            </b-col>
        </b-row>
        <vue-good-table
            :pagination-options="{  enabled: true,
                                    mode: 'records',
                                    perPage: 5,
                                    position: 'top',
                                    perPageDropdown: [5, 10, 15, 25, 35],
                                    dropdownAllowAll: false,
                                    setCurrentPage: 0,
                                    nextLabel: 'Next',
                                    prevLabel: 'Prev',
                                    rowsPerPageLabel: 'Rows per page',
                                    ofLabel: 'of',
                                    pageLabel: 'page', // for 'pages' mode
                                    allLabel: 'All'}"
        styleClass="vgt-table condensed" theme="nocturnal" :columns="columns" :rows="rows"/>
    </div>
</template>

<script>
    import {integer, maxValue, minValue, numeric, required} from 'vuelidate/lib/validators'

    export default {
        name: 'simulation',
        data() {
            return {
                hasBeenSimulated: false,
                columns: [
                    {
                        label: 'Dia',
                        field: 'dia',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Dm.RND',
                        field: 'demanda.rnd',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Dm.Cant',
                        field: 'demanda.cantidad',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Dm.Stock',
                        field: 'demanda.stock',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Pe.RND',
                        field: 'pedido.rnd',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Pe.TpoRep',
                        field: 'pedido.plazoDeReposicion',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Pe.UltRep',
                        field: 'pedido.ultimaReposicion',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Pe.DiaRep',
                        field: 'pedido.diaDeResposicion',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Km',
                        field: 'km',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Ko',
                        field: 'ko',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Ks.Costo',
                        field: 'ks.ks',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Ks.DiaCobro',
                        field: 'ks.diaDeCobro',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Costo total',
                        field: 'costoTotal',
                        type: 'number',
                        sortable: false
                    },
                    {
                        label: 'Costo acu.',
                        field: 'costoAcumulado',
                        type: 'number',
                        sortable: false
                    }
                ],
                rows: [],
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
