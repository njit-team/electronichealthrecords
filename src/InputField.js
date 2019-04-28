import React, {Component} from 'react'
import './index.css'

class InputField extends Component {

 
 render(){

  var error = false;


  for(var i = 0; i<this.props.errors.length ; i++){

      if(this.props.name === this.props.errors[i].fieldName){
        error = this.props.errors[i];
      break;
      }else{
      continue;
     }
  }
      return(
             <div className="col s6" style={{position: "relative"}}>

              {this.props.children}
              <label data-success="success">{this.props.label}</label>

              {
                error ? <p className="custom-input-error">{error.message}</p> : null
              }
             </div>
         

       )
 }
  

  }

  export default InputField