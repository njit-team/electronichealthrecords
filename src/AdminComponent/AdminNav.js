import React, {Component} from 'react'
import M from "materialize-css";
import { BrowserRouter, Route, Switch,Redirect } from 'react-router-dom'

class AdminNav extends Component {
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

      
      <nav className = "top-bar">
      <div class="container nav-wrapper">
       
        <ul id="nav-mobile" class="left hide-on-med-and-down">
        <li><a href="/">Home</a></li>
              <li><a href="/add_staff">Add Staff</a></li>
              <li><a href= "view_staff">View Staff</a></li>
              <li onClick={this.onLogout.bind(this)}><a href= "">Logout</a></li>
              {this.state.logout === "logout" ?  <Redirect to={{pathname: '/login'}} /> : null }
        </ul>
        <ul className= "right">
              
            <li>
              <nav>
            <div className="nav-wrapper">
            <form>
              <div className="input-field top-bar">
                <input id="search" type="search" placeholder = "Search Patient"  required/>
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

export default AdminNav;