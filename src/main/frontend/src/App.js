import { Route, Routes } from 'react-router-dom';
import './App.css';
import About from './components/About';
import Detail from './components/Detail';
import Home from './components/Home';
import Layout from './components/Layout';
import LoggingPage from './components/LoggingPage';
import Logout from "./components/Logout";
//import LearnMiddle from './components/LearnMiddle';
import { useEffect, useState } from 'react';

function App() {

  const [stage,updateStage] = useState("blank");
  console.log("Type is "+typeof updateStage);
  const [user, setUser] = useState("")
  useEffect(()=>{
    console.log("user now is " + user);
  },[user])
  //const name = "Jeet Inani";
  return (
    /* <div className="App">
      <h2>Welcome to react application</h2>
      <h2>My Name is {name}</h2>
      <Home uname={name}/>
    </div> */
    <Routes>
      <Route path='/' element={<Layout stage={stage}/>}>
        <Route index element={<Home stage={stage} user={user}/>}/>
        <Route path='about' element={<About/>}/>
        <Route path='login' element={<LoggingPage updateStage={updateStage} setUser={setUser}/>}/>
        <Route path='/logout' element={<Logout updateStage={updateStage}/>}/>
        <Route path='account/detail/:acno' element={ <Detail/> } />
      </Route>
    </Routes>  
  );
}

export default App;