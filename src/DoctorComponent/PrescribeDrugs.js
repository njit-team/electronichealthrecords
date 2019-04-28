import React, { Component } from 'react';
import { BrowserRouter, Route,Switch } from 'react-router-dom'
import M from "materialize-css";
import InputField from '../InputField'
import axios from 'axios';
import MedicineInput from "./MedicineInput";


class PrescribeDrugs extends Component {
      state = { 
      error: [ ] ,
      success: "",
    }
    componentWillMount(){
      if(localStorage.getItem("token")){
        axios.get(`http://localhost:8081/token/role`,                 
          {headers: {
                       'Authorization': 'Bearer '+localStorage.getItem("token")                  
                  }})
        .then(res=>{
          console.log(res)
          if(res.data[0]!=="ROLE_DOCTOR"){
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
      dropDownOptions:{}
     }
      M.FormSelect.init(elems, options);

  }

   onChange = (e) =>{
      this.setState({
        [e.target.name]: e.target.value 
        })
  }
  handleModalClose() {
    if(this.props.handleModalClose)
        this.props.handleModalClose();
  }
  onSetMedicineName(data){
    this.setState({
      medicineName: data
    })
  }
  
  handleSubmit = (e) => {
    e.persist();
    e.preventDefault();
     
  
    axios.post(`http://localhost:8081/doctor/prescribe/drugs/${this.props.patientId}`,
             { 
              "medicineName": this.state.medicineName,
              "dosage" : this.state.dosage ,
              "additionalComments":this.state.additionalComments,
              "active":this.state.active
            },{headers: {
                    "Content-Type": "application/json",
                     Authorization: 'Bearer '+localStorage.getItem("token") 

                }})
      .then(res=>{
      },err=>{
        console.log(err.request.response)
         this.setState({
         error : JSON.parse(err.request.response).violations
        })
      })


   this.setState({ 
      medicineName : '',
      dosage : '',
     additionalComments : '',
      active: '',
   

    })


  }


  render() {
    return (
      <div className="todo-app ">
       
       <form className=" def-form long-dist-form clearfix" onSubmit={this.handleSubmit}>
    <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>
        
     <div className="row">
       
      <MedicineInput error={this.state.error} medicineName={this.onSetMedicineName.bind(this)}/>
       <InputField errors = {this.state.error}  name="dosage" >
        <select name = "dosage" className = "text-field" value={this.state.dosage} onChange={this.onChange} >
          <option value={this.state.dosage} disabled selected>Choose the Dosage</option>
          <option name = "dosage" value={this.state.dosage} onChange={this.onChange} >1 Daily</option>
          <option name = "dosage" value={this.state.dosage} onChange={this.onChange} >2 daily</option>
          <option name = "dosage" value={this.state.dosage} onChange={this.onChange} >3 Daily</option>  
        </select>
         </InputField>
        
      </div>

      <div className="row">
       <InputField errors = {this.state.error} name="additionalComments" label = "Comments">
       <input type="text" value={this.state.additionalComments}  name="additionalComments" className="text-field" onChange={this.onChange}/>
       </InputField> 
       
        <InputField errors = {this.state.error} name="active" label = "Active">
         <input type="text" name = "active" value={this.state.active} className="text-field" onChange={this.onChange}/>
        </InputField>
      </div>
      
     
     
      <button className="text-field" type="submit" name="submit">Submit    </button>
       
      
       </form>

        
        
      </div>


    );
  }
}

export default PrescribeDrugs