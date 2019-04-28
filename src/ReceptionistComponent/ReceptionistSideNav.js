import React, {Component} from 'react'
import { BrowserRouter, Route, Switch,Redirect } from 'react-router-dom'




class ReceptionistSideNav extends Component{
  state ={
    logout:false
  }
  onLogout(){
       localStorage.removeItem("token")
      this.setState({   
         logout: "logout"
      })
    }

  render(){
    return(


        <ul id="slide-out" className="sidenav">
        <li><div className="user-view">
        <div className="background">
        
        </div>
     
        </div></li>
        <li><a href="/add_patient"><i className="material-icons">add</i>Add Patient</a></li>
        <li><div className="divider"></div></li>
        <li><a href="/book_appointment"><i className="material-icons">book</i>Book Appointment</a></li>
         <li><div className="divider"></div></li>
        <li><a href= "view_appointment"><i className="material-icons">view_module</i>View Appointment</a></li>
        <li><div className="divider"></div></li>
        <li><a href= "view_patient"><i className="material-icons">view_module</i>View Patient</a></li>
        <li><div className="divider"></div></li>
        <li onClick={this.onLogout.bind(this)}><a href= ""><i className="material-icons">lock_outline</i>Logout</a></li>
       
        <li><div className="divider"></div></li>
        
        

          {this.state.logout === "logout" ?  <Redirect to={{pathname: '/login'}} /> : null }
      </ul>
 
   
           )
  }

}

export default ReceptionistSideNav