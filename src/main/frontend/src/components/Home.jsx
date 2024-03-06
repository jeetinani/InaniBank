import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Home() {
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

    const [accounts,setAccounts] = useState([]);

    useEffect(()=>{
        let userInfo = window.sessionStorage.getItem("user-info");
        if (userInfo) {
            //let token = JSON.parse(userInfo).token;
            let options = {
                headers: {
                    "Authorization": `${userInfo}`,
                }
            };
            axios.get(`http://localhost:8081/accounts`, options)
                .then(
                    resp => setAccounts(resp.data)
                )
        }
    },[]);

    return (
    <div className="row">
            <div className="col-10 mx-auto">
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
    </div >
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