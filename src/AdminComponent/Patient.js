import React, {Component} from 'react'
import axios from 'axios'

class Patient extends Component {
  state = {
    patients: [ ]
  }
  componentDidMount(){
    axios.get('http://localhost:8081/healthcare/getPatient')
         .then(res=>{
          console.log(res)
          this.setState({
            patients: res.data.slice(41,42)
          })
         })
  }
  render() {
    const { patients } = this.state;
   console.log(patients)
   console.log(patients.length)
    
    const patientList = patients.length ? (
        patients.map(patient =>{
          return (
            <div className="patient card" key={patient.id} >
            <div className="card-content">
            <span className="card-title">{patient.account.name.firstName} {patient.account.name.lastName} </span>
            </div>
            </div>
            )
        })
            )  : (
     <div className="center">No patient yet</div>
     )
    return (
      <div className="container">
      <div className="center blue-text">Patient </div>
      {patientList}
      </div>
    );
  }
}

export default Patient