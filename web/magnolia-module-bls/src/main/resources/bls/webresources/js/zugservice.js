var home = new Vue({
    el: '#home',
    data: {
        layout: 'home',
        lastName: '',
        firstName: '',
        birthDate: '',
        time: '',
        from: '',
        to: '',
        //Rules for the field validations
        rules: {
            name: [
                {required: true, message: 'Bitte tragen sie Ihren Nachnamen ein!', trigger: 'blur'},
                {min: 0, max: 30, message: 'Es sind nur 30 Zeichen erlaubt!', trigger: 'blur'}
            ]
        }
    },
    methods: {
        getZugservices(e) {
            e.preventDefault();
            axios.post(location.protocol + '//' + location.host + '/.rest/demo/v1/zugservices', {
                time: this.correctTimeFormat(this.time),
                from: this.from,
                to: this.to
            })
                .then(response => (blog_list.zugservices = response.data))
                .catch(error => console.log(error));
            this.layout = '';
            blog_list.layout = 'list';
        },
        //Converts entered time into correct format for the request
        correctTimeFormat(enteredTime) {
            console.log(enteredTime.getMinutes())
            var correctTimeDisplay = enteredTime.getHours() + ':' + enteredTime.getMinutes();
            if (enteredTime.getHours() < 10) {
                return '0' + correctTimeDisplay;
            } else {
                return correctTimeDisplay;
            }
        }
    }
});

var blog_list = new Vue({
        el: '#js-grid-list',
        data: {
            layout: '',
            allowDateCheck: true,
            zugserviceId: '',
            zugservices: '',
            reservationStatus: ''
        },
        methods: {
            showZugserviceDetail(uuid) {
                this.layout = 'zugservice-detail';
                this.zugserviceId = uuid;
                console.log("Zugservicedetails line clicked, uuid: " + uuid);
            },
            showSeatDetail() {
                console.log("showSeatDetail line clicked");
            },
            requestReservation(zugUuid, seatId, waggonNumber, from, to) {
                console.log("reservation requested, seat: " + seatId + ", waggon: " + waggonNumber + ", zug uuid: " + zugUuid + ", from: " + from + ", to: " + to);
                axios.post(location.protocol + '//' + location.host + '/.rest/demo/v1/reservation', {
                    firstname: home.firstName,
                    lastname: home.lastName,
                    dateOfBirth: home.birthDate.getFullYear() + '-' + home.birthDate.getMonth() + '-' + home.birthDate.getDate(),
                    zugserviceID: zugUuid,
                    wagenNumber: waggonNumber,
                    sitzNumber: seatId,
                    departure: from,
                    destination: to
                })
                    .then(response => {
                        this.reservationStatus = response.data;
                        let resConf = this.reservationStatus;
                        if (resConf.message == 'OK') {
                            let zugId = resConf.zugserviceID;
                            let wagNb = resConf.wagenNumber;
                            let sitzNb = resConf.sitzNumber;
                            let seat = this.getSeat(zugId, wagNb, sitzNb);
                            if (seat !== undefined) {
                                seat.reserved = true;
                                //Shows confirmation of reservation
                                var confirmation = this.reservationConfirmation(home.firstName, home.lastName,
                                    this.reservationStatus.departure, this.reservationStatus.destination, wagNb, sitzNb);
                                Swal.fire({
                                    title: 'Reservation bestätigt.',
                                    html: confirmation,
                                    imageUrl: location.protocol + '//' + location.host + '/.resources/bls/webresources/img/Test_QR_Code.png',
                                    customClass: 'reservation-confirmation',
                                    showConfirmButton: false,
                                    showCloseButton: true,
                                    icon: 'success'
                                })
                            }
                        }
                    })
                    .catch(error => console.log(error));
            },
            backToHome() {
                home.layout = 'home';
                this.layout = '';
            },
            setReservation(zugUuid, waggonNb, seatNb) {
                let seat = this.getSeat(zugUuid, waggonNb, seatNb);
                if (seat !== undefined && seat.reserved) return 'reserved';
            },
            setDisabled(zugUuid, waggonNb, seatNb) {
                let seat = this.getSeat(zugUuid, waggonNb, seatNb);
                if (seat !== undefined && seat.reserved) return 'disabled';
            },
            getSeat(zugUuid, waggonNb, seatNb) {
                let wagenNbInt = parseInt(waggonNb);
                let seatNbInt = parseInt(seatNb);
                let trains = this.zugservices;
                for (let i = 0; i < trains.length; i++) {
                    if (trains[i].uuid === zugUuid) {
                        let waggons = trains[i].zugkomposition;
                        for (let j = 0; j < waggons.length; j++) {
                            if (parseInt(waggons[j].number) === wagenNbInt) {
                                let seats = waggons[j].wagenplan.seats;
                                for (let k = 0; k < seats.length; k++) {
                                    if (parseInt(seats[k].id) === seatNbInt) {
                                        return seats[k];
                                    }
                                }
                            }
                        }
                    }
                }
            },
            //makes a multiline text for the reservation confirmation text in the sweetalert2 confirmation window
            reservationConfirmation(fName, lName, departure, destination, wagNb, seatNb) {
                var span = document.createElement("span");
                return span.innerHTML = 'Benutzer: ' + fName + ' ' + lName + '<br>'
                    + 'Strecke: ' + this.reservationStatus.departure + '-' + this.reservationStatus.destination + '<br>'
                    + 'Wagen: ' + wagNb + ' Sitz Nr.: ' + seatNb;
            },
            // converts entered date into correct date format to save
            correctDateFormat(enteredDate) {
                return enteredDate.getFullYear() + '-' + enteredDate.getMonth() + '-' + enteredDate.getDate();
            },
            setSeparator(nextDay){
                return nextDay;
            }
        },
        computed: {
            infoRequest: function () {
                return home.from.charAt(0).toUpperCase() + home.from.slice(1) + ' - ' +
                    home.to.charAt(0).toUpperCase() + home.to.slice(1) + '  ab: ' + home.correctTimeFormat(home.time);
            },
            infoTrainDetail: function () {
                for (var i = 0; i < this.zugservices.length; i++) {
                    if (this.zugservices[i].uuid === this.zugserviceId) {
                        let zug = this.zugservices[i];
                        return zug.departure + ' ' + zug.from + ' ––––––––– ' + zug.to + ' ' + zug.arrival;
                    }
                }

            }
        }
    })
;