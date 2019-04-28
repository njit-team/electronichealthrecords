import React, {Component} from 'react'
import NavBar from './NavBar'
import { BrowserRouter, Route, Switch,Redirect } from 'react-router-dom'
import ViewPrescription from './ViewPrescription'
import ViewLabResult from './ViewLabResult'
import ViewHistory from './ViewMedicalHistory'
import ViewComments from './ViewComments'
import axios from 'axios'

class Home extends Component {
  state = {
     activePrescriptionModal : false,
     activeViewLabResultModal: false,
     activeViewHistoryModal: false,
     activeCommentsModal: false,
     logout: false,
  }
  componentWillMount(){
    if(localStorage.getItem("token")){
      axios.get(`http://localhost:8081/token/role`,                 
        {headers: {
                     'Authorization': 'Bearer '+localStorage.getItem("token")                  
                }})
      .then(res=>{
        console.log(res)
        if(res.data[0]!=="ROLE_PATIENT"){
          this.props.history.push("/login")
        }
       
      },err=>{
      })
    }else{
      this.props.history.push("/login")
    }
  }
    onClickResult(){
      this.setState({      
         activeViewLabResultModal : "ViewLabResultModal",
        //selectedPrescription: id
      })
    }
    onClickPrescription(){
      this.setState({
           activePrescriptionModal : "ViewPrescriptionModal",   
        //selectedPrescription: id
      })
    }
    onClickHistory(){
      this.setState({
           activeViewHistoryModal : "ViewHistoryModal", 
        //selectedPrescription: id
      })
    }
    onClickComments(){
      this.setState({
           activeCommentsModal : "CommentsModal", 
        //selectedPrescription: id
      })
    }

  handleModalClose() {
    this.setState({ activePrescriptionModal: false, activeViewLabResultModal: false, activeViewHistoryModal: false, activeCommentsModal: false });
  }
  onLogout(){
       localStorage.removeItem("token")
      this.setState({   
         logout: "logout"
      })
    }
    
  render(){
    return(
      <div className="">
        <NavBar/>
        <div className="dialog">

          <button className=" trigger-btn">What do you want to do?</button>
          <ul className="options-list">
           <li className="option" onClick={this.onClickHistory.bind(this)}  >View Medical History</li>
            <li className="option"  onClick={this.onClickResult.bind(this)}>View Lab Result</li>
            <li className="option"  onClick={this.onClickPrescription.bind(this)}>View Prescriptions</li>
            <li className="option"  onClick={this.onClickComments.bind(this)}>View Comments</li>
            <li className="option" onClick={this.onLogout.bind(this)}>Logout</li>
          </ul>
      </div>
      {this.state.activePrescriptionModal === "ViewPrescriptionModal" ? <ViewPrescription ID = {this.props.location.state.ID} handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.activeViewLabResultModal === "ViewLabResultModal" ? <ViewLabResult   ID = {this.props.location.state.ID}    handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.activeViewHistoryModal === "ViewHistoryModal" ? <ViewHistory    ID = {this.props.location.state.ID}         handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.activeCommentsModal === "CommentsModal" ? <ViewComments   ID = {this.props.location.state.ID}         handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.logout === "logout" ?  <Redirect to={{pathname: '/login'}} /> : null }
      </div>
        
      );
  }
}

export default Home