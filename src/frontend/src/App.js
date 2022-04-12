import React from 'react';
import {
    BrowserRouter,
    Routes,
    Route,
    Link
} from "react-router-dom";
import Companypage from "./components/Companypage";
import LoginPage from "./components/LoginPage";
import {Dashboard} from "./components/dashboard/dashboard";





// Columns/table





function App() {

    return <Companypage></Companypage>
 //    return (
 //
 //        <BrowserRouter>
 //            <Routes>
 //                <Route exact path="/" component={LoginPage}/>
 //                <Route exact path="/dashboard" component={Dashboard}/>
 //            </Routes>
 //        </BrowserRouter>
 //        );

    //<Route exact path="/companypage" component={Companypage}/>

}
    export default App;
