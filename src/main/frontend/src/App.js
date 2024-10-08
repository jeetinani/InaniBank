import { Route, Routes } from 'react-router-dom';
import { UserContext } from './context/UserContext';
import './App.css';
import axios from "axios";
import About from './components/About';
import Detail from './components/Detail';
import Home from './components/Home';
import Layout from "./components/Layout";
import LoggingPage from './components/LoggingPage';
import Logout from "./components/Logout";
import { useState, useEffect } from 'react';

function App() {

  const [context, updateContext] = useState({ user: "", stage: "blank" });

  let token = window.sessionStorage.getItem("user-info");
  useEffect(() => {
    console.log(`in useEffect of app.js`)
    //console.log('host is ' + links.host);
    /* fetch('/links.json')
      .then(response => response.json())
      .then(data => {
        console.log('Fetched links.json:', data);
        const host = process.env.REACT_APP_BACKEND_URL || `http://${data.host}`;
        console.log(`Setting axios baseURL to ${host}`);
        axios.defaults.baseURL = host;
      })
      .catch(error => {
        console.error('Error fetching links.json:', error);
      }); */
    //const host = process.env.REACT_APP_BACKEND_URL || `http://${links.host}`;;
    //console.log(`Setting axios baseURL to ${host}`);
    //axios.defaults.baseURL = host;
    //axios.defaults.baseURL = `http://${links.host}`;
    if (token) {
      let options = {
        headers: {
          "Authorization": `Bearer ${token}`,
        }
      };
      axios.get(`/api/userName`, options)
        .then(
          resp => {
            updateContext({ user: resp.data, stage: "loggedIn" });
          }
        )
    }
  }, [token]);

  return (

    <UserContext.Provider value={context}>
      <Routes>
        <Route path='/' element={<Layout />}>
          <Route index element={<Home />} />
          <Route path='about' element={<About />} />
          <Route path='login' element={<LoggingPage updateContext={updateContext} />} />
          <Route path='logout' element={<Logout updateStage={(newstage) => { updateContext({ ...context, stage: newstage }) }} />} />
          <Route path='account/detail/:acno' element={<Detail />} />
        </Route>
      </Routes>
    </UserContext.Provider>
  );
}

export default App;