import React from 'react';


const ErrorAlert = (props) =>{
    return(
        <div className="alert alert-danger">
            <strong>{props.message}</strong>
        </div>
    )
}


export default ErrorAlert;