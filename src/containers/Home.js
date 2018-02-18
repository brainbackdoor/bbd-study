import React from 'react';
import { connect } from 'react-redux';
import { Write, MemoList } from 'components';
import { memoPostRequest,
         memoListRequest, 
         memoEditRequest, 
         memoRemoveRequest,   
         memoRemoveFromData  
} from 'actions/memo';
import Memo from '../components/Memo';


class Home extends React.Component {
    constructor(props){
        super(props);
        this.handlePost = this.handlePost.bind(this);
        this.loadNewMemo = this.loadNewMemo.bind(this);
        this.loadOldMemo = this.loadOldMemo.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
        this.handleRemove = this.handleRemove.bind(this);
        this.state = {
            loadingState: false
        };
    }

    handlePost(contents) {
        return this.props.memoPostRequest(contents).then(
            () => {
                if(this.props.postStatus.status === "SUCCESS"){
                    // Trigger load new memo
                    this.loadNewMemo().then(
                        () => {
                            Materialize.toast('Success!', 2000);
                        }
                    );
                } else {
                    /*
                        ERROR CODES
                            1: NOT LOGGED IN
                            2: EMPTY CONTENTS
                    */
                    let $toastContent;
                    switch(this.props.postStatus.error) {
                        case 1: 
                            // if not logged in, notify and refresh after
                            $toastContent = $('<span style="color: #FEB4BA">You are not logged in</span>');
                            Materialize.toast($toastContent, 2000);
                            setTimeout(() => {location.reload(false);}, 2000);
                            break;
                        case 2: 
                            $toastContent = $('<span style="color: #FFB4BA">Please write something</span>');
                            Materialize.toast($toastContent, 2000);
                            break;
                        default:
                            $toastContent = $('<span style="color: #FFB4BA">Something Broke</span>');
                            Materialize.toast($toastContent, 2000);
                            break;
                    }
                }
            }
        );
    }

    handleEdit(id, index, contents) {
        return this.props.memoEditRequest(id, index, contents).then(
            () => {
                if(this.props.editStatus.status ==="SUCCESS") {
                    Materialize.toast('Success!', 2000);
                } else {
                    /*
                        ERROR CODES
                            1: INVALID ID,
                            2: EMPTY CONTENTS
                            3: NOT LOGGED IN
                            4: NO RESOURCE
                            5: PERMISSION FAILURE
                    */
                    let errorMessage = [
                        'Something broke',
                        'Please write soemthing',
                        'You are not logged in',
                        'That memo does not exist anymore',
                        'You do not have permission'
                    ];
                    
                    let error = this.props.editStatus.error;
                    
                    // NOTIFY ERROR
                    let $toastContent = $('<span style="color: #FFB4BA">' + errorMessage[error - 1] + '</span>');
                    Materialize.toast($toastContent, 2000);
                
                    // IF NOT LOGGED IN, REFRESH THE PAGE AFTER 2 SECONDS
                    if(error === 3) {
                        setTimeout(()=> {location.reload(false)}, 2000);
                    }
                }
            }
        );
    }
    handleRemove(id, index) {
        this.props.memoRemoveRequest(id, index).then(() => {
            if(this.props.removeStatus.status==="SUCCESS") {
                // LOAD MORE MEMO IF THERE IS NO SCROLLBAR
                // 1 SECOND LATER. (ANIMATION TAKES 1SEC)
                setTimeout(() => { 
                    if($("body").height() < $(window).height()) {
                        this.loadOldMemo();
                    }
                }, 1000);
            } else {
                // ERROR
                /*
                    DELETE MEMO: DELETE /api/memo/:id
                    ERROR CODES
                        1: INVALID ID
                        2: NOT LOGGED IN
                        3: NO RESOURCE
                        4: PERMISSION FAILURE
                */
                let errorMessage = [
                    'Something broke',
                    'You are not logged in',
                    'That memo does not exist',
                    'You do not have permission'
                ];
                
                 // NOTIFY ERROR
                let $toastContent = $('<span style="color: #FFB4BA">' + errorMessage[this.props.removeStatus.error - 1] + '</span>');
                Materialize.toast($toastContent, 2000);


                // IF NOT LOGGED IN, REFRESH THE PAGE
                if(this.props.removeStatus.error === 2) {
                    setTimeout(()=> {location.reload(false)}, 2000);
                }
            }
        });
    }
    

    componentDidMount() {
        // Load Ne Memo Every 5 seconds
        const loadMemoLoop = () => {
            this.loadNewMemo().then(
                () => {
                    this.memoLoaderTimeoutId = setTimeout(loadMemoLoop, 5000);
                }
            );
        }
        this.props.memoListRequest(true).then(
            () => {
                loadMemoLoop();
            }
        );
        const loadUntilScrollable = () => {
            // IF THE SCROLLBAR DOES NOT EXIST,
            if($("body").height() < $(window).height()) {
                this.loadOldMemo().then(
                    () => {
                        // DO THIS RECURSIVELY UNLESS IT'S LAST PAGE
                        if(!this.props.isLast) {
                            loadUntilScrollable();
                        }
                    }
                );
            }
        };
        
        this.props.memoListRequest(true).then(
            () => {
                // BEGIN NEW MEMO LOADING LOOP
                loadUntilScrollable();
                loadMemoLoop();
            }
        );
        $(window).scroll(() => {
            // WHEN HEIGHT UNDER SCROLLBOTTOM IS LESS THEN 250
            if ($(document).height() - $(window).height() - $(window).scrollTop() < 250) {
                if(!this.state.loadingState){
                    this.loadOldMemo();
                    this.setState({
                        loadingState: true
                    });
                } else {
                    if(this.state.loadingState) {
                        this.setState({
                            loadingState: false
                        });
                    }
                }
            }
        });
    }
    componentWillUnmount() {
        // Stops the loadMemoLoop
        clearTimeout(this.memoLoaderTimeoutId);

        // Remove windows scroll listener
        $(window).unbind();
    }
    loadNewMemo() {
        // Cancel if there is a pending request
        if(this.props.listStatus === 'WAITING')
            return new Promise((resolve, reject) => {
                resolve();
            });
        // If page is empty, do the initial loading
        if(this.props.memoData.length === 0) 
            return this.props.memoListRequest(true);

        return this.props.memoListRequest(false, 'new', this.props.memoData[0]._id);
    }
    loadOldMemo() {
        // Cancel if user is reading the last page
        if(this.props.isLast){
            return new Promise(
                (resolve, reject) => {
                    resolve();
                }
            );
        }

        // Get id of the memo at the bottom
        let lastId = this.props.memoData[this.props.memoData.length - 1]._id;

        // Start request
        return this.props.memoListRequest(false, 'old', lastId).then(() => {
            // If it is last page, notify
            if(this.props.isLast) {
                Materialize.toast('You are reading the last page', 2000);
            }
        });
    }
    render() {
        
        const write = ( 
            <Write onPost={this.handlePost}/> 
        );


        return (
            <div className="wrapper">
                { this.props.isLoggedIn ? write : undefined }
                <MemoList data={this.props.memoData} 
                currentUser={this.props.currentUser}
                onEdit={this.handleEdit}
                onRemove={this.handleRemove}/>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        isLoggedIn: state.authentication.status.isLoggedIn,
        postStatus: state.memo.post,
        currentUser: state.authentication.status.currentUser,
        memoData: state.memo.list.data,
        listStatus: state.memo.list.status,
        isLast: state.memo.list.isLast, 
        editStatus: state.memo.edit,
        removeStatus: state.memo.remove
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        memoPostRequest: (contents) => {
            return dispatch(memoPostRequest(contents));
        },
        memoListRequest: (isInitial, listType, id, username) => {
            return dispatch(memoListRequest(isInitial, listType, id, username));
        },
        memoEditRequest: (id, index, contents) => {
            return dispatch(memoEditRequest(id, index, contents));
        },
        memoRemoveRequest: (id, index) => {
            return dispatch(memoRemoveRequest(id, index));
        }                
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Home);