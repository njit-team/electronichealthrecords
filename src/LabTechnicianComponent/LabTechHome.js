import React, {Component} from 'react'
import { BrowserRouter, Route, Switch,Redirect } from 'react-router-dom'
import LabTechNav from './LabTechNav'
import PerformTest from './PerformTest'


class LabTechHome extends Component {
  state = {
     
     activePerformTestModal :false,
     logout:false
  }
    onPerformTest(){
      this.setState({      
         activePerformTestModal : "performTestModal",
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
    this.setState({activePerformTestModal: false});
  }
    
  render(){
    return(
      <div className="">
        <LabTechNav/>
        <div className="dialog">

          <button className=" trigger-btn">What do you want to do?</button>
          <ul className="options-list">
           <li className="option" onClick={this.onPerformTest.bind(this)}  >Perform Test</li>       
            <li className="option"  onClick={this.onLogout.bind(this)}>Logout</li>
          </ul>
      </div>
       {this.state.activePerformTestModal === "performTestModal" ? <PerformTest    patientId = {this.props.patientId}   handleModalClose={this.handleModalClose.bind(this)} /> : null}
      {this.state.logout === "logout" ?  <Redirect to={{pathname: '/login'}} /> : null }
      </div>
        
      );
  }
}

export default LabTechHome