import React, {Component} from 'react';

import axios from 'axios';
import ReceptionistNav from './ReceptionistNav'
import ReceptionistSideNav from './ReceptionistSideNav'


class PatientDetailsComponent extends Component{
   state = {
    patient: false
  }
  componentDidMount(){

    this.searchAppointments();


  }
  searchAppointments(){
    axios.get(`http://localhost:8081/patient/find/${this.props.patientId}`,{headers: {
                       
                         'Authorization': 'Bearer '+localStorage.getItem("token") 

                    }})
             .then(res=>{
                console.log(res.data);
               this.setState({
                   patient : res.data
        })
            
              })
             
  }



  handleModalClose() {
    if(this.props.handleModalClose)
        this.props.handleModalClose();
  }

  
  render(){
   const {patient} = this.state
   console.log(patient);
      return(
        <div className="custom-modal">
          <div className="modal-container">
            <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>
            <p className="data"><span>Patient name:</span> {patient ? patient.account.name.firstName : "loading..."} </p>
            <p className="data"><span>Patient Address:</span> {patient ? patient.account.address.city : "loading..."}</p>
            <p className="data"><span>Patient DOB:</span> {patient ? patient.account.dateOfBirth : "loading..."}</p>
            <p className="data"><span>Patient phone number:</span> {patient ? patient.account.address.phoneNumber : "loading..."}</p>
            
          </div>
        </div>
      )
  }
}



export default PatientDetailsComponent

