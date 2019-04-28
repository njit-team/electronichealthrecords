import React, {Component} from 'react';
import { BrowserRouter, Route, Switch,Redirect } from 'react-router-dom'
import axios from 'axios';

class IndividualAppointmentModal extends Component{
   state = {
    appointment: [ ]
  }
  componentDidMount(){

    axios.get(`http://localhost:8081/appointment/view/${this.props.appointmentId}`,{headers: {
                   
                     'Authorization': 'Bearer '+localStorage.getItem("token") 

                }})
         .then(res=>{
          console.log(res)
          this.setState({
            appointment: res.data
          })
         })


  }

  handleModalClose() {
    if(this.props.handleModalClose)
        this.props.handleModalClose();
  }

  onremoveAppointment(){
    axios.delete(`http://localhost:8081/appointment/delete/${this.props.appointmentId}`,{headers: {                
                    'Authorization': 'Bearer '+localStorage.getItem("token")
                }})
         .then(res=>{
            if(this.props.onremoveAppointment){
              this.props.onremoveAppointment();
              this.handleModalClose();    
            }
           })

  }
  

  render(){
   const {appointment} = this.state
      return(
        <div className="custom-modal">
          <div className="modal-container">
            <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>
            <p className="data"><span>Patient name:</span> {appointment.patientId}</p>
            <p className="data"><span>Doctor name:</span> {appointment.doctorId}</p>
            <p className="data"><span>Appointment time:</span> {appointment.appointmentDateTime}</p>
            <p className="data"><span>Appointment reason:</span> {appointment.appointmentReason}</p>
            <div className="actions">
              <a class="waves-effect waves-light btn" onClick={this.onremoveAppointment.bind(this)}>remove appointment</a>
            </div>
          </div>
        </div>
      )
  }
}



export default IndividualAppointmentModal

