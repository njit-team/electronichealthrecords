import React, {Component} from 'react';
import ReceptionistNav from './ReceptionistNav'
import PatientDetailsComponent from './PatientDetailsComponent'
import axios from 'axios';


class ViewPatient extends Component{
   state = {
    patient: [ ]
  }

  componentWillMount(){
    if(localStorage.getItem("token")){
      axios.get(`http://localhost:8081/token/role`,                 
        {headers: {
                     'Authorization': 'Bearer '+localStorage.getItem("token")                  
                }})
      .then(res=>{
        console.log(res)
        if(res.data[0]!=="ROLE_RECEPTIONIST"){
          this.props.history.push("/login")
        }
       
      },err=>{
      })
    }else{
      this.props.history.push("/login")
    }
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
  handleModalClose() {
    this.setState({ activeModal: false, activePatientModal: false,  });
  }

  componentDidMount(){
    axios.get('http://localhost:8081/patient/get/all',{headers: {
        'Authorization': 'Bearer '+localStorage.getItem("token")
                    }})
         .then(res=>{
          console.log(res)
          this.setState({
            patient: res.data
          })
         })
  }
  
  render(){
     const { patient } = this.state;
     console.log(patient)
   
    
            const staffList = patient.length ? ( patient.map(patient =>{
                return(
                     <tr key = {patient.staffId}>
                    <td>{patient.account.name.firstName} {patient.account.name.lastName}</td>
                   <td>{patient.patientId}</td>
                   <td>{patient.account.gender}</td>
                       </tr>

                       )

            }) ): ( <div className="center">Loading....</div>)
      
      return(
    <div className="">
     <ReceptionistNav handlekeyPress={this.handlekeyPress.bind(this)}/>
    <table>
        <thead>
          <tr>
              <th data-field="id">Full Name</th>
              <th data-field="name"> PatientId</th>
              <th data-field="price">Gender</th>
          </tr>
        </thead>

        <tbody>
         {staffList}
        </tbody>
      </table>
      {this.state.activePatientModal === "patientModal" ? <PatientDetailsComponent patientId={this.state.patientId} handleModalClose={this.handleModalClose.bind(this)} /> : null}
     </div>

    )
  }
}



export default ViewPatient

