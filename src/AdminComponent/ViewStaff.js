import React, {Component} from 'react';
import AdminNav from './AdminNav'
import SideNav from './SideNav'
import axios from 'axios';


class ViewStaff extends Component{
   state = {
    staff: [ ]
  }
  componentDidMount(){
    axios.get('http://localhost:8081/staff/getStaff',{headers: {

                    }})
         .then(res=>{
          console.log(res)
          this.setState({
            staff: res.data
          })
         })
  }
  
  render(){
     const { staff } = this.state;
     console.log(staff)
   
    
            const staffList = staff.length ? ( staff.map(staff =>{
                return(
                     <tr key = {staff.staffId}>
                    <td>{staff.account.name.firstName} {staff.account.name.lastName}</td>
                   <td>{staff.staffType}</td>
                   <td>{staff.account.gender}</td>
                       </tr>

                       )

            }) ): ( <div className="center">No Staff yet</div>)
      
      return(
    <div className="">
     <AdminNav/>
     <SideNav/>
    <table>
        <thead>
          <tr>
              <th data-field="id">Full Name</th>
              <th data-field="name">Staff Type</th>
              <th data-field="price">Gender</th>
          </tr>
        </thead>

        <tbody>
         {staffList}
        </tbody>
      </table>
     </div>

    )
  }
}



export default ViewStaff

