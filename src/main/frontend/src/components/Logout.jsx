import { useNavigate } from "react-router-dom";

export default function Logout({updateStage}){
    window.sessionStorage.removeItem("user-info");
    const navigate = useNavigate();
    updateStage("loggedOut");
    navigate("/?action=logout");
}