import axiosInstance from "./axiosInstance";


export const login  = (credentials) => {
    return axiosInstance.post(`auth/signIn`,credentials);
};

export const register = (userData) => {
    return axiosInstance.post(`users/signUp`,userData);
}