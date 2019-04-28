import React, {Component} from 'react'
import axios from 'axios';
class ViewPrescription extends Component {
    state = {
      prescriptions : []
    }
    componentDidMount(){
    this.getPescription();

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

  getPescription(){
     axios.get(`http://localhost:8081/patient/prescription/${this.props.ID}`,{headers: {
                       
                         'Authorization': 'Bearer '+localStorage.getItem("token") 
                    }})
             .then(res=>{
              console.log(res)
              this.setState({
                prescriptions: res.data
              })
             })
        

  }

 handleModalClose() {
    if(this.props.handleModalClose)
        this.props.handleModalClose();
  }
    
  render(){
    const {prescriptions } = this.state;
    console.log(prescriptions)
    console.log(this.state);
    


    const prescriptionList = prescriptions.length ? ( prescriptions.map(prescription =>{
        return(
                    <tr><td>{prescription.medicineName}</td>
                    <td>{prescription.dosage}</td>
                    <td>{prescription.additionalComments}</td>
                    <td>{prescription.active}</td>
                     </tr>  
        )
        }) ): ( <div className="center">No prescription yet</div>)
    
    return(

      <div className="custom-modal">
          <div className="modal-container">
            <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>
                <table>
                <thead>
                 <tr>
                   <th>Medicine </th>
                   <th>Dosage</th>
                   <th>Comment</th>
                    <th>Active</th>
                  </tr>
                </thead>

                <tbody>
                 
                    {prescriptionList}
                 


                </tbody>
          </table>
       </div>
    </div>
           
     ) 
  }
}

export default ViewPrescription