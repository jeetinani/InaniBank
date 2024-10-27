import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import axios from "axios";
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';

fetch('/links.json')
  .then(response => response.json())
  .then(data => {
    console.log('Fetched links.json:', data);
    const host = process.env.REACT_APP_GATEWAY_URL || process.env.REACT_APP_BACKEND_URL || `http://${data.host}`;
    console.log(`Setting axios baseURL to ${host}`);
    axios.defaults.baseURL = host;
  })
  .catch(error => {
    console.error('Error fetching links.json:', error);
  });

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
