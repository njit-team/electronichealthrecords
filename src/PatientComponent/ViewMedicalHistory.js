import React, {Component} from 'react'
import axios from 'axios';
class ViewMedicalHistory extends Component {
    state = {
      history : []
    }
    componentWillMount(){
      if(localStorage.getItem("token")){
        axios.get(`http://localhost:8081/token/role`,                 
          {headers: {
                       'Authorization': 'Bearer '+localStorage.getItem("token")                  
                  }})
        .then(res=>{
          console.log(res)
          if(res.data[0]!=="ROLE_PATIENT"){
            this.props.history.push("/login")
          }
         
        },err=>{
        })
      }else{
        this.props.history.push("/login")
      }
    }
    
  componentDidMount(){
    axios.get(`http://localhost:8081/patient/medical/history/${this.props.ID}`,{headers: {
                   
                     Authorization: 'Bearer '+localStorage.getItem("token") 

                }})
         .then(res=>{
          console.log(res)
          this.setState({
            history: res.data
          })
         })


  }
 handleModalClose() {
    if(this.props.handleModalClose)
        this.props.handleModalClose();
  }
    
  render(){
    const {history} = this.state;
    console.log(history)
    return(
      <div className="custom-modal">
          <div className="modal-container">
            <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>
           <p className="data"><span>Record:</span>{ history.record}</p>
            <p className="data"><span>Date:</span> {history.timeStamp}</p>
            <p className="data"><span>Comment:</span> {history.additionalComments}</p>
          </div>
        </div>
           
     ) 
  }
}

export default ViewMedicalHistory