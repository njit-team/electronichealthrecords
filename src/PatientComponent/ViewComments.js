import React, {Component} from 'react'
import axios from 'axios';
class ViewComments extends Component {
    state = {
      comments: []
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
    axios.get(`http://localhost:8081/patient/comments/${this.props.ID}`,{headers: {
                   
                     Authorization: 'Bearer '+localStorage.getItem("token") 

                }})
         .then(res=>{
          console.log(res)
          this.setState({
            comments: res.data
          })
         })


  }
 handleModalClose() {
    if(this.props.handleModalClose)
        this.props.handleModalClose();
  }
    
  render(){
    const {comments} = this.state;

    
    var commentLists = comments.map(comment=>{
      console.log(comment.additionalComments)
      return(
           <p className="data"><span>Comment:</span>{comment.additionalComments}</p>  
             )
    })

    return(
      <div className="custom-modal">
          <div className="modal-container">
            <i className="material-icons close-btn" onClick={this.handleModalClose.bind(this)}>close</i>
              {commentLists}
           
          </div>
        </div>
           
     ) 
  }
}

export default ViewComments