import React, { Component } from 'react';
import { NavLink } from "react-router-dom";
import AdminNav from './AdminNav'
class AdminHome extends Component {

    constructor(props) {
        super(props);
        this.state = {  };
    }
    render() {
        return (
           <div>
               <AdminNav/>
           </div>
        );
    }
}

export default AdminHome;