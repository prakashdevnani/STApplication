import React from 'react';
import axios from 'axios';

export class InputData extends React.Component{

    constructor(props){
        super(props);
        this.textInput=React.createRef();
    }
    render(){
        return(
            <form >
                <textarea rows="10" cols="50" ref={this.textInput}/><br/>
                <button type="submit" onClick={(event)=>this.storeValues(event)}>Calculate Total Amount</button>
            </form>
        );
    }
    storeValues(event){
        event.preventDefault();
        let arrOfTextInput=this.textInput.current.value.split("\n");
        for(let i=0;i<arrOfTextInput.length;i++)
            if(arrOfTextInput[i]===null || arrOfTextInput[i].trim()==="")
                arrOfTextInput.splice(i,i+1)
        axios.post('http://localhost:9090/calculateTax', arrOfTextInput)
            .then(response => this.props.retrieveData(response.data))
    }
}