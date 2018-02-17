import React from 'react';

class Write extends React.Component {
    render() {
        return (
            <div className = "container writer">
                <div className="card">
                    <div className="card-content">
                        <textarea className="materialize-textarea" placeholder="Write down your memo"></textarea>
                    </div>
                <div className="card-action">
                    <a>POST</a>
                </div>
            </div>
        </div>
        );
    }
}

export default Write;