import React, {Component} from 'react'



class SideNav extends Component{

  render(){
    return(

   
        <ul id="slide-out" className="sidenav">
        <li><div className="user-view">
        <div className="background">
        
        </div>
     
        </div></li>
        <li><a href="/"><i className="material-icons">add</i>Add Staff</a></li>
        <li><div className="divider"></div></li>
        <li><a href= "view_staff"><i className="material-icons">view_module</i>View Staff</a></li>
        <li><div className="divider"></div></li>
        <li><a href= "manage_staff"><i className="material-icons">edit</i>Manage Staff</a></li>
        <li><div className="divider"></div></li>
        <li><a href="logout"><i className="material-icons">lock_outline</i>Logout</a></li>
       
        <li><div className="divider"></div></li>

    
   
      </ul>

    
           )
  }

}

export default SideNav