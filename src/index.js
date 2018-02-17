import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { App, Home, Login, Register } from 'containers';

const rootElement = document.getElementById('root');
ReactDOM.render(
    <BrowserRouter>
        <div>
        <Route exact path="/" component={App}/>    
        <Route exact path="/" component={Home}/>
        
        <Route exact path="/login" component={Login}/>
        <Route exact path="/register" component={Register}/>

            
        </div>
    </BrowserRouter>, rootElement
);