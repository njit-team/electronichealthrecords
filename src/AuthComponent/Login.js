import React, { Component } from 'react';
import { BrowserRouter, Route, Switch,Redirect } from 'react-router-dom'
import axios from 'axios';
import InputField from '../InputField'
import NavBar from './NavBar'
import Receptionist from '../ReceptionistComponent/Receptionist'
import Home from '../PatientComponent/Home'

class Login extends Component{
    state = { 
      error: [ ] ,
      role:'',
      ID: '',
      token:''
      
    }

     handleLogin = (e) => {
    e.persist();
    e.preventDefault();
     
      
    axios.post('http://localhost:8081/token/generate' ,{
                     
                      "username": this.state.ID,
                      "password":this.state.password,
                
      })  
      .then(res=>{
       console.log(res)
        localStorage.setItem("token",res.data.jwtToken)
         this.setState({
        role : res.data.userRoles,
        ID   : res.data.userName,
        token: res.data.jwtToken
        })

      },err=>{
        console.log(err.request.response)
         if(err.request.status === 401 || err.request.status === 403){
          this.setState({
          error : [{fieldName:"password",message:"ID or password does not exist"}]
        })
         }
         
      })

   this.setState({ 
     password : ''
    })
  
  }


  onChange = (e) =>{
      this.setState({
      [e.target.name]: e.target.value 
       })
  }
     
      
  
  render(){
    const{role} = this.state;
    console.log(role)
 
    return(
     

    <div className="">
      <NavBar/>
      <form className="def-form login-form clearfix" onSubmit={this.handleLogin}>
        <label for="login-form" className="header">Sign In</label>  
            <InputField errors = {this.state.error} name="username" label = "ID">
               <input type="text" value={this.state.ID}  name="ID" className="validate" onChange={this.onChange}/>
            </InputField> 
            <InputField errors = {this.state.error} name="password" label = "Password">
              <input type="password" value={this.state.password}  name="password" className="validate" onChange={this.onChange}/>
            </InputField>
        
         <input type="submit" name="login" value="Login" className="text-field"/>
      </form>
      
    {this.state.role[0] === "ROLE_RECEPTIONIST" ? <Redirect to={{pathname: '/receptionist',state: {token:this.state.token, ID:this.state.ID}}} /> : null }
     {this.state.role[0] === "ROLE_PATIENT" ? <Redirect to={{pathname: '/patient',state: {token:this.state.token, ID:this.state.ID}}} /> : null }
    {this.state.role[0] === "ROLE_DOCTOR" ? <Redirect to={{pathname: '/doctor',state: {token:this.state.token, ID:this.state.ID}}} /> : null }
   

    </div>

    )
  }
}


export default Login

