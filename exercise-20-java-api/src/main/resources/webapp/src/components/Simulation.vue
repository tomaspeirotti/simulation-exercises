<template>
    <div class="mx-2">
        <b-row>
            <b-col class="px-4" cols="12">
                <b-row class="mb-2">
                    <b-col sm="12" class="text-center"><h4>Configurar</h4></b-col>
                </b-row>
                <b-row class="justify-content-md-center mb-4">
                    <b-col class="px-3" sm="2">
                        <label>Dias de Operacion</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="diasCant">Cant.</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.diasDeOperacion">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasDeOperacion.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasDeOperacion.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasDeOperacion.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasDeOperacion.minValue">Debe ingresar un valor
                            mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Costo Mantenimiento</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="costoMantenimiento">$</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.costoMantenimiento">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoMantenimiento.required">Debe ingresar un
                            valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoMantenimiento.integer">Debe ingresar un
                            entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoMantenimiento.maxValue">Debe ingresar un
                            valor menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoMantenimiento.minValue">Debe ingresar un
                            valor mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Cantidad de reposicion</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="cantReposicion">Cant</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.cantReposicion">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.cantReposicion.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.cantReposicion.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.cantReposicion.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.cantReposicion.minValue">Debe ingresar un valor
                            mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Nivel Reposicion</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="nivelReposicion">Nivel</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.nivelReposicion">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.nivelReposicion.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.nivelReposicion.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.nivelReposicion.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.nivelReposicion.minValue">Debe ingresar un valor
                            mayor o
                            igual a 0
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Dias para reponer</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="diasParaReponer">Cant.</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.diasParaReponer">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasParaReponer.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasParaReponer.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasParaReponer.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasParaReponer.minValue">Debe ingresar un valor
                            mayor o
                            igual a 0
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Stock Inicial</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="stockInicial">Cant.</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.stockInicial">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.stockInicial.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.stockInicial.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.stockInicial.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.stockInicial.minValue">Debe ingresar un valor
                            mayor o
                            igual a 1
                        </div>
                    </b-col>
                </b-row>
                <b-row class="justify-content-md-center mb-4">
                    <b-col class="px-3" sm="2">
                        <label>Costo Ordenamiento</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="costoOrdenamiento">$</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.costoOrdenamiento">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoOrdenamiento.required">Debe ingresar un
                            valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoOrdenamiento.integer">Debe ingresar un
                            entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoOrdenamiento.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoOrdenamiento.minValue">Debe ingresar un valor
                            mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Costo Stockout</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="costoStockout">$</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.costoStockout">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoStockout.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoStockout.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoStockout.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoStockout.minValue">Debe ingresar un valor
                            mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Dias para pagar el stockout</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="diasParaPagarStockOut">Cant.</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.diasParaPagarStockOut">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasParaPagarStockOut.required">Debe ingresar un
                            valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasParaPagarStockOut.integer">Debe ingresar un
                            entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasParaPagarStockOut.maxValue">Debe ingresar un
                            valor menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.diasParaPagarStockOut.minValue">Debe ingresar un
                            valor mayor o
                            igual a 1
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
        <vue-good-table
                :pagination-options="{
                                    enabled: true,
                                    mode: 'records',
                                    perPage: table.perPage,
                                    position: 'top',
                                    perPageDropdown: [5, 10, 15, 25, 35],
                                    dropdownAllowAll: false,
                                    setCurrentPage: table.currentPage,
                                    nextLabel: 'Next',
                                    prevLabel: 'Prev',
                                    rowsPerPageLabel: 'Rows per page',
                                    ofLabel: 'of',
                                    pageLabel: 'page', // for 'pages' mode
                                    allLabel: 'All'}"
                mode="remote" :totalRows="table.totalRows" :isLoading.sync="table.isLoading"
                styleClass="vgt-table condensed" theme="nocturnal" :columns="table.columns" :rows="table.rows"
                @on-page-change="onPageChange" @on-per-page-change="onPerPageChange"/>
        <br>
        <b-row class="justify-content-md-center mb-4">
            <b-col class="px-3" sm="2">
                <label>Costo total Acumulado</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="totalAcumulado">$</span>
                    </div>
                    <input readonly type="text" class="form-control" v-model.number="table.totalAcumulado">
                </div>
            </b-col>
            <b-col class="px-3" sm="2">
                <label>Costo promedio por día</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="promedio">$</span>
                    </div>
                    <input readonly type="text" class="form-control" v-model.number="table.costoPromedioPorDia">
                </div>
            </b-col>
        </b-row>
    </div>
</template>

<script>
    import {integer, maxValue, minValue, numeric, required} from 'vuelidate/lib/validators'

    const axios = require('axios');
    axios.defaults.baseURL = 'http://localhost:8090';
    axios.defaults.auth = {username: 'admin', password: 'admin'};
    axios.defaults.port = 8090;

    export default {
        name: 'simulation',
        data() {
            return {
                hasBeenSimulated: false,
                table: {
                    isLoading: false,
                    totalRows: 0,
                    currentPage: 1,
                    perPage: 10,
                    columns: [
                        {
                            label: 'Dia',
                            field: 'dia',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Dm.RND',
                            field: 'demanda.rnd',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Dm.Cant',
                            field: 'demanda.cantidad',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Dm.Stock',
                            field: 'demanda.stock',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Pe.RND',
                            field: 'pedido.rnd',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Pe.TpoRep',
                            field: 'pedido.plazoDeReposicion',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Pe.UltRep',
                            field: 'pedido.ultimaReposicion',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Pe.DiaRep',
                            field: 'pedido.diaDeResposicion',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Km',
                            field: 'km',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Ko',
                            field: 'ko',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Ks.Costo',
                            field: 'ks.ks',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Ks.DiaCobro',
                            field: 'ks.diaDeCobro',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Costo',
                            field: 'costoTotal',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        },
                        {
                            label: 'Costo acu.',
                            field: 'costoAcumulado',
                            type: 'number',
                            sortable: false,
                            tdClass: 'text-center'
                        }
                    ],
                    rows: [],
                    totalAcumulado: 0,
                    costoPromedioPorDia: 0
                },
                simulation: {
                    diasDeOperacion: 112,
                    costoMantenimiento: 12,
                    cantReposicion: 20,
                    nivelReposicion: 0,
                    diasParaReponer: 7,
                    stockInicial: 12,
                    costoOrdenamiento: 220,
                    costoStockout: 60,
                    diasParaPagarStockOut: 7
                }
            };
        },
        validations: {
            simulation: {
                diasDeOperacion: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                costoMantenimiento: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                cantReposicion: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                nivelReposicion: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(0),
                    numeric,
                    integer
                },
                diasParaReponer: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(0),
                    numeric,
                    integer
                },
                stockInicial: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                costoOrdenamiento: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                costoStockout: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                diasParaPagarStockOut: {
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
                this.table.rows = [];
                this.table.currentPage = 1;
                this.table.costoPromedioPorDia = 0;
                this.table.totalAcumulado = 0;
                this.$v.$touch()
                if (this.$v.$invalid) {
                    alert('Uno de los campos no contiene valores correctos')
                } else {
                    this.hasBeenSimulated = true;
                    axios.post('/simulate', this.simulation
                    ).then((response) => {
                            this.table.totalAcumulado = response.data.totalAcumulado;
                            this.table.costoPromedioPorDia = response.data.costoPromedioPorDia;
                            this.getIteracion(this.table.currentPage, this.table.perPage)
                        }
                    ).catch(function (result) {
                        alert(result.response.data.message)
                    });

                    this.hasBeenSimulated = false;
                }
            },
            onPageChange(params) {
                this.table.currentPage = params.currentPage;
                this.table.perPage = params.currentPerPage;
                this.getIteracion(params.currentPage, params.currentPerPage)
            },
            onPerPageChange(params) {
                this.table.perPage = params.currentPerPage
            },
            getIteracion(page, size) {
                this.table.rows = [];
                this.table.isLoading = true;
                return axios.get('/iterations?page=' + (page - 1) + '&size=' + size
                ).then(response => {
                    this.table.rows = this.table.rows.concat(response.data.iteraciones);
                    this.table.totalRows = response.data.totalRows;
                    this.table.isLoading = false;
                }).catch(function (result) {
                    alert(result.data.message)
                })
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
