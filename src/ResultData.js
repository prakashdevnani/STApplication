import React from 'react';

export class ResultData extends React.Component{
    render(){
        var dataList="";
        for(let i=0;i<this.props.data.length;i++)
            dataList+=this.props.data[i]+"\n";
        const val=this.props.data.length===0?"Please Wait....":dataList+"Sales Taxes: "+this.props.saleTax+"\nTotal: "+this.props.totalBill;
        return(
            <React.Fragment>
            <br/>
            <textarea rows="10" cols="50" style={{bottom:"100px"}} value={val} ref={this.textInput}/>
            </React.Fragment>
        )};
}