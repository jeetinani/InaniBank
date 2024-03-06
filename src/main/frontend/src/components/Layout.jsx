import { Outlet } from "react-router-dom";
import { useState } from "react";
import NavBar from "./NavBar";
import LoggingPage from './LoggingPage';
import Logout from "./Logout";

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