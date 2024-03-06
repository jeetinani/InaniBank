import { Route, Routes } from 'react-router-dom';
import './App.css';
import Layout from './components/Layout';
import About from './components/About';
import Home from './components/Home';
import Login from './components/Login';
import Detail from './components/Detail';
//import LearnMiddle from './components/LearnMiddle';
import { useEffect, useState } from 'react';

function App() {

  const [user, setUser] = useState({
    name:"Jeet",
    age:10,
    surname:"Inani"
  })

  useEffect(()=>{
    console.log("user now is " + JSON.stringify(user));
  },[user])

  //const name = "Jeet Inani";
  return (
    /* <div className="App">
      <h2>Welcome to react application</h2>
      <h2>My Name is {name}</h2>
      <Home uname={name}/>
    </div> */
    <Routes>
      <Route path='/' element={<Layout/>}>
        <Route index element={<Home/>}/>
        <Route path='about' element={<About/>}/>
        <Route path='login' element={<Login/>}/>
        <Route path='account/detail/:acno' element={ <Detail/> } />
      </Route>
    </Routes>  
  );
}

export default App;