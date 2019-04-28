import React, {Component} from 'react'
import {Redirect } from 'react-router-dom'
import DoctorNav from './DoctorNav'
import PrescribeTest from './PrescribeTest'
import ViewLabResult from './ViewLabResult'
import ViewMedicalHistory from './ViewMedicalHistory'
import PrescribeDrugs from './PrescribeDrugs'
import Comment from './Comment'
import axios from 'axios'

class AdministerTreatment extends Component {
  state = {
     activePrescribeTestModal : false,
     activeViewLabResultModal: false,
     activePrescribeDrugModal: false,
     activeHistoryModal :false,
     activeCommentsModal :false,
     logout:false
  }
  componentWillMount(){
    if(localStorage.getItem("token")){
      axios.get(`http://localhost:8081/token/role`,                 
        {headers: {
                     'Authorization': 'Bearer '+localStorage.getItem("token")                  
                }})
      .then(res=>{
        console.log(res)
        if(res.data[0]!=="ROLE_DOCTOR"){
          this.props.history.push("/login")
        }
       
      },err=>{
      })
    }else{
      this.props.history.push("/login")
    }
  }
    onClickHistory(){
      this.setState({      
         activeHistoryModal : "ViewHistoryModal",
        //selectedPrescription: id
      })
    }
    onClickResult(){
      this.setState({      
         activeViewLabResultModal : "ViewLabResultModal",
        //selectedPrescription: id
      })
    }
    onClickPrescribeTest(){
      this.setState({
           activePrescribeTestModal : "PrescribeTestModal",   
        //selectedPrescription: id
      })
    }
    onClickPrescribeDrug(){
      this.setState({
           activePrescribeDrugModal : "PrescribeDrugModal", 
        //selectedPrescription: id
      })
    }

    onComments(){
      this.setState({
           activeCommentsModal : "CommentsModal", 
        //selectedPrescription: id
      })
    }

    onLogout(){
       localStorage.removeItem("token")
      this.setState({   
         logout: "logout"
      })
    }

  handleModalClose() {
    this.setState({ activePrescribeTestModal: false, activeViewLabResultModal: false, activePrescribeDrugModal: false, activeHistoryModal: false,activeCommentsModal: false  });
  }
    
  render(){
    return(
      <div className="">
        <DoctorNav/>
        <div className="dialog">

          <button className=" trigger-btn">What do you want to do?</button>
          <ul className="options-list">
           <li className="option" onClick={this.onClickPrescribeDrug.bind(this)}  >Prescribe Drugs</li>
            <li className="option"  onClick={this.onClickPrescribeTest.bind(this)}>Prescribe Test</li>
            <li className="option"  onClick={this.onClickHistory.bind(this)}>View medical History</li>
            <li className="option"  onClick={this.onClickResult.bind(this)}>View Test Result</li>
            <li className="option"  onClick={this.onComments.bind(this)}>Comment</li>
            <li className="option"  onClick={this.onLogout.bind(this)}>Logout</li>
          </ul>
      </div>
       {this.state.activePrescribeTestModal === "PrescribeTestModal" ? <PrescribeTest  patientId = {this.props.patientId} handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.activePrescribeDrugModal === "PrescribeDrugModal" ? <PrescribeDrugs  patientId = {this.props.patientId} handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.activeViewLabResultModal === "ViewLabResultModal" ? <ViewLabResult   patientId = {this.props.patientId}     handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.activeHistoryModal === "ViewHistoryModal" ? <ViewMedicalHistory      patientId = {this.props.patientId}          handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.activeCommentsModal === "CommentsModal" ? <Comment      patientId = {this.props.patientId}          handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.logout === "logout" ?  <Redirect to={{pathname: '/login'}} /> : null }
      </div>
      
      );
  }
}

export default AdministerTreatment