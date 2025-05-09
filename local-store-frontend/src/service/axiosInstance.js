import axios from 'axios'
import React from 'react'
import { baseURL } from '../appConfig';

const axiosInstance = axios.create({
    baseURL,
    headers :  {
        'context-type' : 'application/json',
    }
});

axiosInstance.interceptors.request.use((config)=>{
    const token  = localStorage.getItem('token');
    if(token){
        config.headers.Authorization =  `Bearer ${token}`
    }
    return config;
});


export default axiosInstance