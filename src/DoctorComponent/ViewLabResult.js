import React, {Component} from 'react'
import axios from 'axios';
class ViewLabResult extends Component {
    state = {
      result : []
      
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
    axios.get(`http://localhost:8081/doctor/test/result/${this.props.patientId}`,{headers: {
                   
                    Authorization: 'Bearer '+localStorage.getItem("token")

                }})
         .then(res=>{
          console.log(res)
          this.setState({
            result: res.data
          })
         })


  }
 handleModalClose() {
    if(this.props.handleModalClose)
        this.props.handleModalClose();
  }
    
  render(){
    const {result} = this.state
    return(
      <div className="custom-modal">
          <div className="modal-container">
            <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>
            <p className="data"><span>Test Name:</span> Tunde</p>
            <p className="data"><span>Test Result:</span> Tunde</p>
            <p className="data"><span>Comments:</span> Tunde</p>
          </div>
        </div>
           
     ) 
  }
}

export default ViewLabResult