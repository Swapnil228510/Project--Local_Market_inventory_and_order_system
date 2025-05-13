import { toast } from "react-toastify";
import { log } from "./logger";
import axiosInstance from "./axiosInstance";

async function handleRequest(requestFunction){
    try{
            const response =  await requestFunction();
            return response;
    }catch(error){
        log(error)
        if(error.code && error.code == "ERR_NETWORK"){
            toast.error("Can't connect to server at the moment! Please try again later")
        }else {
        toast.error("Some error occurred! Please try again");
        }

        return null;
    }
}


export async function getAllProduct(){
    return await handleRequest(()=>axiosInstance.get("/products"));
}