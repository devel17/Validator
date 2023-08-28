import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import App from './main/js/App.js';

class Index extends Component {
  render() {
    return (
      <div >
          <App />
      </div>
    );
  }
}

export default Index;

ReactDOM.render(
  <Index />,
  document.getElementById('root')
);

