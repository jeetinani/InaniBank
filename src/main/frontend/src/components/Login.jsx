import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login(){

    const [credentials,setCredentials] = useState({
        username:"",
        password:""
    });

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

        //console.log(JSON.stringify(credentials));
    }
 
    const navigate = useNavigate();
    const submitHandler = (e)=>{
        e.preventDefault();
        console.log(JSON.stringify(credentials));
        axios.post("http://localhost:8081/users/login",credentials)
        .then(resp=>{
            window.sessionStorage.setItem("user-info", JSON.stringify(resp.data));
            navigate("/")
        })
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
                    </form>
                </div>
            </div>
        </>
    )   
}