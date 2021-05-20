import axios from 'axios'

const RESERVATIONS_REST_API_URL = 'http://localhost:8080/api/getReservations';

class ReservationService{
    getReservations(){
        return axios.get(RESERVATIONS_REST_API_URL);
    }
}

export default new ReservationService();