import { Outlet } from "react-router-dom";
import NavBar from "./NavBar";

export default function Layout({stage}){
    return (
        <>     
        <NavBar stage={stage}/>
        <div className="container">
            <Outlet/>
        </div>
        </>
    )
};