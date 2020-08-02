import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { InputData } from './InputData';
import { ResultData } from './ResultData';

class App extends React.Component {
  constructor(props){
    super(props);
    this.state={
      data:[],
      saleTax:0,
      totalBill:0
    }
  }

  render(){
  return (
    <div className="App">
      <h1>Sales Tax</h1>
      <InputData retrieveData={(data)=>this.setState({data:data.data,saleTax:data.saleTax,totalBill:data.totalBill})}/>
      <ResultData data={this.state.data} saleTax={this.state.saleTax} totalBill={this.state.totalBill}/>
    </div>
  );}
}

export default App;
