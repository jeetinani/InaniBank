import { Link } from "react-router-dom";
import { useContext } from "react";
import { UserContext } from "../context/UserContext";
//import LoginButton from "./LoginButton"


const LoginButton = ({stage}) => {
  //let isLoggedIn = false;
  if(/* window.sessionStorage.getItem("user-info") */stage==="loggedIn"){
    return <Link className="nav-link" to="logout">Logout</Link>
  }else {
    return <Link className="nav-link" to="login">Login</Link> 
  }       
}

export default function NavBar(){
  const context = useContext(UserContext);
  
  return (
      <nav className="navbar navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark">
        <div className="container-fluid">
          <Link className="navbar-brand" to="">Bank UI</Link>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item">
                <Link className="nav-link active" to="">Home</Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="about">About</Link>
              </li>
              <li className="nav-item">
                <LoginButton stage={context.stage}/>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    )
}