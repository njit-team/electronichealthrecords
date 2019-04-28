import React, { Component } from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import AdminNav from './AdminComponent/AdminNav'
import AdminHome from './AdminComponent/AdminHome'
import AddStaff    from  './AdminComponent/AddStaff';
import AdminFooter from  './AdminComponent/AdminFooter';
import SideNav   from  './AdminComponent/SideNav';
import Receptionist from './ReceptionistComponent/Receptionist'
import ViewStaff from './AdminComponent/ViewStaff'
import ViewAppointment from './ReceptionistComponent/ViewAppointment'
import AddPatient from './ReceptionistComponent/AddPatient'
import SignUp from './AuthComponent/SignUp'
import Login from './AuthComponent/Login'
import Home from './PatientComponent/Home'
import DoctorHome from './DoctorComponent/DoctorHome'
import AdministerTreatment from './DoctorComponent/AdministerTreatment'
import LabTechHome from './LabTechnicianComponent/LabTechHome'
import ViewPatient from './ReceptionistComponent/ViewPatient'



class App extends Component {
  
  render() {
    return (
       <BrowserRouter>
      <div className="todo-app ">
    
     <body>      
    <header>
     
    <Switch>
    <Route exact path = "/" component = {AddStaff}/>
    <Route exact path = "/admin" component = {AdminHome}/>
    <Route path = "/view_staff" component = {ViewStaff} />
    <Route path = "/view_patient" component = {ViewPatient} />
    <Route path = "/receptionist" component = {Receptionist} />
    <Route path = "/view_appointment" component = {ViewAppointment} />
    <Route path = "/book_appointment" component = {Receptionist} />
    <Route path = "/add_patient" component = {AddPatient} />
    <Route path = "/sign_up" component = {SignUp} />
    <Route path = "/login" component = {Login} />
    <Route path = "/patient" component = {Home} />
    <Route path = "/doctor" component = {DoctorHome} />
    <Route path = "/treatment" component = {AdministerTreatment} />
    <Route path = "/lab_technician" component = {LabTechHome} />


    
    </Switch>
    </header>
    <main>
   
        
    </main>
    <footer className="page-footer top-bar">
      <AdminFooter/>
    </footer>
     </body>
   
        
      </div>

       </BrowserRouter>

    );
  }
}

export default App;
