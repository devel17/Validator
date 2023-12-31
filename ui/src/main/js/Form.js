import React, { Component } from 'react';

class Form extends Component {
  constructor (props) {
    super(props);
    this.state = {
      phone: '',
      validationResult: ''
    };
  }

  handleUserInput = (e) => {
    this.setState({[e.target.name]: e.target.value});
    this.setState({validationResult: ''});
  }

  validatePhone = () => {
    fetch('http://localhost:8080/api/validate', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        validationSource: this.state.phone
      })
    })
    .then(response => response.json())
    .then(data =>  this.props.validateData(data.validationResult))
    .catch(err => console.log(err.message));
    
  }

  render () {
    return (
      <form align = "center">
        <h4>Enter the phone number</h4>
        <div>
          <label htmlFor="phone">Phone </label>
          <input required type="phone" name="phone"
            placeholder="Phone" maxLength="15"
            value={this.state.phone}
            onChange={this.handleUserInput}  />
        </div>
        <button type="button" onClick={this.validatePhone}>Validate</button>
      </form>
    );
  }
}

export default Form;
