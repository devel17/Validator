import React, { Component } from 'react';
import { FormErrors } from './FormErrors';

class Form extends Component {
  constructor (props) {
    super(props);
    this.state = {
      phone: '',
      formErrors: {phone: ''},
      phoneValid: false
    }
  }

  handleUserInput = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    this.setState({[name]: value},
                  () => { this.validateField(name, value) });
  }

  validateField(fieldName, value) {
    let fieldValidationErrors = this.state.formErrors;
    let phoneValid = this.state.phoneValid;

    switch(fieldName) {
      case 'phone':
        passwordValid = value.length >= 10;
        fieldValidationErrors.phone = phoneValid ? '': ' is too short';
        break;
      default:
        break;
    }
    this.setState({formErrors: fieldValidationErrors,
                    phoneValid: phoneValid
                  }, this.validateForm);
  }

  validateForm() {
    this.setState({formValid: this.state.phoneValid});
  }

  errorClass(error) {
    return(error.length === 0 ? '' : 'has-error');
  }

  render () {
    return (
      <form className="demoForm">
        <h2>Validate</h2>
        <div className="panel panel-default">
          <FormErrors formErrors={this.state.formErrors} />
        </div>
        <div className={`form-group ${this.errorClass(this.state.formErrors.phone)}`}>
          <label htmlFor="phone">Phone</label>
          <input type="phone" required className="form-control" name="phone"
            placeholder="Phone"
            value={this.state.phone}
            onChange={this.handleUserInput}  />
        </div>
        <button type="submit" className="btn btn-primary" disabled={!this.state.formValid}>Validate</button>
      </form>
    )
  }
}

export default Form;
