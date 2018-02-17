import React from 'react';
import { Link } from 'react-router-dom';

class Authentication extends React.Component {
    render() {
        const loginView = (
            <div>loginView</div>
        );

        const registerView = (
            <div>registerView</div>
        );

        return (
            <div className="container auth">
                <Link className="logo" to="/">MEMOPAD</Link>
                <div className="card">
                    <div className="header blue white-text center">
                        <div className="card-content">{this.props.mode ? "LOGIN" : "REGISTER"}</div>
                    </div>
                    {this.props.mode ? loginView : registerView }
                </div>
            </div>
        );
    }
}
Authentication.propTypes = {
    mode: React.PropTypes.bool,
    onLogin: React.PropTypes.func,
    onRegister: React.PropTypes.func
};

Authentication.defaultProps = {
    mode: true,
    onLogin: (id, pw) => {console.error("login function not defined");},
    onRegister: (id, pw) => {console.error("register function not defined");}
};

export default Authentication;