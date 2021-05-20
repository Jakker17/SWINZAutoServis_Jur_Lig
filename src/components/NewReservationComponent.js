import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import Select from 'react-select';
import axios from 'axios';
import { render } from '@testing-library/react';
import SuccessAlert from './SuccessAlert';
import ErrorAlert from './ErrorAlert';

const initialState = {                
    customerName: '',
customerSurname: '',
licensePlate: '',
dateOfReservation: '',
phoneNumber: '',           
timeOfReservation: '11:00',
}

class NewReservationComponent extends React.Component{
    constructor(props){
        super(props);
        this.state={
                customerName: '',
                customerSurname: '',
                licensePlate: '',
                dateOfReservation: '',
                phoneNumber: '',           
                timeOfReservation: '11:00',
                showAlert:false,
                success:true,
                message:'',
                validationError:''
        };
    }

    changeHangler = e =>{
        this.setState({[e.target.name]: e.target.value})
    }

    validate () {
        let validationError = "";
        if(this.state.customerName==""){validationError="Customer Name cannot be empty"}
        else if(this.state.customerSurname==""){validationError="Customer Surname cannot be empty"}
        else if(this.state.licensePlate==""){validationError="License plate cannot be empty"}
        else if(this.state.phoneNumber==""){validationError="Phone number cannot be empty"}
        else if(this.state.dateOfReservation==""){validationError="Date of reservation cannot be empty"}
        else if(/[^a-zA-Z -]/.test(this.state.customerName)){validationError="Invalid characters for name."}
        else if(/[^a-zA-Z -]/.test(this.state.customerSurname)){validationError="Invalid characters for surname."}
        else if(this.state.customerName.length>20){validationError="Customer name cannot be longer then 20 characters."}
        else if(this.state.customerSurname.length>20){validationError="Customer surname cannot be longer then 20 characters."}
        else if(this.state.phoneNumber.length>9){validationError="Phone cannot be longer then 9 characters."}
        else if(this.state.licensePlate.length>7){validationError="License plate cannot be longer then 7 characters."}

        if(validationError){this.setState({validationError:validationError,showAlert:true,success:false});
        return false;
        }

        return true;
    }
  
    submitHandler = e =>{
        e.preventDefault()
        const isValid = this.validate();
        console.log(this.state)
        console.log(this.state.validationError)
        if(isValid){
        axios.post('http://localhost:8080/api/addReservation',{customerName:this.state.customerName,customerSurname:this.state.customerSurname,licensePlate:this.state.licensePlate,
        dateOfReservation:this.state.dateOfReservation,phoneNumber:this.state.phoneNumber,timeOfReservation:this.state.timeOfReservation})
        .then(response =>{console.log(response);
            this.setState({showAlert:true,success:true, message:"Successfull"})
            this.setState(initialState);})
        .catch(error=> {console.log(error.response.status)
            if(error.response.status==400){this.setState({showAlert:true,success:false,message:"Error Ocured."})}
            if(error.response.status ==409){
                this.setState({showAlert:true,success:false, message:"No more then Two reservations at the same date and time"})
            }
            console.log(this.state.message)});

        }
    }

    renderAlerts(){
        if(this.state.showAlert){
            if(this.state.success){
                return <SuccessAlert/>
            }
            else if(this.state.validationError)
            {
                return <ErrorAlert message={this.state.validationError}/>
            }
            else{
                return <ErrorAlert message={this.state.message}/>
            }
        }
    }


    render(){
        const {customerName,customerSurname,licensePlate,dateOfReservation,phoneNumber,timeOfReservation} = this.state

        return(
            <div>
                {this.renderAlerts()}
                <h2>Please Enter Reservation Details</h2>
                <form onSubmit={this.submitHandler}>
                {this.alert && <h2> Submit Successful</h2>}
                <div className="mb-3">        
                    <label className="form-label">Customer name : 
                    <input type="text"  className="form-control" value={customerName} name="customerName" onChange={this.changeHangler}></input> 
                    </label>
                    <label className="form-label">Customer surname : 
                    <input type="text"  className="form-control" value={customerSurname} name="customerSurname" onChange={this.changeHangler}></input>
                    </label>
                </div>
                <div className="mb-3">
                    <label className="form-label">License plate : 
                    <input type="text"  className="form-control" value={licensePlate} name="licensePlate" onChange={this.changeHangler}></input>
                    </label>
                    <label className="form-label">Phone number : 
                    <input type="text"  className="form-control" value={phoneNumber} name="phoneNumber" onChange={this.changeHangler} pattern="[0-9]*"></input>
                    </label>       
                </div> 
                <div className="mb-3" center>
                        <label className="form-label">Reservation date: 
                        <input type="date"  className="form-control" value={dateOfReservation} name="dateOfReservation" onChange={this.changeHangler}></input> 
                        </label>
                        <label className="form-label">Reservation time: <select value={timeOfReservation} name="timeOfReservation" onChange={this.changeHangler} className="form-control">
                            <option value="10:00">10:00</option>
                            <option value="11:00">11:00</option>
                            <option value="12:00">12:00</option>
                            <option value="13:00">13:00</option>
                            <option value="14:00">14:00</option>
                            <option value="15:00">15:00</option>
                            <option value="16:00">16:00</option>
                            <option value="17:00">17:00</option>
                            <option value="18:00">18:00</option>
                             </select>
                        </label>
                </div>
                <button type="submit" className="btn btn-primary">Add</button>
                </form>
            </div>
        )
    }
}

export default NewReservationComponent