import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, browserHistory, IndexRoute } from 'react-router-dom';
import { App, Home, Login, Register } from 'containers';

const rootElement = document.getElementById('root');
ReactDOM.render(
    <BrowserRouter history={browserHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={Home}/>
            <Route path="home" component={Home}/>
            <Route path="login" component={Login}/>
            <Route path="register" component={Register}/>
        </Route>
    </BrowserRouter>, rootElement
);