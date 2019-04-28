import React, { Component } from 'react'
import InputField from '../InputField'
export default class TestInput extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            searchResults : [],
            testName :"",
            error : []
         };
    }
    handleTestInput(e){
        this.setState({ testName : e.target.value })

        if(e.target.value === ""){
            this.setState({
                searchResults:[]
            })
            return;
        }
        let value = e.target.value
        
        fetch(`https://clinicaltables.nlm.nih.gov/api/icd9cm_sg/v3/search?terms=${value}`,{
            method:"GET"
        })
        .then(response=>{
            console.log(response)
            response.json()
            .then(res => {
                console.log(res)
                this.setState({searchResults: res[3] })
            })
        })

    }

    handleSearchResultClick(data) {
        this.setState({ testName: data, searchResults: [] });
      }

    render() {
        return (
        
            <InputField errors = {this.state.error} name="testName" label = "Test Name">
              <input type = "text"  name = "testName" value={this.state.testName} onInput={this.handleTestInput.bind(this)} className="text-field"/>
              <SearchResults results={this.state.searchResults} handleResultClick={this.handleSearchResultClick.bind(this)} />
            </InputField>  
        );
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
         return (
            <div>
            {this.props.results.length > 0 ? 
              <ul class="search-result">
                {resultRender}
              </ul>
            : null}
          </div>
             
         );
     }
 }
 
 export class Result extends Component {
     constructor(props) {
         super(props);
         this.state = {  };
     }
     handleClick() {
        if(this.props.handleClick)
          this.props.handleClick(this.props.data);
      }
    
     render() {
         return (
            <li class="result" onClick={this.handleClick.bind(this)}>{this.props.data}</li>  
         );
     }
 }
 
