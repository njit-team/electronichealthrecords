import React, {Component} from 'react'
import axios from 'axios';
import TestInput from './TestInput'
class PrescribeTest extends Component {
    state = {
      error: []
      
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
        [e.target.name]: e.target.value 
        })
  }
  handleSubmit = (e) => {
    e.persist();
    e.preventDefault();
   
    axios.post(`http://localhost:8081/doctor/prescribe/tests/${this.props.patientId}`,
             { 
              "testName" : this.state.testName
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
    testName:''
  })

  }

 handleModalClose() {
    if(this.props.handleModalClose)
        this.props.handleModalClose();
  }
    
  render(){
    
    return(
      <div className="custom-modal">
          <div className="modal-container">
           <form onSubmit={this.handleSubmit}>
            <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>
             <div className="row">   
                 <TestInput error = {this.state.error}/>
          </div> 
         <button className="text-field" type="submit" name="submit">Submit</button>
         </form>
          </div>
        </div>
           
     ) 
  }
}

export default PrescribeTest