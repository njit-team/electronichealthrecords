import React, { Component } from 'react';
import { BrowserRouter, Route,Switch } from 'react-router-dom'
import M from "materialize-css";
import ReceptionistNav from './ReceptionistNav'
import ViewAppointment from './ViewAppointment'
import PatientDetailsComponent from './PatientDetailsComponent'
import InputField from '../InputField'
import axios from 'axios';

class AddPatient extends Component{
    state = { 
      error: [ ] ,
       activePatientModal: false,
       patientId: '',
       success: []
    }
    componentWillMount(){
      if(localStorage.getItem("token")){
        axios.get(`http://localhost:8081/token/role`,                 
          {headers: {
                       'Authorization': 'Bearer '+localStorage.getItem("token")                  
                  }})
        .then(res=>{
          console.log(res)
          if(res.data[0]!=="ROLE_RECEPTIONIST"){
            this.props.history.push("/login")
          }
         
        },err=>{
        })
      }else{
        this.props.history.push("/login")
      }
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

  handlekeyPress(e){
     if(e.key==='Enter'){
      e.preventDefault();
       this.setState({
       activePatientModal : "patientModal", 
       patientId: e.target.value   
       })
     }
   } 
   
   handleModalClose() {
    this.setState({ activeModal: false, activePatientModal: false,  });
  }

    handleSubmit = (e) => {
    e.persist();
    e.preventDefault();
     
    
    
    axios.post('http://localhost:8081/patient/create ',{ account:{

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
        },{headers: {
                    "Content-Type": "application/json",
                    'Authorization': 'Bearer '+localStorage.getItem("token")

                }})
      .then(res=>{
      console.log(res)
      if(res.status === 200){
          this.setState({
             success : [{message:"Successfully Added Patient and patientID is "+res.data.patientId}]
          })

        }else{
          if(res.status === 403 || res.status === 401){  
            this.props.history.push("/login")
          }
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
     <ReceptionistNav handlekeyPress={this.handlekeyPress.bind(this)}/>
          <form className="def-form long-dist-form clearfix" onSubmit={this.handleSubmit}>
       <div className="row">
       {successful}
      
           
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
     
      <button className="text-field" type="submit" name="submit">Submit</button>
       
     
      </form>
  
     {this.state.activePatientModal === "patientModal" ? <PatientDetailsComponent patientId={this.state.patientId} handleModalClose={this.handleModalClose.bind(this)} /> : null}

    </div>

    )
  }
}


export default AddPatient

