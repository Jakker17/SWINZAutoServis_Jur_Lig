import React from 'react';
import ReservationService from '../services/ReservationService';

class ReservationComponent extends React.Component{

    constructor(props){
        super(props)
        this.state= {
           reservations:[]
        }
    }

    componentDidMount(){
        ReservationService.getReservations().then((response) => {
            console.log(response)
            this.setState({ reservations: response.data.reservations})
        });
    }

    render(){
        return(
            <div>
                <h1 className= "text-center">Reservations List</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>

                            <td>  Id</td>
                            <td>  First Name</td>
                            <td>  Last Name</td>
                            <td>  Phone number</td>
                            <td>  License plate</td>
                            <td>  Date</td>
                            <td>  Time</td>
                        </tr>

                    </thead>
                    <tbody>
                        {
                            this.state.reservations.map(
                                reservation => 
                                <tr key = {reservation.id}>
                                     <td> {reservation.id}</td>   
                                     <td> {reservation.customerName}</td>   
                                     <td> {reservation.customerSurname}</td>   
                                     <td> {reservation.phoneNumber}</td>   
                                     <td> {reservation.licensePlate}</td>
                                     <td> {reservation.dateOfReservation}</td> 
                                     <td> {reservation.timeOfReservation}</td>  
                                </tr>
                            )
                        }

                    </tbody>
                </table>
            </div>
        )
    }

}


export default ReservationComponent