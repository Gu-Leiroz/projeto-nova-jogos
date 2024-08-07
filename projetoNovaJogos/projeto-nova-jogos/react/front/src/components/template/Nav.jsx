/* eslint-disable import/no-anonymous-default-export */
import './Nav.css'
import React from 'react'
import {Link } from 'react-router-dom' 

export default props =>
    <aside className="menu-area">
        <nav className="menu">
            <Link to="/">
                <i className="fa fa-home"></i> Home
            </Link>
            <Link to="/users">
                <i className="fa fa-users"></i> Users
            </Link>

            <Link to="/postagem">
                <i className="fa fa-plus-square"></i> Posts
            </Link>
        </nav>
        
    </aside>