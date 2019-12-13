<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BLS Sitzplatzreservation</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="${ctx.contextPath}/.resources/bls/webresources/css/listview.css">
</head>
<body>
<div id="container">
    <div id="home" v-if="layout === 'home'">
        <div class="tool-bar">
            <div class="infopanel">Reservation</div>
            <div class="bls-icon"></div>
        </div>
        <el-form status-icon :rules="rules" label-width="120px" class="demo-ruleForm" size="medium">
            <el-form-item label="Name" prop="name">
                <el-input v-model="lastName" placeholder="Name"></el-input>
            </el-form-item>
            <el-form-item label="Vorname" prop="firstName">
                <el-input v-model="firstName" placeholder="Vorname"></el-input>
            </el-form-item>
            <el-form-item label="Geburtsdatum" prop="birth">
                <el-date-picker v-model="birthDate" type="date" placeholder="Pick a date"></el-date-picker>
            </el-form-item>
            <el-form-item label="Zeit" prop="startTime">
                <el-time-picker v-model="time" format="HH:mm" placeholder="Departure time"></el-time-picker>
            </el-form-item>
            <el-form-item label="Von" prop="startPlace">
                <el-input v-model="from" placeholder="Haltestelle"></el-input>
            </el-form-item>
            <el-form-item label="Nach" prop="endPlace">
                <el-input v-model="to" placeholder="Haltestelle"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="getZugservices">Suchen</el-button>
            </el-form-item>
        </el-form>
    </div>

    <section id="js-grid-list" v-if="layout === 'zugservice-detail' || layout === 'list'" class="grid-list" v-cloak>

        <div class="tool-bar">
            <div v-on:click="backToHome" class="home-btn" v-if="layout === 'list'"></div>
            <div v-on:click="layout = 'list'" class="back-icon" v-if="layout === 'zugservice-detail'"
                 title="Back"></div>
            <div v-if="layout === 'list'" class="infopanel">{{infoRequest}}</div>
            <div v-else-if="layout === 'zugservice-detail'" class="infopanel">{{infoTrainDetail}}</div>
            <div class="bls-icon"></div>
        </div>

        <div v-if="layout === 'list'" class="list">
            <template v-for="zug in zugservices">
                <div class="date-separator" v-if="setSeparator(zug.nextDay)">Zugverbindung Morgen</div>
                <div v-on:click="showZugserviceDetail(zug.uuid)" class="zugservice">
                    <p>{{zug.departure}} <span>{{zug.from}}</span> ––––––––––– <span>{{zug.to}}</span> {{zug.arrival}}
                    </p>
                </div>
            </template>
        </div>

        <div v-if="layout === 'zugservice-detail'" class="zugservice-detail">
            <template v-for="zug in zugservices">
                <template v-if="zugserviceId === zug.uuid">
                    <div v-for="waggon in zug.zugkomposition" class="waggon">
                        <p>Waggon N°: {{waggon.number}} ({{waggon.wagenplan.description}})</p>
                        <div class="waggon-image">
                            <img v-bind:src="waggon.wagenplan.imageLink">
                            <div class="row-placeholder row1"></div>
                            <div class="row-placeholder row2">
                                <button v-on:click="requestReservation(zug.uuid, 88, waggon.number, zug.from, zug.to)"
                                        id="88" type="button" class="col1"
                                        :class="setReservation(zug.uuid, waggon.number, 88)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 88)"></button>
                                <button v-on:click="requestReservation(zug.uuid, 87, waggon.number, zug.from, zug.to)"
                                        id="87" type="button" class="col2"
                                        :class="setReservation(zug.uuid, waggon.number, 87)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 87)"></button>
                                <button type="button" class="col3 invisible"></button>
                                <button type="button" class="col4 invisible"></button>
                                <button type="button" class="col5 invisible"></button>
                            </div>
                            <div class="row-placeholder row3">
                                <button v-on:click="requestReservation(zug.uuid, 84, waggon.number, zug.from, zug.to)"
                                        id="84" type="button" class="col1"
                                        :class="setReservation(zug.uuid, waggon.number, 84)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 84)"></button>
                                <button v-on:click="requestReservation(zug.uuid, 83, waggon.number, zug.from, zug.to)"
                                        id="83" type="button" class="col2"
                                        :class="setReservation(zug.uuid, waggon.number, 83)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 83)"></button>
                                <button type="button" class="col3 invisible"></button>
                                <button v-on:click="requestReservation(zug.uuid, 82, waggon.number, zug.from, zug.to)"
                                        id="82" type="button" class="col4"
                                        :class="setReservation(zug.uuid, waggon.number, 82)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 82)"></button>
                                <button v-on:click="requestReservation(zug.uuid, 81, waggon.number, zug.from, zug.to)"
                                        id="81" type="button" class="col5"
                                        :class="setReservation(zug.uuid, waggon.number, 81)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 81)"></button>
                            </div>
                            <div class="row-placeholder row4">
                                <button v-on:click="requestReservation(zug.uuid, 78, waggon.number, zug.from, zug.to)"
                                        type="button" id="78" class="col1"
                                        :class="setReservation(zug.uuid, waggon.number, 78)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 78)"></button>
                                <button v-on:click="requestReservation(zug.uuid, 77, waggon.number, zug.from, zug.to)"
                                        type="button" id="77" class="col2"
                                        :class="setReservation(zug.uuid, waggon.number, 77)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 77)"></button>
                                <button type="button" class="col3 invisible"></button>
                                <button v-on:click="requestReservation(zug.uuid, 76, waggon.number, zug.from, zug.to)"
                                        type="button" id="76" class="col4"
                                        :class="setReservation(zug.uuid, waggon.number, 76)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 76)"></button>
                                <button v-on:click="requestReservation(zug.uuid, 75, waggon.number, zug.from, zug.to)"
                                        type="button" id="75" class="col5"
                                        :class="setReservation(zug.uuid, waggon.number, 75)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 75)"></button>
                            </div>
                            <div class="row-placeholder row5">
                                <button v-on:click="requestReservation(zug.uuid, 74, waggon.number, zug.from, zug.to)"
                                        id="74" type="button" class="col1"
                                        :class="setReservation(zug.uuid, waggon.number, 74)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 74)"></button>
                                <button v-on:click="requestReservation(zug.uuid, 73, waggon.number, zug.from, zug.to)"
                                        id="73" type="button" class="col2"
                                        :class="setReservation(zug.uuid, waggon.number, 73)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 73)"></button>
                                <button type="button" class="col3 invisible"></button>
                                <button v-on:click="requestReservation(zug.uuid, 72, waggon.number, zug.from, zug.to)"
                                        id="72" type="button" class="col4"
                                        :class="setReservation(zug.uuid, waggon.number, 72)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 72)"></button>
                                <button v-on:click="requestReservation(zug.uuid, 71, waggon.number, zug.from, zug.to)"
                                        id="71" type="button" class="col5"
                                        :class="setReservation(zug.uuid, waggon.number, 71)"
                                        :disabled="setDisabled(zug.uuid, waggon.number, 71)"></button>
                            </div>
                        </div>
                    </div>
                </template>
            </template>
        </div>
    </section>
</div>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script type="module" src="${ctx.contextPath}/.resources/bls/webresources/js/zugservice.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
</body>
</html>