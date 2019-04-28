import React, { Component } from 'react';
import { BrowserRouter, Route,Switch } from 'react-router-dom'
import M from "materialize-css";
import InputField from '../InputField'
import axios from 'axios';


class Comment extends Component {
      state = { 
      error: [ ] ,
      success: ""
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
  
  handleSubmit = (e) => {
    e.persist();
    e.preventDefault();
     
  
    axios.post(`http://localhost:8081/doctor/comments/${this.props.patientId}`,
             { 
              
              "additionalComments":this.state.additionalComments,
            },{headers: {
                    "Content-Type": "application/json",
                     Authorization: 'Bearer '+localStorage.getItem("token") 

                }})
      .then(res=>{

        console.log(res)

      },err=>{
        console.log(err.request.response)
         this.setState({
         error : JSON.parse(err.request.response).violations
        })
      })


   this.setState({ 
     
     additionalComments : '',
    
     
      

    })


  }

  
  
  render() {
    return (
      <div className="todo-app ">
       
       <form className=" def-form long-dist-form clearfix" onSubmit={this.handleSubmit}>
    <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>

      <div className="row">
       <InputField errors = {this.state.error} name="additionalComments" label = "Comments">
        <textarea id="textarea1" name = "additionalComments" value={this.state.additionalComments} onChange={this.onChange} className="materialize-textarea"></textarea>
       </InputField> 
       
        
      </div>
      
      <button className="text-field" type="submit" name="submit">Submit </button>
       
      
       </form>

        
        
      </div>


    );
  }
}

export default Comment