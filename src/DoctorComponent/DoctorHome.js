import React, {Component} from 'react'
import DoctorNav from './DoctorNav'
import InputField from '../InputField'
import AdministerTreatment from './AdministerTreatment'
import {Redirect } from 'react-router-dom'
import axios from 'axios';

class DoctorHome extends Component {
  constructor(props) {
    super(props);
   
    this.state = {
      error : [],
      active:false,
      patientId:'',
      role: ''
   }

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

onChange = (e) =>{
      this.setState({
        patientId: e.target.value  
        })
  }

    handleSubmit = (e) => {
      console.log(localStorage.getItem("token") )
    e.persist();
    e.preventDefault();
    
    axios.get(`http://localhost:8081/patient/find/${this.state.patientId}`,                 
        {headers: {
                      'Authorization': 'Bearer '+localStorage.getItem("token") 
                    
                }})
      .then(res=>{
        if(res.data.patientId){
           
         this.setState({
          active:true,
          patientId: res.data.patientId
        })
        }
      },err=>{
       console.log(err.response)
         this.setState({
          
        })
      })

    this.setState({ 
      patientId:''

    })
   
   
  }
  
  render(){
    return(
     <div className="">
     <DoctorNav/>
     <form className="def-form long-dist-form clearfix" onSubmit={this.handleSubmit}>
     <div className="row">  
          <InputField errors = {this.state.error} name="patientId" label = "patientId">
             <input id="first_name" type="text" value={this.state.patientId}  name="patientId" className="validate" onChange={this.onChange}/>
           </InputField>
      </div>
  
      <button className="text-field" type="submit" name="submit">Submit</button>
   
      </form>
      {this.state.active ? <AdministerTreatment  patientId={this.state.patientId}  /> : null}
     
  
     </div>
        
      );
  }
}

export default DoctorHome