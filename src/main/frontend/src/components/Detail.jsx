import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { saveAs } from "file-saver";



export default function Detail() {
    const params = useParams();
    const [accountDetails, setAccountDetails] = useState({
        account: null,
        transactions: []
    })
    const [outcome, setOutcome] = useState("");

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
            ]).then(([accResponse, transactionsResponse]) => {
                setAccountDetails({
                    account: accResponse.data,
                    transactions: transactionsResponse.data
                })
            });
        }
    }, [params])

    const getStatement = () => {
        let token = window.sessionStorage.getItem("user-info");
        let options = {
            headers: {
                "Authorization": `Bearer ${token}`,
            }
        };
        axios.get(`/api/statement/${params.acno}`, options)
            .then(response => {
                console.log("Get request successful:" + response.data);
                const url = response.data;
                if (url.startsWith("https://objectstorage")) {
                    // If the URL is pointing to an external storage, open it in a new tab
                    // window.open(url, "_blank");
                    setOutcome(
                        <a href={url} target="_blank" rel="noopener noreferrer" >
                            Open AccountStatement_{params.acno}
                        </a>
                    );
                } else {
                    // Otherwise, download the file directly from the backend
                    // Set the outcome to a link that triggers a download
                    setOutcome(
                        /* <a href="#" onClick={(e) => downloadFile(e, url, options)}>
                            AccountStatement_{params.acno}
                        </a> */
                        <button onClick={() => downloadFile(null, url, options)} style={{ background: 'none', border: 'none', color: 'blue', textDecoration: 'underline', cursor: 'pointer' }}>
                            Download AccountStatement_{params.acno}
                        </button>
                    );
                }
            })
            .catch(error => {
                console.log("Get request failed:", error);
                setOutcome("Failed to generate statement");
            });
    };

    const downloadFile = (e, url, options) => {
        e.preventDefault(); // Prevent the default anchor behavior
        axios.get(url, {
            headers: options.headers,
            responseType: 'blob' // Important to handle the response as a file
        })
            .then(response => {
                const contentDisposition = response.headers['content-disposition'];
                let filename = `AccountStatement_${params.acno}.csv`;

                if (contentDisposition) {
                    const filenameMatch = contentDisposition.match(/filename="(.+)"/);
                    if (filenameMatch && filenameMatch.length === 2) {
                        filename = filenameMatch[1];
                    }
                }

                saveAs(response.data, filename);
            })
            .catch(error => {
                console.log("File download failed:", error);
                setOutcome("Failed to download the file.");
            });
    };


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
                <button onClick={getStatement}>Generate Statement</button>
                {outcome}
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