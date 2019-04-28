import React, { Component } from 'react';
import { BrowserRouter, Route,Switch } from 'react-router-dom'
import M from "materialize-css";
import NavBar from './NavBar'
import AdminFooter from '../AdminComponent/AdminFooter'
import InputField from '../InputField'
import axios from 'axios';

class SignUp extends Component{
    state = { 
      error: [ ] ,
       activePatientModal: false,
       patientId: '',
       success :[]
    }
  
  componentDidMount(){
     var elems = document.querySelectorAll('select');

     let options = {
      classes: "",
      dropDownOptions:{},
      
     }
      M.FormSelect.init(elems, options);

  }

   onChange = (e) =>{
      this.setState({
        [e.target.name]: e.target.value 
        })
  }


    handleSubmit = (e) => {
    e.persist();
    e.preventDefault();
     
  
    axios.post('http://localhost:8081/users/sign-up',{ account:{
            name :{
              "firstName": this.state.firstname,
              "middleName":this.state.middlename,
              "lastName": this.state.lastname
            },
            address:{
               "street": this.state.street,
               "apartment":this.state.apartment,
               "city": this.state.city,
               "state": this.state.state,
               "zipCode":this.state.zipCode,
               
               "country": this.state.country
            },
            "phoneNumber":this.state.phoneNumber,
            "email":this.state.email,
            "dateOfBirth":this.state.dateOfBirth,
            "gender":this.state.gender
        },
        
        "userType":this.state.userType,
        "roles": [this.state.role],
        "password":this.state.password,
        "username": this.state.ID
        },{headers: {
                    "Content-Type": "application/json",
                }})
      .then(res=>{
      console.log(res)
      if(res.status == 200){
          this.setState({
             success : [{message:"Successfully Added User "}]
          })

        }
     
        
      },err=>{
       console.log(err.request.response)
         this.setState({
          
       error : JSON.parse(err.request.response).violations
        })
      })

    this.setState({ 
      firstname : '',
      middlename : '',
      lastname : '',
      street: '',
      apartment: '',
      city: '',
      state:'',
      zipCode: '',
      phoneNumber:'',
      country : '',
      email : '',
      dateOfBirth: '',
      gender: '',
      roles: '',
      userType:'',
      ID:'',
      password:''
    })
   
   
  }
 
      
  
  render(){
    const{success} = this.state
        var arrayLength = success.length;
        for (var i = 0; i < arrayLength; i++) {
            console.log(success[i]);
            var successful =  <p className="custom-input-error">{success[i].message}</p>
                       
        }
 
    return(
     

    <div className="">
     <NavBar/>
          <form className="def-form long-dist-form clearfix" onSubmit={this.handleSubmit}>
          {successful}
       <div className="row">
           
       <InputField errors = {this.state.error} name="account.name.firstName" label = "First Name">
          <input id="first_name" type="text" value={this.state.firstname}  name="firstname" className="validate" onChange={this.onChange}/>
       </InputField>

      <InputField errors = {this.state.error} name="account.name.middleName" label = "Middle Name">
          <input id="last_name" type="text" name="middlename" value={this.state.middlename} onChange={this.onChange} className="validate"/>
      </InputField>
           
        

      </div>

       <div className="row">
      
       <InputField errors = {this.state.error} name="account.name.lastName" label = "Last Name">
           <input  type="text" name="lastname" value={this.state.lastname} onChange={this.onChange} className="validate"/>
       </InputField>

       <InputField errors = {this.state.error} name="account.email" label = "Email">
          <input id="email" name = "email" value={this.state.email}  onChange={this.onChange} type="text" className="validate"/>
      </InputField>     
         
      </div>
       <div className="row">
       <InputField errors = {this.state.error} name="account.gender" >
          <select name = "gender"   value={this.state.gender} onChange={this.onChange} >
            <option name = "gender" value={this.state.gender}  disabled selected>Choose your gender</option>
            <option name = "gender"  value={this.state.gender} onChange={this.onChange}>Male</option>
            <option name = "gender"  value={this.state.gender} onChange={this.onChange}>Female</option>   
          </select>
       </InputField>

         <InputField errors = {this.state.error} name="userType" >
          <select name = "userType" value={this.state.userType} onChange={this.onChange} >
            <option name = "userType" value={this.state.userType}  disabled selected>Choose the User Type</option>
            <option name = "userType" value={this.state.userType} onChange={this.onChange}>Staff</option>
           <option name = "userType" value={this.state.userType} onChange={this.onChange}>Patient</option>   
          </select>
         </InputField>
  
        </div>
      <div className="row">
      <InputField errors = {this.state.error} name="account.dateOfBirth" label = "Date Of Birth">
          <input id="date_of_birth" value={this.state.dateOfBirth} name = "dateOfBirth" onChange={this.onChange} type="date" className="text-field"/>
      </InputField>

        <InputField errors = {this.state.error} name="account.address.phoneNumber" label = "Phone Number">
          <input  type="text" name="phoneNumber" value={this.state.phoneNumber} onChange={this.onChange} className="validate"/>
        </InputField>

      </div>
      <div className="row">
     
       <InputField errors = {this.state.error} name="account.address.street" label = "Street">
          <input  type="text" name="street" value={this.state.street} onChange={this.onChange} className="validate"/>
       </InputField>   
       
      <InputField errors = {this.state.error} name="account.address.apartment" label = "Apartment">
          <input  type="text" name="apartment" value={this.state.apartment} onChange={this.onChange} className="validate"/>
      </InputField>
   
      </div>
      <div className="row"> 
      <InputField errors = {this.state.error} name="account.address.city" label = "City">
          <input type="text" name="city" value={this.state.city} onChange={this.onChange} className="validate"/>
      </InputField>
       <InputField errors = {this.state.error} name="account.address.state" label = "State">
          <input  type="text" name="state" value={this.state.state} onChange={this.onChange} className="validate"/>
       </InputField>
      
 
      </div>
  <div className="row"> 
        
       <InputField errors = {this.state.error} name="account.address.zipCode" label = "Zip-code">
          <input type="text" name="zipCode" value={this.state.zipCode} onChange={this.onChange} className="validate"/>
      </InputField>

       <InputField errors = {this.state.error} name="account.address.country" label = "Country">
          <input  type="text" name="country" value={this.state.country} onChange={this.onChange} className="validate"/>
       </InputField>
       
       
      </div>

       <div className="row">

       <InputField errors = {this.state.error} name="roles" >
          <select name = "role" value={this.state.roles} onChange={this.onChange} >
            <option disabled selected>Choose the role</option>
            <option name = "role" value={this.state.roles} onChange={this.onChange}>Doctor</option>
            <option name = "role"  value={this.state.roles} onChange={this.onChange}>Patient</option>
            <option name = "role" value={this.state.roles} onChange={this.onChange}>Receptionist</option>
            <option name = "role"  value={this.state.roles} onChange={this.onChange}>Lab Technician</option>
          </select>
         </InputField>

        <InputField errors = {this.state.error} name="username" label = "ID">
            <input  type="text" name="ID" value={this.state.ID} onChange={this.onChange} className="validate"/>
         </InputField>
       
      </div>
      <div className="row">

        <InputField errors = {this.state.error} name="password" label = "Password">
            <input  type="password" name="password" value={this.state.password} onChange={this.onChange} className="validate"/>
         </InputField>
       
      </div>
  
      
      <button className="text-field" type="submit" name="submit">Submit</button>
       
     
      </form>
  
  

    </div>

    )
  }
}


export default SignUp

