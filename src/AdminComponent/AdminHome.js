import React, { Component } from 'react';
import { NavLink } from "react-router-dom";
import AdminNav from './AdminNav'
import axios from 'axios'
class AdminHome extends Component {

    constructor(props) {
        super(props);
        this.state = { staff:[],patientcount:[],doctorcount:[],receptionistcount:[],};
    
    }
    componentDidMount(){
        axios.get('http://localhost:8081/staff/getStaff',{headers: {
    
                        }})
             .then(res=>{
              console.log(res)
              this.setState({
                staff: res.data
              })
         })
         axios.get('http://localhost:8081/patient/count',{headers: {
    
        }})
            .then(res=>{
            console.log(res)
            this.setState({
           patientcount: res.data
             })
        })

        axios.get('http://localhost:8081/staff/countStaff/Doctor',{headers: {
    
        }})
            .then(res=>{
            console.log(res)
            this.setState({
           doctorcount: res.data
             })
        })
        axios.get('http://localhost:8081/staff/countStaff/Receptionist',{headers: {
    
        }})
            .then(res=>{
            console.log(res)
            this.setState({
           receptionistcount: res.data
             })
        })
             
      }
    render() {
        const { staff,patientcount,doctorcount,receptionistcount } = this.state;
        console.log(staff)
        console.log(patientcount)
        const staffList = staff.length ? ( staff.map(staff =>{
            return(
                <li className="mb-2">
                <h5 className = "black-text">{staff.account.name.firstName} {staff.account.name.lastName}</h5>
                <p className="black-text">{staff.staffType}</p>
                </li>
                

                   )

        }) ): ( <div className="center">No Staff yet</div>)
  
        return (
           <div>
               <AdminNav/>
               <div class = "container ">
                    <div class="row">
                        <div class="col s12 m4">
                            <div class="card blue-grey darken-1">
                                <div class="card-content white-text">
                                <p className="h2 center-align text-light font-weight-medium">{patientcount}</p>
                                <p className="center-align text-light text-uppercase mb-0" style={{ letterSpacing: "0.2em", fontSize: "13px" }}>PATIENTS</p>
                            </div>   
                        </div>
                        </div>
                        <div class="col s12 m4">
                            <div class="card indigo accent-2">
                                <div class="card-content white-text">
                                <p className="h2 center-align text-light font-weight-medium">{doctorcount}</p>
                                <p className="center-align text-light text-uppercase mb-0" style={{ letterSpacing: "0.2em", fontSize: "13px" }}>DOCTORS</p>
                            </div>   
                        </div>
                        </div>
                        <div class="col s12 m4">
                            <div class="card pink darken-3">
                                <div class="card-content white-text">
                                <p className="h2 center-align text-light font-weight-medium">{receptionistcount}</p>
                                <p className="center-align text-light text-uppercase mb-0" style={{ letterSpacing: "0.2em", fontSize: "13px" }}>RECEPTIONIST</p>
                            </div>   
                        </div>
                        </div>
                    </div>

                        <div class="row">
                        <div class="col s12 m4 ">
                        <div style = {{ maxHeight: "450px", overflow: "auto"}} class="card  ">
                            <div class="card-content white-text">
                                <div className="card-body px-5 py-4">
                                    <h5 class="black-text">Staff</h5>
                                    <hr className="m3"></hr>
                                    <ul className="p-0">
                                   
                                    {staffList}
                                    
                                    </ul>
                                </div>
                            
                            </div>
                            <div class="card-action">
                            <NavLink to=""><button class="btn blue-grey darken-1">See all </button></NavLink>
                            </div>
                          </div>
                        </div>
                        <div class="col s12 m8  ">
                        <div style = {{ maxHeight: "450px", overflow: "auto"}} class="card  ">
                            <div class="card-content white-text">
                                <div className="card-body">
                                <h5 class="black-text">Add Staff</h5>
                                    <hr className="m3"></hr>
                                    <form style  = {{ paddingTop:"20px"}}>
                                        <div className="row">
                                            <div class="input-field col s6">
                                                <input placeholder="Firstname" id="first_name" type="text" class="validate"/>
                                                <label >First Name</label>
                                            </div>
                                            <div class="input-field col s6">
                                                <input placeholder="Middlename" id="last_name" type="text" class="validate"/> 
                                                <label >Middle Name</label>                                     
                                                </div>
                                        </div>
                                        <div className="row">
                                            <div class="input-field col s6">
                                                <input placeholder="Lastname" id="first_name" type="text" class="validate"/>
                                                <label >Last Name</label>
                                                
                                            </div>
                                            <div class="input-field col s6">
                                                <input placeholder="Date Of Birth" id="last_name" type="date" class="validate"/>
                                                <label for="first_name">Date Of Birth </label>                                      
                                                </div>
                                        </div>
                                        <div className="row">
                                            <div class="input-field col s6">
                                                <input placeholder="Gender" id="first_name" type="text" class="validate"/>
                                                <label >Gender</label>
                                                
                                            </div>
                                             <div class="input-field col s6">
                                                <input placeholder="Email" id="first_name" type="text" class="validate"/>
                                                <label>Email</label>
                                                
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div class="input-field col s6">
                                                <input placeholder="Email" id="first_name" type="text" class="validate"/>
                                                <label>Email</label>
                                                
                                            </div>
                                            <div class="input-field col s6">
                                                <input placeholder="Placeholder" id="last_name" type="text" class="validate"/>                                      
                                                </div>
                                        </div>
                                       <input type ="submit" class = "input-field"/>
                                    </form>

                                </div>
                            
                            </div>
                            
                          </div>
                        </div>
                        
                    </div>
                    
               </div>
                
           </div>
        );
    }
}

export default AdminHome;