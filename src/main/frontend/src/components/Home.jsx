import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, Link } from "react-router-dom";

export default function Home({stage,user}) {
    /* constructor(){
        super();
         this.state ={
            accounts:[]
        }; 
        //this.increment = this.increment.bind(this);
        console.log("Constructor executed");
    }*/

    /* static getDerivedStateFromProps(props, state){
        console.log("getDerivedStateFromProps executed")
        return { } //return the state object
    } */

    /* shouldComponentUpdate(nextProps, nextState){
        console.log(nextState)
        console.log("shouldComponentUpdate executed");
        if(nextState.count %2 === 0 )
            return true;
        else
            return false;
    } */

    /* increment(){
        this.setState({
            count: this.state.count + 1
        })
    } */


    //equivalent to componentDidMount and componentDidUpdate
    //useEffect(callbackFn, dependencyList)
    //if dependencyList is not mentioned then it executes when component mounted or updated
    //if empty array then executes only on mount
    //if an array of variables then executes on mount and when the variable value changes
    console.log("stage passed to home = "+stage);
    console.log("user passed to home is "+user)
    const [accounts,setAccounts] = useState([]);

    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const actionParam = queryParams.get('action');


    const api = axios.create({
        baseURL: '/api',  // Use the same path as specified in setupProxy.js
    });
    useEffect(()=>{
        let userInfo = window.sessionStorage.getItem("user-info");
        if (userInfo) {
            //let token = JSON.parse(userInfo).token;
            let options = {
                headers: {
                    "Authorization": `${userInfo}`,
                }
            };
            api.get(`/accounts`, options)
                .then(
                    resp => setAccounts(resp.data)
                )
        }
    },[api]);

    return (
        <>
            <>
            {actionParam==="loggedOut" ? (<div>Logged Out</div>): ( <></> )}
            </>
            {stage==="loggedIn" ? (
            <div className="row">
                <div className="col-10 mx-auto">
                    <h3>Welcome {user} to Inani Bank</h3>
                    <h3>Accounts List</h3>
                    <table className="table table-sm table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Account No</th>
                                <th>Account Holder</th>
                                <th>Type</th>
                                <th>Balance</th>
                                <th>Start Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                accounts.map((acc, index) => {
                                    return <tr key={index} >
                                        <td>
                                            <Link to={`account/detail/${acc.accountNumber}`}>
                                                {acc.accountNumber}
                                            </Link>
                                        </td>
                                        <td>{acc.accountHolder}</td>
                                        <td>{acc.accountType}</td>
                                        <td>{acc.balance}</td>
                                        <td>{acc.openingDate}</td>
                                    </tr>
                                })
                            }

                        </tbody>
                    </table>
                </div>
            </div >):(
            <div>
                <h3>Welcome to Inani Bank</h3>
                <h3>Please Login to Continue</h3>
            </div>)}
        </>
    );


    /* componentDidMount(){
        console.log("componentDidMount executed")

        let userInfo = window.sessionStorage.getItem("user-info");
        if(userInfo){
            let token = JSON.parse(userInfo).token;
            let options = {
                headers:{
                    "Authorization":`Bearer ${token}`
                }
            };
            axios.get("http://localhost:8080/api/accounts", options )
            .then(
                resp=> this.setState({
                    accounts: resp.data
                })
            )
        }
        
    } */


    /* getSnapshotBeforeUpdate(prevProps, prevState){
        console.log("getSnapshotBeforeUpdate executed")
        return null
    } */

    /* componentDidUpdate(){
        console.log("componentDidUpdate executed")
    }

    componentWillUnmount(){
        console.log("componentWillUnmount executed")
    } */
}

/* function Home(){
    return (
        <div>
            <h4>Present in Home Component</h4>
            <h4>Name passed is</h4>                
        </div>
    )
} */

//export default Home;