import React, { Component } from 'react';

class ValidationResult extends Component {
  render() {
    return (
      <div >
        <div >
          <h6 align="center">Result : {this.props.validationResult}</h6>
        </div>
      </div>
    );
  }
}


export default ValidationResult;