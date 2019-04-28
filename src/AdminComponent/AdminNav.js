import React, {Component} from 'react'
import M from "materialize-css";

class AdminNav extends Component {
  componentDidMount() {
      let dropdowns = document.querySelectorAll('.dropdown-trigger');

      var elems = document.querySelectorAll('.sidenav');
      
      var collapsibleElem = document.querySelector('.collapsible');
     M.Collapsible.init(collapsibleElem, options);

    
     let options = {
        inDuration: 300,
        outDuration: 300,
        hover: true, 
        coverTrigger: false 
    }
    
     let option = {
        inDuration: 250,
        outDuration: 250,
        edge: "left",
        onOpenStart:null
        
    }
    
    M.Dropdown.init(dropdowns, options);
     M.Sidenav.init(elems, option);

    }
    
  render(){
    return(

      
      <nav className = "top-bar">
      <div class="nav-wrapper">
        <ul id="nav-mobile" class="left hide-on-med-and-down">
          <li><a href="sass.html">Add Staff</a></li>
          <li><a href="badges.html">View Staff </a></li>
          <li><a href="collapsible.html">Logout</a></li>
        </ul>
      </div>
    </nav>



    
           );
  }
}

export default AdminNav;