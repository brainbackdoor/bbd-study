import {
    AUTH_LOGIN,
    AUTH_LOGIN_SUCCESS,
    AUTH_LOGIN_FAILURE
} from './AcationTypes';

import axios from 'axios';
/*============================================================================
    authentication
==============================================================================*/

// Login
export function loginRequest(username, password) {
    return (dispatch) => {
        // Inform Login API is starting
        dispatch(login());

        // API Request
        return axios.post('/api/account/signin', { username, password })
        .then((response) => {
            // succeed
            dispatch(loginSuccess(username))
        }).catch((error) => {
            // failed
            dispatch(loginFailure());
        });
    };
}

export function login(){
    return {
        type: AUTH_LOGIN
    };
}

export function loginSuccess(username){
    return {
        type: AUTH_LOGIN_SUCCESS,
        username
    };
}

export function loginFailure() {
    return {
        type: AUTH_LOGIN_FAILURE
    };
}