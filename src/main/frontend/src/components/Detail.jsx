import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

export default function Detail() {
    const params = useParams();
    const [accountDetails, setAccountDetails] = useState({
        account: null,
        transactions: []
    })

    useEffect(() => {
        let token = window.sessionStorage.getItem("user-info");
        if (token) {
            //let token = JSON.parse(userInfo).token;
            let options = {
                headers: {
                    "Authorization": `Bearer ${token}`
                }
            };
            Promise.all([
                axios.get(`/api/accounts/${params.acno}`, options),
                axios.get(`/api/accounts/${params.acno}/transactions`, options)
            ]).then(([accResponse, transactionsResponse])=> {
                setAccountDetails({
                    account: accResponse.data,
                    transactions: transactionsResponse.data
                })
            });
        }
    }, [params])


    return (
        <div>
            <div>
                <h3>Account Details</h3>
                {accountDetails.account &&
                    <dl>
                        <dt>Account Number</dt>
                        <dd>{accountDetails.account.accountNumber}</dd>
                        <dt>Account Holder</dt>
                        <dd>{accountDetails.account.accountHolder}</dd>
                        <dt>Account Type</dt>
                        <dd>{accountDetails.account.accountType}</dd>
                        <dt>Balance</dt>
                        <dd>{accountDetails.account.balance}</dd>
                        <dt>Start Date</dt>
                        <dd>{accountDetails.account.openingDate}</dd>
                        <dt>PAN Number</dt>
                        <dd>{accountDetails.account.panCardNumber}</dd>
                        <dt>Aadhar Number</dt>
                        <dd>{accountDetails.account.aadharCardNumber}</dd>
                        <dt>Mobile</dt>
                        <dd>{accountDetails.account.mobile}</dd>
                        <dt>Email</dt>
                        <dd>{accountDetails.account.email}</dd>
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
                            accountDetails.transactions.map((trn, index) => {
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