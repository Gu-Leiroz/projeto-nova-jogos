import React, { Component } from 'react';
import Main from '../template/Main'

const headerProps = {

    icon: 'plus-square',
    title: 'Posts',
    subtitle: 'Register posts.'

}

export default class Posts extends Component {
    render() {
        return (
            <Main {...headerProps}>
                Register posts
            </Main>
        )
    }
}