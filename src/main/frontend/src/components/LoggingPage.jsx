import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function LoggingPage({updateContext}){

    //const {updateStage} = props;
    //console.log("type in loggingPage is "+typeof updateStage);
    const [credentials,setCredentials] = useState({
        username:"",
        password:""
    });
    const [alert, setAlert] = useState("");

    const textNameChangeHandler = (e)=>{
        e.preventDefault();
        if(e.target.name==="username"){
            setCredentials({
                ...credentials,
                //password:credentials.password,
                username:e.target.value
            })
        }else{
            setCredentials({
                ...credentials,
                //userName:credentials.userName,
                password:e.target.value
            })
        }
        if(alert!==""){
            setAlert("")
        }
        //console.log(JSON.stringify(credentials));
    }

    const api = axios.create({
        baseURL: '/api',  // Use the same path as specified in setupProxy.js
    });
 
    const navigate = useNavigate();
    const submitHandler = (e)=>{
        e.preventDefault();
        console.log(JSON.stringify(credentials));
        api.post("/users/login",credentials)
        .then(resp=>{
            window.sessionStorage.setItem("user-info", resp.data);
            updateContext({stage:"loggedIn",
            user:credentials.username});
            navigate(`/`);
        }).catch((error) => {
            // Handle errors, if any
            //console.log(JSON.stringify(error));
            console.error("Login failed:", error);
            if(error.response.status===400){
                setAlert("Invalid Credentials");
            }
        });
    }

    return (
        <>
            <div className="row">
                <div className="col:6 mx-auto">
                    <h3>Sign In</h3>
                    <form onSubmit={submitHandler}>
                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">UserName</label>
                        <input type="text" className="form-control" id="username" /* value={credentials.userName} */ onChange={textNameChangeHandler}
                        name="username" placeholder="User Name"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Password</label>
                        <input type="password" className="form-control" id="password" /* value={credentials.password} */ onChange={textNameChangeHandler}
                        name="password" placeholder="Password"/>
                    </div>
                    <div className="mb-3">
                        <button type="submit" className="btn btn-success" name="submit">Login</button>
                    </div>
                    <div>
                        {alert}
                    </div>
                    </form>
                </div>
            </div>
        </>
    )   
}