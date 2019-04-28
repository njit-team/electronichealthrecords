import React, {Component} from "react";
import InputField from '../InputField';


export default class MedicineInput extends Component {
  constructor(props) {
    super(props);
    this.state = {
      searchResults: [],
      medicineName: ""
    }
  }

  handleMedicineInput(e) {
    this.setState({ medicineName: e.target.value });

    if(e.target.value === "") {
      this.setState({ searchResults: [] });
      return;
    }
    let value = e.target.value;

    fetch(`https://clinicaltables.nlm.nih.gov/api/drug_ingredients/v3/search?terms=${value}&df=code,name`, {
      method: "GET"
    })
    .then(response => {
      
      if(response.status === 200 || response.status === 304) {
        response.json()
        .then(res => {
          this.setState({searchResults: res[3] })
        })
      } 
    })
  }

  handleSearchResultClick(data) {
    if(this.props.medicineName)
    this.props.medicineName(this.state.medicineName);
    this.setState({ medicineName: data, searchResults: [] });
  }


  render() {
    return(
        <InputField errors = {this.props.error} name="medicineName" label = "Medicine Name">
          <input type="text" value={this.state.medicineName}  name="medicineName" className="text-field" onInput={this.handleMedicineInput.bind(this)}/>
          <SearchResults results={this.state.searchResults} handleResultClick={this.handleSearchResultClick.bind(this)} />
        </InputField>
    )
  }
}

export class SearchResults extends Component {
  handleResultClick(data) {
    if(this.props.handleResultClick)
      this.props.handleResultClick(data);
  }

  render() {
    let resultRender = this.props.results.map(data => {
      return <Result key={data[0]} data={data[1]} handleClick={this.handleResultClick.bind(this)} />
    })

    return(
      <div>
        {this.props.results.length > 0 ? 
          <ul class="search-result">
            {resultRender}
          </ul>
        : null}
      </div>
    )
  }
}

class Result extends Component {
  handleClick() {
    if(this.props.handleClick)
      this.props.handleClick(this.props.data);
  }

  render() {
    return(
      <li class="result" onClick={this.handleClick.bind(this)}>{this.props.data}</li>
    )
  }
}