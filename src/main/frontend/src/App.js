import { Route, Routes } from 'react-router-dom';
import './App.css';
import axios from "axios";
import About from './components/About';
import Detail from './components/Detail';
import Home from './components/Home';
import Layout from "./components/Layout";
import LoggingPage from './components/LoggingPage';
import Logout from "./components/Logout";
//import LearnMiddle from './components/LearnMiddle';
import { useState, useEffect } from 'react';

function App() {

  const [context, updateContext] = useState({ user: "", stage: "blank" });

  let token = window.sessionStorage.getItem("user-info");
  useEffect(() => {
    console.log(`in useEffect of app.js`)
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
  }, []);

  //console.log("Type is "+typeof updateStage);
  //const [user, setUser] = useState("")
  //const name = "Jeet Inani";
  return (
    /* <div className="App">
      <h2>Welcome to react application</h2>
      <h2>My Name is {name}</h2>
      <Home uname={name}/>
    </div> */
    <>
      <Routes>
        <Route path='/' element={<Layout stage={context.stage} />}>
          <Route index element={<Home context={context} />} />
          <Route path='about' element={<About />} />
          <Route path='login' element={<LoggingPage updateContext={updateContext} />} />
          <Route path='logout' element={<Logout updateStage={(newstage) => { updateContext({ ...context, stage: newstage }) }} />} />
          <Route path='account/detail/:acno' element={<Detail />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;