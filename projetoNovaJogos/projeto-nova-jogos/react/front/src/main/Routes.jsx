/* eslint-disable import/no-anonymous-default-export */
import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom'

import Home from '../components/home/home'
import UserCrud from '../components/user/userCrud'
import Posts from '../components/posts/posts'

export default props =>

    <Switch>
        <Route exact path='/' component={Home} />
        <Route path='/users' component={UserCrud} />
        <Route path='/postagem' component={Posts} />
        <Redirect from='*' to='/' />
    </Switch>
