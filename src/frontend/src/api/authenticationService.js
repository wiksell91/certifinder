import React from 'react';
import axios from 'axios';


const getToken=()=>{
    return localStorage.getItem('certuser_id') && localStorage.getItem('company_id');
}

export const userLogin=(authRequest)=>{
    return axios({
        'method':'POST',
        'url':`${process.env.hostUrl||'http://localhost:8080'}/api/v1/auth/login`,
        'data':authRequest
    })
}

export const fetchUserData=(authRequest)=>{
    return axios({
        method:'GET',
        url:`${process.env.hostUrl||'http://localhost:8080'}/api/v1/auth/userinfo`,
        headers:{
            'Authorization':'Bearer '+getToken()
        }
    })
}