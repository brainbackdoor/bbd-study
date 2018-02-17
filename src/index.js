import React from 'react';
import ReactDOM from 'react-dom';

// Router
import { BrowserRouter, Route } from 'react-router-dom';

// Container Components
import { App, Home, Login, Register } from 'containers';

// Redux
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import reducers from 'reducers';
import thunk from 'redux-thunk';

const store = createStore(reducers, applyMiddleware(thunk))

const rootElement = document.getElementById('root');
ReactDOM.render(
    <Provider store={store}>
        <BrowserRouter>
        <div>
            <Route exact path="/" component={App}/>    
            <Route exact path="/" component={Home}/>
            <Route exact path="/login" component={Login}/>
            <Route exact path="/register" component={Register}/>
        </div>
        </BrowserRouter>
    </Provider>, rootElement
);