import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import Form from './Form.js';
import ValidationResult from './ValidationResult.js';

class App extends Component {
  constructor (props) {
    super(props);
    this.state = {
      validationResult: ''
    };
  }

  validateData = (value) => {
    this.setState({ validationResult: value });
  }

  render() {
    return (
      <div >
        <div >
          <h2 align="center">Form Validation</h2>
          <ValidationResult validationResult = {this.state.validationResult}/>
        </div>
        <Form validateData={this.validateData}/>
      </div>
    );
  }
}

export default App;

ReactDOM.render(
  <App />,
  document.getElementById('root')
);

