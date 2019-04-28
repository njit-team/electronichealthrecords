import React, {Component} from 'react';

import axios from 'axios';
import ReceptionistNav from './ReceptionistNav'
import IndividualAppointmentModal from './IndividualAppointmentModal'
import PatientDetailsComponent from './PatientDetailsComponent'
import M from "materialize-css";


class ViewAppointment extends Component{
   state = {
    appointment: [ ],
    activeModal : false,
    selectedAppointent: '',
    activePatientModal: false,
    patientId: ''
  }
  componentWillMount(){
    if(localStorage.getItem("token")){
      axios.get(`http://localhost:8081/token/role`,                 
        {headers: {
                     'Authorization': 'Bearer '+localStorage.getItem("token")                  
                }})
      .then(res=>{
        if(res.data[0]!=="ROLE_RECEPTIONIST"){
          this.props.history.push("/login")
        }
       
      },err=>{
      })
    }else{
      this.props.history.push("/login")
    }
  }
  componentDidMount(){

    this.getAppointments()

  }

  getAppointments(){
      axios.get('http://localhost:8081/appointment/view/all',{headers: {
                       
                         'Authorization': 'Bearer '+localStorage.getItem("token") 
                    }})
             .then(res=>{
              console.log(res)
              this.setState({
                appointment: res.data
              })
             })
  }

   onClick(id){
      this.setState({
        activeModal : "appointmentModal",
        selectedAppointent: id
      })
    }
      
  handlekeyPress(e){
     if(e.key==='Enter'){
      e.preventDefault();
       this.setState({
       activePatientModal : "patientModal", 
       patientId: e.target.value   
       })
     }
   }  

     
   onremoveAppointment(){
    this.getAppointments()
   }
  

  
  handleModalClose() {
    this.setState({ activeModal: false, activePatientModal: false });
  }
  
  render(){
     const { appointment } = this.state;
   
     const appointmentList = appointment.length ? ( appointment.map(appointment =>{
        return(
        <TableRow key={appointment.appointmentId} keyy={appointment.appointmentId} onClick={this.onClick.bind(this)} patientId={appointment.patientId} doctorId={appointment.doctorId} appointmentReason={appointment.appointmentReason} />
        )
        }) ): ( <div className="center">No appointments yet</div>)
      
      return(
        <div className="">
         <ReceptionistNav handlekeyPress={this.handlekeyPress.bind(this)}/>
            <table >
              <thead>
                <tr>
                    <th data-field="id">Patient Name</th>
                    <th data-field="name">Doctor Name</th>
                    <th data-field="price">Reason</th>
                </tr>
              </thead>

                  <tbody className="options-list">
                 
                    {appointmentList}
                 
                  </tbody>
            </table>
      {this.state.activeModal === "appointmentModal" ? <IndividualAppointmentModal  appointmentId={this.state.selectedAppointent}  onremoveAppointment = {this.onremoveAppointment.bind(this)} handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.activePatientModal === "patientModal" ? <PatientDetailsComponent patientId={this.state.patientId} handleModalClose={this.handleModalClose.bind(this)} /> : null}
      </div>
    )
  }
}

class TableRow extends Component {
  onClick() {
    if(this.props.onClick)
        this.props.onClick(this.props.keyy);
  }

  render() {
    return(
      <tr className="option  " onClick={this.onClick.bind(this)} >
         <td>{this.props.patientId} </td>
         <td>{this.props.doctorId}</td>
         <td>{this.props.appointmentReason}</td>
      </tr>
    )
  }
}



export default ViewAppointment 

