import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

export default function Detail() {
    const params = useParams();
    const [account, setAccount] = useState();
    const [transactions, setTransactions] = useState([]);

    const api = axios.create({
        baseURL: '/api',  // Use the same path as specified in setupProxy.js
    });
    useEffect(() => {
        let userInfo = window.sessionStorage.getItem("user-info");
        if (userInfo) {
            //let token = JSON.parse(userInfo).token;
            let options = {
                headers: {
                    "Authorization": `${userInfo}`
                }
            };
            api.get(`/accounts/${params.acno}`, options)
                .then(
                    resp => setAccount(resp.data)
                );
            api.get(`/accounts/${params.acno}/transactions`, options)
                .then(
                    resp =>  setTransactions(resp.data)
                );
        }
    })


    return (
        <div>
            <div>
                <h3>Account Details</h3>
                {account &&
                    <dl>
                        <dt>Account Number</dt>
                        <dd>{account.accountNumber}</dd>
                        <dt>Account Holder</dt>
                        <dd>{account.accountHolder}</dd>
                        <dt>Account Type</dt>
                        <dd>{account.accountType}</dd>
                        <dt>Balance</dt>
                        <dd>{account.balance}</dd>
                        <dt>Start Date</dt>
                        <dd>{account.openingDate}</dd>
                        <dt>PAN card</dt>
                        <dd>{account.panCardNumber}</dd>
                        <dt>Aadhar Number</dt>
                        <dd>{account.aadharCardNumber}</dd>
                        <dt>Mobile</dt>
                        <dd>{account.mobile}</dd>
                        <dt>Email</dt>
                        <dd>{account.email}</dd>
                    </dl>
                }
            </div>
            <div>
            <h3>Transactions</h3>
                <table className="table table-sm table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>TransactionId</th>
                            <th>Description</th>
                            <th>Type</th>
                            <th>Amount</th>
                            <th>Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            transactions.map((trn, index) => {
                                return <tr key={index} >
                                    <td>{trn.transactionId}</td>
                                    <td>{trn.description}</td>
                                    <td>{trn.accountTransactionType}</td>
                                    <td>{trn.amount}</td>
                                    <td>{trn.transactionTime}</td>
                                </tr>
                            })
                        }

                    </tbody>
                </table>
            </div>
        </div>
    )
}