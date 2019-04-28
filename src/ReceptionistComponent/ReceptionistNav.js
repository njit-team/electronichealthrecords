import React, {Component} from 'react'
import M from "materialize-css";
import ViewAppointment from './ViewAppointment'
import { BrowserRouter, Route, Switch,Redirect } from 'react-router-dom'

class ReceptionistNav extends Component {
  
  state ={
    logout:false
  }
  onLogout(){
       localStorage.removeItem("token")
      this.setState({   
         logout: "logout"
      })
    }


    handleKeyPress(e){
      if(this.props.handlekeyPress){
        this.props.handlekeyPress(e);
       } 
    }

    
    
  render(){
    

    return(
     

<nav className = "top-bar">
<div class="nav-wrapper">
  <a href="#" class="brand-logo right">Logo</a>
  <ul id="nav-mobile" class="left hide-on-med-and-down">
  <li><a href="/add_patient">Add Patient</a></li>
        <li><a href="/book_appointment">Book Appointment</a></li>
        <li><a href= "view_appointment">View Appointment</a></li>
        <li><a href= "view_patient">View Patient</a></li>
        <li onClick={this.onLogout.bind(this)}><a href= "">Logout</a></li>
        {this.state.logout === "logout" ?  <Redirect to={{pathname: '/login'}} /> : null }
  </ul>
  <ul className= "right">
        
       <li>
        <nav>
       <div className="nav-wrapper">
      <form>
        <div className="input-field top-bar">
          <input id="search" type="search" placeholder = "Search Patient" onKeyDown={this.handleKeyPress.bind(this)}  handleKeyPress={this.handleKeyPress.bind(this)} required/>
          <label className="label-icon" for="search"><i className="material-icons">search</i></label>
          <i className="material-icons">close</i>
        </div>
      </form>
    </div>
  </nav>
      </li>
   
 
      </ul>
</div>
</nav>


    
           );
  }
}



export default ReceptionistNav;