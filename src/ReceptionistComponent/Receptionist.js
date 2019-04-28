import React, { Component } from 'react';
import { BrowserRouter, Route,Switch } from 'react-router-dom'
import M from "materialize-css";
import ReceptionistNav from './ReceptionistNav'
import ViewAppointment from './ViewAppointment'
import InputField from '../InputField'
import PatientDetailsComponent from './PatientDetailsComponent'
import axios from 'axios';



class Receptionist extends Component {
      state = { 
      error: [ ] ,
      activePatientModal: false,
       patientId: '',
       success : []
      
    }
    componentWillMount(){
      if(localStorage.getItem("token")){
        axios.get(`http://localhost:8081/token/role`,                 
          {headers: {
                       'Authorization': 'Bearer '+localStorage.getItem("token")                  
                  }})
        .then(res=>{
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
      dropDownOptions:{}
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
     
  
    axios.post('http://localhost:8081/appointment/book',
             { 
              "appointmentReason": this.state.appointmentReason,
              "appointmentDateTime" : this.state.appointmentDateTime +'z',
              "doctorId":this.state.doctorId,
              "patientId":this.state.patient
            },{headers: {
                    "Content-Type": "application/json",
                     'Authorization': 'Bearer '+localStorage.getItem("token") 

                }})
    
      .then(res=>{
        console.log(res)
        if(res.status == 200){
          this.setState({
             success : [{message:"Successfully Booked Appointment"}]
          })
         

        }

      },err=>{
         this.setState({
         error : JSON.parse(err.request.response).violations
        })
      })


   this.setState({ 
      doctorId : '',
      patient : '',
     appointmentReason : '',
      appointmentDateTime: '',
      

    })

  }

  
  
  render() {
    const{success} = this.state
    var arrayLength = success.length;
    for (var i = 0; i < arrayLength; i++) {
        console.log(success[i]);
        var successful =  <p className="custom-input-error">{success[i].message}</p>          
    }
    return (
       <BrowserRouter>
      <div className="todo-app ">
    
     <body>      
    <header>
     <ReceptionistNav  handlekeyPress={this.handlekeyPress.bind(this)}/>
 

        <Switch>
   
       <Route path = "/view_appointment" component = {ViewAppointment} />
      
       </Switch>
        
    </header>
    <main>
   <form className=" def-form long-dist-form clearfix" onSubmit={this.handleSubmit}>
   
        
     <div className="row">
     {successful}
       
        <InputField errors = {this.state.error} name="patientId" label = "Patient ID">
          <input type="text" value={this.state.patient}  name="patient" className="text-field" onChange={this.onChange}/>
           </InputField>
        
          
      
       <InputField errors = {this.state.error}  name="doctorId" label = "Doctor">
        <input type="text" value={this.state.doctorId}  name="doctorId" className="text-field" onChange={this.onChange}/>
         </InputField>
        
      </div>

      <div className="row">
       <InputField errors = {this.state.error} name="appointmentDateTime" label = "Time">
      
         <input type="datetime-local" name="appointmentDateTime" className = "text-field" value={this.state.appointmentDateTime} onChange={this.onChange} />
          
        </InputField>   
       
        <InputField errors = {this.state.error} name="appointmentReason" label = "Reason">
          <textarea id="textarea1" name = "appointmentReason" value={this.state.appointmentReason} onChange={this.onChange} className="materialize-textarea"></textarea>
          </InputField>
      </div>
     
     
      <button className="text-field" type="submit" name="submit">Submit
      
      </button>
       
      
       </form>

        {this.state.activePatientModal === "patientModal" ? <PatientDetailsComponent patientId={this.state.patientId} handleModalClose={this.handleModalClose.bind(this)} /> : null}
    </main>
    
     </body>
   
        
      </div>

       </BrowserRouter>

    );
  }
}

export default Receptionist