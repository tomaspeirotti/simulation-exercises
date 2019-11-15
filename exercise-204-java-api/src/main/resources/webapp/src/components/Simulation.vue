<template>
    <div class="mx-2">
        <b-row>
            <b-col class="px-4" cols="12">
                <b-row class="mb-2">
                    <b-col sm="12" class="text-center"><h4>Configurar</h4></b-col>
                </b-row>
                <b-row class="justify-content-md-center mb-4">
                    <b-col class="px-3" sm="2">
                        <label>Semanas</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="semanasDeOperacion">Cant.</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.semanas">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.semanas.required">Debe ingresar un
                            valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.semanas.integer">Debe ingresar un
                            entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.semanas.maxValue">Debe ingresar un
                            valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.semanas.minValue">Debe ingresar un
                            valor
                            mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Costo Reparación</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="costoReparacion">$</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.costoReparacion">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoReparacion.required">Debe ingresar un
                            valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoReparacion.integer">Debe ingresar un
                            entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoReparacion.maxValue">Debe ingresar un
                            valor menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.costoReparacion.minValue">Debe ingresar un
                            valor mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Ingresos</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="ingresosExcelente">Excelente</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.ingresosExcelente">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosExcelente.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosExcelente.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosExcelente.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosExcelente.minValue">Debe ingresar un valor
                            mayor o
                            igual a 1
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Ingresos</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="ingresosBuena">Bueno</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.ingresosBueno">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosBueno.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosBueno.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosBueno.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosBueno.minValue">Debe ingresar un valor
                            mayor o
                            igual a 0
                        </div>
                    </b-col>
                    <b-col class="px-3" sm="2">
                        <label>Ingresos</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="ingresosMalo">Malo</span>
                            </div>
                            <input type="text" class="form-control" v-model.number="simulation.ingresosMalo">
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosMalo.required">Debe ingresar un valor
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosMalo.integer">Debe ingresar un entero
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosMalo.maxValue">Debe ingresar un valor
                            menor o
                            igual a 100000
                        </div>
                        <div class="text-danger" v-if="!$v.simulation.ingresosMalo.minValue">Debe ingresar un valor
                            mayor o
                            igual a 0
                        </div>
                    </b-col>
                </b-row>
            </b-col>
        </b-row>
        <br>
        <b-row class="justify-content-md-center mb-4">
            <b-col class="px-3" sm="2">
                <b-button class="btn btn-success" @click="simulate" :disabled="isSimulating">Simular</b-button>
            </b-col>
            <b-col class="px-3" sm="2">
                <label>Balance Politica A</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="balanceA">$</span>
                    </div>
                    <input readonly type="text" class="form-control" v-model.number="A.table.balance">
                </div>
            </b-col>
            <b-col class="px-3" sm="2">
                <label>Balance Politica B</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="balanceB">$</span>
                    </div>
                    <input readonly type="text" class="form-control" v-model.number="B.table.balance">
                </div>
            </b-col>
            <b-col class="px-3" sm="2">
                <label>Balance Politica C</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="balanceC">$</span>
                    </div>
                    <input readonly type="text" class="form-control" v-model.number="C.table.balance">
                </div>
            </b-col>
        </b-row>
        <b-row class="justify-content-md-center mb-4">
            <b-col class="px-6" sm="12">
                <h3>Política A: Reparar cada 6 semanas, independiente del estado</h3>
            </b-col>
        </b-row>
        <vue-good-table
                :pagination-options="{
                                    enabled: true,
                                    mode: 'records',
                                    perPage: A.table.perPage,
                                    position: 'top',
                                    perPageDropdown: [5, 10, 15, 25, 35],
                                    dropdownAllowAll: false,
                                    setCurrentPage: A.table.currentPage,
                                    nextLabel: 'Next',
                                    prevLabel: 'Prev',
                                    rowsPerPageLabel: 'Rows per page',
                                    ofLabel: 'of',
                                    pageLabel: 'page', // for 'pages' mode
                                    allLabel: 'All'}"
                mode="remote" :totalRows="A.table.totalRows"
                styleClass="vgt-table condensed" theme="nocturnal" :columns="A.table.columns" :rows="A.table.rows"
                @on-page-change="onPageChangeA" @on-per-page-change="onPerPageChangeA"/>
        <br>
        <b-row class="justify-content-md-center mb-4">
            <b-col class="px-6" sm="12">
                <h3>Política B: Reparar cada 4 semanas o cuando el estado sea malo, lo que venga mas tarde.</h3>
            </b-col>
        </b-row>
        <vue-good-table
                :pagination-options="{
                                    enabled: true,
                                    mode: 'records',
                                    perPage: B.table.perPage,
                                    position: 'top',
                                    perPageDropdown: [5, 10, 15, 25, 35],
                                    dropdownAllowAll: false,
                                    setCurrentPage: B.table.currentPage,
                                    nextLabel: 'Next',
                                    prevLabel: 'Prev',
                                    rowsPerPageLabel: 'Rows per page',
                                    ofLabel: 'of',
                                    pageLabel: 'page', // for 'pages' mode
                                    allLabel: 'All'}"
                mode="remote" :totalRows="B.table.totalRows"
                styleClass="vgt-table condensed" theme="nocturnal" :columns="B.table.columns" :rows="B.table.rows"
                @on-page-change="onPageChangeB" @on-per-page-change="onPerPageChangeB"/>
        <br>
        <b-row class="justify-content-md-center mb-4">
            <b-col class="px-6" sm="12">
                <h3>Política C: Reparar cuando su estado sea Mala</h3>
            </b-col>
        </b-row>
        <vue-good-table
                :pagination-options="{
                                    enabled: true,
                                    mode: 'records',
                                    perPage: C.table.perPage,
                                    position: 'top',
                                    perPageDropdown: [5, 10, 15, 25, 35],
                                    dropdownAllowAll: false,
                                    setCurrentPage: C.table.currentPage,
                                    nextLabel: 'Next',
                                    prevLabel: 'Prev',
                                    rowsPerPageLabel: 'Rows per page',
                                    ofLabel: 'of',
                                    pageLabel: 'page', // for 'pages' mode
                                    allLabel: 'All'}"
                mode="remote" :totalRows="C.table.totalRows"
                styleClass="vgt-table condensed" theme="nocturnal" :columns="C.table.columns" :rows="C.table.rows"
                @on-page-change="onPageChangeC" @on-per-page-change="onPerPageChangeC"/>
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
                isSimulating: false,
                A: {
                    table: {
                        isLoading: false,
                        totalRows: 0,
                        currentPage: 1,
                        perPage: 10,
                        columns: [
                            {
                                label: 'Semana',
                                field: 'semana',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'RND',
                                field: 'rnd',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Estado',
                                field: 'estado',
                                type: 'text',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Ingresos',
                                field: 'ingresos',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Reparacion',
                                field: 'reparacion',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Sem sin rep',
                                field: 'semanasSinReparacion',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Cant. Rep',
                                field: 'cantidadReparaciones',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Balance',
                                field: 'balance',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            }
                        ],
                        rows: [],
                        balance: 0
                    },
                },
                B: {
                    table: {
                        isLoading: false,
                        totalRows: 0,
                        currentPage: 1,
                        perPage: 10,
                        columns: [
                            {
                                label: 'Semana',
                                field: 'semana',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'RND',
                                field: 'rnd',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Estado',
                                field: 'estado',
                                type: 'text',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Ingresos',
                                field: 'ingresos',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Reparacion',
                                field: 'reparacion',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Sem sin rep',
                                field: 'semanasSinReparacion',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Cant. Rep',
                                field: 'cantidadReparaciones',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Balance',
                                field: 'balance',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            }
                        ],
                        rows: [],
                        balance: 0
                    },
                },
                C: {
                    table: {
                        isLoading: false,
                        totalRows: 0,
                        currentPage: 1,
                        perPage: 10,
                        columns: [
                            {
                                label: 'Semana',
                                field: 'semana',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'RND',
                                field: 'rnd',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Estado',
                                field: 'estado',
                                type: 'text',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Ingresos',
                                field: 'ingresos',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Reparacion',
                                field: 'reparacion',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Sem sin rep',
                                field: 'semanasSinReparacion',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Cant. Rep',
                                field: 'cantidadReparaciones',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            },
                            {
                                label: 'Balance',
                                field: 'balance',
                                type: 'number',
                                sortable: false,
                                tdClass: 'text-center'
                            }
                        ],
                        rows: [],
                        balance: 0
                    },
                },
                simulation: {
                    semanas: 1000,
                    costoReparacion: 480,
                    ingresosExcelente: 1300,
                    ingresosBueno: 650,
                    ingresosMalo: 200
                }
            };
        },
        validations: {
            simulation: {
                semanas: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                costoReparacion: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                ingresosExcelente: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(1),
                    numeric,
                    integer
                },
                ingresosBueno: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(0),
                    numeric,
                    integer
                },
                ingresosMalo: {
                    required,
                    maxValue: maxValue(100000),
                    minValue: minValue(0),
                    numeric,
                    integer
                }
            }
        },
        methods: {
            simulate() {
                this.A.table.rows = [];
                this.A.table.currentPage = 1;
                this.A.table.balance = 0;
                this.B.table.rows = [];
                this.B.table.currentPage = 1;
                this.B.table.balance = 0;
                this.C.table.rows = [];
                this.C.table.currentPage = 1;
                this.C.table.balance = 0;
                this.$v.$touch()
                if (this.$v.$invalid) {
                    alert('Uno de los campos no contiene valores correctos')
                } else {
                    this.isSimulating = true;
                    axios.post('/simulate', this.simulation)
                    .then((response) => {
                        this.A.table.balance = response.data.balancePoliticaA;
                        this.B.table.balance = response.data.balancePoliticaB;
                        this.C.table.balance = response.data.balancePoliticaC;
                        this.getIteracionPoliticaA(this.A.table.currentPage, this.A.table.perPage)
                        this.getIteracionPoliticaB(this.B.table.currentPage, this.B.table.perPage)
                        this.getIteracionPoliticaC(this.C.table.currentPage, this.C.table.perPage)
                    })
                    .catch((result) => alert(result.response.data.message))
                    .finally(() => this.isSimulating = false);
                }
            },
            onPageChangeA(params) {
                this.A.table.currentPage = params.currentPage;
                this.A.table.perPage = params.currentPerPage;
                this.getIteracionPoliticaA(params.currentPage, params.currentPerPage)
            },
            onPerPageChangeA(params) {
                this.A.table.perPage = params.currentPerPage;
            },
            onPageChangeB(params) {
                this.B.table.currentPage = params.currentPage;
                this.B.table.perPage = params.currentPerPage;
                this.getIteracionPoliticaB(params.currentPage, params.currentPerPage)
            },
            onPerPageChangeB(params) {
                this.B.table.perPage = params.currentPerPage;
            },
            onPageChangeC(params) {
                this.C.table.currentPage = params.currentPage;
                this.C.table.perPage = params.currentPerPage;
                this.getIteracionPoliticaC(params.currentPage, params.currentPerPage)
            },
            onPerPageChangeC(params) {
                this.C.table.perPage = params.currentPerPage;
            },
            getIteracionPoliticaA(page, size) {
                this.A.table.rows = [];
                this.A.table.isLoading = true;
                return axios.get('/iterations?page=' + (page - 1) + '&size=' + size + '&politica=A'
                ).then(response => {
                    this.A.table.rows = this.A.table.rows.concat(response.data.iteraciones);
                    this.A.table.totalRows = response.data.totalRows;
                    this.A.table.isLoading = false;
                }).catch(function (result) {
                    alert(result.data.message)
                })
            },
            getIteracionPoliticaB(page, size) {
                this.B.table.rows = [];
                this.B.table.isLoading = true;
                return axios.get('/iterations?page=' + (page - 1) + '&size=' + size + '&politica=B'
                ).then(response => {
                    this.B.table.rows = this.B.table.rows.concat(response.data.iteraciones);
                    this.B.table.totalRows = response.data.totalRows;
                    this.B.table.isLoading = false;
                }).catch(function (result) {
                    alert(result.data.message)
                })
            },
            getIteracionPoliticaC(page, size) {
                this.C.table.rows = [];
                this.C.table.isLoading = true;
                return axios.get('/iterations?page=' + (page - 1) + '&size=' + size + '&politica=C'
                ).then(response => {
                    this.C.table.rows = this.C.table.rows.concat(response.data.iteraciones);
                    this.C.table.totalRows = response.data.totalRows;
                    this.C.table.isLoading = false;
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
