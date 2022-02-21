import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok){
        return response;
    }

    const error = new Error(response.statusText);
    error.response = response;
    return Promise.reject(error);
}


export const gettAllCertusers = () =>
    fetch("api/v1/certuser")
    .then(checkStatus)

export const getAllCert = () =>
    fetch("api/v1/certstatus")
    .then(checkStatus)

export const getAllOrders = () =>
    fetch("/api/v1/orderreq")
    .then(checkStatus)